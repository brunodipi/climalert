package ar.edu.utn.frba.dds.services;

import ar.edu.utn.frba.dds.DatoClimatico;
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

  public WeatherAPIService(WeatherRepository repoClima){
    this.repoClima = repoClima;
  }

  public void obtenerDatosClimaticos() {
    String url = "https://api.weatherapi.com/v1/current.json?key=" + apiKey + "&q=" + ciudad;
    //System.out.println("Intentando consultar la URL: " + url);

    RestTemplate restTemplate = new RestTemplate();
    try {
      weatherApiDTO respuesta = restTemplate.getForObject(url, weatherApiDTO.class);
      if(respuesta != null) {
        double temperatura = respuesta.getCurrent().getTempC();
        double viento = respuesta.getCurrent().getWindKph();

        System.out.println("--- Reporte del Clima en " + ciudad + " ---");
        System.out.println("Temperatura: " + temperatura + " °C");
        System.out.println("Viento: " + viento + " km/h");

        DatoClimatico dato = new DatoClimatico(temperatura, viento);
        repoClima.save(dato);
      }
    } catch (Exception e) {
      System.out.println("Error al consultar la API: " + e.getMessage());
    }
  }


  public List<DatoClimatico> datosHistoricos() {
    return repoClima.findAllDatosClimaticos();
  }
}