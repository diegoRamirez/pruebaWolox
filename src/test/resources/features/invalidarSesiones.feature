# language: es

#Proyecto: Naiofy
#Cliente: Wolox
#Elaborado por: Diego Ramírez
#Email: ramirezarbelaezdiego@gmail.com

@FeatureName:InvalidarSesiones
Característica: Servicio de invalidar sesiones

  @ScenarioName:001_casoExitosoInvalidarSesion
  Esquema del escenario: 001 - Validar caso exitoso invalidar sesiones con usuario Admin
    Dado que se quiere invalidar la sesion de usuario "<TIPO>"
    Cuando se consume el sevicio de invalidar sesion
    Entonces el servicio debe invalidar la sesion y retornar el"<STATUS_CODE>"

    Ejemplos:
      | TIPO           | STATUS_CODE |
      | Admin          |200          |

  @ScenarioName:002_NoDebeInvalidarSesionConUsuarioRegular
  Esquema del escenario: 002 - Validar que no se puede invalidar sesiones con usuario Regular
    Dado que se quiere invalidar la sesion de usuario "<TIPO>"
    Cuando se consume el sevicio de invalidar sesion
    Entonces el servicio no debe invalidar la sesion y retornar statusCode diferente a"<STATUS_CODE>"

    Ejemplos:
      | TIPO           | STATUS_CODE |
      | Regular        |200          |

  @ScenarioName:003_TokenFalso
  Esquema del escenario: 003 - Validar que no acepte un token falso
    Dado que se quiere invalidar la sesion de usuario "<TIPO>"
    Cuando se consume el sevicio de invalidar sesion con token no valido
    Entonces el servico de invalidar sesion debe retornar el"<STATUS_CODE>"

    Ejemplos:
      | TIPO           | STATUS_CODE |
      | Admin          |401          |
