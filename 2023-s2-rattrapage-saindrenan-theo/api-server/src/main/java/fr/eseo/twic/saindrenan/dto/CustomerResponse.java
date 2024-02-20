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
public class CustomerResponse {

    private BigInteger customer_id;

    private String account_no;

    private String first_name;

    private String last_name;

    private String email;

    private LocalDate registration_timestamp ;

}
