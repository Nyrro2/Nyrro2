package Desafio.ConversorDeMoneda;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Principal {
    public static <convertido> void main(String[] args) throws IOException, InterruptedException {

//        Declarando varible
        Scanner lectura = new Scanner(System.in);
        String moneda;
        String extrangera;
        double convertido;
        double valor;

        String menu = """
                ******************************************************
                 Bienvenido a su casa convertido de monedas  Digital: "La Peseta"
                 Trabajamos con todas las monedas globales. Para su uso utilice las abreviaturas establecidas. Ejemplo:
                 1-DOP - Pesos Dominicano
                 2-USD - Dolar Estadounidense
                 3-EUR - Euro
                 4-MXN - Pesos Mexicano
                 6-GBP - Libras Esterlina 
                 Escriba "salir"  para concluir la consulta del sistema
                 ***************************************************
                """;

        while (true){
            try {
    //          Interacion con el cliente
                System.out.println(menu);
                System.out.println("Desde que moneda desea hacer la conversion? Escriba la abreviatura");
                moneda = lectura.nextLine().toUpperCase();
                if (moneda.equalsIgnoreCase("salir")){
                    break;
                }
                System.out.println("A que moneda le gustaria convertir? Escriba la abreviatura");
                extrangera = lectura.nextLine().toUpperCase();
                if ((extrangera.equalsIgnoreCase("salir"))) {
                    break;
                }

                System.out.println("Que cantidad desea convertir?");

                valor = lectura.nextDouble();

                String direccion = "https://v6.exchangerate-api.com/v6/d02194f734da73cd6d0979c6/pair/" + moneda + "/" + extrangera;


                //          Consultando api y recibiendo datos
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(direccion))
                        .build();
                HttpResponse<String> response = client
                        .send(request, HttpResponse.BodyHandlers.ofString());
                String json = response.body();

                Gson gson = new Gson();
                gson.fromJson(json, Divisas.class);

                Divisas resultado = gson.fromJson(json, Divisas.class);
                convertido = (resultado.getConversion_rate() * valor);

                System.out.println(valor+ " " +resultado.getbase_code()+ " equivalen a "  +convertido + " " + resultado.getTarget_code());


               System.out.println("Finalizo la ejecucion de programa ");
                System.out.println("Gracias por utilizar nuestros servicios. Feliz resto del dia ");
            }catch (Throwable  e) {
                System.out.println("ERROR! Favor suministrar datos de manera correcta ");
                System.out.println(e.getMessage());
            }
        }
        System.out.println("Gracias por utilizar nuestros servicios. Feliz resto del dia ");
    }

}








