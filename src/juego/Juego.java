package juego;

import control.Teclado;
import org.w3c.dom.ls.LSOutput;

import javax.swing.*;
import java.awt.*;



public class Juego extends Canvas implements Runnable {
    private static final long serialVersionUID = 1l;

    private static final int ANCHO = 800;
    private static final int ALTO = 600;

    private static volatile boolean EnFuncionamiento= false;
    //Usamos volatile para indicar que no debería poder usarse por dos métodos
    //al mismo tiempo
    private static final String NOMBRE = "juego";
    private static int aps =0;
    private static int fps=0;
    private static JFrame ventana;
    private static Thread thread; //Creamos un thread para dividir el trabajo de ejecución
    private static Teclado teclado;
    private Juego(){
    setPreferredSize(new Dimension(ANCHO,ALTO));

    teclado = new Teclado();
    addKeyListener(teclado);
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
    private synchronized void iniciar(){
        //Colocamos synchronized a el mismo efecto que volatile
        //no permitimos que se utilice al mismo tiempo
        //causando un error fatal en la ejecución
        EnFuncionamiento= true;
        thread = new Thread(this, "Graficos");
        thread.start();//Creamos un segundo Thread para dividir el trbabajo
    }
    private synchronized void Detener(){
        //El programa reconoce que realizar cualquier acción con el thread
        //puede causar un error fatal, el IDE coloca un try catch como sugerencia y lo colocamos
        EnFuncionamiento= false;
        try {
            thread.join(); //Usamos join en lugar de stop para no cortar el programa de forma abrupta
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private void Actualizar(){//Actualizaremos lo que se coloque en pantalla
    teclado.actualizar();
    if(teclado.arriba){
        System.out.println("arriba");
    }
    if(teclado.abajo){
            System.out.println("abajo");
        }
    if(teclado.izquierda){
            System.out.println("izquierda");
        }
        if(teclado.derecha){
            System.out.println("derecha");
        }
        aps++;
    }
    private void Mostrar(){//Dibuja los graficos en consecuencia
    fps++;
    }

    @Override
    public void run() {
            final int NS_POR_SEGUNDO= 1000000000;
            final byte APS_OBJETICO=60;
            //Actualizaciones Por Segundo
            final double NS_POR_ACTUALIZACIÓN= NS_POR_SEGUNDO/APS_OBJETICO;
            long referenciaActualizacion = System.nanoTime();
        // Aquí usaremos el tiempo de procesador más no de la máquina en si
        //para no hacer el juego injugable dependiendo de el SO
        //System.nanoTime();
        long referenciaContador= System.nanoTime();
        double tiempoTranscurrido;
        double delta=0; //Cantidad de tiempo que a transcurrido durante una actualización

        requestFocus();
        while (EnFuncionamiento){
            final long inicioBucle=System.nanoTime();
            tiempoTranscurrido= inicioBucle - referenciaActualizacion;
            //Se almacena el tiempo transcurrido
            //desde este momento y el anterior nanotime
            referenciaActualizacion= inicioBucle;
            delta += tiempoTranscurrido/NS_POR_ACTUALIZACIÓN;
 //Cada vez que delta llegue a 1 se va a actualizar el juego
            while (delta>=1){
                Actualizar();
                delta--;
            }
            //Limitaremos ambos métodos para que se actualicen sin importar el procesamiento

            Mostrar();
        if (System.nanoTime()-referenciaContador>NS_POR_SEGUNDO){
        //Se toma el tiempo para este ciclo, se hace una diferencia con el contador inicial
            //para antes de llegar a este ciclo, y se compara con el tiempo para actualizar
            ventana.setTitle(NOMBRE+ "|| APS: " +aps+" || FPS: "+fps);
            aps=0;
            fps=0;
            referenciaContador=System.nanoTime();
            //Con este metodo tendremos una referencia de cuantos fps corre el juego
        }
        }

        System.out.print("El thread 2 se esta ejecutando con éxito");
    }//Aquí revisamos que el primer thread implementado en la interfaz runnable
    //se esté ejecutando, mas tarde usaremos threads para el multijugador

}
