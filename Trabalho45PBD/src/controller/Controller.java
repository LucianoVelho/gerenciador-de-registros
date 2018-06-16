/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Hash;
import model.Usuario;

/**
 *
 * @author GUSTAVO JUNG
 */
public class Controller {

    RandomAccessFile arquivo = null;

    public void newUser() {
        Usuario u = new Usuario();
        u.setCodigo(Integer.parseInt(JOptionPane.showInputDialog("Digite o código:")));
        u.setNome(JOptionPane.showInputDialog("Digite o nome:"));
        u.setQualificacao(JOptionPane.showInputDialog("Digite a qualificaçao:"));
        u.setSalario(Double.parseDouble(JOptionPane.showInputDialog("Digite o salario:")));

        int hash = (int) u.getCodigo() % 28;
        Hash h = new Hash();

        h.setUsuario(u);
        try {
            arquivo = new RandomAccessFile("data", "rw");
            arquivo.seek(hash * 110);
            System.out.println("" + arquivo.readInt());
            int codigo = arquivo.readInt();
            if (codigo == -1) {
                arquivo.seek((hash * 110));
                arquivo.readInt();
                try {
                    //grava nome formatado com tamanho de 30 caracteres
                    arquivo.writeInt(u.getCodigo());
                    arquivo.writeChars(String.format("%1$30s", u.getNome()));
                    arquivo.writeChars(String.format("%1$15s", u.getQualificacao()));
                    arquivo.writeDouble(u.getSalario());

                    arquivo.close();
                } catch (IOException ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else {
                arquivo.seek((hash * 110) + 106);
                int proximo = arquivo.readInt();
                if (proximo == -1) {
                    arquivo.seek((hash * 110) + 106);

                    long divi = arquivo.length() / 110;
                    arquivo.writeInt((int) (divi));
                    arquivo.seek(arquivo.length());
                    try {
                        arquivo.writeInt((int) divi);
                        //grava nome formatado com tamanho de 30 caracteres
                        arquivo.writeInt(u.getCodigo());
                        arquivo.writeChars(String.format("%1$30s", u.getNome()));
                        arquivo.writeChars(String.format("%1$15s", u.getQualificacao()));
                        arquivo.writeDouble(u.getSalario());
                        arquivo.writeInt(-1);
                        arquivo.close();
                    } catch (IOException ex) {
                        Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                    }

                } else {
                    int atual = proximo;
                    do {
                                                System.out.println("dsadsad"+proximo);

                        atual = proximo;
                        arquivo.seek(((proximo) * 110) + 106);
                        proximo = arquivo.readInt();
                    } while (proximo != -1);
                    arquivo.seek((atual * 110) + 106);
                    long divi = arquivo.length() / 110;
                    arquivo.writeInt((int) (divi));
                    arquivo.seek(arquivo.length());
                    try {
                        arquivo.writeInt((int) divi);
                        //grava nome formatado com tamanho de 30 caracteres
                        arquivo.writeInt(u.getCodigo());
                        arquivo.writeChars(String.format("%1$30s", u.getNome()));
                        arquivo.writeChars(String.format("%1$15s", u.getQualificacao()));
                        arquivo.writeDouble(u.getSalario());
                        arquivo.writeInt(-1);
                        arquivo.close();
                    } catch (IOException ex) {
                        Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }

            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void arquivo() {
        //Abre arquivo para leitura e gravacao
        try {
            arquivo = new RandomAccessFile("data", "rw");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        //grava a quantidade de N no arquivo
        for (int i = 0; i < 28; i++) {
            try {
                arquivo.writeInt(i);
            } catch (IOException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
            // salario aleatorio entre 0 e 1000
            int codigo = -1;
            try {
                Hash h = new Hash();
                arquivo.writeInt(codigo); // grava codigo
            } catch (IOException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                //grava nome formatado com tamanho de 30 caracteres
                arquivo.writeChars(String.format("%1$30s", ""));
            } catch (IOException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                //grava qualificacao formatado com tamanho de 15 caracteres
                arquivo.writeChars(String.format("%1$15s", ""));
            } catch (IOException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                arquivo.writeDouble(0);

            } catch (IOException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                arquivo.writeInt(-1);

            } catch (IOException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        long atam = 0;
        try {
            atam = arquivo.length(); //recupera o tamanho do arquivo
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("a arquivo tem o tamanho de " + atam + " bytes.");
        try {
            arquivo.close(); //fecha o arquivo
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void Leitura() {
        try {
            //Abre arquivo para leitura
            arquivo = new RandomAccessFile("data", "r");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        double atam = 0;

        try {
            atam = arquivo.length();//recupera o tamanho do arquivo
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("o arquivo tem o tamanho de " + atam + " bytes.");

        int codigoH = 0;
        int codUser = 0;
        try {
            for (int i = 0; i < (arquivo.length() / 110); i++) {
                try {
                    codigoH = arquivo.readInt(); //le 4 bytes do arquivo e converte para int
                } catch (IOException ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    codUser = arquivo.readInt(); //le 4 bytes do arquivo e converte para int
                } catch (IOException ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                }

                String nome = " "; // variavel auxiliar para contruir o nome
                for (int j = 0; j < 30; j++) {
                    try {
                        nome += arquivo.readChar(); //le 2 bytes do arquivo e converte para char
                    } catch (IOException ex) {
                        Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                String qualificacao = " "; // variavel auxiliar para contruir o nome
                for (int k = 0; k < 15; k++) {
                    try {
                        qualificacao += arquivo.readChar(); //le 2 bytes do arquivo e converte para char
                    } catch (IOException ex) {
                        Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }//le 8 bytes do arquivo e converte para double
                double salario = 0;
                try {
                    salario = arquivo.readDouble();
                } catch (IOException ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
                int proxima = 0;
                try {
                    proxima = arquivo.readInt();
                } catch (IOException ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println(codigoH + " \t| " + codUser + " \t| " + nome + " \t| " + qualificacao + "\t| " + salario + "\t| " + proxima); // imprime tupla
            }
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
