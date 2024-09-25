package com.example.ecommerceapp.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.Blob;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private  String fileType;
    @Lob
    private Blob image;
    private  String downloadUrl;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private Timestamp downloadTime;
}
