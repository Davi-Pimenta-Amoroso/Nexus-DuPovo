package br.com.mercado.controller;

import br.com.mercado.model.Usuario;
import br.com.mercado.dao.UsuarioDAO;

public class LoginController {
    
    public Usuario autenticar(String login, String senha) {
        UsuarioDAO dao = new UsuarioDAO();
        return dao.buscarPorLoginESenha(login, senha);
    }
}
