package org.raj.tests.vendorportal.model;

public record VendorPortalTestData(String username,
                                   String password,
                                   String monthlyEarning,
                                   String annualEarning,
                                   String profitMargin,
                                   String availableMargin,
                                   String searchKeyword,
                                   int searchResultCount) {
}
