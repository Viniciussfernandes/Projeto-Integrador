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

package Entities;

/**
 *
 * @author Willian Junior <willianjunior.c.f@gmail.com>
 * @date 05/06/2024
 * @brief Class Perfil
 */

// Classe responsavel pelo dados do usuario.
public class Perfil {

    // Variaveis necessesarias
    private String nome;
    private String CPF;

    public Perfil() {
    }
    
    public Perfil(String nome, String CPF) {
        this.nome = nome;
        this.CPF = CPF;
    }

    // gets
    public String getNome() {
        return nome;
    }

    public String getCPF() {
        return CPF;
    }

    // sets
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }
    
    // Metodo para validar CPF
    public static boolean ValidarCPF(String CPF){
        /** {CPF} pego o CPF quando o usuario chama o Metodo
         * {.matches} � usado para retorna um true ou false caso o padr�o seja correspondido
         * {(\\d)} o \\d represente o numero de 0-9 e () representa um grupo de caracteres
         * {\\1{10}} o \\1 significa que vai pegar o primeiro grupo de caracteres e {10} significa repetir 10 vezes */
        if(CPF.matches("(\\d)\\1{10}")){
            return false;
        } else {
            // Iniciliaza��o da variavel para soma
            int soma = 0;
            
            // Fonte dos calculos: https://www.campuscode.com.br/conteudos/o-calculo-do-digito-verificador-do-cpf-e-do-cnpj
            for(int i = 0; i < 9; i++){
                /** Uso Character.getNumericValue() para transformar um digito da String
                 * CPF em Integer para poder fazer a multiplica��o 
                 * charAt(i) vai pegar o numero da posi��o i que foi inicializado no for.
                 * Isso significa que se o i estiver na contagem 5, o charAt vai pegar o numero
                 * da posi��o 5. Logo ap�s multiplico com (10 - i). */
                soma += Character.getNumericValue(CPF.charAt(i)) * (10 - i);
            }
            
            // Reduzo o resto da divis�o por 11 para poder achar o primeiro digito.
            int PrimeiroDigito = soma % 11;
            PrimeiroDigito = 11 - PrimeiroDigito;
            
            // Caso o primeiro digito for maior do que 10, eu zero.
            if(PrimeiroDigito >= 10){
                PrimeiroDigito = 0;
            }
        
            // Reseto a variavel soma, pois ela saiu do for com um numero.
            soma = 0;
            
            // Funciona da mesma forma para achar o segundo numero, mas uso mais um posi��o.
            for(int i = 0; i < 10; i++){
                soma += Character.getNumericValue(CPF.charAt(i)) * (11 - i);
            }
            
            int SegundoDigito = soma % 11;
            SegundoDigito = 11 - SegundoDigito;
            
            if(SegundoDigito >= 10){
                SegundoDigito = 0;
            }
        
            /** Como o metodo � boolean, eu apenas informo a situa��o que deve ser true que nesse caso
             * seria se o numero da variavel PrimeiroDigito � igual ao numero da 9� posi��o da variavel CPF.
             * Para isso uso novamente o Caractere.getNumericaValue(CPF.charAt()) para pegar o valor numerico
             * presente na posi��o especifica da String CPF. A segunda condi��o � da mesma forma so que pegando
             * a 10� posi��o. */
            return PrimeiroDigito == Character.getNumericValue(CPF.charAt(9)) &&
                    SegundoDigito == Character.getNumericValue(CPF.charAt(10));
        }
    }
}
