package org.karamon.poo.interfaces.repositorio;

import org.karamon.poo.interfaces.modelo.Cliente;

import java.util.List;

public interface OrdenableRepositorio {
    List<Cliente> listar(String campo, Direccion dir);
}
