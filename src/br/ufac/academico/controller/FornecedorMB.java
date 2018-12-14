package br.ufac.academico.controller;

import java.util.List;

import javax.faces.bean.*;

import br.ufac.academico.domain.*;
import br.ufac.academico.repositories.*;

@ManagedBean(name="fornecedorControlador")
@SessionScoped
public class FornecedorMB { 
	private FornecedorRepositorio br;
	private MunicipioRepositorio mr;	
	private List<Fornecedor> fornecedores;
	private String chaveNome = "";
	private Fornecedor fornecedor;
	private Integer munCodigo;
	
	public FornecedorMB() {
		br = new FornecedorRepositorio();
		mr = new MunicipioRepositorio();		
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
	
	
	
	public List<Fornecedor> getFornecedores() {
		return fornecedores;
	}
	public void setFornecedores(List<Fornecedor> fornecedores) {
		this.fornecedores = fornecedores;
	}
	public Integer getMunCodigo() {
		return munCodigo;
	}
	public void setMunCodigo(Integer munCodigo) {
		this.munCodigo = munCodigo;
	}
	public Fornecedor getFornecedor() {
		return fornecedor;
	}
	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}
	public String incluir() {
		fornecedor.setMunicipio(mr.recuperar(munCodigo));
		br.adicionar(fornecedor);
		return "fornecedorListagem";
	}
	public String editar(Fornecedor fornecedor) {
		this.fornecedor=fornecedor;
		munCodigo = fornecedor.getMunicipio().getCodigo();
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
		munCodigo = fornecedor.getMunicipio().getCodigo();
		return "fornecedorExclusao";
	}
	public void setBanco(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}
	
	
}