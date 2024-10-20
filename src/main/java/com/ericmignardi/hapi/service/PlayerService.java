package com.ericmignardi.hapi.service;

import com.ericmignardi.hapi.model.Player;
import com.ericmignardi.hapi.repository.PlayerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlayerService {

    private final PlayerRepository playerRepository;

    public List<Player> findAll(){
        return playerRepository.findAll();
    }

    public Player findById(Long id){
        return playerRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    public List<Player> findAllByTeam(String team) {
        return playerRepository.findAll().stream()
                .filter(player -> team.equals(player.getTeam()))
                .collect(Collectors.toList());
    }

    public List<Player> findAllByName(String name) {
        return playerRepository.findAll().stream()
                .filter(player -> player.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Player> findAllByPosition(String position) {
        return playerRepository.findAll().stream()
                .filter(player -> player.getPosition().toLowerCase().contains(position.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Player> findAllByTeamAndPosition(String team, String position) {
        return playerRepository.findAll().stream()
                .filter(player -> team.equals(player.getTeam()) && position.equals(player.getPosition()))
                .collect(Collectors.toList());
    }

    public Player create(Player player) {
        return playerRepository.save(player);
    }

    public Player update(Long id, Player player) {
        Player updatedPlayer = playerRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        updatedPlayer.setName(player.getName());
        updatedPlayer.setAge(player.getAge());
        updatedPlayer.setTeam(player.getTeam());
        updatedPlayer.setPosition(player.getPosition());
        updatedPlayer.setGames(player.getGames());
        updatedPlayer.setGoals(player.getGoals());
        updatedPlayer.setAssists(player.getAssists());
        updatedPlayer.setPoints(player.getPoints());
        updatedPlayer.setPlusMinus(player.getPlusMinus());
        updatedPlayer.setPenaltyMinutes(player.getPenaltyMinutes());
        return playerRepository.save(updatedPlayer);
    }

    @Transactional
    public Player delete(Long id) {
        Player player = playerRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        playerRepository.delete(player);
        return player;
    }
}
