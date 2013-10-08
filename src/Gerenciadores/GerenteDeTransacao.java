package Gerenciadores;

import java.io.IOException;
import java.util.*;

import negocio.Conta;
import negocio.TipoOperacao;
import negocio.Transacao;
import persistencia.TransacaoPersist;

public class GerenteDeTransacao {

	private ArrayList<Transacao> transacoes = new ArrayList<Transacao>();;
	private ArrayList<Transacao> listAux= new ArrayList<Transacao>();
	private TransacaoPersist tp = new TransacaoPersist();


	public void addTransacao(Transacao transacao){
		transacoes.add(transacao);
		atualizarPersistencia();

	}
	private void atualizarPersistencia(){
		try {
			tp.gravarTransacoes(transacoes);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public ArrayList<Transacao> getTransacoes() {
		return transacoes;
	}


	public void setTransacoes(ArrayList<Transacao> transacoes) {
		this.transacoes = transacoes;
	}


	public boolean contemTransacao(Conta conta, TipoOperacao op){
		for(Transacao i: transacoes){
			if(i.getConta().getNumero() == conta.getNumero() && i.getConta().getSenha() == conta.getSenha() && i.getOperacao().equals(op)){
				return true;
			}
		}
		return false;
	}

	public ArrayList<Transacao> extrato(Conta conta){
		verTransacoesPorConta(conta);
		ArrayList<Transacao> aux = new ArrayList<Transacao>();
		if(listAux.size()<5){
			for(int i = -1; i>-listAux.size() ; i--){
				aux.add(aux.get(i));
			}
			return aux;
		}
		for(int i = -1; i>-5 ; i--){
			aux.add(aux.get(i));
		}

		return aux;
	}

	public ArrayList<Transacao> verTransacoesPorConta(Conta conta){
		for(Transacao i: transacoes){
			if(i.getConta().getNumero() == conta.getNumero()){
				listAux.add(i);
			}
		}
		return listAux;
	}

	public List<Transacao> recuperarTransacoes(){
		return tp.recuperarObjeto();
	}












}
