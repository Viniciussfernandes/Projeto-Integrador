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
 * @author Willian Junior <willianjunior.c.f@gmail.com>
 * @date 30/05/2024
 * @brief Class Municipios
 */
public class Municipios {
    
    private Integer codigoIBGE;
    private String nome;
    private String microregiao;
    private String sigla;
    private String regiao;
    private Double area;
    private Integer populacao;
    private Integer domicilios;
    private Double Densidade;
    private Double PIBTotal;
    private Double IDHGeral;
    private String ClassIDHG;
    private Double RendaMedia;
    private Double RendaNominal;
    private Double PEADia;
    private Double IDHEducacao;
    private String ClassIDHE;
    private Double IDHLongevidade;
    private String ClassIDHL;

    public Municipios() {
    }

    public Municipios(Integer codigoIBGE, String nome, String microregiao, String sigla, String regiao, Double area, Integer populacao, Integer domicilios, Double Densidade, Double PIBTotal, Double IDHGeral, String ClassIDHG, Double RendaMedia, Double RendaNominal, Double PEADia, Double IDHEducacao, String ClassIDHE, Double IDHLongevidade, String ClassIDHL) {
        this.codigoIBGE = codigoIBGE;
        this.nome = nome;
        this.microregiao = microregiao;
        this.sigla = sigla;
        this.regiao = regiao;
        this.area = area;
        this.populacao = populacao;
        this.domicilios = domicilios;
        this.Densidade = Densidade;
        this.PIBTotal = PIBTotal;
        this.IDHGeral = IDHGeral;
        this.ClassIDHG = ClassIDHG;
        this.RendaMedia = RendaMedia;
        this.RendaNominal = RendaNominal;
        this.PEADia = PEADia;
        this.IDHEducacao = IDHEducacao;
        this.ClassIDHE = ClassIDHE;
        this.IDHLongevidade = IDHLongevidade;
        this.ClassIDHL = ClassIDHL;
    }

    

    public Integer getCodigoIBGE() {
        return codigoIBGE;
    }

    public void setCodigoIBGE(Integer codigoIBGE) {
        this.codigoIBGE = codigoIBGE;
    }
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getMicroregiao() {
        return microregiao;
    }

    public void setMicroregiao(String microregiao) {
        this.microregiao = microregiao;
    }
    
    public String getSigla() {
        return sigla;
    }
    
    public void setSigla(String sigla) {
        this.sigla = sigla;
    }
    
    public String getRegiao() {
        return regiao;
    }

    public void setRegiao(String regiao) {
        this.regiao = regiao;
    }
    
    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }
    
    public Integer getPopulacao() {
        return populacao;
    }
    
    public void setPopulacao(Integer populacao) {
        this.populacao = populacao;
    }
    
    public Integer getDomicilios() {
        return domicilios;
    }

    public void setDomicilios(Integer domicilios) {
        this.domicilios = domicilios;
    }

    public Double getPIBTotal() {
        return PIBTotal;
    }

    public void setPIBTotal(Double PIBTotal) {
        this.PIBTotal = PIBTotal;
    }
    
    public Double getIDHGeral() {
        return IDHGeral;
    }
    
    public void setIDHGeral(Double IDHGeral) {
        this.IDHGeral = IDHGeral;
    }

    public Double getRendaMedia() {
        return RendaMedia;
    }

    public void setRendaMedia(Double RendaMedia) {
        this.RendaMedia = RendaMedia;
    }

    public Double getRendaNominal() {
        return RendaNominal;
    }

    public void setRendaNominal(Double RendaNominal) {
        this.RendaNominal = RendaNominal;
    }

    public Double getPEADia() {
        return PEADia;
    }

    public void setPEADia(Double PEADia) {
        this.PEADia = PEADia;
    }

    
    public Double getIDHEducacao() {
        return IDHEducacao;
    }
    
    public void setIDHEducacao(Double IDHEducacao) {
        this.IDHEducacao = IDHEducacao;
    }

    public Double getIDHLongevidade() {
        return IDHLongevidade;
    }
    
    public void setIDHLongevidade(Double IDHLongevidade) {
        this.IDHLongevidade = IDHLongevidade;
    }
    
}
