/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML.java to edit this template
 */
package JavaFX.Main;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author Willian Junior <willianjunior.c.f@gmail.com>
 */
public class Login extends Application {
    
    private AnchorPane pane;
    private TextField txNome;
    private TextField txCPF;
    private Button btEntrar, btCadastro;
    private static Stage stage;

    public static Stage getStage() {
        return stage;
    }
    
    @Override
    public void start(Stage Login) throws Exception {
        this.stage = Login;
        String path = "/JavaFX/Telas/Login.fxml";
        Parent login = FXMLLoader.load(getClass().getResource(path));
        
        Scene scene = new Scene(login);
        
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}