package fr.eseo.twic.saindrenan.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import fr.eseo.twic.saindrenan.entity.Customer;

import java.math.BigInteger;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, BigInteger> {

    @Query("SELECT c FROM Customer c WHERE c.account_no = :accountNo")
    Customer findByAccountNo(String accountNo);

    @Query("SELECT c FROM Customer c WHERE c.customer_id = :customerId")
    Customer findByCustomerId(BigInteger customerId);

    @Query("SELECT c FROM Customer c WHERE c.email = :email")
    Customer findByEmail(String email);

    @Query("SELECT c FROM Customer c WHERE c.first_name = :firstName")
    Customer findByFirstName(String firstName);

    @Query("SELECT c FROM Customer c WHERE c.last_name = :lastName")
    Customer findByLastName(String lastName);
}
