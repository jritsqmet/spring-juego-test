package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Videojuego;

@Repository
public interface VideojuegoRepository extends JpaRepository<Videojuego, Long> {
}