package com.example.entrevuespringboot.api.mapper;

public interface SimpleMapper<R, B> {
    B resourceToBean(R ressource);

    R beanToRessource(B bean);
}