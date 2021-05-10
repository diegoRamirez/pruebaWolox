# language: es

#Proyecto: Naiofy
#Cliente: Wolox
#Elaborado por: Diego Ramírez
#Email: ramirezarbelaezdiego@gmail.com

@FeatureName:obtenerAlbumesComprados
Característica: Servicio de listado de albumes comprados

  @ScenarioName:001_casoExitosoListadoAlbumesComprados
  Esquema del escenario: 001 - Validar caso exitoso listado de albumes comprados
    Dado que se quiere obtener el listado de albumes comprados con un usuario "<TIPO>"
    Cuando se consume el sevicio de listado de albumes comprados
    Entonces el servico debe retornar el "<STATUS_CODE>"
    Y los albumes comprados retornados deben tener el formato especificado

    Ejemplos:
      | TIPO           | STATUS_CODE |
      | Admin          |200          |
      | Regular        |200          |

  @ScenarioName:002_TokenFalso
  Esquema del escenario: 002 - Validar que no acepte un token falso
    Dado que se quiere obtener el listado de albumes comprados con un usuario "<TIPO>"
    Cuando se consume el sevicio de listado de albumes comprados con token no valido
    Entonces el servico debe retornar el "<STATUS_CODE>"

    Ejemplos:
      | TIPO           | STATUS_CODE |
      | Admin          |401          |
      | Regular        |401          |

  @ScenarioName:003_RetornarTodosLosAlbumesComprados
  Esquema del escenario: 003 - Validar que retorne todos los albumes comprados
    Dado que se quiere obtener el listado de albumes comprados con un usuario "<TIPO>"
    Cuando se realizan compras y se consume el sevicio de listado de albumes comprados
    Entonces debe retornar todos los albumes comprados por el usuario

    Ejemplos:
      | TIPO           |
      | Admin          |
      | Regular        |