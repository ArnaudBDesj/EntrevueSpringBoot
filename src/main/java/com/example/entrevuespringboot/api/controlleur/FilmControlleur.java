package com.example.entrevuespringboot.api.controlleur;

import com.example.entrevuespringboot.api.mapper.FilmRessourceMapper;
import com.example.entrevuespringboot.api.modele.FilmInRessource;
import com.example.entrevuespringboot.api.modele.FilmRessource;
import com.example.entrevuespringboot.domaine.service.FilmService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/api/film")
@RequiredArgsConstructor
public class FilmControlleur {

    private final FilmService filmService;
    private final FilmRessourceMapper filmRessourceMapper;

    @GetMapping(
            value = "{id}",
            produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<FilmRessource> obtenirFilmParId(@PathVariable("id") long id) {
        return filmService.obtenirFilmParId(id)
                .map(filmRessourceMapper::beanToRessource)
                .map(ResponseEntity::ok)
                .orElseGet(() -> (ResponseEntity<FilmRessource>) ResponseEntity.notFound());
    }

    @PostMapping(
            consumes = { MediaType.APPLICATION_JSON_VALUE },
            produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<FilmRessource> creerFilm(@RequestBody FilmInRessource filmInRessource, HttpServletRequest requete) {
        return Optional.ofNullable(filmService.creerFilm(filmInRessource))
                .map(filmRessourceMapper::beanToRessource)
                .map(film -> ResponseEntity.created(URI.create(requete.getRequestURI() + "/" + film.getId())).body(film))
                .orElseGet(() -> (ResponseEntity<FilmRessource>) ResponseEntity.badRequest());
    }
}
