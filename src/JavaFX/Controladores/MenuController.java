/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */

package JavaFX.Controladores;
import Entities.Municipio;
import JavaFX.Main.CRUD.Atualizar;
import JavaFX.Main.CRUD.Deletar;
import JavaFX.Main.CRUD.Ler;
import JavaFX.Main.Login;
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
import Services.Arquivo;
import Services.CRUD;
import Services.Tratamento;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Willian Junior <willianjunior.c.f@gmail.com>
 */
public class MenuController implements Initializable {
    
    @FXML private Button btVoltar;
    @FXML private Button btCriar;
    @FXML private Button btLer;
    @FXML private Button btAtualizar;
    @FXML private Button btDeletar;
    @FXML private Button btOut;
    
    public static Alert erro = new Alert(Alert.AlertType.ERROR);
    public static Alert info = new Alert(Alert.AlertType.INFORMATION);
    public static Alert conf = new Alert(Alert.AlertType.CONFIRMATION);
    
    public static void TelaMenu(){
        Menu m = new Menu();
            try {
                m.start(new Stage());
            } catch (Exception ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    public static boolean validacaoespaco(TextField espaco, int index, Alert erro){
            if(espaco.getText() == null || espaco.getText().isEmpty()){
                erro.setTitle("Erro");
                erro.setHeaderText("Erro ao tentar acessar a linha do csv");
                erro.setContentText("Por favor preencha o espaço codigo IBGE ou municipio");
                erro.show();
                return false;
            } else {
                if(index == -1){
                    erro.setTitle("Erro");
                    erro.setHeaderText("Erro ao tentar acessar a linha do csv");
                    erro.setContentText("Não foi encontrado esse municipio");
                    erro.show();
                    return false;
                } else return true;
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
    
    public void TelaLogin(){
        try {
            Login l = new Login();
            
            l.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            if(Arquivo.CSVIn.size() < 246){
            Arquivo.In();
            }
            
            btVoltar.setOnMouseClicked((MouseEvent e)->{
                Menu.getStage().close();
                TelaLogin();
            });
            
            btCriar.setOnMouseClicked((MouseEvent e)->{
                CRUD.Create();
                List<Municipio> res = new ArrayList<>();
                        res = Arquivo.getCSVIn();
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
            
            btOut.setOnMouseClicked((MouseEvent e)->{
                Arquivo.Out();
                info.setTitle("Exportação");
                info.setHeaderText("Notas da exportação");
                info.setContentText("Exportado para o caminho C:\\Projeto Integrador\\Out");
                info.show();
            });
        } catch (ParseException ex) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        }
    }
