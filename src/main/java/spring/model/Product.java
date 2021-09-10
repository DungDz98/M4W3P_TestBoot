package spring.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Không được để trống")
    private String productName;
    private String img;
    private double price;
    @ManyToOne
    private Category category;

    public Product() {
    }

    public Product(@NotEmpty String productName, String img, double price) {
        this.productName = productName;
        this.img = img;
        this.price = price;
    }

    public Product(@NotEmpty String productName, String img, double price, Category category) {
        this.productName = productName;
        this.img = img;
        this.price = price;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
