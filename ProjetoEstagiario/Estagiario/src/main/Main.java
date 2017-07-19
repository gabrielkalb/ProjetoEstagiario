package main;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

import main.bean.ArquivoBean;


public class Main {
	
	public static void main(String[] args) {		
		JFrame frmEstagiario = new JFrame("Gerador de Properties");
		frmEstagiario.setTitle("Estagiario");
		frmEstagiario.setResizable(false);
		JLabel madeby = new JLabel();
		madeby.setText("by Gabriel Felipe Kalb");
		final ArquivoBean objetoArquivo = new ArquivoBean();
		
		
		//Monta tela
		frmEstagiario.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Container content = frmEstagiario.getContentPane();
		frmEstagiario.getContentPane().setLayout(null);
		content.add(madeby);
		madeby.setBounds(10, 346, 160, 30);
		frmEstagiario.setSize(652, 415);
		frmEstagiario.setVisible(true);
		content.setVisible(true);		
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 626, 324);
		frmEstagiario.getContentPane().add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Properties", null, panel, null);
		//final JFileChooser fileChooser = new JFileChooser();
		JButton botaoArquivoEntrada = new JButton("botaoArquivoEntrada");
		botaoArquivoEntrada.setHorizontalAlignment(SwingConstants.LEFT);
		botaoArquivoEntrada.setName("arquivoEntrada");
		botaoArquivoEntrada.setText("Arquivo Entrada");
		JButton botaoArquivosaida = new JButton("botaoArquivosaida");
		botaoArquivosaida.setHorizontalAlignment(SwingConstants.LEFT);
		botaoArquivosaida.setName("botaoArquivosaida");
		botaoArquivosaida.setText("Arquivo Saida");
		JButton gerarProperties = new JButton("gerarProperties");
		gerarProperties.setHorizontalAlignment(SwingConstants.LEFT);
		gerarProperties.setName("gerarProperties");
		gerarProperties.setText("Gerar Properties");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(5)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(gerarProperties, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(botaoArquivosaida, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(botaoArquivoEntrada, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(5)
					.addComponent(botaoArquivoEntrada)
					.addGap(5)
					.addComponent(botaoArquivosaida)
					.addGap(5)
					.addComponent(gerarProperties)
					.addContainerGap(212, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
        
		//Gera o properties
		gerarProperties.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					Botões botoes = new Botões();
					ArrayList<String> propriedades = new ArrayList<String>();	
					FileManagement fileManagement = new FileManagement();			
					try {
						String arquivoBrokerXML = botoes.unzip(objetoArquivo.getArquivoEntrada(), objetoArquivo.getPastaSaida());
						propriedades = fileManagement.leAchaProperties(arquivoBrokerXML);
						fileManagement.gravaArquivo(propriedades, objetoArquivo.getArquivoSaida());

						File pasta = new File(objetoArquivo.getPastaSaida());
						fileManagement.removerPasta(pasta);
						
						JOptionPane.showMessageDialog(null, "Finalizado com sucesso");
						
						//Runtime.getRuntime().exec("notepad.exe " + objetoArquivo.getArquivoSaida());
					} catch (IOException e1) {
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null, "Deu erro");
					} catch (Exception e1) {
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null, "Deu erro");
					}
			}
		});
		
				// Salva Arquivo
				botaoArquivosaida.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Botões botoes = new Botões();
						objetoArquivo.setArquivoSaida(botoes.SalvaArquivo());
					}
				});
		

		
		//Ações Botões
		
		// Abre Arquivo		
		botaoArquivoEntrada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Botões botoes = new Botões();
				FileManagement fileManagement = new FileManagement();
				objetoArquivo.setArquivoEntrada(botoes.AbreArquivo());
				objetoArquivo.setPastaSaida(fileManagement.recuperaNomePasta(objetoArquivo.getArquivoEntrada()));
			}
		});
		
		

	}
}
