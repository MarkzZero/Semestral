package controller.arquivos;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.swing.JOptionPane;

public class ArquivosController {

	public ArquivosController() {
		// TODO Auto-generated constructor stub
	}
	
	public void readDir(String path) throws IOException {
		File dir = new File(path);
		if(dir.exists() && dir.isDirectory()) {
			File[] file = dir.listFiles();
			for(File f : file) {
				if(f.isFile()) {
					System.out.println("     \t"+f.getName());
				}else {
					System.out.println("<DIR>\t"+f.getName());
				}
			}
		}else{
			throw new IOException("Diretório inválido!");
		}
	}

	
	public void createFile(String path, String nome, String conteudo) throws IOException {
		File dir = new File(path);
		File file = new File(path, nome);
		if (dir.exists() && dir.isDirectory()) {
			boolean existe = false;
			if(file.exists()) {
				existe = true;
			}
			FileWriter fileWriter = new FileWriter(file, existe);
			PrintWriter print = new PrintWriter(fileWriter);
			print.write(conteudo);
			print.flush(); //Finaliza a escrita
			print.close();
			fileWriter.close();
		}else {
			throw new IOException("Diretório inválido");
		}
	}
	
	public void readFile(String path, String nome) throws IOException {
		File file = new File(path, nome);
		if(file.exists()) {
			FileInputStream fluxo = new FileInputStream(file);
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);
			String linha = buffer.readLine();
			while(linha != null) {
				System.out.println(linha);
				linha = buffer.readLine();
			}
			buffer.close();
			leitor.close();
			fluxo.close();
		}else {
			throw new IOException("Diretório inválido!!");
		}
	}

	
	public void openFile(String path, String nome) throws IOException {
		File file = new File(path, nome);
		if(file.exists() && file.isFile()) {
			Desktop desktop = Desktop.getDesktop();
			desktop.open(file);
		}else {
			throw new IOException("Arquivo inválido!");
		}
	}

}
