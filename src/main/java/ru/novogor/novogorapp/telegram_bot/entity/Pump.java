package ru.novogor.novogorapp.telegram_bot.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "pump")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pump {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pump_id", nullable = false)
    private Long pump_id;
    @NotNull
    private String name;
    @NotNull
    @Column(length = 512)
    private String note;
    @NotNull
    private Date dateVibroDiagnostic;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "station_id")
    private Station station;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "status_id")
    private Status status;

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Насос{").append("номер='").append(name).append('\'').append(", ").append(station)
                .append(", ").append(status).append('}');
        return result.toString();
    }
}
