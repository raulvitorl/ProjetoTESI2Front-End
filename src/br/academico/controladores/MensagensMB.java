package br.academico.controladores;

import java.util.List;

import javax.faces.bean.*;

import br.ufac.academico.entidades.*;
import br.ufac.academico.repositorios.*;

@ManagedBean(name="mensagemControlador")
@SessionScoped
public class MensagensMB { 
	private MensagensRepositorio mr;
	private AtendentesRepositorio ar;
	private TiposMensagensRepositorio tmsr;
	private List<Mensagens> mensagens;
	private String chaveNome = "";
	private Mensagens mensagem;
	private long men_codigo;
	private long men_ate_codigo;
	private long men_tms_codigo;
	private String men_texto;
	private String men_data_envio;

	

	public void setMen_ate_codigo(long men_ate_codigo) {
		this.men_ate_codigo = men_ate_codigo;
	}

	public void setMen_tms_codigo(long men_tms_codigo) {
		this.men_tms_codigo = men_tms_codigo;
	}

	public MensagensMB() {
		mensagem = new Mensagens();
		mr = new MensagensRepositorio();
		ar = new AtendentesRepositorio();
		tmsr = new TiposMensagensRepositorio();
	}

	public List<Mensagens> getMensagens() {
		mensagens = mr.recuperarTodos();
		return mensagens;
	}
	public void setMensagens(List<Mensagens> mensagens) {
		this.mensagens = mensagens;
	}
	public String getChaveNome() {
		return chaveNome;
	}
	public void setChaveNome(String chaveNome) {
		this.chaveNome = chaveNome;
	}
	public Mensagens getMensagem() {
		return mensagem;
	}
	public void setMensagem(Mensagens mensagem) {
		this.mensagem = mensagem;
	}
	public long getMen_codigo() {
		return men_codigo;
	}
	public void setMen_codigo(long men_codigo) {
		this.men_codigo = men_codigo;
	}
	public long getMen_ate_codigo() {
		return men_ate_codigo;
	}
	public void setMen_ate_codigo(int men_ate_codigo) {
		this.men_ate_codigo = men_ate_codigo;
	}
	public long getMen_tms_codigo() {
		return men_tms_codigo;
	}
	public void setMen_tms_codigo(int men_tms_codigo) {
		this.men_tms_codigo = men_tms_codigo;
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
		mensagem = new Mensagens();		
		return "mensagemInclusao";
	}

	public String adicionar(){
		mensagem.setMen_ate_codigo(ar.recuperar(men_ate_codigo));
		mensagem.setTipoMensagem(tmsr.recuperar(men_tms_codigo));
		mr.adicionar(mensagem);
		return "mensagemListagem";
	}

	public String editar(Mensagens mensagem) {
		this.mensagem=mensagem;
		men_tms_codigo = mensagem.getTipoMensagem().getTmsCodigo();
		men_ate_codigo = mensagem.getMen_ate_codigo().getCodigo();
		return "mensagemEdicao";
	}

	public String atualizar() {
		mensagem.setMen_ate_codigo(ar.recuperar(men_ate_codigo));
		mensagem.setTipoMensagem(tmsr.recuperar(men_tms_codigo));
		mr.atualizar(mensagem);
		return "mensagemListagem";
	}

	public String excluir(Mensagens mensagem) {
		this.mensagem=mensagem;
		men_tms_codigo = mensagem.getTipoMensagem().getTmsCodigo();
		men_ate_codigo = mensagem.getMen_ate_codigo().getCodigo();
		return "mensagemExclusao";
	}
	
	public String remover() {
		mr.remover(mensagem);
		return "mensagemListagem";
	}

}