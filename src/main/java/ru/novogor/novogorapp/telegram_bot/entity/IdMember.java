package ru.novogor.novogorapp.telegram_bot.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "id_members")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class IdMember {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
}
