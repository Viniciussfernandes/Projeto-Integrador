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

import Entities.Municipios;
import Services.Operacoes;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Willian Junior <willianjunior.c.f@gmail.com>
 * @date 30/05/2024
 * @brief Class CRUD
 */

public class CRUD extends Municipios {

   protected static List<Municipios> CRUD = new ArrayList<>();
   
   public static void Create(Municipios mun){
       
        double densidade = Operacoes.Densidade(mun.getPopulacao(), mun.getArea());
        double PIBpC = Operacoes.PIBpC(mun.getPIBTotal(), mun.getPopulacao());
        String ClassIDH = Operacoes.ClassIDH(mun.getIDHGeral());
        String ClassIDHE = Operacoes.ClassIDH(mun.getIDHEducacao());
        String ClassIDHL = Operacoes.ClassIDH(mun.getIDHLongevidade());
        
        Municipios UpMun = new Municipios(mun.getCodigoIBGE(), mun.getNome(), mun.getMicroregiao(),
                mun.getSigla(), mun.getRegiao(), mun.getArea(), mun.getPopulacao(), mun.getDomicilios(),
                densidade, mun.getPIBTotal(), PIBpC, mun.getIDHGeral(), ClassIDH, mun.getRendaMedia(),
                mun.getRendaNominal(), mun.getPEADia(), mun.getIDHEducacao(), ClassIDHE,
                mun.getIDHLongevidade(), ClassIDHL);
        
        CRUD.add(UpMun);
   }
   
   public static void Reader(String strpath) throws ParseException{
       try(BufferedReader br = new BufferedReader(new FileReader(strpath))) {
           String itemCsv = br.readLine();
           
           while(itemCsv != null){
               String[] fields = itemCsv.split(",");
               
               int codigoIBGE = Integer.parseInt(fields[0]);
               String nome = fields[1];
               String microregiao = fields[2];
               String sigla = fields[3];
               String regiao = fields[4];
               double area = Double.parseDouble(fields[5]);
               int populacao = Integer.parseInt(fields[6]);
               int domicilios = Integer.parseInt(fields[7]);
               double densidade = Operacoes.Densidade(populacao, area);
               double PIBTotal = Double.parseDouble(fields[8]);
               double PIBpC = Operacoes.PIBpC(PIBTotal, populacao);
               double IDHGeral = Double.parseDouble(fields[9]);
               String ClassIDH = Operacoes.ClassIDH(IDHGeral);
               double RendaMedia = Double.parseDouble(fields[10]);
               double RendaNominal = Double.parseDouble(fields[11]);
               double PEADia = Double.parseDouble(fields[12]);
               double IDHEducacao = Double.parseDouble(fields[13]);
               String ClassIDHE = Operacoes.ClassIDH(IDHEducacao);
               double IDHLongevidade = Double.parseDouble(fields[14]);
               String ClassIDHL = Operacoes.ClassIDH(IDHLongevidade);
               
               Municipios mun = new Municipios(codigoIBGE, nome, microregiao, sigla,
                    regiao, area, populacao, domicilios, densidade, PIBTotal, PIBpC, IDHGeral,
                       ClassIDH, RendaMedia, RendaNominal, PEADia, IDHEducacao, ClassIDHE,
                       IDHLongevidade, ClassIDHL);
               CRUD.add(mun);
               
               itemCsv = br.readLine();
           }
       }
       catch(IOException e){
           System.out.println("Caminho invalido");
       }
       
   }
   
   public static void UpdatePopulacao(int Index, int pop){
       CRUD.get(Index).setPopulacao(pop);
       CRUD.get(Index).setDensidade(Operacoes.Densidade(pop, CRUD.get(Index).getArea()));
   }
   
   public static void UpdateDomicilios(int Index, int dom){
       CRUD.get(Index).setDomicilios(dom);
   }
   
   public static void UpdatePIBTotal(int Index, double pib){
       CRUD.get(Index).setPIBTotal(pib);
       CRUD.get(Index).setPIBpC(Operacoes.PIBpC(pib, CRUD.get(Index).getPopulacao()));
   }
   
   public static void UpdateIDHG(int Index, double idh){
       CRUD.get(Index).setIDHGeral(idh);
       CRUD.get(Index).setClassIDHG(Operacoes.ClassIDH(idh));
   }
   
   public static void UpdateRendaMedia(int Index, double rendM){
       CRUD.get(Index).setRendaMedia(rendM);
   }
   
   public static void UpdateRendaNominal(int Index, double rendN){
       CRUD.get(Index).setRendaNominal(rendN);
   }
   
   public static void UpdatePEADia(int Index, double pe){
       CRUD.get(Index).setPEADia(pe);
   }
   
   public static void UpdateIDHE(int Index, double idh){
       CRUD.get(Index).setIDHEducacao(idh);
       CRUD.get(Index).setClassIDHE(Operacoes.ClassIDH(idh));
   }
   
   public static void UpdateIDHL(int Index, double idh){
       CRUD.get(Index).setIDHLongevidade(idh);
       CRUD.get(Index).setClassIDHL(Operacoes.ClassIDH(idh));
   }
   
   public static void Delete(int index){
       CRUD.remove(index);
   } 
    
}