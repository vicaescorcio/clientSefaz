package br.com.unigex.clientSefaz;

import java.util.List;

import br.com.samuelweb.certificado.exception.CertificadoException;
import br.com.samuelweb.nfe.exception.NfeException;

/**
 * Hello world!
 *
 */
public class App {

	public static void main(String[] args) {
		try {
			String xml = "";
			Manifestacao manifest = new Manifestacao();
			ArquivosManager arquivo = new ArquivosManager();
			DownloadNfe nfe = new DownloadNfe();
			// leitura das chaves

			List<String> chaves = arquivo.lerChaves();

			// inicia configuracoes (leitura de certificado)
			Configuracoes.iniciaConfiguracoes();

			// manifesta nfe`s
			for (String chave : chaves) {
				//manifesta as notas
				 //manifest.manifestarNfe(chave, arquivo.cnpj);
				
				//recupera o xml completo P.S: é necessário o menifesto da NFE antes do download
				xml = nfe.getNfe(chave, arquivo.cnpj);
				
				// salva nfe no diretório
                arquivo.salvarXML(chave, xml);
			}

		} catch (NfeException | CertificadoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
