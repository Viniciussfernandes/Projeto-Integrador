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
import static Services.CRUD.CSVIn;
import static Services.Validacao.cleanNumber;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.text.ParseException;

/**
 * @author Willian Junior <willianjunior.c.f@gmail.com>
 * @date 08/06/2024
 * @brief Class Arquivos
 */
public class Arquivos {

    public static void In() throws ParseException{
       
       String strpath = "C:\\Projeto Integrador\\In\\01.ProjetoIntegrador_BaseMunicipios_In.csv";
       
       File sourceFile = new File(strpath);
       /** Tive muito problemas com a acentuação do arquivo lido e gerado, por isso pesquisei bastante e descobrir 
        * que tenho que ver qual o encoding do arquivo que estou trabalhando e descobri que o arquivo .csv postado no classroom
        * esta como ANSI e acabei chegando nesse codigo abaixo
        */
         Charset encoding = Charset.forName("windows-1252");
         
       try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(sourceFile), encoding))) {
           
           String itemCsv = br.readLine();
           itemCsv = br.readLine();
           
           while(itemCsv != null){
               
               try{
               String[] fields = itemCsv.split(";");
               
               int codigoIBGE = Integer.parseInt(fields[0]);
               String nome = fields[1];
               String microregiao = fields[2];
               String sigla = fields[3];
               String regiao = fields[4];
               double area = Double.parseDouble(cleanNumber(fields[5]));
               double populacao = Double.parseDouble(cleanNumber(fields[6]));
               double domicilios = Double.parseDouble(cleanNumber(fields[7]));
               double PIBTotal = Double.parseDouble(cleanNumber(fields[8]));
               double IDHGeral = Double.parseDouble(cleanNumber(fields[9]));
               double RendaMedia = Double.parseDouble(cleanNumber(fields[10]));
               double RendaNominal = Double.parseDouble(cleanNumber(fields[11]));
               double PEADia = Double.parseDouble(cleanNumber(fields[12]));
               double IDHEducacao = Double.parseDouble(cleanNumber(fields[13]));
               double IDHLongevidade = Double.parseDouble(cleanNumber(fields[14]));
               
               Municipios mun = new Municipios(codigoIBGE, nome, microregiao, sigla,
                    regiao, area, populacao, domicilios, PIBTotal, IDHGeral, RendaMedia,
                      RendaNominal, PEADia, IDHEducacao, IDHLongevidade);
               CSVIn.add(mun);
               
               itemCsv = br.readLine();
               
               } catch (NumberFormatException e) {
                   System.err.println("Erro ao converter nÃºmero na linha: " + itemCsv);
                   e.printStackTrace();
               }
            }
       }
       
       catch(IOException e){
               e.printStackTrace();
               }
       } 
      
   public static void Out(){
        Charset encoding = Charset.forName("windows-1252");
       try(BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream
        ("C:\\Projeto Integrador\\Out\\01.ProjetoIntegrador_BaseMunicipios_Out.csv"), encoding))){
           bw.write("Código IBGE" + ";" + "Municí­pios" + ";" + "Microregião" + ";" +
                   "Estado" + ";" + "Região Geográfica" + ";" + "Área km²" + ";" +
                   "População" + ";" + "Densidade" + ";" + "Domicílios" + ";" + 
                   "PIB Total (R$ mil)" + ";" + "PIB per Capita Total" + ";" +
                   "IDH - Índice de Desenv. Humano" + ";" + "Classificação de IDH - Desenv. Humano" + ";" +
                   "Renda Média" + ";" + "Renda Nominal" + ";" + "PEA Dia" + ";" +
                   "IDH - Dimensção EducaÃ§Ã£o" + ";" + "Classificação do IDH - Dimensão Educação" + ";" +
                   "IDH - Dimensção Longevidade" + ";" + "Classificação do IDH - Dimensão Longevidade");
           bw.newLine();
           
           for(Municipios out : CSVIn){
               bw.write(out.getCodigoIBGE() + ";" + out.getNome() + ";" +
                       out.getMicroregiao() + ";" + out.getSigla() + ";" +
                       out.getRegiao() + ";" + String.format("%.2f", out.getArea()) + ";" +
                       String.format("%.2f", out.getPopulacao()) + ";" +
                       String.format("%.2f", out.getDensidade()) + ";" +
                       String.format("%.2f", out.getDomicilios()) + ";" +
                       String.format("%.2f", out.getPIBTotal()) + ";" +
                       String.format("%.2f", out.getPIBpC()) + ";" +
                       String.format("%.2f", out.getIDHGeral()) + ";" +
                       out.getClassIDHG() + ";" +
                       String.format("%.2f", out.getRendaMedia()) + ";" +
                       String.format("%.2f", out.getRendaNominal()) + ";" +
                       String.format("%.2f", out.getPEADia()) + ";" +
                       String.format("%.2f", out.getIDHEducacao()) + ";" +
                       out.getClassIDHE() + ";" +
                       String.format("%.2f", out.getIDHLongevidade()) + ";" +
                       out.getClassIDHL());
               bw.newLine();
           }
       } catch(IOException e){
           
       }
   }
    
}
