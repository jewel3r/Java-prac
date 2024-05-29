package com.example.luck;

import jakarta.persistence.*;
import lombok.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "clients")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Client implements CommonEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "client_id")
    private Long id;

    @Column(name = "client_name")
    private String clientName;

    @Column(name = "contacts")
    private String contacts;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

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
