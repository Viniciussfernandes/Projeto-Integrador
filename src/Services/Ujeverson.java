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
import static Services.Arquivo.CSVIn;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Willian Junior <willianjunior.c.f@gmail.com>
 * @date 30/05/2024
 * @brief Class Operações
 */

public class Ujeverson {
    // Metodos para a atividade do Ujeverson
    public static int MelhorPIBpC(){
        // Inicializando as variaveis fora do loop
        double maior = 0;
        int index = -1;
        // Loop responsavel por varrer a lista para armazenar o maior PIBpc
        for(int i = 0; i < CSVIn.size(); i++){
            if(!Tratamento.CampoNull(CSVIn.get(i))){
                if(CSVIn.get(i).getPIBpC() > maior){
                    maior = CSVIn.get(i).getPIBpC();
                    index = i;
                }
            }
        }
        return index;
    }
    
    // Segue a mesma logica do que o de cima so que o pior PIBpC
    public static int PiorPIBpC(){
        double menor = Arquivo.CSVIn.get(MelhorPIBpC()).getPIBpC();
        int index = 0;
        for(int i = 0; i < Arquivo.CSVIn.size(); i++){
            if(!Tratamento.CampoNull(Arquivo.CSVIn.get(i))){
                if(Arquivo.CSVIn.get(i).getPIBpC() < menor){
                    menor = Arquivo.CSVIn.get(i).getPIBpC();
                    index = i;
                }
            }
        }
        return index;
    }
    
    // Segue a mesma logica so que com uma condição a mais
    public static int MelhorPIBpC_PiorIDHE(){
        double pib = 0;//Arquivo.CSVIn.get(MelhorPIBpC()).getPIBpC();
        double idh = 0;
        for(Municipio m : Arquivo.getCSVIn()){
            if(!Tratamento.CampoNull(m)){
                if(m.getIDHEducacao() > idh){
                    idh = m.getIDHEducacao();
                }
            }
        }
        int index = 0;
        for(int i = 0; i < CSVIn.size(); i++){
            if(!Tratamento.CampoNull(Arquivo.CSVIn.get(i))){
                if(Arquivo.CSVIn.get(i).getPIBpC() >= pib && Arquivo.CSVIn.get(i).getIDHEducacao() < idh){
                        
                    pib = Arquivo.CSVIn.get(i).getPIBpC();
                    idh = Arquivo.CSVIn.get(i).getIDHEducacao();
                    index = i;
                }
            }
        }
        return index;
    }
}