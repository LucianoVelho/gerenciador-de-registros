
import controller.Controller;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author GUSTAVO JUNG
 */
public class Main {
    public static void main(String[]args){
        Controller c = new Controller();
       int menu1 = Integer.parseInt(JOptionPane.showInputDialog("---MENU---\n\n"
                + "1- Adicionar produto \n"
                + "2- Listar \n"
                + "3- Remover produto da lista \n"
                + "4- Alterar \n"
                + "5- Arquivo \n"));
    
             switch(menu1){
            case 1:
               
               c.newUser();
               c.Leitura();
                break;
            case 2:
                
                c.Leitura();
                break;
            case 3:
               c.remover();
               c.Leitura();
                break;
            case 4:
               c.alterar();
               c.Leitura();
                break;
            case 5:
                c.arquivo();
                c.Leitura();
        }
    
    }
}
