package negocio;


public class ContaPoupanca extends Conta{
	
	private String dataAniversario;
	private double taxaReferencial;
	private double saldoRendimento;
	
	
	public ContaPoupanca(Cliente cliente, int senha, int numero, double saldoInicial) {
		
		super(cliente, senha, numero);
		this.saldo =saldoInicial;
	}


	public String getDataAniversario() {
		return dataAniversario;
	}


	public void setDataAniversario(String dataAniversario) {
		this.dataAniversario = dataAniversario;
	}


	public double getTaxaReferencial() {
		return taxaReferencial;
	}


	public void setTaxaReferencial(double taxaReferencial) {
		this.taxaReferencial = taxaReferencial;
	}


	public double getSaldoRendimento() {
		return saldoRendimento;
	}


	public void setSaldoRendimento(double saldoRendimento) {
		this.saldoRendimento = saldoRendimento;
	}

	

}
