package uk.gov.dvsa.model.cvs.certificateData;

public class Summary extends ResultsItem {
    public static final String EU_NUMBER_SUMMARY_HEADER = "7";


    public Summary(String title) {
        super(title, EU_NUMBER_SUMMARY_HEADER);
    }

    @Override
    public boolean isPresent() {
        return getTitle() != null;
    }
}
