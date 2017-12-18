package com.danielsv.cursomc;

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
import com.danielsv.cursomc.domain.Produto;
import com.danielsv.cursomc.domain.enums.TipoCliente;
import com.danielsv.cursomc.repositories.CategoriaRepository;
import com.danielsv.cursomc.repositories.CidadeRepository;
import com.danielsv.cursomc.repositories.ClienteRepository;
import com.danielsv.cursomc.repositories.EnderecoRepository;
import com.danielsv.cursomc.repositories.EstadoRepository;
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
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaRepository.save(Arrays.asList(cat1, cat2));
		produtoRepository.save(Arrays.asList(p1,p2,p3));
		
		Estado est1 = new Estado(null, "Pernamuco");
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
		
	}
}
