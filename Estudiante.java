public class Estudiante {
    String Cedula;
    String Nombre;
    String Apellido;
    String Teléfono;
    public Estudiante(String cedula, String nombre, String apellido, String teléfono) {
        Cedula = cedula;
        Nombre = nombre;
        Apellido = apellido;
        Teléfono = teléfono;
    }
    public Estudiante() {
    }
    public String getCedula() {
        return Cedula;
    }
    public void setCedula(String cedula) {
        Cedula = cedula;
    }
    public String getNombre() {
        return Nombre;
    }
    public void setNombre(String nombre) {
        Nombre = nombre;
    }
    public String getApellido() {
        return Apellido;
    }
    public void setApellido(String apellido) {
        Apellido = apellido;
    }
    public String getTeléfono() {
        return Teléfono;
    }
    public void setTeléfono(String teléfono) {
        Teléfono = teléfono;
    }
}
