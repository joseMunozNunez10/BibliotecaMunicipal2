# Biblioteca Municipal App

Aplicación Android desarrollada en Kotlin como proyecto académico para demostrar el uso de programación asíncrona, arquitectura moderna y componentes de Material Design 3.

## 🎯 Objetivo del Proyecto

El objetivo principal es desarrollar una aplicación que permita a los usuarios buscar libros por título o autor, consultando su disponibilidad. La funcionalidad clave es el uso de **Kotlin Coroutines** para ejecutar las operaciones de búsqueda en segundo plano, garantizando que la interfaz de usuario permanezca fluida y sin bloqueos.

## ✨ Características

- Búsqueda de libros por título o autor.
- Indicador de carga (`ProgressBar`) mientras se realiza la búsqueda.
- Visualización de resultados en una lista (`RecyclerView`).
- Interfaz de usuario limpia y moderna con Material Design 3.
- Arquitectura robusta y escalable (MVVM).

## ⚙️ Requerimientos Técnicos Implementados

1.  **Arquitectura MVVM**:
    -   **View (`MainActivity`)**: Gestiona la UI, observa los datos del `ViewModel` y delega las acciones del usuario.
    -   **ViewModel (`MainViewModel`)**: Contiene la lógica de presentación. Orquesta las llamadas al repositorio usando corrutinas y expone los datos a la vista a través de `LiveData`.
    -   **Model (`BookRepository`)**: Simula el acceso a una fuente de datos (API o base de datos) con un retardo (`delay(2000)`) para demostrar la asincronía.

2.  **Programación Asíncrona con Kotlin Coroutines**:
    -   **`viewModelScope`**: Se utiliza para lanzar corrutinas que están ligadas al ciclo de vida del `ViewModel`.
    -   **`launch`**: Inicia una corrutina sin devolver un resultado, ideal para operaciones de "disparar y olvidar" como la actualización de la UI.
    -   **`async(Dispatchers.IO)`**: Ejecuta la búsqueda en un hilo de fondo para no bloquear el hilo principal. Devuelve un `Deferred<T>` que contendrá el resultado.
    -   **`await()`**: Suspende la corrutina hasta que el resultado de `async` esté disponible.
    -   **`withContext(Dispatchers.Main)`**: Cambia el contexto de la corrutina al hilo principal de forma segura para actualizar la interfaz de usuario con los resultados.

3.  **Manejo de Ciclo de Vida**:
    -   El `Job` de la corrutina de búsqueda se almacena y se puede cancelar. Al usar `viewModelScope`, la cancelación es automática cuando el `ViewModel` se destruye (`onCleared`), previniendo fugas de memoria y operaciones en segundo plano innecesarias.

## 🚀 Cómo Compilar y Ejecutar

1.  Clona este repositorio.
2.  Abre el proyecto en la última versión de Android Studio.
3.  Asegúrate de que las dependencias de Gradle se sincronicen correctamente.
4.  Construye y ejecuta la aplicación en un emulador o dispositivo físico.

## 📚 Dependencias Clave

-   `androidx.core:core-ktx`
-   `com.google.android.material:material:1.12.0`
-   `androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.2`
-   `androidx.lifecycle:lifecycle-livedata-ktx:2.8.2`
-   `androidx.activity:activity-ktx:1.9.0`
