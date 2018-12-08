package br.ufac.academico.controller;

import java.util.List;

import javax.faces.bean.*;

import br.ufac.academico.domain.*;
import br.ufac.academico.repositories.*;

@ManagedBean(name="produtoControlador")
@SessionScoped
public class ProdutoMB { 
	private ProdutoRepositorio br;
	private List<Produto> produtos;
	private String chaveNome = "";
	private Produto produto;
	public ProdutoMB() {
		br = new ProdutoRepositorio();		
	}
	public List<Produto> getProdutos() {
		produtos= br.recuperarTodosPorNome();
		return produtos;
	}
	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
	public ProdutoRepositorio getCr() {
		return br;
	}
	public String getChaveNome() {
		return chaveNome;
	}
	public void setChaveNome(String chaveNome) {
		this.chaveNome = chaveNome;
	}	
	
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public String incluir() {
		br.adicionar(produto);
		return "produtoListagem";
	}
	public String editar(Produto produto) {
		this.produto=produto;
		return "produtoEdicao";
	}
	public String novo() {
		produto = new Produto();
		return "produtoInclusao";
	}
	public String alterar() {
		br.atualizar(produto);
		return "produtoListagem";
	}
	public String remover() {
		br.remover(produto);
		return "produtoListagem";
	}
	public String excluir(Produto produto) {
		this.produto=produto;
		return "produtoExclusao";
	}
	public void setBanco(Produto produto) {
		this.produto = produto;
	}
	
	
}