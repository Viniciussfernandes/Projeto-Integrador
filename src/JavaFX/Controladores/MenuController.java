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

package JavaFX.Controladores;

import JavaFX.Main.CRUD.Atualizar;
import JavaFX.Main.CRUD.Deletar;
import JavaFX.Main.CRUD.Ler;
import JavaFX.Main.Login;
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
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * @author Willian Junior <willianjunior.c.f@gmail.com>
 * @date 07/06/2024
 * @brief Class MenuController
 */

// Classe responsavel por manipular os elementos da tela Menu.fxml
public class MenuController implements Initializable {
    
    @FXML private Button btVoltar;
    @FXML private Button btCriar;
    @FXML private Button btLer;
    @FXML private Button btAtualizar;
    @FXML private Button btDeletar;
    @FXML private Button btOut;
    
    // Todos os tipos de alert que o programa usa.
    private static Alert erro = new Alert(Alert.AlertType.ERROR);
    private static Alert informativo = new Alert(Alert.AlertType.INFORMATION);
    private static Alert confirmacao = new Alert(Alert.AlertType.CONFIRMATION);

    // gets com o titulo ja definido para eles.
    public static Alert getErro() {
        erro.setTitle("Erro");
        return erro;
    }

    public static Alert getInformativo() {
        informativo.setTitle("Informativo");
        return informativo;
    }

    public static Alert getConfirmacao() {
        confirmacao.setTitle("Confirmação");
        return confirmacao;
    }
    
    
    public static boolean VerificarTextFieldPesquisa(TextField espaco, int index){
            if(espaco.getText() == null || espaco.getText().isEmpty()){
                erro.setHeaderText("Erro ao tentar acessar a linha do csv!");
                erro.setContentText("Por favor preencha o espaço codigo IBGE ou municipio!");
                erro.showAndWait();
                return false;
            } else {
                if(index == -1){
                    erro.setHeaderText("Erro ao tentar acessar a linha do csv!");
                    erro.setContentText("Não foi encontrado esse municipio!");
                    erro.showAndWait();
                    return false;
                } else return true;
            }
    }
    
    // Metodo para pesquisar a linha
    public static int PesquisarLinha(TextField codigoIBGE_municipio){
        // Coloco o conteudo do TextField dentro de uma String
        String cod_mun = codigoIBGE_municipio.getText();
        /** Nesse if que vai determinar se o que o usuario digitou é o codigo do IBGE ou o nome do municipio. Para isso
         * Uso o metodo Tratamento.Caracteres que retorna true se estiver dentro do padrão estabelecido no metodo, nesse caso
         * são com letras e o Tratamento.Numerico com numeros. */
        if(Tratamento.Caracteres(cod_mun)){
            return Tratamento.Pesquisa(0, cod_mun);
        } else if(Tratamento.Numerico(cod_mun)){
            int ibge = Integer.parseInt(cod_mun);
            return Tratamento.Pesquisa(ibge, "");
        } else return -1;
    }
    
    // Mudanças de telas
    public void TelaLogin(){
        try {
            // Instancio a classe Login do main.
            Login l = new Login();
            // E com ela eu incializo o start, alem que colocar (New Stage()) pois a ideia é ele inicializar o stage de la.
            l.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // Os outros da mesma forma
    public static void TelaMenu(){
        Menu m = new Menu();
            try {
                m.start(new Stage());
            } catch (Exception ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // Uma simples verificação se o Arquivo .csv ja foi lido
            if(!Arquivo.isDadosCarregados()){
                Arquivo.In();
            }
            
            // MouseEvent para voltar para tela de Menu
            btVoltar.setOnMouseClicked((MouseEvent e)->{
               /** Menu referindo-se a classe do main chamada de Menu e o .getStage() que eu declarei como private static para pegar o stage e
                * .close() que vai fechar. Em outras palavras, o stage que é responsavel pela exibição da janela, quando eu pego esse stage e uso 
                * .close(), ele fecha e logo em seguida eu uso um metodo que eu criei no MenuController para mudar a tela para o menu. */
                Menu.getStage().close();
                TelaLogin();
            });
            
            // MouseEvent para a operação Create
            btCriar.setOnMouseClicked((MouseEvent e)->{
                // Inicializo a Operação Create
                CRUD.Create();
                // Pop-up informando sobre a criação
                informativo.setTitle("Criação");
                informativo.setHeaderText("Novas informações inseridas");
                informativo.setContentText("Densidade Demográfica\nPIB per Capita Total\nClassificação dos IDHs");
                informativo.showAndWait();
            });
            
            // MouseEvent para a operação Read
            btLer.setOnMouseClicked((MouseEvent e)->{
                try {
                    // Mudança de tela
                    Menu.getStage().close();
                    TelaLer();
                } catch (Exception ex) {
                    Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            
            // MouseEvent para a operação Read
            btAtualizar.setOnMouseClicked((MouseEvent e)->{
                try {
                    // Mudança de tela
                    Menu.getStage().close();
                    TelaAtualizar();
                } catch (Exception ex) {
                    Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            
            // MouseEvent para a operação Read
            btDeletar.setOnMouseClicked((MouseEvent e)->{
                try {
                    // Mudança de tela
                    Menu.getStage().close();
                    TelaDeletar();
                } catch (Exception ex) {
                    Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            
            // MouseEvent para a operação Read
            btOut.setOnMouseClicked((MouseEvent e)->{
                // Exportação do arquivo
                Arquivo.Out();
                // Pop-up informando sobre a exportação
                informativo.setTitle("Exportação");
                informativo.setHeaderText("Notas da exportação");
                informativo.setContentText("Exportado para o caminho C:\\Projeto Integrador\\Out");
                informativo.showAndWait();
            });
        } catch (ParseException ex) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        }
    }
