# MICROSERVICIO-BASE

Aplicacion base para la creacion de microservicios, ya esta configurado con el fin de remplazar 
algunas propiedades y estar preparado para el desarrrollo de los requerimientos pertinentes.

> ### ¡¡SI REALIZA UNA ACTUALIZACION DE ESTA APLICACION POR FAVOR ACTUALIZE LA DOCUMENTACION!!

## Configuracion

> pom.xml

- Se debe renobrar 
    - "groupId"
    - "artifactId"
    - "name"
    - "description"
    
> renombrar carpeta

- se debe renombrar src.main.java."groupId"."artifactId"
    
> resources/global/application.properties

- se debe configurar
    - Swagger configuration
    - LogBack configuration
    - Server configuration
    
## Estructura de la aplicacion

### Propiedades de configuracion

La aplicacion recolecta cada properties a traves de @EnableConfigurationProperties, lo que hace
es llevar a objeto con spring auto configuration las properties, estos objetos se deben armar en

>com.${groupId}.configuration.properties.*

La arquitectura de la configuracion es similar a la de todas nuestras apis, con la diferencia que esta
vez se busca descentralizar todas las properies en las que sean globales para toda la aplicacion y las
de ambiente de desarrollo para facil configuracion y ubicacion, springboot a traves de "spring.profiles.active"
al armar el target unifica todas las properties.

> https://www.baeldung.com/configuration-properties-in-spring-boot

- Configuraciones de variables
    - Global: se configuraran en "resources/global/aplication.properties", tiene como objetivo
    configurar variables que seran utilizadas en todos los ambientes por igual.
    - Environment: se configura en resources/${environment}/*.properties
        - application-api: solo incluir url de apis 
        - application-db: solo incluir url de base de datos
        - application-project: otras variables que se requieran utilizar
        
> tome en cuenta la modificacion de datos en los test, actualmente toma los valores globales, 
el application-api.properties (este se debe mockear en los test) y se le agrega el db y project correspondiente
        
### Logback para logger de informacion

Por cada ambiente se encuentra configurado para que muestre por diferentes herramientas 
cada modo de error se configura en cada appender pertinente en la etiqueta <filter>

```

 <filter class="ch.qos.logback.classic.filter.ThresholdFilter">
      <level>ERROR</level>
 </filter>
```

- dev: Modo DEBBUG por consola
- t1: Modo INFO por consola y modo ERROR fileLog
- pp: Modo INFO por consola y modo ERROR fileLog
- prod: Modo INFO por consola y modo ERROR fileLog y slack
- TEST: Modo INFO en consola

### Manejo de errores

Se implementa una clase llamada CentralControllerHandler para el manejo de excepciones,
SE DEBE EVITAR EL USO DE TRY CATCH, este controlador unifica el manejo de excepciones en toda la aplicacion.
Si la aplicacion lanza una excepcion no especificada en este controlador sera tomada por "handleException"
que espera como error un "Exception" padre de todas las excepciones. se deja especificado el manejo de "IllegalArgumentException" y "NullPointerException".
Si desea puede agregar todas las excepciones que vea necesarias tratar de manera especial.

> https://www.baeldung.com/exception-handling-for-rest-with-spring

## MENCIONES HONORIFICAS PARA UN BUEN MICROSERVICIO

### Desarrollo guiado por test

Se RECOMIENDA implementar cualquier requerimiento a traves del desarrollo guiado por test
TDD, buscando asi una mayor calidad de codigo y lograr que la aplicacion sea mantenible entre las caracteristicas principales.

> https://dosideas.com/wiki/Test_Driven_Development

### Arquitectura de 3 capas como diseño
Las aplicaciones JEE suelen seguir una arquitectura de 3 capas (también conocida como Modelo 2). 
La arquitectura se basa entonces en 3 capas principales:

- Capa de presentación
- Capa de lógica de negocio
- Capa de acceso a datos

> https://dosideas.com/cursos/course/introduccion-al-desarrollo-en-java-con-spring-framework-y-spring-boot/la-arquitectura-de-software

### Utilizar el core de java 8
Antes de agregar una dependencia, investigar si es posible implementar cualquier solucion utilizando el core de java8,
como tambien sus caracteristicas principales como la API de stream para Colecciones y lambdas, para asi
obtener codigo mas facil de entender.