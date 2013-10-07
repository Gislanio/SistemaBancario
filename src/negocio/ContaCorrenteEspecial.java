package negocio;



public class ContaCorrenteEspecial extends Conta {

	private double limite;
	
	public ContaCorrenteEspecial(Cliente cliente, int senha, int numero) {
		super(cliente, senha, numero);
		// TODO Auto-generated constructor stub
	}
	
	/*public ContaCorrenteEspecial(int limite) {
		this.limite =limite;
		
	}*/

	public double getLimite() {
		return limite;
	}

	public void setLimite(double limite) {
		this.limite = limite;
	}
	
}
