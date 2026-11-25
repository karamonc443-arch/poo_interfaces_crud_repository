package org.karamon.poo.interfaces.repositorio;

import org.karamon.poo.interfaces.excepciones.AccesoDatoExcepcion;
import org.karamon.poo.interfaces.modelo.Cliente;

import java.util.List;

public interface CrudRepositorio {
    List<Cliente> listar();

    Cliente porId(Integer id) throws AccesoDatoExcepcion;

    void crear(Cliente cliente) throws AccesoDatoExcepcion;

    void editar(Cliente cliente) throws AccesoDatoExcepcion;

    void eliminar(Integer id) throws AccesoDatoExcepcion;
}
