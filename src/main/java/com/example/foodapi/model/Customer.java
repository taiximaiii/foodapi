package com.example.foodapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String password;
    private String age;
    private String address;
    private String bankNumber;
    @Transient
    private String token;

    @JsonIgnore
    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    private Cart cart;

    @JsonIgnore
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Order> orders = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "customer_payment",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "payment_id")
    )
    @JsonManagedReference
    @JsonIgnore
    private List<Payment> payments = new ArrayList<>();
    public void addPayment(Payment payment) {
        if (payment != null) {
            payments.add(payment);
            payment.getCustomers().add(this);
        }
    }
    public void removePayment(Payment payment) {
        if (payment != null) {
            payments.remove(payment);
            payment.getCustomers().remove(this);
        }
    }
}
