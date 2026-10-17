# QuindíoExpress

Proyecto académico de **Estructuras de Datos** de la **Universidad del Quindío**, desarrollado en Java para simular las operaciones de un centro de distribución de paquetes.

## Descripción

QuindíoExpress permite registrar y consultar paquetes, administrar repartidores, realizar despachos y confirmar entregas. Incluye algoritmos recursivos, búsqueda binaria y divide y vencerás, además de una lista simplemente enlazada genérica implementada en el proyecto.

La aplicación es de escritorio, utiliza **JavaFX** para la interfaz gráfica y mantiene la información **en memoria**; no necesita base de datos.

## Tecnologías

- Java 21
- JavaFX 21.0.6 y FXML
- Maven
- IntelliJ IDEA (opcional)

## Funcionalidades

- Registro y consulta de paquetes, con validación de códigos repetidos.
- Registro y consulta de repartidores.
- Despacho de paquetes en orden de llegada (**FIFO**) o por prioridad.
- Asignación de repartidores disponibles y confirmación de entregas.
- Consulta del historial de entregas.
- Consulta del peso total de paquetes por municipio mediante recursividad.
- Conteo recursivo de paquetes según una prioridad mínima.
- Búsqueda binaria de paquetes por código.
- Búsqueda del paquete de mayor peso mediante divide y vencerás.

## Estructuras de datos

| Estructura | Uso en el proyecto |
|---|---|
| `ArrayList` | Almacenamiento y recorrido de paquetes. |
| `HashMap` | Consultas por código, agrupación por municipio y gestión de repartidores y asignaciones. |
| `TreeSet` | Municipios sin duplicados y en orden. |
| `PriorityQueue` | Selección de paquetes para despacho prioritario. |
| `ListaSimpleEnlazada<T>` | Historial de entregas con una lista propia. |
| `ILista<T>`, `Iterable` e `Iterator` | Contrato y recorrido de la lista genérica. |

También se utilizan comparadores para definir diferentes criterios de ordenamiento de paquetes.

## Organización del código

El proyecto separa las responsabilidades mediante una estructura basada en MVC:

```text
MainViewController → MainController → ModelFactory → QuindioExpress
```

Paquetes principales:

```text
co.edu.uniquindio.quindioexpress
├── comparator
├── controller
├── factory
├── model
├── structures
├── view
└── Main.java
```

La interfaz gráfica se define mediante FXML.

## Requisitos

- **JDK 21** instalado.
- **Apache Maven** instalado, o Maven disponible desde el IDE.
- Conexión a Internet en la primera compilación, si Maven todavía no ha descargado las dependencias.

## Cómo ejecutar el proyecto

### Opción 1. Desde Maven (recomendada)

1. Clonar o descargar este repositorio.
2. Abrir una terminal en la carpeta que contiene `pom.xml`.
3. Ejecutar:

   ```bash
   mvn clean javafx:run
   ```

Maven compila el proyecto y ejecuta la aplicación JavaFX.

### Opción 2. Desde IntelliJ IDEA

1. Abrir la carpeta del proyecto en IntelliJ IDEA y permitir que Maven cargue las dependencias.
2. Comprobar que el SDK del proyecto sea **Java 21**.
3. Abrir **Run → Edit Configurations** y crear una configuración de tipo **Application**.
4. Seleccionar como clase principal:

   ```text
   co.edu.uniquindio.quindioexpress.Main
   ```

5. Seleccionar el módulo principal del proyecto y el JDK 21.
6. Si al ejecutar aparece el mensaje **«faltan los componentes de JavaFX runtime»**, abrir **Modify options → Add VM options** y configurar los módulos JavaFX:

   ```text
   --module-path "RUTAS_A_LOS_JAR_DE_JAVAFX" --add-modules javafx.controls,javafx.fxml
   ```

   Sustituir `RUTAS_A_LOS_JAR_DE_JAVAFX` por las rutas de los archivos JAR de JavaFX **21.0.6** descargados por Maven, separadas por `;` en Windows. En la configuración probada se incluyeron los JAR para Windows de `javafx-base`, `javafx-graphics`, `javafx-controls` y `javafx-fxml`.

7. Guardar la configuración y ejecutar con el botón verde **Run** o `Shift + F10`.

> **Nota:** La ejecución directa desde IntelliJ IDEA fue probada con Java 21 y las opciones de módulos JavaFX indicadas. Las rutas locales de JavaFX son específicas de cada computador y no se incluyen en este repositorio.

## Datos de prueba

Para demostrar las funcionalidades del sistema se prepararon datos iniciales de prueba con **30 paquetes, 5 municipios y 6 repartidores**.

## Propósito académico

El proyecto permite aplicar y analizar:

- Colecciones y estructuras de datos de Java.
- Estructuras genéricas propias e iteradores.
- Algoritmos recursivos y pila de llamadas.
- Búsqueda binaria y divide y vencerás.
- Complejidad temporal y espacial.
- Comparación y justificación de alternativas de implementación.

**Asignatura:** Estructuras de Datos  
**Actividad:** Parcial 1 — Gestión de operaciones de un centro de distribución  
**Institución:** Universidad del Quindío
