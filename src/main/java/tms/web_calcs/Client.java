package tms.web_calcs;

import com.google.gson.Gson;
import tms.web_calcs.enums.TemperatureUnitMeasure;
import tms.web_calcs.models.ConvertRequest;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

/**
 * Author simonpirko
 * Created on 7.11.24
 */

public class Client {

    private static Scanner input(String message){
        System.out.println(message);
        return new Scanner(System.in);
    }


    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
        temperatureRequest();
        //calcRequest();
    }

    private static void temperatureRequest() throws IOException, InterruptedException, URISyntaxException {
        Double value = input("Enter value: ").nextDouble();
        char from = input("Enter current temperature measure (K,C,F): ").next().charAt(0);
        char to = input("Enter target temperature measure (K,C,F): ").next().charAt(0);
        Gson gson = new Gson();
        ConvertRequest model = new ConvertRequest(value,
                TemperatureUnitMeasure.getTemperatureUnitMeasure(from),
                TemperatureUnitMeasure.getTemperatureUnitMeasure(to));

        String json  = gson.toJson(model);

        HttpRequest request = HttpRequest.newBuilder().uri(new URI("http://localhost:8080/convert"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json)).build();

        HttpClient client = HttpClient.newBuilder().build();
        HttpResponse<String> send = client.send(request, HttpResponse.BodyHandlers.ofString());
        String result = send.body();
        System.out.println(result);
    }

    private static void calcRequest() throws URISyntaxException, IOException, InterruptedException {
        String expression = input("Enter your expression(f.e 20+30+50): ").next();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:8080/calc?expression=%s".formatted(expression))).GET().build();

        HttpClient client = HttpClient.newBuilder().build();
        HttpResponse<String> send = client.send(request, HttpResponse.BodyHandlers.ofString());
        String result = send.body();
        System.out.println(result);
    }
}
