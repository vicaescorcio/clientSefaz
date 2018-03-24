package br.com.unigex.clientSefaz;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class ArquivosManager {

	public static File file = new File("/home/vica/Downloads/chaves.txt");
    public String cnpj;
    public static String path = "/home/vica/Downloads/xml/xml_";
	public List<String> lerChaves() {
		List<String> chaveLista = new ArrayList<String>();

		String linha = "";

		FileReader reader;
		try {
			reader = new FileReader(file);
			BufferedReader leitor = new BufferedReader(reader);
			while ((linha = leitor.readLine()) != null) {
				chaveLista.add(linha);
			}
			leitor.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		cnpj = chaveLista.get(0);
		chaveLista.remove(chaveLista.get(0));
		return chaveLista;
	}

	public void salvarXML(String chave, String xml) {
		Path pathXMLFile = Paths.get(path + chave +"-"+ cnpj+ ".xml");
		try {
			Files.write(pathXMLFile, xml.getBytes(), StandardOpenOption.WRITE, StandardOpenOption.APPEND,
					StandardOpenOption.CREATE);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
