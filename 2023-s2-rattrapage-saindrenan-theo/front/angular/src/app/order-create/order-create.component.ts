
import { Component, OnInit } from '@angular/core';
import { Order } from '../models/Order.model';
import { ActivatedRoute, Router } from '@angular/router';
import { DataService } from '../data.service';
import { OrderDetail } from '../models/OrderDetail.model';
import { Product } from '../models/Product.model';
import { FormArray, FormBuilder, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-order-create',
  templateUrl: './order-create.component.html',
  styleUrls: ['./order-create.component.scss']
})
export class OrderCreateComponent implements OnInit{
   total: number = 0;
  order: Order = {
    order_id: 0, 
    account_no: '',
    placed_timestamp: new Date(),
    delivered_timestamp: null as any,
    cancelled_timestamp: null as any,
    order_status_name: 'PLACED'
  };

  account_no!: string | null;

  products: Product[] = [];
  productCodes: String[] = [];
  productNames: String[] = [];

  productCodeSelected: String[] = [];
  productNameSelected: String[] = [];
  productIdSelected: number[] = [];
  quantities: number[] = [];

  orderStatusOptions = [
    { value: 'PLACED', viewValue: 'PLACED' },
    { value: 'DELIVERED', viewValue: 'DELIVERED' },
    { value: 'CANCELLED', viewValue: 'CANCELLED' }
  ];

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private dataService: DataService,
    private formBuilder: FormBuilder) {
      this.tableauForm = this.formBuilder.group({
        rows: this.formBuilder.array([])
      });
      
    }

  async ngOnInit() {

    this.rows.valueChanges.subscribe(values => {
      for (const row of values) {
        const quantity = row.quantity;
        this.quantities.push(quantity);
      }
    });

    this.products = await this.dataService.getProducts();
    
    for (const product of this.products) {
      this.productCodes.push(product.product_no);
      this.productNames.push(product.product_name);
    }

    this.account_no = this.route.snapshot.paramMap.get('account_no');
    this.order.account_no=this.account_no!;
    
  }

   saveOrder() {
    const dataSource : OrderDetail[] = [] ;

    for (let i = 0; i < this.rows.length; i++) {
      const quantity = this.rows.at(i).get('quantity')?.value;
      const orderDetail: OrderDetail = {
        order_id: this.order.order_id,
        product_id: this.productIdSelected[i],
        product_no: this.productCodeSelected[i],
        product_name: this.productNameSelected[i],
        quantity: this.quantities[i] ,
        order_detail_id: Math.floor(Math.random() * 10000),
        price: 0
      };

      console.log(orderDetail);
      dataSource.push(orderDetail);
    }
 
    this.dataService.saveOrder(this.order, dataSource)
      .then(
        response => {
          this.router.navigate(['/client-infos', this.account_no]);
        },
        error => {
          console.error('Error saving order:', error);
        }
      );
   }

  tableauForm: FormGroup;
  
  get rows(): FormArray {
    return this.tableauForm.get('rows') as FormArray;
  }

  addRow() {
    this.rows.push(this.formBuilder.group({
      product_no: [],
      product_name: [],
      quantity: [0]
    }));
  }

  removeRow() {
    this.rows.removeAt(this.rows.length-1);
  }

  onProductCodeSelected(index: number, code: string) {
    const product = this.products.find(p => p.product_no === code);
    
    if (product) {
      this.productCodeSelected[index] = product.product_no;
      this.productNameSelected[index]  = product.product_name;
      this.productIdSelected[index]  = product.product_id;
    }
  }

  onProductNameSelected(index: number, name: string) {
    const product = this.products.find(p => p.product_name === name);
    if (product) {
      this.productCodeSelected[index]  = product.product_no;
      this.productNameSelected[index]  = product.product_name;
      this.productIdSelected[index]  = product.product_id;
    }
  }

}
