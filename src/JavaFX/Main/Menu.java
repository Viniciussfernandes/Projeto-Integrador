/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML.java to edit this template
 */
package JavaFX.Main;

import JavaFX.Controladores.CRUD.CriarController;
import JavaFX.Main.CRUD.Atualizar;
import JavaFX.Main.CRUD.Criar;
import JavaFX.Main.CRUD.Deletar;
import JavaFX.Main.CRUD.Ler;
import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Willian Junior <willianjunior.c.f@gmail.com>
 */
public class Menu extends Application {
    
    private static Stage stage;

    public static Stage getStage() {
        return stage;
    }
    
    @Override
    public void start(Stage Menu) throws Exception {
        this.stage = Menu;
        String path = "/JavaFX/Telas/Menu.fxml";
        Parent menu = FXMLLoader.load(getClass().getResource(path));
        
        Scene scene = new Scene(menu);
        
        stage.setTitle("Menu");
        stage.setScene(scene);
        stage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
