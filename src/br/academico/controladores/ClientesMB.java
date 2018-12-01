package br.academico.controladores;

import java.sql.Date;
import java.util.List;

import javax.faces.bean.*;


import br.ufac.academico.entidades.*;
import br.ufac.academico.repositorios.*;

@ManagedBean(name="clienteControlador")
@SessionScoped
public class ClientesMB { 
	private ClientesRepositorio cr;
	private List<Clientes> clientes;
	private String chaveNome = "";
	private Clientes cliente;
	private int cli_codigo;
	private Municipios cli_mun_codigo;	
	private String cli_nome;
	private Date cli_data_nascimento;
	private String cli_sexo;
	private String cli_endereco;
	private String cli_email;
	private Date cli_data_cadastro;
	private Integer cli_tipo;
	private String cli_status;
	private String cli_fone;
	private String cli_nome_contato;
	private String cli_Cpf;
	
	public String getCli_Cpf() {return cli_Cpf;}
	public void setCli_Cpf(String cli_Cpf) {this.cli_Cpf = cli_Cpf;}
	public Clientes getCliente(){return cliente;}
	public void setCliente(Clientes cliente) {this.cliente = cliente;}
	public int getCli_codigo(){return cli_codigo;}
	public void setCli_codigo(int cli_codigo) {
		this.cli_codigo = cli_codigo;
	}
	public Municipios getCli_mun_codigo() {
		return cli_mun_codigo;
	}
	public void setCli_mun_codigo(Municipios cli_mun_codigo) {
		this.cli_mun_codigo = cli_mun_codigo;
	}
	public String getCli_nome() {
		return cli_nome;
	}
	public void setCli_nome(String cli_nome) {
		this.cli_nome = cli_nome;
	}
	public Date getCli_data_nascimento() {
		return cli_data_nascimento;
	}
	public void setCli_data_nascimento(Date cli_data_nascimento) {
		this.cli_data_nascimento = cli_data_nascimento;
	}
	public String getCli_sexo() {
		return cli_sexo;
	}
	public void setCli_sexo(String cli_sexo) {
		this.cli_sexo = cli_sexo;
	}
	public String getCli_endereco() {
		return cli_endereco;
	}
	public void setCli_endereco(String cli_endereco) {
		this.cli_endereco = cli_endereco;
	}
	public String getCli_email() {
		return cli_email;
	}
	public void setCli_email(String cli_email) {
		this.cli_email = cli_email;
	}
	public Date getCli_data_cadastro() {
		return cli_data_cadastro;
	}
	public void setCli_data_cadastro(Date cli_data_cadastro) {
		this.cli_data_cadastro = cli_data_cadastro;
	}
	public Integer getCli_tipo() {
		return cli_tipo;
	}
	public void setCli_tipo(Integer cli_tipo) {
		this.cli_tipo = cli_tipo;
	}
	public String getCli_status() {
		return cli_status;
	}
	public void setCli_status(String cli_status) {
		this.cli_status = cli_status;
	}
	public String getCli_fone() {
		return cli_fone;
	}
	public void setCli_fone(String cli_fone) {
		this.cli_fone = cli_fone;
	}
	public String getCli_nome_contato() {
		return cli_nome_contato;
	}
	public void setCli_nome_contato(String cli_nome_contato) {
		this.cli_nome_contato = cli_nome_contato;
	}
	public List<Clientes> getClientes() {
		clientes = cr.recuperarTodos();
		return clientes;
	}
	public ClientesMB() {
		cr = new ClientesRepositorio();		
	}
	public List<Clientes> getClientesPorNome() {
		clientes= cr.recuperarTodosPorNome();
		return clientes;
	}
	
	public void setClientes(List<Clientes> clientes) {
		this.clientes = clientes;
	}
	public ClientesRepositorio getCr() {
		return cr;
	}
	public String getChaveNome() {
		return chaveNome;
	}
	public void setChaveNome(String chaveNome) {
		this.chaveNome = chaveNome;
	}
	
	public String incluir() {
		cr.adicionar(cliente);
		return "clienteListagem";
	}
	public String editar(Clientes cliente) {
		this.cliente=cliente;
		return "cclienteEdicao";
	}
	public String novo() {
		cliente = new Clientes(){
			private static final long serialVersionUID = 1L;
		};
		return "clienteInclusao";
	}
	public String alterar() {
		cr.atualizar(cliente);
		return "clienteListagem";
	}
	public String remover() {
		cr.remover(cliente);
		return "clienteListagem";
	}
	public String excluir(Clientes cliente) {
		this.cliente=cliente;
		return "clienteExclusao";
	}
			
}