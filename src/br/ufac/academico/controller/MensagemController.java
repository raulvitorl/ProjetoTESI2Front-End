package br.ufac.academico.controller;

import java.util.List;

import javax.faces.bean.*;

import br.ufac.academico.domain.*;
import br.ufac.academico.repositories.*;

@ManagedBean(name="mensagemControlador")
@SessionScoped
public class MensagemController { 
	private MensagemRepositorio mr;
	private AtendenteRepositorio ar;
	private TipoMensagemRepositorio tmsr;
	private List<Mensagem> mensagens;
	private String chaveNome = "";
	private Mensagem mensagem;
	private Integer ateCodigo;
	private Integer tipoCodigo;
	private String men_texto;
	private String men_data_envio;
	

	
	public Integer getAteCodigo() {
		return ateCodigo;
	}

	public void setAteCodigo(Integer ateCodigo) {
		this.ateCodigo = ateCodigo;
	}

	public Integer getTipoCodigo() {
		return tipoCodigo;
	}

	public void setTipoCodigo(Integer tipoCodigo) {
		this.tipoCodigo = tipoCodigo;
	}

	public MensagemController() {
		mr = new MensagemRepositorio();
		ar = new AtendenteRepositorio();
		tmsr = new TipoMensagemRepositorio();
	}

	public List<Mensagem> getMensagens() {
		mensagens = mr.recuperarTodos();
		return mensagens;
	}
	public void setMensagens(List<Mensagem> mensagens) {
		this.mensagens = mensagens;
	}
	public String getChaveNome() {
		return chaveNome;
	}
	public void setChaveNome(String chaveNome) {
		this.chaveNome = chaveNome;
	}
	public Mensagem getMensagem() {
		return mensagem;
	}
	public void setMensagem(Mensagem mensagem) {
		this.mensagem = mensagem;
	}

	public Integer getMen_ate_codigo() {
		return ateCodigo;
	}
	public void setMen_ate_codigo(int ateCodigo) {
		this.ateCodigo = ateCodigo;
	}
	public Integer getMen_tms_codigo() {
		return tipoCodigo;
	}
	public void setMen_tms_codigo(int tipoCodigo) {
		this.tipoCodigo = tipoCodigo;
	}
	public String getMen_texto() {
		return men_texto;
	}
	public void setMen_texto(String men_texto) {
		this.men_texto = men_texto;
	}
	public String getMen_data_envio() {
		return men_data_envio;
	}
	public void setMen_data_envio(String men_data_envio) {
		this.men_data_envio = men_data_envio;
	}
	public String incluir() {
		mensagem = new Mensagem();
		return "mensagemInclusao";
	}

	public String adicionar(){
		System.out.println("entrou");
		mensagem.setAtendente(ar.recuperar(ateCodigo));
		mensagem.setTipo(tmsr.recuperar(tipoCodigo));
		mr.adicionar(mensagem);
		return "mensagemListagem";
	}

	public String editar(Mensagem mensagem) {
		this.mensagem=mensagem;
		tipoCodigo = mensagem.getTipo().getCodigo();
		ateCodigo = mensagem.getAtendente().getCodigo();
		return "mensagemEdicao";
	}

	public String atualizar() {
		mensagem.setAtendente(ar.recuperar(ateCodigo));
		mensagem.setTipo(tmsr.recuperar(tipoCodigo));
		mr.atualizar(mensagem);
		return "mensagemListagem";
	}

	public String excluir(Mensagem mensagem) {
		this.mensagem=mensagem;
		tipoCodigo = mensagem.getTipo().getCodigo();
		ateCodigo = mensagem.getAtendente().getCodigo();
		return "mensagemExclusao";
	}
	
	public String remover() {
		mr.remover(mensagem);
		return "mensagemListagem";
	}
	
	

}