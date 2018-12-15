package br.ufac.academico.controller;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.*;
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
	

	
}