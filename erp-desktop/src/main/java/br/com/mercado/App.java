package br.com.mercado;

import com.formdev.flatlaf.FlatLightLaf;
import br.com.mercado.view.LoginView;

public class App {
    public static void main(String[] args) {
        FlatLightLaf.setup();
        SwingUtilities.invokeLater(() -> {
            new LoginView().setVisible(true);
        });
    }
}