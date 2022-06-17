package com.example.entrevuespringboot.domaine.service;

import com.example.entrevuespringboot.api.modele.FilmInRessource;
import com.example.entrevuespringboot.domaine.modele.Film;

import java.util.Optional;

public interface FilmService {

    Optional<Film> obtenirFilmParId(Long id);

    Film creerFilm(FilmInRessource filmInRessource);
}
