import java.awt.*;

import javax.swing.*;
import javax.swing.border.LineBorder;


public class JanelaLogin {


        public static void main(String[] args) {
            JFrame janela = new JFrame();

            janela.setSize( 550,400);
            janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            janela.setLocationRelativeTo(null);
            janela.setResizable(false);
            janela.setLayout(null);

            ///// titulo do login
            JLabel labelloginTitulo = new JLabel("Login",SwingConstants.CENTER);
            labelloginTitulo.setFont(new Font("Rockwell", Font.PLAIN, 44));
            labelloginTitulo.setBounds(150, 30, 250, 60);
            janela.add(labelloginTitulo);


            ///////
            JLabel labelNomeUsuario = new JLabel("Nome:");

            labelNomeUsuario.setFont(new Font("tahoma", Font.PLAIN, 22));
            labelNomeUsuario.setBounds(100,120,100,30);
            janela.add(labelNomeUsuario);

            JTextField jtNome= new JTextField();
            jtNome.setBounds(200,120,250,30);
            janela.add(jtNome);
            /// //////
            JLabel labelEmail = new JLabel("Email:");

            labelEmail.setFont(new Font("tahoma", Font.PLAIN, 22));
            labelEmail.setBounds(100,170,100,30);
            janela.add(labelEmail);

            JTextField jtEmail = new JTextField();
            jtEmail.setBounds(200,170,250,30);
            janela.add(jtEmail);
            /// /////

            JButton botaoLogin = new JButton("Login");
            botaoLogin.setBounds(150,246,130,30);

            janela.add(botaoLogin);

            JButton botaoCadastrar= new JButton("Cadastrar");
            botaoCadastrar.setBounds(300,246,130,30);
            janela.add(botaoCadastrar);

            janela.setVisible(true);
        }}
