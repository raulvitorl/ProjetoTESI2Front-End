package br.ufac.academico.controller;

import java.util.List;

import javax.faces.bean.*;


import br.ufac.academico.domain.*;
import br.ufac.academico.repositories.*;

@ManagedBean(name="categoriaControlador")
@SessionScoped
public class CategoriaProdutoController { 
	private CategoriaProdutoRepositorio cr;
	private List<CategoriaProduto> categorias;
	private String chaveNome = "";
	private CategoriaProduto categoria;
	
	private Integer catCodigo;
	
	private int catIdentificador;
	
	private String catDescricao;
	
	
	public List<CategoriaProduto> getCategorias() {
		categorias = cr.recuperarTodos();
		return categorias;
	}
	public void setCategorias(List<CategoriaProduto> categorias) {
		this.categorias = categorias;
	}
	public CategoriaProduto getCategoria() {
		return categoria;
	}
	public void setCategoria(CategoriaProduto categoria) {
		this.categoria = categoria;
	}
	public Integer getCatCodigo() {
		return catCodigo;
	}
	public void setCatCodigo(Integer catCodigo) {
		this.catCodigo = catCodigo;
	}
	public int getCatIdentificador() {
		return catIdentificador;
	}
	public void setCatIdentificador(int catIdentificador) {
		this.catIdentificador = catIdentificador;
	}
	public String getCatDescricao() {
		return catDescricao;
	}
	public void setCatDescricao(String catDescricao) {
		this.catDescricao = catDescricao;
	}
	public CategoriaProdutoController() {
		cr = new CategoriaProdutoRepositorio();		
	}
	public List<CategoriaProduto> getBancos() {
		categorias= cr.recuperarTodosPorNome();
		return categorias;
	}
	public void setBancos(List<CategoriaProduto> categorias) {
		this.categorias = categorias;
	}
	public CategoriaProdutoRepositorio getCr() {
		return cr;
	}
	public String getChaveNome() {
		return chaveNome;
	}
	public void setChaveNome(String chaveNome) {
		this.chaveNome = chaveNome;
	}
	public String incluir() {
		cr.adicionar(categoria);
		return "categoriaListagem";
	}
	public String editar(CategoriaProduto categoria) {
		this.categoria=categoria;
		return "categoriaEdicao";
	}
	public String novo() {
		categoria = new CategoriaProduto();
		return "categoriaInclusao";
	}
	public String alterar() {
		cr.atualizar(categoria);
		return "categoriaListagem";
	}
	public String remover() {
		cr.remover(categoria);
		return "categoriaListagem";
	}
	public String excluir(CategoriaProduto categoria) {
		this.categoria=categoria;
		return "categoriaExclusao";
	}
		
}