package view.disciplinas;

import java.text.ParseException;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Cadastro extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JFormattedTextField txtData;
    private JFormattedTextField txtHorario; 
    private JButton btnNewButton;

    public Cadastro() {
    	setTitle("Cadastro");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 532, 363);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Código do curso");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel.setBounds(55, 41, 134, 24);
        contentPane.add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("Nome da disciplina");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel_1.setBounds(199, 43, 134, 21);
        contentPane.add(lblNewLabel_1);
        
        JLabel lblNewLabel_2 = new JLabel("Data");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel_2.setBounds(372, 46, 114, 14);
        contentPane.add(lblNewLabel_2);
        
        JLabel lblNewLabel_3 = new JLabel("Horário inicial");
        lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel_3.setBounds(55, 135, 134, 14);
        contentPane.add(lblNewLabel_3);
        
        JLabel lblNewLabel_4 = new JLabel("Código do curso");
        lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel_4.setBounds(260, 137, 128, 14);
        contentPane.add(lblNewLabel_4);
        
        textField = new JTextField();
        textField.setBounds(55, 76, 86, 20);
        contentPane.add(textField);
        textField.setColumns(10);
        
        textField_1 = new JTextField();
        textField_1.setBounds(181, 76, 140, 20);
        contentPane.add(textField_1);
        textField_1.setColumns(10);
        
        textField_2 = new JTextField();
        textField_2.setBounds(247, 162, 134, 20);
        contentPane.add(textField_2);
        textField_2.setColumns(10);
        
        try {
            MaskFormatter mascaraData = new MaskFormatter("##/##/####");
            mascaraData.setPlaceholderCharacter('_');
            txtData = new JFormattedTextField(mascaraData);
            txtData.setBounds(372, 76, 86, 20);
            contentPane.add(txtData);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        try {
            MaskFormatter mascaraHora = new MaskFormatter("##:##");
            mascaraHora.setPlaceholderCharacter('_');
            txtHorario = new JFormattedTextField(mascaraHora);
            txtHorario.setBounds(55, 160, 86, 20);
            contentPane.add(txtHorario);
            
            btnNewButton = new JButton("Cadastro");
            btnNewButton.setBounds(189, 254, 105, 23);
            contentPane.add(btnNewButton);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}