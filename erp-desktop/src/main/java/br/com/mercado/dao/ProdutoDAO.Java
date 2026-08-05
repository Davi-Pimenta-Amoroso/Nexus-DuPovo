package br.com.mercado.dao;

import br.com.mercado.model.Produto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {
    private static final boolean MODO_MOCK = true;

    private static List<Produto> produtosMock = new ArrayList<>();
    private static long proximoId = 1;

    private static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/mercado";
        String user = "root";
        String password = "";
        return DriverManager.getConnection(url, user, password);
    }

    public void salvar(Produto produto) {
        if (MODO_MOCK) {
            produto.setId(proximoId++);
            produtosMock.add(produto);
            return;
        }
        salvarNoBanco(produto);
    }

    public List<Produto> listarTodos() {
        if (MODO_MOCK) {
            return new ArrayList<>(produtosMock);
        }
        return listarDoBanco();
    }

    private void salvarNoBanco(Produto produto) {
        String sql = "INSERT INTO produtos (nome, preco, quantidade, categoria) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, produto.getNome());
            ps.setDouble(2, produto.getPreco());
            ps.setInt(3, produto.getQuantidade());
            ps.setString(4, produto.getCategoria());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private List<Produto> listarDoBanco() {
        List<Produto> lista = new ArrayList<>();
        String sql = "SELECT * FROM produtos";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Produto p = new Produto();
                p.setId(rs.getLong("id"));
                p.setNome(rs.getString("nome"));
                p.setPreco(rs.getDouble("preco"));
                p.setQuantidade(rs.getInt("quantidade"));
                p.setCategoria(rs.getString("categoria"));
                lista.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}