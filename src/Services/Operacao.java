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

import static Services.CRUD.CSVIn;

/**
 * @author Willian Junior <willianjunior.c.f@gmail.com>
 * @date 30/05/2024
 * @brief Class Operações
 */

public class Operacao {
    
    public static double Densidade(Double populacao, Double area){
        return populacao / area;
    }
    
    public static double PIBpC(Double PIBTotal, Double populacao){
        return PIBTotal / populacao;
    }
    
    public static String melhorPIBpC(){
        double maior = 0;
        int index = 0;
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
    
    public static String classIDH(Double IDH){
        if(IDH > 0.80){
            return "Muito alto";
        } else if(IDH > 0.70 && IDH <= 0.80){
            return "Alto";
        } else if(IDH > 0.55 && IDH <= 0.70){
            return "Medio";
        } else if(IDH < 0.55){
            return "Baixo";
        } else return "Não foi possivel calcular";
    }
}