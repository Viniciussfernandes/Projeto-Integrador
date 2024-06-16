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

import JavaFX.Controladores.MenuController;
import JavaFX.Main.CRUD.Deletar;
import Services.CRUD;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * @author Willian Junior <willianjunior.c.f@gmail.com>
 * @date 12/06/2024
 * @brief Class DeletarController
 */

public class DeletarController implements Initializable {
    
    @FXML private TextField busca;
    @FXML private Button btDeletar;
    @FXML private Button btVoltar;
    
    
    public void Deletar(){
        // Da mesmo forma que o Atualizar
        int index = MenuController.PesquisarLinha(busca);
        
        if(MenuController.VerificarTextFieldPesquisa(busca, index)){
            
            MenuController.getConfirmacao().setHeaderText("Você está prestes à apagar as informações desse município");
            MenuController.getConfirmacao().setContentText("Tem certeza?");
            
            // Configuro os botões de sim e não
            ButtonType btSim = new ButtonType("Sim");
            ButtonType btNao = new ButtonType("Não");
            
            // Coloco dentro do Alert usando .getButtonTypes.setAll().
            MenuController.getConfirmacao().getButtonTypes().setAll(btSim, btNao);
            
            // Optional pode ou não conter um valor não nulo, uso para evitar de aparecer o erro NullPointerException caso o usuario feche, sem clicar
            Optional<ButtonType> result = MenuController.getConfirmacao().showAndWait();
            
            // Pego o resultado é confiro se o usuario clicou em uma opção com is.Present() e verifico se ele clicou em Sim
            if(result.isPresent() && result.get() == btSim){
                CRUD.Delete(index);
                MenuController.getInformativo().setTitle("Operação deletar");
                MenuController.getInformativo().setHeaderText("Notas da remoção");
                MenuController.getInformativo().setContentText("As seguinte informação foram deletadas:\nArea Km²\nPopulação" +
                        "\nDomicílios\nDensidade Demográfica\nPIB Total\nPIB per Capita Total" +
                        "\nIDH - Índice de Desenvolvimento Humeno Geral\nClassificação do IDH Geral" + 
                        "\nRendaMédia\nRenda Nominal\nPEA Dia\nIDH Dimensão Educação\nClassificação IDH Educação" +
                        "\nIDH Dimensão Longevidade\nClassificação IDH Longevidade");
                MenuController.getInformativo().show();
            }
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // MouseEvent para deletar
        btDeletar.setOnMouseClicked((MouseEvent e) -> {
            Deletar();
        });
        
        // MouseEvent para voltar ao menu
        btVoltar.setOnMouseClicked((MouseEvent e) -> {
            Deletar.getStage().close();
            MenuController.TelaMenu();
        });
    }
}
