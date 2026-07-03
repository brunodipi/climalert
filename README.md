# Climalert
Trabajo practico realizado para la asignatura Diseño de Sistemas de Informacion de la UTN FRBA

## Sobre el proyecto
El mismo consulta datos climaticos de Buenos Aires mediante una [Api](https://www.weatherapi.com/docs/) cada un lapso de 5 minutos y lo almacena localmente. Además, cada 1 minuto analiza el utlimo estado almacenado y evalua si es de riesgo (una temperatura mayor a 35° o una humedad superior a 60%). En caso de determinar como riesgosa se notifica por mail a interesados. 

## Configuracion
El proyecto cuenta con un archivo [application.properties](https://github.com/brunodipi/climalert/blob/main/src/main/resources/application.properties) donde se deben configurar las siguientes variables:
- weather.api.key=${WEATHER_API_KEY}
- notificador.mail=${NOTIFICADOR_MAIL}
- notificador.password=${NOTIFICADOR_PASSWORD}

Para ello se deben definir esas variables de entorno y configurarlas acordemente
- WEATHER_API_KEY: [crear una cuenta en la API](https://www.weatherapi.com/signup.aspx) y copiar la key que brindan
- NOTIFICADOR_MAIL: Mail que envia las alertas
- NOTIFICADOR_PASSWORD: En configuracion de cuenta de Google -> Contraseñas de aplicaciones dar de alta una nueva aplicacion y generar un token del estilo aaaa bbbb cccc dddd
