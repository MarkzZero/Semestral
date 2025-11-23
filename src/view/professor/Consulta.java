package view.professor;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.professor.ProfessorController;
import model.professor.Professor;

public class Consulta extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfCPF;
	private JTextField tfNome;
	private JTextField tfArea;
	private JTextField tfPontos;


	public Consulta(Professor p) {
		setTitle("Professor");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 393, 261);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null); 
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tfCPF = new JTextField(p.getCpf());
		tfCPF.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		tfCPF.setBounds(24, 22, 135, 27);
		contentPane.add(tfCPF);
		tfCPF.setColumns(10);
		tfCPF.setEditable(false);
		tfCPF.setBackground(Color.WHITE);
		
		tfNome = new JTextField(p.getNome());
		tfNome.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		tfNome.setBounds(215, 22, 135, 27);
		contentPane.add(tfNome);
		tfNome.setColumns(10);
		
		tfArea = new JTextField(p.getArea());
		tfArea.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		tfArea.setBounds(24, 73, 135, 27);
		contentPane.add(tfArea);
		tfArea.setColumns(10);
		
		tfPontos = new JTextField(String.valueOf(p.getQpontos()));
		tfPontos.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		tfPontos.setBounds(215, 73, 135, 27);
		contentPane.add(tfPontos);
		tfPontos.setColumns(10);
		
		JButton btnDeletar = new JButton("Deletar");
		btnDeletar.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		btnDeletar.setBounds(24, 152, 135, 27);
		contentPane.add(btnDeletar);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		btnEditar.setBounds(215, 152, 135, 27);
		contentPane.add(btnEditar);
		
		ProfessorController profCtrl = new ProfessorController(tfNome, tfCPF, tfArea, tfPontos);
		
		btnDeletar.addActionListener(profCtrl);
		btnDeletar.addActionListener(e -> dispose());
		
		btnEditar.addActionListener(profCtrl);
		btnEditar.addActionListener(e -> dispose());

	}
}
