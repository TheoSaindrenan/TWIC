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
@Table(name = "order_details")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order_details {

    @Id
    private BigInteger order_detail_id ;

    private BigInteger order_id;

    private BigInteger product_id;

    private Integer quantity;

}
