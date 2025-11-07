public class Dispositivo {
    String Serial;
    String Marca;
    Float Tamaño;
    public Dispositivo(String serial, String marca, Float tamaño, Float precio) {
        Serial = serial;
        Marca = marca;
        Tamaño = tamaño;
        Precio = precio;
    }
    public Dispositivo() {
    }
    Float Precio;
    public String getSerial() {
        return Serial;
    }
    public void setSerial(String serial) {
        Serial = serial;
    }
    public String getMarca() {
        return Marca;
    }
    public void setMarca(String marca) {
        Marca = marca;
    }
    public Float getTamaño() {
        return Tamaño;
    }
    public void setTamaño(Float tamaño) {
        Tamaño = tamaño;
    }
    public Float getPrecio() {
        return Precio;
    }
    public void setPrecio(Float precio) {
        Precio = precio;
    }
}
