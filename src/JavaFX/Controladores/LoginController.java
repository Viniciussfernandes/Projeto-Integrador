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

import Entities.Perfil;
import JavaFX.Main.Login;
import Services.Tratamento;
import javafx.scene.input.MouseEvent;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * @author Willian Junior <willianjunior.c.f@gmail.com>
 * @date 07/06/2024
 * @brief Class LoginController
 */

public class LoginController implements Initializable {
    
    /** Todos os tipos de variaveis que pertencem ao JavaFX deve ter o @FXML antes do private. 
     * O TextField representa o campo de texto;
     * O Button pertence aos botões. */
    @FXML private TextField nome;
    @FXML private TextField CPF;
    @FXML private Button btEntrar;
    
    private static Perfil login = new Perfil();

    public static Perfil getLogin() {
        return login;
    }
    
    // Metodo para validar o login.
    public void VerificarLogin(){
        /** If que faz a verificação de acordo com o padrão apresentado no Tratamento.Caracteres,
         * Tratamento.Numerico, alem de usar o Perfil.ValidarCPF. */
        if(Tratamento.Caracteres(nome.getText()) && Tratamento.Numerico(CPF.getText()) &&
                Perfil.ValidarCPF(CPF.getText())){
            // Caso passe pela verificação, usa o construtor da classe perfil para armazenar o nome e CPF.
            login = new Perfil(nome.getText(), CPF.getText());
            
            /** Login referindo-se a classe do main chamada Login .getStage() que eu declarei como private static para pegar o stage 
             * e .close() que vai fechar. Em outras palavras, o stage que é responsavel pela exibição da janela, quando eu pego esse 
             * stage e uso .close(), ele fecha e logo em seguida eu uso um metodo que eu criei no MenuController para mudar a tela para o menu. */
            Login.getStage().close();
            MenuController.TelaMenu();
        } else {
            // Usando o Alert que eu criei na Classe MenuController eu informo o usuário o contexto do erro.
            MenuController.getErro().setHeaderText("Falha ao fazer o login!");
            MenuController.getErro().setContentText("Informações inseridas incorretas!");
            MenuController.getErro().showAndWait();
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // setOnMouseClicked é o que faz os botões funcionarem com um clique do mouse.
        btEntrar.setOnMouseClicked((MouseEvent e) -> {
            // Se o usuario clicar no botão vai inicializar esse metodo.
            VerificarLogin();
        });
        }
    }
