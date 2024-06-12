/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package JavaFX.Controladores;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author Willian Junior <willianjunior.c.f@gmail.com>
 */
public class LoginController implements Initializable {
    
    @FXML private TextField Login;
    @FXML private TextField CPF;
    @FXML private Button btEntrar;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Alert alert = new Alert(AlertType.ERROR);
        btEntrar.setOnMouseClicked((var event) ->{
        if(Login.getText().equals("Willian")){
            alert.setTitle("Erro");
            alert.setHeaderText("Login Invalido");
            alert.setContentText("Por favor informe informações validas");
            alert.show();
        }
        });
    }    
    
}
