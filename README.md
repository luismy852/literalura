<p align="center">
  <img src="https://github.com/user-attachments/assets/9a868d83-d76e-4708-a85b-756bd6a9c525" alt="banner">
</p>


 ![Badge en Desarollo](https://img.shields.io/badge/STATUS-TERMINADO-blue)

 Este proyecto es una aplicación de gestión de libros y autores, que consume datos de una API externa y los guarda en una base de datos. La aplicación permite realizar varias funcionalidades relacionadas con la consulta y almacenamiento de información sobre libros y sus autores.

 ## :hammer:Funcionalidades del proyecto
 - `Búsqueda de libros por título`: Permite buscar libros a través de su título o autor. Los resultados se obtienen mediante una consulta a una API externa que proporciona información sobre libros disponibles en la base de datos.

- `Listar Libros Consultados:`:  Muestra todos los libros que han sido consultados y guardados en la base de datos. Esta funcionalidad permite revisar la información de los libros que ya han sido procesados.

- `Listar Autores Consultados:`: Muestra todos los autores que han sido consultados y guardados en la base de datos. Esto incluye tanto autores vivos como fallecidos.
  
- `Listar Autores Vivos en un Año Determinado:`:Permite listar a los autores que siguen vivos en un año específico. Esta función filtra los autores por su año de fallecimiento (si está disponible) y devuelve aquellos que no tienen año de fallecimiento o su año de fallecimiento es posterior al año ingresado.

- `Listar Libros por Idioma:`: Filtra los libros almacenados en la base de datos por idioma, permitiendo a los usuarios obtener una lista de libros en un idioma específico.

## Uso de la API
Este proyecto consume una API externa (Gutendex) para obtener información sobre libros. Los datos se consultan en tiempo real cada vez que un usuario realiza una búsqueda por título. A continuación, se detalla el flujo de trabajo:
- `Solicitud a la API:`: <br> <br> La aplicación toma el título ingresado por el usuario, lo transforma para una URL (remplazando espacios por el valor %20), y realiza una petición a la API. <br><br>
La URL base de la API se define como una constante, y el título se concatena a esta URL usando replace para asegurar que no haya problemas en la URL final.<br><br>
Ejemplo de consulta: https://api.libros.com/busqueda?titulo=Don%20Quijote

- `Respuesta de la API:`: <br> <br>
La API devuelve un JSON que incluye detalles del libro, tales como titulo, autor, año_nacimiento, año_fallecimiento, idioma, etc. <br><br>
El JSON se convierte en un objeto Java usando una clase específica (por ejemplo, Datos), que permite extraer atributos como titulo y autor. <br> <br>

- `Guardado en la base de datos:`: <br> <br>
Si el libro no existe en la base de datos, la aplicación lo guarda. <br><br>
En caso de que ya exista, se omite el guardado y se informa al usuario. <br> <br>
Esto evita duplicados y optimiza el acceso a datos consultados previamente.

## Estructura de la Base de Datos
El proyecto utiliza dos tablas principales en la base de datos:
- `Tabla Libros:`: Almacena la información de cada libro (como título, idioma, etc.).
- `Campos:`: Id, Titulo, Autor_Id, Lenguaje.
- `Tabla Autores:`: Almacena la información de cada autor (como Nombre, Fecha de Nacimiento, etc.).
- `Campos:`: Id, Nombre, añoNacimiento, añoFallecimiento.

La relación entre libros y autores es de uno a muchos, es decir, un autor puede escribir varios libros, pero cada libro tiene un solo autor.

## ✔️ Tecnologías utilizadas
- `Java 17`
- `Spring`
- `PostgreSQL`
- `JPA`
