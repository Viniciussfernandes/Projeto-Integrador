/*
 * Copyright (C) 2024 aluno
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
 *
 * @author aluno
 */
public class Historico extends Perfil {

    public Historico(String nome, String CPF) {
        super(nome, CPF);
    }
    
    private String UpdateData;
    private String UpdateTipo;
    private Double UpdateValor;
    
    public DateTimeFormatter getFmt() {
        return DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    }
    
    public LocalDateTime getNow(){
        return LocalDateTime.now();
    }

    public String getUpdateTipo() {
        return UpdateTipo;
    }

    public Double getUpdateValor() {
        return UpdateValor;
    }
    
    public String getUpdateData() {
        return UpdateData;
    }

    public void setUpdateData(LocalDateTime now, DateTimeFormatter fmt) {
        String up = now.format(fmt);
        this.UpdateData = up;
    }

    public void setUpdateTipo(String UpdateTipo) {
        this.UpdateTipo = UpdateTipo;
    }

    public void setUpdateValor(Double UpdateValor) {
        this.UpdateValor = UpdateValor;
    }
}
