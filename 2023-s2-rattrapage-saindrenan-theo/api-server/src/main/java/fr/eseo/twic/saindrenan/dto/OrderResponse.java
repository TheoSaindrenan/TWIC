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
public class OrderResponse {

    private BigInteger order_id;

    private String account_no;

    private LocalDate placed_timestamp;

    private LocalDate delivered_timestamp;

    private LocalDate cancelled_timestamp;
    
    private String order_status_name;
    
    private BigInteger product_id;

    private Integer quantity;

}
