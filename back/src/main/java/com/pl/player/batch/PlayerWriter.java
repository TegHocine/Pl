package com.pl.player.batch;

import com.pl.player.player.Player;
import com.pl.player.player.PlayerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PlayerWriter implements ItemWriter<Player>{
    final PlayerRepository playerRepository;

    public PlayerWriter(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public void write(Chunk<? extends Player> chunk) {
        log.info("Job Writer for player stats csv file started with data : {}", chunk.getItems());
        playerRepository.deleteAll();
        playerRepository.saveAll(chunk.getItems());
    }
}
