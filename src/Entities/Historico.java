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

public class Historico extends Perfil {

    public Historico() {
    }
    
    public Historico(String nome, String CPF) {
        super(nome, CPF);
    }
    
    private String UpdateData;
    private String UpdateTipo;
    private Double UpdateValor;

    public String getUpdateTipo() {
        return UpdateTipo;
    }

    public Double getUpdateValor() {
        return UpdateValor;
    }
    
    public String getUpdateData() {
        return UpdateData;
    }

    public void setUpdateData(LocalDateTime hora, DateTimeFormatter formato) {
        String up = hora.format(formato);
        this.UpdateData = up;
    }

    public void setUpdateTipo(String UpdateTipo) {
        this.UpdateTipo = UpdateTipo;
    }

    public void setUpdateValor(Double UpdateValor) {
        this.UpdateValor = UpdateValor;
    }
    
    public DateTimeFormatter DataFormato() {
        return DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    }
    
    public LocalDateTime HoraAtual(){
        return LocalDateTime.now();
    }
}
