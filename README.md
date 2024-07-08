# Challenge-Literalura

## Descripción

Challenge-Literalura es un proyecto desarrollado en Java utilizando Spring Boot. Este proyecto tiene como objetivo gestionar un catálogo de libros y autores, permitiendo realizar diversas operaciones como buscar libros por título, listar libros y autores registrados, y exhibir estadísticas sobre la cantidad de libros en un determinado idioma.

## Funcionalidades

- **Buscar libro por título:** Permite buscar libros en el catálogo mediante su título.
- **Listar libros registrados:** Muestra todos los libros que se encuentran registrados en el sistema.
- **Listar autores registrados:** Muestra todos los autores registrados en el sistema.
- **Listar autores vivos en un determinado año:** Permite listar los autores que estaban vivos en un año específico.
- **Listar libros por idioma:** Muestra los libros filtrados por el idioma seleccionado.

## Tecnologías Utilizadas

- Java
- Spring Boot
- JPA/Hibernate
- MySQL (o cualquier base de datos que utilices)
- Git

## Estructura del Proyecto

src/
├── main/
│ ├── java/
│ │ └── com/
│ │ └── perla/
│ │ └── curso/
│ │ └── alurachallenge/
│ │ ├── controllers/
│ │ ├── models/
│ │ ├── repositorio/
│ │ └── service/
│ └── resources/
│ ├── application.properties
│ └── static/
└── test/
└── java/

## Configuración del Proyecto

1. Clona el repositorio:
    ```bash
    git clone https://github.com/perlaserrano/Challenge-Literalura.git
    ```
2. Navega al directorio del proyecto:
    ```bash
    cd Challenge-Literalura
    ```
3. Configura la base de datos en el archivo `application.properties`:
    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/nombre_de_tu_base_de_datos
    spring.datasource.username=tu_usuario
    spring.datasource.password=tu_contraseña
    spring.jpa.hibernate.ddl-auto=update
    ```
4. Ejecuta el proyecto desde tu IDE o usando Maven:
    ```bash
    mvn spring-boot:run
    ```

## Uso

Una vez que el proyecto esté en funcionamiento, podrás interactuar con él a través de la consola. Se mostrarán diferentes opciones en un menú principal para navegar y utilizar las diversas funcionalidades.

## Contribuciones

Las contribuciones son bienvenidas. Por favor, sigue los siguientes pasos:

1. Haz un fork del proyecto.
2. Crea una rama con tu nueva funcionalidad o corrección de errores:
    ```bash
    git checkout -b mi-nueva-funcionalidad
    ```
3. Haz commit de tus cambios:
    ```bash
    git commit -m "Añadida nueva funcionalidad"
    ```
4. Sube tus cambios a tu fork:
    ```bash
    git push origin mi-nueva-funcionalidad
    ```
5. Abre un Pull Request en el repositorio original.

## Licencia

Este proyecto está bajo la licencia MIT.
