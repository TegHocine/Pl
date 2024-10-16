package com.pl.player.player;

import com.pl.player.dtos.PaginationDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/player")
@Slf4j
public class PlayerController {
    private final Job playerMigrationJob;
    private final JobLauncher jobLauncher;
    private final PlayerService playerService;

    public PlayerController(Job playerMigrationJob, JobLauncher jobLauncher, PlayerService playerService) {
        this.playerMigrationJob = playerMigrationJob;
        this.jobLauncher = jobLauncher;
        this.playerService = playerService;
    }


    @GetMapping("/team")
    public ResponseEntity<PaginationDto<Player>> getPlayersByTeam(@Param("team") String team,
                                                         @RequestParam(name = "page",defaultValue = "0") int page,
                                                         @RequestParam(name = "size", defaultValue = "10") int size) {
        log.info("called player/team");
        return ResponseEntity.ok(playerService.getPlayersByTeam(team, page, size));
    }

    @GetMapping("/nation")
    public ResponseEntity<PaginationDto<Player>> getPlayersByNation(@Param("nation") String nation,
                                                                    @RequestParam(name = "page",defaultValue = "0") int page,
                                                                    @RequestParam(name = "size", defaultValue = "10") int size) {
        log.info("called player/nation");
        return ResponseEntity.ok(playerService.getPlayersByNation(nation, page, size));
    }

    @GetMapping("/position")
    public ResponseEntity<PaginationDto<Player>> getPlayersByPosition(@Param("position") String position,
                                                                    @RequestParam(name = "page",defaultValue = "0") int page,
                                                                    @RequestParam(name = "size", defaultValue = "10") int size) {
        log.info("called player/position");
        return ResponseEntity.ok(playerService.getPlayersByPosition(position, page, size));
    }

    @GetMapping("/name")
    public ResponseEntity<PaginationDto<Player>> getPlayersByName(@Param("name") String name,
                                                         @RequestParam(name = "page",defaultValue = "0") int page,
                                                         @RequestParam(name = "size", defaultValue = "10") int size) {
        log.info("called player/name");
        return ResponseEntity.ok(playerService.getPlayersByName(name, page, size));
    }




    @GetMapping("/{id}")
    public ResponseEntity<Player> getPlayerById(@PathVariable Long id) {
        return ResponseEntity.ok(playerService.getPlayerById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePlayer(@PathVariable Long id) {
        Player player = playerService.getPlayerById(id);
       if(player != null) {
           playerService.deletePlayer(id);
           return ResponseEntity.ok("Player deleted");
       }
       return ResponseEntity.notFound().build();
    }

    @GetMapping("/fill")
    public ResponseEntity<String> fillPlayers() {
        try {
            JobParameters parameters = new JobParametersBuilder()
                    .addLong("timestamp", System.currentTimeMillis())
                    .toJobParameters();

            jobLauncher.run(playerMigrationJob, parameters);

            return ResponseEntity.ok("Job started successfully");

        } catch (JobInstanceAlreadyCompleteException |
                 JobExecutionAlreadyRunningException |
                 JobParametersInvalidException |
                 JobRestartException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Job failed: " + e.getMessage());
        }
    }
}
