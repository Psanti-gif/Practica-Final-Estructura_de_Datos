# EXPLICACIÓN DETALLADA DEL CÓDIGO - GESTIÓN DE PRÉSTAMOS

## 📚 ÍNDICE
1. [Arquitectura General del Sistema](#arquitectura-general)
2. [Clase Principal - Punto de Entrada](#clase-principal)
3. [Clase Validador - Validaciones Recursivas](#clase-validador)
4. [Clase MenuSistema - Interfaz de Usuario](#clase-menusistema)
5. [Clase GestorPrestamos - Lógica de Negocio](#clase-gestorprestamos)
6. [Clases de Dominio - Modelo de Datos](#clases-de-dominio)
7. [Conceptos de POO Aplicados](#conceptos-poo)
8. [Estructuras de Datos Utilizadas](#estructuras-datos)

---

## 🏗️ ARQUITECTURA GENERAL DEL SISTEMA

El sistema está diseñado siguiendo el patrón de **separación de responsabilidades**:

```
Principal (Punto de entrada)
    ↓
MenuSistema (Interfaz de usuario)
    ↓
GestorPrestamos (Lógica de negocio)
    ↓
Validador (Validaciones)
    ↓
Clases de Dominio (Modelo de datos)
```

**Flujo de ejecución:**
1. `Principal.main()` inicia el programa
2. Crea un objeto `MenuSistema`
3. `MenuSistema` crea un `GestorPrestamos` para manejar los datos
4. Todas las validaciones se hacen a través de `Validador`
5. Los datos se almacenan en `ArrayList` (vectores dinámicos)

---

## 🚀 CLASE PRINCIPAL - PUNTO DE ENTRADA

### Código:
```java
public class Principal {
    public static void main(String[] args) {
        MenuSistema menu = new MenuSistema();
        menu.mostrarMenuPrincipal();
    }    
}
```

### Explicación Detallada:

**1. `public class Principal`**
   - Clase pública que puede ser accedida desde cualquier parte
   - Es la clase principal del programa

**2. `public static void main(String[] args)`**
   - **`public`**: Método accesible desde fuera de la clase
   - **`static`**: Pertenece a la clase, no a una instancia. Se puede llamar sin crear un objeto
   - **`void`**: No retorna ningún valor
   - **`main`**: Nombre especial que Java busca al ejecutar el programa
   - **`String[] args`**: Array de argumentos de línea de comandos (no se usa en este caso)

**3. `MenuSistema menu = new MenuSistema();`**
   - **Creación de objeto**: `new MenuSistema()` crea una nueva instancia
   - **Asignación**: Se asigna a la variable `menu` de tipo `MenuSistema`
   - **Constructor**: Se ejecuta el constructor de `MenuSistema` que inicializa el `GestorPrestamos` y el `Scanner`

**4. `menu.mostrarMenuPrincipal();`**
   - **Llamada a método**: Invoca el método que muestra el menú principal
   - **Encapsulación**: El `main` solo llama métodos, no contiene lógica de negocio

**¿Por qué está así?**
- Cumple el requisito: "En la clase principal no debe existir ningún otro método diferente al main"
- El `main` solo orquesta, no implementa lógica
- Facilita el mantenimiento y prueba del código

---

## ✅ CLASE VALIDADOR - VALIDACIONES RECURSIVAS

### Propósito:
Centraliza todas las validaciones usando **recursión** para garantizar datos válidos.

### Concepto Clave: RECURSIÓN
La recursión es cuando un método se llama a sí mismo. Debe tener:
- **Caso base**: Condición que detiene la recursión
- **Caso recursivo**: Llamada a sí mismo con parámetros modificados

### Métodos Principales:

#### 1. `validarTextoRecursivo(String mensaje, String texto)`

**¿Qué hace?**
Valida que un texto solo contenga letras y espacios (sin números ni caracteres especiales).

**Análisis línea por línea:**

```java
public static String validarTextoRecursivo(String mensaje, String texto) {
```
- **`static`**: Método de clase, se llama sin crear objeto: `Validador.validarTextoRecursivo(...)`
- **`String`**: Retorna un String validado

```java
if (texto == null || texto.trim().isEmpty()) {
```
- **Caso base 1**: Si el texto es `null` o vacío después de quitar espacios
- **`trim()`**: Elimina espacios al inicio y final
- **`isEmpty()`**: Verifica si está vacío

```java
    System.out.println("Error: El campo no puede estar vacío.");
    System.out.print(mensaje);
    String nuevoTexto = scanner.nextLine().trim();
    return validarTextoRecursivo(mensaje, nuevoTexto);
```
- **Caso recursivo**: Muestra error, pide nuevo texto y se llama a sí mismo
- **Recursión**: Continúa hasta que el usuario ingrese un valor válido

```java
for (int i = 0; i < texto.length(); i++) {
    char c = texto.charAt(i);
    if (!Character.isLetter(c) && c != ' ') {
```
- **Iteración**: Recorre cada carácter del texto
- **`charAt(i)`**: Obtiene el carácter en la posición `i`
- **`Character.isLetter(c)`**: Verifica si es una letra
- **`c != ' '`**: Permite espacios

```java
        System.out.println("Error: No se permiten números ni caracteres especiales...");
        System.out.print(mensaje);
        String nuevoTexto = scanner.nextLine().trim();
        return validarTextoRecursivo(mensaje, nuevoTexto);
```
- **Caso recursivo 2**: Si encuentra un carácter inválido, pide nuevo texto y se llama recursivamente

```java
return texto;
```
- **Caso base 2**: Si pasa todas las validaciones, retorna el texto válido

**Ejemplo de ejecución:**
```
Usuario ingresa: "Juan123"
→ Detecta números
→ Muestra error
→ Pide nuevo texto
→ Usuario ingresa: "Juan"
→ ✅ Retorna "Juan"
```

#### 2. `validarEnteroPositivoRecursivo(String mensaje, String entrada)`

**¿Qué hace?**
Valida que la entrada sea un número entero positivo.

**Análisis:**

```java
try {
    int numero = Integer.parseInt(entrada.trim());
```
- **`try-catch`**: Manejo de excepciones
- **`Integer.parseInt()`**: Convierte String a int
- **Si falla**: Lanza `NumberFormatException`

```java
    if (numero <= 0) {
        System.out.println("Error: Debe ingresar un número positivo.");
        System.out.print(mensaje);
        String nuevaEntrada = scanner.nextLine();
        return validarEnteroPositivoRecursivo(mensaje, nuevaEntrada);
    }
    return numero;
```
- **Validación de rango**: Verifica que sea positivo
- **Recursión**: Si no cumple, se llama a sí mismo

```java
} catch (NumberFormatException e) {
    System.out.println("Error: Debe ingresar un número entero válido.");
    System.out.print(mensaje);
    String nuevaEntrada = scanner.nextLine();
    return validarEnteroPositivoRecursivo(mensaje, nuevaEntrada);
}
```
- **Manejo de excepción**: Si no es un número, captura la excepción
- **Recursión**: Pide nuevo valor y se llama recursivamente

**Ejemplo:**
```
Usuario ingresa: "abc"
→ NumberFormatException
→ Muestra error
→ Usuario ingresa: "-5"
→ No es positivo
→ Muestra error
→ Usuario ingresa: "10"
→ ✅ Retorna 10
```

#### 3. `validarOpcionMenuRecursivo(String mensaje, int min, int max, String entrada)`

**¿Qué hace?**
Valida que la opción del menú esté en el rango permitido.

**Concepto importante:**
- **Parámetros `min` y `max`**: Hacen el método reutilizable para diferentes menús
- **Flexibilidad**: Mismo método para menú principal (1-4) y submenús (1-5)

**Ejemplo de uso:**
```java
// Menú principal: opciones 1 a 4
validarOpcionMenuRecursivo("Opción: ", 1, 4, entrada);

// Submenú: opciones 1 a 5
validarOpcionMenuRecursivo("Opción: ", 1, 5, entrada);
```

#### 4. `validarModalidadRecursivo(String mensaje, String entrada)`

**¿Qué hace?**
Valida que la modalidad sea "virtual" o "presencial".

**Punto clave:**
```java
String modalidad = entrada.trim().toLowerCase();
```
- **`toLowerCase()`**: Convierte a minúsculas para comparación insensible a mayúsculas
- Permite: "Virtual", "VIRTUAL", "virtual" → todos válidos

#### 5. `validarPromedioRecursivo(String mensaje, String entrada)`

**¿Qué hace?**
Valida que el promedio esté entre 0.0 y 5.0.

**Validación de rango:**
```java
if (promedio < 0.0f || promedio > 5.0f) {
```
- **`0.0f`**: El sufijo `f` indica que es un float literal
- **Rango**: Sistema de calificación colombiano (0.0 a 5.0)

#### 6. `validarSemestreRecursivo(String mensaje, String entrada)`

**¿Qué hace?**
Valida que el semestre esté entre 1 y 10.

**Lógica:**
- Carreras universitarias típicamente tienen 10 semestres
- Valida rango lógico

#### 7. `validarSerialNumericoRecursivo(String mensaje, String entrada)`

**¿Qué hace?**
Valida que el serial sea numérico (específico para estudiantes de diseño).

**Diferencia importante:**
- **Estudiantes de Ingeniería**: Serial puede ser String (ej: "ABC123")
- **Estudiantes de Diseño**: Serial debe ser int (ej: 12345)

#### 8. `validarCedulaRecursivo(String mensaje, String entrada)`

**¿Qué hace?**
Valida que la cédula solo contenga números (sin letras ni caracteres especiales).

**Análisis:**

```java
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
```

**Conceptos clave:**
- **`Character.isDigit(c)`**: Verifica si un carácter es un dígito (0-9)
- **Recursión**: Si encuentra un carácter no numérico, pide nuevo valor y se llama a sí mismo
- **Caso base**: Si todos los caracteres son dígitos, retorna la cédula válida

**Ejemplo:**
```
Usuario ingresa: "1234567890ABC"
→ Detecta letras
→ Muestra error
→ Usuario ingresa: "1234567890"
→ ✅ Retorna "1234567890"
```

#### 9. `validarTelefonoRecursivo(String mensaje, String entrada)`

**¿Qué hace?**
Valida que el teléfono solo contenga números (sin letras, espacios ni caracteres especiales).

**Análisis:**

```java
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
```

**Características:**
- Similar a `validarCedulaRecursivo` pero para teléfonos
- No permite espacios, guiones, paréntesis u otros caracteres
- Solo acepta dígitos del 0 al 9

**Ejemplo:**
```
Usuario ingresa: "300-123-4567"
→ Detecta guiones
→ Muestra error
→ Usuario ingresa: "3001234567"
→ ✅ Retorna "3001234567"
```

---

## 🎨 CLASE MENUSISTEMA - INTERFAZ DE USUARIO

### Propósito:
Maneja toda la interacción con el usuario mediante menús.

### Atributos:

```java
private GestorPrestamos gestor;
private Scanner scanner;
```

**Explicación:**
- **`gestor`**: Objeto que maneja la lógica de préstamos
- **`scanner`**: Lee entrada del usuario desde la consola
- **`private`**: Encapsulación - solo esta clase puede acceder

### Constructor:

```java
public MenuSistema() {
    gestor = new GestorPrestamos();
    scanner = new Scanner(System.in);
}
```

**¿Qué hace?**
- Inicializa el `GestorPrestamos` (crea los ArrayList vacíos)
- Inicializa el `Scanner` para leer de `System.in` (entrada estándar)

### Método `repetirCaracter(char c, int veces)`

```java
private String repetirCaracter(char c, int veces) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < veces; i++) {
        sb.append(c);
    }
    return sb.toString();
}
```

**¿Por qué existe?**
- `String.repeat()` solo existe desde Java 11
- Este método es compatible con versiones anteriores

**Análisis:**
- **`StringBuilder`**: Más eficiente que concatenar Strings con `+`
- **Bucle `for`**: Repite el carácter `veces` veces
- **`append(c)`**: Agrega el carácter al StringBuilder
- **`toString()`**: Convierte StringBuilder a String

**Ejemplo:**
```java
repetirCaracter('=', 60)
→ Retorna: "============================================================"
```

### Método `mostrarMenuPrincipal()`

**Estructura:**

```java
public void mostrarMenuPrincipal() {
    int opcion;
    do {
        // Mostrar menú
        // Leer opción
        // Procesar opción
    } while (opcion != 4);
}
```

**Bucle `do-while`:**
- **`do`**: Ejecuta el bloque al menos una vez
- **`while (opcion != 4)`**: Continúa hasta que el usuario elija salir (opción 4)
- **Ventaja**: Garantiza que el menú se muestre al menos una vez

**Switch statement:**

```java
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
```

**Explicación:**
- **`switch`**: Evalúa `opcion` y ejecuta el `case` correspondiente
- **`break`**: Sale del switch (sin esto, ejecutaría todos los casos siguientes)
- **`default`**: Se ejecuta si ninguna opción coincide (aunque ya está validado)

**Flujo de navegación:**
```
Menú Principal
    ├─ Opción 1 → Submenú Ingeniería → Vuelve al Principal
    ├─ Opción 2 → Submenú Diseño → Vuelve al Principal
    ├─ Opción 3 → Muestra inventario → Vuelve al Principal
    └─ Opción 4 → Sale del programa
```

### Métodos `mostrarSubmenuIngenieria()` y `mostrarSubmenuDiseno()`

**Estructura similar:**
```java
private void mostrarSubmenuIngenieria() {
    int opcion;
    do {
        // Mostrar submenú
        // Leer opción
        switch (opcion) {
            case 1: gestor.registrarPrestamoIngenieria(); break;
            case 2: gestor.modificarPrestamoIngenieria(); break;
            case 3: gestor.devolverEquipoIngenieria(); break;
            case 4: gestor.buscarEquipoIngenieria(); break;
            case 5: // Volver al principal
        }
    } while (opcion != 5);
}
```

**Puntos clave:**
- **`private`**: Solo `MenuSistema` puede llamarlo
- **Delegación**: Delega la lógica a `GestorPrestamos`
- **Navegación**: Opción 5 vuelve al menú principal sin cerrar el programa

---

## 💼 CLASE GESTORPRESTAMOS - LÓGICA DE NEGOCIO

### Propósito:
Maneja toda la lógica de negocio: CRUD (Create, Read, Update, Delete) de préstamos.

### Estructura de Datos: ArrayList (Vectores Dinámicos)

```java
private ArrayList<EstudianteIngenieria> vector_ingenieros;
private ArrayList<EstudianteDiseno> vector_disenadores;
private ArrayList<ComputadorPortatil> vector_portatil;
private ArrayList<TabletaGrafica> vector_tableta;
```

**¿Qué es ArrayList?**
- **Vector dinámico**: Tamaño se ajusta automáticamente
- **Ventajas sobre array normal:**
  - No necesitas definir tamaño inicial
  - Crece automáticamente al agregar elementos
  - Se reduce al eliminar elementos
  - Métodos útiles: `add()`, `remove()`, `get()`, `size()`, `isEmpty()`

**Inicialización:**
```java
vector_ingenieros = new ArrayList<>();
```
- Crea un ArrayList vacío
- Tamaño inicial: 0
- Crece según se agreguen elementos

### Constructor:

```java
public GestorPrestamos() {
    vector_ingenieros = new ArrayList<>();
    vector_disenadores = new ArrayList<>();
    vector_portatil = new ArrayList<>();
    vector_tableta = new ArrayList<>();
}
```

**Inicializa los 4 vectores dinámicos vacíos.**

### Método `registrarPrestamoIngenieria()`

**Flujo completo:**

1. **Validar cédula:**
```java
String cedula = Validador.validarCedulaRecursivo("Ingrese la cédula: ", scanner.nextLine());
```
- Pide cédula y valida que solo contenga números (recursivamente)
- No acepta letras ni caracteres especiales

2. **Verificar duplicado:**
```java
if (buscarEstudiantePorCedula(cedula) != null) {
    System.out.println("Error: Este estudiante ya tiene un equipo asignado.");
    return;
}
```
- **Regla de negocio**: Un estudiante no puede tener múltiples equipos
- **`return`**: Sale del método si encuentra duplicado

3. **Validar datos del estudiante:**
```java
String nombre = Validador.validarTextoRecursivo("Ingrese el nombre: ", scanner.nextLine());
String apellido = Validador.validarTextoRecursivo("Ingrese el apellido: ", scanner.nextLine());
String telefono = Validador.validarTelefonoRecursivo("Ingrese el teléfono: ", scanner.nextLine());
int semestre = Validador.validarSemestreRecursivo("Ingrese el número de semestre (1-10): ", scanner.nextLine());
float promedio = Validador.validarPromedioRecursivo("Ingrese el promedio acumulado (0.0-5.0): ", scanner.nextLine());
```
- Cada campo se valida según su tipo
- **Cédula**: Solo números
- **Teléfono**: Solo números
- **Nombre/Apellido**: Solo letras y espacios

4. **Seleccionar tipo de equipo:**
```java
System.out.println("\nSeleccione el tipo de equipo:");
System.out.println("1. Computador Portátil");
System.out.println("2. Tableta Gráfica");
int tipoEquipo = Validador.validarOpcionMenuRecursivo("Opción: ", 1, 2, scanner.nextLine());
```

5. **Registrar equipo:**
```java
String serialEquipo = "";
if (tipoEquipo == 1) {
    serialEquipo = registrarComputadorPortatil();
} else {
    serialEquipo = registrarTabletaGrafica();
}
```
- Llama al método correspondiente según la opción
- Obtiene el serial del equipo registrado

6. **Crear y almacenar estudiante:**
```java
EstudianteIngenieria estudiante = new EstudianteIngenieria(
    cedula, nombre, apellido, telefono, semestre, promedio, serialEquipo
);
vector_ingenieros.add(estudiante);
```
- **Creación de objeto**: Usa el constructor con parámetros
- **`add()`**: Agrega el objeto al ArrayList
- El ArrayList crece automáticamente

### Método `modificarPrestamoIngenieria()`

**Características importantes:**

1. **Búsqueda con sobrecarga:**
```java
System.out.println("1. Buscar por cédula");
System.out.println("2. Buscar por serial del equipo");
int opcion = Validador.validarOpcionMenuRecursivo("Opción: ", 1, 2, scanner.nextLine());

EstudianteIngenieria estudiante = null;
if (opcion == 1) {
    estudiante = buscarEstudiantePorCedula(cedula);
} else {
    estudiante = buscarEstudiantePorSerialIngenieria(serial);
}
```
- **Sobrecarga de métodos**: Mismo propósito, diferentes parámetros
- **Flexibilidad**: Busca por cédula O por serial

2. **Modificación selectiva:**
```java
System.out.print("Nombre [" + estudiante.getNombre() + "]: ");
String nombre = scanner.nextLine().trim();
if (!nombre.isEmpty()) {
    estudiante.setNombre(Validador.validarTextoRecursivo("Nombre: ", nombre));
}
```
- **Muestra valor actual**: `[Juan]`
- **Enter para mantener**: Si está vacío, no modifica
- **Validación**: Si ingresa nuevo valor, lo valida antes de asignar

3. **Restricciones:**
- **NO se puede modificar**: Cédula ni serial del equipo
- **Se puede modificar**: Nombre, apellido, teléfono, semestre, promedio

### Método `devolverEquipoIngenieria()`

**Proceso:**

1. **Buscar estudiante:**
```java
EstudianteIngenieria estudiante = buscarEstudiantePorCedula(cedula);
```

2. **Mostrar datos:**
```java
mostrarEstudianteIngenieria(estudiante);
```

3. **Confirmación:**
```java
System.out.print("\n¿Está seguro de eliminar este préstamo? (s/n): ");
String confirmacion = scanner.nextLine().trim().toLowerCase();

if (confirmacion.equals("s") || confirmacion.equals("si")) {
    vector_ingenieros.remove(estudiante);
    System.out.println("\n✓ Equipo devuelto y préstamo eliminado exitosamente.");
}
```
- **Confirmación**: Evita eliminaciones accidentales
- **`remove()`**: Elimina el objeto del ArrayList
- **Reducción automática**: El ArrayList se reduce automáticamente

### Métodos de Búsqueda con Sobrecarga

**Sobrecarga de métodos (Polimorfismo):**

```java
// Búsqueda por cédula
public EstudianteIngenieria buscarEstudiantePorCedula(String cedula) {
    for (EstudianteIngenieria est : vector_ingenieros) {
        if (est.getCedula().equalsIgnoreCase(cedula)) {
            return est;
        }
    }
    return null;
}

// Búsqueda por serial
public EstudianteIngenieria buscarEstudiantePorSerialIngenieria(String serial) {
    for (EstudianteIngenieria est : vector_ingenieros) {
        if (est.getSerial_equipo().equalsIgnoreCase(serial)) {
            return est;
        }
    }
    return null;
}
```

**Conceptos:**
- **Mismo nombre, diferentes parámetros**: Sobrecarga
- **Búsqueda lineal**: Recorre el ArrayList hasta encontrar
- **`equalsIgnoreCase()`**: Comparación sin distinguir mayúsculas/minúsculas
- **`return null`**: Indica que no se encontró

**Búsqueda en ArrayList:**
```java
for (EstudianteIngenieria est : vector_ingenieros) {
    // Enhanced for loop (for-each)
    // Itera sobre cada elemento del ArrayList
}
```

### Método `imprimirInventarioTotal()`

**Funcionalidad completa:**

1. **Encabezado:**
```java
System.out.println("\n" + repetirCaracter('=', 80));
System.out.println("                    INVENTARIO TOTAL DE PRÉSTAMOS");
```

2. **Recorrer estudiantes de Ingeniería:**
```java
for (int i = 0; i < vector_ingenieros.size(); i++) {
    EstudianteIngenieria est = vector_ingenieros.get(i);
    mostrarEstudianteIngenieria(est);
    
    // Buscar equipo asociado
    ComputadorPortatil portatil = buscarPortatilPorSerial(est.getSerial_equipo());
    TabletaGrafica tableta = buscarTabletaPorSerial(est.getSerial_equipo());
    
    if (portatil != null) {
        // Mostrar datos del portátil
    } else if (tableta != null) {
        // Mostrar datos de la tableta
    }
}
```

**Puntos clave:**
- **`size()`**: Obtiene el tamaño actual del ArrayList
- **`get(i)`**: Obtiene el elemento en la posición `i`
- **Relación**: Busca el equipo usando el serial del estudiante
- **Muestra información completa**: Estudiante + Equipo

3. **Estadísticas finales:**
```java
System.out.println("Total de préstamos de Ingeniería: " + vector_ingenieros.size());
System.out.println("Total de préstamos de Diseño: " + vector_disenadores.size());
System.out.println("Total de computadores portátiles: " + vector_portatil.size());
System.out.println("Total de tabletas gráficas: " + vector_tableta.size());
```

### Métodos de Registro de Equipos

**`registrarComputadorPortatil()`:**

1. **Validar serial:**
```java
String serial = Validador.validarNoVacioRecursivo("Ingrese el serial: ", scanner.nextLine());
```

2. **Verificar duplicado:**
```java
if (buscarPortatilPorSerial(serial) != null) {
    System.out.println("Error: Ya existe un computador portátil con este serial.");
    return null;
}
```
- **Regla de negocio**: No puede haber seriales duplicados

3. **Validar datos:**
```java
String marca = Validador.validarTextoRecursivo("Ingrese la marca: ", scanner.nextLine());
float tamaño = Validador.validarFloatPositivoRecursivo("Ingrese el tamaño en pulgadas: ", scanner.nextLine());
float precio = Validador.validarFloatPositivoRecursivo("Ingrese el precio: ", scanner.nextLine());
```

4. **Submenú de Sistema Operativo:**
```java
System.out.println("\nSeleccione el sistema operativo:");
System.out.println("1. Windows 7");
System.out.println("2. Windows 10");
System.out.println("3. Windows 11");
int opcionSO = Validador.validarOpcionMenuRecursivo("Opción: ", 1, 3, scanner.nextLine());
String sistemaOperativo = "";
switch (opcionSO) {
    case 1: sistemaOperativo = "Windows 7"; break;
    case 2: sistemaOperativo = "Windows 10"; break;
    case 3: sistemaOperativo = "Windows 11"; break;
}
```
- **Switch**: Convierte opción numérica a texto descriptivo

5. **Crear y almacenar:**
```java
ComputadorPortatil portatil = new ComputadorPortatil(serial, marca, tamaño, precio, sistemaOperativo, procesador);
vector_portatil.add(portatil);
return serial;
```
- Retorna el serial para asociarlo con el estudiante

**Diferencia importante:**
- **`registrarComputadorPortatil()`**: Para estudiantes de Ingeniería (serial puede ser String)
- **`registrarComputadorPortatilDiseno()`**: Para estudiantes de Diseño (serial debe ser numérico)

---

## 📦 CLASES DE DOMINIO - MODELO DE DATOS

### Jerarquía de Herencia:

```
Estudiante (Clase base)
    ├─ EstudianteIngenieria
    └─ EstudianteDiseno

Dispositivo (Clase base)
    ├─ ComputadorPortatil
    └─ TabletaGrafica
```

### Clase `Estudiante` (Clase Base)

```java
public class Estudiante {
    private String Cedula;
    private String Nombre;
    private String Apellido;
    private String Teléfono;
```

**Conceptos:**

1. **Encapsulamiento:**
   - **`private`**: Atributos solo accesibles dentro de la clase
   - **Getters y Setters**: Métodos públicos para acceder/modificar
   - **Ventaja**: Control sobre cómo se modifican los datos

2. **Getters:**
```java
public String getCedula() {
    return Cedula;
}
```
- Retorna el valor del atributo
- Permite lectura controlada

3. **Setters:**
```java
public void setCedula(String cedula) {
    Cedula = cedula;
}
```
- Modifica el valor del atributo
- Permite validación antes de asignar (aunque no se hace aquí)

4. **Constructores:**
```java
// Constructor con parámetros
public Estudiante(String cedula, String nombre, String apellido, String teléfono) {
    Cedula = cedula;
    Nombre = nombre;
    Apellido = apellido;
    Teléfono = teléfono;
}

// Constructor sin parámetros (por defecto)
public Estudiante() {
}
```
- **Sobrecarga de constructores**: Múltiples formas de crear objetos
- **Constructor por defecto**: Necesario para herencia

### Clase `EstudianteIngenieria` (Herencia)

```java
public class EstudianteIngenieria extends Estudiante {
    private int numero_semestre;
    private float promedio_acumulado;
    private String serial_equipo;
```

**Conceptos de Herencia:**

1. **`extends Estudiante`:**
   - **Herencia**: Hereda todos los atributos y métodos de `Estudiante`
   - **Reutilización**: No necesita redefinir cédula, nombre, apellido, teléfono

2. **Atributos propios:**
   - `numero_semestre`: Específico de ingeniería
   - `promedio_acumulado`: Específico de ingeniería
   - `serial_equipo`: String (puede ser alfanumérico)

3. **Constructor:**
```java
public EstudianteIngenieria(String cedula, String nombre, String apellido, String teléfono, 
                             int numero_semestre, float promedio_acumulado, String serial_equipo) {
    super(cedula, nombre, apellido, teléfono);
    this.numero_semestre = numero_semestre;
    this.promedio_acumulado = promedio_acumulado;
    this.serial_equipo = serial_equipo;
}
```

**Análisis:**
- **`super(...)`**: Llama al constructor de la clase padre
- **Primero se inicializa la clase padre**, luego la hija
- **`this.`**: Distingue el atributo de la clase del parámetro

**Jerarquía de inicialización:**
```
1. Se crea objeto EstudianteIngenieria
2. Se llama super() → inicializa Estudiante
3. Se inicializan atributos propios de EstudianteIngenieria
```

### Clase `EstudianteDiseno` (Herencia)

**Diferencia clave:**
```java
private int serial_equipo;  // int, no String
```

**¿Por qué?**
- Requisito del proyecto: Estudiantes de Diseño tienen serial numérico
- Estudiantes de Ingeniería tienen serial String

**Constructor similar pero con `int serial_equipo`**

### Clase `Dispositivo` (Clase Base)

```java
public class Dispositivo {
    private String Serial;
    private String Marca;
    private Float Tamaño;
    private Float Precio;
```

**Atributos comunes a todos los dispositivos:**
- Serial, Marca, Tamaño, Precio

**Uso de `Float` (Wrapper):**
- **`Float`**: Clase wrapper (objeto)
- **`float`**: Tipo primitivo
- Permite usar `null` si es necesario

### Clase `ComputadorPortatil` (Herencia)

```java
public class ComputadorPortatil extends Dispositivo {
    private String sistema_operativo;
    private String procesador;
```

**Atributos adicionales:**
- Específicos de computadores portátiles
- Hereda: Serial, Marca, Tamaño, Precio

**Constructor:**
```java
public ComputadorPortatil(String serial, String marca, Float tamaño, Float precio, 
                         String sistema_operativo, String procesador) {
    super(serial, marca, tamaño, precio);
    this.sistema_operativo = sistema_operativo;
    this.procesador = procesador;
}
```

### Clase `TabletaGrafica` (Herencia)

```java
public class TabletaGrafica extends Dispositivo {
    private float peso;
    private String almacenamiento;
```

**Atributos adicionales:**
- Específicos de tabletas gráficas
- Hereda: Serial, Marca, Tamaño, Precio

---

## 🎯 CONCEPTOS DE POO APLICADOS

### 1. ENCAPSULAMIENTO

**Definición:** Ocultar los detalles internos de una clase y exponer solo lo necesario.

**En el código:**
```java
private String Cedula;  // Privado - no accesible desde fuera

public String getCedula() {  // Público - acceso controlado
    return Cedula;
}
```

**Beneficios:**
- Control sobre cómo se accede a los datos
- Facilita cambios internos sin afectar otras clases
- Previene modificaciones accidentales

### 2. HERENCIA

**Definición:** Una clase puede heredar atributos y métodos de otra clase.

**Ejemplo:**
```
Estudiante (padre)
    ↓
EstudianteIngenieria (hija)
    - Hereda: Cedula, Nombre, Apellido, Teléfono
    - Agrega: numero_semestre, promedio_acumulado, serial_equipo
```

**Ventajas:**
- **Reutilización de código**: No duplicar código común
- **Mantenibilidad**: Cambios en la clase padre afectan a todas las hijas
- **Organización**: Jerarquía lógica de clases

### 3. POLIMORFISMO

**Definición:** Mismo nombre, diferentes implementaciones.

**Tipos en el código:**

**a) Sobrecarga de métodos:**
```java
// Mismo nombre, diferentes parámetros
buscarEstudiantePorCedula(String cedula)
buscarEstudiantePorSerialIngenieria(String serial)
```

**b) Polimorfismo de herencia:**
```java
Estudiante est1 = new EstudianteIngenieria(...);
Estudiante est2 = new EstudianteDiseno(...);
// Ambos son Estudiante, pero comportamientos diferentes
```

### 4. ABSTRACCIÓN

**Definición:** Representar conceptos del mundo real como clases.

**En el código:**
- `Estudiante`: Abstracción de un estudiante real
- `Dispositivo`: Abstracción de un dispositivo electrónico
- `GestorPrestamos`: Abstracción del proceso de gestión

---

## 📊 ESTRUCTURAS DE DATOS UTILIZADAS

### ArrayList (Vector Dinámico)

**¿Qué es?**
- Implementación de lista dinámica en Java
- Tamaño se ajusta automáticamente

**Operaciones principales:**

1. **Agregar elemento:**
```java
vector_ingenieros.add(estudiante);
```
- Agrega al final
- El ArrayList crece automáticamente

2. **Eliminar elemento:**
```java
vector_ingenieros.remove(estudiante);
```
- Busca y elimina el objeto
- El ArrayList se reduce automáticamente

3. **Obtener tamaño:**
```java
int tamaño = vector_ingenieros.size();
```

4. **Obtener elemento:**
```java
EstudianteIngenieria est = vector_ingenieros.get(0);
```

5. **Verificar si está vacío:**
```java
if (vector_ingenieros.isEmpty()) {
    // No hay elementos
}
```

6. **Recorrer:**
```java
// For-each (recomendado)
for (EstudianteIngenieria est : vector_ingenieros) {
    // Procesar cada elemento
}

// For tradicional
for (int i = 0; i < vector_ingenieros.size(); i++) {
    EstudianteIngenieria est = vector_ingenieros.get(i);
    // Procesar
}
```

**Ventajas sobre Array tradicional:**
- Tamaño dinámico
- Métodos útiles integrados
- Más fácil de usar

**Desventajas:**
- Ligeramente más lento que arrays (marginal)
- Más consumo de memoria

---

## 🔄 FLUJO COMPLETO DE UN PRÉSTAMO

### Ejemplo: Registrar préstamo de Ingeniería

```
1. Usuario selecciona: "1. Estudiantes de Ingeniería"
   ↓
2. MenuSistema.mostrarSubmenuIngenieria()
   ↓
3. Usuario selecciona: "1. Registrar préstamo"
   ↓
4. MenuSistema llama: gestor.registrarPrestamoIngenieria()
   ↓
5. GestorPrestamos pide cédula
   ↓
6. Validador.validarNoVacioRecursivo() valida
   ↓
7. GestorPrestamos verifica duplicado
   ↓
8. GestorPrestamos pide y valida todos los datos
   ↓
9. Usuario selecciona tipo de equipo
   ↓
10. GestorPrestamos llama registrarComputadorPortatil()
    ↓
11. Se validan datos del equipo
    ↓
12. Se crea objeto ComputadorPortatil
    ↓
13. Se agrega a vector_portatil
    ↓
14. Se retorna serial
    ↓
15. Se crea objeto EstudianteIngenieria
    ↓
16. Se agrega a vector_ingenieros
    ↓
17. Mensaje de éxito
    ↓
18. Vuelve al submenú
```

---

## 🎓 CONCEPTOS ADICIONALES IMPORTANTES

### 1. Manejo de Excepciones (try-catch)

```java
try {
    int numero = Integer.parseInt(entrada);
    // Código que puede fallar
} catch (NumberFormatException e) {
    // Qué hacer si falla
    System.out.println("Error: Debe ingresar un número válido.");
}
```

**¿Por qué?**
- `Integer.parseInt()` puede lanzar excepción si el String no es un número
- `try-catch` captura la excepción y maneja el error elegantemente

### 2. Comparación de Strings

```java
// ❌ INCORRECTO
if (texto == "valor") { }

// ✅ CORRECTO
if (texto.equals("valor")) { }
if (texto.equalsIgnoreCase("valor")) { }  // Sin distinguir mayúsculas
```

**¿Por qué?**
- `==` compara referencias (direcciones de memoria)
- `.equals()` compara contenido

### 3. StringBuilder vs Concatenación

```java
// ❌ INEFICIENTE (crea muchos objetos)
String resultado = "";
for (int i = 0; i < 1000; i++) {
    resultado += "a";
}

// ✅ EFICIENTE
StringBuilder sb = new StringBuilder();
for (int i = 0; i < 1000; i++) {
    sb.append("a");
}
String resultado = sb.toString();
```

### 4. Modificadores de Acceso

- **`public`**: Accesible desde cualquier clase
- **`private`**: Solo accesible dentro de la misma clase
- **`protected`**: Accesible en la misma clase y subclases
- **`package` (sin modificador)**: Accesible en el mismo paquete

### 5. Métodos Estáticos

```java
public static String validarTextoRecursivo(...) {
    // ...
}
```

**Características:**
- Pertenece a la clase, no a una instancia
- Se llama: `Validador.validarTextoRecursivo(...)`
- No puede acceder a atributos no estáticos
- Útil para métodos utilitarios

---

## 📝 RESUMEN PARA LA EXPOSICIÓN

### Puntos Clave a Mencionar:

1. **Arquitectura:**
   - Separación de responsabilidades
   - Clases especializadas (Validador, Gestor, Menu)

2. **POO:**
   - Encapsulamiento (atributos private)
   - Herencia (Estudiante → EstudianteIngenieria/Diseno)
   - Polimorfismo (sobrecarga de métodos)
   - Abstracción (modelo del mundo real)

3. **Recursión:**
   - Todos los métodos de validación son recursivos
   - Garantizan datos válidos antes de continuar

4. **Estructuras de Datos:**
   - ArrayList (vectores dinámicos)
   - Tamaño se ajusta automáticamente

5. **Validaciones:**
   - Texto (solo letras)
   - Números (enteros, flotantes)
   - Rangos (semestre 1-10, promedio 0.0-5.0)
   - Opciones de menú

6. **Funcionalidades:**
   - CRUD completo (Create, Read, Update, Delete)
   - Búsqueda con sobrecarga
   - Inventario total
   - Validación de duplicados

---

## 🎯 PREGUNTAS FRECUENTES PARA LA EXPOSICIÓN

**P: ¿Por qué usar recursión en las validaciones?**
R: Garantiza que el usuario siempre ingrese datos válidos. Si ingresa algo inválido, el método se llama a sí mismo hasta obtener un valor válido.

**P: ¿Por qué ArrayList y no arrays normales?**
R: Los ArrayList son dinámicos - crecen y se reducen automáticamente. Los arrays tienen tamaño fijo y son menos flexibles.

**P: ¿Qué es la herencia?**
R: Permite que una clase (hija) herede atributos y métodos de otra (padre), evitando duplicar código.

**P: ¿Por qué los atributos son private?**
R: Encapsulamiento - control sobre cómo se accede a los datos. Solo se modifican a través de métodos públicos (setters).

**P: ¿Cómo funciona la búsqueda?**
R: Recorre el ArrayList comparando valores. Si encuentra coincidencia, retorna el objeto; si no, retorna null.

---

¡Éxito en tu exposición! 🚀



