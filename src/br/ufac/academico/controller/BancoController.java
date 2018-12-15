package br.ufac.academico.controller;

import java.util.List;

import javax.faces.bean.*;

import br.ufac.academico.domain.*;
import br.ufac.academico.repositories.*;

@ManagedBean(name="bancoControlador")
@SessionScoped
public class BancoController { 
	private BancoRepositorio br;
	private List<Banco> bancos;
	private String chaveNome = "";
	private Banco banco;
	
	public BancoController() {
		br = new BancoRepositorio();		
	}
	public List<Banco> getBancos() {
		bancos= br.recuperarTodosPorNome();
		return bancos;
	}
	public void setBancos(List<Banco> bancos) {
		this.bancos = bancos;
	}
	public BancoRepositorio getCr() {
		return br;
	}
	public String getChaveNome() {
		return chaveNome;
	}
	public void setChaveNome(String chaveNome) {
		this.chaveNome = chaveNome;
	}
	public Banco getBanco() {
		return banco;
	}
	public String incluir() {
		br.adicionar(banco);
		return "bancoListagem";
	}
	public String editar(Banco banco) {
		this.banco=banco;
		return "bancoEdicao";
	}
	public String novo() {
		banco = new Banco();
		return "bancoInclusao";
	}
	public String alterar() {
		br.atualizar(banco);
		return "bancoListagem";
	}
	public String remover() {
		br.remover(banco);
		return "bancoListagem";
	}
	public String excluir(Banco banco) {
		this.banco=banco;
		return "bancoExclusao";
	}
	public void setBanco(Banco banco) {
		this.banco = banco;
	}
	
	
}