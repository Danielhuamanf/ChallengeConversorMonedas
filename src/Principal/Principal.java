package Principal;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import Modelos.*;

public class Principal {
    public static void main(String[] args) {
        ExchangeRateClient client = new ExchangeRateClient();
        Map<String, Object> exchangeRates;
        try {
            exchangeRates = client.getExchangeRates();
            Map<String, Double> rates = (Map<String, Double>) exchangeRates.get("conversion_rates");
            CurrencyConverter converter = new CurrencyConverter(rates);

            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("Seleccione una opción:");
                System.out.println("1) Dólar =>> Sol peruano");
                System.out.println("2) Sol peruano =>> Dólar");
                System.out.println("3) Dólar =>> Real brasileño");
                System.out.println("4) Real brasileño =>> Dólar");
                System.out.println("7) Salir");

                int option = scanner.nextInt();

                if (option == 7) {
                    break;
                }

                System.out.print("Ingrese la cantidad: ");
                double amount = scanner.nextDouble();
                double result = 0;

                switch (option) {
                    case 1:
                        result = converter.convert("USD", "PEN", amount);
                        System.out.println(amount + " Dólar(es) son " + result + " Sol(es) peruano(s)");
                        break;
                    case 2:
                        result = converter.convert("PEN", "USD", amount);
                        System.out.println(amount + " Sol(es) peruano(s) son " + result + " Dólar(es)");
                        break;
                    case 3:
                        result = converter.convert("USD", "BRL", amount);
                        System.out.println(amount + " Dólar(es) son " + result + " Real(es) brasileño(s)");
                        break;
                    case 4:
                        result = converter.convert("BRL", "USD", amount);
                        System.out.println(amount + " Real(es) brasileño(s) son " + result + " Dólar(es)");
                        break;
                    default:
                        System.out.println("Opción no válida");
                }
            }
            scanner.close();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}

