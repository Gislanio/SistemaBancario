package persistencia;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;


import negocio.Cliente;

public class ClientePersist {
	// Cria o objeto serializado
	Cliente c;
	File arquivo = new File("Clientes.txt");
	List<Cliente> clientes= new ArrayList<Cliente>();
	 ObjectOutputStream output ;
	 ObjectInputStream intput ;


	public void gravarCliente(ArrayList<Cliente> clientes) throws IOException{
		if(arquivo.exists()==false){
			arquivo = new File("Clientes.txt");
		}
			FileOutputStream arquivoOut = new FileOutputStream(arquivo);
			ObjectOutputStream objeto = new ObjectOutputStream(arquivoOut);
			
			objeto.writeObject(clientes);

			objeto.flush();

			objeto.close();

			arquivoOut.flush();

			arquivoOut.close();	
		

	
	}

	public List<Cliente> recuperarObjeto() {

		List<Cliente> c = new ArrayList<Cliente>();
		try{

			FileInputStream arquivoLeitura = new FileInputStream("Clientes.txt");
			ObjectInputStream objLeitura = new ObjectInputStream(arquivoLeitura);

			c = (List<Cliente>) objLeitura.readObject();



			objLeitura.close();

			arquivoLeitura.close();
			return c;

		}

		catch( Exception e ){
			e.printStackTrace( );
		}
		return null;


	}


}




