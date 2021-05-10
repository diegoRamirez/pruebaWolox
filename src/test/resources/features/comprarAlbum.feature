# language: es

#Proyecto: Naiofy
#Cliente: Wolox
#Elaborado por: Diego Ramírez
#Email: ramirezarbelaezdiego@gmail.com

@FeatureName:ComprarAlbum
Característica: Servicio de comprar albumes

  @ScenarioName:001_casoExitosoComprarAlbumes
  Esquema del escenario: 001 - Validar caso exitoso compra de albumes
    Dado que se quiere comprar un album con un usuario "<TIPO>"
    Cuando se consume el sevicio de compra de albumes
    Entonces el servico debe retornar el"<STATUS_CODE>"

    Ejemplos:
      | TIPO           | STATUS_CODE |
      | Admin          |201          |
      | Regular        |201          |

  @ScenarioName:002_TokenFalso
  Esquema del escenario: 002 - Validar que no acepte un token falso
    Dado que se quiere comprar un album con un usuario "<TIPO>"
    Cuando se consume el sevicio de compra de albumes con token no valido
    Entonces el servico debe retornar el"<STATUS_CODE>"

    Ejemplos:
      | TIPO           | STATUS_CODE |
      | Admin          |401          |
      | Regular        |401          |

  @ScenarioName:003_NoComprarDosVecesElMismoAlbum
  Esquema del escenario: 003 - Validar que no acepte comprar dos veces el mismo album con el mismo usuario
    Dado que se quiere comprar un album con un usuario "<TIPO>"
    Cuando se consume el sevicio de compra dos veces con el mismo usuario y mismo album
    Entonces el servico debe retornar el"<STATUS_CODE>"

    Ejemplos:
      | TIPO           | STATUS_CODE |
      | Admin          |400          |
      | Regular        |400          |