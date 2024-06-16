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
import static Services.Arquivo.CSVIn;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author Willian Junior <willianjunior.c.f@gmail.com>
 * @date 05/06/2024
 * @brief Class Validacao
 */

public class Tratamento {
    
    // Metodo para limpar os pontos e virgulas
    
    public static List<Municipio> FormatarLista(List<Municipio> list){
        List<Municipio> res = new ArrayList<>();
        res = list;
        for(Municipio m : res){
            if(!CampoNull(m)){
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
            
            String densidadeFormat = String.format("%.2f", m.getDensidade());
            densidadeFormat = areaFormat.replace(",", ".");
            m.setDensidade(Double.parseDouble(densidadeFormat));
                
            String PIBpCFormat = String.format("%.2f", m.getPIBpC());
            PIBpCFormat = PIBpCFormat.replace(",", ".");
            m.setPIBpC(Double.parseDouble(PIBpCFormat));
            }
        } 
        return res;
    }
    
    public static boolean CampoNull(Municipio m){
        if(m.getArea() == null || m.getPopulacao() == null || m.getDomicilios() == null ||
            m.getPIBTotal() == null || m.getPIBpC() == null || m.getDensidade() == null ||
            m.getIDHGeral() == null || m.getClassIDHG() == null || m.getRendaMedia() == null ||
            m.getRendaNominal() == null || m.getPEADia() == null || m.getIDHEducacao() == null ||
            m.getClassIDHE() == null || m.getIDHLongevidade() == null || m.getClassIDHL() == null){
                return true;
        } return false;
    }
    
    public static String LimparInteger(String numero){
        String limpo = numero.replaceAll("\\,0+$", "");
        limpo = limpo.replace(".", "");
        return limpo;
    }
    
    public static String LimparDouble(String numero){
        String limpo = numero.replace(".", "");
        limpo = limpo.replace(",", ".");
        return limpo;
    }
    
    // Validar caractreres especiais exceto � e acentos
    public static boolean Caracteres(String str){
        Pattern Caracter = Pattern.compile("^[a-zA-Z�-��-���\\s]{1,50}$");
        return Caracter.matcher(str).matches();
    }
    
    // Validar numeros com o tamanho maximo de 11 e apenas valores numericos
    public static boolean Numerico(String numero){
        Pattern Numerico = Pattern.compile("^[0-9]+(\\.[0-9]+)?$");
        return Numerico.matcher(numero).matches();
    }
    
    public static Integer Pesquisa(int codigoIBGE, String municipio){
        int index = -1;
        for(int i = 0; i < CSVIn.size(); i++){
            if (CSVIn.get(i).getCodigoIBGE().equals(codigoIBGE) || 
                    CSVIn.get(i).getNome().equals(municipio)){
                index = i;
            }
       } return index;
    }
    
}
