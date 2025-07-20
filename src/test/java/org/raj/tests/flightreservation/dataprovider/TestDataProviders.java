package org.raj.tests.flightreservation.dataprovider;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.raj.tests.util.GoogleSheetReader;
import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TestDataProviders {

    private static final String SPREADSHEET_ID = "YOUR_SPREADSHEET_ID_HERE";
    private static final String SHEET_RANGE = "Sheet1!A1:D"; // Adjust as per your sheet

    @DataProvider(name = "googleSheetJsonData")
    public static Iterator<Object[]> getGoogleSheetJsonData() {
        List<Object[]> testData = new ArrayList<>();
        try {
            String jsonData = GoogleSheetReader.getSheetDataAsJson(SPREADSHEET_ID, SHEET_RANGE);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(jsonData);

            if (rootNode.isArray()) {
                for (JsonNode node : rootNode) {
                    // You can pass the entire JSON node as a single object to your test method
                    // Or, you can parse specific fields if your test method expects separate parameters
                    testData.add(new Object[]{node.toString()}); // Pass JSON string
                }
            }
        } catch (IOException | GeneralSecurityException e) {
            System.out.println(e.getMessage());
        }
        return testData.iterator();
    }

    // Example of a data provider that can parse specific fields from JSON
    @DataProvider(name = "loginData")
    public static Iterator<Object[]> getLoginData() {
        List<Object[]> testData = new ArrayList<>();
        try {
            String jsonData = GoogleSheetReader.getSheetDataAsJson(SPREADSHEET_ID, "Sheet1!A1:C"); // Assuming columns like Username, Password, ExpectedResult
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(jsonData);

            if (rootNode.isArray()) {
                for (JsonNode node : rootNode) {
                    String firstname = node.has("firstName") ? node.get("firstName").asText() : "";
                    String lastname = node.has("lastName") ? node.get("lastName").asText() : "";
                    String email = node.has("email") ? node.get("email").asText() : "";
                    String password  = node.has("password") ? node.get("password").asText() : "";
                    String street  = node.has("street") ? node.get("street").asText() : "";
                    String city  = node.has("city") ? node.get("city").asText() : "";
                    String zip  = node.has("zip") ? node.get("zip").asText() : "";
                    String passengersCount  = node.has("passengersCount") ? node.get("passengersCount").asText() : "";
                    String expectedPrice  = node.has("expectedPrice") ? node.get("expectedPrice").asText() : "";
                    testData.add(new Object[]{firstname, lastname, email , password, street , city, zip, passengersCount, expectedPrice});
                }
            }
        } catch (IOException | GeneralSecurityException e) {
            System.out.println(e.getMessage());
        }
        return testData.iterator();
    }
}
