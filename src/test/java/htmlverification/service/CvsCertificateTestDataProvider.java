package htmlverification.service;

import htmlverification.framework.component.DefectSummaryComponent;
import uk.gov.dvsa.model.cvs.*;
import uk.gov.dvsa.model.cvs.certificateData.*;
import uk.gov.dvsa.enums.CertificateTypes;

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

    public static VTP20 getVtp20WithNoOdometerHistory() {
        VTP20 document = getVtp20();

        CvsMotCertificateData data = document.getData();
        data.setOdometerHistoryList(null);

        return document;
    }

    public static VTP20 getReplacementVtp20() {
        VTP20 document = getVtp20();

        document.setReissue(generateReissuer());

        return document;
    }

    public static VTP20 getVtp20() {
        VTP20 vtp20 = new VTP20();
        vtp20.setDocumentName(CertificateTypes.CVS_PASS.getCertificateType());
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

    private static Reissue generateReissuer() {
        Reissue reissue = new Reissue();
        reissue.setIssuer("Joe");
        reissue.setReason("Replacement");
        reissue.setDate("12.1.2022");
        return reissue;
    }

    public static VTP30 getReplacementVtp30() {
        VTP30 document = getVtp30();

        document.setReissue(generateReissuer());

        return document;
    }

    public static VTP30 getVtp30() {
        VTP30 vtp30 = new VTP30();
        vtp30.setDocumentName(CertificateTypes.CVS_FAIL.getCertificateType());
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
        vtg5.setDocumentName(CertificateTypes.CVS_HGV_PASS.getCertificateType());
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
        vtg5.setReissue(generateReissuer());

        return vtg5;
    }

    public static VTG5A getVtg5a() {
        VTG5A vtg5a = new VTG5A();
        vtg5a.setDocumentName(CertificateTypes.CVS_TRL_PASS.getCertificateType());
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
                .setTrn("ABC1234")
                .setIsTrailer(true);

        vtg5a.setData(vtg5aData);

        Signature signature = new Signature();
        signature
                .setImageData("iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAYAAAAfFcSJAAAADUlEQVR42mNkYPhfDwAChwGA60e6kgAAAABJRU5ErkJggg==")
                .setImageType("png");

        vtg5a.setSignature(signature);
        vtg5a.setReissue(generateReissuer());

        return vtg5a;
    }

    public static VTG5W getVTG5W() {
        VTG5W vtg5W = new VTG5W();
        vtg5W.setDocumentName(CertificateTypes.CVS_HGV_PASS_WELSH.getCertificateType());
        CvsMotCertificateDataWelsh vtg5WData = new CvsMotCertificateDataWelsh();
        vtg5WData
                .setMinorDefectsWelsh(generateRFRs(MINOR_RFR_TEXT, 1))
                .setAdvisoryDefectsWelsh(generateRFRs(ADVISORY_RFR_TEXT, 1))
                .setMinorDefectsHeader(DefectSummaryComponent.MINOR_DEFECTS_HEADER_TEST_WELSH_CVS)
                .setAdvisoryDefectsHeader(DefectSummaryComponent.ADVISORIES_HEADER_TEXT_WELSH_CVS)
                .setMinorDefects(generateRFRs(MINOR_RFR_TEXT, 1))
                .setAdvisoryDefects(generateRFRs(ADVISORY_RFR_TEXT, 1))

                .setCountryOfRegistrationCode("GB")
                .setRawVin(VIN)
                .setDateOfTheTest("22.08.2023")
                .setExpiryDate("31.08.2024")
                .setTestStationPNumber("P12345")
                .setTestStationName("TEST STATION NAME")
                .setMake("MERCEDES")
                .setModel("Z WAGON")
                .setVehicleEuClassification("M1")
                .setRawVrm("KA15APH")
                .setCurrentOdometer(
                        new CvsOdometerReading("20000", "mi", "22.08.2023")
                )
                .setOdometerHistoryList(Arrays.asList(
                        new CvsOdometerReading("12000", "mi", "13.08.2022"),
                        new CvsOdometerReading("7000", "mi", "02.08.2021")
                        )
                )
                .setIssuersName("TESTER NAME")
                .setTestStationName("TEST STATION NAME")
                .setTestNumber("X01X00001")
                .setEarliestDateOfTheNextTest("01.07.2024");

        vtg5W.setData(vtg5WData);

        Signature signature = new Signature();
        signature
                .setImageData("iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAYAAAAfFcSJAAAADUlEQVR42mNkYPhfDwAChwGA60e6kgAAAABJRU5ErkJggg==")
                .setImageType("png");

        vtg5W.setSignature(signature);
        vtg5W.setReissue(generateReissuer());

        return vtg5W;
    }

    public static CvsHgvPassBilingual getCvsHgvPassBilingual() {
        CvsHgvPassBilingual hgvPassBilingual = new CvsHgvPassBilingual();
        hgvPassBilingual.setDocumentName(CertificateTypes.CVS_HGV_PASS_BILINGUAL.getCertificateType());

        CvsMotCertificateDataWelsh bilingualData = new CvsMotCertificateDataWelsh();
        bilingualData
                .setMinorDefectsWelsh(generateRFRs(MINOR_RFR_TEXT, 1))
                .setAdvisoryDefectsWelsh(generateRFRs(ADVISORY_RFR_TEXT, 1))
                .setMinorDefectsHeader(DefectSummaryComponent.MINOR_DEFECTS_HEADER_TEST_WELSH_CVS)
                .setAdvisoryDefectsHeader(DefectSummaryComponent.ADVISORIES_HEADER_TEXT_WELSH_CVS)
                .setMinorDefects(generateRFRs(MINOR_RFR_TEXT, 1))
                .setAdvisoryDefects(generateRFRs(ADVISORY_RFR_TEXT, 1))


                .setCountryOfRegistrationCode("GB")
                .setRawVin(VIN)
                .setDateOfTheTest("22.08.2023")
                .setExpiryDate("31.08.2024")
                .setTestStationPNumber("P12345")
                .setTestStationName("TEST STATION NAME")
                .setMake("MERCEDES")
                .setModel("Z WAGON")
                .setVehicleEuClassification("M1")
                .setRawVrm("KA15APH")
                .setCurrentOdometer(
                        new CvsOdometerReading("20000", "km", "22.08.2023")
                )
                .setOdometerHistoryList(Arrays.asList(
                                new CvsOdometerReading("12000", "km", "13.08.2022"),
                                new CvsOdometerReading("7000", "km", "02.08.2021")
                        )
                )
                .setIssuersName("TESTER NAME")
                .setTestStationName("TEST STATION NAME")
                .setTestNumber("X01X00001")
                .setEarliestDateOfTheNextTest("01.07.2024");

        hgvPassBilingual.setData(bilingualData);
        return hgvPassBilingual;
    }

    public static VTG5 getVtg5HavingInvalidXMLCharacter() {
        VTG5 document = getVtg5();

        CvsMotCertificateData data = document.getData();
        data.setAdvisoryDefects(generateRFRs(INVALID_XML_RFR_TEXT, 3));

        return document;
    }

    public static VTG30 getVtg30() {
        VTG30 vtg30 = new VTG30();
        vtg30.setDocumentName(CertificateTypes.CVS_HGV_TRL_FAIL.getCertificateType());
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
        vtg30.setReissue(generateReissuer());

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
