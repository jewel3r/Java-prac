package com.example.luck;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "workplaces")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Workplace implements CommonEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "w_id", nullable = false)
    private Long id;

    @Column(name = "w_name", nullable = false)
    private String name;
}
