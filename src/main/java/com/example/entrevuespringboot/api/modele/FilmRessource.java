package com.example.entrevuespringboot.api.modele;

import lombok.Data;

import java.util.Set;

@Data
public class FilmRessource {
    private Long id;
    private String titre;
    private String description;
    private Set<ActeurRessource> acteurs;
}
