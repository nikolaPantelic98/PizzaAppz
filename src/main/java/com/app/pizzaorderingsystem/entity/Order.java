package com.app.pizzaorderingsystem.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Table
public class Order {

    @Id
    @SequenceGenerator(
            name = "order_sequence",
            sequenceName = "order_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "order_sequence"
    )
    private Long orderId;
    @Column(
            name = "first_name"
    )
    private String firstName;
    @Column(
            name = "last_name"
    )
    private String lastName;
    @Column(
            name = "city"
    )
    private String city;
    @Column(
            name = "postcode"
    )
    private String postcode;
    @Column(
            name = "street_and_number"
    )
    private String streetAndNumber;
    @ManyToOne
    @JoinColumn(
            name = "pizza_id",
            referencedColumnName = "pizzaId"
    )
    private Pizza pizza;
    @ManyToOne
    @JoinColumn(
            name = "pizza_size_id",
            referencedColumnName = "pizzaSizeId"
    )
    private PizzaSize pizzaSize;

}
