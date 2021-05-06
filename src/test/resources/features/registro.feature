# language: es

#Proyecto: Naiofy
#Cliente: Wolox
#Elaborado por: Diego Ramírez
#Email: ramirezarbelaezdiego@gmail.com

@FeatureName:LoginEnPortal
Característica: Servicio de login

  @ScenarioName:001_casoExitosoLogin
  Esquema del escenario: 001 - Validar que el status code retornado sea 200
    Dado Un cliente que desea loguearse en la web "<TIPO>"
    Cuando se consume el sevicio de login
    Entonces el servico debe retornar un "<STATUS_CODE>"
    Y debe retornar un token de autorizacion
    Y debe retornar el atributo user_id

    Ejemplos:
      | TIPO           | STATUS_CODE |
      | Admin          |200          |
      | Regular        |200          |


