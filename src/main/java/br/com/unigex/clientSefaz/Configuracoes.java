package br.com.unigex.clientSefaz;

import br.com.samuelweb.certificado.Certificado;
import br.com.samuelweb.certificado.CertificadoService;
import br.com.samuelweb.certificado.exception.CertificadoException;
import br.com.samuelweb.nfe.dom.ConfiguracoesIniciaisNfe;
import br.com.samuelweb.nfe.exception.NfeException;
import br.com.samuelweb.nfe.util.ConstantesUtil;
import br.com.samuelweb.nfe.util.Estados;

public class Configuracoes {
	public static ConfiguracoesIniciaisNfe iniciaConfiguracoes() throws NfeException, CertificadoException {
		// Certificado Arquivo, Parametros: -Caminho Certificado, - Senha
		Certificado certificado = CertificadoService.certificadoPfx(
				"/home/vica/P/certificados/betos_car/2017-2018/Certificado_A1_Betos_Car_2017_2018_senha_BC123.pfx",
				"BC123");
		// Certificado certificado =
		// CertificadoService.listaCertificadosWindows().get(0);
		System.out.println(certificado.getVencimento());
		return ConfiguracoesIniciaisNfe.iniciaConfiguracoes(Estados.CE, ConstantesUtil.AMBIENTE.PRODUCAO, certificado,
				"/home/vica/Downloads/schemas");
	}

}
