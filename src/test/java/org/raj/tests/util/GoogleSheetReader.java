package org.raj.tests.util;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class GoogleSheetReader {

    private static final String APPLICATION_NAME = "Google Sheets Automation Data";
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final String TOKENS_DIRECTORY_PATH = "tokens"; // Directory to store user credentials

    /**
     * Global instance of the scopes required by this quickstart.
     * If there are no longer needed permissions, delete them.
     */
    private static final List<String> SCOPES = Collections.singletonList(SheetsScopes.SPREADSHEETS_READONLY);
    private static final String CREDENTIALS_FILE_PATH = "/credentials.json"; // Path to your downloaded credentials.json

    /**
     * Creates an authorized HttpTransport object.
     * @return An authorized HttpTransport object.
     * @throws IOException If the credentials file cannot be found.
     * @throws GeneralSecurityException If there is a security issue with the transport.
     */
    private static Credential authorize() throws IOException, GeneralSecurityException {
        // Load client secrets.
        InputStreamReader in = new InputStreamReader(Objects.requireNonNull(GoogleSheetReader.class.getResourceAsStream(CREDENTIALS_FILE_PATH)));
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, in);

        // Build flow and trigger user authorization request.
        NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        return new AuthorizationCodeInstalledApp(
                new com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow.Builder(
                        HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                        .setDataStoreFactory(new FileDataStoreFactory(new File(TOKENS_DIRECTORY_PATH)))
                        .setAccessType("offline")
                        .build(),
                new LocalServerReceiver())
                .authorize("rajabhishek.shaw90@gmail.com");
    }

    /**
     * Returns an authorized Sheets API service object.
     * @return An authorized Sheets API service object.
     * @throws IOException If the credentials file cannot be found.
     * @throws GeneralSecurityException If there is a security issue with the transport.
     */
    public static Sheets getSheetsService() throws IOException, GeneralSecurityException {
        NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        Credential credential = authorize();
        return new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

    /**
     * Reads data from a Google Sheet and converts it into a JSON array of objects.
     * Each object represents a row, with keys derived from the header row.
     *
     * @param spreadsheetId The ID of the Google Spreadsheet.
     * @param range The A1 notation or R1C1 notation of the range to retrieve values from (e.g., "Sheet1!A1:D").
     * @return A String representation of the JSON array.
     * @throws IOException If there's an issue reading from the Google Sheet.
     * @throws GeneralSecurityException If there's an issue with Google API authentication.
     */
    public static String getSheetDataAsJson(String spreadsheetId, String range)
            throws IOException, GeneralSecurityException {

        Sheets service = getSheetsService();
        ValueRange response = service.spreadsheets().values()
                .get(spreadsheetId, range)
                .execute();

        List<List<Object>> values = response.getValues();

        if (values == null || values.isEmpty()) {
            System.out.println("No data found in the specified range.");
            return "[]";
        }

        // Assume the first row is the header
        List<Object> header = values.get(0);
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode jsonArray = mapper.createArrayNode();

        for (int i = 1; i < values.size(); i++) { // Start from second row for data
            List<Object> row = values.get(i);
            ObjectNode jsonObject = mapper.createObjectNode();

            for (int j = 0; j < header.size(); j++) {
                String key = header.get(j).toString();
                String value = (j < row.size() && row.get(j) != null) ? row.get(j).toString() : "";
                jsonObject.put(key, value);
            }
            jsonArray.add(jsonObject);
        }
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonArray);
    }

    public static void main(String[] args) {
        // Example Usage (replace with your actual Spreadsheet ID and Range)
        String spreadsheetId = "13VKAa1y1lpPmw6jEMbYNdoZG0erd9Sdkm4_lqU3W1wM";
        String range = "Sheet1!A1:H"; // Adjust the range based on your sheet and columns

        try {
            String jsonData = getSheetDataAsJson(spreadsheetId, range);
            System.out.println(jsonData);
        } catch (IOException | GeneralSecurityException e) {
            e.printStackTrace();
        }
    }
}