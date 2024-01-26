package com.picsart;

import jakarta.persistence.*;

import javax.swing.text.DefaultEditorKit;
import java.util.List;

@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "customerid")
    private Integer customerID;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name =  "email", unique = true)
    private String email;

    @Column(name = "phone", nullable = false)
    private String phone;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Sale> purchases;

    public Customer() {

    }

    public Customer(int customerID) {
        this.customerID = customerID;
    }

    public Customer(int customerID, String name, String email, String phone) {
        this.customerID = customerID;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public Customer getCustomer(Customer customer) {
        return customer;
    }

    public Integer getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Integer customerID) {
        this.customerID = customerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Name='" + name +
                ", email= " + email +
                ", phone= " + phone;
    }
}
