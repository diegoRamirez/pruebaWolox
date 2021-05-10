# language: es

#Proyecto: Naiofy
#Cliente: Wolox
#Elaborado por: Diego Ramírez
#Email: ramirezarbelaezdiego@gmail.com

@FeatureName:LoginEnPortal
Característica: Servicio de login

  @ScenarioName:001_casoExitosoLogin
  Esquema del escenario: 001 - Validar caso exitoso login
    Dado Un cliente que desea loguearse en la web "<TIPO>"
    Cuando se consume el sevicio de login
    Entonces el servico debe retornar un "<STATUS_CODE>"
    Y debe retornar un token de autorizacion
    Y debe retornar el atributo user_id
    Y el cuerpo de la respuesta debe ser acorde al formato especificado

    Ejemplos:
      | TIPO           | STATUS_CODE |
      | Admin          |200          |
      | Regular        |200          |


