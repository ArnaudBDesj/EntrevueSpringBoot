package com.example.entrevuespringboot.domaine.repository;

import com.example.entrevuespringboot.domaine.modele.Acteur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ActeurRepository extends JpaRepository<Acteur, Long> {
    /**
     * Recherche d'un acteur par le nom et prénom.
     *
     * @param nom    nom de l'acteur
     * @param prenom prénom de l'acteur
     * @return l'optional acteur
     */
    Optional<Acteur> findByNomAndPrenom(String nom, String prenom);
}
