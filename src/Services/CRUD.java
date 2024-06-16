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

import Entities.Municipio;

/**
 * @author Willian Junior <willianjunior.c.f@gmail.com>
 * @date 30/05/2024
 * @brief Class CRUD
 */

public class CRUD {

   // Metodo para inserir as informações calculaveis
   public static void Create(){
       for (Municipio create : Arquivo.getCSVIn()) {
           // Uma verificação simples se o campo esta null. O ! é de negação, então tem que retornar false.
                if(!Tratamento.CampoNull(create)){
                // Calculando com base no que tem no CSVIn
                double densidade = Municipio.Densidade(create.getPopulacao(), create.getArea());
                double PIBpC = Municipio.PIBpC(create.getPIBTotal(), create.getPopulacao());
                String ClassIDH = Municipio.classIDH(create.getIDHGeral());
                String ClassIDHE = Municipio.classIDH(create.getIDHEducacao());
                String ClassIDHL = Municipio.classIDH(create.getIDHLongevidade());
           
                // Uso os sets para colocar na lista
                create.setDensidade(densidade);
                create.setPIBpC(PIBpC);
                create.setClassIDHG(ClassIDH);
                create.setClassIDHE(ClassIDHE);
                create.setClassIDHL(ClassIDHL);
                }
        }
   }
  /**
   public static StringBuilder Reader(int i){
       StringBuilder Planilha = new StringBuilder(
                       Arquivo.getCSVIn().get(i).getCodigoIBGE() + "; " + Arquivo.getCSVIn().get(i).getNome() + "; " +
                       Arquivo.getCSVIn().get(i).getMicroregiao() + "; " + Arquivo.getCSVIn().get(i).getSigla() + "; " +
                       Arquivo.getCSVIn().get(i).getRegiao() + "; " + String.format("%.2f", Arquivo.getCSVIn().get(i).getArea()) + "; " +
                       Arquivo.getCSVIn().get(i).getPopulacao() + "; " +
                       String.format("%.2f", Arquivo.getCSVIn().get(i).getDensidade()) + "; " +
                       String.format("%.2f", Arquivo.getCSVIn().get(i).getDomicilios()) + "; " +
                       String.format("%.2f", Arquivo.getCSVIn().get(i).getPIBTotal()) + "; " +
                       String.format("%.2f", Arquivo.getCSVIn().get(i).getPIBpC()) + "; " +
                       String.format("%.2f", Arquivo.getCSVIn().get(i).getIDHGeral()) + "; " +
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
   */
   /** Todos os Updates seguem a mesma logica, informe o index , o valor da atualização, dependendo do tipo de mudança
    * o tipo do valor pode altera, por conta disso coloquei em metodos separados.*/
   public static void UpdatePopulacao(int index, int pop){
       Arquivo.getCSVIn().get(index).setPopulacao(pop);
       Arquivo.getCSVIn().get(index).setDensidade(Municipio.Densidade(pop, Arquivo.getCSVIn().get(index).getArea()));
   }
   
   public static void UpdateDomicilios(int index, double dom){
       Arquivo.getCSVIn().get(index).setDomicilios(dom);
   }
   
   public static void UpdatePIBTotal(int index, double pib){
       Arquivo.getCSVIn().get(index).setPIBTotal(pib);
       Arquivo.getCSVIn().get(index).setPIBpC(Municipio.PIBpC(pib, Arquivo.getCSVIn().get(index).getPopulacao()));
   }
   
   public static void UpdateIDHG(int index, double idh){
       Arquivo.getCSVIn().get(index).setIDHGeral(idh);
       Arquivo.getCSVIn().get(index).setClassIDHG(Municipio.classIDH(idh));
   }
   
   public static void UpdateRendaMedia(int index, double rendM){
       Arquivo.getCSVIn().get(index).setRendaMedia(rendM);
   }
   
   public static void UpdateRendaNominal(int index, double rendN){
       Arquivo.getCSVIn().get(index).setRendaNominal(rendN);
   }
   
   public static void UpdatePEADia(int index, int pe){
       Arquivo.getCSVIn().get(index).setPEADia(pe);
   }
   
   public static void UpdateIDHE(int Index, double idh){
       Arquivo.getCSVIn().get(Index).setIDHEducacao(idh);
   }
   
   public static void UpdateIDHL(int index, double idh){
       Arquivo.getCSVIn().get(index).setIDHLongevidade(idh);
   }
   
   // Metodo para deletar uma linha.
   public static void Delete(int index){
       /** Basicamente ele vai colocar todas as informações exceto codigo do IBGE, municipio, microregião, estado e região.
        * Isso foi em uma aula, onde eu ouvi uma conversa entre o professor que falava que não fazia sentido um usuario comum
        * apagar um municipio inteiro, ja que o municipio continuaria existindo e so alguem de grande autoridade poderia fazer isso
        * esse programa é apenas para controle de informações então não fazia sentido poder apagar um municipio inteiro assim tão facilmente.
        * Por isso apaguei apenas os dados numericos desse municipio. */
        Arquivo.getCSVIn().get(index).setArea(null); 
        Arquivo.getCSVIn().get(index).setPopulacao(null);
        Arquivo.getCSVIn().get(index).setDomicilios(null);
        Arquivo.getCSVIn().get(index).setPIBTotal(null);
        Arquivo.getCSVIn().get(index).setIDHGeral(null);
        Arquivo.getCSVIn().get(index).setRendaMedia(null);
        Arquivo.getCSVIn().get(index).setRendaNominal(null);
        Arquivo.getCSVIn().get(index).setPEADia(null);
        Arquivo.getCSVIn().get(index).setIDHEducacao(null);
        Arquivo.getCSVIn().get(index).setIDHLongevidade(null);
        
        Arquivo.getCSVIn().get(index).setDensidade(null);
        Arquivo.getCSVIn().get(index).setPIBpC(null);
        Arquivo.getCSVIn().get(index).setClassIDHG(null);
        Arquivo.getCSVIn().get(index).setClassIDHE(null);
        Arquivo.getCSVIn().get(index).setClassIDHL(null);
   }  
}