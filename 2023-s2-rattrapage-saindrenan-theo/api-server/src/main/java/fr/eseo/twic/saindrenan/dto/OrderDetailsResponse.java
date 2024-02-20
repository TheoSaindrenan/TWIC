package fr.eseo.twic.saindrenan.dto;

import java.math.BigInteger;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailsResponse {

    private BigInteger order_detail_id ;

    private BigInteger order_id;

    private BigInteger product_id;

    private Integer quantity;

    private String product_name;

    private Integer price;

}
