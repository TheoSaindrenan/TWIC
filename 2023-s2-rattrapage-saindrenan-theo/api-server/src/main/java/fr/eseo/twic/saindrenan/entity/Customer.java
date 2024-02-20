package fr.eseo.twic.saindrenan.entity;
import java.math.BigInteger;
import java.time.LocalDate;

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
@Table(name = "customers")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    private BigInteger customer_id;

    private String account_no;

    private String first_name;

    private String last_name;

    private String email;

    private LocalDate registration_timestamp ;

}