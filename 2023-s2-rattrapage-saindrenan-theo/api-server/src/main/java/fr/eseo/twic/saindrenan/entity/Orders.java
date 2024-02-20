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
@Table(name = "orders")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Orders {

    @Id
    private BigInteger order_id;

    private String account_no;

    private BigInteger order_status_id;

    private LocalDate placed_timestamp;

    private LocalDate delivered_timestamp;

    private LocalDate cancelled_timestamp;

}