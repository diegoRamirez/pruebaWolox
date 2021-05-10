# language: es

#Proyecto: Naiofy
#Cliente: Wolox
#Elaborado por: Diego Ramírez
#Email: ramirezarbelaezdiego@gmail.com

@FeatureName:ListadoUsuarios
Característica: Servicio de listado de usuarios

  @ScenarioName:001_casoExitosoListadoUsuarios
  Esquema del escenario: 001 - Validar caso exitoso listado de usuarios
    Dado que se quiere obtener el listado de usuarios con un usuario "<TIPO>"
    Cuando se consume el sevicio de listado de usuarios
    Entonces el servico debe retornar  "<STATUS_CODE>"
    Y los usuarios retornados deben tener el formato especificado
    Y se deben traer usuarios de los dos tipos

    Ejemplos:
      | TIPO           | STATUS_CODE |
      | Admin          |200          |
      | Regular        |200          |

  @ScenarioName:002_TokenFalso
  Esquema del escenario: 002 - Validar que no acepte un token falso
    Dado que se quiere obtener el listado de usuarios con un usuario "<TIPO>"
    Cuando se consume el sevicio de listado de usuarios con un token no valido
    Entonces el servico debe retornar  "<STATUS_CODE>"

    Ejemplos:
      | TIPO           | STATUS_CODE |
      | Admin          |401          |
      | Regular        |401          |