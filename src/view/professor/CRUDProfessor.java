package view.professor;
import view.professor.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JEditorPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CRUDProfessor extends JFrame {

	private static final long serialVersionUID = 2L;
	private JPanel contentPane;
	
	public CRUDProfessor() {
		setTitle("Professor");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocation(200, 100);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton cadastrar = new JButton("Cadastrar");
		JButton consultar = new JButton("Consultar");
		JButton editar = new JButton("Editar");	
		JButton deletar = new JButton("Deletar");

        cadastrar.setBounds(29, 90, 120, 30);
        consultar.setBounds(276, 90, 120, 30);
        editar.setBounds(29, 173, 120, 30);
        deletar.setBounds(276, 173, 120, 30);
        
        getContentPane().add(cadastrar);
        getContentPane().add(consultar);
        getContentPane().add(editar);
		getContentPane().add(deletar);
        
        // Evento dos botões
		
		cadastrar.addActionListener(e -> new Cadastro().setVisible(true));
		consultar.addActionListener(e -> new Consulta().setVisible(true));
		editar.addActionListener(e -> new Edicao().setVisible(true));
		deletar.addActionListener(e -> new Delete().setVisible(true));
		
		

	}
}
