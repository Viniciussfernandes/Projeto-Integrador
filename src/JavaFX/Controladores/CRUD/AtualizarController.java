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

/**
 * @author Willian Junior <willianjunior.c.f@gmail.com>
 * @date 07/06/2024
 * @brief Class Arquivos
 */

public class AtualizarController implements Initializable {
    
    // Responsavel por receber o codigo IBGE ou nome do Municipio
    @FXML private TextField pesquisa;
    
    // Responsavel por receber os valores da atualiza��o
    @FXML private TextField populacao;
    @FXML private TextField domicilios;
    @FXML private TextField PIBTotal;
    @FXML private TextField IDHG;
    @FXML private TextField rendaM;
    @FXML private TextField rendaN;
    @FXML private TextField IDHE;
    @FXML private TextField IDHL;
    @FXML private TextField PEADia;
    
    // Bot�es
    @FXML private Button btAtualizar;
    @FXML private Button btVoltar;
    
    // Onde eu armazeno a ultima atualiza��o
    private static Alert ultimaAtualizacao = new Alert(Alert.AlertType.INFORMATION);

    public static Alert getUltimaAtualizacao() {
        ultimaAtualizacao.setTitle("Atualiza��o");
        return ultimaAtualizacao;
    }
    
    // Metodo para atualizar.
    public void AtualizarDados(){
        // Inicializo a variavel index com o metodo MenuController.PesquisarLinha(), onde vai retorna o numero da linha.
        int index = MenuController.PesquisarLinha(pesquisa);
            try{
                //Aqui eu fa�o uma verifica��o se o TextField fornecido � nulo ou o nome do muncicipio ou codigo � inexistente.
                if(MenuController.VerificarTextFieldPesquisa(pesquisa, index)){
                    // Outra verifica��o se as informa��es do municipio foram apagadas.
                    if(Tratamento.CampoNull(Arquivo.getCSVIn().get(index))){
                        MenuController.getErro().setHeaderText("Informa��es inexistentes!");
                        MenuController.getErro().setContentText("Por favor escolha outra municipio!");
                        MenuController.getErro().showAndWait();
                    } else {
                        // Passando pelas verifica��es, crio uma variavel boolean para uma condicional futura.
                        boolean seguir = true;
                        
                        // Separo os dados fornecidos nos campos de TextFields
                        TextField[] Vet_Tex = {populacao, domicilios, PIBTotal, IDHG, rendaM, rendaN, IDHE, IDHL, PEADia};
                        
                        // Crio uma lista onde ser�o armazenadas os campos que foram preenchidos.
                        List<TextField> InfoNaoNulo = new ArrayList<>();
                        
                        // Um for-each para preencher a lista de campos que n�o est�o vazios ou null
                        for(TextField tf : Vet_Tex){
                            /** Nessa verifica��o, confiro se o valor fornecido � nulo, se esta vazio, utilizo a nega��o !, pois quando
                             * utilizo .isEmpty() ele vai retorna true se estiver vazio, o que eu quero � o contrario, que n�o esteja 
                             * vazio. Alem do Tratamento.Numerico que vai verificar os numero fornecidos de acordo com o padr�o estabelecido.*/
                            if(tf.getText() != null && !tf.getText().isEmpty() && Tratamento.Numerico(tf.getText())){
                                InfoNaoNulo.add(tf);
                                // Caso passe pela verifica��o eu coloco a variavel boolean como true
                                seguir = true;
                                // Outra verifica��o para caso o usuario digite alguma coisa, mas est� incorreta.
                            } else if(!Tratamento.Numerico(tf.getText()) && !tf.getText().isEmpty()){
                                MenuController.getErro().setHeaderText("Erro ao ler os dados!");
                                MenuController.getErro().setContentText("Informa��es fornecidas incorretas!");
                                MenuController.getErro().showAndWait();
                                // Caso ele n�o passe pela verifica��o eu saio do loop e coloco a variavel boolean como false para n�o seguir.
                                seguir = false;
                                break;
                            }
                        }
                        
                        // Verifica��o se esta tudo certo com os dados fornecidos.
                        if(seguir){
                            
                            // Instancio a classe Historico para pegar as informa��es do usuario que esta alterando.
                            Historico hist = new Historico(LoginController.getLogin().getNome(), LoginController.getLogin().getCPF());

                            // Verifica��o se a Lista esta vazia
                            if(InfoNaoNulo.size() == 0){
                                MenuController.getErro().setHeaderText("Erro ao ler os dados!");
                                MenuController.getErro().setContentText("Por favor preencha os campos!");
                                MenuController.getErro().showAndWait();
                            } else {
                                // for-each para mudar os dados da lista.
                                for(TextField manipulacao : InfoNaoNulo){
                                    // Verificar qual campo esta lidando. Logo em seguida chama o metodo de update.
                                    if(manipulacao.equals(populacao)){
                                        int populacao = Integer.parseInt(manipulacao.getText());
                                        UpdatePopulacao(index, populacao);
                                    }
                                    else if(manipulacao.equals(domicilios)){
                                        Double domicilios = Double.parseDouble(manipulacao.getText());
                                        UpdateDomicilios(index, domicilios);
                                    }
                                    else if(manipulacao.equals(PIBTotal)){
                                        Double pib = Double.parseDouble(manipulacao.getText());
                                        UpdatePIBTotal(index, pib);
                                    }
                                    else if(manipulacao.equals(IDHG)){
                                        Double idhg = Double.parseDouble(manipulacao.getText());
                                        UpdateIDHG(index, idhg);
                                    }   
                                    else if(manipulacao.equals(rendaM)){
                                        Double rendm = Double.parseDouble(manipulacao.getText());
                                        UpdateRendaMedia(index, rendm);
                                    }
                                    else if(manipulacao.equals(rendaN)){
                                        Double rendn = Double.parseDouble(manipulacao.getText());
                                        UpdateRendaNominal(index, rendn);
                                    }
                                    else if(manipulacao.equals(IDHE)){
                                        Double idhe = Double.parseDouble(manipulacao.getText());
                                        UpdateIDHE(index, idhe);
                                    }
                                    else if(manipulacao.equals(IDHL)){
                                        Double idhl = Double.parseDouble(manipulacao.getText());
                                        UpdateIDHL(index, idhl);
                                    }
                                    else if(manipulacao.equals(PEADia)){
                                        int pea = Integer.parseInt(manipulacao.getText());
                                        UpdatePEADia(index, pea);
                                    }
                                }
                            
                                // Um StringBuilder por conta de poder incremetar a String sem alterar o conteudo.
                                StringBuilder mensagem = new StringBuilder("As seguintes informa��es foram atualizadas:\n");
                                for(TextField tf : InfoNaoNulo){
                                    mensagem.append(tf.getId() + ": " + tf.getText() + "\n");
                                } mensagem.append("\nResponsavel:" + 
                                        "\nNome: " + hist.getNome() + 
                                        "\nCPF: " + Tratamento.MascararCPF(hist.getCPF()) + 
                                        "\nData e hora: " + hist.getUpdateData());
                                ultimaAtualizacao.setHeaderText("Notas da atualiza��o");
                                // Transformo o StringBuilder de volta para String e coloco no ContentText().
                                ultimaAtualizacao.setContentText(mensagem.toString());
                                ultimaAtualizacao.showAndWait();
                        }
                    }
                }
            }
            } catch (NumberFormatException e){
                MenuController.getErro().setHeaderText("Erro ao ler os dados!");
                MenuController.getErro().setContentText("O valor excede o limite permitido!");
                MenuController.getErro().showAndWait();
            }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // MouseEvent para atualizar os dados.
        btAtualizar.setOnMouseClicked((MouseEvent e)->{
            AtualizarDados();
        });
        
        // MouseEvent para voltar ao menu.
        btVoltar.setOnMouseClicked((MouseEvent e)->{
            Atualizar.getStage().close();
            MenuController.TelaMenu();
        });
        }
    }


