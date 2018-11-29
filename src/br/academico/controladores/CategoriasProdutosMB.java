package br.academico.controladores;

import java.util.List;

import javax.faces.bean.*;


import br.ufac.academico.entidades.*;
import br.ufac.academico.repositorios.*;

@ManagedBean(name="categoriaControlador")
@SessionScoped
public class CategoriasProdutosMB { 
	private CategoriasProdutosRepositorio cr;
	private List<CategoriasProdutos> categorias;
	private String chaveNome = "";
	private CategoriasProdutos categoria;
	
	private long catCodigo;
	
	private int catIdentificador;
	
	private String catDescricao;
	
	
	public List<CategoriasProdutos> getCategorias() {
		categorias = cr.recuperarTodos();
		return categorias;
	}
	public void setCategorias(List<CategoriasProdutos> categorias) {
		this.categorias = categorias;
	}
	public CategoriasProdutos getCategoria() {
		return categoria;
	}
	public void setCategoria(CategoriasProdutos categoria) {
		this.categoria = categoria;
	}
	public long getCatCodigo() {
		return catCodigo;
	}
	public void setCatCodigo(long catCodigo) {
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
	public CategoriasProdutosMB() {
		cr = new CategoriasProdutosRepositorio();		
	}
	public List<CategoriasProdutos> getBancos() {
		categorias= cr.recuperarTodosPorNome();
		return categorias;
	}
	public void setBancos(List<CategoriasProdutos> categorias) {
		this.categorias = categorias;
	}
	public CategoriasProdutosRepositorio getCr() {
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
	public String editar(CategoriasProdutos categoria) {
		this.categoria=categoria;
		return "categoriaEdicao";
	}
	public String novo() {
		categoria = new CategoriasProdutos();
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
	public String excluir(CategoriasProdutos categoria) {
		this.categoria=categoria;
		return "categoriaExclusao";
	}
		
}