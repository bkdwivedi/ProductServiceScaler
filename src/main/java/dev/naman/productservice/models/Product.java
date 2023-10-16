package dev.naman.productservice.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product extends BaseModel {
        @ManyToOne(cascade = {CascadeType.PERSIST})
        @JoinColumn(name = "category")
        private Category category;
        @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
        private Price price;
        private String title;
        private String description;
        private String image;
}
