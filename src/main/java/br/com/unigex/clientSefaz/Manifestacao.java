package br.com.unigex.clientSefaz;
import br.com.samuelweb.nfe.Nfe;
import br.com.samuelweb.nfe.dom.Enum.StatusEnum;
import br.com.samuelweb.nfe.dom.Enum.TipoManifestacao;
import br.com.samuelweb.nfe.exception.NfeException;
import br.com.samuelweb.nfe.util.XmlUtil;
import br.inf.portalfiscal.nfe.schema.envConfRecebto.TRetEnvEvento;


public class Manifestacao {

	public void manifestarNfe(String chave, String cnpj) {
		// TODO Auto-generated method stub

        try {

            String chaves = "23180324578949000212550010000038771000038779";
            String cnpjs = "24578949000131";
            String motivo = null;
            TipoManifestacao tipoManifestacao = TipoManifestacao.CIENCIA_DA_OPERACAO;

            TRetEnvEvento retorno = Nfe.manifestacao(chave, tipoManifestacao, cnpj, motivo, XmlUtil.dataNfe());

            if (!StatusEnum.LOTE_EVENTO_PROCESSADO.getCodigo().equals(retorno.getCStat())) {
                throw new NfeException("Status:" + retorno.getCStat() + " - Motivo:" + retorno.getXMotivo());
            }

            if (!StatusEnum.EVENTO_VINCULADO.getCodigo().equals(retorno.getRetEvento().get(0).getInfEvento().getCStat())) {
                throw new NfeException("Status:" + retorno.getRetEvento().get(0).getInfEvento().getCStat() + " - Motivo:" + retorno.getRetEvento().get(0).getInfEvento().getXMotivo());
            }

            System.out.println("Status:" + retorno.getRetEvento().get(0).getInfEvento().getCStat());
            System.out.println("Motivo:" + retorno.getRetEvento().get(0).getInfEvento().getXMotivo());
            System.out.println("Data:" + retorno.getRetEvento().get(0).getInfEvento().getDhRegEvento());

        } catch (NfeException e) {
            System.err.println(e.getMessage());
        }

	}

}
