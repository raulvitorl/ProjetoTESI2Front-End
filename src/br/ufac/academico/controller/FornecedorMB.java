package br.ufac.academico.controller;

import java.util.Date;
import java.util.List;

import javax.faces.bean.*;
import javax.persistence.Column;

import br.ufac.academico.domain.*;
import br.ufac.academico.repositories.*;

@ManagedBean(name="fornecedorControlador")
@SessionScoped
public class FornecedorMB { 
	private FornecedorRepositorio br;
	private List<Fornecedor> fornecedores;
	private String chaveNome = "";
	private Fornecedor fornecedor;
	
	public FornecedorMB() {
		br = new FornecedorRepositorio();		
	}
	public List<Fornecedor> getFornecedors() {
		fornecedores= br.recuperarTodosPorNome();
		return fornecedores;
	}
	public void setFornecedors(List<Fornecedor> fornecedores) {
		this.fornecedores = fornecedores;
	}
	public FornecedorRepositorio getCr() {
		return br;
	}
	public String getChaveNome() {
		return chaveNome;
	}
	public void setChaveNome(String chaveNome) {
		this.chaveNome = chaveNome;
	}
	
	
	
	public Fornecedor getFornecedor() {
		return fornecedor;
	}
	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}
	public String incluir() {
		br.adicionar(fornecedor);
		return "fornecedorListagem";
	}
	public String editar(Fornecedor fornecedor) {
		this.fornecedor=fornecedor;
		return "fornecedorEdicao";
	}
	public String novo() {
		fornecedor = new Fornecedor();
		return "fornecedorInclusao";
	}
	public String alterar() {
		br.atualizar(fornecedor);
		return "fornecedorListagem";
	}
	public String remover() {
		br.remover(fornecedor);
		return "fornecedorListagem";
	}
	public String excluir(Fornecedor fornecedor) {
		this.fornecedor=fornecedor;
		return "fornecedorExclusao";
	}
	public void setBanco(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}
	
	
}