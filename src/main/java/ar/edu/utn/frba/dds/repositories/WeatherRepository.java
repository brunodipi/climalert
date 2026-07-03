package ar.edu.utn.frba.dds.repositories;

import ar.edu.utn.frba.dds.DatoClimatico;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class WeatherRepository {

  private final List<DatoClimatico> datosClimaticos = new ArrayList<>();

  public void save(DatoClimatico datoClimatico) {
      datosClimaticos.add(datoClimatico);
  }

  public List<DatoClimatico> findAllDatosClimaticos() {
    return datosClimaticos;
  }

  public DatoClimatico ultimoDatoClimatico(){
    return datosClimaticos.getLast();
  }
}
