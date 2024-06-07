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

/**
 *
 * @author Willian Junior <willianjunior.c.f@gmail.com>
 * @date 05/06/2024
 * @brief Class Validacao
 */

public class Validacao {

    public static String cleanNumber(String number){
        String clean = number.replace(".", "");
        clean = clean.replace(",", ".");
        return clean;
    }
    
    public static boolean validarCPF(String CPF){
        
        CPF = CPF.replaceAll("[^0-9]", "");
        
        if(CPF.length() > 11){
            return false;
        } else if(CPF.matches("(\\d)\\1{10}")){
            return false;
        } else {
            int soma = 0;
            
            for(int i = 0; i < 9; i++){
                soma += Character.getNumericValue(CPF.charAt(i)) * (10 - i);
            }
            
            int PrimeiroDigito = soma % 11;
            PrimeiroDigito = 11 - PrimeiroDigito;
            
            if(PrimeiroDigito >= 10){
                PrimeiroDigito = 0;
            }
        
            soma = 0;
            
            for(int i = 0; i < 10; i++){
                soma += Character.getNumericValue(CPF.charAt(i)) * (11 - i);
            }
            
            int SegundoDigito = soma % 11;
            SegundoDigito = 11 - SegundoDigito;
            
            if(SegundoDigito >= 10){
                SegundoDigito = 0;
            }
        
            return PrimeiroDigito == Character.getNumericValue(CPF.charAt(9)) &&
                    SegundoDigito == Character.getNumericValue(CPF.charAt(10));
        }
        
    }
}
