package br.ufac.academico.controller;

import java.util.List;

import javax.faces.bean.*;

import br.ufac.academico.domain.*;
import br.ufac.academico.repositories.*;

@ManagedBean(name="municipioControlador")
@SessionScoped
public class MunicipioMB { 
	private MunicipioRepositorio br;
	private List<Municipio> municipios;
	private String chaveNome = "";
	private Municipio municipio;
	
	public Municipio getMunicipio() {
		return municipio;
	}
	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}
	
	public MunicipioMB() {
		br = new MunicipioRepositorio();		
	}
	public List<Municipio> getMunicipios() {
		municipios= br.recuperarTodosPorNome();
		return municipios;
	}
	
	
	public void setMunicipios(List<Municipio> municipios) {
		this.municipios = municipios;
	}
	public MunicipioRepositorio getCr() {
		return br;
	}
	public String getChaveNome() {
		return chaveNome;
	}
	public void setChaveNome(String chaveNome) {
		this.chaveNome = chaveNome;
	}
	public Municipio getBanco() {
		return municipio;
	}
	public String incluir() {
		br.adicionar(municipio);
		return "municipioListagem";
	}
	public String editar(Municipio municipio) {
		this.municipio=municipio;
		return "municipioEdicao";
	}
	public String novo() {
		municipio = new Municipio();
		return "municipioInclusao";
	}
	public String alterar() {
		br.atualizar(municipio);
		return "municipioListagem";
	}
	public String remover() {
		br.remover(municipio);
		return "municipioListagem";
	}
	public String excluir(Municipio municipio) {
		this.municipio=municipio;
		return "municipioExclusao";
	}
	public void setBanco(Municipio municipio) {
		this.municipio = municipio;
	}
	
	
}