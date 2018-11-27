package br.academico.controladores;

import java.util.List;

import javax.faces.bean.*;

import br.ufac.academico.entidades.*;
import br.ufac.academico.repositorios.*;

@ManagedBean(name="bancoControlador")
@SessionScoped
public class BancosMB { 
	private BancosRepositorio br;
	private List<Bancos> bancos;
	private String chaveNome = "";
	private Bancos banco;
	private String nome;
	private int codigo;
		
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public BancosMB() {
		br = new BancosRepositorio();		
	}
	public List<Bancos> getBancos() {
		bancos= br.recuperarTodosPorNome();
		return bancos;
	}
	public void setBancos(List<Bancos> bancos) {
		this.bancos = bancos;
	}
	public BancosRepositorio getCr() {
		return br;
	}
	public String getChaveNome() {
		return chaveNome;
	}
	public void setChaveNome(String chaveNome) {
		this.chaveNome = chaveNome;
	}
	public Bancos getBanco() {
		return banco;
	}
	public String incluir() {
		br.adicionar(banco);
		return "bancoListagem";
	}
	public String editar(Bancos banco) {
		this.banco=banco;
		return "bancoEdicao";
	}
	public String novo() {
		banco = new Bancos();
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
	public String excluir(Bancos banco) {
		this.banco=banco;
		return "bancoExclusao";
	}
	public void setBanco(Bancos banco) {
		this.banco = banco;
	}
	
	
}