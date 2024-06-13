/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */

package JavaFX.Controladores;
import JavaFX.Main.CRUD.Atualizar;
import JavaFX.Main.CRUD.Criar;
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
    
    public void TelaCriar() throws IOException, Exception{
        Menu.getStage().close();
        Criar c = new Criar();
        
        c.start(new Stage());
    }
    
    public void TelaAtualizar() throws IOException, Exception{
        Menu.getStage().close();
        Atualizar a = new Atualizar();
        
        a.start(new Stage());
    }
    
    public void TelaDeletar() throws IOException, Exception{
        Menu.getStage().close();
        Deletar d = new Deletar();
        
        d.start(new Stage());
    }
    
    public void TelaLer() throws IOException, Exception{
        Menu.getStage().close();
        Ler l = new Ler();
        
        l.start(new Stage());
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btCriar.setOnMouseClicked((MouseEvent e)->{
            try {
                TelaCriar();
            } catch (Exception ex) {
                Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        btLer.setOnMouseClicked((MouseEvent e)->{
            try {
                TelaLer();
            } catch (Exception ex) {
                Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        btAtualizar.setOnMouseClicked((MouseEvent e)->{
            try {
                TelaAtualizar();
            } catch (Exception ex) {
                Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        btDeletar.setOnMouseClicked((MouseEvent e)->{
            try {
                TelaDeletar();
            } catch (Exception ex) {
                Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        }
    }