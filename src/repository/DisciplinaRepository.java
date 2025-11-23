package repository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import lista.Lista;
import model.disciplina.Disciplina;

public class DisciplinaRepository {
	private String path;

	public DisciplinaRepository(String path) {
		super();
		this.path = path;
	}
	
	public Disciplina buscarDisciplina(String codDisc) {
		try {
			Lista<Disciplina> lista = readFile("disciplinas.csv");
			
	        for (int i = 0; i < lista.size(); i++) {
	            Disciplina d = lista.get(i);
	            if (d.getCodDisc().equals(codDisc)) {
	                return d;
	            }
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        return null;
	}
	
	private Lista<Disciplina> readFile(String nome) throws IOException {
	    File file = new File(path, nome);
	    Lista<Disciplina> listaDisc= new Lista<>();

	    if(file.exists()) {
	        FileInputStream fluxo = new FileInputStream(file);
	        InputStreamReader leitor = new InputStreamReader(fluxo);
	        BufferedReader buffer = new BufferedReader(leitor);

	        String linha = buffer.readLine();
	        while(linha != null) {
	            String[] campos = linha.split(";");
	            String codDisc = campos[0];
	            String nomeDisc = campos[1];
	            String diaSemana = campos[2];
	            String horaInicio = campos[3];
	            String quantidadeHoras = campos[4];
	            String codCurso = campos[5];

	            Disciplina disciplina = new Disciplina(codDisc, nomeDisc, diaSemana, horaInicio, quantidadeHoras, codCurso);

	            if(listaDisc.isEmpty()) {
	                listaDisc.addFirst(disciplina);
	            } else {
	                try {
						listaDisc.addLast(disciplina);
					} catch (Exception e) {
						e.printStackTrace();
					}
	            }

	            linha = buffer.readLine();
	        }

	        buffer.close();
	        leitor.close();
	        fluxo.close();

	        return listaDisc;

	    } else {
	        throw new IOException("Diretório inválido!!");
	    }
	}

}
