package br.com.mercado.view;

import br.com.mercado.dao.ProdutoDAO;
import br.com.mercado.model.Produto;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class CadastroProdutoInternalFrame extends JInternalFrame {

    private JTextField txtNome;
    private JTextField txtPreco;
    private JTextField txtQuantidade;
    private JTextField txtCategoria;
    private JTable table;
    private DefaultTableModel tableModel;

    private ProdutoDAO produtoDAO = new ProdutoDAO();

    public CadastroProdutoInternalFrame() {
        super("Cadastro de Produtos", true, true, true, true);
        setSize(600, 400);
        setLayout(new BorderLayout());


        JPanel panelForm = new JPanel(new GridBagLayout());
        panelForm.setBorder(new EmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

// Linha 0: Nome (ocupa mais espaço) + Preço
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0.5;
        panelForm.add(new JLabel("Nome:"), gbc);
        gbc.gridx = 1; gbc.gridy = 0;
        panelForm.add(new JLabel("Preço:"), gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        txtNome = new JTextField(15);
        panelForm.add(txtNome, gbc);
        gbc.gridx = 1; gbc.gridy = 1;
        txtPreco = new JTextField(10);
        panelForm.add(txtPreco, gbc);

// Linha 2: Quantidade + Categoria
        gbc.gridx = 0; gbc.gridy = 2;
        panelForm.add(new JLabel("Quantidade:"), gbc);
        gbc.gridx = 1; gbc.gridy = 2;
        panelForm.add(new JLabel("Categoria:"), gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        txtQuantidade = new JTextField(15);
        panelForm.add(txtQuantidade, gbc);
        gbc.gridx = 1; gbc.gridy = 3;
        txtCategoria = new JTextField(10);
        panelForm.add(txtCategoria, gbc);

// Linha 4: botão Salvar ocupando as duas colunas
        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2;
        JButton btnSalvar = new JButton("Salvar Produto");
        btnSalvar.setBackground(new Color(41, 98, 255));
        btnSalvar.setForeground(Color.WHITE);
        btnSalvar.setFocusPainted(false);
        btnSalvar.setFont(new Font("Segoe UI", Font.BOLD, 13));
        panelForm.add(btnSalvar, gbc);

        add(panelForm, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(new Object[]{"ID", "Nome", "Preço", "Quantidade", "Categoria"}, 0);
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);
        
        atualizarTabela();

        btnSalvar.addActionListener(e -> {
            try {
                String nome = txtNome.getText();
                double preco = Double.parseDouble(txtPreco.getText().trim());
                int quantidade = Integer.parseInt(txtQuantidade.getText().trim());
                String categoria = txtCategoria.getText().trim();
                if (nome.isEmpty() || categoria.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Preencher todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                Produto produto = new Produto();
                produto.setNome(nome);
                produto.setPreco(preco);
                produto.setQuantidade(quantidade);
                produto.setCategoria(categoria);
                produtoDAO.salvar(produto);
                atualizarTabela();
                limparCampos();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Preço e Quantidade devem ser números válidos", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private void atualizarTabela() {
    tableModel.setRowCount(0);
    for (Produto p : produtoDAO.listarTodos()) {
        tableModel.addRow(new Object[]{
            p.getId(), p.getNome(), p.getPreco(), p.getQuantidade(), p.getCategoria()
        });
    }
}

    private void limparCampos() {
        txtNome.setText("");
        txtPreco.setText("");
        txtQuantidade.setText("");
        txtCategoria.setText("");
    }
}