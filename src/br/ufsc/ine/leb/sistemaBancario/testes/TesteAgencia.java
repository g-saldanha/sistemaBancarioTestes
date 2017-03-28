package br.ufsc.ine.leb.sistemaBancario.testes;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;

import br.ufsc.ine.leb.sistemaBancario.Agencia;
import br.ufsc.ine.leb.sistemaBancario.Banco;
import br.ufsc.ine.leb.sistemaBancario.Conta;
import br.ufsc.ine.leb.sistemaBancario.Dinheiro;
import br.ufsc.ine.leb.sistemaBancario.Entrada;
import br.ufsc.ine.leb.sistemaBancario.EstadosDeOperacao;
import br.ufsc.ine.leb.sistemaBancario.Moeda;
import br.ufsc.ine.leb.sistemaBancario.Operacao;
import br.ufsc.ine.leb.sistemaBancario.Saida;
import br.ufsc.ine.leb.sistemaBancario.SistemaBancario;
import br.ufsc.ine.leb.sistemaBancario.Transacao;
import br.ufsc.ine.leb.sistemaBancario.ValorMonetario;

public class TesteAgencia {
	
	private SistemaBancario sistemaBancario;
	private Banco bb;
	private Agencia centro;
	private String bancoDefault = "Banco do Brasil";
	private Conta contaMaria;

	public void criarSitemaBancario() {
		sistemaBancario = new SistemaBancario();
	}
	
	public void criarBanco() {
		bb = sistemaBancario.criarBanco("Banco do Brasil", Moeda.BRL);
	}
	
	public void criarAgenciaCentro() {
		centro = bb.criarAgencia("Centro");
	}
	
	public void criarContaMAria() {
		contaMaria = centro.criarConta("Maria");
	}

	@Test
	public void caixaEconomicaTrindade() throws Exception {
		criarSitemaBancario();
		Banco caixaEconomica = sistemaBancario.criarBanco("Caixa Econômica", Moeda.BRL);
		Agencia caixaEconomicaTrindade = caixaEconomica.criarAgencia("Trindade");
		assertEquals("001", caixaEconomicaTrindade.obterIdentificador());
		assertEquals("Trindade", caixaEconomicaTrindade.obterNome());
		assertEquals(caixaEconomica, caixaEconomicaTrindade.obterBanco());
	}
	
	@Test
	public void casoDeTeste1() throws Exception {
		criarSitemaBancario();
		criarBanco();
		assertEquals(bancoDefault, bb.obterNome());
		assertEquals(Moeda.BRL, bb.obterMoeda());
	}
	
	@Test
	public void casoDeTeste2() throws Exception {
		criarSitemaBancario();
		criarBanco();
		criarAgenciaCentro();
		assertEquals("001", centro.obterIdentificador());
		assertEquals("Centro", centro.obterNome());
		assertEquals(bb, centro.obterBanco());
	}
	
	@Test
	public void casoDeTeste3() throws Exception {
		criarSitemaBancario();
		criarBanco();
		criarAgenciaCentro();
		criarContaMAria();
		assertEquals("0001-5", contaMaria.obterIdentificador());
		assertEquals("Maria", contaMaria.obterTitular());
		assertEquals(new Dinheiro(Moeda.BRL, 0, 0).positivo(), contaMaria.calcularSaldo());
		
	}
	
	@Ignore
	@Test
	public void casoDeTeste4() throws Exception {
		criarSitemaBancario();
		criarBanco();
		criarAgenciaCentro();
		criarContaMAria();
		Dinheiro depositoDezReais = new Dinheiro(Moeda.BRL, 10, 0);
		contaMaria.adicionarTransacao(new Entrada(contaMaria, depositoDezReais)); 
		//assertEquals(EstadosDeOperacao.SUCESSO, c );
		assertEquals(depositoDezReais.positivo(), contaMaria.calcularSaldo());
	}
	
	@Test
	public void casoDeTeste5() throws Exception {
		criarSitemaBancario();
		criarBanco();
		criarAgenciaCentro();
		criarContaMAria();
		Dinheiro depositoDezReais = new Dinheiro(Moeda.BRL, 10, 0);
		contaMaria.adicionarTransacao(new Entrada(contaMaria, depositoDezReais));
		Dinheiro saqueSeisReais = new Dinheiro(Moeda.BRL, 6, 0);
		Dinheiro saldoQuatroReais = new Dinheiro(Moeda.BRL, 4, 0);
		contaMaria.adicionarTransacao(new Saida(contaMaria, saqueSeisReais));
		assertEquals(saldoQuatroReais.positivo(), contaMaria.calcularSaldo());
		
		
	}
	
	@Test
	public void casoDeTeste6() throws Exception {
		criarSitemaBancario();
		criarBanco();
		criarAgenciaCentro();
		criarContaMAria();
		
	}
	


}
