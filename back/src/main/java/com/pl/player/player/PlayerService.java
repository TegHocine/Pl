package com.pl.player.player;

import com.pl.player.dtos.PaginationDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PlayerService {
    private final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public PaginationDto<Player> getPlayersByNation(String nation, int page, int size)  {
        Pageable pageable =  PageRequest.of(page,size);
        Page<Player> playerPage = playerRepository.findByNation(nation, pageable);
        List<Player> player = playerPage.getContent();
        return PaginationDto.<Player>builder()
                .currentPage(page)
                .totalPages(playerPage.getTotalPages())
                .totalItems(playerPage.getTotalElements())
                .hasNextPage(playerPage.hasNext())
                .page(player)
                .build();
    }

    public PaginationDto<Player>  getPlayersByTeam(String team, int page, int size)  {
        Pageable pageable =  PageRequest.of(page,size);
        Page<Player> playerPage = playerRepository.findByTeam(team, pageable);
        List<Player> player = playerPage.getContent();
        return PaginationDto.<Player>builder()
                .currentPage(page)
                .totalPages(playerPage.getTotalPages())
                .totalItems(playerPage.getTotalElements())
                .hasNextPage(playerPage.hasNext())
                .page(player)
                .build();
    }

    public PaginationDto<Player> getPlayersByPosition(String position, int page, int size) {
        Pageable pageable =  PageRequest.of(page,size);
        Page<Player> playerPage = playerRepository.findByPosition(position, pageable);
        List<Player> player = playerPage.getContent();
        return PaginationDto.<Player>builder()
                .currentPage(page)
                .totalPages(playerPage.getTotalPages())
                .totalItems(playerPage.getTotalElements())
                .hasNextPage(playerPage.hasNext())
                .page(player)
                .build();
    }

    public PaginationDto<Player>  getPlayersByName(String name, int page, int size) {
        Pageable pageable =  PageRequest.of(page,size);
        Page<Player> playerPage = playerRepository.findByPlayerName(name, pageable);
        List<Player> player = playerPage.getContent();
        return PaginationDto.<Player>builder()
                .currentPage(page)
                .totalPages(playerPage.getTotalPages())
                .totalItems(playerPage.getTotalElements())
                .hasNextPage(playerPage.hasNext())
                .page(player)
                .build();
    }


    public Player getPlayerById(Long id) {
        return playerRepository.getReferenceById(id);
    }

    public void deletePlayer(Long id) {
        playerRepository.deleteById(id);
    }


}
