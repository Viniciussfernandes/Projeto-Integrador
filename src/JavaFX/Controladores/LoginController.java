/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package JavaFX.Controladores;
import Entities.Perfil;
import JavaFX.Main.Login;
import JavaFX.Main.Menu;
import Services.Tratamento;
import javafx.scene.input.MouseEvent;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Willian Junior <willianjunior.c.f@gmail.com>
 */
public class LoginController implements Initializable {
    
    @FXML private TextField login;
    @FXML private TextField CPF;
    @FXML private Button btEntrar;
    private static Perfil perfil;

    public static Perfil getPerfil() {
        return perfil;
    }
    
    public void Verif_Login(){
        if(Tratamento.Caracteres(login.getText()) && Tratamento.Numerico(CPF.getText()) &&
                Perfil.ValidarCPF(CPF.getText())){
            perfil = new Perfil(login.getText(), CPF.getText());
            Login.getStage().close();
            TelaMenu();
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Falha ao fazer o login");
            alert.setContentText("Informações inseridas incorretas");
            alert.show();
        }
    }
    
    public void TelaMenu(){
        Menu m = new Menu();
            try {
                m.start(new Stage());
            } catch (Exception ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btEntrar.setOnMouseClicked((MouseEvent e) -> {
            Verif_Login();
        });
        }
    }
