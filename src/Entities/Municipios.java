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
    private Double populacao;
    private Double domicilios;
    private Double Densidade;
    private Double PIBTotal;
    private Double PIBpC;
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

    public Municipios(Integer codigoIBGE, String nome, String microregiao, String sigla, String regiao, Double area, Double populacao, Double domicilios, Double PIBTotal, Double IDHGeral, Double RendaMedia, Double RendaNominal, Double PEADia, Double IDHEducacao, Double IDHLongevidade) {
        this.codigoIBGE = codigoIBGE;
        this.nome = nome;
        this.microregiao = microregiao;
        this.sigla = sigla;
        this.regiao = regiao;
        this.area = area;
        this.populacao = populacao;
        this.domicilios = domicilios;
        this.PIBTotal = PIBTotal;
        this.IDHGeral = IDHGeral;
        this.RendaMedia = RendaMedia;
        this.RendaNominal = RendaNominal;
        this.PEADia = PEADia;
        this.IDHEducacao = IDHEducacao;
        this.IDHLongevidade = IDHLongevidade;
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
    
    public Double getPopulacao() {
        return populacao;
    }
    
    public void setPopulacao(Double populacao) {
        this.populacao = populacao;
    }
    
    public Double getDomicilios() {
        return domicilios;
    }

    public void setDomicilios(Double domicilios) {
        this.domicilios = domicilios;
    }

    public Double getDensidade() {
        return Densidade;
    }

    public void setDensidade(Double Densidade) {
        this.Densidade = Densidade;
    }
    
    public Double getPIBTotal() {
        return PIBTotal;
    }

    public void setPIBTotal(Double PIBTotal) {
        this.PIBTotal = PIBTotal;
    }

    public Double getPIBpC() {
        return PIBpC;
    }

    public void setPIBpC(Double PIBpC) {
        this.PIBpC = PIBpC;
    }
    
    public Double getIDHGeral() {
        return IDHGeral;
    }
    
    public void setIDHGeral(Double IDHGeral) {
        this.IDHGeral = IDHGeral;
    }

    public String getClassIDHG() {
        return ClassIDHG;
    }

    public void setClassIDHG(String ClassIDHG) {
        this.ClassIDHG = ClassIDHG;
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

    public String getClassIDHE() {
        return ClassIDHE;
    }

    public void setClassIDHE(String ClassIDHE) {
        this.ClassIDHE = ClassIDHE;
    }
    
    public Double getIDHLongevidade() {
        return IDHLongevidade;
    }
    
    public void setIDHLongevidade(Double IDHLongevidade) {
        this.IDHLongevidade = IDHLongevidade;
    }

    public String getClassIDHL() {
        return ClassIDHL;
    }

    public void setClassIDHL(String ClassIDHL) {
        this.ClassIDHL = ClassIDHL;
    }
    
}