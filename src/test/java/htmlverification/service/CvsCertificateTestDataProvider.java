package htmlverification.service;

import htmlverification.framework.component.DefectSummaryComponent;
import uk.gov.dvsa.model.cvs.VTG30;
import uk.gov.dvsa.model.cvs.VTP20;
import uk.gov.dvsa.model.cvs.VTP30;
import uk.gov.dvsa.model.cvs.VTG5;
import uk.gov.dvsa.model.cvs.VTG5A;
import uk.gov.dvsa.model.cvs.certificateData.CvsMotCertificateData;
import uk.gov.dvsa.model.cvs.certificateData.CvsMotFailCertificateData;
import uk.gov.dvsa.model.cvs.certificateData.CvsOdometerReading;
import uk.gov.dvsa.model.cvs.certificateData.Signature;
import uk.gov.dvsa.model.mot.enums.CertificateTypes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CvsCertificateTestDataProvider {
    public static final String VIN = "QQIDAAAAAAA058568";
    public static final String INVALID_XML_RFR_TEXT = "Invalid XML character\f RFR";
    public static final String ADVISORY_RFR_TEXT = "Advisory RFR";
    public static final String MINOR_RFR_TEXT = "Minor RFR";
    public static final String DANGEROUS_RFR_TEXT = "Dangerous RFR";
    public static final String MAJOR_RFR_TEXT = "Major RFR";
    public static final String PRS_RFR_TEXT = "PRS RFR";

    public static final String ODOMETER_VALUE = "22,341";

    public static VTP20 getVtp20HavingInvalidXMLCharacter() {
        VTP20 document = getVtp20();

        CvsMotCertificateData data = document.getData();
        data.setAdvisoryDefects(generateRFRs(INVALID_XML_RFR_TEXT, 3));

        return document;
    }

    public static VTP20 getVtp20() {
        VTP20 vtp20 = new VTP20();
        vtp20.setDocumentName(CertificateTypes.CVS_PASS.getType());
        CvsMotCertificateData vtp20Data = new CvsMotCertificateData();
        vtp20Data
                .setMinorDefectsHeader(DefectSummaryComponent.MINOR_DEFECTS_HEADER_TEXT)
                .setMinorDefects(generateRFRs(MINOR_RFR_TEXT, 1))

                .setAdvisoryDefectsHeader(DefectSummaryComponent.ADVISORIES_HEADER_TEXT)
                .setAdvisoryDefects(generateRFRs(ADVISORY_RFR_TEXT, 1))

                .setSeatBeltNumber("10")
                .setSeatBeltPreviousCheckDate("12.11.2018")
                .setSeatBeltTested("Yes")

                .setCountryOfRegistrationCode("GB")
                .setRawVin(VIN)
                .setDateOfTheTest("12.11.2018")
                .setExpiryDate("12.10.2018")
                .setTestStationPNumber("P12345")
                .setTestStationName("TEST STATION NAME")
                .setMake("Aston Martin")
                .setModel("DB11")
                .setVehicleEuClassification("M1")
                .setRawVrm("KA1SAPH")
                .setCurrentOdometer(
                    new CvsOdometerReading("22341", "mi", "01.02.2019")
                )
                .setOdometerHistoryList(Arrays.asList(
                    new CvsOdometerReading("120", "km", "01.02.2016"),
                    new CvsOdometerReading("330", "km", "30.01.2017")
                ))
                .setIssuersName("R.DREWNO")
                .setTestStationName("POPULAR GARAGES")
                .setTestNumber("1806 8140 0628")
                .setEarliestDateOfTheNextTest("13.10.2018");

        vtp20.setData(vtp20Data);

        Signature signature = new Signature();
        signature
                .setImageData("iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAYAAAAfFcSJAAAADUlEQVR42mNkYPhfDwAChwGA60e6kgAAAABJRU5ErkJggg==")
                .setImageType("png");

        vtp20.setSignature(signature);

        return vtp20;
    }

    public static VTP30 getVtp30() {
        VTP30 vtp30 = new VTP30();
        vtp30.setDocumentName(CertificateTypes.CVS_FAIL.getType());
        CvsMotFailCertificateData vtp30Data = new CvsMotFailCertificateData();

        vtp30Data
                .setMinorDefectsHeader(DefectSummaryComponent.MINOR_DEFECTS_HEADER_TEXT)
                .setMinorDefects(generateRFRs(MINOR_RFR_TEXT, 1))

                .setAdvisoryDefectsHeader(DefectSummaryComponent.ADVISORIES_HEADER_TEXT)
                .setAdvisoryDefects(generateRFRs(ADVISORY_RFR_TEXT, 1))

                .setSeatBeltNumber("10")
                .setSeatBeltPreviousCheckDate("12.11.2018")
                .setSeatBeltTested("Yes")

                .setCountryOfRegistrationCode("GB")
                .setRawVin(VIN)
                .setDateOfTheTest("12.11.2018")
                .setExpiryDate("12.10.2018")
                .setTestStationPNumber("P12345")
                .setTestStationName("TEST STATION NAME")
                .setMake("Aston Martin")
                .setModel("DB11")
                .setVehicleEuClassification("M1")
                .setRawVrm("KA1SAPH")
                .setCurrentOdometer(
                        new CvsOdometerReading("22341", "mi", "01.02.2019")
                )
                .setOdometerHistoryList(Arrays.asList(
                        new CvsOdometerReading("120", "km", "01.02.2016"),
                        new CvsOdometerReading("330", "km", "30.01.2017")
                ))
                .setIssuersName("R.DREWNO")
                .setTestStationName("POPULAR GARAGES")
                .setTestNumber("1806 8140 0628")
                .setEarliestDateOfTheNextTest("13.10.2018");

        vtp30Data
                .setMajorDefectsHeader(DefectSummaryComponent.MAJOR_DEFECTS_HEADER_TEXT)
                .setMajorDefects(generateRFRs(MAJOR_RFR_TEXT, 1))

                .setDangerousDefectsHeader(DefectSummaryComponent.DANGEROUS_DEFECTS_HEADER_TEXT)
                .setDangerousDefects(generateRFRs(DANGEROUS_RFR_TEXT, 1))

                .setPrsDefects(generateRFRs(PRS_RFR_TEXT, 1));

        vtp30.setFailData(vtp30Data);

        Signature signature = new Signature();
        signature
                .setImageData("iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAYAAAAfFcSJAAAADUlEQVR42mNkYPhfDwAChwGA60e6kgAAAABJRU5ErkJggg==")
                .setImageType("png");

        vtp30.setSignature(signature);

        return vtp30;
    }

    public static VTG5 getVtg5() {
        VTG5 vtg5 = new VTG5();
        vtg5.setDocumentName(CertificateTypes.CVS_HGV_PASS.getType());
        CvsMotCertificateData vtg5Data = new CvsMotCertificateData();
        vtg5Data
                .setMinorDefectsHeader(DefectSummaryComponent.MINOR_DEFECTS_HEADER_TEXT)
                .setMinorDefects(generateRFRs(MINOR_RFR_TEXT, 1))

                .setAdvisoryDefectsHeader(DefectSummaryComponent.ADVISORIES_HEADER_TEXT)
                .setAdvisoryDefects(generateRFRs(ADVISORY_RFR_TEXT, 1))

                .setSeatBeltNumber("10")
                .setSeatBeltPreviousCheckDate("12.11.2018")
                .setSeatBeltTested("Yes")

                .setCountryOfRegistrationCode("GB")
                .setRawVin(VIN)
                .setDateOfTheTest("12.11.2018")
                .setExpiryDate("12.10.2018")
                .setTestStationPNumber("P12345")
                .setTestStationName("TEST STATION NAME")
                .setMake("Aston Martin")
                .setModel("DB11")
                .setVehicleEuClassification("M1")
                .setRawVrm("KA1SAPH")
                .setCurrentOdometer(
                        new CvsOdometerReading("22341", "mi", "01.02.2019")
                )
                .setOdometerHistoryList(Arrays.asList(
                        new CvsOdometerReading("120", "km", "01.02.2016"),
                        new CvsOdometerReading("330", "km", "30.01.2017")
                ))
                .setIssuersName("R.DREWNO")
                .setTestStationName("POPULAR GARAGES")
                .setTestNumber("1806 8140 0628")
                .setEarliestDateOfTheNextTest("13.10.2018");

        vtg5.setData(vtg5Data);

        Signature signature = new Signature();
        signature
                .setImageData("iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAYAAAAfFcSJAAAADUlEQVR42mNkYPhfDwAChwGA60e6kgAAAABJRU5ErkJggg==")
                .setImageType("png");

        vtg5.setSignature(signature);

        return vtg5;
    }

    public static VTG5A getVtg5a() {
        VTG5A vtg5a = new VTG5A();
        vtg5a.setDocumentName(CertificateTypes.CVS_TRL_PASS.getType());
        CvsMotCertificateData vtg5aData = new CvsMotCertificateData();
        vtg5aData
                .setMinorDefectsHeader(DefectSummaryComponent.MINOR_DEFECTS_HEADER_TEXT)
                .setMinorDefects(generateRFRs(MINOR_RFR_TEXT, 1))

                .setAdvisoryDefectsHeader(DefectSummaryComponent.ADVISORIES_HEADER_TEXT)
                .setAdvisoryDefects(generateRFRs(ADVISORY_RFR_TEXT, 1))
                .setCountryOfRegistrationCode("GB")
                .setRawVin(VIN)
                .setDateOfTheTest("12.11.2018")
                .setExpiryDate("12.10.2018")
                .setTestStationPNumber("P12345")
                .setTestStationName("TEST STATION NAME")
                .setMake("Aston Martin")
                .setModel("DB11")
                .setIssuersName("R.DREWNO")
                .setTestStationName("POPULAR GARAGES")
                .setTestNumber("1806 8140 0628")
                .setEarliestDateOfTheNextTest("13.10.2018")
                .setTrn("ABC1234");

        vtg5a.setData(vtg5aData);

        Signature signature = new Signature();
        signature
                .setImageData("iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAYAAAAfFcSJAAAADUlEQVR42mNkYPhfDwAChwGA60e6kgAAAABJRU5ErkJggg==")
                .setImageType("png");

        vtg5a.setSignature(signature);

        return vtg5a;
    }


    public static VTG5 getVtg5HavingInvalidXMLCharacter() {
        VTG5 document = getVtg5();

        CvsMotCertificateData data = document.getData();
        data.setAdvisoryDefects(generateRFRs(INVALID_XML_RFR_TEXT, 3));

        return document;
    }

    public static VTG30 getVtg30() {
        VTG30 vtg30 = new VTG30();
        vtg30.setDocumentName(CertificateTypes.CVS_HGV_TRL_FAIL.getType());
        CvsMotFailCertificateData vtg30Data = new CvsMotFailCertificateData();

        vtg30Data
                .setMinorDefectsHeader(DefectSummaryComponent.MINOR_DEFECTS_HEADER_TEXT)
                .setMinorDefects(generateRFRs(MINOR_RFR_TEXT, 1))

                .setAdvisoryDefectsHeader(DefectSummaryComponent.ADVISORIES_HEADER_TEXT)
                .setAdvisoryDefects(generateRFRs(ADVISORY_RFR_TEXT, 1))

                .setSeatBeltNumber("10")
                .setSeatBeltPreviousCheckDate("12.11.2018")
                .setSeatBeltTested("Yes")

                .setCountryOfRegistrationCode("GB")
                .setRawVin(VIN)
                .setDateOfTheTest("12.11.2018")
                .setExpiryDate("12.10.2018")
                .setTestStationPNumber("P12345")
                .setTestStationName("TEST STATION NAME")
                .setMake("Aston Martin")
                .setModel("DB11")
                .setVehicleEuClassification("M1")
                .setRawVrm("KA1SAPH")
                .setCurrentOdometer(
                        new CvsOdometerReading("22341", "mi", "01.02.2019")
                )
                .setOdometerHistoryList(Arrays.asList(
                        new CvsOdometerReading("120", "km", "01.02.2016"),
                        new CvsOdometerReading("330", "km", "30.01.2017")
                ))
                .setIssuersName("R.DREWNO")
                .setTestStationName("POPULAR GARAGES")
                .setTestNumber("1806 8140 0628")
                .setEarliestDateOfTheNextTest("13.10.2018")
                .setTrn("ABC1234");

        vtg30Data
                .setMajorDefectsHeader(DefectSummaryComponent.MAJOR_DEFECTS_HEADER_TEXT)
                .setMajorDefects(generateRFRs(MAJOR_RFR_TEXT, 1))

                .setDangerousDefectsHeader(DefectSummaryComponent.DANGEROUS_DEFECTS_HEADER_TEXT)
                .setDangerousDefects(generateRFRs(DANGEROUS_RFR_TEXT, 1))

                .setPrsDefects(generateRFRs(PRS_RFR_TEXT, 1))
                .setIsTrailer(true);

        vtg30.setFailData(vtg30Data);

        Signature signature = new Signature();
        signature
                .setImageData("iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAYAAAAfFcSJAAAADUlEQVR42mNkYPhfDwAChwGA60e6kgAAAABJRU5ErkJggg==")
                .setImageType("png");

        vtg30.setSignature(signature);

        return vtg30;
    }

    public static VTG30 getVtg30HavingInvalidXMLCharacter() {
        VTG30 document = getVtg30();

        CvsMotCertificateData data = document.getFailData();
        data.setAdvisoryDefects(generateRFRs(INVALID_XML_RFR_TEXT, 3));

        return document;
    }

    private static List<String> generateRFRs(String rfrName, int numberOfRfrs) {
        List<String> reasonsForRejection = new ArrayList<>();

        for (int i = 0; i < numberOfRfrs; i++) {
            reasonsForRejection.add(rfrName + " #" + i); }
        return reasonsForRejection;
    }
}
