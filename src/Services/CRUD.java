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
import Entities.Municipios;
import Entities.Perfil;
import static Services.Validacao.cleanNumber;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @author Willian Junior <willianjunior.c.f@gmail.com>
 * @date 30/05/2024
 * @brief Class CRUD
 */

public class CRUD extends Municipios {

   protected static List<Municipios> CSVIn = new ArrayList<>();
   
   public static void Create(){
       
       for (int i = 0; i < CSVIn.size(); i++) {
           double densidade = Operacoes.Densidade(CSVIn.get(i).getPopulacao(), CSVIn.get(i).getArea());
           double PIBpC = Operacoes.PIBpC(CSVIn.get(i).getPIBTotal(), CSVIn.get(i).getPopulacao());
           String ClassIDH = Operacoes.ClassIDH(CSVIn.get(i).getIDHGeral());
           String ClassIDHE = Operacoes.ClassIDH(CSVIn.get(i).getIDHEducacao());
           String ClassIDHL = Operacoes.ClassIDH(CSVIn.get(i).getIDHLongevidade());
           
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
   
   public static void UpdatePopulacao(int Index, double pop, Historico hist){
       CSVIn.get(Index).setPopulacao(pop);
       CSVIn.get(Index).setDensidade(Operacoes.Densidade(pop, CSVIn.get(Index).getArea()));
       hist.setUpdateDate(hist.getNow(),hist.getFmt1());
       hist.setUpdateType("População e Densidade");
   }
   
   public static void UpdateDomicilios(int Index, double dom, Historico hist){
       CSVIn.get(Index).setDomicilios(dom);
       hist.setUpdateDate(hist.getNow(),hist.getFmt1());
       hist.setUpdateType("Domicílios");
   }
   
   public static void UpdatePIBTotal(int Index, double pib, Historico hist){
       CSVIn.get(Index).setPIBTotal(pib);
       CSVIn.get(Index).setPIBpC(Operacoes.PIBpC(pib, CSVIn.get(Index).getPopulacao()));
       hist.setUpdateDate(hist.getNow(),hist.getFmt1());
       hist.setUpdateType("PIBTotal e PIBpC");
   }
   
   public static void UpdateIDHG(int Index, double idh, Historico hist){
       CSVIn.get(Index).setIDHGeral(idh);
       CSVIn.get(Index).setClassIDHG(Operacoes.ClassIDH(idh));
       hist.setUpdateDate(hist.getNow(),hist.getFmt1());
       hist.setUpdateType("IDHG e Classificação");
   }
   
   public static void UpdateRendaMedia(int Index, double rendM, Historico hist){
       CSVIn.get(Index).setRendaMedia(rendM);
       hist.setUpdateDate(hist.getNow(),hist.getFmt1());
       hist.setUpdateType("Renda Media");
   }
   
   public static void UpdateRendaNominal(int Index, double rendN, Historico hist){
       CSVIn.get(Index).setRendaNominal(rendN);
       hist.setUpdateDate(hist.getNow(),hist.getFmt1());
       hist.setUpdateType("Renda Nominal");
   }
   
   public static void UpdatePEADia(int Index, double pe, Historico hist){
       CSVIn.get(Index).setPEADia(pe);
       hist.setUpdateDate(hist.getNow(),hist.getFmt1());
       hist.setUpdateType("PEA Dia");
   }
   
   public static void UpdateIDHE(int Index, double idh, Historico hist){
       CSVIn.get(Index).setIDHEducacao(idh);
       CSVIn.get(Index).setClassIDHE(Operacoes.ClassIDH(idh));
       hist.setUpdateDate(hist.getNow(),hist.getFmt1());
       hist.setUpdateType("IDHE e Classificação");
   }
   
   public static void UpdateIDHL(int Index, double idh, Historico hist){
       CSVIn.get(Index).setIDHLongevidade(idh);
       CSVIn.get(Index).setClassIDHL(Operacoes.ClassIDH(idh));
       hist.setUpdateDate(hist.getNow(),hist.getFmt1());
       hist.setUpdateType("IDHL e Classificação");
   }
   
   
   public static void Delete(String municipio, Integer codigoIBGE){
       Iterator<Municipios> iterator = CSVIn.iterator();
       while(iterator.hasNext()){
           Municipios mun = iterator.next();
           if(mun.getNome().equals(municipio)){
               iterator.remove();
           } else if(mun.getNome().equals(codigoIBGE)){
               iterator.remove();
           }
       }
   }  
}