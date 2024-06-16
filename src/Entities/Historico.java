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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Willian Junior <willianjunior.c.f@gmail.com>
 * @date 07/06/2024
 * @brief Class Arquivos
 */

/** Esse classe vai ser responsavel por armazenar o historico do usuário, ele é instanciado
 * com o nome e CPF do usuario, a data vai ser definida pelo set*/

public class Historico extends Perfil {

    private String updateData;
    
    // So pode ser instanciado com nome e CPF. As variaveis estão na classe Perfil.
    public Historico(String nome, String cpf) {
        super(nome, cpf);
    }
    
    /** Esse get vai usar um formato especifico de data e hora, ambas presentes logo abaixo.
     * A variavel dataHora vai receber a data e hora atual através do metodo dataHoraAtual(). 
     * A variavel formato vai receber o formato que eu defini através do metodo dataHoraFormato().
     * Depois apenas retorno uma String formatada de acordo com os requisitos do cliente. */
    public String getUpdateData() {
        LocalDateTime dataHora = dataHoraAtual();
        DateTimeFormatter formato = dataHoraFormato();
        return dataHora.format(formato);
    }
    
    public LocalDateTime dataHoraAtual(){
        return LocalDateTime.now();
    }
    
    public DateTimeFormatter dataHoraFormato() {
        return DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    }
}
