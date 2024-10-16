package com.pl.player.batch;

import com.pl.player.player.Player;
import com.pl.player.player.PlayerRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.job.flow.support.SimpleFlow;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class BatchConfiguration {
    final PlayerRepository playerRepository;
    final PlayerWriter playerWriter;

    public BatchConfiguration(PlayerRepository playerRepository, PlayerWriter playerWriter) {
        this.playerRepository = playerRepository;
        this.playerWriter = playerWriter;
    }


    @Bean
    public Step playerStep(JobRepository jobRepository, PlatformTransactionManager transactionManager, PlayerReader playerReader) {
        return new StepBuilder("playerStep",jobRepository)
                .<Player, Player>chunk(1000, transactionManager)
                .reader(playerReader.reader())
                .writer(playerWriter)
                .build();
    }

    @Bean
    public Job playerMigrationJob(JobRepository jobRepository, Step step) {
        Flow flow = new FlowBuilder<SimpleFlow>("Getting data from player stats csv file Flow")
                .start(step)
                .end();
        return new JobBuilder("PlayerStatsMigrationJob", jobRepository)
                .start(flow)
                .end()
                .build();
    }
}
