package negocio;
import java.io.Serializable;


public class Cliente implements Serializable {
	private String nome;
	private String cpf;
	private int rg;
	private String endereco;
	
	public Cliente(){
		
	}
	
	
	public Cliente(String nome, String cpf, int rg, String endereco) {
		super( );
		this.nome = nome;
		this.cpf = cpf;
		this.rg = rg;
		this.endereco = endereco;
	}


	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public int getRg() {
		return rg;
	}
	public void setRg(int rg) {
		this.rg = rg;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	

}
