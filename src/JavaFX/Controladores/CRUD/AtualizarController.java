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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
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
    private static Alert UltimaAtualizacao = new Alert(Alert.AlertType.INFORMATION);

    public static Alert getUltimaAtualizacao() {
        return UltimaAtualizacao;
    }
    
    public void AtualizarDados(){
            int index = MenuController.PesquisarLinha(Pesquisa);
            Alert erro = new Alert(Alert.AlertType.ERROR);
            try{
            if(MenuController.validacaoespaco(Pesquisa, index, erro)){
                if(Arquivo.CSVIn.get(index).getPopulacao() == null){
                    erro.setTitle("Erro");
                    erro.setHeaderText("Informa��es inexistentes");
                    erro.setContentText("Por favor escolha outra municipio");
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
                                erro.setContentText("Informa��es fornecidas incorretas");
                                erro.show();
                                seguir = false;
                                break;
                            }
                        }
                        
                        if(seguir){
                            Historico hist = new Historico(LoginController.getPerfil().getNome(), LoginController.getPerfil().getCPF());

                            if(InfoNaoNulo.size() == 0){
                                erro.setTitle("Erro");
                                erro.setHeaderText("Erro ao ler os dados");
                                erro.setContentText("Por favor preencha os campos");
                                erro.show();
                            } else {
                            for(TextField manipulacao : InfoNaoNulo){
                                if(manipulacao.equals(Populacao)){
                                    int populacao = Integer.parseInt(manipulacao.getText());
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
                                    int pea = Integer.parseInt(manipulacao.getText());
                                    UpdatePEADia(index, pea, hist);
                                }
                            }
                            
                            StringBuilder mensagem = new StringBuilder("As seguintes informa��es foram atualizadas:\n");
                            for(TextField tf : InfoNaoNulo){
                                mensagem.append(tf.getId() + ": " + tf.getText() + "\n");
                            } mensagem.append("\nResponsavel:\n" + hist.getNome() + "\n" + hist.getCPF() + "\n" + hist.getUpdateData());
                            UltimaAtualizacao.setTitle("Atualiza��o");
                            UltimaAtualizacao.setHeaderText("Notas da atualiza��o");
                            UltimaAtualizacao.setContentText(mensagem.toString());
                            UltimaAtualizacao.show();
                        }
                        }
                }
            }
            } catch (NumberFormatException e){
                MenuController.erro.setTitle("Erro");
                MenuController.erro.setHeaderText("Erro ao ler os dados");
                MenuController.erro.setContentText("O valor excede o limite permitido");
                MenuController.erro.show();
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


