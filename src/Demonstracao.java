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

import Entities.Historico;
import Entities.Municipio;
import Entities.Perfil;
import Services.Arquivo;
import Services.CRUD;
import Services.Ujeverson;
import Services.Tratamento;
import java.text.ParseException;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

/**
 * @author Willian Junior <willianjunior.c.f@gmail.com>
 * @date 30/05/2024
 * @brief Class teste
 */

public class Demonstracao {
    public static void main(String[] args) throws ParseException {
        
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        
        boolean perfil = true;
        boolean operacao = false;
        boolean update = false;
        int ibge = 0;
        String mun = "";
        int index = 0;
        
        Historico hist = new Historico();
        
        System.out.println("Bem-vindo ao Densus");
        System.out.println("Por favor digite as seguintes informações");
        while(perfil){
            System.out.print("Nome: ");
            String nome = sc.next();
            if(Tratamento.Caracteres(nome)){
                System.out.println("CPF: ");
                String CPF = sc.next();
                if(Tratamento.Numerico(CPF) && Perfil.ValidarCPF(CPF)){
                    System.out.println("Cadastro concluido");
                    hist = new Historico(nome, CPF);
                    perfil = false;
                    operacao = true;
                    break;
                } else System.out.println("Digite um CPF valido");
            } else System.out.println("Digite um nome valido");
        }
        Arquivo.In();
        while(operacao){
        System.out.println("Digite uma operação:");
        System.out.println("1 - Criar");
        System.out.println("2 - Read");
        System.out.println("3 - Update");
        System.out.println("4 - Delete");
        System.out.println("5 - Exportar");
        System.out.println("6 - Sair");
        int op = sc.nextInt();
        
        switch(op){
            case 1 -> {
                CRUD.Create();
                System.out.println("As seguintes informações foram adicionadas a planilha");
                System.out.println("Densidade Demográfica");
                System.out.println("PIB per Capita Total");
                System.out.println("Classificação IDH");
                break;
            }
            case 2 -> {
                for(int i = 0; i < Arquivo.CSVIn.size(); i++){
                    System.out.println(CRUD.Reader(i));
                }
                break;
            }
            case 3 -> {
                update = true;
                while(update){
                System.out.println("Selecione a seguinte opção que deve mudar");
                System.out.println("1 - População");
                System.out.println("2 - Domicilios");
                System.out.println("3 - PIB Total");
                System.out.println("4 - IDH Desenvolvimento Humano Geral");
                System.out.println("5 - Renda Média");
                System.out.println("6 - Renda Nominal");
                System.out.println("7 - PEA Dia");
                System.out.println("8 - IDH Dimensão Educação");
                System.out.println("9 - IDH Dimensão Longevidade");
                System.out.println("10 - Sair");
                int up_op = sc.nextInt();
                if(up_op < 10 && up_op > 0){
                System.out.println("Como deseja localizar a linha?");
                System.out.println("1 - Codigo IBGE");
                System.out.println("2 - Nome do municipio");
                int lin_op = sc.nextInt();
                ibge = 0;
                mun = "";
                if(lin_op == 1){
                    System.out.print("Codigo IBGE: ");
                    ibge = sc.nextInt();
                } else if(lin_op == 2){
                    System.out.println("Nome do municipio: ");
                    mun = sc.next();
                } else System.out.println("Digite um valor valido");
                index = Tratamento.Pesquisa(ibge, mun);
                }
                switch(up_op){
                    case 1 -> {
                        System.out.println("Digite o novo valor:");
                        int pop = sc.nextInt();
                        CRUD.UpdatePopulacao(index, pop, hist);
                        break;
                    }
                    case 2 -> {
                        System.out.println("Digite o novo valor:");
                        double com = sc.nextDouble();
                        CRUD.UpdateDomicilios(index, ibge, hist);
                        break;
                    }
                    case 3 -> {
                        System.out.println("Digite o novo valor:");
                        double pib = sc.nextDouble();
                        CRUD.UpdatePIBTotal(index, pib, hist);
                        break;
                    }
                    case 4 -> {
                        System.out.println("Digite o novo valor:");
                        double idh = sc.nextDouble();
                        CRUD.UpdateIDHG(index, idh, hist);
                        break;
                    }
                    case 5 -> {
                        System.out.println("Digite o novo valor:");
                        double rendM = sc.nextDouble();
                        CRUD.UpdateRendaMedia(index, rendM, hist);
                        break;
                    }
                    case 6 -> {
                        System.out.println("Digite o novo valor:");
                        double rendN = sc.nextDouble();
                        CRUD.UpdateRendaNominal(index, rendN, hist);
                        break;
                    }
                    case 7 -> {
                        System.out.println("Digite o novo valor:");
                        int pea = sc.nextInt();
                        CRUD.UpdatePEADia(index, pea, hist);
                        break;
                    }
                    case 8 -> {
                        System.out.println("Digite o novo valor:");
                        double idhe = sc.nextDouble();
                        CRUD.UpdateIDHE(index, ibge, hist);
                        break;
                    }
                    case 9 -> {
                        System.out.println("Digite o novo valor:");
                        double idhl = sc.nextDouble();
                        CRUD.UpdateIDHL(index, ibge, hist);
                        break;
                    }
                    case 10 -> {
                        update = false;
                        break;
                    }
                }
                }
            }
            case 4 -> {
                System.out.println("Qual linha você deseja apagar?");
                System.out.println("Como deseja localizar a linha?");
                System.out.println("1 - Codigo IBGE");
                System.out.println("2 - Nome do municipio");
                int lin_del = sc.nextInt();
                ibge = 0;
                mun = "";
                if(lin_del == 1){
                    System.out.print("Codigo IBGE: ");
                    ibge = sc.nextInt();
                } else if(lin_del == 2){
                    System.out.println("Nome do municipio: ");
                    mun = sc.next();
                } else System.out.println("Digite um valor valido");
                index = Tratamento.Pesquisa(ibge, mun);
                break;
            }
            case 5 -> {
                Arquivo.Out();
                System.out.println("Arquivo exportado com sucesso");
                System.out.println("C:\\Projeto Integrador\\Out");
                break;
            }
            case 6 -> {
                System.out.println("Saindo...");
                operacao = false;
                break;
            }
        }
        }
        
    }
}
