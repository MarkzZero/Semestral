package view.disciplinas;

import java.awt.Font;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import controller.disciplinas.disciplinaController;

public class Cadastro extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField codDisciplina;
    private JTextField nomeDisciplina;
    private JTextField codCurso;
    private JFormattedTextField txtHorario; 
    private JButton btnCadastrar;
    private JTextField textField;
    private JTextField textField_1;

    public Cadastro() {
    	setTitle("Cadastro");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 532, 363);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Código da disciplina");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel.setBounds(37, 41, 134, 24);
        contentPane.add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("Nome da disciplina");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel_1.setBounds(199, 43, 134, 21);
        contentPane.add(lblNewLabel_1);
        
        JLabel lblNewLabel_2 = new JLabel("Dia da semana");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel_2.setBounds(372, 46, 114, 14);
        contentPane.add(lblNewLabel_2);
        
        JLabel lblNewLabel_3 = new JLabel("Horário inicial");
        lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel_3.setBounds(55, 135, 134, 14);
        contentPane.add(lblNewLabel_3);
        
        JLabel lblNewLabel_4 = new JLabel("Código do curso");
        lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel_4.setBounds(346, 135, 128, 14);
        contentPane.add(lblNewLabel_4);
        
        codDisciplina = new JTextField();
        codDisciplina.setBounds(55, 76, 86, 20);
        contentPane.add(codDisciplina);
        codDisciplina.setColumns(10);
        
        nomeDisciplina = new JTextField();
        nomeDisciplina.setBounds(181, 76, 140, 20);
        contentPane.add(nomeDisciplina);
        nomeDisciplina.setColumns(10);
        
        codCurso = new JTextField();
        codCurso.setBounds(340, 160, 134, 20);
        contentPane.add(codCurso);
        codCurso.setColumns(10);
        
        try {
            MaskFormatter mascaraData = new MaskFormatter("##/##/####");
            mascaraData.setPlaceholderCharacter('_');
        } catch (ParseException e) {
            e.printStackTrace();
        }


        try {
            MaskFormatter mascaraHora = new MaskFormatter("##:##");
            mascaraHora.setPlaceholderCharacter('_');
            txtHorario = new JFormattedTextField(mascaraHora);
            txtHorario.setBounds(55, 160, 86, 20);
            contentPane.add(txtHorario);
            
            btnCadastrar = new JButton("Cadastro");
            btnCadastrar.setBounds(189, 254, 105, 23);
            contentPane.add(btnCadastrar);
            
            textField = new JTextField();
            textField.setBounds(352, 76, 122, 20);
            contentPane.add(textField);
            textField.setColumns(10);
            
            textField_1 = new JTextField();
            textField_1.setBounds(181, 160, 140, 20);
            contentPane.add(textField_1);
            textField_1.setColumns(10);
            
            JLabel lblNewLabel_5 = new JLabel("Quantidade de horas diárias");
            lblNewLabel_5.setBounds(181, 135, 140, 16);
            contentPane.add(lblNewLabel_5);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        disciplinaController discCtrl = new disciplinaController(codCurso, codCurso, codCurso, txtHorario, txtHorario);
        btnCadastrar.addActionListener(discCtrl);
        btnCadastrar.addActionListener(e -> dispose());
    }
}