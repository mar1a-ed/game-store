package com.maria.game_store.model.entity;

import com.maria.game_store.model.enums.Genre;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tb_games")
public class Game implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "title", length = 100)
    private String title;

    @Column(name = "studio", nullable = true)
    private String studio;

    @Column(name = "description", length = 300)
    private String description;

    @Column(name = "genre")
    private Genre genre;

    @Column(name = "stock_quantity")
    private Integer stockQuantity;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "age_rating")
    private Integer ageRating;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return Objects.equals(id, game.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
