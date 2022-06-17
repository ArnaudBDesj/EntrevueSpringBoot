package com.example.entrevuespringboot.api.modele;

import lombok.Data;

import java.util.Set;

@Data
public class FilmInRessource {
    private String titre;
    private String description;
    private Set<ActeurInRessource> acteurs;
}
