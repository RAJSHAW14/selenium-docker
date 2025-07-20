package org.raj.tests.vendorportal.dataprovider;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.raj.tests.util.GoogleSheetReader;
import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class VendorDataProviders {

    private static final String SPREADSHEET_ID = "13VKAa1y1lpPmw6jEMbYNdoZG0erd9Sdkm4_lqU3W1wM";
    private static final String SHEET_RANGE = "Sheet1!A1:H"; // Adjust as per your sheet

    @DataProvider(name = "googleSheetJsonData")
    public static Iterator<Object[]> getGoogleSheetJsonData() {
        List<Object[]> testData = new ArrayList<>();
        try {
            String jsonData = GoogleSheetReader.getSheetDataAsJson(SPREADSHEET_ID, SHEET_RANGE);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(jsonData);

            if (rootNode.isArray()) {
                for (JsonNode node : rootNode) {
                    testData.add(new Object[]{node.toString()});
                }
            }
        } catch (IOException | GeneralSecurityException e) {
            e.printStackTrace();
        }
        return testData.iterator();
    }
}
