package uk.gov.dvsa.model.cvs.certificateData;

import com.fasterxml.jackson.annotation.JsonProperty;
import uk.gov.dvsa.model.mot.certificateData.FormattedOdometerReading;
import uk.gov.dvsa.model.mot.results.DefectsList;
import uk.gov.dvsa.model.mot.results.Summary;
import uk.gov.dvsa.view.cvs.CvsOdometerReadingFormatter;
import uk.gov.dvsa.view.mot.CountryCodeFormatter;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CvsMotCertificateData  {

    public static final String EU_NUMBER_FOR_DEFECTS = "6";

    private static final String ADVISORIES_HEADER = "Monitor and repair if necessary (advisories)";
    private static final String MINOR_DEFECTS_HEADER = "Repair as soon as possible (minor defects)";
    public static final String PASS_SUMMARY_HEADER = "Pass";
    public static final String PASS_WITH_DEFECTS_HEADER = "Pass with defects";
    public static final String TESTING_ORGANISATION = "DRIVER AND VEHICLE STANDARDS AGENCY";
    public static final CvsOdometerReadingFormatter ODOMETER_FORMATTER =  new CvsOdometerReadingFormatter();
    private static final CountryCodeFormatter COUNTRY_CODE_FORMATTER = new CountryCodeFormatter();

    @JsonProperty("SeatBeltTested")
    private String seatBeltTested;

    @JsonProperty("SeatBeltPreviousCheckDate")
    private String seatBeltPreviousCheckDate;

    @JsonProperty("SeatBeltNumber")
    private String seatBeltNumber;

    @JsonProperty("TestNumber")
    private String testNumber;

    @JsonProperty("TestStationPNumber")
    private String testStationPNumber;

    @JsonProperty("TestStationName")
    private String testStationName;

    @JsonProperty("IssuersName")
    private String issuersName;

    @JsonProperty("RawVRM")
    private String rawVrm;

    @JsonProperty("RawVIN")
    private String rawVin;

    @JsonProperty("Make")
    private String make;

    @JsonProperty("Model")
    private String model;

    @JsonProperty("CountryOfRegistrationCode")
    private String countryOfRegistrationCode;

    @JsonProperty("VehicleEuClassification")
    private String vehicleEuClassification;

    @JsonProperty("CurrentOdometer")
    private CvsOdometerReading currentOdometer;

    @JsonProperty("OdometerUnit")
    private String odometerUnit;

    @JsonProperty("ExpiryDate")
    private String expiryDate;

    @JsonProperty("DateOfTheTest")
    private String dateOfTheTest;

    @JsonProperty("AdvisoryDefects")
    private List<String> advisoryDefects;

    @JsonProperty("AdvisoryDefectsHeader")
    private String advisoryDefectsHeader;

    @JsonProperty("OdometerHistoryList")
    private List<CvsOdometerReading> odometerHistoryList;

    @JsonProperty("MinorDefectsHeader")
    private String minorDefectsHeader;

    @JsonProperty("EarliestDateOfTheNextTest")
    private String earliestDateOfTheNextTest;

    @JsonProperty("MinorDefects")
    private List<String> minorDefects;

    @JsonProperty("Trn")
    private  String trn;

    @JsonProperty("IsTrailer")
    private boolean isTrailer;

    @JsonProperty("Recalls")
    private Recalls recalls;

    public String getSeatBeltTested() {
        return seatBeltTested;
    }

    public CvsMotCertificateData setSeatBeltTested(String seatBeltTested) {
        this.seatBeltTested = seatBeltTested;
        return this;
    }

    public String getSeatBeltPreviousCheckDate() {
        return seatBeltPreviousCheckDate;
    }

    public CvsMotCertificateData setSeatBeltPreviousCheckDate(String seatBeltPreviousCheckDate) {
        this.seatBeltPreviousCheckDate = seatBeltPreviousCheckDate;
        return this;
    }

    public String getSeatBeltNumber() {
        return seatBeltNumber;
    }

    public CvsMotCertificateData setSeatBeltNumber(String seatBeltNumber) {
        this.seatBeltNumber = seatBeltNumber;
        return this;
    }

    public String getTestNumber() {
        return testNumber;
    }

    public CvsMotCertificateData setTestNumber(String testNumber) {
        this.testNumber = testNumber;
        return this;
    }

    public String getTestStationName() {
        return testStationName;
    }

    public CvsMotCertificateData setTestStationName(String testStationName) {
        this.testStationName = testStationName;
        return this;
    }

    public String getTestStationPNumber() {
        return testStationPNumber;
    }

    public CvsMotCertificateData setTestStationPNumber(String testStationPNumber) {
        this.testStationPNumber = testStationPNumber;
        return this;
    }

    public String getTestingOrganisation() {
        return TESTING_ORGANISATION;
    }

    public CvsOdometerReading getCurrentOdometer() {
        return currentOdometer;
    }

    public String getFormattedCurrentOdometer() {
        return ODOMETER_FORMATTER.formatValue(currentOdometer);
    }

    public CvsMotCertificateData setCurrentOdometer(CvsOdometerReading currentOdometer) {
        this.currentOdometer = currentOdometer;
        return this;
    }

    public String getIssuersName() {
        return issuersName;
    }

    public CvsMotCertificateData setIssuersName(String issuersName) {
        this.issuersName = issuersName;
        return this;
    }

    public String getRawVrm() {
        return rawVrm;
    }

    public CvsMotCertificateData setRawVrm(String rawVrm) {
        this.rawVrm = rawVrm;
        return this;
    }

    public String getRawVin() {
        return rawVin;
    }

    public CvsMotCertificateData setRawVin(String rawVin) {
        this.rawVin = rawVin;
        return this;
    }

    public String getMake() {
        return make;
    }

    public CvsMotCertificateData setMake(String make) {
        this.make = make;
        return this;
    }

    public String getModel() {
        return model;
    }

    public CvsMotCertificateData setModel(String model) {
        this.model = model;
        return this;
    }

    public CvsMotCertificateData setAdvisoryDefects(List<String> advisoryDefects) {
        this.advisoryDefects = advisoryDefects;
        return this;
    }

    public List<String> getAdvisoryDefects() {
        return this.advisoryDefects;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public CvsMotCertificateData setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
        return this;
    }

    public String getLocationOfTheTest() {

        if (null != testStationPNumber && !testStationPNumber.isEmpty()) {
            return testStationPNumber.trim() + ", "  + testStationName.trim();
        }
        return testStationName.trim();
    }

    public String getAdvisoryDefectsHeader() {
        return advisoryDefectsHeader;
    }

    public CvsMotCertificateData setAdvisoryDefectsHeader(String advisoryDefectsHeader) {
        this.advisoryDefectsHeader = advisoryDefectsHeader;
        return this;
    }

    public String getOdometerUnit() {
        return odometerUnit;
    }

    public void setOdometerUnit(String odometerUnit) {
        this.odometerUnit = odometerUnit;
    }

    public String getDateOfTheTest() {
        return dateOfTheTest;
    }

    public CvsMotCertificateData setDateOfTheTest(String dateOfTheTest) {
        this.dateOfTheTest = dateOfTheTest;
        return this;
    }

    public String getCountryOfRegistrationCode() {
        return countryOfRegistrationCode;
    }

    public String getCountryOfRegistrationCodeFormatted() {
        return COUNTRY_CODE_FORMATTER.format(countryOfRegistrationCode);
    }

    public CvsMotCertificateData setCountryOfRegistrationCode(String countryOfRegistrationCode) {
        this.countryOfRegistrationCode = countryOfRegistrationCode;
        return this;
    }

    public String getVehicleEuClassification() {
        return vehicleEuClassification;
    }

    public CvsMotCertificateData setVehicleEuClassification(String vehicleEuClassification) {
        this.vehicleEuClassification = vehicleEuClassification;
        return this;
    }

    public List<CvsOdometerReading> getOdometerHistoryList() {
        return odometerHistoryList;
    }

    public List<FormattedOdometerReading> getMileageHistory() {
        if (null != odometerHistoryList) {
            return odometerHistoryList.stream()
                    .map(
                            entry -> new FormattedOdometerReading(
                                    ODOMETER_FORMATTER.formatValue(entry),
                                    entry.getDate()
                            )
                    )
                    .collect(Collectors.toList());
        } else {
            return Collections.emptyList();
        }
    }

    public CvsMotCertificateData setOdometerHistoryList(List<CvsOdometerReading> odometerHistoryList) {
        this.odometerHistoryList = odometerHistoryList;
        return this;
    }

    public CvsMotCertificateData setMinorDefects(List<String> minorDefects) {
        this.minorDefects = minorDefects;
        return this;
    }

    public List<String> getMinorDefects() {
        return this.minorDefects;
    }

    public String getMinorDefectsHeader() {
        return minorDefectsHeader;
    }

    public CvsMotCertificateData setMinorDefectsHeader(String minorDefectsHeader) {
        this.minorDefectsHeader = minorDefectsHeader;
        return this;
    }

    public String getEarliestDateOfTheNextTest() {
        return earliestDateOfTheNextTest;
    }

    public CvsMotCertificateData setEarliestDateOfTheNextTest(String earliestDateOfTheNextTest) {
        this.earliestDateOfTheNextTest = earliestDateOfTheNextTest;
        return this;
    }

    public DefectsList getAdvisory() {
        return new DefectsList(ADVISORIES_HEADER, this.advisoryDefects);
    }

    public DefectsList getMinor() {
        return new DefectsList(MINOR_DEFECTS_HEADER, this.minorDefects, EU_NUMBER_FOR_DEFECTS);
    }

    public Summary getSummary() {
        return new Summary(buildSummaryTitle());
    }

    private String buildSummaryTitle() {
        if (!hasMinorDefects(getMinorDefects())) {
            return PASS_SUMMARY_HEADER;
        } else {
            return PASS_WITH_DEFECTS_HEADER;
        }
    }

    public boolean hasMinorDefects(List<String> minorDefects) {
        return minorDefects != null && !minorDefects.isEmpty();
    }

    public  String getTrn() { return  trn; }

    public CvsMotCertificateData setTrn(String trnValue) {
        this.trn = trnValue;
        return  this;
    }

    public boolean getIsTrailer(){
        return this.isTrailer;
    }

    public CvsMotCertificateData setIsTrailer(boolean isTrailerValue) {
        this.isTrailer = isTrailerValue;
        return this;
    }

    public Recalls getRecalls() {return recalls;}

    public CvsMotCertificateData setRecalls(Recalls recalls) {
        this.recalls = recalls;
        return this;
    }
}
