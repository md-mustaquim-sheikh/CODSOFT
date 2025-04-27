import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class CurrencyConverter {

    private static final String API_KEY = "491260ba063d6514eaaef34a";
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n===================================");
        System.out.println("...Welcome to Currency Converter...");
        System.out.println("===================================\n");
        
        System.out.print("Enter base currency (e.g., USD, EUR, INR): ");
        String baseCurrency = scanner.nextLine().toUpperCase().trim();
        
        System.out.print("Enter target currency (e.g., USD, EUR, INR): ");
        String targetCurrency = scanner.nextLine().toUpperCase().trim();
        
        System.out.print("Enter amount to convert: ");
        double amount = scanner.nextDouble();

        try {

            double exchangeRate = getExchangeRate(baseCurrency, targetCurrency);
            double convertedAmount = amount * exchangeRate;
            
            System.out.println("\n----------------------------------");
            System.out.printf(" %-9s: %12.2f %-3s %n", "Original", amount, baseCurrency);
            System.out.printf(" %-9s: %12.2f %-3s %n", "Converted", convertedAmount, targetCurrency);
            System.out.printf(" %-9s: %12.6f      %n", "Rate", exchangeRate);
            System.out.println("----------------------------------\n");
            
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    private static double getExchangeRate(String baseCurrency, String targetCurrency) throws IOException {
        String urlStr = BASE_URL + API_KEY + "/pair/" + baseCurrency + "/" + targetCurrency;
        
        URL url = new URL(urlStr);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        
        int responseCode = connection.getResponseCode();
        if (responseCode != 200) {
            throw new IOException("Failed to get exchange rate. Server returned: " + responseCode);
        }
        
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            StringBuilder response = new StringBuilder();
            String line;
            
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            
            String responseStr = response.toString();
            String rateKey = "\"conversion_rate\":";
            int rateIndex = responseStr.indexOf(rateKey);
            
            if (rateIndex == -1) {
                throw new IOException("Invalid API response format");
            }
            
            int start = rateIndex + rateKey.length();
            int end = responseStr.indexOf(",", start);
            if (end == -1) {
                end = responseStr.indexOf("}", start);
            }
            
            String rateStr = responseStr.substring(start, end).trim();
            return Double.parseDouble(rateStr);
        }
    }
}