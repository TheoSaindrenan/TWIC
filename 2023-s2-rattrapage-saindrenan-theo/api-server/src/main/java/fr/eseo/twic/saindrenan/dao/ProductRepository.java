package fr.eseo.twic.saindrenan.dao;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import fr.eseo.twic.saindrenan.entity.Products;
import java.util.List;


@Repository
public interface ProductRepository extends JpaRepository<Products, BigInteger> {

    @Query("SELECT p FROM Products p WHERE p.product_id = :product_id")
    Products findByProduct_id(BigInteger product_id);

    @Query("SELECT p FROM Products p WHERE p.product_no = :product_no")
    List<Products> findByProduct_no(String product_no);
}
