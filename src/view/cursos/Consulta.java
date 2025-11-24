package view.cursos;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.curso.CursoController;
import model.cursos.Curso;

public class Consulta extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfCodCurso;
	private JTextField tfNomeCurso;
	private JTextField tfArea;

	public Consulta(Curso c) {
		
		setTitle("Curso");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 393, 272);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null); 
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tfCodCurso = new JTextField(c.getCodCurso());
		tfCodCurso.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		tfCodCurso.setBounds(24, 23, 148, 29);
		contentPane.add(tfCodCurso);
		tfCodCurso.setColumns(10);
		tfCodCurso.setEnabled(false);
		
		tfNomeCurso = new JTextField(c.getNomeCurso());
		tfNomeCurso.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		tfNomeCurso.setBounds(24, 76, 327, 29);
		contentPane.add(tfNomeCurso);
		tfNomeCurso.setColumns(10);
		
		tfArea = new JTextField(c.getArea());
		tfArea.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		tfArea.setBounds(24, 130, 327, 29);
		contentPane.add(tfArea);
		tfArea.setColumns(10);
		
		
		JButton btnDeletar = new JButton("Deletar");
		btnDeletar.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		btnDeletar.setBounds(24, 182, 148, 29);
		contentPane.add(btnDeletar);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		btnEditar.setBounds(207, 182, 148, 29);
		contentPane.add(btnEditar);
		
		CursoController cursoCtrl = new CursoController(tfCodCurso, tfNomeCurso, tfArea);
		
		btnDeletar.addActionListener(cursoCtrl);
		btnDeletar.addActionListener(e -> dispose());
		btnEditar.addActionListener(cursoCtrl);
		btnEditar.addActionListener(e -> dispose());

	}

}
