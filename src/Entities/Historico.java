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
    
    private String UpdateDate;
    private String UpdateType;
    
    public DateTimeFormatter getFmt1() {
        return DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    }
    
    public LocalDateTime getNow(){
        return LocalDateTime.now();
    }

    public String getUpdateType() {
        return UpdateType;
    }
    
    public String getUpdateDate() {
        return UpdateDate;
    }

    public void setUpdateDate(LocalDateTime now, DateTimeFormatter fmt) {
        String up = now.format(fmt);
        this.UpdateDate = up;
    }

    public void setUpdateType(String UpdateType) {
        this.UpdateType = UpdateType;
    }
    
    
}
