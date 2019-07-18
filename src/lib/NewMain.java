/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib;

/**
 *
 * @author josef
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        for (int i = 0; i < 20; i++) {
            
            System.out.println(
                Pendiente.getInstacePendiente(
                        new Pendiente.Point(5, 6),
                        new Pendiente.Point(7, 1)
                ).getEcuation()
        );
        }
        
    }

}
