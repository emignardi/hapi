package com.ericmignardi.hapi.controller;

import com.ericmignardi.hapi.model.Player;
import com.ericmignardi.hapi.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/players")
@RequiredArgsConstructor
public class PlayerController {

    private final PlayerService playerService;

    @GetMapping
    public List<Player> findAll(@RequestParam(required = false) String team, @RequestParam(required = false) String name, @RequestParam(required = false) String position) {
        if (team != null && position != null) {
            return playerService.findAllByTeamAndPosition(team, position);
        } else if (team != null) {
            return playerService.findAllByTeam(team);
        } else if (name != null) {
            return playerService.findAllByName(name);
        } else if (position != null) {
            return playerService.findAllByPosition(position);
        } else {
            return playerService.findAll();
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Player> create(@RequestBody Player player) {
        Player createdPlayer = playerService.create(player);
        return new ResponseEntity<>(createdPlayer, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Player> update(@PathVariable Long id, @RequestBody Player player) {
        Player updatedPlayer = playerService.update(id, player);
        return new ResponseEntity<>(updatedPlayer, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Player> delete(@PathVariable Long id) {
        Player deletedPlayer = playerService.delete(id);
        return new ResponseEntity<>(deletedPlayer, HttpStatus.OK);
    }
}
