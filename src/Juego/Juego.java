package Juego;

import javax.swing.*;
import java.awt.*;

public class Juego extends Canvas implements Runnable {
    private static final long serialVersionUID = 1l;

    private static final int ANCHO = 800;
    private static final int ALTO = 600;

    private static boolean EnFuncionamiento= false;
    private static final String NOMBRE ="Juego";

    private static JFrame ventana;
    private static Thread Thread; //Creamos un thread para dividir el trabajo de ejecución

    private Juego(){
    setPreferredSize(new Dimension(ANCHO,ALTO));

    ventana = new JFrame(NOMBRE);
    ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setResizable(false);
        ventana.setLayout(new BorderLayout());
        ventana.add(this,BorderLayout.CENTER);
        ventana.pack();
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
    }

    public static void main(String[] args) {
        Juego juego = new Juego();
        juego.iniciar();
    }
    private void iniciar(){
        EnFuncionamiento= true;
        Thread= new Thread(this, "Graficos");
        Thread.start();//Creamos un segundo Thread para dividir el trbabajo
    }
    private void Detener(){
        EnFuncionamiento= false;
    }

    @Override
    public void run() {
        while (EnFuncionamiento){

        }

        System.out.print("El thread 2 se esta ejecutando con éxito");
    }//Aquí revisamos que el primer thread implementado en la interfaz runnable
    //se esté ejecutando, mas tarde usaremos threads para el multijugador
}
