package fr.eseo.twic.saindrenan.service;

import fr.eseo.twic.saindrenan.dto.OrderRequest;
import fr.eseo.twic.saindrenan.dto.OrderResponse;
import fr.eseo.twic.saindrenan.dto.OrderUpdateRequest;
import fr.eseo.twic.saindrenan.entity.Orders;
import fr.eseo.twic.saindrenan.entity.Products;
import fr.eseo.twic.saindrenan.entity.Order_details;
import fr.eseo.twic.saindrenan.entity.Order_status;
import fr.eseo.twic.saindrenan.dao.OrderDetailRepository;
import fr.eseo.twic.saindrenan.dao.OrderRepository;
import fr.eseo.twic.saindrenan.dao.OrderStatusRepository;
import fr.eseo.twic.saindrenan.dao.ProductRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderStatusRepository orderStatusRepository;
    
    @Autowired
    private ProductRepository productRepository;

    public List<Orders> getOrders() {
        return orderRepository.findAll();
    }

    public List<OrderResponse> getOrdersByNumber(String number) {
        List<OrderResponse> resp = new ArrayList<>();
        List<Orders> orders = orderRepository.findByAccountNo(number);
        for (Orders order : orders) {            
            Order_status order_status= orderStatusRepository.findByOrderStatusId(order.getOrder_status_id());
            OrderResponse orderResponse = new OrderResponse(); 
            orderResponse.setAccount_no(order.getAccount_no());
            orderResponse.setOrder_id(order.getOrder_id());
            orderResponse.setCancelled_timestamp(order.getCancelled_timestamp());
            orderResponse.setDelivered_timestamp(order.getDelivered_timestamp());
            orderResponse.setPlaced_timestamp(order.getPlaced_timestamp());
            orderResponse.setOrder_status_name(order_status.getOrder_status_name());

            resp.add(orderResponse);
        }
        return resp;
        
    }

    public Orders getOrderById(BigInteger id) {
        Orders order = orderRepository.findByOrder_id(id);
        if (order != null) {
            return order;
        }
        return null;
    }

    public Products getProductById(BigInteger id) {
        Products products = productRepository.findByProduct_id(id);
        if (products != null) {
            return products;
        }
        return null;
    }

    public List<Order_details> getOrderDetailsById(BigInteger id) {
        List<Order_details> order_detail = orderDetailRepository.findByOrderId(id);
        if (order_detail != null) {
            return order_detail;
        }
        return null;
    }

    public List<Products> getProducts() {
        List<Products> products = productRepository.findAll();
        if (products != null) {
            return products;
        }
        return null;
    }

    public boolean updateOrder(OrderUpdateRequest orderRequest) {
        Orders order = orderRepository.findByOrder_id(orderRequest.getOrder_id());
        if (order != null) {
            order.setCancelled_timestamp(orderRequest.getCancelled_timestamp());
            order.setDelivered_timestamp(orderRequest.getDelivered_timestamp());
            order.setPlaced_timestamp(orderRequest.getPlaced_timestamp());

            Order_status order_status = orderStatusRepository.findByOrderStatusId(order.getOrder_status_id());
            order_status.setOrder_status_name(orderRequest.getOrder_status_name());
            orderStatusRepository.save(order_status);
            
            orderRepository.save(order);
            return true;
        }
        return false;
    }

    public boolean deleteOrder(BigInteger id) {
        Orders order = orderRepository.findByOrder_id(id);
        if (order != null) {
            orderRepository.delete(order);

            List<Order_details> order_detail = orderDetailRepository.findByOrderId(id);
            orderDetailRepository.deleteAll(order_detail);

            Order_status order_status = orderStatusRepository.findByOrderStatusId(order.getOrder_status_id());
            orderStatusRepository.delete(order_status);

            return true;
        }
        return false;
    }

    public Orders createOrder(OrderRequest orderRequest) {
        Orders order = new Orders(
            orderRequest.getOrder_id(),
            orderRequest.getAccount_no(),
            orderRequest.getOrder_status_id(),
            orderRequest.getPlaced_timestamp(),
            orderRequest.getDelivered_timestamp(),
            orderRequest.getCancelled_timestamp());

        orderDetailRepository.saveAll(Arrays.asList(orderRequest.getOrderDetails()));

        Order_status order_status = new Order_status();
        order_status.setOrder_status_id(orderRequest.getOrder_status_id());
        order_status.setOrder_status_name(orderRequest.getOrder_status_name());
        orderStatusRepository.save(order_status);
        
        orderRepository.save(order);
        return order;
    }
}
