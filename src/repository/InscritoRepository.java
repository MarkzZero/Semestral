package repository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import lista.Lista;
import model.inscrito.Inscrito;

public class InscritoRepository {
	private String path;
	
	public InscritoRepository(String path) {
		this.path = path;
	}

	public Lista<Inscrito> readFile(String nome) throws IOException {
		File file = new File(path, nome);
		Lista<Inscrito> listaInscrito = new Lista<>();

		if (file.exists()) {
			FileInputStream fluxo = new FileInputStream(file);
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);

			String linha = buffer.readLine();
			while (linha != null) {
				String[] campos = linha.split(";");
				String cpf = campos[0];
				String codDisciplina = campos[1];
				String codProcesso = campos[2];

				Inscrito inscrito = new Inscrito(cpf, codDisciplina, codProcesso);

				if (listaInscrito.isEmpty()) {
					listaInscrito.addFirst(inscrito);
				} else {
					try {
						listaInscrito.addLast(inscrito);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

				linha = buffer.readLine();
			}

			buffer.close();
			leitor.close();
			fluxo.close();

			return listaInscrito;

		} else {
			throw new IOException("Diretório inválido!!");
		}
	}

}
