# NAIOFY tienda de albumes online API TEST (Cucumber-Rest Assured)
Esta suite de automatización está diseñada para probar los servicios asociados a la funcionalidad de venta de albumes online de la empresa NAIOFY

----------------------------------------

El proyecto cuenta con 33 escenarios de prueba distribuidos en los siguientes archivos:

* src/test/resources/features/registro.feature
* src/test/resources/features/login.feature
* src/test/resources/features/listadoUsuarios.feature
* src/test/resources/features/listadoAlbumes.feature
* src/test/resources/features/listadoFotosAlbum.feature
* src/test/resources/features/invalidarSesiones.feature
* src/test/resources/features/comprarAlbum.feature
* src/test/resources/features/albumesComprados.feature

**Los escenarios de registro incluyen:**

**Escenario:** Validar que el status code retornado sea 201. Este escenario valida que la respuesta del servicio sea correcta cuando se realizar un nuevo registro de usuario de manera exitosa. 

**Escenario:** Validar que solo se acepte el dominio de wolox. Este escenario valida que se muestre un error cuando en el formulario de registro se ingrese un email con un dominio diferente a wolox.com.ar

**Escenario:** Validar que en los campos firstName y lastName no se permitan caracteres numéricos.

===========================================================================

**Los escenarios de Login incluyen:**

**Escenario:** Validar caso exitoso de login. En este escenario se valida la respuesta exitosa cuando  se realiza correctamente el login. Además se verifica que se retorne
un token de autenticación, el atributo user_id en el body del response y que dicho atributo cuente con el formato especificado en los requerimientos. En este escenario también se 
valida que se pueda hacer login con los dos roles de usuario que maneja el sistema (Admin, Regular).

============================================================================

**Los escenarios de listar usuarios incluyen:**

**Escenario:** Validar caso exitoso de listar usuarios. En este escenario se valida la respuesta exitosa cuando  se realiza correctamente el consumo del servicio de listar usuarios.
Además se verifica que el formato de los objetos de respuesta sea el especificado en el requerimiento y que en el listado que trae la respuesta aparezcan usuarios con ambos roles, dependiendo del rol del usuario 
que se utilizó para consumir el servicio.

Admin: Puede ver todos los usuarios registrados.

Regular: Puede ver solo los usuarios regulares.

**Escenario:** Validar que no acepte un token falso. En este escenario se valida que al ejecutar el servicio de listar usuarios, utilizando un token de autenticación diferente al generado en el login, 
se retorne un mensaje de error y no se permita retornar el listado de usuarios. Este escenario es evaluado con rol de administrador y con rol de regular.

==================================================================================

**Los escenarios de listar fotos de un album incluyen:**

**Escenario:** Validar caso exitoso de listado de fotos de albumes. En este escenario se valida la respuesta exitosa cuando  se realiza correctamente el consumo del servicio de listar fotos de un album.
Además se verifica que el formato de los objetos de respuesta sea el especificado en el requerimiento. Este escenario se valida con los dos roles estipulados en el requerimiento (Admin, Regular).

**Escenario:** Validar que no acepte un token falso. En este escenario se valida que al ejecutar el servicio de listar fotos de un album, utilizando un token de autenticación diferente al generado en el login, 
se retorne un mensaje de error y no se permita retornar el listado de usuarios. Este escenario es evaluado con rol de administrador y con rol de regular.

==================================================================================

**Los escenarios de listar albumes incluyen:**

**Escenario:** Validar caso exitoso de listado albumes. En este escenario se valida la respuesta exitosa cuando  se realiza correctamente el consumo del servicio de listar albumes.
Además se verifica que el formato de los objetos de respuesta sea el especificado en el requerimiento y que los objetos retornados no presenten paginación. Este escenario se valida con los dos roles estipulados en el requerimiento (Admin, Regular).

**Escenario:** Validar que no acepte un token falso. En este escenario se valida que al ejecutar el servicio de listar albumes, utilizando un token de autenticación diferente al generado en el login, 
se retorne un mensaje de error y no se permita retornar el listado de usuarios. Este escenario es evaluado con rol de administrador y con rol de regular.

==================================================================================

**Los escenarios de invalidar sesiones incluyen:**

**Escenario:** Validar caso exitoso de invalidar sesion con un usuario admin. En este escenario se valida la respuesta exitosa cuando  se realiza correctamente el consumo del servicio de invalidar sesion.

**Escenario:** Validar que no se puede invalidar sesion con un usuario regular. En este escenario se valida que al intentar invalidar una sesion, estándo logueado con un usuario regular, el servicio retorne un mensaje de error.
  
**Escenario:** Validar que no acepte un token falso. En este escenario se valida que al ejecutar el servicio de invalidar sesion, utilizando un token de autenticación diferente al generado en el login, 
se retorne un mensaje de error y no se permita invalidar la sesion.

================================================================================

**Los escenarios de comprar un album incluyen:**

**Escenario:** Validar caso exitoso de comprar un  album. En este escenario se valida la respuesta exitosa cuando  se realiza correctamente el consumo del servicio de comprar un album.
 Este escenario se valida con los dos roles estipulados en el requerimiento (Admin, Regular).

**Escenario:** Validar que no acepte un token falso. En este escenario se valida que al ejecutar el servicio de comprar un album, utilizando un token de autenticación diferente al generado en el login, 
se retorne un mensaje de error. Este escenario es evaluado con rol de administrador y con rol de regular.

**Escenario:** Validar que no acepte comprar dos veces el mismo album con el mismo usuario. En este escenario se valida que un usuario no pueda comprar dos veces el mismo album, verificando que retorna un mensaje de error.
Este escenario se valida con los dos roles estipulados en el requerimiento (Admin, Regular).

================================================================================

**Los escenarios de albumes comprados incluyen:**

**Escenario:** Validar caso exitoso listado de albumes comprados. En este escenario se valida la respuesta exitosa cuando  se realiza correctamente el consumo del servicio de listar albumes comprados.
 Este escenario se valida con los dos roles estipulados en el requerimiento (Admin, Regular).

**Escenario:** Validar que no acepte un token falso. En este escenario se valida que al ejecutar el servicio de comprar un album, utilizando un token de autenticación diferente al generado en el login, 
se retorne un mensaje de error. Este escenario es evaluado con rol de administrador y con rol de regular.

**Escenario:** Validar que no acepte comprar dos veces el mismo album con el mismo usuario. En este escenario se valida que un usuario no pueda comprar dos veces el mismo album, verificando que retorna un mensaje de error.
Este escenario se valida con los dos roles estipulados en el requerimiento (Admin, Regular).

**Escenario:** Validar que retorne todos los albumes comprados. En este escenario se valida que el servicio de retornar todos los albumes comprados por el usuario.
Este escenario se valida con los dos roles estipulados en el requerimiento (Admin, Regular).

------------------------------------------------------------------------------------------
CREDENCIALES
------------------------------------------------------------------------------------------
En la carpeta del proyecto se encuentra un archivo de propiedades donde se encuentran las credenciales y userID de un usuario admin y un usuario regular con
los cuales se realizan las pruebas.

En caso de necesitar realizar estas pruebas con un usuario diferente, basta con ir a este archivo y modificar las propiedades que sea necesario.

El archivo se enecuentra en la siguente ruta dentro de la carpeta del proyecto:

* src/test/resources/configuration.properties


------------------------------------------------------------------------------------------
AMBIENTE
------------------------------------------------------------------------------------------
Para la ejecución del proyecto se debe contar con:

Java 1.8

gradle (Yo usé la versión 6.6.1)

intellij idea(O cualquier otro IDE)

------------------------------------------------------------------------------------------


EJECUCIÓN
------------------------------------------------------------------------------------------
El proyecto cuenta con un test runner por cada servicio automatizado, de manera que se puedan ejecutar de manera independiente:

* src/test/java/co/com/wolox/naiofy/test/runner/TestRunnerRegistro.java

* src/test/java/co/com/wolox/naiofy/test/runner/TestRunnerLogin.java

* src/test/java/co/com/wolox/naiofy/test/runner/TestRunnerListadoUsuarios.java

* src/test/java/co/com/wolox/naiofy/test/runner/TestRunnerListadoFotosAlbum.java

* src/test/java/co/com/wolox/naiofy/test/runner/TestRunnerListadoAlbumes.java

* src/test/java/co/com/wolox/naiofy/test/runner/TestRunnerInvalidarSesion.java

* src/test/java/co/com/wolox/naiofy/test/runner/TestRunnerComprarAlbum.java

* src/test/java/co/com/wolox/naiofy/test/runner/TestRunnerAlbumesComprados.java


-----------------------------------------------------------------------------------
REPORTES
-----------------------------------------------------------------------------------

Al ejecutar la automatización, se generará un reporte de cucumber donde se pueden apreciar los escenarios pasados y los fallidos.

Este reporte podrá ser observado en cualquier navegador y quedará alojado en la carpeta del proyecto en el siguiente path:

* target/cucumberReportAll.html


