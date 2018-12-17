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

@ManagedBean(name="vendaControlador")
@SessionScoped
public class VendaController { 
	private VendaRepositorio vr;
	private ProdutoRepositorio pr;
	private ClienteRepositorio cr;
	private AtendenteRepositorio ar;
	private BancoRepositorio br;
	private List<Venda> vendas;
	private String chaveNome = "";
	private Venda venda;
	private Integer cliCodigo;
	private Integer ateCodigo;
	private Integer banCodigo;
	private Integer proCodigo;
	private List <Produto> produtos = new ArrayList<>();
	private Produto produto;



	public Integer getProCodigo() {
		return proCodigo;
	}
	public void setProCodigo(Integer proCodigo) {
		this.proCodigo = proCodigo;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public VendaController() {
		vr = new VendaRepositorio();
		pr = new ProdutoRepositorio();
		cr = new ClienteRepositorio();
		ar = new AtendenteRepositorio();
		br = new BancoRepositorio();
	}
	public List<Venda> getVendas() {
		vendas= vr.recuperarTodos();
		return vendas;
	}
	public void setVendas(List<Venda> vendas) {
		this.vendas = vendas;
	}
	public VendaRepositorio getCr() {
		return vr;
	}
	public String getChaveNome() {
		return chaveNome;
	}
	public void setChaveNome(String chaveNome) {
		this.chaveNome = chaveNome;
	}	



	public Integer getCliCodigo() {
		return cliCodigo;
	}
	public void setCliCodigo(Integer cliCodigo) {
		this.cliCodigo = cliCodigo;
	}
	public Integer getAteCodigo() {
		return ateCodigo;
	}
	public void setAteCodigo(Integer ateCodigo) {
		this.ateCodigo = ateCodigo;
	}
	public List<Produto> getProdutos() {
		return produtos;
	}
	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
	public Venda getVenda() {
		return venda;
	}
	public void setVenda(Venda venda) {
		this.venda = venda;
	}

	public Integer getBanCodigo() {
		return banCodigo;
	}
	public void setBanCodigo(Integer banCodigo) {
		this.banCodigo = banCodigo;
	}
	public String incluir() {
		venda.setProdutos(produtos);
		venda.setValorTotal(valorTotal(produtos));
		venda.setCliente(cr.recuperar(cliCodigo));
		venda.setAtendente(ar.recuperar(ateCodigo));
		venda.setBanco(br.recuperar(banCodigo));
		vr.adicionar(venda);
		return "vendaListagem";
	}
	public String editar(Venda venda) {
		this.venda=venda;
		produtos = venda.getProdutos();
		cliCodigo = venda.getCliente().getCodigo();
		System.out.println(venda.getCliente().getCodigo());
		ateCodigo = venda.getAtendente().getCodigo();
		banCodigo = venda.getBanco().getCodigo();
		return "vendaEdicao";
	}
	public String novo() {
		venda = new Venda();
		return "vendaInclusao";
	}
	public String alterar() {
		vr.atualizar(venda);
		return "vendaListagem";
	}
	public String remover() {
		vr.remover(venda);
		return "vendaListagem";
	}
	public String excluir(Venda venda) {
		this.venda=venda;
		produtos = venda.getProdutos();
		cliCodigo = venda.getCliente().getCodigo();
		ateCodigo = venda.getAtendente().getCodigo();
		return "vendaExclusao";
	}


	public String adiciona(){
		produto = pr.recuperar(proCodigo);
		produtos.add(produto);
		int tot = produto.getQntDisponivel();
		produto.setQntDisponivel(tot-1);
		pr.atualizar(produto);
		System.out.println("Tem "+produto.getQntDisponivel()+" Sofás");
		return "#";
	}

	public float valorTotal(List <Produto> produtos){
		if(produtos.isEmpty() || produtos==null)
			return 0;
		float valor=0;
		for(int i=0;i<produtos.size();i++){
			valor+=produtos.get(i).getValorUnitario();
		}

		return valor;
	}

	public String relatorio(Venda v){ 
		String nome = UUID.randomUUID().toString();
		produtos = v.getProdutos(); 
		Document document = new Document(); 
		try { 
			PdfWriter.getInstance(document, new FileOutputStream(nome+".pdf")); 
			} catch (FileNotFoundException | DocumentException e) 
		{ e.printStackTrace(); } 
			document.open();
			PdfPTable table = new PdfPTable(8);
		try { 
			document.add(new Paragraph("CLIENTE:  "+v.getCliente().getNome()));
			document.add(new Paragraph("VENDEDOR:  "+v.getAtendente().getNome()));
			document.add(new Paragraph("CÓDIGO  PRODUTO VALOR ")); 
			for(Produto p: produtos) { 
				document.add(new Paragraph("     "+p.getCodigo()+"     |     "+p.getDescricao()+"     |      "+p.getValorUnitario())); 
				}
			document.add(new Paragraph("VALOR TOTAL: R$"+v.getValorTotal()));
			document.add(new Paragraph("FORMA DE PAGAMENTO: "+v.getFormaPagamento()));
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