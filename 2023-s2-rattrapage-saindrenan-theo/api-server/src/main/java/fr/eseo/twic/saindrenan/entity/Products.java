package fr.eseo.twic.saindrenan.entity;
import java.math.BigInteger;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "products")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Products {

    @Id
    private BigInteger product_id;

    private String product_no;

    private String product_name;

    private Integer price;

}