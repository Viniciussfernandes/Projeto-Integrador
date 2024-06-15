/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */

package JavaFX.Controladores;
import JavaFX.Main.CRUD.Atualizar;
import JavaFX.Main.CRUD.Deletar;
import JavaFX.Main.CRUD.Ler;
import java.io.IOException;
import javafx.scene.input.MouseEvent;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import JavaFX.Main.Menu;
import Services.Tratamento;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Willian Junior <willianjunior.c.f@gmail.com>
 */
public class MenuController implements Initializable {
    
    @FXML private Button btCriar;
    @FXML private Button btLer;
    @FXML private Button btAtualizar;
    @FXML private Button btDeletar;
    
    public static void TelaMenu(){
        Menu m = new Menu();
            try {
                m.start(new Stage());
            } catch (Exception ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    public static int PesquisarLinha(TextField info){
        String mani = info.getText();
        if(Tratamento.Caracteres(mani)){
            return Tratamento.Pesquisa(0, mani);
        } else if(Tratamento.Numerico(mani)){
            int ibge = Integer.parseInt(mani);
            return Tratamento.Pesquisa(ibge, "");
        } else return -1;
    }
    
    public void TelaAtualizar(){
        try {
            Atualizar a = new Atualizar();
            
            a.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void TelaDeletar(){
        try {
            Deletar d = new Deletar();
            
            d.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void TelaLer(){
        try {
            Ler l = new Ler();
            
            l.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btCriar.setOnMouseClicked((MouseEvent e)->{
            Alert info = new Alert(Alert.AlertType.INFORMATION);
            info.setTitle("Criação");
            info.setHeaderText("Novas informações inseridas");
            info.setContentText("Densidade Demográfica\nPIB per Capita Total\nClassificação dos IDHs");
            info.show();
        });
        
        btLer.setOnMouseClicked((MouseEvent e)->{
            try {
                Menu.getStage().close();
                TelaLer();
            } catch (Exception ex) {
                Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        btAtualizar.setOnMouseClicked((MouseEvent e)->{
            try {
                Menu.getStage().close();
                TelaAtualizar();
            } catch (Exception ex) {
                Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        btDeletar.setOnMouseClicked((MouseEvent e)->{
            try {
                Menu.getStage().close();
                TelaDeletar();
            } catch (Exception ex) {
                Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        }
    }
