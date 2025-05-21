# PROYECTO DE EMPRESA: SISTEMA DE FICHAJE MiMPC 📝

Realizado por Carlos Cañada Sánchez


# INDICE 🗃️
- INTRODUCCIÓN
- REQUISITOS MÍNIMOS
- DESCARGA DEL PROYECTO
- MANUAL DE USUARIO

ENLACE AL VIDEO EXPLICATIVO:
https://youtu.be/1OnIXJ5Aggw


# INTRODUCCIÓN 🤵‍♂️

En este repositorio vengo a presentar mi proyecto final de empresa que consta de un sistema de fichaje para empleados.

Para trabajadores de la empresa registrará si ha entrado o ha salido en una base de datos. Por otro lado, el usuario administrador al ingresar su código tendrá a su disposición un listado de los fichajes de los trabajadores al area de trabajo.


# REQUISITOS MÍNIMOS 💻

Para poder utilizar el programa será necesario tener instalados los siguientes programas:

- Java Runtime Enviroment 23
- javafx-sdk-24.0.1
- Base de datos MySQL

Primero, para descargar el JRE 23 debemos ir al enlace de Oracle que les dejo a continuación:

https://www.oracle.com/es/java/technologies/downloads/

Para instalar el correcto, es necesario que elija el sistema operativo del equipo que esté usando y descargue el enlace que pone "x64 Installer"

![image](https://github.com/user-attachments/assets/ad9e5e5d-16cf-4fe0-b139-7fd0913f98de)


Segundo, para descargar las librerías de JavaFX deben ir al siguiente enlace:

https://gluonhq.com/products/javafx/

Bajamos al apartado de "Downloads" y en el filtro de búsqueda seleccionamos la versión 24.0.1, el sistema operativo que estemos usando y en el tipo elejimos SDK para descargar el enlace que necesitamos.

![image](https://github.com/user-attachments/assets/5cf0f197-157c-4478-904a-c2175329b91a)

Una vez descargado, tendremos una carpeta comprimida como la siguiente:

![image](https://github.com/user-attachments/assets/46a1d80e-3e57-4a08-b0ba-4162257c8591)

Al descomprimirla, es importante que la ruta de destino sea en la carpeta raiz del sistema "C:/" para que el ejecutable pueda leer las librerías necesarias de JavaFX.

![image](https://github.com/user-attachments/assets/95a5cbe8-6c64-4177-a464-b91db796f88d)

Por último, es necesario que tengamos una base de datos en la que podamos guardar los datos del programa. En mi caso, tengo dos contenedores conectado de MySQL y PhPMyAdmin en el cual tengo conectado mi programa.

![image](https://github.com/user-attachments/assets/0feea163-3b3f-4cd3-a82d-bddaed59eca2)

![image](https://github.com/user-attachments/assets/e658d2c4-77c2-4fc2-8da2-f8f0057e0df1)

En el video explicativo que hay al inicio del documento explico más detalladamente cómo debe tener montada la estructura de la base de datos y el código para que la app funcione correctamente.


# DESCARGA DEL PROYECTO 💾

Para la descarga, primero debe ir al repositorio del proyecto. En el siguiente enlace podrá llegar hasta él: 

https://github.com/Carloscs053/Proyecto_FFE.git

Abrimos la pestaña "<> Code" y descargamos el archivo ZIP

![image](https://github.com/user-attachments/assets/cf32a5ba-093d-40b7-bf7d-fbb408ea5939)

Vamos al directorio dónde se nos haya descargado el archivo (normalmente "Descargas") y extraemos el .zip

En la ventana que nos aparece, le indicamos la ruta dónde queramos tener nuestro programa:

![image](https://github.com/user-attachments/assets/c0457aae-3663-4f36-a4d0-e4ca8cdd5b63)

En la carpeta resultante de la extracción nos debe aparecer un listado de archivos como el siguiente:

![image](https://github.com/user-attachments/assets/9b0b1ee4-7ee8-40bb-95d0-c565b982aa6a)

Tendremos un directorio que dice "Proyecto_FFE" que es dónde tenemos el proyecto en Java y JavaFX. También tendremos un "README.md" que es este mismo documento y por último tendremos un archivo que se llama "MiPC Fichaje", este es el acceso directo a nuestra app, el cual podemos copiar o cortar y llevarlo a nuestro escritorio o a la ruta que deseemos.


# MANUAL DE USUARIO

El uso de nuestra app es realmente sencilla. Primero, debemos poner en marcha nuestra base de datos, que como he enseñado en capturas anteriores, tengo los contenedores de Docker de MySQL y PhPMyAdmin encendidos y el usuario logueado en el navegador. Una vez hecho esto, damos doble click en el acceso directo y nos saldrá una pantalla como la siguiente: 

![image](https://github.com/user-attachments/assets/1f1f4d2d-b97d-4d51-8d9d-556a62bf6663)

Los usuarios de prueba son los siguientes:

- Nombre: Admin Código: 1234 (IMPORTANTE: Por cómo está programada nuestra app, es muy importante que el usuario con derechos de administrador tenga de contraseña 1234 ya que de lo contrario no mostrará el listado de actividad de la empresa)
- Nombre: Trabajador1 Código: 4321
- Nombre: Belén Código: 9876

Vamos paso a paso:
La pantalla principal, como se ve en la última captura, consta del logo de la empresa, un título, un Text Area en el que escribiremos el código, un reloj, el botón de enviar y un pad numérico.
El pad numérico va del 0 al 9 y además tiene dos botones, uno de borrar uno a uno los dígitos y otro de resetear el Text Area.

Ahora pasemos al comportamiento de la app. Si introducimos un código inexistente, nos dará un feedback que consta de un mensaje en rojo que dice "Código incorrecto"

![image](https://github.com/user-attachments/assets/2c6459f2-c760-40a3-88af-7469b4d33a19)

Por otro lado, si ingresamos el código de un trabajador, nos aparecerá si es el fichaje de salida o el de entrada, acorde a la última actividad registrada en la base de datos.

![image](https://github.com/user-attachments/assets/ea3969fd-bb2f-4169-9213-fb6ae73b9b72)

![image](https://github.com/user-attachments/assets/6f6db0ca-c64e-463e-abc1-7dea8cbd2826)

Por último, si introducimos el código de un administrador, no registra la actividad, sino que muestra un listado con todas las actividades de los trabajadores con el siguiente formato:
Nombre del trabajador - Código del trabajador - Actividad - Fecha - Hora

![image](https://github.com/user-attachments/assets/533b7309-7ac0-42a8-a5a5-f7559dbac24a)

Y con eso concluiría la actividad de la aplicación.

Les vuelvo a dejar el enlace al video explicativo a continuación: https://youtu.be/1OnIXJ5Aggw

Espero que haya sido de su agrado :)
