import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MaterialModule } from './material.module';
import { FlexLayoutModule } from '@angular/flex-layout';
import { RouterModule, Routes } from '@angular/router';
import { DataService } from './data.service';
import { CustomerListComponent } from './customer-list/customer-list.component';
import { MatPaginatorModule } from '@angular/material/paginator';
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
  declarations: [
    AppComponent,
    CustomerListComponent,
    ClientInfosComponent,
    OrderInfosComponent,
    OrderCreateComponent
  ],
  imports: [
    BrowserModule,
    MatPaginatorModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    BrowserAnimationsModule,
    MaterialModule,
    FlexLayoutModule,
    ReactiveFormsModule,
    [RouterModule.forRoot(routes)]
  ],
  providers: [DataService],
  bootstrap: [AppComponent],
  exports: [RouterModule]
})
export class AppModule { }