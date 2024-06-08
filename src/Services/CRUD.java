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

package Services;

import Entities.Historico;
import Entities.Municipio;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Willian Junior <willianjunior.c.f@gmail.com>
 * @date 30/05/2024
 * @brief Class CRUD
 */

public class CRUD extends Municipio {

    // Lista para armazenar os dados das linhas
   protected static List<Municipio> CSVIn = new ArrayList<>();
   
   // Metodo para inserir as informações calculaveis
   public static void Create(){
       for (int i = 0; i < CSVIn.size(); i++) {
           // Calculando com base no que tem no CSVIn
           double densidade = Operacao.Densidade(CSVIn.get(i).getPopulacao(), CSVIn.get(i).getArea());
           double PIBpC = Operacao.PIBpC(CSVIn.get(i).getPIBTotal(), CSVIn.get(i).getPopulacao());
           String ClassIDH = Operacao.classIDH(CSVIn.get(i).getIDHGeral());
           String ClassIDHE = Operacao.classIDH(CSVIn.get(i).getIDHEducacao());
           String ClassIDHL = Operacao.classIDH(CSVIn.get(i).getIDHLongevidade());
           
           // Uso os sets para colocar na lista
           CSVIn.get(i).setDensidade(densidade);
           CSVIn.get(i).setPIBpC(PIBpC);
           CSVIn.get(i).setClassIDHG(ClassIDH);
           CSVIn.get(i).setClassIDHE(ClassIDHE);
           CSVIn.get(i).setClassIDHL(ClassIDHL);
       }
   }
   
  /**
   public static void Reader(){
       for(int i = 0; i < CSVIn.size(); i++){
           // Indeciso em como apresentar, quando fazer a tela, eu implemento
       }
   }*/
   
   /** Todos os Updates seguem a mesma logica. Indicando o codigoIBGE para atualizar
    * uma informação especifica. Como cada construtor precisa de uma informação especifica
    * então separei em blocos especificos, tambem para conseguir atualizar um sem precisar dos
    * outros.
    */
   public static void UpdatePopulacao(int Index, double pop, Historico hist){
       // Usando sets para atualizar a lista
       CSVIn.get(Index).setPopulacao(pop);
       CSVIn.get(Index).setDensidade(Operacao.Densidade(pop, CSVIn.get(Index).getArea()));
       // Armazenando as informações da atualização
       hist.setUpdateData(hist.getNow(),hist.getFmt());
       hist.setUpdateTipo("População e Densidade");
       hist.setUpdateValor(pop);
   }
   
   public static void UpdateDomicilios(int Index, double dom, Historico hist){
       CSVIn.get(Index).setDomicilios(dom);
       hist.setUpdateData(hist.getNow(),hist.getFmt());
       hist.setUpdateTipo("Domicílios");
       hist.setUpdateValor(dom);
   }
   
   public static void UpdatePIBTotal(int Index, double pib, Historico hist){
       CSVIn.get(Index).setPIBTotal(pib);
       CSVIn.get(Index).setPIBpC(Operacao.PIBpC(pib, CSVIn.get(Index).getPopulacao()));
       hist.setUpdateData(hist.getNow(),hist.getFmt());
       hist.setUpdateTipo("PIBTotal e PIBpC");
       hist.setUpdateValor(pib);
   }
   
   public static void UpdateIDHG(int Index, double idh, Historico hist){
       CSVIn.get(Index).setIDHGeral(idh);
       CSVIn.get(Index).setClassIDHG(Operacao.classIDH(idh));
       hist.setUpdateData(hist.getNow(),hist.getFmt());
       hist.setUpdateTipo("IDHG e Classificação");
       hist.setUpdateValor(idh);
   }
   
   public static void UpdateRendaMedia(int Index, double rendM, Historico hist){
       CSVIn.get(Index).setRendaMedia(rendM);
       hist.setUpdateData(hist.getNow(),hist.getFmt());
       hist.setUpdateTipo("Renda Media");
       hist.setUpdateValor(rendM);
   }
   
   public static void UpdateRendaNominal(int Index, double rendN, Historico hist){
       CSVIn.get(Index).setRendaNominal(rendN);
       hist.setUpdateData(hist.getNow(),hist.getFmt());
       hist.setUpdateTipo("Renda Nominal");
       hist.setUpdateValor(rendN);
   }
   
   public static void UpdatePEADia(int Index, double pe, Historico hist){
       CSVIn.get(Index).setPEADia(pe);
       hist.setUpdateData(hist.getNow(),hist.getFmt());
       hist.setUpdateTipo("PEA Dia");
       hist.setUpdateValor(pe);
   }
   
   public static void UpdateIDHE(int Index, double idh, Historico hist){
       CSVIn.get(Index).setIDHEducacao(idh);
       CSVIn.get(Index).setClassIDHE(Operacao.classIDH(idh));
       hist.setUpdateData(hist.getNow(),hist.getFmt());
       hist.setUpdateTipo("IDHE e Classificação");
       hist.setUpdateValor(idh);
   }
   
   public static void UpdateIDHL(int Index, double idh, Historico hist){
       CSVIn.get(Index).setIDHLongevidade(idh);
       CSVIn.get(Index).setClassIDHL(Operacao.classIDH(idh));
       hist.setUpdateData(hist.getNow(),hist.getFmt());
       hist.setUpdateTipo("IDHL e Classificação");
       hist.setUpdateValor(idh);
   }
   
   // Metodo para deletar uma linha
   public static void Delete(String municipio, Integer codigoIBGE){
       // Um simples loop com o if responsavel por achar a linha e exclui-la
       for(int i = 0; i < CSVIn.size(); i++){
            if (CSVIn.get(i).getCodigoIBGE().equals(codigoIBGE) || 
                    CSVIn.get(i).getNome().equals(municipio)){
                CSVIn.remove(i);
            }
       }
   }  
}