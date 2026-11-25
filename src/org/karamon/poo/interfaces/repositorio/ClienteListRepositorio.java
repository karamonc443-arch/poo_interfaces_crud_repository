
package org.karamon.poo.interfaces.repositorio;

import org.karamon.poo.interfaces.excepciones.LecturaAccesoDatoExcepcion;
import org.karamon.poo.interfaces.modelo.Cliente;

import java.util.ArrayList;
import java.util.List;

public class ClienteListRepositorio implements CompletoRepositorio {

    private final List<Cliente> dataSource;

    public ClienteListRepositorio() {
        this.dataSource = new ArrayList<>();
    }

    private static int ordenar(String campo, Cliente c1, Cliente c2)
            throws LecturaAccesoDatoExcepcion {

        return switch (campo) {
            case "id" -> c1.getId().compareTo(c2.getId());
            case "nombre" -> c1.getNombre().compareTo(c2.getNombre());
            case "apellido" -> c1.getApellido().compareTo(c2.getApellido());
            default -> throw new LecturaAccesoDatoExcepcion(
                    "Campo de ordenación no válido: " + campo);
        };
    }

    @Override
    public List<Cliente> listar() {
        return new ArrayList<>(dataSource);
    }

    @Override
    public Cliente porId(Integer id) throws LecturaAccesoDatoExcepcion {

        if (id == null || id <= 0) {
            throw new LecturaAccesoDatoExcepcion("Id inválido, debe ser > 0");
        }

        for (Cliente cliente : dataSource) {
            if (id.equals(cliente.getId())) {
                return cliente;
            }
        }

        throw new LecturaAccesoDatoExcepcion("No existe ningún registro con id: " + id);
    }

    @Override
    public void crear(Cliente cliente) throws LecturaAccesoDatoExcepcion {

        if (cliente == null) {
            throw new LecturaAccesoDatoExcepcion("El cliente no puede ser null");
        }

        if (cliente.getId() == null || cliente.getId() <= 0) {
            throw new LecturaAccesoDatoExcepcion("El id debe ser mayor que cero");
        }

        for (Cliente c : dataSource) {
            if (c.getId().equals(cliente.getId())) {
                throw new LecturaAccesoDatoExcepcion("El cliente ya existe");
            }
        }

        dataSource.add(cliente);
    }

    @Override
    public void editar(Cliente cliente) throws LecturaAccesoDatoExcepcion {

        if (cliente == null) {
            throw new LecturaAccesoDatoExcepcion("El cliente no puede ser null");
        }

        Cliente editarCliente = porId(cliente.getId());

        editarCliente.setNombre(cliente.getNombre());
        editarCliente.setApellido(cliente.getApellido());
    }

    @Override
    public void eliminar(Integer id) throws LecturaAccesoDatoExcepcion {

        Cliente clienteEliminar = porId(id);

        dataSource.remove(clienteEliminar);
    }

    @Override
    public List<Cliente> listar(String campo, Direccion dir)
            throws LecturaAccesoDatoExcepcion {

        if (dir == null) {
            throw new LecturaAccesoDatoExcepcion("La dirección no puede ser null");
        }

        List<Cliente> listaOrdenada = new ArrayList<>(dataSource);

        listaOrdenada.sort((c1, c2) -> {
            try {
                int resultado = ordenar(campo, c1, c2);
                return dir == Direccion.ASC ? resultado : -resultado;
            } catch (LecturaAccesoDatoExcepcion e) {
                throw new RuntimeException(e);
            }
        });

        return listaOrdenada;
    }

    @Override
    public List<Cliente> listar(int desde, int hasta)
            throws LecturaAccesoDatoExcepcion {

        if (desde < 0 || hasta > dataSource.size() || desde > hasta) {

            throw new LecturaAccesoDatoExcepcion("Rango de índices inválido");
        }

        return new ArrayList<>(dataSource.subList(desde, hasta));
    }

    @Override
    public int total() {
        return dataSource.size();
    }
}
