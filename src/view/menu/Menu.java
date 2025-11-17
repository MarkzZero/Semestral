package view.menu;
import view.cursos.CRUDcursos;
import view.disciplinas.CRUDdisciplinas;
import view.inscritos.CRUDinscritos;
import view.professor.CRUDProfessor;

import javax.swing.*;
import java.awt.Font;

public class Menu extends JFrame {
    private static final long serialVersionUID = 1L;

    public Menu() {
        setTitle("Menu Principal");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        // Título
        JLabel lblNewLabel = new JLabel("Insira uma opção:");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel.setBounds(131, 27, 187, 38);
        getContentPane().add(lblNewLabel);

        // Botões
        JButton btn1 = new JButton("Professores");
        JButton btn2 = new JButton("Disciplinas");
        JButton btn3 = new JButton("Cursos");
        JButton btn4 = new JButton("Inscritos");

        btn1.setBounds(29, 90, 120, 30);
        btn2.setBounds(227, 90, 120, 30);
        btn3.setBounds(29, 173, 120, 30);
        btn4.setBounds(227, 173, 120, 30);

        getContentPane().add(btn1);
        getContentPane().add(btn2);
        getContentPane().add(btn3);
        getContentPane().add(btn4);

        // Ações dos botões
        btn1.addActionListener(e -> new CRUDProfessor().setVisible(true));
        btn2.addActionListener(e -> new CRUDdisciplinas().setVisible(true));
        btn3.addActionListener(e -> new CRUDcursos().setVisible(true));
        btn4.addActionListener(e -> new CRUDinscritos().setVisible(true));
    }

    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.setLocationRelativeTo(null); 
        menu.setVisible(true);
    }
}

