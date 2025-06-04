package uk.gov.dvsa.model.cvs.certificateData;

public abstract class ResultsItem {

    /**
     *  Displayed together with the title, may contain letters, optional
     */
    private String euCompliantNumber;

    /**
     *  Header describing a list of reasons for rejection that are displayed below it
     */
    private String title;

    public String getEuCompliantNumber() {
        return euCompliantNumber;
    }

    public void setEuCompliantNumber(String euCompliantNumber) {
        this.euCompliantNumber = euCompliantNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ResultsItem(String title, String euCompliantNumber) {
        this.euCompliantNumber = euCompliantNumber;
        this.title = title;
    }

    public abstract boolean isPresent();
}
