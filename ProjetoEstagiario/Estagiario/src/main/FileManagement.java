package main;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

public class FileManagement {
	public ArrayList<String> leAchaProperties(String fileLocation)
			throws Exception, IOException {
		Document doc = null;

		SAXBuilder builder = new SAXBuilder();

		ArrayList<String> propriedadesLogs = new ArrayList<String>();
		ArrayList<String> propriedadesMQ = new ArrayList<String>();
		ArrayList<String> propriedadesWS = new ArrayList<String>();
		ArrayList<String> propriedadesFile = new ArrayList<String>();
		ArrayList<String> propriedades = new ArrayList<String>();
		ArrayList<String> propriedadesUserDefined = new ArrayList<String>();
		ArrayList<String> propriedadesSocket = new ArrayList<String>();
		ArrayList<String> propriedadesMQHeader = new ArrayList<String>();
		
		doc = builder.build(fileLocation);
		Element properties = doc.getRootElement();
		List<Element> compiledMessageFlow = properties.getChildren();

		String titulo = "##############################\n"
				+ "###          Logs          ###\n"
				+ "##############################\n";

		propriedadesLogs.add(titulo);

		for (int i = 0; i < compiledMessageFlow.size(); i++) {
			List<Element> configurableProperty = compiledMessageFlow.get(i)
					.getChildren();
			for (Element f : configurableProperty) {
				if (f.getAttributeValue("override") != null) {
					if (f.getAttributeValue("uri").contains("filePath")) {

						propriedadesLogs.add(f.getAttributeValue("uri") + "="
								+ f.getAttributeValue("override"));
					}
				}
			}
		}
		Collections.sort(propriedadesLogs);

		titulo = "\n\n\n\n\n##############################\n"
				+ "###          MQ            ###\n"
				+ "##############################\n";

		propriedadesMQ.add(titulo);

		for (int i = 0; i < compiledMessageFlow.size(); i++) {
			List<Element> configurableProperty = compiledMessageFlow.get(i)
					.getChildren();
			for (Element f : configurableProperty) {
				if (f.getAttributeValue("override") != null) {
					if ((f.getAttributeValue("uri").contains("queueName"))
							|| f.getAttributeValue("uri").contains("replyToQ")) {

						propriedadesMQ.add(f.getAttributeValue("uri") + "="
								+ f.getAttributeValue("override"));
					}
				}
			}
		}
		Collections.sort(propriedadesMQ);
		
		titulo = "\n\n\n\n\n##############################\n"
				+ "###          WS            ###\n"
				+ "##############################\n";

		propriedadesWS.add(titulo);

		for (int i = 0; i < compiledMessageFlow.size(); i++) {
			List<Element> configurableProperty = compiledMessageFlow.get(i)
					.getChildren();
			for (Element f : configurableProperty) {
				if (f.getAttributeValue("override") != null) {
					if ((f.getAttributeValue("uri").contains("urlSelector"))
							|| f.getAttributeValue("uri").contains("webServiceURL")
							|| f.getAttributeValue("uri").contains("URLSpecifier")
							|| f.getAttributeValue("uri").contains("httpProxyLocation")
							|| f.getAttributeValue("uri").contains("sslProtocol")) {

						propriedadesWS.add(f.getAttributeValue("uri") + "="
								+ f.getAttributeValue("override"));
					}
				}
			}
		}
		Collections.sort(propriedadesWS);
		
		titulo = "\n\n\n\n\n##############################\n"
				+ "###        FILE            ###\n"
				+ "##############################\n";

		propriedadesFile.add(titulo);

		for (int i = 0; i < compiledMessageFlow.size(); i++) {
			List<Element> configurableProperty = compiledMessageFlow.get(i)
					.getChildren();
			for (Element f : configurableProperty) {
				if (f.getAttributeValue("override") != null) {
					if ((f.getAttributeValue("uri").contains("outputDirectory"))
							|| f.getAttributeValue("uri").contains("outputFilename")
							|| f.getAttributeValue("uri").contains("filenamePattern")
							|| f.getAttributeValue("uri").contains("inputDirectory")) {

						propriedadesFile.add(f.getAttributeValue("uri") + "="
								+ f.getAttributeValue("override"));
					}
				}
			}
		}
		Collections.sort(propriedadesFile);
		
		titulo = "\n\n\n\n\n##############################\n"
				+ "###        USER DEFINED       ###\n"
				+ "##############################\n";

		propriedadesUserDefined.add(titulo);

		for (int i = 0; i < compiledMessageFlow.size(); i++) {
			List<Element> configurableProperty = compiledMessageFlow.get(i)
					.getChildren();
			for (Element f : configurableProperty) {
				if (f.getAttributeValue("override") != null) {
					String s[] = f.getAttributeValue("uri").split("#");
					if (!s[1].contains(".")) {

						propriedadesUserDefined.add(f.getAttributeValue("uri") + "="
								+ f.getAttributeValue("override"));
					}
				}
			}
		}
		Collections.sort(propriedadesUserDefined);
		
		titulo = "\n\n\n\n\n##############################\n"
				+ "###        SOCKET         ###\n"
				+ "##############################\n";

		propriedadesSocket.add(titulo);

		for (int i = 0; i < compiledMessageFlow.size(); i++) {
			List<Element> configurableProperty = compiledMessageFlow.get(i)
					.getChildren();
			for (Element f : configurableProperty) {
				if (f.getAttributeValue("override") != null) {
					if ((f.getAttributeValue("uri").contains("connectionDetails"))
							|| f.getAttributeValue("uri").contains("timeoutSendingData")
							|| f.getAttributeValue("uri").contains("validateMaster")
							|| f.getAttributeValue("uri").contains("timeoutWaitingForData")) {

						propriedadesSocket.add(f.getAttributeValue("uri") + "="
								+ f.getAttributeValue("override"));
					}
				}
			}
		}
		Collections.sort(propriedadesSocket);
		
		titulo = "\n\n\n\n\n##############################\n"
				+ "###        MQHeader        ###\n"
				+ "##############################\n";

		propriedadesMQHeader.add(titulo);

		for (int i = 0; i < compiledMessageFlow.size(); i++) {
			List<Element> configurableProperty = compiledMessageFlow.get(i)
					.getChildren();
			for (Element f : configurableProperty) {
				if (f.getAttributeValue("override") != null) {
					if ((f.getAttributeValue("uri").contains("mqdlhDestQMgrName"))
							|| f.getAttributeValue("uri").contains("mqdlhDestQName")
							|| f.getAttributeValue("uri").contains("mqmdReplyToQ")
							|| f.getAttributeValue("uri").contains("mqmdReplyToQMgr")) {

						propriedadesMQHeader.add(f.getAttributeValue("uri") + "="
								+ f.getAttributeValue("override"));
					}
				}
			}
		}
		Collections.sort(propriedadesMQHeader);
		

		// Organiza, coloca primeiro as logs depois propriedades de MQ
		if (propriedadesLogs.size() > 1) {
			for (int i = 0; i < propriedadesLogs.size(); i++) {
				propriedades.add(propriedadesLogs.get(i));
			}
		}
		if (propriedadesMQ.size() > 1) {
			for (int i = 0; i < propriedadesMQ.size(); i++) {
				propriedades.add(propriedadesMQ.get(i));
			}
		}
		if (propriedadesWS.size() > 1) {
			for (int i = 0; i < propriedadesWS.size(); i++) {
				propriedades.add(propriedadesWS.get(i));
			}
		}
		if (propriedadesFile.size() > 1) {
			for (int i = 0; i < propriedadesFile.size(); i++) {
				propriedades.add(propriedadesFile.get(i));
			}
		}
		
		if (propriedadesUserDefined.size() > 1) {
			for (int i = 0; i < propriedadesUserDefined.size(); i++) {
				propriedades.add(propriedadesUserDefined.get(i));
			}
		}

		if (propriedadesSocket.size() > 1) {
			for (int i = 0; i < propriedadesSocket.size(); i++) {
				propriedades.add(propriedadesSocket.get(i));
			}
		}
		
		if (propriedadesMQHeader.size() > 1) {
			for (int i = 0; i < propriedadesMQHeader.size(); i++) {
				propriedades.add(propriedadesMQHeader.get(i));
			}
		}
		
		return propriedades;
	}

	public void gravaArquivo(ArrayList<String> propriedades, String fileLocation)
			throws IOException {
		File arquivoOut;
		arquivoOut = new File(fileLocation);
		FileOutputStream fos = new FileOutputStream(arquivoOut);

		for (int i = 0; i < propriedades.size(); i++) {
			String saida = propriedades.get(i) + "\n";
			fos.write(saida.getBytes());
		}

		fos.close();
	}

	public String recuperaNomePasta(String caminhoArquivo) {
		File arquivo = new File(caminhoArquivo);

		return arquivo.getParent().toString() + "\\"
				+ arquivo.getName().replace(".", "");
	}

	public void removerPasta(File f) {
		if (f.isDirectory()) {
			File[] files = f.listFiles();
			for (int i = 0; i < files.length; ++i) {
				removerPasta(files[i]);
			}
		}
		f.delete();
	}

}
