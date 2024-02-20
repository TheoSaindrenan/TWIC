import { OrderDetail } from "./OrderDetail.model";


export interface OrderRequest {

    order_id :number ;

    account_no : String ;

    order_status_id : number ;

    placed_timestamp : Date ;

    delivered_timestamp : Date ;

    cancelled_timestamp:Date ;

    orderDetails: OrderDetail[];


    order_status_name:String ;
}

