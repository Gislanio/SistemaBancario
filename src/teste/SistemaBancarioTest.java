package teste;

import junit.framework.Assert;
import negocio.Cliente;
import negocio.Conta;
import negocio.ContaCorrenteEspecial;
import negocio.ContaPoupanca;
import negocio.TipoOperacao;
import negocio.Transacao;

import org.junit.Before;
import org.junit.Test;

import excecao.ClienteException;
import excecao.ContaException;
import facade.BancoFachada;


public class SistemaBancarioTest {

	BancoFachada banco;

	@Before
	public void iniciar(){
		banco= new BancoFachada();
	}

	@Test
	public void criarCliente(){
		Cliente c = new Cliente("Ana Maria", "00900800709", 7654908, "Rua: Antonio Silveira - MS/PA");
		banco.adicionarCliente(c);
		Cliente c1 = new Cliente("Joao Maria", "00123800709", 7655608, "Rua: Antonio Silveira - MS/PA");
		banco.adicionarCliente(c1);
		Cliente c2 = new Cliente("Clara Maria", "00987630709", 7344908, "Rua: Antonio Silveira - MS/PA");
		banco.adicionarCliente(c2);

		Assert.assertNotNull(banco.buscarCliente(c.getCpf()));
		Assert.assertNotNull(banco.buscarCliente(c1.getCpf()));
		Assert.assertNotNull(banco.buscarCliente(c2.getCpf()));	
	}

	@Test
	public void criarConta(){
		Cliente c = new Cliente("Ana Maria", "00900800709", 7654908, "Rua: Antonio Silveira - MS/PA");
		banco.adicionarCliente(c);
		Conta conta = new Conta (c,9876,98765);
		banco.criarConta(conta);

		Assert.assertNotNull(banco.buscarConta(98765));

	}

	@Test
	public void criarTransacao(){
		Cliente c = new Cliente("Ana Maria", "00900800709", 7654908, "Rua: Antonio Silveira - MS/PA");
		banco.adicionarCliente(c);
		Conta conta = new Conta (c,9876,98765);
		banco.criarConta(conta);
		Transacao t = new Transacao(conta, TipoOperacao.ABERTURA);
		banco.criarTransacao(t);

		Assert.assertTrue(banco.contemTransacao(conta, TipoOperacao.ABERTURA));
	}

	@Test
	public void RecuperarDadosCliente(){
		Cliente c = new Cliente("Ana Maria", "00900800709", 7654908, "Rua: Antonio Silveira - MS/PA");
		banco.adicionarCliente(c);

		Assert.assertEquals(c.getCpf(), banco.recuperarCliente(c).getCpf());

	}

	@Test
	public void recuperarConta(){
		Cliente c = new Cliente("Ana Maria", "00900800709", 7654908, "Rua: Antonio Silveira - MS/PA");
		banco.adicionarCliente(c);
		Conta conta = new Conta (c,9876,98765);
		banco.criarConta(conta);

		Assert.assertEquals(banco.recuperarConta(conta).getNumero(), conta.getNumero());
	}

	public void recuperarTransacoes(){

		Cliente c = new Cliente("Ana Maria", "00900800709", 7654908, "Rua: Antonio Silveira - MS/PA");
		banco.adicionarCliente(c);
		Conta conta = new Conta (c,9876,98765);
		banco.criarConta(conta);
		Transacao t = new Transacao(conta, TipoOperacao.ABERTURA);
		banco.criarTransacao(t);

		Assert.assertTrue(banco.contemTransacao(conta, TipoOperacao.ABERTURA));
	}

	@Test
	public void verTransacoesDaConta(){
		Cliente c = new Cliente("Ana Maria", "00900800709", 7654908, "Rua: Antonio Silveira - MS/PA");
		banco.adicionarCliente(c);
		Conta conta = new Conta (c,9876,98765);
		banco.criarConta(conta);
		Transacao t = new Transacao(conta, TipoOperacao.ABERTURA);
		banco.criarTransacao(t);

		banco.depositar(conta, 100.00);
		t.setOperacao(TipoOperacao.DEPOSITO);
		banco.criarTransacao(t);

		Assert.assertNotNull(banco.verTransacoesPorConta(conta));
		Assert.assertTrue(banco.contemTransacao(conta, TipoOperacao.DEPOSITO));

	}

	@Test(expected = ContaException.class)
	public void addContaMesmoCliente(){

		Cliente c = new Cliente("Ana Maria", "00900800709", 7654908, "Rua: Antonio Silveira - MS/PA");
		banco.adicionarCliente(c);
		Conta conta = new Conta (c,9876,98765);
		banco.criarConta(conta);
		Transacao t = new Transacao(conta, TipoOperacao.ABERTURA);
		banco.criarTransacao(t);

		c = new Cliente("Ana Maria", "00900800709", 7654908, "Rua: Antonio Silveira - MS/PA");
		banco.adicionarCliente(c);
		conta = new Conta (c,9546,99965);
		banco.criarConta(conta);
		t = new Transacao(conta, TipoOperacao.ABERTURA);
		banco.criarTransacao(t);

	}

	@Test
	public void sacar(){
		Cliente c = new Cliente("Ana Maria", "00900800709", 7654908, "Rua: Antonio Silveira - MS/PA");
		banco.adicionarCliente(c);
		Conta conta = new Conta (c,9876,98765);
		banco.criarConta(conta);
		Transacao t = new Transacao(conta, TipoOperacao.ABERTURA);
		banco.criarTransacao(t);

		banco.depositar(conta, 300.00);
		t.setOperacao(TipoOperacao.DEPOSITO);
		banco.criarTransacao(t);
		banco.sacar(conta, 200.00);
		t.setOperacao(TipoOperacao.SAQUE);
		banco.criarTransacao(t);

		Assert.assertTrue(banco.contemTransacao(conta, TipoOperacao.SAQUE));
		Assert.assertEquals(100.00, banco.buscarConta(98765).getSaldo());
	}

	@Test
	public void depositar(){
		Cliente c = new Cliente("Ana Maria", "00900800709", 7654908, "Rua: Antonio Silveira - MS/PA");
		banco.adicionarCliente(c);
		Conta conta = new Conta (c,9876,98765);
		banco.criarConta(conta);
		Transacao t = new Transacao(conta, TipoOperacao.ABERTURA);
		banco.criarTransacao(t);

		banco.depositar(conta, 300.00);
		t.setOperacao(TipoOperacao.DEPOSITO);
		banco.criarTransacao(t);

		Assert.assertTrue(banco.contemTransacao(conta, TipoOperacao.DEPOSITO));
		Assert.assertEquals(300.00, banco.buscarConta(98765).getSaldo());
	}

	@Test
	public void transferencia(){
		Cliente c = new Cliente("Ana Maria", "00900800709", 7654908, "Rua: Antonio Silveira - MS/PA");
		banco.adicionarCliente(c);
		Conta contaOrigem = new Conta (c,9876,98765);
		banco.criarConta(contaOrigem);
		Transacao t = new Transacao(contaOrigem, TipoOperacao.ABERTURA);
		banco.criarTransacao(t);

		banco.depositar(contaOrigem, 100.00);
		t = new Transacao(contaOrigem, TipoOperacao.DEPOSITO);
		banco.criarTransacao(t);


		Cliente c1 = new Cliente("Antonio Soares", "87253800709", 9874908, "Rua: Antonio Silveira - MS/PA");
		banco.adicionarCliente(c1);
		Conta contaDest = new Conta (c1,8765,12365);
		banco.criarConta(contaDest);
		Transacao t1 = new Transacao(contaDest, TipoOperacao.ABERTURA);
		banco.criarTransacao(t1);
		banco.tranferir(contaOrigem, 100.00, contaDest);
		t = new Transacao(contaOrigem, TipoOperacao.SAQUE);
		banco.criarTransacao(t);
		t = new Transacao(contaDest, TipoOperacao.DEPOSITO);
		banco.criarTransacao(t);


		Assert.assertEquals(0.0, contaOrigem.getSaldo());

	}

	@Test(expected = ContaException.class)
	public void depositarNegativo(){
		Cliente c = new Cliente("Ana Maria", "00900800709", 7654908, "Rua: Antonio Silveira - MS/PA");
		banco.adicionarCliente(c);
		Conta conta = new Conta (c,9876,98765);
		banco.criarConta(conta);
		Transacao t = new Transacao(conta, TipoOperacao.ABERTURA);
		banco.criarTransacao(t);

		banco.depositar(conta, -100.00);
		t = new Transacao(conta, TipoOperacao.DEPOSITO);
		banco.criarTransacao(t);

	}

	@Test(expected = ContaException.class)
	public void SacarValorMaiorqueSaldo(){
		Cliente c = new Cliente("Ana Maria", "00900800709", 7654908, "Rua: Antonio Silveira - MS/PA");
		banco.adicionarCliente(c);
		Conta conta = new Conta (c,9876,98765);
		banco.criarConta(conta);
		Transacao t = new Transacao(conta, TipoOperacao.ABERTURA);
		banco.criarTransacao(t);

		banco.depositar(conta, 100.00);
		t = new Transacao(conta, TipoOperacao.DEPOSITO);
		banco.criarTransacao(t);
		banco.sacar(conta, 200.00);
		t = new Transacao(conta, TipoOperacao.SAQUE);
		banco.criarTransacao(t);


	}

	@Test
	public void CriarContaEspecial(){
		Cliente c = new Cliente("Ana Maria", "00900800709", 7654908, "Rua: Antonio Silveira - MS/PA");
		banco.adicionarCliente(c);
		Conta conta = new ContaCorrenteEspecial (c,9876,98765, 100);
		banco.criarConta(conta);
		Transacao t = new Transacao(conta, TipoOperacao.ABERTURA);
		banco.criarTransacao(t);

		Assert.assertTrue(banco.contemTransacao(conta, TipoOperacao.ABERTURA));
		Assert.assertEquals(100.0, ((ContaCorrenteEspecial) banco.recuperarConta(conta)).getValorLimiteAtual());
	}

	@Test
	public void sacarContaEspecial(){
		Cliente c = new Cliente("Ana Maria", "00900800709", 7654908, "Rua: Antonio Silveira - MS/PA");
		banco.adicionarCliente(c);
		ContaCorrenteEspecial conta = new ContaCorrenteEspecial (c,9876,98765, 100);
		banco.criarConta(conta);
		Transacao t = new Transacao(conta, TipoOperacao.ABERTURA);
		banco.criarTransacao(t);
		banco.sacarContaEspecial(conta, 50.0);
		t = new Transacao(conta, TipoOperacao.SAQUE);
		banco.criarTransacao(t);

		Assert.assertEquals(50.0, ((ContaCorrenteEspecial) banco.recuperarConta(conta)).getValorLimiteAtual());

	}

	@Test
	public void depositarContaEspecial(){
		Cliente c = new Cliente("Ana Maria", "00900800709", 7654908, "Rua: Antonio Silveira - MS/PA");
		banco.adicionarCliente(c);
		ContaCorrenteEspecial conta = new ContaCorrenteEspecial (c,9876,98765, 100);
		banco.criarConta(conta);
		Transacao t = new Transacao(conta, TipoOperacao.ABERTURA);
		banco.criarTransacao(t);
		banco.sacarContaEspecial(conta, 50.0);
		t = new Transacao(conta, TipoOperacao.SAQUE);
		banco.criarTransacao(t);
		banco.depositarContaEspecial(conta, 100);
		
		Assert.assertEquals(100.0, ((ContaCorrenteEspecial) banco.recuperarConta(conta)).getValorLimiteAtual());
		Assert.assertEquals(50.0, ((ContaCorrenteEspecial) banco.recuperarConta(conta)).getSaldo());
	}


	@Test(expected = ContaException.class)
	public void transferenciaErro(){
		Cliente c = new Cliente("Ana Maria", "00900800709", 7654908, "Rua: Antonio Silveira - MS/PA");
		banco.adicionarCliente(c);
		Conta contaOrigem = new Conta (c,9876,98765);
		banco.criarConta(contaOrigem);
		Transacao t = new Transacao(contaOrigem, TipoOperacao.ABERTURA);
		banco.criarTransacao(t);

		banco.depositar(contaOrigem, 100.00);
		t.setOperacao(TipoOperacao.DEPOSITO);

		Cliente c1 = new Cliente("Antonio Soares", "87253800709", 9874908, "Rua: Antonio Silveira - MS/PA");
		banco.adicionarCliente(c1);
		Conta contaDest = new Conta (c1,8765,12365);
		banco.criarConta(contaDest);
		Transacao t1 = new Transacao(contaDest, TipoOperacao.ABERTURA);
		banco.criarTransacao(t1);
		banco.tranferir(contaOrigem, 200.00, contaDest);

	}

	@Test
	public void desativarConta(){
		Cliente c = new Cliente("Ana Maria", "00900800709", 7654908, "Rua: Antonio Silveira - MS/PA");
		banco.adicionarCliente(c);
		Conta conta = new Conta (c,9876,98765);
		banco.criarConta(conta);
		Transacao t = new Transacao(conta, TipoOperacao.ABERTURA);
		banco.criarTransacao(t);
		banco.desativarConta(conta);
		Assert.assertEquals(false, banco.recuperarConta(conta).isAtiva());
	}

	@Test (expected = ContaException.class)
	public void sacarContaDesativada(){
		Cliente c = new Cliente("Ana Maria", "00900800709", 7654908, "Rua: Antonio Silveira - MS/PA");
		banco.adicionarCliente(c);
		Conta conta = new Conta (c,9876,98765);
		banco.criarConta(conta);
		Transacao t = new Transacao(conta, TipoOperacao.ABERTURA);
		banco.criarTransacao(t);
		banco.desativarConta(conta);
		banco.sacar(conta, 50.00);
	}

	@Test (expected = ContaException.class)
	public void depositarContaDesativada(){
		Cliente c = new Cliente("Ana Maria", "00900800709", 7654908, "Rua: Antonio Silveira - MS/PA");
		banco.adicionarCliente(c);
		Conta conta = new Conta (c,9876,98765);
		banco.criarConta(conta);
		Transacao t = new Transacao(conta, TipoOperacao.ABERTURA);
		banco.criarTransacao(t);
		banco.desativarConta(conta);
		banco.depositar(conta, 50.00);

	}

	public void criarContaPoupanca(){
		Cliente c = new Cliente("Ana Maria", "00900800709", 7654908, "Rua: Antonio Silveira - MS/PA");
		banco.adicionarCliente(c);
		Conta conta = new ContaPoupanca (c,9876,98765, 100);
		banco.criarConta(conta);
		Transacao t = new Transacao(conta, TipoOperacao.ABERTURA);
		banco.criarTransacao(t);

		Assert.assertTrue(banco.contemTransacao(conta, TipoOperacao.ABERTURA));
	}

	@Test
	public void calcularRendimentos(){
		Cliente c = new Cliente("Ana Maria", "00900800709", 7654908, "Rua: Antonio Silveira - MS/PA");
		banco.adicionarCliente(c);
		ContaPoupanca conta = new ContaPoupanca (c,9876,98765,1000.0);
		banco.criarConta(conta);
		Transacao t = new Transacao(conta, TipoOperacao.ABERTURA);
		banco.criarTransacao(t);
		banco.calcularRendimentos(conta, 0.617, "15/11/2013");

		Assert.assertEquals(1006.17,banco.recuperarConta(conta).getSaldo());

	}
	@Test
	public void removerCliente(){
		Cliente c = new Cliente("Ana Maria", "00900800709", 7654908, "Rua: Antonio Silveira - MS/PA");
		banco.adicionarCliente(c);
		ContaPoupanca conta = new ContaPoupanca (c,9876,98765,100.0);
		banco.criarConta(conta);
		Transacao t = new Transacao(conta, TipoOperacao.ABERTURA);
		banco.criarTransacao(t);
		banco.removerCliente(c);
		Assert.assertNull(banco.recuperarCliente(c));
		//Assert.assertNull(banco.recuperarConta(conta));
	}

	@Test(expected = ClienteException.class)
	public void removerClienteNaoExistente(){
		Cliente c = new Cliente("Ana Maria", "00900800709", 7654908, "Rua: Antonio Silveira - MS/PA");
		//banco.adicionarCliente(c);
		ContaPoupanca conta = new ContaPoupanca (c,9876,98765,100.0);
		banco.criarConta(conta);
		Transacao t = new Transacao(conta, TipoOperacao.ABERTURA);
		banco.criarTransacao(t);
		banco.removerCliente(c);

	}
	@Test
	public void removerConta(){
		Cliente c = new Cliente("Ana Maria", "00900800709", 7654908, "Rua: Antonio Silveira - MS/PA");
		banco.adicionarCliente(c);
		ContaPoupanca conta = new ContaPoupanca (c,9876,98765,100.0);
		banco.criarConta(conta);
		Transacao t = new Transacao(conta, TipoOperacao.ABERTURA);
		banco.criarTransacao(t);
		banco.removerConta(conta);
		t = new Transacao(conta, TipoOperacao.EXCLUIR);
		banco.criarTransacao(t);

		Assert.assertNull(banco.recuperarConta(conta));
	}

	@Test(expected = ContaException.class)
	public void removerContaNaoExistente(){
		Cliente c = new Cliente("Ana Maria", "00900800709", 7654908, "Rua: Antonio Silveira - MS/PA");
		banco.adicionarCliente(c);
		ContaPoupanca conta = new ContaPoupanca (c,9876,98765,100.0);
		//banco.criarConta(conta);
		Transacao t = new Transacao(conta, TipoOperacao.ABERTURA);
		banco.criarTransacao(t);
		banco.removerConta(conta);

		Assert.assertNull(banco.recuperarConta(conta));
	}



	@Test 
	public void calcularRendimentos6MesesDepois(){
		Cliente c = new Cliente("Ana Maria", "00900800709", 7654908, "Rua: Antonio Silveira - MS/PA");
		banco.adicionarCliente(c);
		ContaPoupanca conta = new ContaPoupanca (c,9876,98765,100.0);
		banco.criarConta(conta);
		Transacao t = new Transacao(conta, TipoOperacao.ABERTURA);
		banco.criarTransacao(t);


		Assert.assertEquals(103.702, banco.calcularRendimentoEmMeses(conta, 0.617, 6 ));

	}

	@Test (expected = ContaException.class)
	public void calcularRendimentosErro(){
		Cliente c = new Cliente("Ana Maria", "00900800709", 7654908, "Rua: Antonio Silveira - MS/PA");
		banco.adicionarCliente(c);
		ContaPoupanca conta = new ContaPoupanca (c,9876,98765,100.0);
		banco.criarConta(conta);
		Transacao t = new Transacao(conta, TipoOperacao.ABERTURA);
		banco.criarTransacao(t);

		banco.calcularRendimentos(conta, 0.617, "07/10/2013");

	}

	public void testarPersistencia(){
		
	}






}
