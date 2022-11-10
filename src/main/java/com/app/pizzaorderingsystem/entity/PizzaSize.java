package com.app.pizzaorderingsystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Table(
        name = "pizza_size"
)
public class PizzaSize {

    @Id
    @SequenceGenerator(
            name = "pizza_size_sequence",
            sequenceName = "pizza_size_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "pizza_size_sequence"
    )
    private Long pizzaSizeId;
    @Column(
            name = "name"
    )
    private String name;
    @Column(
            name = "price"
    )
    private Float price;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "pizza_id",
            referencedColumnName = "pizzaId"
    )
    private Pizza pizza;

    @JsonIgnore
    @OneToMany(mappedBy = "pizzaSize")
    @ToString.Exclude
    private Set<Order> orders = new HashSet<>();

    // modified toString method for displaying the list of pizza sizes in the form of name and price
    @Override
    public String toString() {
        return  "Size: " + name + ", " + "price: " + " $" + price;

    }
}
