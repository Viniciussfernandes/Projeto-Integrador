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

import static Services.Arquivo.CSVIn;

/**
 * @author Willian Junior <willianjunior.c.f@gmail.com>
 * @date 30/05/2024
 * @brief Class Operações
 */

public class Ujeverson {
    // Metodos para a atividade do Ujeverson
    public static String melhorPIBpC(){
        // Inicializando as variaveis fora do loop
        double maior = 0;
        int index = 0;
        // Loop responsavel por varrer a lista para armazenar o maior PIBpc
        for(int i = 0; i < CSVIn.size(); i++){
            if(CSVIn.get(i).getPIBpC() > maior){
                maior = CSVIn.get(i).getPIBpC();
                index = i;
            }
        }
        return "Municipio: " + CSVIn.get(index).getNome() + 
                String.format("\nPIBpC: %.2f", CSVIn.get(index).getPIBpC()) + 
                "\nIDH - Desenvolv. Humano: " + CSVIn.get(index).getIDHGeral() + 
                ", Classificação: " + CSVIn.get(index).getClassIDHG()+ 
                "\nIDH - Dimensão Educação: " + CSVIn.get(index).getIDHEducacao() + 
                ", Classificação: " + CSVIn.get(index).getClassIDHE()+ 
                "\nIDH - Dimensão Longevidade: " + CSVIn.get(index).getIDHLongevidade() +
                ", Classificação: " + CSVIn.get(index).getClassIDHL();
    }
    
    // Segue a mesma logica do que o de cima so que o pior PIBpC
    public static String piorPIBpC(){
        double menor = 0;
        int index = 0;
        for(int i = 0; i < CSVIn.size(); i++){
            if(CSVIn.get(i).getPIBpC() < menor){
                menor = CSVIn.get(i).getPIBpC();
                index = i;
            }
        }
        return "Municipio: " + CSVIn.get(index).getNome() + 
                String.format("\nPIBpC: %.2f", CSVIn.get(index).getPIBpC()) + 
                "\nIDH - Desenvolv. Humano: " + CSVIn.get(index).getIDHGeral() + 
                ", Classificação: " + CSVIn.get(index).getClassIDHG()+ 
                "\nIDH - Dimensão Educação: " + CSVIn.get(index).getIDHEducacao() + 
                ", Classificação: " + CSVIn.get(index).getClassIDHE()+ 
                "\nIDH - Dimensão Longevidade: " + CSVIn.get(index).getIDHLongevidade() +
                ", Classificação: " + CSVIn.get(index).getClassIDHL();
    }
    
    // Segue a mesma logica so que com uma condição a mais
    public static String melhorPIBpCpiorIDHE(){
        double pib = 0;
        double idh = 0;
        int index = 0;
        for(int i = 0; i < CSVIn.size(); i++){
            if(CSVIn.get(i).getPIBpC() > pib && CSVIn.get(i).getIDHEducacao() < idh){
                pib = CSVIn.get(i).getPIBpC();
                idh = CSVIn.get(i).getIDHEducacao();
                index = i;
            }
        }
        return "Municipio: " + CSVIn.get(index).getNome() + 
                String.format("\nPIBpC: %.2f", CSVIn.get(index).getPIBpC()) + 
                "\nIDH - Desenvolv. Humano: " + CSVIn.get(index).getIDHGeral() + 
                ", Classificação: " + CSVIn.get(index).getClassIDHG()+ 
                "\nIDH - Dimensão Educação: " + CSVIn.get(index).getIDHEducacao() + 
                ", Classificação: " + CSVIn.get(index).getClassIDHE()+ 
                "\nIDH - Dimensão Longevidade: " + CSVIn.get(index).getIDHLongevidade() +
                ", Classificação: " + CSVIn.get(index).getClassIDHL();
    }
}