package persistencia;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import java.util.List;

import negocio.Transacao;


public class TransacaoPersist {



	// Cria o objeto serializado
	Transacao t;
	File arquivo = new File("Transacoes.txt");
	List<Transacao> transacoes= new ArrayList<Transacao>();
	ObjectOutputStream output ;
	ObjectInputStream intput ;

	public void gravarTransacoes(List<Transacao> transacoes) throws IOException{
		if(arquivo.exists()==false){
			arquivo = new File("Transacoes.txt");
		}
		FileOutputStream arquivoOut = new FileOutputStream(arquivo);
		ObjectOutputStream objeto = new ObjectOutputStream(arquivoOut);
		objeto.writeObject(transacoes);

		objeto.flush();
		objeto.close();
		
		arquivoOut.flush();
		arquivoOut.close();	
	}

	public List<Transacao> recuperarObjeto() {

		try{

			FileInputStream arquivoLeitura = new FileInputStream("Contas.txt");
			ObjectInputStream objLeitura = new ObjectInputStream(arquivoLeitura);

			transacoes = (List<Transacao>) objLeitura.readObject();



			objLeitura.close();
			arquivoLeitura.close();
			return transacoes;
		}
		catch( Exception e ){
			e.printStackTrace( );
		}
		return null;
	}


}
