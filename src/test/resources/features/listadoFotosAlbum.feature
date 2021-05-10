# language: es

#Proyecto: Naiofy
#Cliente: Wolox
#Elaborado por: Diego Ramírez
#Email: ramirezarbelaezdiego@gmail.com

@FeatureName:ObtenerListadoFotosAlbum
Característica: Servicio de listado de fotos de albumes

  @ScenarioName:001_casoExitosoListadoFotosAlbum
  Esquema del escenario: 001 - Validar caso exitoso listado de fotos de albumes
    Dado que se quiere obtener el listado de fotos de un album con un usuario "<TIPO>"
    Cuando se consume el sevicio de listado de fotos de un album
    Entonces el servico debe retornar "<STATUS_CODE>"
    Y las fotos retornadas deben tener el formato especificado

    Ejemplos:
      | TIPO           | STATUS_CODE |
      | Admin          |200          |
      | Regular        |200          |

  @ScenarioName:002_TokenFalso
  Esquema del escenario: 002 - Validar que no acepte un token falso
    Dado que se quiere obtener el listado de fotos de un album con un usuario "<TIPO>"
    Cuando se consume el sevicio de listado de fotos de un album con token no valido
    Entonces el servico debe retornar "<STATUS_CODE>"

    Ejemplos:
      | TIPO           | STATUS_CODE |
      | Admin          |401          |
      | Regular        |401          |