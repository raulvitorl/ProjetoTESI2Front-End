package br.ufac.academico.controller;

import java.util.List;

import javax.faces.bean.*;

import br.ufac.academico.domain.*;
import br.ufac.academico.repositories.*;

@ManagedBean(name="vendaControlador")
@SessionScoped
public class VendaMB { 
	private VendaRepositorio br;
	private List<Venda> vendas;
	private String chaveNome = "";
	private Venda venda;
	public VendaMB() {
		br = new VendaRepositorio();		
	}
	public List<Venda> getVendas() {
		vendas= br.recuperarTodos();
		return vendas;
	}
	public void setVendas(List<Venda> vendas) {
		this.vendas = vendas;
	}
	public VendaRepositorio getCr() {
		return br;
	}
	public String getChaveNome() {
		return chaveNome;
	}
	public void setChaveNome(String chaveNome) {
		this.chaveNome = chaveNome;
	}	
	
	public Venda getVenda() {
		return venda;
	}
	public void setVenda(Venda venda) {
		this.venda = venda;
	}
	public String incluir() {
		br.adicionar(venda);
		return "vendaListagem";
	}
	public String editar(Venda venda) {
		this.venda=venda;
		return "vendaEdicao";
	}
	public String novo() {
		venda = new Venda();
		return "vendaInclusao";
	}
	public String alterar() {
		br.atualizar(venda);
		return "vendaListagem";
	}
	public String remover() {
		br.remover(venda);
		return "vendaListagem";
	}
	public String excluir(Venda venda) {
		this.venda=venda;
		return "vendaExclusao";
	}
	public void setBanco(Venda venda) {
		this.venda = venda;
	}
	
	
}