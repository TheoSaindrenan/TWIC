import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { DataService } from '../data.service';
import { Customer } from '../models/Customer.model';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';

@Component({
  selector: 'app-customer-list',
  templateUrl: './customer-list.component.html',
  styleUrls: ['./customer-list.component.scss']
})
export class CustomerListComponent implements OnInit {
  displayedColumns: string[] = ['account_no', 'first_name', 'last_name'];
  dataSource: MatTableDataSource<Customer>; 
  pageSize: number = 5; 
  searchText: string = '';
  showNoResultsMessage: boolean=false;
  
  @ViewChild(MatPaginator, {static: true}) paginator!: MatPaginator;

  constructor(
    private router: Router,
    private dataService: DataService,
  ) {
    this.dataSource = new MatTableDataSource<Customer>();
  }

  async ngOnInit() {
    this.dataSource.data = await this.dataService.getCustomers();
    this.dataSource.paginator = this.paginator;
  }

  search() {
    const searchText = this.searchText.toLowerCase().trim();
    this.dataSource.filter = searchText;
    const filteredItemsCount = this.dataSource.filteredData.length;
  
    if (filteredItemsCount === 0) {
      this.showNoResultsMessage = true;
    } else {
      this.showNoResultsMessage = false;
    }
  
    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }
  
  onPageChange(event: any) {
    this.pageSize = event.pageSize;
  }

  onSelectCustomer(account_no: number) {
    this.router.navigate(['/client-infos', account_no]);
  }
}
