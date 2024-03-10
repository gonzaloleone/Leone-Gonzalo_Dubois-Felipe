package com.backend.proyectoClinica.dao;

import java.util.List;

public interface IDao<T> {

    T guardar(T t);
    List<T> listarTodos();

    T buscarPorId(int id);

}
