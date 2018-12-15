package br.ufac.academico.controller;

import java.util.List;

import javax.faces.bean.*;

import br.ufac.academico.domain.*;
import br.ufac.academico.repositories.*;

@ManagedBean(name="tipomensagemControlador")
@SessionScoped
public class TipoMensagemController { 
	private TipoMensagemRepositorio br;
	private List<TipoMensagem> tipomensagens;
	private String chaveNome = "";
	private TipoMensagem tipomensagem;
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
	public TipoMensagemController() {
		br = new TipoMensagemRepositorio();		
	}
	public List<TipoMensagem> getTiposMensagens() {
		tipomensagens= br.recuperarTodos();
		return tipomensagens;
	}
	public void setTipoMensagem(List<TipoMensagem> tipomensagems) {
		this.tipomensagens = tipomensagems;
	}
	public TipoMensagemRepositorio getCr() {
		return br;
	}
	public String getChaveNome() {
		return chaveNome;
	}
	public void setChaveNome(String chaveNome) {
		this.chaveNome = chaveNome;
	}
	public TipoMensagem getTipoMensagem() {
		return tipomensagem;
	}
	public String incluir() {
		br.adicionar(tipomensagem);
		return "tipomensagemListagem";
	}
	public String editar(TipoMensagem tipomensagem) {
		this.tipomensagem=tipomensagem;
		return "tipomensagemEdicao";
	}
	public String novo() {
		tipomensagem = new TipoMensagem();
		return "tipomensagemInclusao";
	}
	public String alterar() {
		br.atualizar(tipomensagem);
		return "tipomensagemListagem";
	}
	public String remover() {
		br.remover(tipomensagem);
		return "tipomensagemListagem";
	}
	public String excluir(TipoMensagem tipomensagem) {
		this.tipomensagem=tipomensagem;
		return "tipomensagemExclusao";
	}
	public void setTipoMensagem(TipoMensagem tipomensagem) {
		this.tipomensagem = tipomensagem;
	}
	
	
}