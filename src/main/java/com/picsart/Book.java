package com.picsart;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @Column(name = "bookid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer bookID;

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    @Column(name = "genre")
    private String genre;

    @Column(name = "price")
    private Double price;

    @Column(name = "quantityinstock")
    private int quantityInStock;

    @OneToMany(mappedBy = "book")
    private List<Sale> sales;


    public Book() {

    }

    public Book(int bookID) {
        this.bookID = bookID;
    }
    public Book(int bookID, String title, String author, String genre, Double price, int quantityInStock) {
        this.bookID = bookID;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.price = price;
        this.quantityInStock = quantityInStock;
    }


    public Book getBook(Book book) {
        return book;
    }

    public Integer getBookID() {
        return bookID;
    }

    public void setBookID(Integer bookID) {
        this.bookID = bookID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    @Override
    public String toString() {
        return "ID: " + bookID +
                ", Title: " + title +
                ", Author: " + author +
                ", Genre: " + genre +
                ", Price: " + price +
                ", QuantityInStock: " + quantityInStock;
    }
}
