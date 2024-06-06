/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
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
