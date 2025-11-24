import java.util.Scanner;

public class MenuSistema {
    private GestorPrestamos gestor;
    private Scanner scanner;
    
    public MenuSistema() {
        gestor = new GestorPrestamos();
        scanner = new Scanner(System.in);
    }
    
    private String repetirCaracter(char c, int veces) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < veces; i++) {
            sb.append(c);
        }
        return sb.toString();
    }
    
    public void mostrarMenuPrincipal() {
        int opcion;
        do {
            System.out.println("\n" + repetirCaracter('=', 60));
            System.out.println("    GESTIÓN PRÉSTAMO EQUIPOS ELECTRÓNICOS");
            System.out.println("              SAN JUAN DE DIOS           ");
            System.out.println(repetirCaracter('=', 60));
            System.out.println("1. Estudiantes de Ingeniería");
            System.out.println("2. Estudiantes de Diseño");
            System.out.println("3. Imprimir inventario total");
            System.out.println("4. Salir del programa");
            System.out.println(repetirCaracter('=', 60));
            System.out.print("Seleccione una opción: ");
            
            String entrada = scanner.nextLine();
            opcion = Validador.validarOpcionMenuRecursivo("Seleccione una opción: ", 1, 4, entrada);
            
            switch (opcion) {
                case 1:
                    mostrarSubmenuIngenieria();
                    break;
                case 2:
                    mostrarSubmenuDiseno();
                    break;
                case 3:
                    gestor.imprimirInventarioTotal();
                    break;
                case 4:
                    System.out.println("\n¡Gracias por usar el sistema! Hasta luego.");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 4);
    }
    
    private void mostrarSubmenuIngenieria() {
        int opcion;
        do {
            System.out.println("\n" + repetirCaracter('-', 60));
            System.out.println("         SUBMENÚ - ESTUDIANTES DE INGENIERÍA");
            System.out.println(repetirCaracter('-', 60));
            System.out.println("1. Registrar préstamo");
            System.out.println("2. Modificar préstamo");
            System.out.println("3. Devolución de equipo");
            System.out.println("4. Buscar equipo");
            System.out.println("5. Volver al menú principal");
            System.out.println(repetirCaracter('-', 60));
            System.out.print("Seleccione una opción: ");
            
            String entrada = scanner.nextLine();
            opcion = Validador.validarOpcionMenuRecursivo("Seleccione una opción: ", 1, 5, entrada);
            
            switch (opcion) {
                case 1:
                    gestor.registrarPrestamoIngenieria();
                    break;
                case 2:
                    gestor.modificarPrestamoIngenieria();
                    break;
                case 3:
                    gestor.devolverEquipoIngenieria();
                    break;
                case 4:
                    gestor.buscarEquipoIngenieria();
                    break;
                case 5:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 5);
    }
    
    private void mostrarSubmenuDiseno() {
        int opcion;
        do {
            System.out.println("\n" + repetirCaracter('-', 60));
            System.out.println("         SUBMENÚ - ESTUDIANTES DE DISEÑO");
            System.out.println(repetirCaracter('-', 60));
            System.out.println("1. Registrar préstamo");
            System.out.println("2. Modificar préstamo");
            System.out.println("3. Devolución de equipo");
            System.out.println("4. Buscar equipo");
            System.out.println("5. Volver al menú principal");
            System.out.println(repetirCaracter('-', 60));
            System.out.print("Seleccione una opción: ");
            
            String entrada = scanner.nextLine();
            opcion = Validador.validarOpcionMenuRecursivo("Seleccione una opción: ", 1, 5, entrada);
            
            switch (opcion) {
                case 1:
                    gestor.registrarPrestamoDiseno();
                    break;
                case 2:
                    gestor.modificarPrestamoDiseno();
                    break;
                case 3:
                    gestor.devolverEquipoDiseno();
                    break;
                case 4:
                    gestor.buscarEquipoDiseno();
                    break;
                case 5:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 5);
    }
}

