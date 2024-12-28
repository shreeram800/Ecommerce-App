package com.example.ecommerceapp.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.sql.Blob;
import java.sql.Timestamp;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Image image1 = (Image) o;
        return id == image1.id && Objects.equals(name, image1.name) && Objects.equals(fileType, image1.fileType) && Objects.equals(image, image1.image) && Objects.equals(downloadUrl, image1.downloadUrl) && Objects.equals(product, image1.product) && Objects.equals(downloadTime, image1.downloadTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, fileType, image, downloadUrl, product, downloadTime);
    }

    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", fileType='" + fileType + '\'' +
                ", image=" + image +
                ", downloadUrl='" + downloadUrl + '\'' +
                ", product=" + product +
                ", downloadTime=" + downloadTime +
                '}';
    }
}
