package br.academico.controladores;

import java.util.List;

import javax.faces.bean.*;
import javax.persistence.Column;

import br.ufac.academico.entidades.*;
import br.ufac.academico.repositorios.*;

@ManagedBean(name="atendenteControlador")
@SessionScoped
public class AtendentesMB { 
	private AtendentesRepositorio br;
	private List<Atendentes> atendentes;
	private String chaveNome = "";
	private Atendentes atendente;
	
	private String nome;
	
	private String ultimoAcesso;
	
	private String ramal;
	
	private String email;
	
	private char status;
	
	private String ate_perfil;
	
	private long codigo;
	
	public Atendentes getAtendente() {
		return atendente;
	}
	public void setAtendente(Atendentes atendente) {
		this.atendente = atendente;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getUltimoAcesso() {
		return ultimoAcesso;
	}
	public void setUltimoAcesso(String ultimoAcesso) {
		this.ultimoAcesso = ultimoAcesso;
	}
	public String getRamal() {
		return ramal;
	}
	public void setRamal(String ramal) {
		this.ramal = ramal;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public char getStatus() {
		return status;
	}
	public void setStatus(char status) {
		this.status = status;
	}
	public String getAte_perfil() {
		return ate_perfil;
	}
	public void setAte_perfil(String ate_perfil) {
		this.ate_perfil = ate_perfil;
	}
	public long getCodigo() {
		return codigo;
	}
	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}
	public AtendentesMB() {
		br = new AtendentesRepositorio();		
	}
	public List<Atendentes> getAtendentes() {
		atendentes= br.recuperarTodosPorNome();
		return atendentes;
	}
	public void setAtendentes(List<Atendentes> atendentes) {
		this.atendentes = atendentes;
	}
	public AtendentesRepositorio getCr() {
		return br;
	}
	public String getChaveNome() {
		return chaveNome;
	}
	public void setChaveNome(String chaveNome) {
		this.chaveNome = chaveNome;
	}
	public Atendentes getBanco() {
		return atendente;
	}
	public String incluir() {
		br.adicionar(atendente);
		return "atendenteListagem";
	}
	public String editar(Atendentes atendente) {
		this.atendente=atendente;
		return "atendenteEdicao";
	}
	public String novo() {
		atendente = new Atendentes();
		return "atendenteInclusao";
	}
	public String alterar() {
		br.atualizar(atendente);
		return "atendenteListagem";
	}
	public String remover() {
		br.remover(atendente);
		return "atendenteListagem";
	}
	public String excluir(Atendentes atendente) {
		this.atendente=atendente;
		return "atendenteExclusao";
	}
	public void setBanco(Atendentes atendente) {
		this.atendente = atendente;
	}
	
	
}