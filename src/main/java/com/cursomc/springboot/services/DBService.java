package com.cursomc.springboot.services;

import static com.cursomc.springboot.domain.enums.EstadoPagamento.PENDENTE;
import static com.cursomc.springboot.domain.enums.EstadoPagamento.QUITADO;
import static com.cursomc.springboot.domain.enums.TipoCliente.PESSOA_FISICA;
import static java.util.Arrays.asList;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

@Service
public class DBService {

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

	public void instantiateTestDataBase() throws ParseException {

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
		Produto p4 = new Produto(null, "Mesa de escritorio", 300.00);
		Produto p5 = new Produto(null, "Toalha", 50.00);
		Produto p6 = new Produto(null, "Colcha", 200.00);
		Produto p7 = new Produto(null, "Tv true color", 1200.00);
		Produto p8 = new Produto(null, "Roçadeira", 2000.00);
		Produto p9 = new Produto(null, "Abajour", 100.00);
		Produto p10 = new Produto(null, "Pendente", 180.00);
		Produto p11 = new Produto(null, "Shampoo", 2000.00);

		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2, p4));
		cat3.getProdutos().addAll(Arrays.asList(p5, p6));
		cat4.getProdutos().addAll(Arrays.asList(p1, p2, p3, p7));
		cat5.getProdutos().addAll(Arrays.asList(p8));
		cat6.getProdutos().addAll(Arrays.asList(p9, p10));
		cat7.getProdutos().addAll(Arrays.asList(p11));

		p1.getCategorias().addAll(Arrays.asList(cat1, cat4));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2, cat4));
		p3.getCategorias().addAll(Arrays.asList(cat1, cat4));
		p4.getCategorias().addAll(Arrays.asList(cat2));
		p5.getCategorias().addAll(Arrays.asList(cat3));
		p6.getCategorias().addAll(Arrays.asList(cat3));
		p7.getCategorias().addAll(Arrays.asList(cat4));
		p8.getCategorias().addAll(Arrays.asList(cat5));
		p9.getCategorias().addAll(Arrays.asList(cat6));
		p10.getCategorias().addAll(Arrays.asList(cat6));
		p11.getCategorias().addAll(Arrays.asList(cat7));

		categoriaRepository.saveAll(asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
		produtoRepository.saveAll(asList(p1, p2, p3));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));

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

		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);

		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));

		p1.getItens().addAll(Arrays.asList(ip1));
		p1.getItens().addAll(Arrays.asList(ip2));
		p1.getItens().addAll(Arrays.asList(ip3));

		itemPedidoRepository.saveAll(asList(ip1, ip2, ip3));
	}
}