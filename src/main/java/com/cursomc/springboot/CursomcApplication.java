package com.cursomc.springboot;

import static com.cursomc.springboot.domain.enums.EstadoPagamento.PENDENTE;
import static com.cursomc.springboot.domain.enums.EstadoPagamento.QUITADO;
import static com.cursomc.springboot.domain.enums.TipoCliente.PESSOA_FISICA;
import static java.util.Arrays.asList;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.cursomc.springboot.domain.Categoria;
import com.cursomc.springboot.domain.Cidade;
import com.cursomc.springboot.domain.Cliente;
import com.cursomc.springboot.domain.Endereco;
import com.cursomc.springboot.domain.Estado;
import com.cursomc.springboot.domain.ItemPedido;
import com.cursomc.springboot.domain.Pagamento;
import com.cursomc.springboot.domain.PagamentoComBoleto;
import com.cursomc.springboot.domain.PagamentoComCartao;
import com.cursomc.springboot.domain.Pedido;
import com.cursomc.springboot.domain.Produto;
import com.cursomc.springboot.repositories.CategoriaRepository;
import com.cursomc.springboot.repositories.CidadeRepository;
import com.cursomc.springboot.repositories.ClienteRepository;
import com.cursomc.springboot.repositories.EnderecoRepository;
import com.cursomc.springboot.repositories.EstadoRepository;
import com.cursomc.springboot.repositories.ItemPedidoRepository;
import com.cursomc.springboot.repositories.PagamentoRepository;
import com.cursomc.springboot.repositories.PedidoRepository;
import com.cursomc.springboot.repositories.ProdutoRepository;

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
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria("Informatica", null);
		Categoria cat2 = new Categoria("Escritorio", null);
		Categoria cat3 = new Categoria("Cama mesa e banho", null);
		Categoria cat4 = new Categoria("Eletrônicos", null);
		Categoria cat5 = new Categoria("Jardinagens", null);
		Categoria cat6 = new Categoria("Dexoração", null);
		Categoria cat7 = new Categoria("Perfumaria", null);
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);

		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));

		categoriaRepository.saveAll(asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
		produtoRepository.saveAll(asList(p1, p2, p3));

		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");

		Cidade c1 = new Cidade(null, "Uberlandia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);

		est1.getCidades().addAll(asList(c1));
		est2.getCidades().addAll(asList(c2, c3));

		estadoRepository.saveAll(asList(est1, est2));
		cidadeRepository.saveAll(asList(c1, c2, c3));

		Cliente cli1 = new Cliente(null, "Maria", "maria@gmail.com", "12345678900", PESSOA_FISICA);
		cli1.getTelefones().addAll(asList("98525142", "236987414"));

		Endereco e1 = new Endereco(null, "Rua flores", "300", "apt 303", "jardim", "38254578", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "3877012", cli1, c2);

		cli1.getEnderecos().addAll(asList(e1, e2));

		clienteRepository.saveAll(asList(cli1));
		enderecoRepository.saveAll(asList(e1, e2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Pedido ped1 = new Pedido(null, sdf.parse("30/10/2017 10:20"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("20/05/2020 15:30"), cli1, e2);
		
		Pagamento pgto1 = new PagamentoComCartao(null, QUITADO, ped1, 6);
		ped1.setPagamento(pgto1);
		
		Pagamento pgto2 = new PagamentoComBoleto(null, PENDENTE, ped2, sdf.parse("20/05/2017 12:30"), null);
		ped2.setPagamento(pgto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		pedidoRepository.saveAll(asList(ped1, ped2));
		pagamentoRepository.saveAll(asList(pgto1, pgto2));
		
		ItemPedido ip1= new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2= new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3= new ItemPedido(ped2, p2, 100.00, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1,ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p1.getItens().addAll(Arrays.asList(ip2));
		p1.getItens().addAll(Arrays.asList(ip3));
		
		itemPedidoRepository.saveAll(asList(ip1, ip2, ip3));
	}
}
