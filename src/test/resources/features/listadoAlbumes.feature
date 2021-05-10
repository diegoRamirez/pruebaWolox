# language: es

#Proyecto: Naiofy
#Cliente: Wolox
#Elaborado por: Diego Ramírez
#Email: ramirezarbelaezdiego@gmail.com

@FeatureName:obtenerListadoAlbumes
Característica: Servicio de listado de albumes

  @ScenarioName:001_casoExitosoListadoAlbumes
  Esquema del escenario: 001 - Validar caso exitoso listado de albumes
    Dado que se quiere obtener el listado de albumes con un usuario "<TIPO>"
    Cuando se consume el sevicio de listado de albumes
    Entonces el servico debe retornar el  "<STATUS_CODE>"
    Y los albumes retornados deben tener el formato especificado
    Y los albumes retornados no deben tener paginacion

    Ejemplos:
      | TIPO           | STATUS_CODE |
      | Admin          |200          |
      | Regular        |200          |

  @ScenarioName:002_TokenFalso
  Esquema del escenario: 002 - Validar que no acepte un token falso
    Dado que se quiere obtener el listado de albumes con un usuario "<TIPO>"
    Cuando se consume el sevicio de listado de albumes con token no valido
    Entonces el servico debe retornar el  "<STATUS_CODE>"

    Ejemplos:
      | TIPO           | STATUS_CODE |
      | Admin          |401          |
      | Regular        |401          |