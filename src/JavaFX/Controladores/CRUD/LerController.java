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

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */

import Entities.Municipio;
import JavaFX.Controladores.MenuController;
import JavaFX.Main.CRUD.Atualizar;
import JavaFX.Main.CRUD.Ler;
import Services.Arquivo;
import Services.CRUD;
import Services.Tratamento;
import Services.Ujeverson;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.util.StringConverter;
import javafx.util.Callback;

/**
 *
 * @author Davi Erick
 * tabela
 * colCodigo
 * colMunicipios
 * colMicrorregiao
 * colEstado
 * colRegiaoGeografica
 * colArea
 * colPopulacao
 * colDomicilios
 * colPibTotal
 * colIDH
 * colRendaMedia
 * colRendaNominal
 * colPea
 * colIDHEducacao
 * colIDHLongevidade
 */
public class LerController implements Initializable {
    
    @FXML private TableView<Municipio> tabela = new TableView<Municipio>();
    
    @FXML private TableColumn<Municipio, Integer> colCodigo = new TableColumn<>();
    @FXML private TableColumn<Municipio, String> colMunicipios = new TableColumn<>();
    @FXML private TableColumn<Municipio, String> colMicrorregiao = new TableColumn<>();
    @FXML private TableColumn<Municipio, String> colEstado = new TableColumn<>();
    @FXML private TableColumn<Municipio, String> colRegiaoGeografica = new TableColumn<>();
    @FXML private TableColumn<Municipio, Double> colArea = new TableColumn<>();
    @FXML private TableColumn<Municipio, Integer> colPopulacao = new TableColumn<>();
    @FXML private TableColumn<Municipio, Double> colDensidade = new TableColumn<>();
    @FXML private TableColumn<Municipio, Double> colDomicilios = new TableColumn<>();
    @FXML private TableColumn<Municipio, Double> colPibTotal = new TableColumn<>();
    @FXML private TableColumn<Municipio, Double> colPIBpC = new TableColumn<>();
    @FXML private TableColumn<Municipio, Double> colIDH = new TableColumn<>();
    @FXML private TableColumn<Municipio, String> colClassIDH = new TableColumn<>();
    @FXML private TableColumn<Municipio, Double> colRendaMedia = new TableColumn<>();
    @FXML private TableColumn<Municipio, Double> colRendaNominal = new TableColumn<>();
    @FXML private TableColumn<Municipio, Integer> colPea = new TableColumn<>();
    @FXML private TableColumn<Municipio, Double> colIDHEducacao = new TableColumn<>();
    @FXML private TableColumn<Municipio, String> colClassIDHE = new TableColumn<>();
    @FXML private TableColumn<Municipio, Double> colIDHLongevidade = new TableColumn<>();
    @FXML private TableColumn<Municipio, String> colClassIDHL = new TableColumn<>();
    
    @FXML private TextField Busca;        
    @FXML private Button btBuscar;
    @FXML private Button btVoltar;
    @FXML private Button btMelhorPIB;
    @FXML private Button btPiorPIB;
    @FXML private Button btMelhorPIBPiorIDHE;
    @FXML private Button btUltimaAtualizacao;
    @FXML private Button btDesfazerAtualizacoes;
    
    public void UltimaAtualizacao(){
        if(AtualizarController.getUltimaAtualizacao().getTitle().isEmpty() || AtualizarController.getUltimaAtualizacao().getHeaderText().isEmpty() ||
                AtualizarController.getUltimaAtualizacao().getContentText().isEmpty()){
            MenuController.erro.setHeaderText("Informações inexistente");
            MenuController.erro.setContentText("Ainda não foi utilizado o recurso de Atualização");
            MenuController.erro.show();
        } else AtualizarController.getUltimaAtualizacao().show();
    }
    
    public void DesfazerAtualizacoes(){
            MenuController.conf.setTitle("Confirmação");
            MenuController.conf.setHeaderText("Exemplo");
            MenuController.conf.setContentText("Você deseja confirmar essa escolha?");
            
            ButtonType btSim = new ButtonType("Sim");
            ButtonType btNao = new ButtonType("Não");
            
            MenuController.conf.getButtonTypes().setAll(btSim, btNao);
            
            Optional<ButtonType> result = MenuController.conf.showAndWait();
            
            if(result.isPresent() && result.get() == btSim){
                try {
                    // Aberto a discusão se devo deixar a ultima atualização ou se removo
                    AtualizarController.getUltimaAtualizacao().setContentText("");
                    Arquivo.CSVIn.clear();
                    Arquivo.In();
                    PesquisarMunicipio();
                    Alert info = new Alert(Alert.AlertType.INFORMATION);
                    info.setTitle("Operação deletar");
                    info.setHeaderText("Notas da remoção");
                    info.setContentText("Todos os dados foram restaurados");
                    info.show();
                } catch (ParseException ex) {
                    Logger.getLogger(LerController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
    }
    
    public void PesquisarMunicipio(){
        if(Busca.getText().equals("")){
            preencherTabela(Tratamento.FormatarLista(Arquivo.getCSVIn()));
        } else {
            List<Municipio> res = new ArrayList<>();
            for(Municipio m : Arquivo.getCSVIn()){
                if(m.getNome().equals(Busca.getText()) || (m.getCodigoIBGE().toString()).equals(Busca.getText()))
                    res.add(m);
            }
            preencherTabela(res);
        }
    }
    
    public void Ujeverson(int option){
        int CampoNull = 0;
        for(Municipio m : Arquivo.getCSVIn()){
            if(Tratamento.CampoNull(m)){
                CampoNull++;
            }
        }
        if(CampoNull == 246){
                MenuController.erro.setTitle("Erro");
                MenuController.erro.setHeaderText("Informações inexistentes");
                MenuController.erro.setContentText("Por favor selecione a operação Criar primeiro");
                MenuController.erro.show();
        } else {
            switch(option){
                case 1->{
                    List<Municipio> MelhorPIBpC = new ArrayList<>();
                    MelhorPIBpC.add(Arquivo.CSVIn.get(Ujeverson.MelhorPIBpC()));
                    preencherTabela(MelhorPIBpC);
                }
                case 2->{
                    List<Municipio> PiorPIBpC = new ArrayList<>();
                    PiorPIBpC.add(Arquivo.CSVIn.get(Ujeverson.PiorPIBpC()));
                    preencherTabela(PiorPIBpC);
                }
                case 3->{
                    List<Municipio> MelhorPIBpC_PiorIDHE = new ArrayList<>();
                    MelhorPIBpC_PiorIDHE.add(Arquivo.getCSVIn().get(Ujeverson.MelhorPIBpC_PiorIDHE()));
                    preencherTabela(MelhorPIBpC_PiorIDHE);
                }
            }
        }
    }
    
    public void preencherTabela(List<Municipio> Tabela){
        ObservableList<Municipio> dados = FXCollections.observableArrayList(Tabela);
        
        colCodigo.setCellValueFactory(new PropertyValueFactory<Municipio, Integer>("codigoIBGE"));
        colMunicipios.setCellValueFactory(new PropertyValueFactory<Municipio, String>("nome"));
        colMicrorregiao.setCellValueFactory(new PropertyValueFactory<Municipio, String>("microregiao"));
        colEstado.setCellValueFactory(new PropertyValueFactory<Municipio, String>("sigla"));
        colRegiaoGeografica.setCellValueFactory(new PropertyValueFactory<Municipio, String>("regiao"));
        colArea.setCellValueFactory(new PropertyValueFactory<Municipio, Double>("area"));
        colPopulacao.setCellValueFactory(new PropertyValueFactory<Municipio, Integer>("populacao"));
        colDensidade.setCellValueFactory(new PropertyValueFactory<Municipio, Double>("Densidade"));
        colDomicilios.setCellValueFactory(new PropertyValueFactory<Municipio, Double>("domicilios"));
        colPibTotal.setCellValueFactory(new PropertyValueFactory<Municipio, Double>("PIBTotal"));
        colPIBpC.setCellValueFactory(new PropertyValueFactory<Municipio, Double>("PIBpC"));
        colIDH.setCellValueFactory(new PropertyValueFactory<Municipio, Double>("IDHGeral"));
        colClassIDH.setCellValueFactory(new PropertyValueFactory<Municipio, String>("ClassIDHG"));
        colRendaMedia.setCellValueFactory(new PropertyValueFactory<Municipio, Double>("RendaMedia"));
        colRendaNominal.setCellValueFactory(new PropertyValueFactory<Municipio, Double>("RendaNominal"));
        colPea.setCellValueFactory(new PropertyValueFactory<Municipio, Integer>("PEADia"));
        colIDHEducacao.setCellValueFactory(new PropertyValueFactory<Municipio, Double>("IDHEducacao"));
        colClassIDHE.setCellValueFactory(new PropertyValueFactory<Municipio, String>("ClassIDHE"));
        colIDHLongevidade.setCellValueFactory(new PropertyValueFactory<Municipio, Double>("IDHLongevidade"));
        colClassIDHL.setCellValueFactory(new PropertyValueFactory<Municipio, String>("ClassIDHL"));
        
        tabela.setItems(dados);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            btBuscar.setOnMouseClicked((MouseEvent e)->{
                PesquisarMunicipio();
            });
            btVoltar.setOnMouseClicked((MouseEvent e)->{
                Ler.getStage().close();
                MenuController.TelaMenu();
            });
            btMelhorPIB.setOnMouseClicked((MouseEvent e)->{
                Ujeverson(1);
            });
            btPiorPIB.setOnMouseClicked((MouseEvent e)->{
                Ujeverson(2);
            });
            btMelhorPIBPiorIDHE.setOnMouseClicked((MouseEvent e)->{
                Ujeverson(3);
            });
            btDesfazerAtualizacoes.setOnMouseClicked((MouseEvent e)->{
                DesfazerAtualizacoes();
            });
            btUltimaAtualizacao.setOnMouseClicked((MouseEvent e)->{
                UltimaAtualizacao();
            });
            PesquisarMunicipio();
    }
    
    
    
}