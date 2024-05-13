# MISW4203-Rabbits-Mobile

Este repositorio contiene el código fuente para la aplicación móvil del proyecto MISW4203-Rabbits. Sigue las instrucciones a continuación para clonar y construir el archivo APK utilizando Android Studio.

## Requisitos Previos

Antes de comenzar, asegúrate de tener instalado lo siguiente:

- Android Studio (última versión recomendada)
- Git

## Clonar el Repositorio

Para obtener el proyecto en tu máquina local, sigue estos pasos:

1. Abre la terminal en tu sistema.
2. Clona el repositorio utilizando Git:
   ```bash
   git clone https://github.com/jfcopete/MISW4203-Rabbits-Mobile.git
   ```
3. Una vez clonado, navega al directorio del proyecto:
   ```bash
   cd MISW4203-Rabbits-Mobile
   ```

## Abrir el Proyecto en Android Studio

Sigue estos pasos para abrir el proyecto en Android Studio:

1. Abre Android Studio.
2. En la pantalla de inicio, selecciona "Open an existing project" (Abrir un proyecto existente).
3. Navega hasta el directorio donde clonaste el repositorio y selecciona el proyecto.
4. Espera a que Android Studio indexe el proyecto y complete la configuración inicial.

## Construir el APK

Para construir el APK, sigue estos pasos en Android Studio:

1. Ve al menú "Build" en la parte superior.
2. Selecciona "Build Bundle(s) / APK(s)" y luego "Build APK(s)".
3. Android Studio comenzará a construir el APK. Este proceso puede tomar unos minutos.
4. Una vez completado, verás una notificación en la esquina inferior derecha que dice "Build Successful" (Construcción exitosa).
5. Haz clic en "locate" en la notificación para abrir el directorio donde se ha guardado el APK o navega manualmente a `app/build/outputs/apk/debug` dentro del directorio del proyecto para encontrar el archivo APK.

## Instalar el APK en un Dispositivo

Para instalar el APK en un dispositivo Android:

1. Activa la opción "USB debugging" (Depuración USB) en tu dispositivo Android.
2. Conecta tu dispositivo a la computadora mediante un cable USB.
3. En Android Studio, selecciona 'Run' (Ejecutar) en el menú superior y elige tu dispositivo en el diálogo que aparece.
