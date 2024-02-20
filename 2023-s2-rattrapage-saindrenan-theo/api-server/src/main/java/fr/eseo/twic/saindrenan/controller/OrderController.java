package fr.eseo.twic.saindrenan.controller;

import fr.eseo.twic.saindrenan.dto.OrderDetailsResponse;
import fr.eseo.twic.saindrenan.dto.OrderRequest;
import fr.eseo.twic.saindrenan.dto.OrderResponse;
import fr.eseo.twic.saindrenan.dto.OrderUpdateRequest;
import fr.eseo.twic.saindrenan.entity.Order_details;
import fr.eseo.twic.saindrenan.entity.Orders;
import fr.eseo.twic.saindrenan.entity.Products;
import fr.eseo.twic.saindrenan.service.OrderService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;


    @GetMapping
    public ResponseEntity<List<Orders>> getAllOrders() {
        List<Orders> orders = orderService.getOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/account_number/{number}")
    public ResponseEntity<List<OrderResponse>> getOrdersByNumber(@PathVariable String number) {
        List<OrderResponse> orders = orderService.getOrdersByNumber(number);
        if (orders != null) {
            return new ResponseEntity<List<OrderResponse>>(orders, HttpStatus.OK);
        } else {
            return new ResponseEntity<List<OrderResponse>>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{order_id}")
    public ResponseEntity<Orders> getOrderById(@PathVariable BigInteger order_id) {
        Orders order = orderService.getOrderById(order_id);
        if (order != null) {
            return new ResponseEntity<Orders>(order, HttpStatus.OK);
        } else {
            return new ResponseEntity<Orders>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{order_id}/orderDetails")
    public ResponseEntity<List<OrderDetailsResponse>> getOrderDetailsById(@PathVariable BigInteger order_id) {
        List<OrderDetailsResponse> orderDetailsResponseFinal = new ArrayList<>();
        List<Order_details> orders = orderService.getOrderDetailsById(order_id);
        for (Order_details order : orders) {
            Products product = orderService.getProductById(order.getProduct_id());
            OrderDetailsResponse orderDetailsResponse = new OrderDetailsResponse();
            orderDetailsResponse.setOrder_detail_id(order.getOrder_detail_id());
            orderDetailsResponse.setOrder_id(order.getOrder_id());
            orderDetailsResponse.setPrice(product.getPrice());
            orderDetailsResponse.setProduct_id(order.getProduct_id());
            orderDetailsResponse.setProduct_name(product.getProduct_name());
            orderDetailsResponse.setQuantity(order.getQuantity());
            orderDetailsResponseFinal.add(orderDetailsResponse);
        }
        return new ResponseEntity<List<OrderDetailsResponse>>(orderDetailsResponseFinal, HttpStatus.OK);
    }

    @GetMapping("/products")
    public ResponseEntity<List<Products>> getProducts() {
        List<Products> products = orderService.getProducts();
        if (products != null) {
            return new ResponseEntity<List<Products>>(products, HttpStatus.OK);
        } else {
            return new ResponseEntity<List<Products>>(HttpStatus.BAD_REQUEST);
        }
    }
    

    @PutMapping
    public ResponseEntity<String> updateOrder(@RequestBody OrderUpdateRequest orderRequest) {
        boolean done = orderService.updateOrder(orderRequest);
        if (!done) {
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>( HttpStatus.OK);
        }
    }


    @DeleteMapping("/{order_id}")
    public ResponseEntity<String> deleteOrder(@PathVariable BigInteger order_id) {
        boolean done = orderService.deleteOrder(order_id);
        if (!done) {
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>( HttpStatus.OK);
        }
    }

    @PostMapping
    public ResponseEntity<Orders> createOrder(@RequestBody OrderRequest orderRequest) {
        Orders order = orderService.createOrder(orderRequest);
        if (order != null) {
            return new ResponseEntity<Orders>(order, HttpStatus.OK);
        } else {
            return new ResponseEntity<Orders>(HttpStatus.BAD_REQUEST);
        }
    }


}