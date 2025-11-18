public class EstudianteIngenieria extends Estudiante{
    private int numero_semestre;
    private float promedio_acumulado;
    private String serial_equipo;
    public int getNumero_semestre() {
        return numero_semestre;
    }
    public void setNumero_semestre(int numero_semestre) {
        this.numero_semestre = numero_semestre;
    }
    public float getPromedio_acumulado() {
        return promedio_acumulado;
    }
    public EstudianteIngenieria(String cedula, String nombre, String apellido, String teléfono, int numero_semestre,
            float promedio_acumulado, String serial_equipo) {
        super(cedula, nombre, apellido, teléfono);
        this.numero_semestre = numero_semestre;
        this.promedio_acumulado = promedio_acumulado;
        this.serial_equipo = serial_equipo;
    }
    public void setPromedio_acumulado(float promedio_acumulado) {
        this.promedio_acumulado = promedio_acumulado;
    }
    public String getSerial_equipo() {
        return serial_equipo;
    }
    public void setSerial_equipo(String serial_equipo) {
        this.serial_equipo = serial_equipo;
    }
}
