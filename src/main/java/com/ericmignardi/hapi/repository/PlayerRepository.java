package com.ericmignardi.hapi.repository;

import com.ericmignardi.hapi.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
    void deleteByName(String playerName);
    Optional<Player> findByName(String playerName);
}
