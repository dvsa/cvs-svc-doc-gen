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

    public static VTP20W getVtp20wWithNoOdometerHistory() {
        VTP20W document = getVtp20W();

        CvsMotCertificateData data = document.getData();
        data.setOdometerHistoryList(null);

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

    public static VTP20W getVtp20W() {
        VTP20W vtp20W = new VTP20W();
        vtp20W.setDocumentName(CertificateTypes.CVS_PASS_WELSH.getCertificateType());
        CvsMotCertificateDataWelsh vtp20WData = new CvsMotCertificateDataWelsh();
        vtp20WData
                .setMinorDefectsWelsh(generateRFRs(MINOR_RFR_TEXT, 1))
                .setAdvisoryDefectsWelsh(generateRFRs(ADVISORY_RFR_TEXT, 1))
                .setMinorDefectsHeader(DefectSummaryComponent.MINOR_DEFECTS_HEADER_TEXT_WELSH_CVS)
                .setAdvisoryDefectsHeader(DefectSummaryComponent.ADVISORIES_HEADER_TEXT_WELSH_CVS)
                .setMinorDefects(generateRFRs(MINOR_RFR_TEXT, 1))
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
                        new CvsOdometerReading("120", "mi", "01.02.2016"),
                        new CvsOdometerReading("330", "mi", "30.01.2017")
                ))
                .setIssuersName("R.DREWNO")
                .setTestStationName("POPULAR GARAGES")
                .setTestNumber("1806 8140 0628")
                .setEarliestDateOfTheNextTest("13.10.2018");

        vtp20W.setData(vtp20WData);

        Signature signature = new Signature();
        signature
                .setImageData("iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAYAAAAfFcSJAAAADUlEQVR42mNkYPhfDwAChwGA60e6kgAAAABJRU5ErkJggg==")
                .setImageType("png");

        vtp20W.setSignature(signature);

        return vtp20W;
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
                .setMinorDefectsHeader(DefectSummaryComponent.MINOR_DEFECTS_HEADER_TEXT_WELSH_CVS)
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

    public static VTG5AW getVTG5AW() {
        VTG5AW vtg5AW = new VTG5AW();
        vtg5AW.setDocumentName(CertificateTypes.CVS_TRL_PASS_WELSH.getCertificateType());
        CvsMotCertificateDataWelsh vtg5AWData = new CvsMotCertificateDataWelsh();
        vtg5AWData
                .setMinorDefectsWelsh(generateRFRs(MINOR_RFR_TEXT, 1))
                .setAdvisoryDefectsWelsh(generateRFRs(ADVISORY_RFR_TEXT, 1))
                .setMinorDefectsHeader(DefectSummaryComponent.MINOR_DEFECTS_HEADER_TEXT_WELSH_CVS)
                .setAdvisoryDefectsHeader(DefectSummaryComponent.ADVISORIES_HEADER_TEXT_WELSH_CVS)
                .setMinorDefects(generateRFRs(MINOR_RFR_TEXT, 1))
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

        vtg5AW.setData(vtg5AWData);

        Signature signature = new Signature();
        signature
                .setImageData("iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAYAAAAfFcSJAAAADUlEQVR42mNkYPhfDwAChwGA60e6kgAAAABJRU5ErkJggg==")
                .setImageType("png");

        vtg5AW.setSignature(signature);
        vtg5AW.setReissue(generateReissuer());

        return vtg5AW;
    }

    public static CvsHgvPassBilingual getCvsHgvPassBilingual() {
        CvsHgvPassBilingual hgvPassBilingual = new CvsHgvPassBilingual();
        hgvPassBilingual.setDocumentName(CertificateTypes.CVS_HGV_PASS_BILINGUAL.getCertificateType());

        CvsMotCertificateDataWelsh bilingualData = new CvsMotCertificateDataWelsh();
        bilingualData
                .setMinorDefectsWelsh(generateRFRs(MINOR_RFR_TEXT, 1))
                .setAdvisoryDefectsWelsh(generateRFRs(ADVISORY_RFR_TEXT, 1))
                .setMinorDefectsHeader(DefectSummaryComponent.MINOR_DEFECTS_HEADER_TEXT_WELSH_CVS)
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

    public static CvsPsvPassBilingual getCvsPsvPassBilingual() {
        CvsPsvPassBilingual psvPassBilingual = new CvsPsvPassBilingual();
        psvPassBilingual.setDocumentName(CertificateTypes.CVS_PASS_BILINGUAL.getCertificateType());

        CvsMotCertificateDataWelsh bilingualData = new CvsMotCertificateDataWelsh();
        bilingualData
                .setMinorDefectsWelsh(generateRFRs(MINOR_RFR_TEXT, 1))
                .setAdvisoryDefectsWelsh(generateRFRs(ADVISORY_RFR_TEXT, 1))
                .setMinorDefectsHeader(DefectSummaryComponent.MINOR_DEFECTS_HEADER_TEXT_WELSH_CVS)
                .setAdvisoryDefectsHeader(DefectSummaryComponent.ADVISORIES_HEADER_TEXT_WELSH_CVS)
                .setMinorDefects(generateRFRs(MINOR_RFR_TEXT, 1))
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

        psvPassBilingual.setData(bilingualData);
        return psvPassBilingual;
    }

    public static CvsTrlPassBilingual getCvsTrlPassBilingual() {
        CvsTrlPassBilingual trlPassBilingual = new CvsTrlPassBilingual();
        trlPassBilingual.setDocumentName(CertificateTypes.CVS_TRL_PASS_BILINGUAL.getCertificateType());

        CvsMotCertificateDataWelsh bilingualData = new CvsMotCertificateDataWelsh();
        bilingualData
                .setMinorDefectsWelsh(generateRFRs(MINOR_RFR_TEXT, 1))
                .setAdvisoryDefectsWelsh(generateRFRs(ADVISORY_RFR_TEXT, 1))
                .setMinorDefectsHeader(DefectSummaryComponent.MINOR_DEFECTS_HEADER_TEXT_WELSH_CVS)
                .setAdvisoryDefectsHeader(DefectSummaryComponent.ADVISORIES_HEADER_TEXT_WELSH_CVS)
                .setMinorDefects(generateRFRs(MINOR_RFR_TEXT, 1))
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

        trlPassBilingual.setData(bilingualData);
        return trlPassBilingual;
    }

    public static CvsHgvTrlFailBilingual getCvsHgvTrlFailBilingual() {
        CvsHgvTrlFailBilingual hgvFailBilingual = new CvsHgvTrlFailBilingual();
        hgvFailBilingual.setDocumentName(CertificateTypes.CVS_HGV_TRL_FAIL_BILINGUAL.getCertificateType());

        CvsMotFailCertificateDataWelsh bilingualData = new CvsMotFailCertificateDataWelsh();
        bilingualData
                .setAdvisoryDefectsWelsh(generateRFRs(ADVISORY_RFR_TEXT, 1))
                .setMinorDefectsWelsh(generateRFRs(MINOR_RFR_TEXT, 1))
                .setDangerousDefectsWelsh(generateRFRs(DANGEROUS_RFR_TEXT, 1))
                .setMajorDefectsWelsh(generateRFRs(MAJOR_RFR_TEXT, 1))
                .setDangerousDefects(generateRFRs(MINOR_RFR_TEXT, 1))
                .setMajorDefects(generateRFRs(MAJOR_RFR_TEXT, 1))
                .setAdvisoryDefects(generateRFRs(ADVISORY_RFR_TEXT, 1))
                .setMinorDefects(generateRFRs(MINOR_RFR_TEXT, 1))

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
                .setIssuersName("TESTER NAME")
                .setTestStationName("TEST STATION NAME")
                .setTestNumber("X01X00001")
                .setEarliestDateOfTheNextTest("13.10.2018")
                .setTrn("ABC1234")
                .setIsTrailer(true);

        hgvFailBilingual.setFailData(bilingualData);

        Signature signature = new Signature();
        signature
                .setImageData("iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAYAAAAfFcSJAAAADUlEQVR42mNkYPhfDwAChwGA60e6kgAAAABJRU5ErkJggg==")
                .setImageType("png");

        hgvFailBilingual.setSignature(signature);

        return hgvFailBilingual;
    };

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

    public static VTG30W getVTG30W() {
        VTG30W vtg30W = new VTG30W();
        vtg30W.setDocumentName(CertificateTypes.CVS_HGV_TRL_FAIL_WELSH.getCertificateType());
        CvsMotFailCertificateDataWelsh vtg30wData = new CvsMotFailCertificateDataWelsh();

        vtg30wData
                .setAdvisoryDefectsWelsh(generateRFRs(ADVISORY_RFR_TEXT, 1))
                .setMinorDefectsWelsh(generateRFRs(MINOR_RFR_TEXT, 1))
                .setDangerousDefectsWelsh(generateRFRs(DANGEROUS_RFR_TEXT, 1))
                .setMajorDefectsWelsh(generateRFRs(MAJOR_RFR_TEXT, 1))
                .setDangerousDefectsHeader(DefectSummaryComponent.DANGEROUS_DEFECTS_HEADER_PARTIAL_TEXT_WELSH_CVS)
                .setMajorDefectsHeader(DefectSummaryComponent.MAJOR_DEFECTS_HEADER_TEXT_WELSH_CVS)
                .setMinorDefectsHeader(DefectSummaryComponent.MINOR_DEFECTS_HEADER_TEXT_WELSH_CVS)
                .setAdvisoryDefectsHeader(DefectSummaryComponent.ADVISORIES_HEADER_TEXT_WELSH_CVS)
                .setMinorDefects(generateRFRs(MINOR_RFR_TEXT, 1))
                .setAdvisoryDefects(generateRFRs(ADVISORY_RFR_TEXT, 1))

                .setSeatBeltNumber("10")
                .setSeatBeltPreviousCheckDate("12.11.2018")
                .setSeatBeltTested("Yes")

                .setCountryOfRegistrationCode("GB")
                .setRawVin(VIN)
                .setDateOfTheTest("12.11.2018")
                .setExpiryDate("12.10.2018")
                .setMake("MERCEDES")
                .setModel("Z WAGON")
                .setVehicleEuClassification("M1")
                .setRawVrm("KA1SAPH")
                .setCurrentOdometer(
                        new CvsOdometerReading("20000", "mi", "22.08.2023")
                )
                .setOdometerHistoryList(Arrays.asList(
                        new CvsOdometerReading("12000", "mi", "13.08.2022"),
                        new CvsOdometerReading("7000", "mi", "02.08.2021")
                ))
                .setIssuersName("TESTER NAME")
                .setTestStationName("TEST STATION NAME")
                .setTestStationPNumber("P12345")
                .setTestNumber("X01X00001")
                .setEarliestDateOfTheNextTest("01.07.2024")
                .setTrn("ABC1234")
                .setIsTrailer(true);

        vtg30wData
                .setDangerousDefects(generateRFRs(DANGEROUS_RFR_TEXT, 1))
                .setMajorDefects(generateRFRs(MAJOR_RFR_TEXT, 1));

        vtg30W.setFailData(vtg30wData);

        Signature signature = new Signature();
        signature
                .setImageData("iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAYAAAAfFcSJAAAADUlEQVR42mNkYPhfDwAChwGA60e6kgAAAABJRU5ErkJggg==")
                .setImageType("png");

        vtg30W.setSignature(signature);

        return vtg30W;
    }

    public static IVA30 getIVA30() {
        IVA30 iva30 = new IVA30();
        iva30.setDocumentName(CertificateTypes.IVA30.getCertificateType());
        IvaFailCertificateData iva30Data = new IvaFailCertificateData();
        iva30Data.setVin("T0000111134");
        iva30Data.setSerialNo("012345");
        iva30Data.setVehicleTrailerNrNo("ABC123");
        iva30Data.setTestCategoryClass("M1");
        iva30Data.setTestCategoryBasicNormal("Normal");
        iva30Data.setMake("Make");
        iva30Data.setModel("Model");
        iva30Data.setBodyType("Body Type");
        iva30Data.setDate("28/11/2023");
        iva30Data.setTesterName("Tester One");
        iva30Data.setReapplicationDate("27/05/2024");
        iva30Data.setStation("Abshire-Kub");
        iva30Data.setAdditionalDefects(new AdditionalDefect[]{new AdditionalDefect("DefectName", "DefectNotes")});
        RequiredStandard mockRS = new RequiredStandard();
        mockRS.setPrs(false);
        mockRS.setRequiredStandard("Noise");
        mockRS.setRefCalculation("1.1");
        mockRS.setSectionNumber("1");
        mockRS.setAdditionalInfo(false);
        mockRS.setSectionDescription("description");
        mockRS.setRsNumber(1);
        mockRS.setInspectionTypes(new String[]{"Normal"});
        mockRS.setAdditionalNotes("Some additional defect");
        RequiredStandard[] requiredStandards = new RequiredStandard[]{mockRS};
        iva30Data.setRequiredStandards(requiredStandards);
        iva30.setIvaData(iva30Data);

        Signature signature = new Signature();
        signature
            .setImageData("iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAYAAAAfFcSJAAAADUlEQVR42mNkYPhfDwAChwGA60e6kgAAAABJRU5ErkJggg==")
            .setImageType("png");

        iva30.setSignature(signature);

        return iva30;
    }

    public static MSVA30 getMSVA30() {
        MSVA30 msva30 = new MSVA30();
        msva30.setDocumentName(CertificateTypes.MSVA30.getCertificateType());
        MsvaFailCertificateData msva30Data = new MsvaFailCertificateData();
        msva30Data.setVin("P0123010956789");
        msva30Data.setSerialNumber("012345");
        msva30Data.setVehicleZNumber("ABC123");
        msva30Data.setType("motorcycle");
        msva30Data.setMake("Make");
        msva30Data.setModel("Model");
        msva30Data.setDate("28/11/2023");
        msva30Data.setTesterName("Tester One");
        msva30Data.setRetestDate("27/05/2024");
        msva30Data.setStation("Abshire-Kub");
        msva30Data.setAdditionalDefects(new AdditionalDefect[]{new AdditionalDefect("DefectName", "DefectNotes")});
        RequiredStandard mockRS = new RequiredStandard();
        mockRS.setPrs(false);
        mockRS.setRequiredStandard("Noise");
        mockRS.setRefCalculation("1.1");
        mockRS.setSectionNumber("1");
        mockRS.setAdditionalInfo(false);
        mockRS.setSectionDescription("description");
        mockRS.setRsNumber(1);
        mockRS.setInspectionTypes(new String[]{"Normal"});
        mockRS.setAdditionalNotes("Some additional defect");
        RequiredStandard[] requiredStandards = new RequiredStandard[]{mockRS};
        msva30Data.setRequiredStandards(requiredStandards);
        msva30.setMsvaData(msva30Data);

        Signature signature = new Signature();
        signature
                .setImageData("iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAYAAAAfFcSJAAAADUlEQVR42mNkYPhfDwAChwGA60e6kgAAAABJRU5ErkJggg==")
                .setImageType("png");

        msva30.setSignature(signature);

        return msva30;
    }

    public static VTG30 getVtg30HavingInvalidXMLCharacter() {
        VTG30 document = getVtg30();

        CvsMotCertificateData data = document.getFailData();
        data.setAdvisoryDefects(generateRFRs(INVALID_XML_RFR_TEXT, 3));

        return document;
    }

    public static VTG30W getVTG30WHavingInvalidXMLCharacter() {
        VTG30W document = getVTG30W();

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
