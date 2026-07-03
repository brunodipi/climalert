package ar.edu.utn.frba.dds;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DatoClimatico {

  private double temperatura;
  private double sensacionTermica;
  private String condicion;
  private double humedad;
  private double viento;
  private String direccionViento;
  private double rafagas;
  private double precipitacionMm;
  private double presionMb;
  private double uv;
  private double visibilidadKm;
  private int chanceDeLluvia;

  //Para esta primera iteración solamente consideraremos como “alerta” a una temperatura mayor a
  //35° y una humedad superior a 60%.
  public Boolean analizarCritico(){
    return this.temperatura>8 || this.humedad>60;
  }
}
