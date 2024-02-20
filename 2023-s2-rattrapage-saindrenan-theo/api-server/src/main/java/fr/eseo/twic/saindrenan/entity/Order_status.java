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
@Table(name = "order_statuses")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order_status {

    @Id
    private BigInteger order_status_id;

    private String order_status_name;

}