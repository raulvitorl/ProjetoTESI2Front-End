package br.ufac.academico.controller;

import java.util.List;

import javax.faces.bean.*;

import br.ufac.academico.domain.*;
import br.ufac.academico.repositories.*;

@ManagedBean(name="atendenteControlador")
@SessionScoped
public class AtendenteMB { 
	private AtendenteRepositorio br;
	private List<Atendente> atendentes;
	private String chaveNome = "";
	private Atendente atendente;
	public AtendenteMB() {
		br = new AtendenteRepositorio();		
	}
	public List<Atendente> getAtendentes() {
		atendentes= br.recuperarTodosPorNome();
		return atendentes;
	}
	public void setAtendentes(List<Atendente> atendentes) {
		this.atendentes = atendentes;
	}
	public AtendenteRepositorio getCr() {
		return br;
	}
	public String getChaveNome() {
		return chaveNome;
	}
	public void setChaveNome(String chaveNome) {
		this.chaveNome = chaveNome;
	}
	public Atendente getBanco() {
		return atendente;
	}
	public String incluir() {
		br.adicionar(atendente);
		return "atendenteListagem";
	}
	public String editar(Atendente atendente) {
		this.atendente=atendente;
		return "atendenteEdicao";
	}
	public String novo() {
		atendente = new Atendente();
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
	public String excluir(Atendente atendente) {
		this.atendente=atendente;
		return "atendenteExclusao";
	}
	public void setBanco(Atendente atendente) {
		this.atendente = atendente;
	}
	
	
}