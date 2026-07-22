package br.com.mercado.dao;

import br.com.mercado.dao.ProdutoDAO;
import br.com.mercado.model.produto;

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


        JPanel panelForm = new JPanel(new GridLayout());
        panelForm.setBorder(new EmptyBorder(15, 15, 15, 15));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0;
        panelForm.add(new JLabel("Nome: "), gbc);
        txtNome = new JTextField(15);
        gbc.gridx = 1;
        panelForm.add(txtNome, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panelForm.add(new JLabel("Preço: "), gbc);
        txtPreco = new JTextField(15);
        gbc.gridx = 1;
        panelForm.add(txtPreco, gbc);


        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.setBackground(new Color(51, 102, 255));
        btnSalvar.setForeground(Color.WHITE);
        gbc.gridx = 0; gbc.gridy = 4;
        panelForm.add(btnSalvar, gbc);

        add(panelForm, BorderLayout.NORTH);

        modeloTabela = new DefaultTableModel(new Object[]{"ID", "nome", "Preço", "Quantidade", "Categoria"}, 0);
        tabela = new JTable(modeloTabela);
        add(new JScrollPane(tabela), BorderLayout.CENTER);
        
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

            } catch (NumberFormatExceprion ex) {
                JOptionPane.showMessageDialog(this, "Preço e Quantidade devem ser números válidos", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private void atualizarTabela() {
        modeloTabela.setRowCount(0);
        for (Produto p : produtoDAO.listarTodos()) {
            modeloTabela.addRow(new Object[]{
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