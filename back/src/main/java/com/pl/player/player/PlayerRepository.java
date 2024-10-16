package com.pl.player.player;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

    @Query("SELECT p FROM Player p WHERE :team IS NULL OR :team = '' OR LOWER(p.team) LIKE LOWER(CONCAT('%', :team, '%'))")
    Page<Player> findByTeam(@Param("team") String team, Pageable pageable);

    @Query("SELECT p FROM Player p WHERE :playerName IS NULL OR :playerName = '' OR LOWER(p.playerName) LIKE LOWER(CONCAT('%', :playerName, '%'))")
    Page<Player> findByPlayerName(@Param("playerName") String playerName, Pageable pageable);

    @Query("SELECT p FROM Player p WHERE :nation IS NULL OR :nation = '' OR LOWER(p.nation) LIKE LOWER(CONCAT('%', :nation, '%'))")
    Page<Player> findByNation(@Param("nation") String nation, Pageable pageable);

    @Query("SELECT p FROM Player p WHERE :position IS NULL OR :position = '' OR LOWER(p.position) LIKE LOWER(CONCAT('%', :position, '%'))")
    Page<Player> findByPosition(@Param("position") String position, Pageable pageable);
}
