package br.academico.controladores;

import java.util.List;

import javax.faces.bean.*;

import br.ufac.academico.entidades.*;
import br.ufac.academico.repositorios.*;

@ManagedBean(name="tipomensagemControlador")
@SessionScoped
public class TiposMensagensMB { 
	private TiposMensagensRepositorio br;
	private List<TiposMensagens> tipomensagens;
	private String chaveNome = "";
	private TiposMensagens tipomensagem;
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
	public TiposMensagensMB() {
		br = new TiposMensagensRepositorio();		
	}
	public List<TiposMensagens> getTiposMensagens() {
		tipomensagens= br.recuperarTodos();
		return tipomensagens;
	}
	public void setTiposMensagens(List<TiposMensagens> tipomensagems) {
		this.tipomensagens = tipomensagems;
	}
	public TiposMensagensRepositorio getCr() {
		return br;
	}
	public String getChaveNome() {
		return chaveNome;
	}
	public void setChaveNome(String chaveNome) {
		this.chaveNome = chaveNome;
	}
	public TiposMensagens getTipoMensagem() {
		return tipomensagem;
	}
	public String incluir() {
		br.adicionar(tipomensagem);
		return "tipomensagemListagem";
	}
	public String editar(TiposMensagens tipomensagem) {
		this.tipomensagem=tipomensagem;
		return "tipomensagemEdicao";
	}
	public String novo() {
		tipomensagem = new TiposMensagens();
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
	public String excluir(TiposMensagens tipomensagem) {
		this.tipomensagem=tipomensagem;
		return "tipomensagemExclusao";
	}
	public void setTipoMensagem(TiposMensagens tipomensagem) {
		this.tipomensagem = tipomensagem;
	}
	
	
}