package view.inscritos;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import lista.Lista;
import model.inscrito.InscritoDTO;

public class Inscritos extends JFrame {

    private static final long serialVersionUID = 1L;

    	public Inscritos(Lista<InscritoDTO> lista) {

        setTitle("Inscritos da Disciplina");
        setSize(597, 466);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        String[] colunas = { "CPF", "Nome", "Área", "Pontos", "Processo" };

        DefaultTableModel model = new DefaultTableModel(colunas, 0);
        int tamanho = lista.size();
        for (int i = 0; i < tamanho; i++) {
            try {
					model.addRow(new Object[]{
				    lista.get(i).getCpf(),
				    lista.get(i).getNome(),
				    lista.get(i).getArea(),
				    lista.get(i).getPontos(),
				    lista.get(i).getCodProcesso()
				});
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        

        JTable tabela = new JTable(model);
        tabela.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        JScrollPane scroll = new JScrollPane(tabela);

        getContentPane().add(scroll, BorderLayout.CENTER);
    }
}
