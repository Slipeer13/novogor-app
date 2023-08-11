package ru.novogor.novogorapp.telegram_bot.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "station")
@Getter
@Setter
@ToString
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
}
