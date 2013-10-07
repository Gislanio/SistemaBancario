package persistencia;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import negocio.Conta;


public class ContaPersist {



	// Cria o objeto serializado
	Conta c;
	File arquivo = new File("Contas.txt");
	List<Conta> contas= new ArrayList<Conta>();
	ObjectOutputStream output ;
	ObjectInputStream intput ;




	public void gravarConta(ArrayList<Conta> contas) throws IOException{
		if(arquivo.exists()==false){
			arquivo = new File("Contas.txt");
		}
		FileOutputStream arquivoOut = new FileOutputStream(arquivo);
		ObjectOutputStream objeto = new ObjectOutputStream(arquivoOut);

		objeto.writeObject(contas);

		objeto.flush();

		objeto.close();

		arquivoOut.flush();

		arquivoOut.close();	

	}
	

	public List<Conta> recuperarObjeto() {

		List<Conta> c = new ArrayList<Conta>();
		try{

			FileInputStream arquivoLeitura = new FileInputStream("Contas.txt");
			ObjectInputStream objLeitura = new ObjectInputStream(arquivoLeitura);

			c = (List<Conta>) objLeitura.readObject();



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
