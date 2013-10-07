package negocio;

import java.io.Serializable;
import java.util.Date;



public class Transacao implements Serializable {
	//private int id;
	private Conta conta;
	private Date data;
	private boolean finalizado = false;
	private TipoOperacao operacao;
	
	public Transacao(Conta conta, TipoOperacao tipoOp){
		this.conta= conta;
		operacao = tipoOp;
		data = new Date();
		
	}
	
	public Transacao() {
		// TODO Auto-generated constructor stub
	}

	public TipoOperacao getOperacao() {
		return operacao;
	}
	public void setOperacao(TipoOperacao operacao) {
		this.operacao = operacao;
	}
	
	public Conta getConta() {
		return conta;
	}
	public void setConta(Conta conta) {
		this.conta = conta;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public boolean isFinalizado() {
		return finalizado;
	}
	public void setFinalizado(boolean finalizado) {
		this.finalizado = finalizado;
	}
	
	
	public String toString(){
		return "Conta: "+ conta +" Transacao : "+ operacao + " Data/Hora: " + data;
	}

}
