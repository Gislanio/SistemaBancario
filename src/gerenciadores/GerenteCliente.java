package gerenciadores;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import negocio.Cliente;
import excecao.ClienteException;
import persistencia.ClientePersist;


public class GerenteCliente {
	Cliente cliente;
	ClientePersist cp = new ClientePersist();
	ArrayList<Cliente> clientes = new ArrayList<Cliente>();
	List<Cliente> c1;
	public void addCliente(Cliente cliente) throws ClassNotFoundException, Exception{
		if(clientes.contains(cliente) == true){
			throw new ClienteException(
					"Este cliente já existe!");
		}
		clientes.add(cliente);
		atualizarPersistencia();
	}
	
	private void atualizarPersistencia(){
		try {
			cp.gravarCliente(clientes);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void removeCliente(Cliente cliente){

		if(clientes.contains(cliente) == false){
			throw new ClienteException(
					"Este cliente não existe!");
		}
		clientes.remove(cliente);
		atualizarPersistencia();
	}
	
	public Cliente searchCliente(String cpf){
		for(Cliente i : clientes){
			if(i.getCpf().equals(cpf)){
				return i;
			}
		}
		throw new ClienteException("A busca não encontrou este cliente");
	}
	
	public Cliente recuperarCliente(Cliente c){
		c1=  cp.recuperarObjeto();
		for(Cliente i:c1){
			if(c.getCpf().equals(i.getCpf())){
				return i;
			}
		}
		return null;
		
	}
	
	public List<Cliente> recuperarAllCliente(){
		return cp.recuperarObjeto();
	}

	
}
