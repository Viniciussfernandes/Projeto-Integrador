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
import JavaFX.Controladores.MenuController;

/**
 * @author Willian Junior <willianjunior.c.f@gmail.com>
 * @date 30/05/2024
 * @brief Class Operações
 */

public class Ujeverson {
    // Metodo para o melhor PIBpC
    public static int MelhorPIBpC(){
        int index = -1;
        try{
        // Inicializando as variaveis fora do loop. Aquele mesmo ponto do Tratamento.Pesquisa() sobre o index.
        double maior = 0;
        // Loop responsavel por varrer a lista para armazenar o maior PIBpc.
        for(int i = 0; i < Arquivo.getCSVIn().size(); i++){
            // Lembrando que ! é negação.
            if(!Tratamento.CampoNull(Arquivo.getCSVIn().get(i))){
                if(Arquivo.getCSVIn().get(i).getPIBpC() > maior){
                    // Armazeno o maior na variavel, para poder comparar com os outros valores.
                    maior = Arquivo.getCSVIn().get(i).getPIBpC();
                    index = i;
                }
            }
        }
        
        } catch (NullPointerException e){
                MenuController.getErro().setHeaderText("Informações inexistentes");
                MenuController.getErro().setContentText("Por favor selecione a operação Criar primeiro");
                MenuController.getErro().show();
        }
        return index;
    }
    
    // Metodo para o pior PIBpC.
    public static int PiorPIBpC(){
        /** Armazeno na variavel menor, o maior PIBpC, pois fica melhor ao percorrer a lista atras do pior. 
         * A explicação é simples, caso eu coloque zero, todos os numeros vão ser maiores, logo vai pegar nenhum 
         * se for de acordo com a condicional if(PIBpC < menor). */
        double menor = Arquivo.getCSVIn().get(MelhorPIBpC()).getPIBpC();
        int index = -1;
        for(int i = 0; i < Arquivo.getCSVIn().size(); i++){
            if(!Tratamento.CampoNull(Arquivo.getCSVIn().get(i))){
                if(Arquivo.getCSVIn().get(i).getPIBpC() < menor){
                    // Armazeno o menor na variavel, para poder comparar com os outros valores.
                    menor = Arquivo.getCSVIn().get(i).getPIBpC();
                    index = i;
                }
            }
        }
        return index;
    }
    
    // Metodo para Melhor PIBpC com o pior IDH Educação.
    public static int MelhorPIBpC_PiorIDHE(){
        double pib = 0;
        double idh = 0;
        int index = -1;
        // Faço um for-each para achar o melhor IDH Educação, pelo mesmo motivo de achar o melhor PIBpC.
        for(Municipio m : Arquivo.getCSVIn()){
            if(!Tratamento.CampoNull(m)){
                if(m.getIDHEducacao() > idh){
                    idh = m.getIDHEducacao();
                }
            }
        }
        for(int i = 0; i < Arquivo.getCSVIn().size(); i++){
            if(!Tratamento.CampoNull(Arquivo.getCSVIn().get(i))){
                /** Basicamente se o PIBpC for maior do que 0 ou o valor anterior e se o IDH Educação for menor que o maior ou
                 * valor anterior. Lembrando que tem que cumprir as duas condições */
                if(Arquivo.getCSVIn().get(i).getPIBpC() >= pib && Arquivo.getCSVIn().get(i).getIDHEducacao() < idh){
                    // Armazeno nas variaveis, para poder comparar-las com os outros valores.
                    pib = Arquivo.getCSVIn().get(i).getPIBpC();
                    idh = Arquivo.getCSVIn().get(i).getIDHEducacao();
                    index = i;
                }
            }
        }
        return index;
    }
}