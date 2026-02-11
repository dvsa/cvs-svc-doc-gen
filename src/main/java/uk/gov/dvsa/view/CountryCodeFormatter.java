package uk.gov.dvsa.view;

public class CountryCodeFormatter {

    public String format(String countryCode) {
        if (countryCode == null) {
            return null;

        }

        switch (countryCode) {
            case "XNEU":
                return "Non EU";
            case "XUKN":
                return "Not known";
            case "XNA":
                return"Not Applicable";
            default:
                return countryCode.toUpperCase();
        }
    }
}
