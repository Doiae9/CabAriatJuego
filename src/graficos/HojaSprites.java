package graficos;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class HojaSprites {
    private final int ancho;//El final es para que corra mas rápido el juego
    private final int alto;
    public final int [] pixeles;

    public HojaSprites(final String ruta, final int ancho, final int alto){
        this.ancho= ancho;
        this.alto= alto;

        pixeles = new int[ancho * alto];

        BufferedImage imagen = null;
        try {
            imagen = ImageIO.read(HojaSprites.class.getResource(ruta));
            imagen.getRGB(0,0,ancho,alto,pixeles,0,ancho);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public int getAncho(){
        return ancho;

    }

    }


