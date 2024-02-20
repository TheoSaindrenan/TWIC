package fr.eseo.twic.saindrenan.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import fr.eseo.twic.saindrenan.entity.Order_details;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<Order_details, BigInteger> {

    @Query("SELECT od FROM Order_details od WHERE od.order_id = :orderId")
    List<Order_details> findByOrderId(BigInteger orderId);

    @Query("SELECT od FROM Order_details od WHERE od.product_id = :productId")
    List<Order_details> findByProductId(BigInteger productId);

    @Query("SELECT od FROM Order_details od WHERE od.order_detail_id = :orderDetailId")
    Order_details findByOrderDetailId(BigInteger orderDetailId);

}
