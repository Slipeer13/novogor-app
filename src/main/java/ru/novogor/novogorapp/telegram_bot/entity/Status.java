package ru.novogor.novogorapp.telegram_bot.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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

    public List<Pump> getPumpList() {
        if(pumpList == null) pumpList = new ArrayList<>();
        return pumpList;
    }

    @Override
    public String toString() {
        return ", состояние " + name;
    }
}
