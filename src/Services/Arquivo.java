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

// Classe responsavel pela importação e exportação do arquivo a ou de uma lista.
public class Arquivo {

    // Para facilitar para descobrir se o In() ja foi executado.
    private static boolean dadosCarregados = false;

    public static boolean isDadosCarregados() {
        return dadosCarregados;
    }
    
    // Lista para armazenar os dados das linhas
    private static List<Municipio> CSVIn = new ArrayList<>();

    public static List<Municipio> getCSVIn() {
        return CSVIn;
    }
    /** Tive muito problemas com a acentuação do arquivo lido e gerado, por isso pesquisei bastante e descobrir
     * que tenho que ver qual o encoding do arquivo que estou trabalhando e descobri que o arquivo .csv postado no classroom
     * esta como ANSI e acabei chegando nesse codigo abaixo. Desobri isso abrindo o .csv no documento de texto do windows e
     * no canto direito inferior esta escrito o tipo do arquivo que nesse caso é ANSI.
     * Alem disso tem que ir na barrinha escrita <default config> do lado de onde da run. 
     * Clicar em customize... ir em Sources e depois em Enconding la em baixo e mudar para 
     * windows-1252 que seria o ANSI que estamos trabalhando. */
       
    /** O Charset é usado para definir como o arquivo vai ser lido, na minha interpretação é basicamente definindo o enconding
     * que vou trabalhar. */
    public static Charset encoding = Charset.forName("windows-1252");
    
    // Metodo para ler o arquivo
    public static void In() throws ParseException{
         
       /** FileInputStream abre o arquivo em um determinado endereço e o InputStreamReader
        * vai especificar qual o enconding que irei utilizar, sendo o mesmo que o arquivo
        * original, ANSI, logo após o BufferedReader armazenara essa leitura em buffer. */
       try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(
       "C:\\Projeto Integrador\\In\\01.ProjetoIntegrador_BaseMunicipios_In.csv"), encoding))) {
           
           /** A varivael itemCsv vai ser responsavel por ler a linha. Uso 2 vezes
            * para poder pular a primeira linha, pois a primeira é o titulo */ 
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
               /** O metodo LimparDouble() e LimparInteger() está presente na classe Tratamento desse mesmo pacote.
                * Mas basicamente ele vai retorna a String sem virgula e com um ponto separando as casas decimais.
                * No caso do LimparInteger() vai retorna sem ponto e virgula. */
               double area = Double.parseDouble(LimparDouble(fields[5]));
               int populacao = Integer.parseInt(LimparInteger(fields[6]));
               double domicilios = Double.parseDouble(LimparDouble(fields[7]));
               double PIBTotal = Double.parseDouble(LimparDouble(fields[8]));
               double IDHGeral = Double.parseDouble(LimparDouble(fields[9]));
               double RendaMedia = Double.parseDouble(LimparDouble(fields[10]));
               double RendaNominal = Double.parseDouble(LimparDouble(fields[11]));
               int PEADia = Integer.parseInt(LimparInteger(fields[12]));
               double IDHEducacao = Double.parseDouble(LimparDouble(fields[13]));
               double IDHLongevidade = Double.parseDouble(LimparDouble(fields[14]));
               
               // Instanciando a classe para pôr na lista CSVIn.
               Municipio mun = new Municipio(codigoIBGE, nome, microregiao, sigla,
                    regiao, area, populacao, domicilios, PIBTotal, IDHGeral, RendaMedia,
                    RendaNominal, PEADia, IDHEducacao, IDHLongevidade);
               CSVIn.add(mun);
               
               //Assim passa para a proxima linha até aparecer null
               itemCsv = br.readLine();
            }
           dadosCarregados = true;
       } 
       
       catch(IOException e){
               e.printStackTrace();
               }
       } 
      
    // Metodo para exportar o arquivo.
    public static void Out(){
        // Segue a mesma logica do In() so que com OutputStreamWriter e FileOutputStream, mas desempenham o mesmo papel.
        try(BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream
        ("C:\\Projeto Integrador\\Out\\01.ProjetoIntegrador_BaseMunicipios_Out.csv"), encoding))){
            /** Caso vocês abram o .csv em um documento de texto do windows, perceberão que o ; é responsavel por separar
             * as celulas no excel, por isso eu ponho dessa forma, alem de ser mais facil de identificar a separação.
             * Definindo o titulo. */
            bw.write("Código IBGE" + ";" + "Municí­pios" + ";" + "Microregião" + ";" +
                   "Estado" + ";" + "Região Geográfica" + ";" + "Área km²" + ";" +
                   "População" + ";" + "Densidade" + ";" + "Domicílios" + ";" + 
                   "PIB Total (R$ mil)" + ";" + "PIB per Capita Total" + ";" +
                   "IDH - Índice de Desenv. Humano" + ";" + "Classificação de IDH - Desenv. Humano" + ";" +
                   "Renda Média" + ";" + "Renda Nominal" + ";" + "PEA Dia" + ";" +
                   "IDH - Dimensção EducaÃ§Ã£o" + ";" + "Classificação do IDH - Dimensão Educação" + ";" +
                   "IDH - Dimensção Longevidade" + ";" + "Classificação do IDH - Dimensão Longevidade");
           bw.newLine();
           
           // Loop que vai escrever cada linha
           for(Municipio out : CSVIn){
               /** Da mesma forma que eu separei a String usando ";" estarei usando para pular a celula. 
                * Uso o for-each para varrer a lista. 
                * Uma explicação de como funcionar o for-each. Primeiro a definição do tipo que no nosso caso é o Municipio,
                * que por sua vez é a classe que eu criei no pacote Entities. Se vocês reparem o tipo da lista é Municipio. 
                * Depois um nome para representar, logo após o :, vem a definicição de qual vai ser a base que irar percorrer,
                * em outras palavras, o que o (nome que você definiu) vai percorrer? Nesse caso quero que ele percora a lista inteira. */
               bw.write(out.getCodigoIBGE() + ";" + out.getNome() + ";" + out.getMicroregiao() + ";" +
                       out.getSigla() + ";" + out.getRegiao() + ";" + String.format("%.2f", out.getArea()) + ";" +
                       out.getPopulacao() + ";" + String.format("%.2f", out.getDensidade()) + ";" + 
                       String.format("%.2f", out.getDomicilios()) + ";" + String.format("%.2f", out.getPIBTotal()) + ";" +
                       String.format("%.2f", out.getPIBpC()) + ";" + String.format("%.2f", out.getIDHGeral()) + ";" +
                       out.getClassIDHG() + ";" + String.format("%.2f", out.getRendaMedia()) + ";" +
                       String.format("%.2f", out.getRendaNominal()) + ";" + out.getPEADia() + ";" + 
                       String.format("%.2f", out.getIDHEducacao()) + ";" + out.getClassIDHE() + ";" + 
                       String.format("%.2f", out.getIDHLongevidade()) + ";" + out.getClassIDHL());
               bw.newLine();
           }
       } catch(IOException e){
       }
   }
}
