package com.example.entrevuespringboot.domaine.service.impl;

import com.example.entrevuespringboot.api.modele.ActeurInRessource;
import com.example.entrevuespringboot.api.modele.FilmInRessource;
import com.example.entrevuespringboot.domaine.modele.Acteur;
import com.example.entrevuespringboot.domaine.modele.Film;
import com.example.entrevuespringboot.domaine.repository.ActeurRepository;
import com.example.entrevuespringboot.domaine.repository.FilmRepository;
import com.example.entrevuespringboot.domaine.service.FilmService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FilmServiceImpl implements FilmService {
    private final FilmRepository filmRepository;
    private final ActeurRepository acteurRepository;

    @Override
    public Optional<Film> obtenirFilmParId(Long id) {
        return filmRepository.findById(id);
    }

    @Override
    public Film creerFilm(FilmInRessource filmInRessource) {
        Film film = initialiserFilmDetails(filmInRessource);
        film.setActeurs(initialiserActeursSet(filmInRessource.getActeurs()));
        return filmRepository.save(film);
    }

    /**
     * Initialise les détails du film uniquement (ne modifie la liste d'acteurs).
     *
     * @param filmInRessource ressource du film
     * @return l'entité film avec les détails initialisés
     */
    private Film initialiserFilmDetails(FilmInRessource filmInRessource) {
        Film film = new Film();
        film.setTitre(filmInRessource.getTitre());
        film.setDescription(filmInRessource.getDescription());
        return film;
    }

    /**
     * Initialise la liste d'acteurs en vérifiant si l'acteur est déjà en BD.
     * Si l'acteur n'est pas déjà créé, il est persisté en BD.
     *
     * @param acteurs liste d'acteurs ressource
     * @return liste d'entité d'acteurs
     */
    private Set<Acteur> initialiserActeursSet(Set<ActeurInRessource> acteurs) {
        if (acteurs != null) {
            return acteurs.stream()
                    .map(this::recupererOuCreerActeur)
                    .collect(Collectors.toCollection(HashSet::new));
        }
        return Collections.emptySet();
    }

    /**
     * Vérifier si l'acteur est déjà en BD.
     * Si l'acteur n'est pas déjà créé, il est persisté en BD.
     *
     * @param acteur la ressource acteur
     * @return l'entité acteur
     */
    private Acteur recupererOuCreerActeur(ActeurInRessource acteur) {
        return acteurRepository.findByNomAndPrenom(acteur.getNom(), acteur.getPrenom())
                .orElseGet(() -> acteurRepository.save(initialiserActeurDetails(acteur)));
    }

    /**
     * Initialise les détails de l'acteur.
     *
     * @param acteurInRessource ressource acteur
     * @return l'entité acteur avec les détails initialisés
     */
    private Acteur initialiserActeurDetails(ActeurInRessource acteurInRessource) {
        Acteur acteur = new Acteur();
        acteur.setNom(acteurInRessource.getNom());
        acteur.setPrenom(acteurInRessource.getPrenom());
        return acteur;
    }
}
