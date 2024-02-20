package fr.eseo.twic.saindrenan.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import fr.eseo.twic.saindrenan.entity.Order_status;

import java.math.BigInteger;

@Repository
public interface OrderStatusRepository extends JpaRepository<Order_status, BigInteger> {

    @Query("SELECT os FROM Order_status os WHERE os.order_status_name = :orderStatusName")
    List<Order_status> findByOrderStatusName(String orderStatusName);

    @Query("SELECT os FROM Order_status os WHERE os.order_status_id = :orderStatusId")
    Order_status findByOrderStatusId(BigInteger orderStatusId);
}
