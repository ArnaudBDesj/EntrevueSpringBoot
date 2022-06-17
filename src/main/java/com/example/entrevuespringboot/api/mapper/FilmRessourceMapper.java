package com.example.entrevuespringboot.api.mapper;

import com.example.entrevuespringboot.api.modele.FilmRessource;
import com.example.entrevuespringboot.domaine.modele.Film;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FilmRessourceMapper extends SimpleMapper<FilmRessource, Film> {

}
