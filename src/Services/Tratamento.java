/**
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
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author Willian Junior <willianjunior.c.f@gmail.com>
 * @date 05/06/2024
 * @brief Class Validacao
 */

public class Tratamento {
    
    // Um metodo para formatar a casa decimal.
    public static List<Municipio> FormatarLista(List<Municipio> list){
        // Faço uma copia do arquivo original;
        List<Municipio> res = new ArrayList<>();
        res = list;
        // Com o for-each varro a lista formatando as casas decimais
        for(Municipio m : res){
            // Uma verificação se o campo está nulo. Lembrando que o ! é negação, logo quero que seja false.
            if(!CampoNull(m)){
                /** A formatação da casa decimal acontece da seguinte forma, pego o numero double transformo
                 * em String, usando o String.format, com a formatação. Depois disso substituo a , por ., pois
                 * quando o transformo o double em String, o . vira , por conta disso preciso desfazer essa mudança
                 * se não da um erro de NumberFormatException. Depois apenas uma parseDouble para transforma-lo de volta
                 * para double e uso o set para por de volta na lista. */
            String areaFormat = String.format("%.2f", m.getArea());
            areaFormat = areaFormat.replace(",", ".");
            m.setArea(Double.parseDouble(areaFormat));
            
            String domiFormat = String.format("%.2f", m.getDomicilios());
            domiFormat = domiFormat.replace(",", ".");
            m.setDomicilios(Double.parseDouble(domiFormat));
            
            String PIBFormat = String.format("%.2f", m.getPIBTotal());
            PIBFormat = PIBFormat.replace(",", ".");
            m.setPIBTotal(Double.parseDouble(PIBFormat));
            
            String rendaMFormat = String.format("%.2f", m.getRendaMedia());
            rendaMFormat = rendaMFormat.replace(",", ".");
            m.setRendaMedia(Double.parseDouble(rendaMFormat));
            
            String rendaNFormat = String.format("%.2f", m.getRendaNominal());
            rendaNFormat = rendaNFormat.replace(",", ".");
            m.setRendaNominal(Double.parseDouble(rendaNFormat));
            
            if(m.getPIBpC() != null || m.getDensidade() != null || m.getClassIDHE() != null ||
                    m.getClassIDHG() != null || m.getClassIDHL() != null){
                
                String densidadeFormat = String.format("%.2f", m.getDensidade());
                densidadeFormat = areaFormat.replace(",", ".");
                m.setDensidade(Double.parseDouble(densidadeFormat));
                
                String PIBpCFormat = String.format("%.2f", m.getPIBpC());
                PIBpCFormat = PIBpCFormat.replace(",", ".");
                m.setPIBpC(Double.parseDouble(PIBpCFormat));
            }
            }
        } 
        // Depois de tudo retorno a copia da lista formatada
        /** Eu crio uma copia da lista e uso a formatação em cima dessa, é por conta das operações matematicas, se eu limitar
         * as casas decimais na hora de por os dados na lista, vai dar resultados incorretos, por conta disso a lista original, Arquivo.CSVIn,
         * deve ser mantida intacta. */
        return res;
    }
    
    // Para mascarar o CPF
    public static String MascararCPF(String cpf) {
        // .substring vai começar a escrever escrever depois de uma determina posição que nesse caso seria a posição 8
        return "*********" + cpf.substring(cpf.length() - 3);
    }
    
    // Coreção a ser feita, nesse caso vai retorna sempre falso se eu não usar a operação Criar, por conta disso vai dar erro em algum momento
    public static boolean CampoNull(Municipio m){
        if(m.getArea() == null || m.getPopulacao() == null || m.getDomicilios() == null ||
            m.getPIBTotal() == null || m.getIDHGeral() == null  || m.getRendaMedia() == null ||
            m.getRendaNominal() == null || m.getPEADia() == null || m.getIDHEducacao() == null ||
            m.getIDHLongevidade() == null ){
                return true;
        } return false;
    }
    
    // Metodo para limpar o int.
    public static String LimparInteger(String numero){
        /** {\\,\\d+} O \\, vai achar a , o \\d+ vai encontrar todos os numeros depois da ,
         * {$} signifca o fim da String
         * {, ""} vai substituir tudo da regra "\\,d+$" por nada
         * Um exemplo, o numero 8500,00030 vai virar 8500. */
        String limpo = numero.replaceAll("\\,\\d+$", "");
        limpo = limpo.replace(".", "");
        return limpo;
    }
    
    public static String LimparDouble(String numero){
        /** Uma simples remoção de . por nada e , por ponto.
         * Isso se deve ao fato do arquivo .csv conter . separando milhares e , separando casa decimal */
        String limpo = numero.replace(".", "");
        limpo = limpo.replace(",", ".");
        return limpo;
    }
    
    // Validar caractreres especiais exceto ç e acentos
    public static boolean Caracteres(String str){
        /** {^} O incio da String
         * {[a-z} Todos as letras de a-z minusculo
         * {A-Z} Todas as letras de A-Z maisculo
         * {à-ú} Todas as consoantes minusculas com acentos
         * {À-Ú} Todas as consoantes maisculas com acentos
         * {çÇ} ç minusculo e Ç maisculo
         * {\\s]} espaço
         * {{1,50}} Tudo dentro de [] pode ser digitado de 1 à 50.
         * {$} Fim da String */
        // Pattern esta servindo para compilar a expressão "^[a-zA-Zà-úÀ-ÚçÇ\\s]{1,50}$"
        Pattern Caracter = Pattern.compile("^[a-zA-Zà-úÀ-ÚçÇ\\s]{1,50}$");
        /** O .matcher serve para verificar se corresponde ao padrão definido.
         * O .matches retorna true estiver dentro do padrão compilado e false se estiver fora do padrão. */
        return Caracter.matcher(str).matches();
    }
    
    // Validar numeros com o tamanho maximo de 11 e apenas valores numericos
    public static boolean Numerico(String numero){
        /** {^} O incio da String
         * {[0-9]+} Todos os numeros, o + signifca "uma ou mais vezes"
         * {(\\.} Corresponde ao . literal
         * {[0-9]+)} Novamente, mas apos o .
         * {?} Significa que os valores dentro de () podem ser opcionais
         * Isso implica que pode ter ou não ter casa decimal.
         * {$} Fim da String */
        // Da mesma forma que Caracteres()
        Pattern Numerico = Pattern.compile("^[0-9]+(\\.[0-9]+)?$");
        return Numerico.matcher(numero).matches();
    }
    
    // Metodo para pesquisar
    public static Integer Pesquisa(int codigoIBGE, String municipio){
        /** Inicializo como -1, pois o numero 0 esta ocupado com um objeto, então caso coloque 0, vai retorna 
         * um valor existente. Minha intenção é se durante o for-each não achar ele retorna uma valor que não 
         * existe na lista. */
        int index = -1;
        // Simplemente percorro a lista e comparo se os valores fornecidos são iguais através de .equals.
        for(int i = 0; i < Arquivo.getCSVIn().size(); i++){
            if (Arquivo.getCSVIn().get(i).getCodigoIBGE().equals(codigoIBGE) || 
                    Arquivo.getCSVIn().get(i).getNome().equals(municipio)){
                index = i;
            }
       } return index;
    }
    
}
