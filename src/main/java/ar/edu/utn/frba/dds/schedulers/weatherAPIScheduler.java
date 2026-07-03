package ar.edu.utn.frba.dds.schedulers;

import ar.edu.utn.frba.dds.services.WeatherAPIService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class weatherAPIScheduler {
  private final WeatherAPIService weatherAPIService;

  public weatherAPIScheduler(WeatherAPIService weatherAPIService) {
    this.weatherAPIService = weatherAPIService;
  }

  @Scheduled(fixedRate = 300000) // 300000 ms = 5 minutos
  public void obtenerDatosClimaticos() {
    weatherAPIService.obtenerDatosClimaticos();
  }

  //Delay para que no mande vacio
  @Scheduled(fixedRate = 60000, initialDelay = 300000) //60000 = 1 min
  public void analizarUltimoDatoClimatico() {
    System.out.println("Schedule de analizar");
    weatherAPIService.analizarUltimoDatoClimatico();
  }
}