package ru.novogor.novogorapp.telegram_bot.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "pump")
@Getter
@Setter
@ToString
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
    private String note;
    @NotNull
    private Date dateVibroDiagnostic;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "station_id")
    private Station station;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "status_id")
    private Status status;

}
