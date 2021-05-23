package com.ibank;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

public class Conta {
    public static NumberFormat dinheiro = NumberFormat.getCurrencyInstance(new Locale("pt","BR"));

    public static void GUI(String cpf) {
        Interface.Limpar();
        String dado = BancoDeDados.pegarConta(cpf);
        String nome = BancoDeDados.pegarNome(dado);
        String agencia = BancoDeDados.pegarAgencia(dado);
        String nconta = BancoDeDados.pegarNConta(dado);
        Double saldocc = Double.parseDouble(BancoDeDados.pegarSaldoCC(dado));
        Double saldocp = Double.parseDouble(BancoDeDados.pegarSaldoCP(dado));
        Double saldoci = Double.parseDouble(BancoDeDados.pegarSaldoCI(dado));
        System.out.println("..........................................");
        System.out.println(".                iBanco!                 .");
        System.out.println("..........................................");
        System.out.println("Olá, " + nome);
        System.out.println("..........................................");
        System.out.println("Agencia: " + agencia);
        System.out.println("Nº da Conta: " + nconta);
        System.out.println("Saldo Conta-corrente: " + dinheiro.format(saldocc));
        System.out.println("Saldo Conta-poupança: " + dinheiro.format(saldocp));
        System.out.println("Saldo Conta-investimento: " + dinheiro.format(saldoci));
        System.out.println("..........................................");
        System.out.println(".            1. Transferir               .");
        System.out.println(".            2. Depositar                .");
        System.out.println(".            3. Sacar                    .");
        System.out.println(".            4. Extrato                  .");
        System.out.println(".            5. Sair da Conta            .");
        System.out.println("..........................................");
        System.out.print("Digite o numero do que deseja: ");
        Scanner ler = new Scanner(System.in);
        try {
            switch (Integer.parseInt(ler.nextLine().replaceAll(" ", ""))) {
                case (1):
                    TransferirParaGUI(cpf);
                    break;
                case (2):
                    DepositarGUI(cpf);
                    break;
                case (3):
                    Sacar(cpf, false);
                    break;
                case (4):
                    Extrato(cpf);
                    break;
                case (5):
                    BemVindo.GUI();
                    break;
                case (0):
                    BancoDeDados.contas("Conta", cpf);
                    break;
                default:
                    GUI(cpf);
                    break;
            }
        } catch (Exception e) {
            GUI(cpf);
        }
    }

    public static void TransferirParaGUI(String cpf) {
        Interface.Limpar();
        System.out.println("....................................");
        System.out.println(".           Transferência          .");
        System.out.println("....................................");
        System.out.println(".  1. Para Conta-corrente          .");
        System.out.println(".  2. Para Conta-poupança          .");
        System.out.println(".  3. Para Conta-investimento      .");
        System.out.println(".  4. PIX Para outra Pessoa        .");
        System.out.println(".  5. Voltar                       .");
        System.out.println("....................................");
        System.out.print("Digite o numero do que deseja: ");
        Scanner ler = new Scanner(System.in);
        try {
            switch (Integer.parseInt(ler.nextLine().replaceAll(" ", ""))) {
                case (1):
                    TransferirDaGUI(cpf, "CC");
                    break;
                case (2):
                    TransferirDaGUI(cpf, "CP");
                    break;
                case (3):
                    TransferirDaGUI(cpf, "CI");
                    break;
                case (4):
                    TransferirDaGUI(cpf, "PIX");
                    break;
                case (5):
                    GUI(cpf);
                    break;
                default:
                    TransferirParaGUI(cpf);
                    break;
            }
        } catch (Exception e) {
            TransferirParaGUI(cpf);
        }
    }

    public static void TransferirDaGUI(String cpf, String para) {
        Interface.Limpar();
        System.out.println("....................................");
        System.out.println(".           Transferência          .");
        System.out.println("....................................");
        switch (para) {
            case "CC":
                System.out.println(".  1. Da Conta-poupança            .");
                System.out.println(".  2. Da Conta-investimento        .");
                break;
            case "CP":
                System.out.println(".  1. Da Conta-corrente            .");
                System.out.println(".  2. Da Conta-investimento        .");
                break;
            case "CI":
                System.out.println(".  1. Da Conta-corrente            .");
                System.out.println(".  2. Da Conta-poupança            .");
                break;
            default:
                System.out.println(".  1. Da Conta-corrente            .");
                System.out.println(".  2. Da Conta-poupança            .");
                System.out.println(".  3. Da Conta-investimento        .");
                break;
        }
        if (para.equals("CC") || para.equals("CP") || para.equals("CI")) {
            System.out.println(".  3. Voltar                       .");
        } else {
            System.out.println(".  4. Voltar                       .");
        }
        System.out.println("....................................");
        System.out.print("Digite o numero do que deseja: ");
        Scanner ler = new Scanner(System.in);
        int resp = -1;
        try {
            resp = Integer.parseInt(ler.nextLine().replaceAll(" ", ""));
        } catch (Exception e) {
            TransferirDaGUI(cpf, para);
        }
        switch (para) {
            case "CC":
                switch (resp) {
                    case (1):
                        Transferir(cpf, para, "CP", false);
                        break;
                    case (2):
                        Transferir(cpf, para, "CI", false);
                        break;
                    case (3):
                        TransferirParaGUI(cpf);
                        break;
                    default:
                        TransferirDaGUI(cpf, para);
                        break;
                }
                break;
            case "CP":
                switch (resp) {
                    case (1):
                        Transferir(cpf, para, "CC", false);
                        break;
                    case (2):
                        Transferir(cpf, para, "CI", false);
                        break;
                    case (3):
                        TransferirParaGUI(cpf);
                        break;
                    default:
                        TransferirDaGUI(cpf, para);
                        break;
                }
                break;
            case "CI":
                switch (resp) {
                    case (1):
                        Transferir(cpf, para, "CC", false);
                        break;
                    case (2):
                        Transferir(cpf, para, "CP", false);
                        break;
                    case (3):
                        TransferirParaGUI(cpf);
                        break;
                    default:
                        TransferirDaGUI(cpf, para);
                        break;
                }
                break;
            default:
                switch (resp) {
                    case (1):
                        Transferir(cpf, para, "CC", false);
                        break;
                    case (2):
                        Transferir(cpf, para, "CP", false);
                        break;
                    case (3):
                        Transferir(cpf, para, "CI", false);
                        break;
                    case (4):
                        TransferirParaGUI(cpf);
                        break;
                    default:
                        TransferirDaGUI(cpf, para);
                        break;
                }
                break;
        }
    }

    public static void Transferir(String cpf, String para, String da, boolean errou) {
        Interface.Limpar();
        String datipoextenso = da.equals("CC") ? "Conta-corrente" : (da.equals("CP") ? "Conta-poupança" : (da.equals("CI") ? "Conta-investimento" : ""));
        String paratipoextenso = para.equals("CC") ? "Conta-corrente" : (para.equals("CP") ? "Conta-poupança" : (para.equals("CI") ? "Conta-investimento" : "PIX"));
        System.out.println("................................");
        System.out.println("Transferência da "+datipoextenso+" para "+ paratipoextenso);
        System.out.println("................................");
        System.out.println("Saldo Disponível: " + dinheiro.format(((da.equals("CC") ? Double.parseDouble(BancoDeDados.pegarSaldoCC(BancoDeDados.pegarConta(cpf))) : (da.equals("CP") ? Double.parseDouble(BancoDeDados.pegarSaldoCP(BancoDeDados.pegarConta(cpf))) : (da.equals("CI") ? Double.parseDouble(BancoDeDados.pegarSaldoCI(BancoDeDados.pegarConta(cpf))) : ""))))));
        Scanner ler = new Scanner(System.in);
        if (errou) {
            System.out.println("Erro: Conta inexistente, Saldo Insuficiente ou Informou um valor inválido!");
            System.out.print("Deseja voltar ? (Sim = Inicio, Nao = Tentar Novamente): ");
            String resp = ler.nextLine();
            if (resp.equalsIgnoreCase("Sim") || resp.equalsIgnoreCase("S")) {
                TransferirParaGUI(cpf);
            } else Transferir(cpf, para, da, !resp.equalsIgnoreCase("Nao") && !resp.equalsIgnoreCase("N"));
        } else {
            String paracpf = "";
            if (para.equals("PIX")) {
                System.out.print("Digite CPF destinatário: ");
                paracpf = ler.nextLine();
                if (BancoDeDados.pegarConta(paracpf).equals("") || BancoDeDados.listaNegra(paracpf)) {
                    Transferir(cpf, para, da, true);
                }
            }
            System.out.print("Digite a quantia: ");
            Double quantia = 0.0;
            try {
                quantia = Double.parseDouble(ler.nextLine().replaceAll(" ", ""));
            } catch (Exception e) {
                Transferir(cpf, para, da, true);
            }
            double saldo = ((da.equals("CC") ? Double.parseDouble(BancoDeDados.pegarSaldoCC(BancoDeDados.pegarConta(cpf))) : (da.equals("CP") ? Double.parseDouble(BancoDeDados.pegarSaldoCP(BancoDeDados.pegarConta(cpf))) : (da.equals("CI") ? Double.parseDouble(BancoDeDados.pegarSaldoCI(BancoDeDados.pegarConta(cpf))) : 0.0))));
            if (quantia <= 0.0 || quantia > saldo) {
                Transferir(cpf, para, da, true);
            } else if (para.equals("CC")) {
                    BancoDeDados.definirSaldoCC(cpf, String.valueOf(Double.parseDouble(BancoDeDados.pegarSaldoCC(BancoDeDados.pegarConta(cpf)))+quantia));
                if (da.equals("CI")) {
                    BancoDeDados.definirSaldoCI(cpf, String.valueOf(Double.parseDouble(BancoDeDados.pegarSaldoCI(BancoDeDados.pegarConta(cpf)))-quantia));
                } else {
                    BancoDeDados.definirSaldoCP(cpf, String.valueOf(Double.parseDouble(BancoDeDados.pegarSaldoCP(BancoDeDados.pegarConta(cpf)))-quantia));
                }
            } else if (para.equals("CP")) {
                BancoDeDados.definirSaldoCP(cpf, String.valueOf(Double.parseDouble(BancoDeDados.pegarSaldoCP(BancoDeDados.pegarConta(cpf)))+quantia));
                if (da.equals("CI")) {
                    BancoDeDados.definirSaldoCI(cpf, String.valueOf(Double.parseDouble(BancoDeDados.pegarSaldoCI(BancoDeDados.pegarConta(cpf)))-quantia));
                } else {
                    BancoDeDados.definirSaldoCC(cpf, String.valueOf(Double.parseDouble(BancoDeDados.pegarSaldoCC(BancoDeDados.pegarConta(cpf)))-quantia));
                }
            } else if (para.equals("CI")) {
                BancoDeDados.definirSaldoCI(cpf, String.valueOf(Double.parseDouble(BancoDeDados.pegarSaldoCI(BancoDeDados.pegarConta(cpf)))+quantia));
                if (da.equals("CP")) {
                    BancoDeDados.definirSaldoCP(cpf, String.valueOf(Double.parseDouble(BancoDeDados.pegarSaldoCP(BancoDeDados.pegarConta(cpf)))-quantia));
                } else {
                    BancoDeDados.definirSaldoCC(cpf, String.valueOf(Double.parseDouble(BancoDeDados.pegarSaldoCC(BancoDeDados.pegarConta(cpf)))-quantia));
                }
            } else {
                BancoDeDados.definirSaldoCC(paracpf, String.valueOf(Double.parseDouble(BancoDeDados.pegarSaldoCC(BancoDeDados.pegarConta(paracpf)))+quantia));
                if (da.equals("CI")) {
                    BancoDeDados.definirSaldoCI(cpf, String.valueOf(Double.parseDouble(BancoDeDados.pegarSaldoCI(BancoDeDados.pegarConta(cpf)))-quantia));
                } else if (da.equals("CP")) {
                    BancoDeDados.definirSaldoCP(cpf, String.valueOf(Double.parseDouble(BancoDeDados.pegarSaldoCP(BancoDeDados.pegarConta(cpf)))-quantia));
                } else {
                    BancoDeDados.definirSaldoCC(cpf, String.valueOf(Double.parseDouble(BancoDeDados.pegarSaldoCC(BancoDeDados.pegarConta(cpf)))-quantia));
                }
            }
            if (para.equals("PIX")) {
                BancoDeDados.addExtrato(paracpf, "Transferência para Conta-corrente de CPF " + cpf + " + " + dinheiro.format(quantia));
                BancoDeDados.addExtrato(cpf, "Transferência para CPF " + paracpf + " da "+datipoextenso+" - " + dinheiro.format(quantia));
            } else {
                BancoDeDados.addExtrato(cpf, "Transferência para "+paratipoextenso+" da "+datipoextenso+" " + dinheiro.format(quantia));
            }
            Interface.Limpar();
            System.out.println("................................");
            System.out.println(".    Transferência Sucesso!    .");
            System.out.println("................................");
            if (!para.equals("PIX")) {
                System.out.println("Novo Saldo "+ paratipoextenso +": "+dinheiro.format(((para.equals("CC") ? Double.parseDouble(BancoDeDados.pegarSaldoCC(BancoDeDados.pegarConta(cpf))) : (para.equals("CP") ? Double.parseDouble(BancoDeDados.pegarSaldoCP(BancoDeDados.pegarConta(cpf))) : (para.equals("CI") ? Double.parseDouble(BancoDeDados.pegarSaldoCI(BancoDeDados.pegarConta(cpf))) : ""))))));
            }
            System.out.println("Novo Saldo "+ datipoextenso +": "+dinheiro.format(((da.equals("CC") ? Double.parseDouble(BancoDeDados.pegarSaldoCC(BancoDeDados.pegarConta(cpf))) : (da.equals("CP") ? Double.parseDouble(BancoDeDados.pegarSaldoCP(BancoDeDados.pegarConta(cpf))) : (da.equals("CI") ? Double.parseDouble(BancoDeDados.pegarSaldoCI(BancoDeDados.pegarConta(cpf))) : ""))))));
            System.out.println("................................");
            System.out.print("Pressione qualquer tecla para continuar...");
            try{System.in.read();}catch(Exception ignored){}
            GUI(cpf);
        }
    }


    public static void DepositarGUI(String cpf) {
        Interface.Limpar();
        System.out.println("....................................");
        System.out.println(".             Deposito             .");
        System.out.println("....................................");
        System.out.println(".  1. Depoistar Conta-corrente     .");
        System.out.println(".  2. Depoistar Conta-poupança     .");
        System.out.println(".  3. Depoistar Conta-investimento .");
        System.out.println(".  4. Voltar                       .");
        System.out.println("....................................");
        System.out.print("Digite o numero do que deseja: ");
        Scanner ler = new Scanner(System.in);
        try {
            switch (Integer.parseInt(ler.nextLine().replaceAll(" ", ""))) {
                case (1):
                    Depositar(cpf, "CC", false);
                    break;
                case (2):
                    Depositar(cpf, "CP", false);
                    break;
                case (3):
                    Depositar(cpf, "CI", false);
                    break;
                case (4):
                    GUI(cpf);
                    break;
                default:
                    DepositarGUI(cpf);
                    break;
            }
        } catch (Exception e) {
            DepositarGUI(cpf);
        }
    }

    public static void Extrato(String cpf) {
        Interface.Limpar();
        System.out.println("....................................");
        System.out.println(".              Extrato             .");
        System.out.println("....................................");
        System.out.println(BancoDeDados.pegarExtrato(BancoDeDados.pegarConta(cpf)).replaceAll("<>", ",").replaceAll("><", "\n"));
        System.out.print("Pressione qualquer tecla para continuar...");
        try{System.in.read();}catch(Exception ignored){}
        GUI(cpf);
    }

    public static void Depositar(String cpf, String tipo, boolean errou) {
        Interface.Limpar();
        String tipoextenso = tipo.equals("CC") ? "Conta-corrente" : (tipo.equals("CP") ? "Conta-poupança" : (tipo.equals("CI") ? "Conta-investimento" : ""));
        System.out.println("................................");
        System.out.println("Deposito em "+ tipoextenso);
        System.out.println("................................");
        Scanner ler = new Scanner(System.in);
        if (errou) {
            System.out.println("Erro: Informe um valor valido!");
            System.out.print("Deseja voltar ? (Sim = Inicio, Nao = Tentar Novamente): ");
            String resp = ler.nextLine();
            if (resp.equalsIgnoreCase("Sim") || resp.equalsIgnoreCase("S")) {
                DepositarGUI(cpf);
            } else Depositar(cpf, tipo, !resp.equalsIgnoreCase("Nao") && !resp.equalsIgnoreCase("N"));
        } else {
            System.out.print("Digite a quantia: ");
            Double quantia = 0.0;
            try {
                quantia = Double.parseDouble(ler.nextLine().replaceAll(" ", ""));
            } catch (Exception e) {
                Depositar(cpf, tipo, true);
            }
            if (quantia <= 0.0) {
                Depositar(cpf, tipo, true);
            } else if (tipo.equals("CC")) {
                BancoDeDados.definirSaldoCC(cpf, String.valueOf(Double.parseDouble(BancoDeDados.pegarSaldoCC(BancoDeDados.pegarConta(cpf)))+quantia));
            } else if (tipo.equals("CP")) {
                BancoDeDados.definirSaldoCP(cpf, String.valueOf(Double.parseDouble(BancoDeDados.pegarSaldoCP(BancoDeDados.pegarConta(cpf)))+quantia));
            } else if (tipo.equals("CI")) {
                BancoDeDados.definirSaldoCI(cpf, String.valueOf(Double.parseDouble(BancoDeDados.pegarSaldoCI(BancoDeDados.pegarConta(cpf)))+quantia));
            }
            BancoDeDados.addExtrato(cpf, "Deposito na "+ tipoextenso +" + "+dinheiro.format(quantia));
            Interface.Limpar();
            System.out.println("................................");
            System.out.println(".       Deposito Sucesso!      .");
            System.out.println("................................");
            System.out.println("Novo Saldo "+ tipoextenso +": "+dinheiro.format(((tipo.equals("CC") ? Double.parseDouble(BancoDeDados.pegarSaldoCC(BancoDeDados.pegarConta(cpf))) : (tipo.equals("CP") ? Double.parseDouble(BancoDeDados.pegarSaldoCP(BancoDeDados.pegarConta(cpf))) : (tipo.equals("CI") ? Double.parseDouble(BancoDeDados.pegarSaldoCI(BancoDeDados.pegarConta(cpf))) : ""))))));
            System.out.println("................................");
            System.out.print("Pressione qualquer tecla para continuar...");
            try{System.in.read();}catch(Exception ignored){}
            GUI(cpf);
        }
    }

    public static void Sacar(String cpf, boolean errou) {
        Interface.Limpar();
        System.out.println("................................");
        System.out.println(".             Sacar            .");
        System.out.println("................................");
        System.out.println("Saldo Disponível: " + dinheiro.format(Double.parseDouble(BancoDeDados.pegarSaldoCC(BancoDeDados.pegarConta(cpf)))));
        Scanner ler = new Scanner(System.in);
        if (errou) {
            System.out.println("Erro: Saldo Insuficiente ou Informou um valor inválido!");
            System.out.print("Deseja voltar ? (Sim = Inicio, Nao = Tentar Novamente): ");
            String resp = ler.nextLine();
            if (resp.equalsIgnoreCase("Sim") || resp.equalsIgnoreCase("S")) {
                GUI(cpf);
            } else Sacar(cpf, !resp.equalsIgnoreCase("Nao") && !resp.equalsIgnoreCase("N"));
        } else {
            System.out.print("Digite a quantia: ");
            Double quantia = 0.0;
            try {
                quantia = Double.parseDouble(ler.nextLine().replaceAll(" ", ""));
            } catch (Exception e) {
                Sacar(cpf, true);
            }
            if (quantia <= 0.0 || quantia > Double.parseDouble(BancoDeDados.pegarSaldoCC(BancoDeDados.pegarConta(cpf)))) {
                Sacar(cpf, true);
            } else {
                BancoDeDados.definirSaldoCC(cpf, String.valueOf(Double.parseDouble(BancoDeDados.pegarSaldoCC(BancoDeDados.pegarConta(cpf)))-quantia));
                BancoDeDados.addExtrato(cpf, "Sacou - "+dinheiro.format(quantia));
                Interface.Limpar();
                System.out.println("................................");
                System.out.println(".        Sacou Sucesso!        .");
                System.out.println("................................");
                System.out.println("Novo Saldo Conta-corrente: " + dinheiro.format(Double.parseDouble(BancoDeDados.pegarSaldoCC(BancoDeDados.pegarConta(cpf)))));
                System.out.println("................................");
                System.out.print("Pressione qualquer tecla para continuar...");
                try{System.in.read();}catch(Exception ignored){}
                GUI(cpf);
            }
        }
    }

}