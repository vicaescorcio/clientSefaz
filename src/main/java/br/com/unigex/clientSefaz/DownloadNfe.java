package br.com.unigex.clientSefaz;

import java.io.IOException;
import java.util.List;

import br.com.samuelweb.nfe.Nfe;
import br.com.samuelweb.nfe.dom.Enum.StatusEnum;
import br.com.samuelweb.nfe.exception.NfeException;
import br.com.samuelweb.nfe.util.ConstantesUtil;
import br.com.samuelweb.nfe.util.XmlUtil;
import br.inf.portalfiscal.nfe.schema.retdistdfeint.RetDistDFeInt;
import br.inf.portalfiscal.nfe.schema.retdistdfeint.RetDistDFeInt.LoteDistDFeInt.DocZip;

public class DownloadNfe {

	public String getNfe(String chave, String cnpj) {
		String xml = "";
		try {
			RetDistDFeInt retorno = Nfe.distribuicaoDfe(ConstantesUtil.TIPOS.CNPJ, cnpj, ConstantesUtil.TIPOS.CHAVE,
					chave);

			System.out.println("Status:" + retorno.getCStat());
			System.out.println("Motivo:" + retorno.getXMotivo());
			System.out.println("NSU:" + retorno.getUltNSU());
			System.out.println("Max NSU:" + retorno.getMaxNSU());

			if (StatusEnum.DOC_LOCALIZADO_PARA_DESTINATARIO.getCodigo().equals(retorno.getCStat())) {
				
				List<DocZip> listaDoc = retorno.getLoteDistDFeInt().getDocZip();

				System.out.println("Encontrado " + listaDoc.size() + " Notas.");
				for (DocZip docZip : listaDoc) {
					System.out.println("Schema: " + docZip.getSchema());
					System.out.println("NSU:" + docZip.getNSU());
					System.out.println("XML: " + XmlUtil.gZipToXml(docZip.getValue()));
					System.out.println(docZip.toString());
					xml = XmlUtil.gZipToXml(docZip.getValue());
				}

			}
		} catch (NfeException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return xml;
	}

}
