public class EstudianteDiseno extends Estudiante{
    private String modalidad;
    private int cantidad_asignaturas;
    private int serial_equipo;
    public String getModalidad() {
        return modalidad;
    }
    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }
    public int getCantidad_asignaturas() {
        return cantidad_asignaturas;
    }
    public EstudianteDiseno(String cedula, String nombre, String apellido, String teléfono, String modalidad,
            int cantidad_asignaturas, int serial_equipo) {
        super(cedula, nombre, apellido, teléfono);
        this.modalidad = modalidad;
        this.cantidad_asignaturas = cantidad_asignaturas;
        this.serial_equipo = serial_equipo;
    }
    public void setCantidad_asignaturas(int cantidad_asignaturas) {
        this.cantidad_asignaturas = cantidad_asignaturas;
    }
    public int getSerial_equipo() {
        return serial_equipo;
    }
    public void setSerial_equipo(int serial_equipo) {
        this.serial_equipo = serial_equipo;
    }

}
