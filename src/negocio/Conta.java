package negocio;

import java.io.Serializable;
import java.util.Date;


public class Conta implements Serializable {
	
	private Cliente cliente;
	private int senha;
	private boolean ativa;
	private int numero;
	private Date dataAbertura;
	protected double saldo;
	//private TipoOperacao tipoOp;
	
	
	public Conta(Cliente cliente, int senha, int numero){
		this.cliente = cliente;
		this.senha = senha;
		this.numero = numero;
		dataAbertura = new Date();
		saldo = 0.0;
		ativa= true;
	//	tipoOp= tipoOp.ABERTURA;
	}
	public Conta (Cliente cliente, int senha, int numero, double saldo){
		this.cliente = cliente;
		this.senha = senha;
		this.numero = numero;
		dataAbertura = new Date();
		this.saldo = saldo;
		ativa= true;
	//	tipoOp= tipoOp.ABERTURA;
	}
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public int getSenha() {
		return senha;
	}
	public void setSenha(int senha) {
		this.senha = senha;
	}
	public boolean isAtiva() {
		return ativa;
	}
	public void setAtiva(boolean ativa) {
		this.ativa = ativa;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public Date getDataAbertura() {
		return dataAbertura;
	}
	public void setDataAbertura(Date dataAbertura) {
		this.dataAbertura = dataAbertura;
	}
	public double getSaldo() {
		return saldo;
	}
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	

	
	

}
