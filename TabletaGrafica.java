public class TabletaGrafica extends Dispositivo {
    private float peso;
    private String almacenamiento;
    public float getPeso() {
        return peso;
    }
    public void setPeso(float peso) {
        this.peso = peso;
    }
    public String getAlmacenamiento() {
        return almacenamiento;
    }
    public void setAlmacenamiento(String almacenamiento) {
        this.almacenamiento = almacenamiento;
    }
    public TabletaGrafica(String serial, String marca, Float tamaño, Float precio, float peso, String almacenamiento) {
        super(serial, marca, tamaño, precio);
        this.peso = peso;
        this.almacenamiento = almacenamiento;
    }
}
