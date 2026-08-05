package br.com.mercado.view;

import javax.swing.*;
import java.awt.*;

public class TelaPrincipal extends JFrame {

    private JDesktopPane desktop;

    public TelaPrincipal() {
        // Configurações da Janela Principal do Mercado DuPovo
        setTitle("Nexus ERP - Mercado DuPovo");
        setSize(1024, 768);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        desktop = new JDesktopPane();
        desktop.setBackground(new Color(220, 225, 230));
        setContentPane(desktop);

        criarMenu();
    }

    private void criarMenu() {
        JMenuBar barraMenu = new JMenuBar();

        // Menu de Cadastros obrigatórios do TCC
        JMenu menuCadastros = new JMenu("Cadastros");
        JMenuItem itemProdutos = new JMenuItem("Produtos");
        JMenuItem itemFuncionarios = new JMenuItem("Funcionários");

        menuCadastros.add(itemProdutos);
        menuCadastros.add(itemFuncionarios);

        // Menu do Sistema
        JMenu menuSistema = new JMenu("Sistema");
        JMenuItem itemSair = new JMenuItem("Sair");
        menuSistema.add(itemSair);

        barraMenu.add(menuCadastros);
        barraMenu.add(menuSistema);
        setJMenuBar(barraMenu);

        // Abre a tela de cadastro de produtos dentro do painel principal
        itemProdutos.addActionListener(e -> {
            try {
                CadastroProdutoInternalFrame telaProd = new CadastroProdutoInternalFrame();
                desktop.add(telaProd);
                telaProd.setVisible(true);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao abrir tela: " + ex.getMessage());
            }
        });

        itemSair.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this, "Deseja realmente sair?", "Confirmar", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TelaPrincipal().setVisible(true);
        });
    }
}