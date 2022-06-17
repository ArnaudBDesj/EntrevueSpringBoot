package com.example.entrevuespringboot.api.mapper;

import com.example.entrevuespringboot.api.modele.ActeurRessource;
import com.example.entrevuespringboot.domaine.modele.Acteur;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ActeurRessourceMapper extends SimpleMapper<ActeurRessource, Acteur> {

}
