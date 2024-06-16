/*
 * Copyright (C) 2024 Willian Junior <willianjunior.c.f@gmail.com>
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */

package JavaFX.Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Willian Junior <willianjunior.c.f@gmail.com>
 * @date 07/06/2024
 * @brief Class Login
 */

public class Login extends Application {
    
    /** Esse é o começo de tudo, então o que vou falar aqui serve para todos ou outros. */
    
    /** Coloco o Stage dentro de uma variavel e depois um get e para poder ter acesso ao stage dessa classe.
     * Eu utilizo isso para fechar a janela ou inicializar a janela */
    private static Stage stage;

    public static Stage getStage() {
        return stage;
    }
    
    @Override
    public void start(Stage Login) throws Exception {
        // Através do this.stage eu renomeio o Stage para Login
        this.stage = Login;
        // Apartir daqui é automatico na criação do JavaAnt - FXML. Apenas atente-se ao caminho do .fxml que correponde a tela.
        String path = "/JavaFX/Telas/Login.fxml";
        Parent login = FXMLLoader.load(getClass().getResource("/JavaFX/Telas/Login.fxml"));
        
        Scene scene = new Scene(login);
        
        // Coloco um titulo na janela
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
