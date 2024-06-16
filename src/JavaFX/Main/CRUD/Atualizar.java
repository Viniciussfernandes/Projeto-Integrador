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

package JavaFX.Main.CRUD;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Willian Junior <willianjunior.c.f@gmail.com>
 * @date 12/06/2024
 * @brief Class Atualizar
 */

public class Atualizar extends Application {
    
    private static Stage stage;

    public static Stage getStage() {
        return stage;
    }
    
    @Override
    public void start(Stage Atualizar) throws Exception {
        this.stage = Atualizar;
        String path = "/JavaFX/Telas/CRUD/Atualizar.fxml";
        Parent login = FXMLLoader.load(getClass().getResource("/JavaFX/Telas/CRUD/Atualizar.fxml"));
        
        Scene scene = new Scene(login);
        
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }
    
    public void mudarTela(ActionEvent event) throws IOException{
        Parent tela2 = FXMLLoader.load(getClass().getResource("/JavaFX/Telas/Menu.fxml"));
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
