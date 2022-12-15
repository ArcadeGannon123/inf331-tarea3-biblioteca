# inf331-tarea3-biblioteca
#### Nombre: Javier Vargas

## Descripción
Prueba de concepto para un sistema de registro y búsqueda de libros. 

## Instalación
- Este programa requiere jdk-17.0.5
- Descargar el programa y guardardelo donde más le convenga.

## Ejecución
Dentro de la capeta */src* ejecutar los siguientes comandos:
```sh
javac biblioteca/Main.java biblioteca/Registro.java biblioteca/ConsoleInput.java  
java biblioteca.Main
```
Para ejecutar las pruebas unitarias, ejecutar los siguientes comandos:
```sh
javac -cp junit-4.13.2.jar;. biblioteca/*.java   
java -cp junit-4.13.2.jar;hamcrest-core-1.3.jar;. org.junit.runner.JUnitCore  biblioteca.RegistroTest
```
Asegurece de que existan los archivos **junit-4.13.2.jar** y **hamcrest-core-1.3.jar**  dentro de la carpeta */src*.
Covertura de pruebas del 91%, consulte el archivo ECLEmma.html para más detalles.
