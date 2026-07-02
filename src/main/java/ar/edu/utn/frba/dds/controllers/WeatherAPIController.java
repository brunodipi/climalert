package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.DatoClimatico;
import ar.edu.utn.frba.dds.services.WeatherAPIService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clima")
public class WeatherAPIController {

  private final WeatherAPIService climaService;

  public WeatherAPIController(WeatherAPIService climaService) {
    this.climaService = climaService;
  }

  @GetMapping("/")
  public List<DatoClimatico> climaHistorico() {
    List<DatoClimatico> datos = climaService.datosHistoricos();
    System.out.println(datos);
    return datos;
  }
}
