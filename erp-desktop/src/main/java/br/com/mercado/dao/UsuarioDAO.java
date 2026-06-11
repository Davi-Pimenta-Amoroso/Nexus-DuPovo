package br.com.mercado.dao;

import br.com.mercado.model.Usuario;
import java.sql.*;

public class UsuarioDAO {
    
    private Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/mercado";
        String user = "root";
        String password = "";
        return DriverManager.getConnection(url, user, password);
    }
    public Usuario buscarPorLoginESenha(String login, String senha) {
        String sql = "SELECT * FROM usuarios WHERE login  = ? AND senha = ?";
        try (Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){

                ps.setString(1, login);
                ps.setString(2, senha);

                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    Usuario usuario = new Usuario();
                    usuario.setId(rs.getLong("id"));
                    usuario.setNome(rs.getString("nome"));
                    usuario.setLogin(rs.getString("login"));
                    usuario.setSenha(rs.getString("senha"));
                    usuario.setCargo(rs.getString("cargo"));
                    return usuario;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }
}
