package view.cursos;
import view.cursos.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

public class CRUDcursos extends JFrame {

	private static final long serialVersionUID = 4L;
	private JPanel contentPane;

	public CRUDcursos() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Cursos");
		setBounds(100, 100, 450, 300);
		setLocation(400, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton cadastrar = new JButton("Cadastrar");
		cadastrar.setBounds(51, 86, 120, 33);
		contentPane.add(cadastrar);
		
		JButton consultar = new JButton("Consultar");
		consultar.setBounds(255, 88, 106, 28);
		contentPane.add(consultar);
		
		JButton editar = new JButton("Editar");
		editar.setBounds(51, 165, 120, 33);
		contentPane.add(editar);
		
		
		JButton deletar = new JButton("Deletar");
		deletar.setBounds(255, 165, 113, 28);
		contentPane.add(deletar);
		
		cadastrar.addActionListener(e  -> new Cadastro().setVisible(true));
		editar.addActionListener(e -> new Editar().setVisible(true));
		deletar.addActionListener(e -> new Deletar().setVisible(true));
	}

}
