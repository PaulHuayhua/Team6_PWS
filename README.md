# Sistema de Alquiler de Peliculas

Proyecto (Java + Maven + JUnit 5) resuelto para la actividad
"Refactorizando Codigo Espagueti", con las funcionalidades asignadas:
**calcular costo, aplicar recargo por retraso, validar dias de alquiler**.

## Estructura

```
sistema-alquiler-peliculas/
├── pom.xml
├── src/
│   ├── main/java/
│   │   ├── original/
│   │   │   └── AlquilerPeliculas.java            (Parte 1 - codigo espagueti "ANTES")
│   │   └── refactorizado/
│   │       ├── CalculadorCostoAlquiler.java      (Parte 2 - logica de negocio aislada)
│   │       ├── ServicioAlquiler.java             (Parte 2 - orquesta el caso de uso)
│   │       ├── RepositorioAlquiler.java          (Parte 2 - interfaz de persistencia)
│   │       ├── RepositorioAlquilerConsola.java   (Parte 2 - implementacion por defecto)
│   │       ├── NotificadorCliente.java           (Parte 2 - interfaz de notificacion)
│   │       └── NotificadorClienteConsola.java    (Parte 2 - implementacion por defecto)
│   └── test/java/
│       └── refactorizado/
│           ├── CalculadorCostoAlquilerTest.java  (Parte 3 - pruebas AAA sobre el calculo)
│           └── ServicioAlquilerTest.java         (Parte 3 - pruebas AAA sobre el flujo, con fakes)
```

## Como ejecutar

Con Maven instalado, desde la carpeta del proyecto:

```bash
# Compilar y correr las pruebas unitarias
mvn test

# Ejecutar el codigo original (ver la salida "desordenada" por consola)
mvn compile exec:java -Dexec.mainClass="original.AlquilerPeliculas"

# Ejecutar el codigo refactorizado (mismo resultado numerico)
mvn compile exec:java -Dexec.mainClass="refactorizado.ServicioAlquiler"
```

Si no tienes el plugin `exec`, tambien puedes compilar y correr manualmente:

```bash
javac -d out $(find src/main/java -name "*.java")
java -cp out original.AlquilerPeliculas
java -cp out refactorizado.ServicioAlquiler
```

## Que revisa cada parte de la actividad

- **Parte 1 (Analizar):** `original/AlquilerPeliculas.java` concentra
  validacion, calculo de tarifas, recargo, "persistencia" y "notificacion"
  en un solo metodo (`procesarAlquiler`), con condicionales anidados,
  valores magicos, y clases fuertemente acopladas a `System.out` (dificil
  de probar de forma aislada).
- **Parte 2 (Refactorizar):**
  - `CalculadorCostoAlquiler` contiene solo la logica de negocio (calculo
    de tarifas, recargo y validacion), sin ninguna dependencia externa.
  - `ServicioAlquiler` orquesta el caso de uso, pero ya no depende
    directamente de la consola: recibe por constructor un
    `RepositorioAlquiler` y un `NotificadorCliente` (interfaces). Por
    defecto usa las implementaciones `*Consola`, que imprimen exactamente
    los mismos mensajes que el codigo original, pero en produccion (o en
    pruebas) se pueden reemplazar por otras implementaciones (base de
    datos real, envio de correo real, o un doble de prueba) sin tocar
    `ServicioAlquiler`.
  - El resultado numerico es identico al original para las mismas
    entradas (15.0 para ESTRENO/3 dias/0 retraso, 7.0 para CLASICO/2
    dias/2 retraso).
- **Parte 3 (Pruebas Unitarias):**
  - `CalculadorCostoAlquilerTest.java`: 4 pruebas AAA sobre el calculo
    puro (caso exitoso, caso con recargo, caso limite de validacion y
    tipo de pelicula desconocido).
  - `ServicioAlquilerTest.java`: 2 pruebas AAA sobre el flujo completo,
    usando dobles de prueba (fakes) para el repositorio y el notificador,
    verificando el costo devuelto y que ambos colaboradores fueron
    invocados, sin imprimir nada por consola.
- **Parte 4 (Ejecutar y Verificar):** corre `mvn test` y adjunta en tu
  entrega la captura de pantalla de la consola/IDE mostrando las 6
  pruebas en verde (`Tests run: 6, Failures: 0, Errors: 0`).

## Nota

Este es un ejemplo de referencia para guiarte en la actividad. Se
recomienda adaptar nombres de clases/paquetes segun lo pida tu docente,
agregar tus propios casos de prueba si lo consideras necesario, y generar
tu mismo la captura de la ejecucion en tu entorno (IDE o terminal) para
la entrega — no reemplaza esa evidencia.
