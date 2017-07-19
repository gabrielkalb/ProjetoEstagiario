package main;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import javax.swing.JFileChooser;

public class Botões {
	public String AbreArquivo() {
		final JFileChooser fileChooser = new JFileChooser();
		fileChooser.showSaveDialog(fileChooser);
		File inFile = fileChooser.getSelectedFile();
		
		return inFile.getPath();
	}
	
	public String SalvaArquivo() {
		final JFileChooser fileChooser = new JFileChooser();
		fileChooser.showSaveDialog(fileChooser);
		File outFile = fileChooser.getSelectedFile();
		
		return outFile.getPath();
	}
	
	@SuppressWarnings("rawtypes")
	public String unzip(String nomeArquivo, String diretorioSaida) {  
	    Enumeration entries;  
	    ZipFile zipFile;  
	    try {  
	        zipFile = new ZipFile(nomeArquivo);  
	        
	        entries = zipFile.entries();  
	        
	        (new File(diretorioSaida)).mkdir();
	        (new File(diretorioSaida +"/"+ "META-INF")).mkdir();
	        
	        while (entries.hasMoreElements()) {  
	            ZipEntry entry = (ZipEntry) entries.nextElement();  
	  
	            if (entry.isDirectory()) {  
	                // Assume directories are stored parents first then children.  
	                System.err.println("Extracting directory: " + diretorioSaida +"/"+entry.getName());  
	                // This is not robust, just for demonstration purposes.  
	                (new File(diretorioSaida +"/"+entry.getName())).mkdir();  
	                continue;  
	            }  
	  
	           
	            copyInputStream(zipFile.getInputStream(entry),  
	                           new BufferedOutputStream(new FileOutputStream(diretorioSaida +"/"+ entry.getName())));  
	        }  
	  
	        zipFile.close();  
	    } catch (IOException ioe) {  
	        System.err.println("Unhandled exception:");  
	        ioe.printStackTrace();    
	    }  
	    return (diretorioSaida +"/"+ "META-INF/broker.xml");
	}  
	  
	private static final void copyInputStream(InputStream in, OutputStream out) throws  
	      IOException {  
	      byte[] buffer = new byte[1024];  
	      int len;  
	  
	      while ((len = in.read(buffer)) >= 0)  
	          out.write(buffer, 0, len);  
	  
	      in.close();  
	      out.close();  
	}  
}
