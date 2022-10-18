package graficos;

public final class Sprite {
    private int lado; //Lado de cada cuadrado

    private int x;
    private int y;
    public int[] pixeles;
    private HojaSprites hoja;
    public Sprite(final int lado, final int columna, final int fila, final HojaSprites hoja ){
        this.lado= lado;

        pixeles = new int[lado * lado]; //Se puede usar solo lado, ya que se usa el mismo valor

        this.x= columna * lado;
        this.y= fila *lado;
        this.hoja= hoja;

        for (int y =0; y< lado; y++ ){
            for (int x =0; x<lado; x++){
                pixeles[x+y*lado]=hoja.pixeles[(x+this.x)+(y+this.y)*hoja.getAncho()];
            }
        }
    }
}
