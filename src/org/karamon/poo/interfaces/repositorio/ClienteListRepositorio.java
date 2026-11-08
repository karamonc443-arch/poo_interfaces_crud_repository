package org.karamon.poo.interfaces.repositorio;

import org.karamon.poo.interfaces.modelo.Cliente;

import java.util.ArrayList;
import java.util.List;

public class ClienteListRepositorio implements CompletoRepositorio {

    private final List<Cliente> dataSource;

    public ClienteListRepositorio() {
        this.dataSource = new ArrayList<>();
    }

    private static int ordenar(String campo, Cliente cliente1, Cliente cliente2) {
        int resultado = 0;
        switch (campo) {
            case "id" -> resultado = cliente1.getId().compareTo(cliente2.getId());
            case "nombre" -> resultado = cliente1.getNombre().compareTo(cliente2.getNombre());
            case "apellido" -> resultado = cliente1.getApellido().compareTo(cliente2.getApellido());
        }
        return resultado;
    }

    @Override
    public List<Cliente> listar() {
        return dataSource;
    }

    @Override
    public Cliente porId(Integer id) {
        Cliente buscarCliente = new Cliente();
        for (Cliente cliente : dataSource) {
            if (cliente.getId() != null && cliente.getId().equals(id)) {
                buscarCliente = cliente;
                break;
            }
        }
        return buscarCliente;
    }

    @Override
    public void crear(Cliente cliente) {
        this.dataSource.add(cliente);

    }

    @Override
    public void editar(Cliente cliente) {
        Cliente editarCliente = this.porId(cliente.getId());
        editarCliente.setNombre(cliente.getNombre());
        editarCliente.setApellido(cliente.getApellido());
    }

    @Override
    public void eliminar(Integer id) {
        Cliente clienteEliminar = this.porId(id);
        this.dataSource.remove(clienteEliminar);
    }

    @Override
    public List<Cliente> listar(String campo, Direccion dir) {
        List<Cliente> listaOrdenada = new ArrayList<>(this.dataSource);
        listaOrdenada.sort((cliente1, cliente2) -> {
            int resultado = 0;
            if (dir == Direccion.ASC) {
                resultado = ordenar(campo, cliente1, cliente2);

            } else if (dir == Direccion.DESC) {
                resultado = ordenar(campo, cliente2, cliente1);
            }
            return resultado;
        });
        return listaOrdenada;
    }

    @Override
    public List<Cliente> listar(int desde, int hasta) {

        return dataSource.subList(desde, hasta);
    }

    @Override
    public int total() {
        return this.dataSource.size();
    }
}

