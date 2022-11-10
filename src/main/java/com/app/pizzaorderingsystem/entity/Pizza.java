package com.app.pizzaorderingsystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
        name = "pizza"
)
public class Pizza {

    @Id
    @SequenceGenerator(
            name = "pizza_sequence",
            sequenceName = "pizza_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "pizza_sequence"
    )
    private Long pizzaId;
    @Column(
            name = "name"
    )
    private String name;
    @Column(
            name = "description"
    )
    private String description;

    @JsonIgnore
    @OneToMany(mappedBy = "pizza")
    @ToString.Exclude
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<PizzaSize> pizzaSizes = new HashSet<>();
    @Column(
            name = "image"
    )
    private String image;

    @JsonIgnore
    @OneToMany(mappedBy = "pizza")
    @ToString.Exclude
    private Set<Order> orders = new HashSet<>();


    // method used in thymeleaf to get a unique image path
    @Transient
    public String getImagePath() {
        if (image == null || pizzaId == null) {
            return null;
        }
        return "/pizza-images/" + pizzaId + "/" + image;
    }
}
