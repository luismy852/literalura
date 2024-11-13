package com.proyecto.literalura.service;

public interface IDatos {
    <T> T obtenerDatos (String json, Class<T> clase);
}
