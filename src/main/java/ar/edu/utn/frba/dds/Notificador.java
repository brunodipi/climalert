package ar.edu.utn.frba.dds;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Notificador {

  //Hardcodeados los que nos dice en consigna
  private List<String> correos = new ArrayList<>(List.of("dipietrobr1@gmail.com", "admin@clima.com", "emergencias@clima.com", "meteorologia@clima.com"));

  @Value("${notificador.mail}")
  private String remitente;

  @Value("${notificador.password}")
  private String password;

  public void notificarAlerta(DatoClimatico datoClimatico){
    for (String correo : correos) {
      mandarMail(correo, datoClimatico);
      System.out.println("Notificacion enviada a: " + correo);
    }
  }

  private void mandarMail(String destinatario, DatoClimatico datoClimatico){
    //Props server
    Properties propiedades = new Properties();
    propiedades.put("mail.smtp.host", "smtp.gmail.com");
    propiedades.put("mail.smtp.port", "587");
    propiedades.put("mail.smtp.auth", "true");
    propiedades.put("mail.smtp.starttls.enable", "true");

    //Sesión
    Session sesion = Session.getInstance(propiedades, new Authenticator() {
      @Override
      protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(remitente, password);
      }
    });

    try {
      //Crear el mensaje
      Message mensaje = new MimeMessage(sesion);
      mensaje.setFrom(new InternetAddress(remitente));
      mensaje.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
      mensaje.setSubject("ALERTA CLIMÁTICA: Condiciones críticas detectadas");

      //Armar el cuerpo usando tu DatoClimatico
      // Como tu DatoClimatico tiene la anotación @Data de Lombok, podemos usar los getters
      String cuerpoMail = "Hola,\n\n" +
          "El sistema Climalert ha detectado condiciones climáticas críticas. " +
          "A continuación, el reporte detallado:\n\n" +
          "CONDICIONES ACTUALES\n" +
          "-------------------------\n" +
          "Estado: " + datoClimatico.getCondicion() + "\n" +
          "Temperatura: " + datoClimatico.getTemperatura() + " °C\n" +
          "Sensación Térmica: " + datoClimatico.getSensacionTermica() + " °C\n" +
          "Humedad: " + datoClimatico.getHumedad() + " %\n" +
          "Presión Atmosférica: " + datoClimatico.getPresionMb() + " mb\n" +
          "Precipitaciones: " + datoClimatico.getPrecipitacionMm() + " mm\n" +
          "Índice UV: " + datoClimatico.getUv() + "\n" +
          "Visibilidad: " + datoClimatico.getVisibilidadKm() + " km\n\n" +
          "VIENTOS\n" +
          "-------------------------\n" +
          "Velocidad: " + datoClimatico.getViento() + " km/h\n" +
          "Dirección: " + datoClimatico.getDireccionViento() + "\n" +
          "Ráfagas: " + datoClimatico.getRafagas() + " km/h\n\n" +
          "Por favor, tome las precauciones necesarias.\n" +
          "Saludos,\nSistema de Alertas Climalert.";

      mensaje.setText(cuerpoMail);

      //Enviar
      Transport.send(mensaje);
      System.out.println("Correo de alerta enviado exitosamente a: " + destinatario);

    } catch (MessagingException e) {
      System.err.println("Error al enviar el correo de alerta a " + destinatario);
      e.printStackTrace();
    }
  }
}
