<em> # Proyecto LiterAlura </em>

**LiterAlura** es una aplicación de gestión de libros que consume la API pública de Gutendex para buscar y registrar información sobre libros. Además, permite realizar búsquedas avanzadas, listar libros por idioma, y consultar autores y sus detalles en una base de datos. Es un proyecto desarrollado con **Spring Boot**, **JPA**, y **REST API**.

## Funcionalidades

- **1 - Buscar libros por título:** *Consume la API de Gutendex para buscar un libro específico y guardarlo en la base de datos.*
- **2 - Listar libros registrados:** *Muestra un listado de todos los libros almacenados en la base de datos.*
- **3 - Listar autores registrados:** *Presenta los autores de los libros guardados junto con sus obras.*
- **4 - Listar autores vivos en un Determinado Año:** *Permite consultar qué autores estaban vivos en un año determinado*
- **5 - Listar Libros por Idioma:** *Muestra los idiomas de los libros guardados en la base de datos*
- **6 - Buscar Libros por Idioma:** *Permite elegir un idioma y busca en la base de datos los libros que están en ese idioma*
- **7 - Buscar los 15 Libros mas buscados:** *Busca en la Api Gutendex los 15 libros más descargados*
- **0 - Sale del programa**

## 🛠️ Tecnologías utilizadas
- **Java 17**
- **Spring Boot**
- **Hibernate/JPA** *para persistencia de datos.*
- **Gutendex API** *para la consulta de información sobre libros.*
- **Postgres** *como base de datos relacional.*
- **Jackson** *para la serialización/deserialización de JSON.*

## ⚙️ Estructura del proyecto
### Modelos principales
1. **`Datos`**: Representa la respuesta de la API de Gutendex.
2. **`DatosLibros`**: Contiene la información detallada de cada libro desde la API.
3. **`Libros`**: Representa la entidad persistida en la base de datos.

### Clases 
- **`Principal`**: Clase principal con el menú interactivo.
- **`LibrosController`**: Controlador REST para gestionar las operaciones de la aplicación.
- **`ConsumirApi`**: Maneja las llamadas a la API de Gutendex.
- **`ConvierteDatos`**: Convierte los datos obtenidos de la API en objetos de dominio.

### 📊 Ejemplos de uso
**Buscar un libro por título**
*1. Selecciona la opción 1 en el menú.*
*2. Ingresa el nombre del libro.*
*3. Si el libro existe, se mostrará su información y será guardado en la base de datos.*

  **Listar libros por idioma**
**1. Selecciona la opción 5 en el menú.**
**2. La aplicación mostrará los libros registrados y sus idiomas traducidos.*

  ### 🔍 Gutendex API
   **La aplicación utiliza la API pública de Gutendex para obtener información sobre libros.**
   *Más información en https://gutendex.com/.*

## 📝 Instalación y uso
1. **Clona este repositorio:**

git clone https://github.com/tuusuario/literalura.git
cd literalura
2. Configura la base de datos:
o Crea una base de datos en POSTGRES llamada literalura.
o Configura las credenciales en el archivo application.properties:
properties
Copiar código
spring.datasource.url=jdbc:mysql://localhost:3306/literalura
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
3. Ejecuta la aplicación:
o Con IntelliJ IDEA o cualquier IDE de tu preferencia:
 Importa el proyecto como un proyecto Maven.
 Ejecuta la clase principal LiterAluraApplication.
o
2. Configura la base de datos:
o Crea una base de datos en MySQL llamada literalura.
o Configura las credenciales en el archivo application.properties:
properties
Copiar código
spring.datasource.url=jdbc:mysql://localhost:3306/literalura
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
3. Ejecuta la aplicación:
o Con IntelliJ IDEA o cualquier IDE de tu preferencia:
 Importa el proyecto como un proyecto Maven.
 Ejecuta la clase principal LiterAluraApplication.
o O desde la línea de comandos:
bash
Copiar código
mvn spring-boot:run
4. Interacción con la aplicación:
o Accede al menú desde la terminal y explora las diferentes funcionalidades.
o Los libros y autores se guardarán automáticamente en la base de datos.

### 📜 Licencia
**Este proyecto está bajo la licencia MIT.**
*👨💻 Desarrollado por Roberto Carriero 📌*
*Buenos Aires, Argentina*