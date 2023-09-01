package ru.novogor.novogorapp.telegram_bot.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "status")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "status_id", nullable = false)
    private Long status_id;

    @Column(unique = true)
    @NotNull
    private String name;

    @OneToMany(mappedBy = "status")
    @ToString.Exclude
    private List<Pump> pumpList;

    public void setName(String name) {
        this.name = Objects.requireNonNullElse(name, "неизвестное состояние");
    }

    @Override
    public String toString() {
        return " состояние " + name;
    }
}
