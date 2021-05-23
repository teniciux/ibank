package com.ibank;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.regex.Pattern;

public class BancoDeDados {

    public static String dados = "{\"Contas\": []}";

    public static boolean listaNegra(String valor) {
        return (valor.replaceAll(" ", "").equals("") || valor.contains("\\") || valor.contains("\"") || valor.contains(",") || valor.contains(":") || valor.contains("}") || valor.contains("{") || valor.contains("[") || valor.contains("]"));
    }

    public static String novaConta(String nome, String cpf) {
        Random rand = new Random();
        String agencia = (rand.nextInt(9000) + 1000) +"-"+ rand.nextInt(10);
        String nconta = (rand.nextInt(90000000) + 10000000) +"-"+ rand.nextInt(10);
        String saldocc = "0";
        String saldocp = "0";
        String saldoci = "0";
        String dado = "{ \"CPF\": \""+cpf+"\"," +
                      " \"Nome\": \""+nome+"\"," +
                      " \"Agencia\": \""+agencia+"\"," +
                      " \"NConta\": \""+nconta+"\"," +
                      " \"Extrato\": \"\"," +
                      " \"SaldoCC\": \""+saldocc+"\"," +
                      " \"SaldoCP\": \""+saldocp+"\"," +
                      " \"SaldoCI\": \""+saldoci+"\" }";
        if (!(dados.split(Pattern.quote("["))[1].split(Pattern.quote("]"))[0].equals(""))) {
            dado = "," + dado;
        }
        dados = "{\"Contas\":[" + dados.split(Pattern.quote("["))[1].split(Pattern.quote("]"))[0] + dado + "]}";
        return agencia+","+nconta;
    }

    public static String pegarConta(String cpf) {
       String[] preDado = dados.split(Pattern.quote("{"));
        for (String i : preDado) {
            if (i.contains("CPF") && i.split(Pattern.quote("CPF\": \""))[1].split(Pattern.quote("\""))[0].equals(cpf)){
                return i.split(Pattern.quote("}"))[0];
            }
        }
        return "";
    }

    public static String pegarNome(String dado) { return dado.split(Pattern.quote("Nome\": \""))[1].split(Pattern.quote("\""))[0]; }

    public static String pegarAgencia(String dado) { return dado.split(Pattern.quote("Agencia\": \""))[1].split(Pattern.quote("\""))[0]; }

    public static String pegarNConta(String dado) { return dado.split(Pattern.quote("NConta\": \""))[1].split(Pattern.quote("\""))[0]; }

    public static String pegarExtrato(String dado) { return dado.split(Pattern.quote("Extrato\": \""))[1].split(Pattern.quote("\""))[0]; }

    public static String pegarSaldoCC(String dado) { return dado.split(Pattern.quote("SaldoCC\": \""))[1].split(Pattern.quote("\""))[0]; }

    public static String pegarSaldoCP(String dado) { return dado.split(Pattern.quote("SaldoCP\": \""))[1].split(Pattern.quote("\""))[0]; }

    public static String pegarSaldoCI(String dado) { return dado.split(Pattern.quote("SaldoCI\": \""))[1].split(Pattern.quote("\""))[0]; }

    public static void definirSaldoCC(String cpf, String valor) { definirValor(cpf, "SaldoCC", valor); }

    public static void definirSaldoCP(String cpf, String valor) { definirValor(cpf, "SaldoCP", valor); }

    public static void definirSaldoCI(String cpf, String valor) { definirValor(cpf, "SaldoCI", valor); }

    public static void definirValor(String cpf, String c, String valor) {
        String[] pre = dados.split(Pattern.quote("CPF\": \"" + cpf + "\""))[1].split(Pattern.quote(dados.split(Pattern.quote("CPF\": \"" + cpf + "\""))[1].split(Pattern.quote(c + "\": \""))[0]))[1].split(Pattern.quote("\""));
        dados = dados.split(Pattern.quote("CPF\": \""+cpf+"\""))[0] + "CPF\": \""+cpf+"\"" + dados.split(Pattern.quote("CPF\": \""+cpf+"\""))[1].split(Pattern.quote(c+"\": \""))[0] + c+"\": \"" + valor;
        int contador = 0;
        for (String i : pre) {
            if (contador > 2) {
                dados = dados + "\"" + i;
            }
            contador++;
        }
    }

    public static void addExtrato(String cpf, String valor) {
        String extrato = pegarExtrato(pegarConta(cpf));
        if (extrato.equals("")) {
            definirValor(cpf, "Extrato", valor.replaceAll(",", "<>") + " - " + DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").format(LocalDateTime.now()));
        } else {
            definirValor(cpf, "Extrato", extrato + "><" + valor.replaceAll(",", "<>") + " - " + DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").format(LocalDateTime.now()));
        }
    }

    public static void contas(String voltar, String cpf) {
        Interface.Limpar();
        System.out.println("................................");
        System.out.println(".            Contas!           .");
        System.out.println("................................");
        String pre = "";
        int contadorDeTabs = 0;
        for (char c : dados.toCharArray()) {
            if (c != '}' && c != ']') {
             pre += c;
            }
            if (c == '{' || c == '}' || c == '[' || c == ']' || c == ',') {
                if (c == '{' || c == '[') {
                    contadorDeTabs++;
                } else if (c != ',') {
                    contadorDeTabs = contadorDeTabs - 1;
                }
                pre += "\n";
                for (int i = 0; i < contadorDeTabs;i++){
                    pre += "\t";
                }
                if (c == '}' || c == ']') {
                    pre += c;
                }
            }
        }
        System.out.println(pre);
        System.out.print("Pressione qualquer tecla para continuar...");
        try{System.in.read();}catch(Exception ignored){}
        if (voltar.equals("Conta")) {Conta.GUI(cpf);}
        else if (voltar.equals("BemVindo")) {BemVindo.GUI();}
    }

}

