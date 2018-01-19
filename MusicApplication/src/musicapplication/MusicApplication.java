/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musicapplication;
import JavaMVCControllers.*;

/**
 *
 * @author waqas
 */
public class MusicApplication {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // creating a controller object and start application
	System.out.println("MVC based project");
        MusicAppicationController controller = new MusicAppicationController();
        controller.startApplication();
    }
    
}
