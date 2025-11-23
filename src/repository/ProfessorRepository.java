package repository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import lista.Lista;
import model.professor.Professor;

public class ProfessorRepository {
	private String path;
	
	public ProfessorRepository(String path) {
		this.path = path;
	}
	
	public Professor buscarProfessor(String cpf) {
	    try {
	        Lista<Professor> listaProfessores = readFile("professor.csv");
	        
	        for(int i = 0; i < listaProfessores.size(); i++) {
	            Professor p = listaProfessores.get(i);
	            if(p.getCpf().equals(cpf)) {
	                return p;
	            }
	        }
	    } catch(Exception e) {
	        e.printStackTrace();
	    }
	    
	    return null;
	}
	
	public Lista<Professor> readFile(String nome) throws IOException {
	    File file = new File(path, nome);
	    Lista<Professor> listaProfessor = new Lista<>();

	    if(file.exists()) {
	        FileInputStream fluxo = new FileInputStream(file);
	        InputStreamReader leitor = new InputStreamReader(fluxo);
	        BufferedReader buffer = new BufferedReader(leitor);

	        String linha = buffer.readLine();
	        while(linha != null) {
	            String[] campos = linha.split(";");
	            String cpf = campos[0];
	            String nomeProf = campos[1];
	            String area = campos[2];
	            int pontos = Integer.parseInt(campos[3]);

	            Professor professor = new Professor(cpf, nomeProf, area, pontos);

	            if(listaProfessor.isEmpty()) {
	                listaProfessor.addFirst(professor);
	            } else {
	                try {
						listaProfessor.addLast(professor);
					} catch (Exception e) {
						e.printStackTrace();
					}
	            }

	            linha = buffer.readLine();
	        }

	        buffer.close();
	        leitor.close();
	        fluxo.close();

	        return listaProfessor;

	    } else {
	        throw new IOException("Diretório inválido!!");
	    }
	}

}
