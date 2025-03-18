package dev.java10x.MagicFridgeAI.model;

import dev.java10x.MagicFridgeAI.utils.FoodCategoryEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "food_item")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class FoodItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private FoodCategoryEnum category;
    private Integer quantity;
    private LocalDateTime expirationDate;



}
