import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { CustomerListComponent } from './customer-list/customer-list.component';
import { ClientInfosComponent } from './client-infos/client-infos.component';
import { OrderInfosComponent } from './order-infos/order-infos.component';
import { OrderCreateComponent } from './order-create/order-create.component';

const routes: Routes = [
  { path: '', component: CustomerListComponent },
  { path: 'client-infos/:account_no', component: ClientInfosComponent },
  { path: 'order-infos/:order_id', component: OrderInfosComponent },  
  { path: 'order-create/:account_no', component: OrderCreateComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
