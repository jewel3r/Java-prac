package com.example.luck;

import jakarta.persistence.*;
import lombok.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "employees")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employee implements CommonEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emp_id", nullable = false)
    private Long id;

    @Column(name = "emp_name", nullable = false)
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "education")
    private String education;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "workplace", nullable = false)
    private Workplace workplace;

    @Column(name = "birth_date")
    private Date birthDate;

    public String getBirthDate() {
        if (birthDate == null) {
            return null;
        }
        DateFormat outputFormatter = new SimpleDateFormat("dd/MM/yyyy");
        return outputFormatter.format(birthDate);
    }

    public Date getRealDate() {
        return birthDate;
    }
}
