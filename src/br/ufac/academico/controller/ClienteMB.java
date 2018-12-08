package br.ufac.academico.controller;

import java.util.List;

import javax.faces.bean.*;

import br.ufac.academico.domain.*;
import br.ufac.academico.repositories.*;

@ManagedBean(name="clienteControlador")
@SessionScoped
public class ClienteMB { 
	private ClienteRepositorio br;
	private List<Cliente> clientes;
	private String chaveNome = "";
	private Cliente cliente;
	public ClienteMB() {
		br = new ClienteRepositorio();		
	}
	public List<Cliente> getClientes() {
		clientes= br.recuperarTodosPorNome();
		return clientes;
	}
	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}
	public ClienteRepositorio getCr() {
		return br;
	}
	public String getChaveNome() {
		return chaveNome;
	}
	public void setChaveNome(String chaveNome) {
		this.chaveNome = chaveNome;
	}	
	
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public String incluir() {
		br.adicionar(cliente);
		return "clienteListagem";
	}
	public String editar(Cliente cliente) {
		this.cliente=cliente;
		return "clienteEdicao";
	}
	public String novo() {
		cliente = new Cliente();
		return "clienteInclusao";
	}
	public String alterar() {
		br.atualizar(cliente);
		return "clienteListagem";
	}
	public String remover() {
		br.remover(cliente);
		return "clienteListagem";
	}
	public String excluir(Cliente cliente) {
		this.cliente=cliente;
		return "clienteExclusao";
	}
	public void setBanco(Cliente cliente) {
		this.cliente = cliente;
	}
	
	
}