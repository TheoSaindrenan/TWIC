import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { Order } from '../models/Order.model';
import { ActivatedRoute, Router } from '@angular/router';
import { DataService } from '../data.service';
import { MatTableDataSource } from '@angular/material/table';
import { OrderDetail } from '../models/OrderDetail.model';

@Component({
  selector: 'app-order-infos',
  templateUrl: './order-infos.component.html',
  styleUrls: ['./order-infos.component.scss']
})
export class OrderInfosComponent implements OnInit{
  
  displayedColumns: string[] = ['product_id', 'product_name', 'quantity', 'price'];
  isModified: boolean = false;
  initialOrder: any;
  order!: Order;
  total: number = 0;

  dataSource: MatTableDataSource<OrderDetail>;

  orderStatusOptions = [
    { value: 'PLACED', viewValue: 'PLACED' },
    { value: 'DELIVERED', viewValue: 'DELIVERED' },
    { value: 'CANCELLED', viewValue: 'CANCELLED' }
  ];

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private dataService: DataService) {
      this.dataSource = new MatTableDataSource<OrderDetail>();
    }

  async ngOnInit() {
    
    const order_id = this.route.snapshot.paramMap.get('order_id');

    this.dataSource.data  = await this.dataService.getOrderDetailsById(order_id!);

    for (const detail of this.dataSource.data) {
      this.total += detail.price * detail.quantity ;
    }

    this.order = await this.dataService.getOrderById(order_id!);
    this.initialOrder =this.order;
  }

  get isDeliveredOrCancelled(): boolean {
    return this.order.order_status_name === 'DELIVERED' || this.order.order_status_name === 'CANCELLED';
  }

  onStatusChange() {
    if (this.isDeliveredOrCancelled) {
      const currentDate =  new Date();
      if (this.order.order_status_name === 'DELIVERED') {
        this.order.delivered_timestamp = currentDate;
      } else if (this.order.order_status_name === 'CANCELLED') {
        this.order.cancelled_timestamp = currentDate;
      }
    }
  }

  modifyOrder() { 
    this.dataService.updateOrder(this.order)
      .then(  
        response => {
          this.router.navigate(['/client-infos', this.order.account_no]);
        },
        error => {
          console.error('Error saving order:', error);
        }
      );
   
  }

  onInputChange() {
    this.isModified = true;
  }
}
