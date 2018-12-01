package br.academico.controladores;

import java.util.List;

import javax.faces.bean.*;

import br.ufac.academico.entidades.*;
import br.ufac.academico.repositorios.*;

@ManagedBean(name="municipioControlador")
@SessionScoped
public class MunicipiosMB { 
	private MunicipiosRepositorio br;
	private List<Municipios> municipios;
	private String chaveNome = "";
	private Municipios municipio;
	private Integer mun_codigo;
	private String mun_nome; 
	private String mun_uf_estado;
	private String mun_cep;
		
	
	public Municipios getMunicipio() {
		return municipio;
	}
	public void setMunicipio(Municipios municipio) {
		this.municipio = municipio;
	}
	public Integer getMun_codigo() {
		return mun_codigo;
	}
	public void setMun_codigo(Integer mun_codigo) {
		this.mun_codigo = mun_codigo;
	}
	public String getMun_nome() {
		return mun_nome;
	}
	public void setMun_nome(String mun_nome) {
		this.mun_nome = mun_nome;
	}
	public String getMun_uf_estado() {
		return mun_uf_estado;
	}
	public void setMun_uf_estado(String mun_uf_estado) {
		this.mun_uf_estado = mun_uf_estado;
	}
	public String getMun_cep() {
		return mun_cep;
	}
	public void setMun_cep(String mun_cep) {
		this.mun_cep = mun_cep;
	}
	public MunicipiosMB() {
		br = new MunicipiosRepositorio();		
	}
	public List<Municipios> getMunicipios() {
		municipios= br.recuperarTodosPorNome();
		return municipios;
	}
	public void setMunicipios(List<Municipios> municipios) {
		this.municipios = municipios;
	}
	public MunicipiosRepositorio getCr() {
		return br;
	}
	public String getChaveNome() {
		return chaveNome;
	}
	public void setChaveNome(String chaveNome) {
		this.chaveNome = chaveNome;
	}
	public Municipios getBanco() {
		return municipio;
	}
	public String incluir() {
		br.adicionar(municipio);
		return "municipioListagem";
	}
	public String editar(Municipios municipio) {
		this.municipio=municipio;
		return "municipioEdicao";
	}
	public String novo() {
		municipio = new Municipios();
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
	public String excluir(Municipios municipio) {
		this.municipio=municipio;
		return "municipioExclusao";
	}
	public void setBanco(Municipios municipio) {
		this.municipio = municipio;
	}
	
	
}