# language: es

#Proyecto: Naiofy
#Cliente: Wolox
#Elaborado por: Diego Ramírez
#Email: ramirezarbelaezdiego@gmail.com

@FeatureName:Registro
Característica: Servicio de registro

  @ScenarioName:001_casoExitosoRegistro
  Esquema del escenario: 001 - Validar que el status code retornado sea 201
    Dado que se desea crear un nuevo usuario
    Cuando se consume el sevicio de registro
    Entonces el servicio debe retornar  "<STATUS_CODE>"

    Ejemplos:
    | STATUS_CODE |
    |201          |

  @ScenarioName:002_validacionDominioCorreo
  Esquema del escenario: 002 - Validar que solo se acepte el dominio de wolox
    Dado que se desea crear un nuevo usuario
    Cuando se ingresa un email con dominio diferente al de wolox
    Entonces el servicio debe retornar  "<STATUS_CODE>"

    Ejemplos:
      | STATUS_CODE |
      |422          |

  @ScenarioName:003_validacionFormatoFirstName
  Esquema del escenario: 003 - Validar que el campo firstName no reciba números
    Dado que se desea crear un nuevo usuario
    Cuando se ingresa un numero en el campo firstName
    Entonces el servicio debe retornar  "<STATUS_CODE>"

    Ejemplos:
      | STATUS_CODE |
      |422          |

  @ScenarioName:004_validacionFormatoLastName
  Esquema del escenario: 004 - Validar que el campo lastName no reciba números
    Dado que se desea crear un nuevo usuario
    Cuando se ingresa un numero en el campo lastName
    Entonces el servicio debe retornar  "<STATUS_CODE>"

    Ejemplos:
      | STATUS_CODE |
      |422          |

  @ScenarioName:005_validacionListadoErrores
  Esquema del escenario: 004 - Validar cuando se introduzcan varios errores se muestren todos juntos
    Dado que se desea crear un nuevo usuario
    Cuando se introducen errores en los tres campos de entrada
    Entonces el servicio debe retornar  "<STATUS_CODE>"
    Y se debe retorna un mensaje con los tres errores inducidos

    Ejemplos:
      | STATUS_CODE |
      |422          |



