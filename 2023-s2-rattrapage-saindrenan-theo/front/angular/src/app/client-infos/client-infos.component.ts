import { Component, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { DataService } from '../data.service';
import { Customer } from '../models/Customer.model';
import { MatTableDataSource } from '@angular/material/table';
import { Order } from '../models/Order.model';
import { MatPaginator } from '@angular/material/paginator';

@Component({
  selector: 'app-client-infos',
  templateUrl: './client-infos.component.html',
  styleUrls: ['./client-infos.component.scss']
})
export class ClientInfosComponent implements OnInit {
  displayedColumns: string[] = ['order_id', 'placed_timestamp', 'order_status_name'];
  customer!: Customer; 
  pageSize: number = 5; 
  
  dataSource: MatTableDataSource<Order>;
  @ViewChild(MatPaginator, {static: true}) paginator!: MatPaginator;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private dataService: DataService) {
      this.dataSource = new MatTableDataSource<Order>();
     }

  async ngOnInit() {
    
    const account_no = this.route.snapshot.paramMap.get('account_no');

    this.customer = await this.dataService.getCustomer(account_no!);

    let orders = await this.dataService.getOrderFromAccount_no(account_no!);
    
    orders.sort((a, b) => {
      if (a.order_status_name < b.order_status_name) return -1;
      if (a.order_status_name > b.order_status_name) return 1;
      return new Date(b.placed_timestamp).getTime() - new Date(a.placed_timestamp).getTime();
    });
    this.dataSource.data = orders
    this.dataSource.paginator = this.paginator;
  }

  onPageChange(event: any) {
    this.pageSize = event.pageSize;
  }

  onSelectOrder(order_id: number) {
    this.router.navigate(['/order-infos', order_id]);
  }

  onCreateOrder() {
    this.router.navigate(['/order-create', this.customer.account_no]);
  }
}
