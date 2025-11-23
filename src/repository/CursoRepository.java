package repository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import lista.Lista;
import model.cursos.Curso;

public class CursoRepository {
	private String path;
	
	

	public CursoRepository(String path) {
		super();
		this.path = path;
	}


	public Curso buscar(String codigoCurso) throws Exception {
	    Lista<Curso> lista = readFile("cursos.csv");
	    
	    for (int i = 0; i < lista.size(); i++) {
	        Curso c = lista.get(i);
	        if (c.getCodCurso().equals(codigoCurso)) {
	            return c;
	        }
	    }
	    
	    return null; 
	}

	
	public Lista<Curso> readFile(String nome) throws IOException {
	    File file = new File(path, nome);
	    Lista<Curso> listaCurso = new Lista<>();

	    if(file.exists()) {
	        FileInputStream fluxo = new FileInputStream(file);
	        InputStreamReader leitor = new InputStreamReader(fluxo);
	        BufferedReader buffer = new BufferedReader(leitor);

	        String linha = buffer.readLine();
	        while(linha != null) {
	            String[] campos = linha.split(";");
	            String codCurso = campos[0];
	            String nomeCurso = campos[1];
	            String area = campos[2];

	            Curso Curso = new Curso(codCurso, nomeCurso, area);

	            if(listaCurso.isEmpty()) {
	                listaCurso.addFirst(Curso);
	            } else {
	                try {
						listaCurso.addLast(Curso);
					} catch (Exception e) {
						e.printStackTrace();
					}
	            }

	            linha = buffer.readLine();
	        }

	        buffer.close();
	        leitor.close();
	        fluxo.close();

	        return listaCurso;

	    } else {
	        throw new IOException("Diretório inválido!!");
	    }
	}
}
