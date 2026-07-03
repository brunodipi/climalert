package ar.edu.utn.frba.dds.services;

import ar.edu.utn.frba.dds.DatoClimatico;
import ar.edu.utn.frba.dds.Notificador;
import ar.edu.utn.frba.dds.dto.weatherApiDTO;
import ar.edu.utn.frba.dds.repositories.WeatherRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherAPIService {
  @Value("${weather.api.key}")
  private String apiKey;

  @Value("${weather.api.ciudad}")
  private String ciudad;

  private WeatherRepository repoClima;
  private Notificador notificador;

  public WeatherAPIService(WeatherRepository repoClima, Notificador notificador){
    this.repoClima = repoClima;
    this.notificador = notificador;
  }

  public void obtenerDatosClimaticos() {
    String url = "https://api.weatherapi.com/v1/current.json?key=" + apiKey + "&q=" + ciudad;
    //System.out.println("Intentando consultar la URL: " + url);

    RestTemplate restTemplate = new RestTemplate();
    try {
      weatherApiDTO respuesta = restTemplate.getForObject(url, weatherApiDTO.class);
      if(respuesta != null) {
        DatoClimatico dato = new DatoClimatico(
            respuesta.getCurrent().getTempC(),
            respuesta.getCurrent().getFeelslikeC(),
            respuesta.getCurrent().getCondition().getText(),
            respuesta.getCurrent().getHumidity(),
            respuesta.getCurrent().getWindKph(),
            respuesta.getCurrent().getWindDir(),
            respuesta.getCurrent().getGustKph(),
            respuesta.getCurrent().getPrecipMm(),
            respuesta.getCurrent().getPressureMb(),
            respuesta.getCurrent().getUv(),
            respuesta.getCurrent().getVisKm(),
            respuesta.getCurrent().getChanceOfRain()
        );

        System.out.println("--- Reporte del Clima en " + ciudad + " ---");
        System.out.println("Temperatura: " + dato.getTemperatura() + "°C");
        System.out.println("Humedad: " + dato.getHumedad() + "%");

        repoClima.save(dato);
      }
    } catch (Exception e) {
      System.out.println("Error al consultar la API: " + e.getMessage());
    }
  }

  public List<DatoClimatico> datosHistoricos() {
    return repoClima.findAllDatosClimaticos();
  }

  public void analizarUltimoDatoClimatico(){
    DatoClimatico ultimoDato = repoClima.ultimoDatoClimatico();
    if(ultimoDato.analizarCritico()){
      System.out.println("Voy a notificar!!");
      notificador.notificarAlerta(ultimoDato);
    }
  }
}