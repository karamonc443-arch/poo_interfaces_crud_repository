package org.karamon.poo.interfaces.repositorio;

import org.karamon.poo.interfaces.modelo.Cliente;

import java.util.List;

public interface PaginableRepositorio {
    List<Cliente> listar(int desde, int hasta);

}
