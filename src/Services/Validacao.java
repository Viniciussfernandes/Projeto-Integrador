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
        
        if(CPF.length() < 11){
            return false;
        }
        
        int sum = 0;
        for(int i = 0; i <= 9; i++){
            sum =+ Character.getNumericValue(CPF.charAt(i)) * (10 - i);
        }
        int PrimeiroDigito = sum % 11;
        PrimeiroDigito -= 11;
        if(PrimeiroDigito >= 10){
            PrimeiroDigito = 0;
        }
        
        sum = 0;
        for(int i = 0; i <= 10; i++){
            sum =+ Character.getNumericValue(CPF.charAt(i)) * (11 - i);
        }
        int SegundoDigito = sum % 11;
        SegundoDigito -= 11;
        if(SegundoDigito >= 10){
            SegundoDigito = 0;
        }
        
        return PrimeiroDigito == Character.getNumericValue(CPF.charAt(9)) &&
                SegundoDigito == Character.getNumericValue(CPF.charAt(10));
        
    }
}
