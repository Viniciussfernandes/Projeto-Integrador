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

// Essa classe vai ser responsavel por armazenar o objeto dentro da lista
public class Municipio {
    
    // Variaveis basicas
    private Integer codigoIBGE;
    private String nome;
    private String microregiao;
    private String sigla;
    private String regiao;
    private Double area;
    private Integer populacao;
    private Double domicilios;
    private Double PIBTotal;
    private Double IDHGeral;
    private Double RendaMedia;
    private Double RendaNominal;
    private Integer PEADia;
    private Double IDHEducacao;
    private Double IDHLongevidade;
    
    // Variaveis da opera��o Create
    private Double Densidade;
    private Double PIBpC;
    private String ClassIDHG;
    private String ClassIDHE;
    private String ClassIDHL;
    
    // Construtor com as variaveis basicas, as outras ser�o colocadas no objeto atrav�s de sets
    public Municipio(Integer codigoIBGE, String nome, String microregiao, String sigla,
            String regiao, Double area, Integer populacao, Double domicilios, Double PIBTotal,
            Double IDHGeral, Double RendaMedia, Double RendaNominal, Integer PEADia,
            Double IDHEducacao, Double IDHLongevidade) {
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
    
    // gets
    public Integer getCodigoIBGE() {
        return codigoIBGE;
    }

    public String getNome() {
        return nome;
    }

    public String getMicroregiao() {
        return microregiao;
    }

    public String getSigla() {
        return sigla;
    }

    public String getRegiao() {
        return regiao;
    }

    public Double getArea() {
        return area;
    }

    public Integer getPopulacao() {
        return populacao;
    }

    public Double getDomicilios() {
        return domicilios;
    }

    public Double getPIBTotal() {
        return PIBTotal;
    }

    public Double getIDHGeral() {
        return IDHGeral;
    }

    public Double getRendaMedia() {
        return RendaMedia;
    }

    public Double getRendaNominal() {
        return RendaNominal;
    }

    public Integer getPEADia() {
        return PEADia;
    }

    public Double getIDHEducacao() {
        return IDHEducacao;
    }

    public Double getIDHLongevidade() {
        return IDHLongevidade;
    }

    public Double getDensidade() {
        return Densidade;
    }

    public Double getPIBpC() {
        return PIBpC;
    }

    public String getClassIDHG() {
        return ClassIDHG;
    }

    public String getClassIDHE() {
        return ClassIDHE;
    }

    public String getClassIDHL() {
        return ClassIDHL;
    }

    // sets 
    public void setCodigoIBGE(Integer codigoIBGE) {
        this.codigoIBGE = codigoIBGE;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setMicroregiao(String microregiao) {
        this.microregiao = microregiao;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public void setRegiao(String regiao) {
        this.regiao = regiao;
    }

    public void setArea(Double area) {
        this.area = area;
    }
    
    // Sets para a opera��o Create.
    public void setDensidade(Double Densidade) {
        this.Densidade = Densidade;
    }

    public void setPIBpC(Double PIBpC) {
        this.PIBpC = PIBpC;
    }

    public void setClassIDHG(String ClassIDHG) {
        this.ClassIDHG = ClassIDHG;
    }

    public void setClassIDHE(String ClassIDHE) {
        this.ClassIDHE = ClassIDHE;
    }

    public void setClassIDHL(String ClassIDHL) {
        this.ClassIDHL = ClassIDHL;
    }
    
    // Sets para a Opera��o Update.
    public void setPopulacao(Integer populacao) {
        this.populacao = populacao;
    }

    public void setDomicilios(Double domicilios) {
        this.domicilios = domicilios;
    }

    public void setPIBTotal(Double PIBTotal) {
        this.PIBTotal = PIBTotal;
    }

    public void setIDHGeral(Double IDHGeral) {
        this.IDHGeral = IDHGeral;
    }

    public void setRendaMedia(Double RendaMedia) {
        this.RendaMedia = RendaMedia;
    }

    public void setRendaNominal(Double RendaNominal) {
        this.RendaNominal = RendaNominal;
    }

    public void setPEADia(Integer PEADia) {
        this.PEADia = PEADia;
    }

    public void setIDHEducacao(Double IDHEducacao) {
        this.IDHEducacao = IDHEducacao;
    }

    public void setIDHLongevidade(Double IDHLongevidade) {
        this.IDHLongevidade = IDHLongevidade;
    }
    
    // Metodos para a opera��o Create. As formulas est�o presente no classroom.
    public static double Densidade(Integer populacao, Double area){
        return populacao / area;
    }
    
    public static double PIBpC(Double PIBTotal, Integer populacao){
        return PIBTotal / populacao;
    }
    
    public static String classIDH(Double IDH){
        if(IDH > 0.80){
            return "Muito alto";
        } else if(IDH > 0.70 && IDH <= 0.80){
            return "Alto";
        } else if(IDH > 0.55 && IDH <= 0.70){
            return "Medio";
        } else if(IDH <= 0.55){
            return "Baixo";
        } else return "N�o foi possivel calcular";
    }
}