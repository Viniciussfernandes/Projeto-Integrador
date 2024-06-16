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

import Entities.Municipio;
import JavaFX.Controladores.MenuController;
import JavaFX.Main.CRUD.Ler;
import Services.Arquivo;
import Services.Tratamento;
import Services.Ujeverson;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * @author Willian Junior <willianjunior.c.f@gmail.com>
 * @author Davi Erick <davierick999@gmail.com>
 * @date 07/06/2024
 * @brief Class Arquivos
 */

public class LerController implements Initializable {
    
    // Essas são as variaveis responsaveis pelo funcionando da tabela, começando pela propria tabela.
    @FXML private TableView<Municipio> tabela = new TableView<Municipio>();
    
    // Essa são as colunas tabelas, cada com o tipo Municipio seguido pelo tipo da variavel.
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
    
    @FXML private TextField busca;        
    @FXML private Button btBuscar;
    @FXML private Button btVoltar;
    @FXML private Button btMelhorPIB;
    @FXML private Button btPiorPIB;
    @FXML private Button btMelhorPIBPiorIDHE;
    @FXML private Button btUltimaAtualizacao;
    @FXML private Button btDesfazerAtualizacoes;
    
    // Aqui aparece o pop-up sobre as ultimas atualizações.
    public void UltimaAtualizacao(){
        // Uma verificação se a atualização se foi utilizada antes.
        if(AtualizarController.getUltimaAtualizacao().getHeaderText().isEmpty() || AtualizarController.getUltimaAtualizacao().getContentText().isEmpty()){
            // Se sim vai aparecer uma mensagem de erro.
            AtualizarController.getUltimaAtualizacao().setHeaderText("Informações inexistente");
            AtualizarController.getUltimaAtualizacao().setContentText("Ainda não foi utilizado o recurso de Atualização");
            AtualizarController.getUltimaAtualizacao().show();
            
        } else {
            AtualizarController.getUltimaAtualizacao().show();
        }
    }
    
    // Metodo para desfazer todas as alterações
    public void DesfazerAtualizacoes(){
        // Da mesma forma que foi com o botão de delete
        MenuController.getConfirmacao().setHeaderText("Exemplo");
        MenuController.getConfirmacao().setContentText("Você deseja confirmar essa escolha?");
            
        ButtonType btSim = new ButtonType("Sim");
        ButtonType btNao = new ButtonType("Não");
            
        MenuController.getConfirmacao().getButtonTypes().setAll(btSim, btNao);
            
        Optional<ButtonType> result = MenuController.getConfirmacao().showAndWait();
            
        if(result.isPresent() && result.get() == btSim){
            try {
                // Aberto a discusão se devo deixar a ultima atualização ou se removo.
                AtualizarController.getUltimaAtualizacao().setContentText("");
                
                // Limpar a lista com .clear().
                Arquivo.getCSVIn().clear();
                
                // Chamo o metodo Arquivo.In() denovo
                Arquivo.In();
                
                // Chamo o metodo PesquisarMunicipio() para ja mostrar ao usuario a nova planilha.
                PesquisarMunicipio();
                
                // Pop-up.
                MenuController.getInformativo().setHeaderText("Notas da remoção");
                MenuController.getInformativo().setContentText("Todos os dados foram restaurados");
                MenuController.getInformativo().show();
                } catch (ParseException ex) {
                    Logger.getLogger(LerController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
    }
    
    // Responsavel por verificar o TextField de busca.
    public void PesquisarMunicipio(){
        // Uma verificação se o TextField Busca está vazio.
        if(busca.getText().equals("")){
            // Se estiver vazio chama o metodo preencherTabela com todos os dados.
            preencherTabela(Tratamento.FormatarLista(Arquivo.getCSVIn()));
        } else {
            // Se tiver alguma coisa, eu crio nova lista onde estara o resultado da busca.
            List<Municipio> resultado = new ArrayList<>();
            for(Municipio m : Arquivo.getCSVIn()){
                // vai varrer a lista em busca do nome do municipio ou codigoIBGE digitado
                if(m.getNome().equals(busca.getText()) || (m.getCodigoIBGE().toString()).equals(busca.getText()))
                    resultado.add(m);
            }
            preencherTabela(resultado);
        }
    }
    
    public void Ujeverson(int option){
        // Inicializo uma variavel chamada de CampoNull, onde vou fazer a contagem de quantos campos null tem na lista.
        int qtd_CampoNull = 0;
        // for-each para percorrer a lista atras de algum campo null.
        for(Municipio m : Arquivo.getCSVIn()){
            if(Tratamento.CampoNull(m)){
                // Se estiver null ele incrementa a variavel.
                qtd_CampoNull++;
            }
        }
        /** Uma verificação da contagem de campo null, se tiver a mesma quantidade do que o tamanho da lista, quer dizer 
         * que o usuario não fez a operação create e informo através do alert para o usuario fazer isso primeiro. */
        if(qtd_CampoNull == Arquivo.getCSVIn().size()){
                MenuController.getErro().setHeaderText("Informações inexistentes");
                MenuController.getErro().setContentText("Por favor selecione a operação Criar primeiro");
                MenuController.getErro().show();
        } else {
            // Passando pela verificação e fornecido a opção de qual operação, passamos para um switch-case.
            switch(option){
                case 1->{
                    // Crio uma nova lista que vai adicionar qual vai ser o resultado da busca.
                    List<Municipio> MelhorPIBpC = new ArrayList<>();
                    MelhorPIBpC.add(Arquivo.getCSVIn().get(Ujeverson.MelhorPIBpC()));
                    // Chamo o preencherTabela() com a lista que foi encontrada.
                    preencherTabela(MelhorPIBpC);
                }
                // Esses da mesma forma.
                case 2->{
                    List<Municipio> PiorPIBpC = new ArrayList<>();
                    PiorPIBpC.add(Arquivo.getCSVIn().get(Ujeverson.PiorPIBpC()));
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
        /** ObservableList representa uma lista cujas alterações podem ser observadas.
         * FXCollections.observableArrayList cria uma nova instânia na ObservableList.
         * A Tabela, do tipo Lista<Municipio>, esta sendo responsabel por inicializar essa nova instancia */
        ObservableList<Municipio> dados = FXCollections.observableArrayList(Tabela);
        
        /** colCodigo representa a coluna que foi inicializada anteriormente.
         * setCellValueFactory define como os valores serão extraídos e exibidos na coluna.
         * new PropertyValueFactory cria um ObservableValue para ser usado pelas células/variaveis da coluna.
         * <Municipio, Integer> especifica para o ValueFactory qual o tipo do objeto e qual o tipo da variavel.
         * ("codigoIBGE") especifica qual o nome que a ValueFactory deve procurar para achar o valor. */
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
        
        // Define os itens que a tablea exibirá.
        tabela.setItems(dados);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // MouseEvent para a barra de pesquisa
        btBuscar.setOnMouseClicked((MouseEvent e)->{
            PesquisarMunicipio();
        });
        
        // MouseEvent para voltar a tela de menu
        btVoltar.setOnMouseClicked((MouseEvent e)->{
            Ler.getStage().close();
            MenuController.TelaMenu();
        });
        
        // MouseEvent para aparecer o municipio com o melhor PIBpC
        btMelhorPIB.setOnMouseClicked((MouseEvent e)->{
            Ujeverson(1);
        });
        
        // MouseEvent para aparecer o municipio com o pior PIBpC
        btPiorPIB.setOnMouseClicked((MouseEvent e)->{
            Ujeverson(2);
        });
        
        // MouseEvent para aparecer o municipio com o melhor PIBpC com o pior IDH Educação
        btMelhorPIBPiorIDHE.setOnMouseClicked((MouseEvent e)->{
            Ujeverson(3);
        });
        
        // MouseEvent para defazer as mudanças
        btDesfazerAtualizacoes.setOnMouseClicked((MouseEvent e)->{
            DesfazerAtualizacoes();
        });
        
        // MouseEvent para aparecer o pop-up sobre as ultimas atualizações
        btUltimaAtualizacao.setOnMouseClicked((MouseEvent e)->{
            UltimaAtualizacao();
        });
        
        // Inicializar com os dados na tabela.
        PesquisarMunicipio();
    }
    
    
    
}