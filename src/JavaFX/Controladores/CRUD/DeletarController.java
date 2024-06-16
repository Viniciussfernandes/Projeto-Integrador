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

package JavaFX.Controladores.CRUD;

import Entities.Perfil;
import JavaFX.Controladores.MenuController;
import JavaFX.Main.CRUD.Deletar;
import Services.Arquivo;
import Services.CRUD;
import Services.Tratamento;
import java.net.URL;
import java.text.ParseException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author Willian Junior <willianjunior.c.f@gmail.com>
 * @date 12/06/2024
 * @brief Class DeletarController
 */
public class DeletarController implements Initializable {
    
    @FXML private TextField Busca;
    @FXML private Button btDeletar;
    @FXML private Button btVoltar;
    
    
    public void delete(){
        int index = MenuController.PesquisarLinha(Busca);
        Alert erro = new Alert(Alert.AlertType.ERROR);
        
        if(MenuController.validacaoespaco(Busca, index, erro)){
            
            MenuController.conf.setTitle("Confirmação");
            MenuController.conf.setHeaderText("Exemplo");
            MenuController.conf.setContentText("Você deseja confirmar essa escolha?");
            
            ButtonType btSim = new ButtonType("Sim");
            ButtonType btNao = new ButtonType("Não");
            
            MenuController.conf.getButtonTypes().setAll(btSim, btNao);
            
            Optional<ButtonType> result = MenuController.conf.showAndWait();
            
            if(result.isPresent() && result.get() == btSim){
                CRUD.Delete(index);
                Alert info = new Alert(Alert.AlertType.INFORMATION);
                info.setTitle("Operação deletar");
                info.setHeaderText("Notas da remoção");
                info.setContentText("As seguinte informação foram deletadas:\nArea Km²\nPopulação" +
                        "\nDomicílios\nDensidade Demográfica\nPIB Total\nPIB per Capita Total" +
                        "\nIDH - Índice de Desenvolvimento Humeno Geral\nClassificação do IDH Geral" + 
                        "\nRendaMédia\nRenda Nominal\nPEA Dia\nIDH Dimensão Educação\nClassificação IDH Educação" +
                        "\nIDH Dimensão Longevidade\nClassificação IDH Longevidade");
                info.show();
            }
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            btDeletar.setOnMouseClicked((MouseEvent e) -> {
                delete();
            });
            btVoltar.setOnMouseClicked((MouseEvent e) -> {
                Deletar.getStage().close();
                MenuController.TelaMenu();
            });
        }
    }
