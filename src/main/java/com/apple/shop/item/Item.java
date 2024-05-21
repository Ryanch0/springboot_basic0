package com.apple.shop.item;


import jakarta.persistence.*;
import lombok.*;

@Entity
@ToString
@Getter
@Setter
@Table(indexes = @Index(columnList = "title", name = "작명"))
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Integer price;
    private String username;
    private String img;

}
