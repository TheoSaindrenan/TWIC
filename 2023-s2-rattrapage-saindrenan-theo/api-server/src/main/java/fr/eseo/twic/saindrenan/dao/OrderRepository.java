package fr.eseo.twic.saindrenan.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import fr.eseo.twic.saindrenan.entity.Orders;

import java.math.BigInteger;

@Repository
public interface OrderRepository extends JpaRepository<Orders, BigInteger> {

    @Query("SELECT o FROM Orders o WHERE o.account_no = :accountNo")
    List<Orders> findByAccountNo(String accountNo);

    @Query("SELECT o FROM Orders o WHERE o.order_id = :order_id")
    Orders findByOrder_id(BigInteger order_id);

}
