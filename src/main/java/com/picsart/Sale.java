package com.picsart;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "sales")
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "saleid")
    private int saleID;

    @ManyToOne
    @JoinColumn(name = "bookid", nullable = false)
    private Book book;

    @ManyToOne
    @JoinColumn(name = "customerid", nullable = false)
    private Customer customer;

    @Column(name = "dateofsale")
    private Date dateOfSale;

    @Column(name = "quantitysold", nullable = false)
    private int quantitySold;

    @Column(name = "totalprice", nullable = false)
    private double totalPrice;

    public Sale() {

    }

    public Sale(Book book, Customer customer, Date dateOfSale, int quantitySold, double totalPrice) {
        this.book = book;
        this.customer = customer;
        this.dateOfSale = dateOfSale;
        this.quantitySold = quantitySold;
        this.totalPrice = totalPrice;
    }

    public Sale getSale(Sale sale) {
        return sale;
    }
    public int getSaleID() {
        return saleID;
    }

    public void setSaleID(int saleID) {
        this.saleID = saleID;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Date getDateOfSale() {
        return dateOfSale;
    }

    public void setDateOfSale(Date dateOfSale) {
        this.dateOfSale = dateOfSale;
    }

    public int getQuantitySold() {
        return quantitySold;
    }

    public void setQuantitySold(int quantitySold) {
        this.quantitySold = quantitySold;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }



    @Override
    public String toString() {
        return "Sale ID: " + saleID +
        ", Book ID: " + book.getBookID() +
                ", Sale Date: " + dateOfSale +
                ", QuantitySold: " + quantitySold;
    }
}
