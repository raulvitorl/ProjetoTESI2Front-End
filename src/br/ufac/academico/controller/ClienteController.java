package br.ufac.academico.controller;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import br.ufac.academico.domain.*;
import br.ufac.academico.repositories.*;

@ManagedBean(name="clienteControlador")
@SessionScoped
public class ClienteController { 
	private ClienteRepositorio br;
	private MunicipioRepositorio mr;
	private List<Cliente> clientes;
	private List <Venda> vendas = new ArrayList<>();
	private Integer munCodigo;
	private String chaveNome = "";
	private Cliente cliente;
	private String mensagemDeErro;
	public ClienteController() {
		br = new ClienteRepositorio();	
		mr = new MunicipioRepositorio();
	}
	public List<Cliente> getClientes() {
		clientes= br.recuperarTodosPorNome();
		return clientes;
	}
	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}
		
	public Integer getMunCodigo() {
		return munCodigo;
	}
	public void setMunCodigo(Integer munCodigo) {
		this.munCodigo = munCodigo;
	}
	public ClienteRepositorio getCr() {
		return br;
	}
	public String getChaveNome() {
		return chaveNome;
	}
	public void setChaveNome(String chaveNome) {
		this.chaveNome = chaveNome;
	}	
	
	
	
	public String getMensagemDeErro() {
		return mensagemDeErro;
	}
	public void setMensagemDeErro(String mensagemDeErro) {
		this.mensagemDeErro = mensagemDeErro;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public String incluir() {
		Municipio m = mr.recuperar(munCodigo);
		cliente.setMunicipio(m);
		br.adicionar(cliente);
		return "clienteListagem";
	}
	public String editar(Cliente cliente) {
		this.cliente=cliente;
		munCodigo = cliente.getMunicipio().getCodigo();
		return "clienteEdicao";
	}
	public String novo() {
		cliente = new Cliente();
		return "clienteInclusao";
	}
	public String alterar() {
		br.atualizar(cliente);
		return "clienteListagem";
	}
	
	public String remover() {
		try {
			br.remover(cliente);
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
					"O nome de administrador informado já esta sendo usado", 
					"O nome de administrador informado já esta sendo usado"));
		}
		
		return "clienteListagem";
	}
	public String excluir(Cliente cliente) {
		this.cliente=cliente;
		munCodigo = cliente.getMunicipio().getCodigo();
		return "clienteExclusao";
	}
	public void setBanco(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public String relatorio(Cliente c) throws DocumentException, IOException{ 
		String nome = UUID.randomUUID().toString();
		float valortotal = 0;
		Document document = new Document(); 
		try { 
			PdfWriter.getInstance(document, new FileOutputStream(nome+".pdf")); 
			} catch (FileNotFoundException | DocumentException e) 
		{ e.printStackTrace(); } 
			document.open();
			vendas = c.getVendas();
			
		try { 
			document.add(new Paragraph("CLIENTE:  "+c.getCodigo()+"   "+c.getNome()));
			document.add(new Paragraph("CLIENTE DESDE:  "+c.getCadastro()));
			document.add(new Paragraph("JÁ REALIZOU  :"+vendas.size()+" COMPRAS EM NOSSA LOJA"));
			document.add(new Paragraph("----------------------COMPRAS--------------------------------"));
			for(Venda v: vendas) { 
				document.add(new Paragraph("        "+v.getCodigo()+"        |        "+v.getValorTotal()+"        |         "+v.getFormaPagamento()+"      |      "+v.getStatusPagamento())); 
				valortotal+=v.getValorTotal();
			}
			document.add(new Paragraph("VALOR TOTAL GASTO: R$"+valortotal));
			}
		
		catch (DocumentException | NullPointerException e) {
			document.add(new Paragraph("CLIENTE NAO POSSUI DADOS PARA SEREM MOSTRADOS"));
			document.close(); 
			Desktop.getDesktop().open(new File(nome+".pdf")); 
			return "vendaListagem";}
			document.close(); 
		try { 
			Desktop.getDesktop().open(new File(nome+".pdf"));
			} catch (IOException e) { e.printStackTrace(); } 
		return "vendaListagem"; 
		}


	
	
}