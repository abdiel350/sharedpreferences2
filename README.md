-Se procede a la creacion de un nuevo proyecto usando Android Studio Koala Feature Drop version 2024.1.2 -Luego New Project , seguido procederemos a utilizar la plantilla Navigation Drawer Views Activity.
-En nuestra siguiente ventana, en donde dice Name se procede a colocar nuestro nombre de proyecto el cual es SharedPreferences2, seguido de nuestro Package name el cual llevara como nombre es.ua.eps.sharedpreferences2,
lenguaje Kotlin, SDK API 24 ("Nougat"; Android 7.0), seguido de Finish para la creación. -El siguiente paso seria la creación de nuestro dispositivo virtual: Create Virtual Device, seleccionamos 
la opcion Phone seguido de Pixel 2, API 34 y la version de Android (14). Se debe utilizar Gradle 8.7 que es la version que da Koala por defecto.

Para crear esta aplicación, necesitamos implementar las siguientes funcionalidades:
   1-Pantalla principal (HomeFragment):
        Un EditText para que el usuario ingrese texto.
        Un TextView para mostrar el texto ingresado, con el formato aplicado según las preferencias del usuario.
        Un botón Previsualizar que, al presionarlo, actualiza el TextView con el texto formateado según las preferencias.
        Un botón Cerrar Aplicación que cierra la aplicación.
    2-Pantalla de preferencias (PreferencesActivity o PreferencesFragment):
        Esta pantalla se accede desde un menú Drawer.
        Contiene dos categorías o pantallas de preferencias:
            Formato Básico: Con opciones para ajustar el tamaño de texto (1 a 50), el color del texto (lista de colores), el color de fondo (lista de colores) y opciones para Negrita (bold) y Cursiva (italic).
            Formato Avanzado: Con opciones para ajustar la transparencia (alpha, de 0 a 1) y la rotación (de 0 a 360 grados).
Sabiendo esto procedo a explicar un poco la app, al presionar el boton de  "Preview":
        El texto ingresado en el EditText se muestra en el TextView con el formato seleccionado en las preferencias.
        Si no hay texto, se muestra un mensaje indicando que se debe introducir texto.

 El boton close es para "Cerrar Aplicación":
        La aplicación se cierra cuando el usuario presiona este botón.
        ************************************************************************
        -Sabiendo que vamos a trabajar con SharedPreferences: El cual se utiliza para guardar y recuperar las opciones seleccionadas por el usuario en la pantalla de preferencias.
         -PreferenceActivity o PreferenceFragment: El caul define las preferencias que permiten configurar el formato básico y avanzado.
         -DrawerLayout: Para mostrar el menú de navegación donde se puede acceder a las preferencias.
         *************************************************************************************************
        - La parte mas complicado o donde tuve un poco de problema fue en el  Manejo de preferencias,al integrar las preferencias con el TextView 
         para que los cambios de tamaño de texto, colores y estilos se reflejen dinámicamente cuando el usuario presiona el botón Previsualizar, debido a que todo se debe aplicar en tiempo real.
        - Aplicar cambios como la rotación del TextView y la transparencia de manera visual, mientras aseguraba que el diseño siga siendo funcional en diferentes tamaños de pantalla.
        -La implementacion adecuada para la navegación entre la pantalla principal y las preferencias utilizando un DrawerLayout y asegurando que las preferencias se mantengan sincronizadas con la interfaz principal.
