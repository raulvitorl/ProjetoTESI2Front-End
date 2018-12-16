package br.ufac.academico.controller;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.faces.bean.*;

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
		br.remover(cliente);
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
	
	public String relatorio(Cliente c){ 
		String nome = UUID.randomUUID().toString();
		vendas = c.getVendas(); 
		float valortotal = 0;
		Document document = new Document(); 
		try { 
			PdfWriter.getInstance(document, new FileOutputStream(nome+".pdf")); 
			} catch (FileNotFoundException | DocumentException e) 
		{ e.printStackTrace(); } 
			document.open();
			PdfPTable table = new PdfPTable(8);
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
		
		catch (DocumentException e) { e.printStackTrace(); }
		try {
			document.add(table);
		} catch (DocumentException e1) {
			e1.printStackTrace();
		}
		document.close(); 
		try { 
			Desktop.getDesktop().open(new File(nome+".pdf"));
			} catch (IOException e) { e.printStackTrace(); } 
		return "vendaListagem"; 
		}


	
	
}