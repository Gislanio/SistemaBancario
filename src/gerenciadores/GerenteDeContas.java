package gerenciadores;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import negocio.Conta;
import excecao.ContaException;
import persistencia.ContaPersist;
import negocio.ContaCorrenteEspecial;
import negocio.ContaPoupanca;



public class GerenteDeContas {
	Conta conta;
	ArrayList<Conta> contas = new ArrayList<Conta>();
	ContaPersist contaFile = new ContaPersist();
	ArrayList<Conta> aux = new ArrayList<Conta>();
	GerenteCliente gC= new GerenteCliente();
	GerenteDeTransacao gT = new GerenteDeTransacao();
	public void criarConta(Conta conta){
		for(Conta i : contas){
			if(i.getCliente().getCpf().equals(conta.getCliente().getCpf())|| i.getNumero()==conta.getNumero()){
				throw new ContaException("Você utilizou dados inválidos.");
			}
		}
		contas.add(conta);	
		atualizarPersistencia();
	}

	private void atualizarPersistencia(){
		try {
			contaFile.gravarConta(contas);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	

	public void removerConta(Conta c){
		if(verificarConta(c.getNumero(), c.getSenha()) == false){
			throw new ContaException("Conta Não Localizada!");
		}
		contas.remove(c);
		atualizarPersistencia();

	}

	public void depositar(Conta conta, double valor){

		if(verificarConta(conta.getNumero(), conta.getSenha())== false || conta.isAtiva()==false){
			throw new ContaException(
					"Conta não existente ou desativada!!");
		}
		for(Conta i: contas){
			if(i.getSenha() == conta.getSenha()){
				if(valor<=0){
					throw new ContaException("Você inseriu dados negativos!");
				}
				i.setSaldo(i.getSaldo()+valor);
				atualizarPersistencia();
			}
		}
	}

	public void transferirSaldo(Conta contaOrigem, double valor, Conta contaDestino){
		if(verificarConta(contaOrigem.getNumero(), contaOrigem.getSenha())==false || 
				verificarConta(contaDestino.getNumero(), contaDestino.getSenha())==false|| valor <=0 ){
			throw new ContaException("Dados de contas inválidos! Verifique se a conta existe ou está desativada!");

		}
		sacar(contaOrigem, valor);
		depositar(contaDestino, valor);
		atualizarPersistencia();
	}

	public void sacar(Conta conta, double valor){
		if(conta instanceof ContaCorrenteEspecial){
			
		}
		if(verificarConta(conta.getNumero(), conta.getSenha()) == false || conta.isAtiva()==false || valor > conta.getSaldo()){
			throw new ContaException(
					"Algum dado inserido é invalido!");
		}

		
		for(Conta i: contas){
			if(i.getSenha() == conta.getSenha() ){
				i.setSaldo(i.getSaldo()-valor);
				atualizarPersistencia();
			}
		}
	}
	
	public void sacarContaEspecial(ContaCorrenteEspecial conta, double valor){
		if(verificarConta(conta.getNumero(), conta.getSenha()) == false  || valor > conta.getSaldo()+conta.getLimitePadrao() || valor <0){
			throw new ContaException(
					"Algum dado inserido é invalido!");
		}

		for(Conta i: contas){
			if(i.getSenha() == conta.getSenha()){
					
				while(valor>0){
					if(i.getSaldo() >0){
						i.setSaldo(i.getSaldo()-1);
						valor--;
						
					}
					if(i.getSaldo()==0 && ((ContaCorrenteEspecial) i).getValorLimiteAtual() >0){
						((ContaCorrenteEspecial) i).setValorLimiteAtual(((ContaCorrenteEspecial) i).getValorLimiteAtual()-1);
						valor--;
					}
					
					
				}
				
				atualizarPersistencia();
			}
		}

		
		for(Conta i: contas){
			if(i.getSenha() == conta.getSenha() || i instanceof ContaCorrenteEspecial){
				i.setSaldo(i.getSaldo()-valor);
				atualizarPersistencia();
			}
		}
	}

	
	public void depositarContaEspecial(ContaCorrenteEspecial conta, double valor){
		if(verificarConta(conta.getNumero(), conta.getSenha()) == false || valor <0 ){
			throw new ContaException(
					"Algum dado inserido é invalido!");
		}

		for(Conta i: contas){
			if(i.getSenha() == conta.getSenha()){
				ContaCorrenteEspecial j = (ContaCorrenteEspecial) i;
				while(valor>0){
					
					if(j.getValorLimiteAtual()!= j.getLimitePadrao()){
						j.setValorLimiteAtual(j.getValorLimiteAtual()+1);
						valor--;
						
					}
					if(j.getLimitePadrao()==j.getValorLimiteAtual()){
						j.setSaldo(j.getSaldo()+1);
						valor--;
					}
					
					
				}
				
				atualizarPersistencia();
			}
		}
	}
	public void desativarConta(Conta conta){

		for(Conta i: contas){
			if(i.getSenha() == conta.getSenha()){
				i.setAtiva(false);
				atualizarPersistencia();
			}
		}

	}

	public Conta buscarConta(int numero){

		for(Conta i : contas){
			if(i.getNumero()==numero){
				return i;
			}
		}
		throw new ContaException("Conta não localizada!");
	}

	private boolean verificarConta(int numero, int senha){
		for(Conta i: contas){
			if(i.getNumero()==numero && i.getSenha()==senha){
				return true;
			}
			if(i.isAtiva() == false){
				return false;
			}
		}
		return false;
	}

	public Conta recuperaConta(Conta c){
		aux = (ArrayList<Conta>) contaFile.recuperarObjeto();
		for(Conta i: aux){
			if(c.getNumero()==i.getNumero() && c.getSenha()==i.getSenha()){
				return i;
			}
		}
		return null;



	}


	public double calcularRendimentos(ContaPoupanca c, double taxa, String data){
		Date data1 = validaData(data);

		if((data1).before(c.getDataAbertura())){
			throw new ContaException("Data inválida! Anterior a abertura da conta!");
		}
		if(data1.getMonth()== c.getDataAbertura().getMonth()){
			throw new ContaException("Os rendimentos só podem ser retirados após um mês!");
		}
		int indice = Math.abs(c.getDataAbertura().getMonth() - data1.getMonth());
		for(int i = 0;i < indice; i++){
			double aplicarRendimento = ((c.getSaldo()*taxa)/100)+c.getSaldo();
			c.setSaldo(aplicarRendimento);
		}
		c.setDataAniversario(data);
		atualizarPersistencia();
		return c.getSaldo();

	}

	public double calcularRendimentoEmMeses(ContaPoupanca c, double taxa, int meses){
		double rendimento = meses*(((c.getSaldo()*taxa)/100))+c.getSaldo();
		return rendimento;
	}

	private Date validaData(String dataStr){
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setLenient(true);

		try {
			Date data = sdf.parse(dataStr);
			return data;
			// se passou pra cá, é porque a data é válida
		} catch(ParseException e) {
			// se cair aqui, a data é inválida
			System.err.println("Data inválida");
		}
		return null;

	}


	


}
