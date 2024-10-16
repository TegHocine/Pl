package com.pl.player.player;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String playerName;
    private String nation;
    private String position;
    private Integer age;
    private Integer matchPlayed;
    private Integer starts;
    private Double minutesPlayed;
    private Double goals;
    private Double assists;
    private Double penaltiesScored;
    private Double yellowCards;
    private Double redCards;
    private Double expectedGoals;
    private Double expectedAssists;
    private String team;
}
