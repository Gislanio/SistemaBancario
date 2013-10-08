package facade;

import java.util.ArrayList;
import java.util.List;

import negocio.Cliente;
import negocio.Conta;
import negocio.ContaCorrenteEspecial;
import negocio.ContaPoupanca;
import negocio.TipoOperacao;
import negocio.Transacao;

import Gerenciadores.GerenteCliente;
import Gerenciadores.GerenteDeContas;
import Gerenciadores.GerenteDeTransacao;


public class BancoFachada {
	
	GerenteDeTransacao g_transacao = new GerenteDeTransacao();
	GerenteCliente g_cliente = new GerenteCliente();
	GerenteDeContas g_conta = new GerenteDeContas();
	
	//Operações com Cliente
	public void adicionarCliente (Cliente c){
		try {
			g_cliente.addCliente(c);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void removerCliente(Cliente c){
		g_cliente.removeCliente(c);
	}
	public Cliente buscarCliente(String cpf){
		return g_cliente.searchCliente(cpf);
	}
	public Cliente recuperarCliente(Cliente c){
		return g_cliente.recuperarCliente(c);
	}
	public List<Cliente> recuperarAllClientes(){
		return g_cliente.recuperarAllCliente();
	}
	
	//Operações com Contas
	
	public Conta buscarConta(int num){
		return g_conta.buscarConta(num);
	}
	public Conta recuperarConta(Conta c){
		return g_conta.recuperaConta(c);
	}
	
	public void criarConta(Conta conta){
		g_conta.criarConta(conta);
	}
	
	public void desativarConta(Conta conta){
		g_conta.desativarConta(conta);
	}
	
	public void depositar(Conta conta, double valor){
		g_conta.depositar(conta, valor);
	}
	
	public void sacar(Conta conta, double valor){
		g_conta.sacar(conta, valor);
	}
	
	public double calcularRendimentos(ContaPoupanca c, double taxa, String data){
		return g_conta.calcularRendimentos(c, taxa, data);
	}

	public void removerConta(Conta conta){
		g_conta.removerConta(conta);
	}
	
	public double calcularRendimentoEmMeses(ContaPoupanca c, double taxa, int meses){
		return g_conta.calcularRendimentoEmMeses(c, taxa, meses);
	}
	
	public void sacarContaEspecial(ContaCorrenteEspecial conta, double valor){
		g_conta.sacarContaEspecial(conta, valor);
	}
	
	public void depositarContaEspecial(ContaCorrenteEspecial conta, double valor){
		g_conta.depositarContaEspecial(conta, valor);
	}
	//Operações com Transações
	
	public void tranferir( Conta contaOrig, double valor, Conta contaDest){
		g_conta.transferirSaldo(contaOrig, valor, contaDest);
	}	
	
	public void criarTransacao(Transacao t){
		g_transacao.addTransacao(t);
	}
	public boolean contemTransacao(Conta c, TipoOperacao to){
		return g_transacao.contemTransacao(c, to);
	}
	
	public List <Transacao> getList(){
		return g_transacao.getTransacoes();
	}
	
	public List<Transacao> recuperarTransacoes(){
		return g_transacao.recuperarTransacoes();
	}
	
	public ArrayList<Transacao> extrato(Conta conta){
		return g_transacao.extrato(conta);
	}
	
	public List<Transacao> verTransacoesPorConta(Conta conta){
		return g_transacao.verTransacoesPorConta(conta);
	}
	
	public List<Transacao> retornaTransacoesConta(Conta conta){
		return g_transacao.verTransacoesPorConta(conta);
	}
	
	
}
