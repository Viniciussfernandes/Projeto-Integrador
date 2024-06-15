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
import static Services.Tratamento.*;
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
import java.util.ArrayList;
import java.util.List;

/**
 * @author Willian Junior <willianjunior.c.f@gmail.com>
 * @date 08/06/2024
 * @brief Class Arquivos
 */

public class Arquivo {

    // Lista para armazenar os dados das linhas
    public static List<Municipio> CSVIn = new ArrayList<>();

    public static List<Municipio> getCSVIn() {
        return CSVIn;
    }
   
    // Metodo para ler o arquivo
    public static void In() throws ParseException{
       /** Tive muito problemas com a acentua��o do arquivo lido e gerado, por isso pesquisei bastante e descobrir 
        * que tenho que ver qual o encoding do arquivo que estou trabalhando e descobri que o arquivo .csv postado no classroom
        * esta como ANSI e acabei chegando nesse codigo abaixo
        */
         Charset encoding = Charset.forName("windows-1252");
         
       /** FileInputStream vai abrir o arquivo em um determinado endere�o e o InputStreamReader
        * vai especificar qual o enconding que irei utilizar, sendo o mesmo que o arquivo
        * original, ANSI, logo ap�s o BufferedReader armazenara essa leitura.
        */
       try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(
       "C:\\Projeto Integrador\\In\\01.ProjetoIntegrador_BaseMunicipios_In.csv"), encoding))) {
           
           /** A varivael itemCsv vai ser responsavel por ler a linha e uso 2 vezes
            * para poder pular a primeira linha, pois a primeira � o titulo
            */ 
           
           String itemCsv = br.readLine();
           itemCsv = br.readLine();
           
           // Loop responsavel por ler todas as 247 linhas do .csv
           while(itemCsv != null){
               
               // Separo todos os Strings com .split usando ";" como parametro
               String[] fields = itemCsv.split(";");
               
               // Esse bloco transformara os Strings em seus respectivos tipos
               int codigoIBGE = Integer.parseInt(fields[0]);
               String nome = fields[1];
               String microregiao = fields[2];
               String sigla = fields[3];
               String regiao = fields[4];
               double area = Double.parseDouble(LimparNumero(fields[5]));
               double populacao = Double.parseDouble(LimparNumero(fields[6]));
               double domicilios = Double.parseDouble(LimparNumero(fields[7]));
               double PIBTotal = Double.parseDouble(LimparNumero(fields[8]));
               double IDHGeral = Double.parseDouble(LimparNumero(fields[9]));
               double RendaMedia = Double.parseDouble(LimparNumero(fields[10]));
               double RendaNominal = Double.parseDouble(LimparNumero(fields[11]));
               double PEADia = Double.parseDouble(LimparNumero(fields[12]));
               double IDHEducacao = Double.parseDouble(LimparNumero(fields[13]));
               double IDHLongevidade = Double.parseDouble(LimparNumero(fields[14]));
               
               // Instanciando a classe para p�r na lista CSVIn
               Municipio mun = new Municipio(codigoIBGE, nome, microregiao, sigla,
                    regiao, area, populacao, domicilios, PIBTotal, IDHGeral, RendaMedia,
                    RendaNominal, PEADia, IDHEducacao, IDHLongevidade);
               CSVIn.add(mun);
               
               //Assim passa para a proxima linha at� aparecer null
               itemCsv = br.readLine();
            }
       }
       
       catch(IOException e){
               e.printStackTrace();
               }
       } 
      
    // Metodo para exportar o arquivo
    public static void Out(){
        // Da mesma forma que eu defino um enconding para ler, especifico qual vai ser na hora de exportar
        Charset encoding = Charset.forName("windows-1252");
        // Segue a mesma logica do In() so que com metodos para out
        try(BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream
        ("C:\\Projeto Integrador\\Out\\01.ProjetoIntegrador_BaseMunicipios_Out.csv"), encoding))){
            // Para definir os titulos
            bw.write("C�digo IBGE" + ";" + "Munic�pios" + ";" + "Microregi�o" + ";" +
                   "Estado" + ";" + "Regi�o Geogr�fica" + ";" + "�rea km�" + ";" +
                   "Popula��o" + ";" + "Densidade" + ";" + "Domic�lios" + ";" + 
                   "PIB Total (R$ mil)" + ";" + "PIB per Capita Total" + ";" +
                   "IDH - �ndice de Desenv. Humano" + ";" + "Classifica��o de IDH - Desenv. Humano" + ";" +
                   "Renda M�dia" + ";" + "Renda Nominal" + ";" + "PEA Dia" + ";" +
                   "IDH - Dimens��o Educação" + ";" + "Classifica��o do IDH - Dimens�o Educa��o" + ";" +
                   "IDH - Dimens��o Longevidade" + ";" + "Classifica��o do IDH - Dimens�o Longevidade");
           bw.newLine();
           
           // Loop que vai escrever cada linha
           for(Municipio out : CSVIn){
               /** Da mesma forma que eu separei a String usando ";" estarei usando
                * para pular a celula ou para separar mesmo
                */
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
