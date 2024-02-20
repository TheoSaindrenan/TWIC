import { Observable } from 'rxjs';
import { Customer } from './models/Customer.model';
import { Order } from './models/Order.model';
import { OrderDetail } from './models/OrderDetail.model';
import { Product } from './models/Product.model';
import { RequestEmployee } from './models/RequestEmployee.model';
import { OrderRequest } from './models/OrderRequest.model';
export class DataService {

  async updateOrder(order: Order): Promise<any> {
    try {
      const response = await fetch('http://localhost:8080/orders', {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({ order })
      });

      if (!response.ok) {
        throw new Error(`HTTP error! Status: ${response.status}`);
      }

      const result = await response.json();
      return result;
    } catch (error) {
      console.error("Error saving order:", error);
      throw error;
    }
  }
  
  async saveOrder(order: Order, orderDetails: OrderDetail[]): Promise<any> {
    const request: OrderRequest = {
      order_id: Math.floor(Math.random() * 10000),
      account_no: order.account_no,
      order_status_id: Math.floor(Math.random() * 10000),
      placed_timestamp: order.placed_timestamp,
      delivered_timestamp: order.delivered_timestamp,
      cancelled_timestamp: order.cancelled_timestamp,
      orderDetails : orderDetails,
      order_status_name: 'PLACED'
    };
  

    try {
      const response = await fetch('http://localhost:8080/orders', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({ request })
      });

      if (!response.ok) {
        throw new Error(`HTTP error! Status: ${response.status}`);
      }

      const result = await response.json();
      return result;
    } catch (error) {
      console.error("Error saving order:", error);
      throw error;
    }
  }

  async getProducts(): Promise<Product[]> {
    try {
      const response = await fetch('http://localhost:8080/orders/products', {
        method: 'GET'
      });  
      if (!response.ok) {
        throw new Error(`HTTP error! Status: ${response.status}`);
      }  
      const result = await response.json();
      return result;
    } catch (error) {
      console.error("Error fetching customers:", error);
      throw error;  
    }
  }

  async getCustomers() : Promise<Customer[]>
  {  
    try {
      const response = await fetch('http://localhost:8080/customers', {
        method: 'GET'
      });  
      if (!response.ok) {
        throw new Error(`HTTP error! Status: ${response.status}`);
      }  
      const result = await response.json();
      return result;
    } catch (error) {
      console.error("Error fetching customers:", error);
      throw error;  
    }
  }

  async getCustomer(account_no: string) : Promise<Customer>
  {  
    try {
      const response = await fetch('http://localhost:8080/customers/account_number/'+ account_no, {
        method: 'GET'
      });  
      if (!response.ok) {
        throw new Error(`HTTP error! Status: ${response.status}`);
      }  
      const result = await response.json();
      return result;
    } catch (error) {
      console.error("Error fetching manager:", error);
      throw error;
    }
  }
 
  async getOrderFromAccount_no(account_no: string) : Promise<Order[]>
  {  
    try {
      const response = await fetch('http://localhost:8080/orders/account_number/'+ account_no, {
        method: 'GET'
      });  
      if (!response.ok) {
        throw new Error(`HTTP error! Status: ${response.status}`);
      }  
      const result = await response.json();
      return result;
    } catch (error) {
      console.error("Error fetching employees:", error);
      throw error;  
    }
  }

  async getOrderById(order_id: string) : Promise<Order>
  {  
    try {
      const response = await fetch('http://localhost:8080/orders/'+ order_id, {
        method: 'GET'
      });  
      if (!response.ok) {
        throw new Error(`HTTP error! Status: ${response.status}`);
      }  
      const result = await response.json();
      return result;
    } catch (error) {
      console.error("Error fetching employees:", error);
      throw error;  
    }
  }

  async getOrderDetailsById(order_id: string) : Promise<OrderDetail[]>
  {  
    try {
      const response = await fetch('http://localhost:8080/orders/'+ order_id + "/orderDetails", {
        method: 'GET'
      });  
      if (!response.ok) {
        throw new Error(`HTTP error! Status: ${response.status}`);
      }  
      const result = await response.json();
      return result;
    } catch (error) {
      console.error("Error fetching employees:", error);
      throw error;  
    }
  }

  async updateEmployeeDetails(employee: RequestEmployee): Promise<void> {
    try {
      const response = await fetch('http://localhost:8080/v1.0/employees', {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(employee), 
      });
  
      if (!response.ok) {
        throw new Error(`HTTP error! Status: ${response.status}`);
      }  
      const result = await response.json();  
      console.log('Employee details updated successfully:', result);
    } catch (error) {
      console.error('Error updating employee details:', error);
      throw error;
    }
  }

  async addEmployeeDetails(employee: RequestEmployee): Promise<void> {
    try {
      const response = await fetch('http://localhost:8080/v1.0/employees', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(employee), 
      });
  
      if (!response.ok) {
        throw new Error(`HTTP error! Status: ${response.status}`);
      }  
      const result = await response.json();  
      console.log('Employee details updated successfully:', result);
    } catch (error) {
      console.error('Error updating employee details:', error);
      throw error;
    }
  }

  async deleteEmployeeDetails(employeeId: number): Promise<void> {
    try {
      const response = await fetch('http://localhost:8080/v1.0/employees/' + employeeId, {
        method: 'DELETE',
      });
  
      if (!response.ok) {
        throw new Error(`HTTP error! Status: ${response.status}`);
      }
  
      console.log('Employee details deleted successfully');
    } catch (error) {
      console.error('Error deleting employee details:', error);
      throw error;
    }
  }
  

}
