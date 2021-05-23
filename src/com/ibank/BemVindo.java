package com.ibank;

import java.util.Scanner;
import java.util.regex.Pattern;

public class BemVindo {

    public static void GUI(){
        Interface.Limpar();
        System.out.println("................................");
        System.out.println(".      Bem-vindo ao iBanco!    .");
        System.out.println("................................");
        System.out.println(".     1. Entrar na Conta       .");
        System.out.println(".     2. Criar nova Conta      .");
        System.out.println("................................");
        System.out.print("Digite o numero do que deseja: ");
        Scanner ler = new Scanner(System.in);
        try {
            switch (Integer.parseInt(ler.nextLine().replaceAll(" ", ""))) {
                case (1):
                    Entrar(false);
                    break;
                case (2):
                    Cadastro();
                    break;
                case (0):
                    BancoDeDados.contas("BemVindo", "");
                    break;
                default:
                    GUI();
                    break;
            }
        } catch (Exception e) {
            GUI();
        }
    }

    public static void Cadastro() {
        Interface.Limpar();
        Scanner ler = new Scanner(System.in);
        System.out.println("................................");
        System.out.println(".           Cadastro           .");
        System.out.println("................................");
        System.out.println("AVISO: Se desejar voltar digite voltar em qualquer campo.");
        System.out.print("Digite seu nome: ");
        String nome = ler.nextLine();
        while (BancoDeDados.listaNegra(nome)) {
            System.out.println("Nome Inválido, Tente novamente!");
            System.out.print("Digite seu nome: ");
            nome = ler.nextLine();
        }
        if (nome.equalsIgnoreCase("voltar")) {GUI();}
        System.out.print("Digite seu CPF: ");
        String cpf = ler.nextLine();
        while (!(BancoDeDados.pegarConta(cpf).equals("") && !BancoDeDados.listaNegra(cpf))) {
            System.out.println("CPF Já Cadastrado ou Inválido, Tente novamente!");
            System.out.print("Digite seu CPF: ");
            cpf = ler.nextLine();
        }
        if (cpf.equalsIgnoreCase("voltar")) {GUI();}
        String[] AC = BancoDeDados.novaConta(nome, cpf).split(Pattern.quote(","));
        Interface.Limpar();
        System.out.println("................................");
        System.out.println(".       Conta Cadastrada!      .");
        System.out.println("................................");
        System.out.println(".     Agencia: "+AC[0]+"          .");
        System.out.println(".   Nº da Conta "+AC[1]+"     .");
        System.out.println("................................");
        System.out.print("Pressione qualquer tecla para continuar...");
        try{System.in.read();}catch(Exception ignored){}
        GUI();
    }

    public static void Entrar(boolean re) {
        Interface.Limpar();
        Scanner ler = new Scanner(System.in);
        System.out.println("................................");
        System.out.println(".            Entrar            .");
        System.out.println("................................");
        if (re) {
            System.out.println("Erro: Conta não existente!");
            System.out.print("Deseja voltar ao inicio? (Sim = Inicio, Nao = Tentar Novamente): ");
            String resp = ler.nextLine();
            if (resp.equalsIgnoreCase("Sim") || resp.equalsIgnoreCase("S")) {
                GUI();
            } else Entrar(!resp.equalsIgnoreCase("Nao") && !resp.equalsIgnoreCase("N"));
        } else {
            System.out.print("Digite seu CPF: ");
            String cpf = ler.nextLine();
            if (BancoDeDados.pegarConta(cpf).equals("") || BancoDeDados.listaNegra(cpf)) {
                Entrar(true);
            } else {
                Conta.GUI(cpf);
            }
        }
    }

}
