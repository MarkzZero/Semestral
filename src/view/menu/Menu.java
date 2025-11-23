package view.menu;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import view.cursos.CRUDcursos;
import view.disciplinas.CRUDdisciplinas;
import view.disciplinas.ProcessosAbertos;
import view.inscritos.CRUDinscritos;
import view.professor.CRUDProfessor;

public class Menu extends JFrame {
    private static final long serialVersionUID = 1L;

    public Menu() {
        setTitle("Menu Principal");
        setSize(400, 325);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);
        
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 384, 307);
        getContentPane().add(panel);
        panel.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Escolha uma opção");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 15));
        lblNewLabel.setBounds(114, 29, 142, 43);
        panel.add(lblNewLabel);
        
        JButton btnProfessor = new JButton("Professores");
        btnProfessor.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        btnProfessor.setBounds(40, 94, 110, 33);
        panel.add(btnProfessor);
        
        JButton btnDisciplina = new JButton("Disciplinas");
        btnDisciplina.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        btnDisciplina.setBounds(235, 94, 110, 33);
        panel.add(btnDisciplina);
        
        JButton btnCurso = new JButton("Cursos");
        btnCurso.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        btnCurso.setBounds(40, 173, 110, 33);
        panel.add(btnCurso);
        
        JButton btnInscrito = new JButton("Inscritos");
        btnInscrito.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        btnInscrito.setBounds(235, 173, 110, 33);
        panel.add(btnInscrito);
        
        JButton btnProcessos = new JButton("Processos abertos");
        btnProcessos.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        btnProcessos.setBounds(114, 227, 142, 34);
        panel.add(btnProcessos);
        
        btnProfessor.addActionListener(e -> new CRUDProfessor().setVisible(true));
        btnDisciplina.addActionListener(e -> new CRUDdisciplinas().setVisible(true));
        btnCurso.addActionListener(e -> new CRUDcursos().setVisible(true));
        btnInscrito.addActionListener(e -> new CRUDinscritos().setVisible(true));
        btnProcessos.addActionListener(e -> new ProcessosAbertos(). setVisible(true));
      
    }

    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.setLocationRelativeTo(null); 
        menu.setVisible(true);
    }
}

