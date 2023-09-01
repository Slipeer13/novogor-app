package ru.novogor.novogorapp.telegram_bot.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "station")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Station {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "station_id", nullable = false)
    private Long station_id;

    @Column(unique = true)
    @NotNull
    private String name;

    @OneToMany(mappedBy = "station")
    @ToString.Exclude
    private List<Pump> pumpList;

    public void setName(String name) {
        this.name = Objects.requireNonNullElse(name, "неизвестная станция");
    }

    @Override
    public String toString() {
        return "станция "+ name;
    }
}
