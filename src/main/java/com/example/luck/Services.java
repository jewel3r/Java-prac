package com.example.luck;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "services")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Services implements CommonEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "serv_id", nullable = false)
    private Long id;

    @Column(name = "serv_name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    @Positive
    private double price;
}
