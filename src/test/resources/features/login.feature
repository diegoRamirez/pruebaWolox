# language: es

#Proyecto: Naiofy
#Cliente: Wolox
#Elaborado por: Diego Ramírez
#Email: ramirezarbelaezdiego@gmail.com

@FeatureName:LoginEnPortal
Característica: Servicio de login

  @ScenarioName:ObtenerToken
  Esquema del escenario: 001 - obtener token
    Dado Un cliente que desea loguearse en la web
    Cuando se consume el sevicio de login
    Entonces el servico debe retornar un token

    Ejemplos:
      | TIPO           |
      | Admin          |
      | Regular        |