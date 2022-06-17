package com.example.entrevuespringboot.domaine.repository;

import com.example.entrevuespringboot.domaine.modele.Film;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmRepository extends JpaRepository<Film, Long> {
}
