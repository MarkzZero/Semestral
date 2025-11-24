package view.inscritos;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.inscricoes.inscricoesController;
import model.inscrito.Inscrito;

public class Consulta extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField cpf;
	private JTextField codDisc;

	public Consulta(Inscrito inscrito) {
		setTitle("Inscritos");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 391, 184);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		cpf = new JTextField(inscrito.getCpf());
		cpf.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		cpf.setBounds(26, 28, 125, 31);
		contentPane.add(cpf);
		cpf.setColumns(10);
		
		codDisc = new JTextField(inscrito.getCodDisciplina());
		codDisc.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		codDisc.setBounds(214, 28, 125, 31);
		contentPane.add(codDisc);
		codDisc.setColumns(10);
		
		JButton btnDeletar = new JButton("Deletar");
		btnDeletar.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		btnDeletar.setBounds(26, 90, 125, 31);
		contentPane.add(btnDeletar);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		btnEditar.setBounds(214, 90, 125, 31);
		contentPane.add(btnEditar);
		
		inscricoesController inscCtrl = new inscricoesController(cpf, codDisc);
		
		btnDeletar.addActionListener(inscCtrl);
		btnDeletar.addActionListener(e -> dispose());
		btnEditar.addActionListener(inscCtrl);
		btnEditar.addActionListener(e -> dispose());

	}
}
