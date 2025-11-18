public class ComputadorPortatil extends Dispositivo{
    private String sistema_operativo;
    private String procesador;
    public String getSistema_operativo() {
        return sistema_operativo;
    }
    public void setSistema_operativo(String sistema_operativo) {
        this.sistema_operativo = sistema_operativo;
    }
    public String getProcesador() {
        return procesador;
    }
    public void setProcesador(String procesador) {
        this.procesador = procesador;
    }
    public ComputadorPortatil(String serial, String marca, Float tamaño, Float precio, String sistema_operativo,
            String procesador) {
        super(serial, marca, tamaño, precio);
        this.sistema_operativo = sistema_operativo;
        this.procesador = procesador;
    }
}
