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

/**
 *
 * @author Willian Junior <willianjunior.c.f@gmail.com>
 * @date 12/06/2024
 * @brief Class AtualizarController
 */

import Entities.Historico;
import JavaFX.Controladores.LoginController;
import JavaFX.Controladores.MenuController;
import JavaFX.Main.CRUD.Atualizar;
import Services.Arquivo;
import static Services.CRUD.*;
import Services.Tratamento;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class AtualizarController implements Initializable {
    
    @FXML private TextField Pesquisa;
    @FXML private TextField Populacao;
    @FXML private TextField Domicilios;
    @FXML private TextField PIBTotal;
    @FXML private TextField IDHG;
    @FXML private TextField RendaM;
    @FXML private TextField RendaN;
    @FXML private TextField IDHE;
    @FXML private TextField IDHL;
    @FXML private TextField PEADia;
    @FXML private Button btAtualizar;
    @FXML private Button btVoltar;
    
    public void AtualizarDados(){
        try {
            Arquivo.In();
            
            Alert erro = new Alert(Alert.AlertType.ERROR);
            if(Pesquisa.getText() == null || Pesquisa.getText().isEmpty()){
                erro.setTitle("Erro");
                erro.setHeaderText("Erro ao tentar acessar a linha do csv");
                erro.setContentText("Por favor preencha o espaço codigo IBGE ou municipio");
                erro.show();
            } else {
                
                int index = MenuController.PesquisarLinha(Pesquisa);
                if(index == -1){
                    erro.setTitle("Erro");
                    erro.setHeaderText("Erro ao tentar acessar a linha do csv");
                    erro.setContentText("Não foi encontrado esse municipio");
                    erro.show();
                } else {
                    
                    boolean seguir = true;
                    
                        TextField[] Vet_Tex = {Populacao, Domicilios, PIBTotal, IDHG, RendaM, RendaN, IDHE, IDHL, PEADia};
                        List<TextField> InfoNaoNulo = new ArrayList<>();
                        for(TextField tf : Vet_Tex){
                            if(tf.getText() != null && !tf.getText().isEmpty() && Tratamento.Numerico(tf.getText())){
                                InfoNaoNulo.add(tf);
                                seguir = true;
                            } else if(!Tratamento.Numerico(tf.getText()) && !tf.getText().isEmpty()){
                                erro.setTitle("Erro");
                                erro.setHeaderText("Erro ao ler os dados");
                                erro.setContentText("Informações fornecidas incorretas");
                                erro.show();
                                seguir = false;
                                break;
                            }
                        }
                        if(InfoNaoNulo.size() == 0){
                            erro.setTitle("Erro");
                            erro.setHeaderText("Erro ao ler os dados");
                            erro.setContentText("Por favor preencha os campos");
                            erro.show();
                        } else {
                        Historico hist = new Historico("Joao", "08090478107");
                        //Historico hist = new Historico(LoginController.getPerfil().getNome(), LoginController.getPerfil().getCPF());
                        if(seguir){
                            for(TextField manipulacao : InfoNaoNulo){
                                if(manipulacao.equals(Populacao)){
                                    Double populacao = Double.parseDouble(manipulacao.getText());
                                    UpdatePopulacao(index, populacao, hist);
                                }
                                else if(manipulacao.equals(Domicilios)){
                                    Double domicilios = Double.parseDouble(manipulacao.getText());
                                    UpdateDomicilios(index, domicilios, hist);
                                }
                                else if(manipulacao.equals(PIBTotal)){
                                    Double pib = Double.parseDouble(manipulacao.getText());
                                    UpdatePIBTotal(index, pib, hist);
                                }
                                else if(manipulacao.equals(IDHG)){
                                    Double idhg = Double.parseDouble(manipulacao.getText());
                                    UpdateIDHG(index, idhg, hist);
                                }
                                else if(manipulacao.equals(RendaM)){
                                    Double rendm = Double.parseDouble(manipulacao.getText());
                                    UpdateRendaMedia(index, rendm, hist);
                                }
                                else if(manipulacao.equals(RendaN)){
                                    Double rendn = Double.parseDouble(manipulacao.getText());
                                    UpdateRendaNominal(index, rendn, hist);
                                }
                                else if(manipulacao.equals(IDHE)){
                                    Double idhe = Double.parseDouble(manipulacao.getText());
                                    UpdateIDHE(index, idhe, hist);
                                }
                                else if(manipulacao.equals(IDHL)){
                                    Double idhl = Double.parseDouble(manipulacao.getText());
                                    UpdateIDHL(index, idhl, hist);
                                }
                                else if(manipulacao.equals(PEADia)){
                                    Double pea = Double.parseDouble(manipulacao.getText());
                                    UpdatePEADia(index, pea, hist);
                                }
                            }
                            
                            StringBuilder mensagem = new StringBuilder("As seguintes informações foram atualizadas:\n");
                            for(TextField tf : InfoNaoNulo){
                                mensagem.append(tf.getId() + ": " + tf.getText() + "\n");
                            } mensagem.append("\nResponsavel:\n" + hist.getNome() + "\n" + hist.getCPF() + "\n" + hist.getUpdateData());
                            Alert inf = new Alert(Alert.AlertType.INFORMATION);
                            inf.setTitle("Atualização");
                            inf.setHeaderText("Notas da atualização");
                            inf.setContentText(mensagem.toString());
                            inf.show();
                        }
                        }
                }
            }
        } catch (ParseException ex) {
            Logger.getLogger(AtualizarController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btAtualizar.setOnMouseClicked((MouseEvent e)->{
            AtualizarDados();
        });
        btVoltar.setOnMouseClicked((MouseEvent e)->{
            Atualizar.getStage().close();
            MenuController.TelaMenu();
        });
        }
    }


