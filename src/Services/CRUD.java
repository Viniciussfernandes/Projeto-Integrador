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
           // Calculando com base no que tem no CSVIn
           double densidade = Municipio.Densidade(CSVIn.get(i).getPopulacao(), CSVIn.get(i).getArea());
           double PIBpC = Municipio.PIBpC(CSVIn.get(i).getPIBTotal(), CSVIn.get(i).getPopulacao());
           String ClassIDH = Municipio.classIDH(CSVIn.get(i).getIDHGeral());
           String ClassIDHE = Municipio.classIDH(CSVIn.get(i).getIDHEducacao());
           String ClassIDHL = Municipio.classIDH(CSVIn.get(i).getIDHLongevidade());
           
           // Uso os sets para colocar na lista
           CSVIn.get(i).setDensidade(densidade);
           CSVIn.get(i).setPIBpC(PIBpC);
           CSVIn.get(i).setClassIDHG(ClassIDH);
           CSVIn.get(i).setClassIDHE(ClassIDHE);
           CSVIn.get(i).setClassIDHL(ClassIDHL);
       }
   }
   
  
   public static String Reader(int i){
            return CSVIn.get(i).getCodigoIBGE() + "; " + CSVIn.get(i).getNome() + "; " +
                   CSVIn.get(i).getMicroregiao() + "; " + CSVIn.get(i).getSigla() + "; " +
                   CSVIn.get(i).getRegiao() + "; " + String.format("%.2f", CSVIn.get(i).getArea()) + "; " +
                   String.format("%.2f", CSVIn.get(i).getPopulacao()) + "; " +
                   String.format("%.2f", CSVIn.get(i).getDensidade()) + "; " +
                   String.format("%.2f", CSVIn.get(i).getDomicilios()) + "; " +
                   String.format("%.2f", CSVIn.get(i).getPIBTotal()) + "; " +
                   String.format("%.2f", CSVIn.get(i).getPIBpC()) + "; " +
                       String.format("%.2f", CSVIn.get(i).getIDHGeral()) + "; " +
                       CSVIn.get(i).getClassIDHG() + "; " +
                       String.format("%.2f", CSVIn.get(i).getRendaMedia()) + "; " +
                       String.format("%.2f", CSVIn.get(i).getRendaNominal()) + "; " +
                       String.format("%.2f", CSVIn.get(i).getPEADia()) + "; " +
                       String.format("%.2f", CSVIn.get(i).getIDHEducacao()) + "; " +
                       CSVIn.get(i).getClassIDHE() + "; " +
                       String.format("%.2f", CSVIn.get(i).getIDHLongevidade()) + "; " +
                       CSVIn.get(i).getClassIDHL();
       }
   
   /** Todos os Updates seguem a mesma logica. Indicando o codigoIBGE para atualizar
    * uma informação especifica. Como cada construtor precisa de uma informação especifica
    * então separei em blocos especificos, tambem para conseguir atualizar um sem precisar dos
    * outros.
    */
   public static void UpdatePopulacao(int index, double pop, Historico hist){
       // Usando sets para atualizar a lista
       CSVIn.get(index).setPopulacao(pop);
       CSVIn.get(index).setDensidade(Municipio.Densidade(pop, CSVIn.get(index).getArea()));
       // Armazenando as informações da atualização
       hist.setUpdateData(hist.HoraAtual(),hist.DataFormato());
       hist.setUpdateTipo("População e Densidade");
       hist.setUpdateValor(pop);
   }
   
   public static void UpdateDomicilios(int index, double dom, Historico hist){
       CSVIn.get(index).setDomicilios(dom);
       hist.setUpdateData(hist.HoraAtual(),hist.DataFormato());
       hist.setUpdateTipo("Domicílios");
       hist.setUpdateValor(dom);
   }
   
   public static void UpdatePIBTotal(int index, double pib, Historico hist){
       CSVIn.get(index).setPIBTotal(pib);
       CSVIn.get(index).setPIBpC(Municipio.PIBpC(pib, CSVIn.get(index).getPopulacao()));
       hist.setUpdateData(hist.HoraAtual(),hist.DataFormato());
       hist.setUpdateTipo("PIBTotal e PIBpC");
       hist.setUpdateValor(pib);
   }
   
   public static void UpdateIDHG(int index, double idh, Historico hist){
       CSVIn.get(index).setIDHGeral(idh);
       CSVIn.get(index).setClassIDHG(Municipio.classIDH(idh));
       hist.setUpdateData(hist.HoraAtual(),hist.DataFormato());
       hist.setUpdateTipo("IDHG e Classificação");
       hist.setUpdateValor(idh);
   }
   
   public static void UpdateRendaMedia(int index, double rendM, Historico hist){
       CSVIn.get(index).setRendaMedia(rendM);
       hist.setUpdateData(hist.HoraAtual(),hist.DataFormato());
       hist.setUpdateTipo("Renda Media");
       hist.setUpdateValor(rendM);
   }
   
   public static void UpdateRendaNominal(int index, double rendN, Historico hist){
       CSVIn.get(index).setRendaNominal(rendN);
       hist.setUpdateData(hist.HoraAtual(),hist.DataFormato());
       hist.setUpdateTipo("Renda Nominal");
       hist.setUpdateValor(rendN);
   }
   
   public static void UpdatePEADia(int index, double pe, Historico hist){
       CSVIn.get(index).setPEADia(pe);
       hist.setUpdateData(hist.HoraAtual(),hist.DataFormato());
       hist.setUpdateTipo("PEA Dia");
       hist.setUpdateValor(pe);
   }
   
   public static void UpdateIDHE(int Index, double idh, Historico hist){
       CSVIn.get(Index).setIDHEducacao(idh);
       CSVIn.get(Index).setClassIDHE(Municipio.classIDH(idh));
       hist.setUpdateData(hist.HoraAtual(),hist.DataFormato());
       hist.setUpdateTipo("IDHE e Classificação");
       hist.setUpdateValor(idh);
   }
   
   public static void UpdateIDHL(int index, double idh, Historico hist){
       CSVIn.get(index).setIDHLongevidade(idh);
       CSVIn.get(index).setClassIDHL(Municipio.classIDH(idh));
       hist.setUpdateData(hist.HoraAtual(),hist.DataFormato());
       hist.setUpdateTipo("IDHL e Classificação");
       hist.setUpdateValor(idh);
   }
   
   // Metodo para deletar uma linha. Obs: O professor quer que não exclua o codigo IBGE e nome municipio
   public static void Delete(int index){
        CSVIn.remove(index);
   }  
}