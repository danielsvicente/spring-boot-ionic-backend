package com.danielsv.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.danielsv.cursomc.domain.Categoria;
import com.danielsv.cursomc.domain.Cidade;
import com.danielsv.cursomc.domain.Cliente;
import com.danielsv.cursomc.domain.Endereco;
import com.danielsv.cursomc.domain.Estado;
import com.danielsv.cursomc.domain.ItemPedido;
import com.danielsv.cursomc.domain.Pagamento;
import com.danielsv.cursomc.domain.PagamentoComBoleto;
import com.danielsv.cursomc.domain.PagamentoComCartao;
import com.danielsv.cursomc.domain.Pedido;
import com.danielsv.cursomc.domain.Produto;
import com.danielsv.cursomc.domain.enums.EstadoPagamento;
import com.danielsv.cursomc.domain.enums.TipoCliente;
import com.danielsv.cursomc.repositories.CategoriaRepository;
import com.danielsv.cursomc.repositories.CidadeRepository;
import com.danielsv.cursomc.repositories.ClienteRepository;
import com.danielsv.cursomc.repositories.EnderecoRepository;
import com.danielsv.cursomc.repositories.EstadoRepository;
import com.danielsv.cursomc.repositories.ItemPedidoRepository;
import com.danielsv.cursomc.repositories.PagamentoRepository;
import com.danielsv.cursomc.repositories.PedidoRepository;
import com.danielsv.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		Categoria cat3 = new Categoria(null, "Celulares");
		Categoria cat4 = new Categoria(null, "Games");
		Categoria cat5 = new Categoria(null, "TV e Áudio");
		Categoria cat6 = new Categoria(null, "Moda, Beleza e Perfumaria");
		Categoria cat7 = new Categoria(null, "Livros");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaRepository.save(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
		produtoRepository.save(Arrays.asList(p1,p2,p3));
		
		Estado est1 = new Estado(null, "Pernambuco");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade cid1 = new Cidade(null, "Belo Jardim", est1);
		Cidade cid2 = new Cidade(null, "São Paulo", est2);
		Cidade cid3 = new Cidade(null, "Praia Grande", est2);
		
		est1.getCidades().addAll(Arrays.asList(cid1));
		est1.getCidades().addAll(Arrays.asList(cid2, cid3));
				
		estadoRepository.save(Arrays.asList(est1, est2));
		cidadeRepository.save(Arrays.asList(cid1, cid2, cid3));
		
		Cliente cli1 = new Cliente(null, "Daniel Vicente", "daniel_vs@mail.com", "39164386899", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("353899749253", "551189566651"));
		
		Endereco end1 = new Endereco(null, "Rua Juiz de Fora", "878", "", "Vila Ema", "03286000", cli1, cid2);
		Endereco end2 = new Endereco(null, "Rua Flores", "300", "Casa dos fundos", "Jardim", "38220834", cli1, cid1);
		
		cli1.getEnderecos().addAll(Arrays.asList(end1, end2));
		
		clienteRepository.save(Arrays.asList(cli1));
		enderecoRepository.save(Arrays.asList(end1, end2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, end1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 20:45"), cli1, end2);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		pedidoRepository.save(Arrays.asList(ped1, ped2));
		pagamentoRepository.save(Arrays.asList(pagto1, pagto2));
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		itemPedidoRepository.save(Arrays.asList(ip1, ip2, ip3));
		
	}
}
