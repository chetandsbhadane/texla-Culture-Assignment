package com.example.demo.Entity;

import java.util.Arrays;

import javax.persistence.*;

@Entity
@Table(name="expense2")
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String category;
    
    private double amount;

    @Lob
    // @Column(name = "pdf", columnDefinition="BLOB")
    private byte[] pdf;

    public Expense() {
    }

    public Expense(Long id, String category, double amount, byte[] pdf) {
        this.id = id;
        this.category = category;
        this.amount = amount;
        this.pdf = pdf;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public byte[] getPdfData() {
        return pdf;
    }

    public void setPdfData(byte[] pdfData) {
        this.pdf = pdfData;
    }

    @Override
    public String toString() {
        return "Expense [id=" + id + ", category=" + category + ", amount=" + amount + ", pdfData="
                + Arrays.toString(pdf) + "]";
    }

    
    
    
    

}
