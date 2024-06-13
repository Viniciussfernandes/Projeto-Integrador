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
import Services.Tratamento;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author Willian Junior <willianjunior.c.f@gmail.com>
 * @date 12/06/2024
 * @brief Class CriarController
 */

public class CriarController implements Initializable {
    
    @FXML private TextField login;
    @FXML private TextField CPF;
    @FXML private Button btEntrar;
    

    public void Verif_Login(){
        if(Tratamento.Caracteres(login.getText()) && Tratamento.Numerico(CPF.getText()) &&
                Perfil.ValidarCPF(CPF.getText())){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Sucesso");
            alert.setHeaderText(null);
            alert.setContentText("Login bem-sucedido");
            alert.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Falha ao fazer o login");
            alert.setContentText("Informações inseridas incorretas");
            alert.show();
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btEntrar.setOnMouseClicked((MouseEvent e) -> {
            Verif_Login();
        });
        }
    }

