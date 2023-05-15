# ClaseAppMovil

## Vinilos

Vinilos is an app that allows music album collectors to organize and manage their information.

### Download

You can download the APK for Android at this link: [apk app vinilos](https://uniandes-my.sharepoint.com/:u:/g/personal/mr_gomezc1_uniandes_edu_co/EY52XxFMIKFKgMRAer-CeAIB43_K_iMSz_m1sFodYUsAnQ?e=ITLyOe)

### Compatibility
This application is functional from Android API 30, and on emulator or minimum Android 11 device.

### Prerequisites
Android Studio
Kotlin

### How to run the app from Android Studio

  1. Clone the repository on your local machine.
  2. Open Android Studio and select “Open an existing Android Studio project”.
  3. Navigate to the directory where you cloned the repository and click “OK”. 
  Alternatively, you can:
  1. Fork this project.
  2. On the Android welcome screen select Get from VCS.
  3. Add your github account and select the project.
 
  4. Once the project files are loaded go to File - Sync project with Gradle Files
  5. When finished, in the Build menu select Make Project
  6. In the toolbar, select the app in the run configuration menu.
  7. In the target device menu, select the device on which you want to run the app.
  8. If you don’t have any devices configured, you must create a virtual Android device to use Android Emulator or connect a physical device.
  9. Click on Run option from Run menu to run the app.
 
There you go! Now you should be able to run the app without any problems.

### Backend
The backend of the app is hosted on an AWS instance and is available at http://52.90.82.141:3000/

## To run the backend locally follow this instructions:

1. Download the repository on your local machine or clone the repository to a local directory.
2. Using a command line locate yourself into the downloaded directory or repository
3. Using a command line run the command ´npm install´
4. Using a command line run the command:

# development
$ npm run start

# watch mode
$ npm run start:dev

# production mode
$ npm run start:prod

For support please contact the authors of the BackEnd repository

Once repository is running please change the API host on the NetworkServiceAdaptor.kt file on this project.
____________________________________________________________________________________________________________________________

## Vinilos

Vinilos es una aplicación que permite a los coleccionistas de álbumes de música organizar y gestionar su información.

### Descarga

Puedes descargar el APK para Android en este enlace: [apk app vinilos](https://uniandes-my.sharepoint.com/:u:/g/personal/mr_gomezc1_uniandes_edu_co/EY52XxFMIKFKgMRAer-CeAIB43_K_iMSz_m1sFodYUsAnQ?e=ITLyOe)

### Compatibilidad

Esta aplicación es funcional desde la API 30 de Android, y en el emulador o dispositivo minimo Android 11.

## Requisitos previos

- Android Studio
- Kotlin

## Cómo ejecutar la app desde android studio

1. Clona el repositorio en tu máquina local. 
2. Abre Android Studio y selecciona "Open an existing Android Studio project".
3. Navega hasta el directorio donde clonaste el repositorio y haz clic en "OK".
De manera alternativa puedes:
1. Realiza fork a este proyecto.
2. En la pantalla de bienvenida de Android selecciona Get from VCS
3. Agrega tú cuenta de github y seleccionas el proyecto.

4. Una vez se cargan los archivos del proyecto vas a la opción File - Sync project with Gradle Files
5. Al terminar, en el menu Build seleccionas Make Project
6. En la barra de herramientas, selecciona la app en el menú de configuración de ejecución.
7. En el menú del dispositivo de destino, selecciona el dispositivo en el que deseas ejecutar la app.
8. Si no tienes ningún dispositivo configurado, debes crear un dispositivo virtual de Android para usar Android Emulator o conectar un dispositivo físico
10. Haz clic en la opción Run desde el menu Run para ejecutar la app

¡Listo! Ahora deberías poder ejecutar la app sin problemas.

### Backend

El backend de la app esta alojado en una instancia de AWS y esta disponible en http://52.90.82.141:3000/

## Para ejecutar el backend localmente, siga estas instrucciones:

1. Descargue el repositorio en su máquina local o clone el repositorio en un directorio local.
2. Usando una línea de comando, ubíquese en el directorio o repositorio descargado.
3. Usando una línea de comando, ejecute el comando ´npm install´
4. Usando una línea de comando, ejecute el comando:

# Desarrollo
$ npm run start

# Modo de observación
$ npm run start:dev

# Modo de producción
$ npm run start:prod

Para obtener soporte, comuníquese con los autores del repositorio BackEnd.

Una vez que se esté ejecutando el repositorio, cambie el host de la API en el archivo NetworkServiceAdaptor.kt en este proyecto.
