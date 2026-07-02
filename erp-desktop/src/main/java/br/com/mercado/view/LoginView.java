package br.com.mercado.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import br.com.mercado.controller.LoginController;
import br.com.mercado.model.Usuario;

public class LoginView extends JFrame {

    private JTextField txtUsuario;
    private JPasswordField txtSenha;
    private JButton btnLogin;

    public LoginView() {
        setTitle("Login - DuPovo");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        // Painel principal com fundo e espaçamento
        JPanel painelPrincipal = new JPanel(new BorderLayout());
        painelPrincipal.setBackground(new Color(17, 43, 104));
        painelPrincipal.setBorder(new EmptyBorder(30, 40, 30, 40));
        setContentPane(painelPrincipal);

        // Título
        JLabel lblTitulo = new JLabel("Nexus ERP - DuPovo", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setBorder(new EmptyBorder(0, 0, 25, 0));
        painelPrincipal.add(lblTitulo, BorderLayout.NORTH);

        // Painel do formulário com GridBagLayout
        JPanel painelForm = new JPanel(new GridBagLayout());
        painelForm.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Font fonteLabel = new Font("Segoe UI", Font.PLAIN, 14);
        Font fonteCampo = new Font("Segoe UI", Font.PLAIN, 14);

        // Label + campo Usuário
        // Label + campo Usuário
        JLabel lblUsuario = new JLabel("Usuário");
        lblUsuario.setForeground(Color.WHITE);
        lblUsuario.setFont(fonteLabel);
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0;
        painelForm.add(lblUsuario, gbc);   // ADICIONE ESTA LINHA

        txtUsuario = new JTextField(15);
        txtUsuario.setFont(fonteCampo);
        gbc.gridx = 1; gbc.gridy = 0; gbc.weightx = 1;
        painelForm.add(txtUsuario, gbc);

// Label + campo Senha
        JLabel lblSenha = new JLabel("Senha");
        lblSenha.setForeground(Color.WHITE);
        lblSenha.setFont(fonteLabel);
        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0;
        painelForm.add(lblSenha, gbc);   // ADICIONE ESTA LINHA

        txtSenha = new JPasswordField(15);
        txtSenha.setFont(fonteCampo);
        gbc.gridx = 1; gbc.gridy = 1; gbc.weightx = 1;
        painelForm.add(txtSenha, gbc);

        painelPrincipal.add(painelForm, BorderLayout.CENTER);

        // Botão de login estilizado
        btnLogin = new JButton("Entrar");
        btnLogin.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnLogin.setBackground(new Color(51, 102, 204));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFocusPainted(false);
        btnLogin.setBorder(new EmptyBorder(10, 20, 10, 20));
        btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JPanel painelBotao = new JPanel();
        painelBotao.setOpaque(false);
        painelBotao.add(btnLogin);
        painelPrincipal.add(painelBotao, BorderLayout.SOUTH);

        // Ação do botão (mantenha a lógica que você já tinha)
        btnLogin.addActionListener(e -> {
            String login = txtUsuario.getText();
            String senha = new String(txtSenha.getPassword());

            LoginController controller = new LoginController();
            Usuario usuarioLogado = controller.autenticar(login, senha);

            if (usuarioLogado != null) {
                String mensagem = "Bem Vindo - Nexus ERP - DuPovo " 
                        + usuarioLogado.getNome() + " (" + usuarioLogado.getCargo() + ")!";
                JOptionPane.showMessageDialog(null, mensagem);
                this.dispose();

                TelaPrincipal telaPrincipal = new TelaPrincipal();
                telaPrincipal.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Login inválido. Tente novamente.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        setVisible(true);
    }
}