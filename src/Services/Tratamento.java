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

import static Services.Arquivo.CSVIn;
import java.util.regex.Pattern;

/**
 * @author Willian Junior <willianjunior.c.f@gmail.com>
 * @date 05/06/2024
 * @brief Class Validacao
 */

public class Tratamento {
    // Metodo para limpar os pontos e virgulas
    public static String LimparNumero(String numero){
        String limpo = numero.replace(".", "");
        limpo = limpo.replace(",", ".");
        return limpo;
    }
    
    // Validar caractreres especiais exceto ç e acentos
    public static boolean Caracteres(String str){
        Pattern Caracter = Pattern.compile("^[a-zA-Zà-úÀ-ÚçÇ\\\\s]{1,50}+$");
        return Caracter.matcher(str).matches();
    }
    
    // Validar numeros com o tamanho maximo de 11 e apenas valores numericos
    public static boolean Numerico(String numero){
        Pattern Numerico = Pattern.compile("^[0-9]{1,11}+$");
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
