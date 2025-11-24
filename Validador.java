import java.util.Scanner;

public class Validador {
    private static Scanner scanner = new Scanner(System.in);
    
    // Método recursivo para validar que un texto solo contenga letras (sin números ni caracteres especiales)
    public static String validarTextoRecursivo(String mensaje, String texto) {
        if (texto == null || texto.trim().isEmpty()) {
            System.out.println("Error: El campo no puede estar vacío.");
            System.out.print(mensaje);
            String nuevoTexto = scanner.nextLine().trim();
            return validarTextoRecursivo(mensaje, nuevoTexto);
        }
        
        // Verificar si contiene números o caracteres especiales
        for (int i = 0; i < texto.length(); i++) {
            char c = texto.charAt(i);
            if (!Character.isLetter(c) && c != ' ') {
                System.out.println("Error: No se permiten números ni caracteres especiales. Solo letras y espacios.");
                System.out.print(mensaje);
                String nuevoTexto = scanner.nextLine().trim();
                return validarTextoRecursivo(mensaje, nuevoTexto);
            }
        }
        
        return texto;
    }
    
    // Método recursivo para validar que un texto no esté vacío
    public static String validarNoVacioRecursivo(String mensaje, String texto) {
        if (texto == null || texto.trim().isEmpty()) {
            System.out.println("Error: El campo no puede estar vacío.");
            System.out.print(mensaje);
            String nuevoTexto = scanner.nextLine().trim();
            return validarNoVacioRecursivo(mensaje, nuevoTexto);
        }
        return texto;
    }
    
    // Método recursivo para validar número entero positivo
    public static int validarEnteroPositivoRecursivo(String mensaje, String entrada) {
        try {
            int numero = Integer.parseInt(entrada.trim());
            if (numero <= 0) {
                System.out.println("Error: Debe ingresar un número positivo.");
                System.out.print(mensaje);
                String nuevaEntrada = scanner.nextLine();
                return validarEnteroPositivoRecursivo(mensaje, nuevaEntrada);
            }
            return numero;
        } catch (NumberFormatException e) {
            System.out.println("Error: Debe ingresar un número entero válido.");
            System.out.print(mensaje);
            String nuevaEntrada = scanner.nextLine();
            return validarEnteroPositivoRecursivo(mensaje, nuevaEntrada);
        }
    }
    
    // Método recursivo para validar número flotante positivo
    public static float validarFloatPositivoRecursivo(String mensaje, String entrada) {
        try {
            float numero = Float.parseFloat(entrada.trim());
            if (numero <= 0) {
                System.out.println("Error: Debe ingresar un número positivo.");
                System.out.print(mensaje);
                String nuevaEntrada = scanner.nextLine();
                return validarFloatPositivoRecursivo(mensaje, nuevaEntrada);
            }
            return numero;
        } catch (NumberFormatException e) {
            System.out.println("Error: Debe ingresar un número válido.");
            System.out.print(mensaje);
            String nuevaEntrada = scanner.nextLine();
            return validarFloatPositivoRecursivo(mensaje, nuevaEntrada);
        }
    }
    
    // Método recursivo para validar opción de menú
    public static int validarOpcionMenuRecursivo(String mensaje, int min, int max, String entrada) {
        try {
            int opcion = Integer.parseInt(entrada.trim());
            if (opcion < min || opcion > max) {
                System.out.println("Error: Debe seleccionar una opción entre " + min + " y " + max + ".");
                System.out.print(mensaje);
                String nuevaEntrada = scanner.nextLine();
                return validarOpcionMenuRecursivo(mensaje, min, max, nuevaEntrada);
            }
            return opcion;
        } catch (NumberFormatException e) {
            System.out.println("Error: Debe ingresar un número entero válido.");
            System.out.print(mensaje);
            String nuevaEntrada = scanner.nextLine();
            return validarOpcionMenuRecursivo(mensaje, min, max, nuevaEntrada);
        }
    }
    
    // Método recursivo para validar modalidad (virtual o presencial)
    public static String validarModalidadRecursivo(String mensaje, String entrada) {
        if (entrada == null || entrada.trim().isEmpty()) {
            System.out.println("Error: El campo no puede estar vacío.");
            System.out.print(mensaje);
            String nuevaEntrada = scanner.nextLine().trim();
            return validarModalidadRecursivo(mensaje, nuevaEntrada);
        }
        
        String modalidad = entrada.trim().toLowerCase();
        if (!modalidad.equals("virtual") && !modalidad.equals("presencial")) {
            System.out.println("Error: La modalidad debe ser 'virtual' o 'presencial'.");
            System.out.print(mensaje);
            String nuevaEntrada = scanner.nextLine();
            return validarModalidadRecursivo(mensaje, nuevaEntrada);
        }
        
        return modalidad;
    }
    
    // Método recursivo para validar promedio (0.0 a 5.0)
    public static float validarPromedioRecursivo(String mensaje, String entrada) {
        try {
            float promedio = Float.parseFloat(entrada.trim());
            if (promedio < 0.0f || promedio > 5.0f) {
                System.out.println("Error: El promedio debe estar entre 0.0 y 5.0.");
                System.out.print(mensaje);
                String nuevaEntrada = scanner.nextLine();
                return validarPromedioRecursivo(mensaje, nuevaEntrada);
            }
            return promedio;
        } catch (NumberFormatException e) {
            System.out.println("Error: Debe ingresar un número válido.");
            System.out.print(mensaje);
            String nuevaEntrada = scanner.nextLine();
            return validarPromedioRecursivo(mensaje, nuevaEntrada);
        }
    }
    
    // Método recursivo para validar semestre (1 a 10)
    public static int validarSemestreRecursivo(String mensaje, String entrada) {
        try {
            int semestre = Integer.parseInt(entrada.trim());
            if (semestre < 1 || semestre > 10) {
                System.out.println("Error: El semestre debe estar entre 1 y 10.");
                System.out.print(mensaje);
                String nuevaEntrada = scanner.nextLine();
                return validarSemestreRecursivo(mensaje, nuevaEntrada);
            }
            return semestre;
        } catch (NumberFormatException e) {
            System.out.println("Error: Debe ingresar un número entero válido.");
            System.out.print(mensaje);
            String nuevaEntrada = scanner.nextLine();
            return validarSemestreRecursivo(mensaje, nuevaEntrada);
        }
    }
    
    // Método recursivo para validar que un serial sea numérico (para estudiantes de diseño)
    public static String validarSerialNumericoRecursivo(String mensaje, String entrada) {
        if (entrada == null || entrada.trim().isEmpty()) {
            System.out.println("Error: El serial no puede estar vacío.");
            System.out.print(mensaje);
            String nuevaEntrada = scanner.nextLine();
            return validarSerialNumericoRecursivo(mensaje, nuevaEntrada);
        }
        
        try {
            Integer.parseInt(entrada.trim());
            return entrada.trim();
        } catch (NumberFormatException e) {
            System.out.println("Error: El serial debe ser un número entero válido.");
            System.out.print(mensaje);
            String nuevaEntrada = scanner.nextLine();
            return validarSerialNumericoRecursivo(mensaje, nuevaEntrada);
        }
    }
    
    // Método recursivo para validar que la cédula solo contenga números
    public static String validarCedulaRecursivo(String mensaje, String entrada) {
        if (entrada == null || entrada.trim().isEmpty()) {
            System.out.println("Error: La cédula no puede estar vacía.");
            System.out.print(mensaje);
            String nuevaEntrada = scanner.nextLine().trim();
            return validarCedulaRecursivo(mensaje, nuevaEntrada);
        }
        
        // Verificar que solo contenga números
        String cedula = entrada.trim();
        for (int i = 0; i < cedula.length(); i++) {
            char c = cedula.charAt(i);
            if (!Character.isDigit(c)) {
                System.out.println("Error: La cédula solo puede contener números.");
                System.out.print(mensaje);
                String nuevaEntrada = scanner.nextLine().trim();
                return validarCedulaRecursivo(mensaje, nuevaEntrada);
            }
        }
        
        return cedula;
    }
    
    // Método recursivo para validar que el teléfono solo contenga números
    public static String validarTelefonoRecursivo(String mensaje, String entrada) {
        if (entrada == null || entrada.trim().isEmpty()) {
            System.out.println("Error: El teléfono no puede estar vacío.");
            System.out.print(mensaje);
            String nuevaEntrada = scanner.nextLine().trim();
            return validarTelefonoRecursivo(mensaje, nuevaEntrada);
        }
        
        // Verificar que solo contenga números
        String telefono = entrada.trim();
        for (int i = 0; i < telefono.length(); i++) {
            char c = telefono.charAt(i);
            if (!Character.isDigit(c)) {
                System.out.println("Error: El teléfono solo puede contener números.");
                System.out.print(mensaje);
                String nuevaEntrada = scanner.nextLine().trim();
                return validarTelefonoRecursivo(mensaje, nuevaEntrada);
            }
        }
        
        return telefono;
    }
}

