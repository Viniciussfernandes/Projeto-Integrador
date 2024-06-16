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
import static Services.Arquivo.CSVIn;
import Entities.Municipio;

/**
 * @author Willian Junior <willianjunior.c.f@gmail.com>
 * @date 30/05/2024
 * @brief Class CRUD
 */

public class CRUD {

   // Metodo para inserir as informações calculaveis
   public static void Create(){
       for (int i = 0; i < CSVIn.size(); i++) {
           if(Tratamento.CampoNull(Arquivo.CSVIn.get(i))){
           // Calculando com base no que tem no CSVIn
           double densidade = Municipio.Densidade(Arquivo.CSVIn.get(i).getPopulacao(), Arquivo.CSVIn.get(i).getArea());
           double PIBpC = Municipio.PIBpC(Arquivo.CSVIn.get(i).getPIBTotal(), Arquivo.CSVIn.get(i).getPopulacao());
           String ClassIDH = Municipio.classIDH(Arquivo.CSVIn.get(i).getIDHGeral());
           String ClassIDHE = Municipio.classIDH(Arquivo.CSVIn.get(i).getIDHEducacao());
           String ClassIDHL = Municipio.classIDH(Arquivo.CSVIn.get(i).getIDHLongevidade());
           
           // Uso os sets para colocar na lista
           Arquivo.CSVIn.get(i).setDensidade(densidade);
           Arquivo.CSVIn.get(i).setPIBpC(PIBpC);
           Arquivo.CSVIn.get(i).setClassIDHG(ClassIDH);
           Arquivo.CSVIn.get(i).setClassIDHE(ClassIDHE);
           Arquivo.CSVIn.get(i).setClassIDHL(ClassIDHL);
       }
   }
   }
  
   public static StringBuilder Reader(int i){
       StringBuilder Planilha = new StringBuilder(
                       CSVIn.get(i).getCodigoIBGE() + "; " + CSVIn.get(i).getNome() + "; " +
                       CSVIn.get(i).getMicroregiao() + "; " + CSVIn.get(i).getSigla() + "; " +
                       CSVIn.get(i).getRegiao() + "; " + String.format("%.2f", CSVIn.get(i).getArea()) + "; " +
                       CSVIn.get(i).getPopulacao() + "; " +
                       String.format("%.2f", CSVIn.get(i).getDensidade()) + "; " +
                       String.format("%.2f", CSVIn.get(i).getDomicilios()) + "; " +
                       String.format("%.2f", CSVIn.get(i).getPIBTotal()) + "; " +
                       String.format("%.2f", CSVIn.get(i).getPIBpC()) + "; " +
                       String.format("%.2f", CSVIn.get(i).getIDHGeral()) + "; " +
                       CSVIn.get(i).getClassIDHG() + "; " +
                       String.format("%.2f", CSVIn.get(i).getRendaMedia()) + "; " +
                       String.format("%.2f", CSVIn.get(i).getRendaNominal()) + "; " + 
                               CSVIn.get(i).getPEADia() + "; " +
                       String.format("%.2f", CSVIn.get(i).getIDHEducacao()) + "; " +
                       CSVIn.get(i).getClassIDHE() + "; " +
                       String.format("%.2f", CSVIn.get(i).getIDHLongevidade()) + "; " +
                       CSVIn.get(i).getClassIDHL() + "\n");
            return Planilha;
       }
   
   /** Todos os Updates seguem a mesma logica. Indicando o codigoIBGE para atualizar
    * uma informação especifica. Como cada construtor precisa de uma informação especifica
    * então separei em blocos especificos, tambem para conseguir atualizar um sem precisar dos
    * outros.
    */
   public static void UpdatePopulacao(int index, int pop, Historico hist){
       // Usando sets para atualizar a lista
       CSVIn.get(index).setPopulacao(pop);
       CSVIn.get(index).setDensidade(Municipio.Densidade(pop, CSVIn.get(index).getArea()));
       // Armazenando as informações da atualização
       hist.setUpdateData(hist.HoraAtual(),hist.DataFormato());
       hist.setUpdateTipo("População e Densidade");
   }
   
   public static void UpdateDomicilios(int index, double dom, Historico hist){
       CSVIn.get(index).setDomicilios(dom);
       hist.setUpdateData(hist.HoraAtual(),hist.DataFormato());
       hist.setUpdateTipo("Domicílios");
   }
   
   public static void UpdatePIBTotal(int index, double pib, Historico hist){
       CSVIn.get(index).setPIBTotal(pib);
       CSVIn.get(index).setPIBpC(Municipio.PIBpC(pib, CSVIn.get(index).getPopulacao()));
       hist.setUpdateData(hist.HoraAtual(),hist.DataFormato());
       hist.setUpdateTipo("PIBTotal e PIBpC");
   }
   
   public static void UpdateIDHG(int index, double idh, Historico hist){
       CSVIn.get(index).setIDHGeral(idh);
       CSVIn.get(index).setClassIDHG(Municipio.classIDH(idh));
       hist.setUpdateData(hist.HoraAtual(),hist.DataFormato());
       hist.setUpdateTipo("IDHG e Classificação");
   }
   
   public static void UpdateRendaMedia(int index, double rendM, Historico hist){
       CSVIn.get(index).setRendaMedia(rendM);
       hist.setUpdateData(hist.HoraAtual(),hist.DataFormato());
       hist.setUpdateTipo("Renda Media");
   }
   
   public static void UpdateRendaNominal(int index, double rendN, Historico hist){
       CSVIn.get(index).setRendaNominal(rendN);
       hist.setUpdateData(hist.HoraAtual(),hist.DataFormato());
       hist.setUpdateTipo("Renda Nominal");
   }
   
   public static void UpdatePEADia(int index, int pe, Historico hist){
       CSVIn.get(index).setPEADia(pe);
       hist.setUpdateData(hist.HoraAtual(),hist.DataFormato());
       hist.setUpdateTipo("PEA Dia");
   }
   
   public static void UpdateIDHE(int Index, double idh, Historico hist){
       CSVIn.get(Index).setIDHEducacao(idh);
       CSVIn.get(Index).setClassIDHE(Municipio.classIDH(idh));
       hist.setUpdateData(hist.HoraAtual(),hist.DataFormato());
       hist.setUpdateTipo("IDHE e Classificação");
   }
   
   public static void UpdateIDHL(int index, double idh, Historico hist){
       CSVIn.get(index).setIDHLongevidade(idh);
       CSVIn.get(index).setClassIDHL(Municipio.classIDH(idh));
       hist.setUpdateData(hist.HoraAtual(),hist.DataFormato());
       hist.setUpdateTipo("IDHL e Classificação");
   }
   
   // Metodo para deletar uma linha. Obs: O professor quer que não exclua o codigo IBGE e nome municipio
   public static void Delete(int index){
        CSVIn.get(index).setArea(null); 
        CSVIn.get(index).setPopulacao(null);
        CSVIn.get(index).setDomicilios(null);
        CSVIn.get(index).setPIBTotal(null);
        CSVIn.get(index).setIDHGeral(null);
        CSVIn.get(index).setRendaMedia(null);
        CSVIn.get(index).setRendaNominal(null);
        CSVIn.get(index).setPEADia(null);
        CSVIn.get(index).setIDHEducacao(null);
        CSVIn.get(index).setIDHLongevidade(null);
        
        CSVIn.get(index).setDensidade(null);
        CSVIn.get(index).setPIBpC(null);
        CSVIn.get(index).setClassIDHG(null);
        CSVIn.get(index).setClassIDHE(null);
        CSVIn.get(index).setClassIDHL(null);
   }  
}