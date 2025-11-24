import java.util.ArrayList;
import java.util.Scanner;

public class GestorPrestamos {
    private static Scanner scanner = new Scanner(System.in);
    
    // Vectores dinámicos para almacenar los datos
    private ArrayList<EstudianteIngenieria> vector_ingenieros;
    private ArrayList<EstudianteDiseno> vector_disenadores;
    private ArrayList<ComputadorPortatil> vector_portatil;
    private ArrayList<TabletaGrafica> vector_tableta;
    
    public GestorPrestamos() {
        vector_ingenieros = new ArrayList<>();
        vector_disenadores = new ArrayList<>();
        vector_portatil = new ArrayList<>();
        vector_tableta = new ArrayList<>();
    }
    
   
    
    public void registrarPrestamoIngenieria() {
        System.out.println("\n=== REGISTRAR PRÉSTAMO - ESTUDIANTE DE INGENIERÍA ===");
        
        // Validar cédula
        System.out.print("Ingrese la cédula: ");
        String cedula = Validador.validarCedulaRecursivo("Ingrese la cédula: ", scanner.nextLine());
        
        // Verificar que no tenga otro equipo asignado
        if (buscarEstudiantePorCedula(cedula) != null) {
            System.out.println("Error: Este estudiante ya tiene un equipo asignado.");
            return;
        }
        
        // Validar nombre
        System.out.print("Ingrese el nombre: ");
        String nombre = Validador.validarTextoRecursivo("Ingrese el nombre: ", scanner.nextLine());
        
        // Validar apellido
        System.out.print("Ingrese el apellido: ");
        String apellido = Validador.validarTextoRecursivo("Ingrese el apellido: ", scanner.nextLine());
        
        // Validar teléfono
        System.out.print("Ingrese el teléfono: ");
        String telefono = Validador.validarTelefonoRecursivo("Ingrese el teléfono: ", scanner.nextLine());
        
        // Validar semestre
        System.out.print("Ingrese el número de semestre (1-10): ");
        int semestre = Validador.validarSemestreRecursivo("Ingrese el número de semestre (1-10): ", scanner.nextLine());
        
        // Validar promedio
        System.out.print("Ingrese el promedio acumulado (0.0-5.0): ");
        float promedio = Validador.validarPromedioRecursivo("Ingrese el promedio acumulado (0.0-5.0): ", scanner.nextLine());
        
        // Seleccionar equipo
        System.out.println("\nSeleccione el tipo de equipo:");
        System.out.println("1. Computador Portátil");
        System.out.println("2. Tableta Gráfica");
        System.out.print("Opción: ");
        int tipoEquipo = Validador.validarOpcionMenuRecursivo("Opción: ", 1, 2, scanner.nextLine());
        
        String serialEquipo = "";
        if (tipoEquipo == 1) {
            // Registrar computador portátil
            serialEquipo = registrarComputadorPortatil();
        } else {
            // Registrar tableta gráfica
            serialEquipo = registrarTabletaGrafica();
        }
        
        if (serialEquipo == null || serialEquipo.isEmpty()) {
            System.out.println("Error al registrar el equipo.");
            return;
        }
        
        // Crear y agregar estudiante
        EstudianteIngenieria estudiante = new EstudianteIngenieria(cedula, nombre, apellido, telefono, semestre, promedio, serialEquipo);
        vector_ingenieros.add(estudiante);
        
        System.out.println("\n✓ Préstamo registrado exitosamente.");
    }
    
    public void modificarPrestamoIngenieria() {
        System.out.println("\n=== MODIFICAR PRÉSTAMO - ESTUDIANTE DE INGENIERÍA ===");
        System.out.println("1. Buscar por cédula");
        System.out.println("2. Buscar por serial del equipo");
        System.out.print("Opción: ");
        int opcion = Validador.validarOpcionMenuRecursivo("Opción: ", 1, 2, scanner.nextLine());
        
        EstudianteIngenieria estudiante = null;
        if (opcion == 1) {
            System.out.print("Ingrese la cédula: ");
            String cedula = scanner.nextLine().trim();
            estudiante = buscarEstudiantePorCedula(cedula);
        } else {
            System.out.print("Ingrese el serial del equipo: ");
            String serial = scanner.nextLine().trim();
            estudiante = buscarEstudiantePorSerialIngenieria(serial);
        }
        
        if (estudiante == null) {
            System.out.println("Error: No se encontró el préstamo.");
            return;
        }
        
        System.out.println("\nDatos actuales:");
        mostrarEstudianteIngenieria(estudiante);
        
        System.out.println("\nIngrese los nuevos datos (presione Enter para mantener el valor actual):");
        
        // Modificar nombre
        System.out.print("Nombre [" + estudiante.getNombre() + "]: ");
        String nombre = scanner.nextLine().trim();
        if (!nombre.isEmpty()) {
            estudiante.setNombre(Validador.validarTextoRecursivo("Nombre: ", nombre));
        }
        
        // Modificar apellido
        System.out.print("Apellido [" + estudiante.getApellido() + "]: ");
        String apellido = scanner.nextLine().trim();
        if (!apellido.isEmpty()) {
            estudiante.setApellido(Validador.validarTextoRecursivo("Apellido: ", apellido));
        }
        
        // Modificar teléfono
        System.out.print("Teléfono [" + estudiante.getTeléfono() + "]: ");
        String telefono = scanner.nextLine().trim();
        if (!telefono.isEmpty()) {
            estudiante.setTeléfono(Validador.validarTelefonoRecursivo("Teléfono: ", telefono));
        }
        
        // Modificar semestre
        System.out.print("Semestre [" + estudiante.getNumero_semestre() + "]: ");
        String semestreStr = scanner.nextLine().trim();
        if (!semestreStr.isEmpty()) {
            estudiante.setNumero_semestre(Validador.validarSemestreRecursivo("Semestre: ", semestreStr));
        }
        
        // Modificar promedio
        System.out.print("Promedio [" + estudiante.getPromedio_acumulado() + "]: ");
        String promedioStr = scanner.nextLine().trim();
        if (!promedioStr.isEmpty()) {
            estudiante.setPromedio_acumulado(Validador.validarPromedioRecursivo("Promedio: ", promedioStr));
        }
        
        System.out.println("\n✓ Préstamo modificado exitosamente.");
    }
    
    public void devolverEquipoIngenieria() {
        System.out.println("\n=== DEVOLVER EQUIPO - ESTUDIANTE DE INGENIERÍA ===");
        System.out.print("Ingrese la cédula del estudiante: ");
        String cedula = scanner.nextLine().trim();
        
        EstudianteIngenieria estudiante = buscarEstudiantePorCedula(cedula);
        if (estudiante == null) {
            System.out.println("Error: No se encontró el préstamo.");
            return;
        }
        
        System.out.println("\nDatos del préstamo a eliminar:");
        mostrarEstudianteIngenieria(estudiante);
        
        System.out.print("\n¿Está seguro de eliminar este préstamo? (s/n): ");
        String confirmacion = scanner.nextLine().trim().toLowerCase();
        
        if (confirmacion.equals("s") || confirmacion.equals("si")) {
            vector_ingenieros.remove(estudiante);
            System.out.println("\n✓ Equipo devuelto y préstamo eliminado exitosamente.");
        } else {
            System.out.println("\nOperación cancelada.");
        }
    }
    
    public void buscarEquipoIngenieria() {
        System.out.println("\n=== BUSCAR EQUIPO - ESTUDIANTE DE INGENIERÍA ===");
        System.out.println("1. Buscar por cédula");
        System.out.println("2. Buscar por serial del equipo");
        System.out.print("Opción: ");
        int opcion = Validador.validarOpcionMenuRecursivo("Opción: ", 1, 2, scanner.nextLine());
        
        EstudianteIngenieria estudiante = null;
        if (opcion == 1) {
            System.out.print("Ingrese la cédula: ");
            String cedula = scanner.nextLine().trim();
            estudiante = buscarEstudiantePorCedula(cedula);
        } else {
            System.out.print("Ingrese el serial del equipo: ");
            String serial = scanner.nextLine().trim();
            estudiante = buscarEstudiantePorSerialIngenieria(serial);
        }
        
        if (estudiante == null) {
            System.out.println("Error: No se encontró el préstamo.");
        } else {
            System.out.println("\n=== RESULTADO DE BÚSQUEDA ===");
            mostrarEstudianteIngenieria(estudiante);
        }
    }
    
    // Métodos de búsqueda con sobrecarga
    public EstudianteIngenieria buscarEstudiantePorCedula(String cedula) {
        for (EstudianteIngenieria est : vector_ingenieros) {
            if (est.getCedula().equalsIgnoreCase(cedula)) {
                return est;
            }
        }
        return null;
    }
    
    public EstudianteIngenieria buscarEstudiantePorSerialIngenieria(String serial) {
        for (EstudianteIngenieria est : vector_ingenieros) {
            if (est.getSerial_equipo().equalsIgnoreCase(serial)) {
                return est;
            }
        }
        return null;
    }
    
    private void mostrarEstudianteIngenieria(EstudianteIngenieria est) {
        System.out.println("Cédula: " + est.getCedula());
        System.out.println("Nombre: " + est.getNombre());
        System.out.println("Apellido: " + est.getApellido());
        System.out.println("Teléfono: " + est.getTeléfono());
        System.out.println("Semestre: " + est.getNumero_semestre());
        System.out.println("Promedio: " + est.getPromedio_acumulado());
        System.out.println("Serial del equipo: " + est.getSerial_equipo());
    }
    
  
    
    public void registrarPrestamoDiseno() {
        System.out.println("\n=== REGISTRAR PRÉSTAMO - ESTUDIANTE DE DISEÑO ===");
        
        // Validar cédula
        System.out.print("Ingrese la cédula: ");
        String cedula = Validador.validarCedulaRecursivo("Ingrese la cédula: ", scanner.nextLine());
        
        // Verificar que no tenga otro equipo asignado
        if (buscarEstudianteDisenoPorCedula(cedula) != null) {
            System.out.println("Error: Este estudiante ya tiene un equipo asignado.");
            return;
        }
        
        // Validar nombre
        System.out.print("Ingrese el nombre: ");
        String nombre = Validador.validarTextoRecursivo("Ingrese el nombre: ", scanner.nextLine());
        
        // Validar apellido
        System.out.print("Ingrese el apellido: ");
        String apellido = Validador.validarTextoRecursivo("Ingrese el apellido: ", scanner.nextLine());
        
        // Validar teléfono
        System.out.print("Ingrese el teléfono: ");
        String telefono = Validador.validarTelefonoRecursivo("Ingrese el teléfono: ", scanner.nextLine());
        
        // Validar modalidad
        System.out.print("Ingrese la modalidad (virtual/presencial): ");
        String modalidad = Validador.validarModalidadRecursivo("Ingrese la modalidad (virtual/presencial): ", scanner.nextLine());
        
        // Validar cantidad de asignaturas
        System.out.print("Ingrese la cantidad de asignaturas: ");
        int asignaturas = Validador.validarEnteroPositivoRecursivo("Ingrese la cantidad de asignaturas: ", scanner.nextLine());
        
        // Seleccionar equipo
        System.out.println("\nSeleccione el tipo de equipo:");
        System.out.println("1. Computador Portátil");
        System.out.println("2. Tableta Gráfica");
        System.out.print("Opción: ");
        int tipoEquipo = Validador.validarOpcionMenuRecursivo("Opción: ", 1, 2, scanner.nextLine());
        
        int serialEquipo = 0;
        String serialStr = "";
        if (tipoEquipo == 1) {
            // Registrar computador portátil (el serial debe ser numérico para diseño)
            serialStr = registrarComputadorPortatilDiseno();
            if (serialStr == null || serialStr.isEmpty()) {
                System.out.println("Error al registrar el equipo.");
                return;
            }
            serialEquipo = Integer.parseInt(serialStr);
        } else {
            // Registrar tableta gráfica (el serial debe ser numérico para diseño)
            serialStr = registrarTabletaGraficaDiseno();
            if (serialStr == null || serialStr.isEmpty()) {
                System.out.println("Error al registrar el equipo.");
                return;
            }
            serialEquipo = Integer.parseInt(serialStr);
        }
        
        // Crear y agregar estudiante
        EstudianteDiseno estudiante = new EstudianteDiseno(cedula, nombre, apellido, telefono, modalidad, asignaturas, serialEquipo);
        vector_disenadores.add(estudiante);
        
        System.out.println("\n✓ Préstamo registrado exitosamente.");
    }
    
    public void modificarPrestamoDiseno() {
        System.out.println("\n=== MODIFICAR PRÉSTAMO - ESTUDIANTE DE DISEÑO ===");
        System.out.println("1. Buscar por cédula");
        System.out.println("2. Buscar por serial del equipo");
        System.out.print("Opción: ");
        int opcion = Validador.validarOpcionMenuRecursivo("Opción: ", 1, 2, scanner.nextLine());
        
        EstudianteDiseno estudiante = null;
        if (opcion == 1) {
            System.out.print("Ingrese la cédula: ");
            String cedula = scanner.nextLine().trim();
            estudiante = buscarEstudianteDisenoPorCedula(cedula);
        } else {
            System.out.print("Ingrese el serial del equipo: ");
            String serialStr = scanner.nextLine().trim();
            try {
                int serial = Integer.parseInt(serialStr);
                estudiante = buscarEstudianteDisenoPorSerial(serial);
            } catch (NumberFormatException e) {
                System.out.println("Error: Serial inválido.");
                return;
            }
        }
        
        if (estudiante == null) {
            System.out.println("Error: No se encontró el préstamo.");
            return;
        }
        
        System.out.println("\nDatos actuales:");
        mostrarEstudianteDiseno(estudiante);
        
        System.out.println("\nIngrese los nuevos datos (presione Enter para mantener el valor actual):");
        
        // Modificar nombre
        System.out.print("Nombre [" + estudiante.getNombre() + "]: ");
        String nombre = scanner.nextLine().trim();
        if (!nombre.isEmpty()) {
            estudiante.setNombre(Validador.validarTextoRecursivo("Nombre: ", nombre));
        }
        
        // Modificar apellido
        System.out.print("Apellido [" + estudiante.getApellido() + "]: ");
        String apellido = scanner.nextLine().trim();
        if (!apellido.isEmpty()) {
            estudiante.setApellido(Validador.validarTextoRecursivo("Apellido: ", apellido));
        }
        
        // Modificar teléfono
        System.out.print("Teléfono [" + estudiante.getTeléfono() + "]: ");
        String telefono = scanner.nextLine().trim();
        if (!telefono.isEmpty()) {
            estudiante.setTeléfono(Validador.validarTelefonoRecursivo("Teléfono: ", telefono));
        }
        
        // Modificar modalidad
        System.out.print("Modalidad [" + estudiante.getModalidad() + "]: ");
        String modalidad = scanner.nextLine().trim();
        if (!modalidad.isEmpty()) {
            estudiante.setModalidad(Validador.validarModalidadRecursivo("Modalidad: ", modalidad));
        }
        
        // Modificar cantidad de asignaturas
        System.out.print("Cantidad de asignaturas [" + estudiante.getCantidad_asignaturas() + "]: ");
        String asignaturasStr = scanner.nextLine().trim();
        if (!asignaturasStr.isEmpty()) {
            estudiante.setCantidad_asignaturas(Validador.validarEnteroPositivoRecursivo("Cantidad de asignaturas: ", asignaturasStr));
        }
        
        System.out.println("\n✓ Préstamo modificado exitosamente.");
    }
    
    public void devolverEquipoDiseno() {
        System.out.println("\n=== DEVOLVER EQUIPO - ESTUDIANTE DE DISEÑO ===");
        System.out.print("Ingrese la cédula del estudiante: ");
        String cedula = scanner.nextLine().trim();
        
        EstudianteDiseno estudiante = buscarEstudianteDisenoPorCedula(cedula);
        if (estudiante == null) {
            System.out.println("Error: No se encontró el préstamo.");
            return;
        }
        
        System.out.println("\nDatos del préstamo a eliminar:");
        mostrarEstudianteDiseno(estudiante);
        
        System.out.print("\n¿Está seguro de eliminar este préstamo? (s/n): ");
        String confirmacion = scanner.nextLine().trim().toLowerCase();
        
        if (confirmacion.equals("s") || confirmacion.equals("si")) {
            vector_disenadores.remove(estudiante);
            System.out.println("\n✓ Equipo devuelto y préstamo eliminado exitosamente.");
        } else {
            System.out.println("\nOperación cancelada.");
        }
    }
    
    public void buscarEquipoDiseno() {
        System.out.println("\n=== BUSCAR EQUIPO - ESTUDIANTE DE DISEÑO ===");
        System.out.println("1. Buscar por cédula");
        System.out.println("2. Buscar por serial del equipo");
        System.out.print("Opción: ");
        int opcion = Validador.validarOpcionMenuRecursivo("Opción: ", 1, 2, scanner.nextLine());
        
        EstudianteDiseno estudiante = null;
        if (opcion == 1) {
            System.out.print("Ingrese la cédula: ");
            String cedula = scanner.nextLine().trim();
            estudiante = buscarEstudianteDisenoPorCedula(cedula);
        } else {
            System.out.print("Ingrese el serial del equipo: ");
            String serialStr = scanner.nextLine().trim();
            try {
                int serial = Integer.parseInt(serialStr);
                estudiante = buscarEstudianteDisenoPorSerial(serial);
            } catch (NumberFormatException e) {
                System.out.println("Error: Serial inválido.");
                return;
            }
        }
        
        if (estudiante == null) {
            System.out.println("Error: No se encontró el préstamo.");
        } else {
            System.out.println("\n=== RESULTADO DE BÚSQUEDA ===");
            mostrarEstudianteDiseno(estudiante);
        }
    }
    
    // Métodos de búsqueda con sobrecarga
    public EstudianteDiseno buscarEstudianteDisenoPorCedula(String cedula) {
        for (EstudianteDiseno est : vector_disenadores) {
            if (est.getCedula().equalsIgnoreCase(cedula)) {
                return est;
            }
        }
        return null;
    }
    
    public EstudianteDiseno buscarEstudianteDisenoPorSerial(int serial) {
        for (EstudianteDiseno est : vector_disenadores) {
            if (est.getSerial_equipo() == serial) {
                return est;
            }
        }
        return null;
    }
    
    private void mostrarEstudianteDiseno(EstudianteDiseno est) {
        System.out.println("Cédula: " + est.getCedula());
        System.out.println("Nombre: " + est.getNombre());
        System.out.println("Apellido: " + est.getApellido());
        System.out.println("Teléfono: " + est.getTeléfono());
        System.out.println("Modalidad: " + est.getModalidad());
        System.out.println("Cantidad de asignaturas: " + est.getCantidad_asignaturas());
        System.out.println("Serial del equipo: " + est.getSerial_equipo());
    }
    
    // ========== MÉTODOS PARA REGISTRAR EQUIPOS ==========
    
    private String registrarComputadorPortatil() {
        System.out.println("\n=== REGISTRAR COMPUTADOR PORTÁTIL ===");
        
        // Validar serial
        System.out.print("Ingrese el serial: ");
        String serial = Validador.validarNoVacioRecursivo("Ingrese el serial: ", scanner.nextLine());
        
        // Verificar que no esté duplicado
        if (buscarPortatilPorSerial(serial) != null) {
            System.out.println("Error: Ya existe un computador portátil con este serial.");
            return null;
        }
        
        // Validar marca
        System.out.print("Ingrese la marca: ");
        String marca = Validador.validarTextoRecursivo("Ingrese la marca: ", scanner.nextLine());
        
        // Validar tamaño
        System.out.print("Ingrese el tamaño en pulgadas: ");
        float tamaño = Validador.validarFloatPositivoRecursivo("Ingrese el tamaño en pulgadas: ", scanner.nextLine());
        
        // Validar precio
        System.out.print("Ingrese el precio: ");
        float precio = Validador.validarFloatPositivoRecursivo("Ingrese el precio: ", scanner.nextLine());
        
        // Seleccionar sistema operativo
        System.out.println("\nSeleccione el sistema operativo:");
        System.out.println("1. Windows 7");
        System.out.println("2. Windows 10");
        System.out.println("3. Windows 11");
        System.out.print("Opción: ");
        int opcionSO = Validador.validarOpcionMenuRecursivo("Opción: ", 1, 3, scanner.nextLine());
        String sistemaOperativo = "";
        switch (opcionSO) {
            case 1: sistemaOperativo = "Windows 7"; break;
            case 2: sistemaOperativo = "Windows 10"; break;
            case 3: sistemaOperativo = "Windows 11"; break;
        }
        
        // Seleccionar procesador
        System.out.println("\nSeleccione el procesador:");
        System.out.println("1. AMD Ryzen");
        System.out.println("2. Intel Core i5");
        System.out.print("Opción: ");
        int opcionProc = Validador.validarOpcionMenuRecursivo("Opción: ", 1, 2, scanner.nextLine());
        String procesador = (opcionProc == 1) ? "AMD Ryzen" : "Intel Core i5";
        
        ComputadorPortatil portatil = new ComputadorPortatil(serial, marca, tamaño, precio, sistemaOperativo, procesador);
        vector_portatil.add(portatil);
        
        System.out.println("\n✓ Computador portátil registrado exitosamente.");
        return serial;
    }
    
    // Métodos específicos para estudiantes de diseño (serial debe ser numérico)
    private String registrarComputadorPortatilDiseno() {
        System.out.println("\n=== REGISTRAR COMPUTADOR PORTÁTIL ===");
        
        // Validar serial (debe ser numérico para diseño)
        System.out.print("Ingrese el serial (debe ser numérico): ");
        String serial = Validador.validarSerialNumericoRecursivo("Ingrese el serial (debe ser numérico): ", scanner.nextLine());
        
        // Verificar que no esté duplicado
        if (buscarPortatilPorSerial(serial) != null) {
            System.out.println("Error: Ya existe un computador portátil con este serial.");
            return null;
        }
        
        // Validar marca
        System.out.print("Ingrese la marca: ");
        String marca = Validador.validarTextoRecursivo("Ingrese la marca: ", scanner.nextLine());
        
        // Validar tamaño
        System.out.print("Ingrese el tamaño en pulgadas: ");
        float tamaño = Validador.validarFloatPositivoRecursivo("Ingrese el tamaño en pulgadas: ", scanner.nextLine());
        
        // Validar precio
        System.out.print("Ingrese el precio: ");
        float precio = Validador.validarFloatPositivoRecursivo("Ingrese el precio: ", scanner.nextLine());
        
        // Seleccionar sistema operativo
        System.out.println("\nSeleccione el sistema operativo:");
        System.out.println("1. Windows 7");
        System.out.println("2. Windows 10");
        System.out.println("3. Windows 11");
        System.out.print("Opción: ");
        int opcionSO = Validador.validarOpcionMenuRecursivo("Opción: ", 1, 3, scanner.nextLine());
        String sistemaOperativo = "";
        switch (opcionSO) {
            case 1: sistemaOperativo = "Windows 7"; break;
            case 2: sistemaOperativo = "Windows 10"; break;
            case 3: sistemaOperativo = "Windows 11"; break;
        }
        
        // Seleccionar procesador
        System.out.println("\nSeleccione el procesador:");
        System.out.println("1. AMD Ryzen");
        System.out.println("2. Intel Core i5");
        System.out.print("Opción: ");
        int opcionProc = Validador.validarOpcionMenuRecursivo("Opción: ", 1, 2, scanner.nextLine());
        String procesador = (opcionProc == 1) ? "AMD Ryzen" : "Intel Core i5";
        
        ComputadorPortatil portatil = new ComputadorPortatil(serial, marca, tamaño, precio, sistemaOperativo, procesador);
        vector_portatil.add(portatil);
        
        System.out.println("\n✓ Computador portátil registrado exitosamente.");
        return serial;
    }
    
    private String registrarTabletaGraficaDiseno() {
        System.out.println("\n=== REGISTRAR TABLETA GRÁFICA ===");
        
        // Validar serial (debe ser numérico para diseño)
        System.out.print("Ingrese el serial (debe ser numérico): ");
        String serial = Validador.validarSerialNumericoRecursivo("Ingrese el serial (debe ser numérico): ", scanner.nextLine());
        
        // Verificar que no esté duplicado
        if (buscarTabletaPorSerial(serial) != null) {
            System.out.println("Error: Ya existe una tableta gráfica con este serial.");
            return null;
        }
        
        // Validar marca
        System.out.print("Ingrese la marca: ");
        String marca = Validador.validarTextoRecursivo("Ingrese la marca: ", scanner.nextLine());
        
        // Validar tamaño
        System.out.print("Ingrese el tamaño en pulgadas: ");
        float tamaño = Validador.validarFloatPositivoRecursivo("Ingrese el tamaño en pulgadas: ", scanner.nextLine());
        
        // Validar precio
        System.out.print("Ingrese el precio: ");
        float precio = Validador.validarFloatPositivoRecursivo("Ingrese el precio: ", scanner.nextLine());
        
        // Seleccionar almacenamiento
        System.out.println("\nSeleccione el almacenamiento:");
        System.out.println("1. 256 GB");
        System.out.println("2. 512 GB");
        System.out.println("3. 1 TB");
        System.out.print("Opción: ");
        int opcionAlm = Validador.validarOpcionMenuRecursivo("Opción: ", 1, 3, scanner.nextLine());
        String almacenamiento = "";
        switch (opcionAlm) {
            case 1: almacenamiento = "256 GB"; break;
            case 2: almacenamiento = "512 GB"; break;
            case 3: almacenamiento = "1 TB"; break;
        }
        
        // Validar peso
        System.out.print("Ingrese el peso en kg: ");
        float peso = Validador.validarFloatPositivoRecursivo("Ingrese el peso en kg: ", scanner.nextLine());
        
        TabletaGrafica tableta = new TabletaGrafica(serial, marca, tamaño, precio, peso, almacenamiento);
        vector_tableta.add(tableta);
        
        System.out.println("\n✓ Tableta gráfica registrada exitosamente.");
        return serial;
    }
    
    private String registrarTabletaGrafica() {
        System.out.println("\n=== REGISTRAR TABLETA GRÁFICA ===");
        
        // Validar serial
        System.out.print("Ingrese el serial: ");
        String serial = Validador.validarNoVacioRecursivo("Ingrese el serial: ", scanner.nextLine());
        
        // Verificar que no esté duplicado
        if (buscarTabletaPorSerial(serial) != null) {
            System.out.println("Error: Ya existe una tableta gráfica con este serial.");
            return null;
        }
        
        // Validar marca
        System.out.print("Ingrese la marca: ");
        String marca = Validador.validarTextoRecursivo("Ingrese la marca: ", scanner.nextLine());
        
        // Validar tamaño
        System.out.print("Ingrese el tamaño en pulgadas: ");
        float tamaño = Validador.validarFloatPositivoRecursivo("Ingrese el tamaño en pulgadas: ", scanner.nextLine());
        
        // Validar precio
        System.out.print("Ingrese el precio: ");
        float precio = Validador.validarFloatPositivoRecursivo("Ingrese el precio: ", scanner.nextLine());
        
        // Seleccionar almacenamiento
        System.out.println("\nSeleccione el almacenamiento:");
        System.out.println("1. 256 GB");
        System.out.println("2. 512 GB");
        System.out.println("3. 1 TB");
        System.out.print("Opción: ");
        int opcionAlm = Validador.validarOpcionMenuRecursivo("Opción: ", 1, 3, scanner.nextLine());
        String almacenamiento = "";
        switch (opcionAlm) {
            case 1: almacenamiento = "256 GB"; break;
            case 2: almacenamiento = "512 GB"; break;
            case 3: almacenamiento = "1 TB"; break;
        }
        
        // Validar peso
        System.out.print("Ingrese el peso en kg: ");
        float peso = Validador.validarFloatPositivoRecursivo("Ingrese el peso en kg: ", scanner.nextLine());
        
        TabletaGrafica tableta = new TabletaGrafica(serial, marca, tamaño, precio, peso, almacenamiento);
        vector_tableta.add(tableta);
        
        System.out.println("\n✓ Tableta gráfica registrada exitosamente.");
        return serial;
    }
    
    // Métodos de búsqueda de equipos
    public ComputadorPortatil buscarPortatilPorSerial(String serial) {
        for (ComputadorPortatil portatil : vector_portatil) {
            if (portatil.getSerial().equalsIgnoreCase(serial)) {
                return portatil;
            }
        }
        return null;
    }
    
    public TabletaGrafica buscarTabletaPorSerial(String serial) {
        for (TabletaGrafica tableta : vector_tableta) {
            if (tableta.getSerial().equalsIgnoreCase(serial)) {
                return tableta;
            }
        }
        return null;
    }
    
    // ========== MÉTODO PARA IMPRIMIR INVENTARIO TOTAL ==========
    
    private String repetirCaracter(char c, int veces) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < veces; i++) {
            sb.append(c);
        }
        return sb.toString();
    }
    
    public void imprimirInventarioTotal() {
        System.out.println("\n" + repetirCaracter('=', 80));
        System.out.println("                    INVENTARIO TOTAL DE PRÉSTAMOS");
        System.out.println(repetirCaracter('=', 80));
        
        System.out.println("\n--- ESTUDIANTES DE INGENIERÍA ---");
        if (vector_ingenieros.isEmpty()) {
            System.out.println("No hay préstamos registrados.");
        } else {
            for (int i = 0; i < vector_ingenieros.size(); i++) {
                EstudianteIngenieria est = vector_ingenieros.get(i);
                System.out.println("\nPréstamo #" + (i + 1) + ":");
                mostrarEstudianteIngenieria(est);
                
                // Mostrar información del equipo
                ComputadorPortatil portatil = buscarPortatilPorSerial(est.getSerial_equipo());
                TabletaGrafica tableta = buscarTabletaPorSerial(est.getSerial_equipo());
                
                if (portatil != null) {
                    System.out.println("\nEquipo asignado - Computador Portátil:");
                    System.out.println("  Serial: " + portatil.getSerial());
                    System.out.println("  Marca: " + portatil.getMarca());
                    System.out.println("  Tamaño: " + portatil.getTamaño() + " pulgadas");
                    System.out.println("  Precio: $" + portatil.getPrecio());
                    System.out.println("  Sistema Operativo: " + portatil.getSistema_operativo());
                    System.out.println("  Procesador: " + portatil.getProcesador());
                } else if (tableta != null) {
                    System.out.println("\nEquipo asignado - Tableta Gráfica:");
                    System.out.println("  Serial: " + tableta.getSerial());
                    System.out.println("  Marca: " + tableta.getMarca());
                    System.out.println("  Tamaño: " + tableta.getTamaño() + " pulgadas");
                    System.out.println("  Precio: $" + tableta.getPrecio());
                    System.out.println("  Almacenamiento: " + tableta.getAlmacenamiento());
                    System.out.println("  Peso: " + tableta.getPeso() + " kg");
                }
            }
        }
        
        System.out.println("\n--- ESTUDIANTES DE DISEÑO ---");
        if (vector_disenadores.isEmpty()) {
            System.out.println("No hay préstamos registrados.");
        } else {
            for (int i = 0; i < vector_disenadores.size(); i++) {
                EstudianteDiseno est = vector_disenadores.get(i);
                System.out.println("\nPréstamo #" + (i + 1) + ":");
                mostrarEstudianteDiseno(est);
                
                // Mostrar información del equipo
                String serialStr = String.valueOf(est.getSerial_equipo());
                ComputadorPortatil portatil = buscarPortatilPorSerial(serialStr);
                TabletaGrafica tableta = buscarTabletaPorSerial(serialStr);
                
                if (portatil != null) {
                    System.out.println("\nEquipo asignado - Computador Portátil:");
                    System.out.println("  Serial: " + portatil.getSerial());
                    System.out.println("  Marca: " + portatil.getMarca());
                    System.out.println("  Tamaño: " + portatil.getTamaño() + " pulgadas");
                    System.out.println("  Precio: $" + portatil.getPrecio());
                    System.out.println("  Sistema Operativo: " + portatil.getSistema_operativo());
                    System.out.println("  Procesador: " + portatil.getProcesador());
                } else if (tableta != null) {
                    System.out.println("\nEquipo asignado - Tableta Gráfica:");
                    System.out.println("  Serial: " + tableta.getSerial());
                    System.out.println("  Marca: " + tableta.getMarca());
                    System.out.println("  Tamaño: " + tableta.getTamaño() + " pulgadas");
                    System.out.println("  Precio: $" + tableta.getPrecio());
                    System.out.println("  Almacenamiento: " + tableta.getAlmacenamiento());
                    System.out.println("  Peso: " + tableta.getPeso() + " kg");
                }
            }
        }
        
        System.out.println("\n" + repetirCaracter('=', 80));
        System.out.println("Total de préstamos de Ingeniería: " + vector_ingenieros.size());
        System.out.println("Total de préstamos de Diseño: " + vector_disenadores.size());
        System.out.println("Total de computadores portátiles: " + vector_portatil.size());
        System.out.println("Total de tabletas gráficas: " + vector_tableta.size());
        System.out.println(repetirCaracter('=', 80) + "\n");
    }
}

