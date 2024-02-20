package fr.eseo.twic.saindrenan.dto;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

import fr.eseo.twic.saindrenan.entity.Order_details;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {

    private BigInteger order_id;

    private String account_no;

    private BigInteger order_status_id;

    private LocalDate placed_timestamp;

    private LocalDate delivered_timestamp;

    private LocalDate cancelled_timestamp;


    private Order_details[] orderDetails;

    private String order_status_name;

}
