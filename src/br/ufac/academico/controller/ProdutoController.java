package br.ufac.academico.controller;

import java.util.List;

import javax.faces.bean.*;

import br.ufac.academico.domain.*;
import br.ufac.academico.repositories.*;

@ManagedBean(name="produtoControlador")
@SessionScoped
public class ProdutoController { 
	private ProdutoRepositorio br;
	private FornecedorRepositorio fr;
	private CategoriaProdutoRepositorio cpr;
	private Integer forCodigo;
	private Integer catCodigo;
	private List<Produto> produtos;
	private String chaveNome = "";
	private Produto produto;
	
	public ProdutoController() {
		br = new ProdutoRepositorio();		
		cpr = new CategoriaProdutoRepositorio();
		fr = new FornecedorRepositorio();
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
		produto.setCategoria(cpr.recuperar(catCodigo));
		produto.setFornecedor(fr.recuperar(forCodigo));
		br.adicionar(produto);
		return "produtoListagem";
	}
	public String editar(Produto produto) {
		this.produto=produto;
		catCodigo = produto.getCategoria().getCodigo();
		forCodigo = produto.getFornecedor().getCodigo();
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
		catCodigo = produto.getCategoria().getCodigo();
		forCodigo = produto.getFornecedor().getCodigo();
		return "produtoExclusao";
	}
	public Integer getForCodigo() {
		return forCodigo;
	}
	public void setForCodigo(Integer forCodigo) {
		this.forCodigo = forCodigo;
	}
	public Integer getCatCodigo() {
		return catCodigo;
	}
	public void setCatCodigo(Integer catCodigo) {
		this.catCodigo = catCodigo;
	}
		
}