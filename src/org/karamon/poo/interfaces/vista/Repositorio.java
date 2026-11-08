package org.karamon.poo.interfaces.vista;

import org.karamon.poo.interfaces.modelo.Cliente;
import org.karamon.poo.interfaces.repositorio.ClienteListRepositorio;
import org.karamon.poo.interfaces.repositorio.CompletoRepositorio;
import org.karamon.poo.interfaces.repositorio.Direccion;

import java.util.List;

public class Repositorio {
    public static void main(String[] args) {
        CompletoRepositorio repo = new ClienteListRepositorio();
        repo.crear(new Cliente("Jano", "Pérez"));
        repo.crear(new Cliente("Bea", "González"));
        repo.crear(new Cliente("Luci", "Martinez"));
        repo.crear(new Cliente("Karamon", "Cherif"));
        System.out.println("===================== CLIENTES REGISTRADOS =====================");
        List<Cliente> clientes = repo.listar();
        clientes.forEach(System.out::println);

        List<Cliente> paginable = repo.listar(1, 3);
        System.out.println("=========================== PAGINABLE ===========================");
        paginable.forEach(System.out::println);
        System.out.println("=========================== ORDENABLE ===========================");
        List<Cliente> ordenar = repo.listar("nombre", Direccion.ASC);
        ordenar.forEach(System.out::println);
        System.out.println("=========================== EDITAR ===========================");
        Cliente editarCliente = new Cliente("Bea", "Mena");
        editarCliente.setId(2);
        repo.editar(editarCliente);
        Cliente editado = repo.porId(2);
        System.out.println(editado);
        System.out.println("=========================== ELIMINAR CLIENTE ===========================");
        repo.eliminar(2);
        clientes.forEach(System.out::println);
        System.out.println("Total de registros: " + repo.total());


    }
}
