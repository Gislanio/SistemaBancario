package negocio;



public class ContaCorrenteEspecial extends Conta {

	private double limitePadrao;
	private double ValorLimiteAtual;
	
	public ContaCorrenteEspecial(Cliente cliente, int senha, int numero, double limitePadrao) {
		super(cliente, senha, numero);
		this.limitePadrao = limitePadrao;
		ValorLimiteAtual = limitePadrao;
	}
	
	
	
	/*public ContaCorrenteEspecial(int limite) {
		this.limite =limite;
		
	}*/

	


	public double getLimitePadrao() {
		return limitePadrao;
	}



	public void setLimitePadrao(double limitePadrao) {
		this.limitePadrao = limitePadrao;
	}



	public double getValorLimiteAtual() {
		return ValorLimiteAtual;
	}



	public void setValorLimiteAtual(double valorLimiteAtual) {
		ValorLimiteAtual = valorLimiteAtual;
	}
	
	
}
