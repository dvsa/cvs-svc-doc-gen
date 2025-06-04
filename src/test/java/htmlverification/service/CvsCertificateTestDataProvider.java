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

    public static RwtCertificate getRwtData() {
        RwtCertificate rwtCertificate = new RwtCertificate();
        rwtCertificate.setDocumentName(CertificateTypes.RWT_DATA.getCertificateType());
        RwtCertificateData rwtCertificateData = new RwtCertificateData();

        rwtCertificateData.setDgvw(98204)
                .setWeight2(40568)
                .setVehicleNumber("NKPILNCN")
                .setVin("GYFC26269R240355")
                .setIssuersName("CVS Dev1")
                .setDateOfInspection("26.02.2019")
                .setTestStationPNumber("09-4129632")
                .setDocumentNumber("123")
                .setDate("26.02.2019")
                .setIsTrailer(false)
                .setDefects(null);
        rwtCertificate.setRwtData(rwtCertificateData);
        return rwtCertificate;
    }

    public static RwtCertificate getRwtDataFail() {
        RwtCertificate rwtCertificate = new RwtCertificate();
        rwtCertificate.setDocumentName(CertificateTypes.RWT_DATA.getCertificateType());
        RwtCertificateData rwtCertificateData = new RwtCertificateData();
        String[] defectsList =new String[3];
        defectsList[0] = "54.1.a.ii Power steering: not working correctly and obviously affects steering control. Axles: 7. Inner Offside. Asdasd";
        defectsList[1] = "54.1.d.i Power steering: reservoir is below minimum level. Axles: 7. Outer Nearside.";
        defectsList[2] = "5.1 Compression Ignition Engines Statutory Smoke Meter Test: null Dasdasdccc";
        rwtCertificateData.setDgvw(98204)
                .setWeight2(40568)
                .setVehicleNumber("NKPILNCN")
                .setVin("GYFC26269R240355")
                .setIssuersName("CVS Dev1")
                .setDateOfInspection("26.02.2019")
                .setDocumentNumber("ABC123")
                .setTestStationPNumber("09-4129632")
                .setDate("26.02.2019")
                .setDefects(defectsList)
                .setIsTrailer(false);
        rwtCertificate.setRwtData(rwtCertificateData);
        return rwtCertificate;
    }

    public static AdrPassCertificate getAdrPass() {
        AdrPassCertificate adrPassCertificate = new AdrPassCertificate();
        adrPassCertificate.setDocumentName(CertificateTypes.ADR_PASS.getCertificateType());
        AdrPassCertificateData adrPassCertificateData = new AdrPassCertificateData();

        adrPassCertificateData.setApplicantDetails( new ApplicantDetails("applicantDetailsName", "applicantDetailsAddress1", "applicantDetailsAddress2", "applicantDetailsPostTown", "applicantAddress3", "applicantDetailsPostCode", "applicantTelephoneNumber", "applicantEmailAddress"))
                .setMake("demoMake").setBrakeEndurance(true).setVin("demoVin").setPermittedDangerousGoods(new String[]{"\"FP <61 (FL)\"", "Explosives (type 2)", "Explosives (type 3)"})
                .setVrm("demoVrm").setSpecialProvisions("demoSpecialProvisions").setTankCode("demoTankCode").setTankManufacturer("demoTankManufacturer")
                .setTankManufactureSerialNo("demoTankManufacturerSerialNo").setTankStatement(new TankStatement("Substances permitted under the tank code and any special provisions specified in 9 may be carried", "demoStatement", null))
                .setTc2InitApprovalNo("demoTc2InitApprovalNo").setAdrVehicleType("demoAdrVehicleType").setWeight("demoWeight").setYearOfManufacture("1950").setNotes("demoNotes");
        adrPassCertificate.setAdrData(adrPassCertificateData);
        return adrPassCertificate;
    }

    public static TrailerIntoService getTrailerIntoService(int paragraphId) {
        TrailerIntoService model = new TrailerIntoService();
        model.setDocumentName(CertificateTypes.TRAILER_INTO_SERVICE.getCertificateType());

        model.setVin("ABCDEFGH444444")
                .setTrailerId("1234567Z")
                .setApprovalTypeNumber("a00*AB00/0000*000")
                .setLetterDateRequested("2023-02-17T15:16:09.060Z")
                .setApplicantDetails(new ApplicantDetails("applicant name", "applicant address 1", "applicant address 2", "applicant post town", "applicant address 3", "applicant post code", "applicantTelephoneNumber", "applicantEmailAddress"))
                .setParagraphId(paragraphId > 0 ? paragraphId : 3);

        return model;
    }

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

    public static VTP30W getVtp30wWithNoOdometerHistory() {
        VTP30W document = getVtp30w();

        CvsMotCertificateData data = document.getFailData();
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
                .setEarliestDateOfTheNextTest("13.10.2018")
                .setRecalls(
                        new Recalls("Aston Martin", true)
                );

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
                .setEarliestDateOfTheNextTest("13.10.2018")
                .setRecalls(
                        new Recalls("Aston Martin", true)
                );

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
                .setEarliestDateOfTheNextTest("13.10.2018")
                .setRecalls(
                        new Recalls("Aston Martin", true)
                );

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

    public static VTP30W getVtp30w() {
        VTP30W vtp30 = new VTP30W();
        vtp30.setDocumentName(CertificateTypes.CVS_FAIL_WELSH.getCertificateType());
        CvsMotFailCertificateDataWelsh vtp30Data = new CvsMotFailCertificateDataWelsh();

        vtp30Data
                .setMinorDefectsWelsh(generateRFRs(MINOR_RFR_TEXT, 1))
                .setAdvisoryDefectsWelsh(generateRFRs(ADVISORY_RFR_TEXT, 1))
                .setMinorDefectsHeader(DefectSummaryComponent.MINOR_DEFECTS_HEADER_TEXT_WELSH_CVS)
                .setAdvisoryDefectsHeader(DefectSummaryComponent.ADVISORIES_HEADER_TEXT_WELSH)
                .setSeatBeltNumber("10")
                .setDateOfTheTest("12.11.2018")
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
                .setEarliestDateOfTheNextTest("13.10.2018")
                .setRecalls(
                        new Recalls("Aston Martin", true)
                )
                .setOdometerUnit("mi");

        vtp30Data
                .setMajorDefectsWelsh(generateRFRs(MAJOR_RFR_TEXT, 1))
                .setDangerousDefectsWelsh(generateRFRs(DANGEROUS_RFR_TEXT, 1))
                .setMajorDefectsHeader(DefectSummaryComponent.MAJOR_DEFECTS_HEADER_TEXT_WELSH)
                .setDangerousDefectsHeader(DefectSummaryComponent.DANGEROUS_DEFECTS_HEADER_PARTIAL_TEXT_WELSH_CVS)
                .setPrsDefects(generateRFRs(PRS_RFR_TEXT, 1))
                .setMajorDefects(generateRFRs(MAJOR_RFR_TEXT, 1))
                .setDangerousDefects(generateRFRs(DANGEROUS_RFR_TEXT, 1));

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
                .setEarliestDateOfTheNextTest("13.10.2018")
                .setRecalls(
                        new Recalls("Aston Martin", true)
                );

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
                .setIsTrailer(true)
                .setRecalls(
                        new Recalls("Aston Martin", true)
                );

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
                .setEarliestDateOfTheNextTest("01.07.2024")
                .setRecalls(
                        new Recalls("Aston Martin", true)
                );

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
                .setIsTrailer(true)
                .setRecalls(
                        new Recalls("Aston Martin", true)
                );

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
                .setEarliestDateOfTheNextTest("01.07.2024")
                .setRecalls(
                        new Recalls("Aston Martin", true)
                );

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
                .setEarliestDateOfTheNextTest("13.10.2018")
                .setRecalls(
                        new Recalls("Aston Martin", true)
                );

        psvPassBilingual.setData(bilingualData);
        return psvPassBilingual;
    }
    public static VTP30Bilingual getCvsPsvFailBilingual() {
        VTP30Bilingual psvPassBilingual = new VTP30Bilingual();
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
                .setIsTrailer(true)
                .setRecalls(
                        new Recalls("Aston Martin", true)
                );

        trlPassBilingual.setData(bilingualData);
        return trlPassBilingual;
    }

    public static CvsTrlPRSBilingual getCvsTrlPrsBilingual() {
        CvsTrlPRSBilingual trlPrsBilingual = new CvsTrlPRSBilingual();
        trlPrsBilingual.setDocumentName(CertificateTypes.CVS_TRL_PRS_BILINGUAL.getCertificateType());
        CvsMotFailCertificateDataWelsh failData = new CvsMotFailCertificateDataWelsh();
        CvsMotCertificateDataWelsh passData = new CvsMotCertificateDataWelsh();

        passData
                .setMinorDefectsWelsh(generateRFRs(MINOR_RFR_TEXT, 1))
                .setAdvisoryDefectsWelsh(generateRFRs(ADVISORY_RFR_TEXT, 1))
                .setMinorDefectsHeader(DefectSummaryComponent.MINOR_DEFECTS_HEADER_TEXT_WELSH_CVS)
                .setAdvisoryDefectsHeader(DefectSummaryComponent.ADVISORIES_HEADER_TEXT_WELSH_CVS)
                .setMinorDefects(generateRFRs(MINOR_RFR_TEXT, 1))
                .setAdvisoryDefects(generateRFRs(ADVISORY_RFR_TEXT, 1))
                .setCurrentOdometer(
                        new CvsOdometerReading("22341", "mi", "01.02.2019")
                )
                .setOdometerHistoryList(Arrays.asList(
                        new CvsOdometerReading("120", "km", "01.02.2016"),
                        new CvsOdometerReading("330", "km", "30.01.2017")
                ))
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
                .setIsTrailer(true)
                .setRecalls(
                        new Recalls("Aston Martin", true)
                );

        failData
                .setPrsDefectsHeaderWelsh(DefectSummaryComponent.PRS_DEFECTS_HEADER_TEXT_WELSH_CVS)
                .setPrsDefectsWelsh(generateRFRs(PRS_RFR_TEXT, 1))
                .setPrsDefects(generateRFRs(PRS_RFR_TEXT, 1))
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
                .setIsTrailer(true)
                .setRecalls(
                        new Recalls("Aston Martin", true)
                );
        trlPrsBilingual.setFailData(failData);

        Signature signature = new Signature();
        signature
                .setImageData("iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAYAAAAfFcSJAAAADUlEQVR42mNkYPhfDwAChwGA60e6kgAAAABJRU5ErkJggg==")
                .setImageType("png");

        trlPrsBilingual.setSignature(signature);
        trlPrsBilingual.setData(passData);
        trlPrsBilingual.setData(passData);

        return trlPrsBilingual;
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
                .setIsTrailer(true)
                .setRecalls(
                        new Recalls("Aston Martin", true)
                );

        hgvFailBilingual.setFailData(bilingualData);

        Signature signature = new Signature();
        signature
                .setImageData("iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAYAAAAfFcSJAAAADUlEQVR42mNkYPhfDwAChwGA60e6kgAAAABJRU5ErkJggg==")
                .setImageType("png");

        hgvFailBilingual.setSignature(signature);

        return hgvFailBilingual;
    }

    public static CvsHgvPRSBilingual getCvsHgvPRSBilingual() {
        CvsHgvPRSBilingual hgvPRSBilingual = new CvsHgvPRSBilingual();
        hgvPRSBilingual.setDocumentName(CertificateTypes.CVS_HGV_PRS_BILINGUAL.getCertificateType());

        CvsMotFailCertificateDataWelsh bilingualFailData = new CvsMotFailCertificateDataWelsh();
        bilingualFailData
                .setPrsDefectsHeaderWelsh(DefectSummaryComponent.PRS_DEFECTS_HEADER_TEXT_WELSH_CVS)
                .setPrsDefectsWelsh(generateRFRs(PRS_RFR_TEXT, 1))
                .setPrsDefects(generateRFRs(PRS_RFR_TEXT, 1))
                .setSeatBeltNumber("10")
                .setSeatBeltPreviousCheckDate("12.11.2018")
                .setSeatBeltTested("Yes")
                .setCountryOfRegistrationCode("GB")
                .setRawVin(VIN)
                .setDateOfTheTest("22.08.2023")
                .setExpiryDate("31.08.2024")
                .setTestStationPNumber("P12345")
                .setTestStationName("TEST STATION NAME")
                .setMake("Aston Martin")
                .setModel("DB11")
                .setVehicleEuClassification("M1")
                .setRawVrm("KA1SAPH")
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
                .setEarliestDateOfTheNextTest("01.07.2024")
                .setTrn(null)
                .setIsTrailer(false)
                .setRecalls(
                        new Recalls("Aston Martin", true)
                );

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
                .setMake("Aston Martin")
                .setModel("DB11")
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
                .setEarliestDateOfTheNextTest("01.07.2024")
                .setRecalls(
                        new Recalls("Aston Martin", true)
                );

        hgvPRSBilingual.setFailData(bilingualFailData);
        hgvPRSBilingual.setData(bilingualData);

        return hgvPRSBilingual;
    }

    public static VTP30Bilingual getVtp30Bilingual() {
        VTP30Bilingual hgvFailBilingual = new VTP30Bilingual();
        hgvFailBilingual.setDocumentName(CertificateTypes.CVS_FAIL_BILINGUAL.getCertificateType());

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
                .setIsTrailer(true)
                .setRecalls(
                        new Recalls("Aston Martin", true)
                );

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
                .setTrn("ABC1234")
                .setRecalls(
                        new Recalls("Aston Martin", true)
                );

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
                .setIsTrailer(true)
                .setRecalls(
                        new Recalls("Aston Martin", true)
                );

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
        // PRS mock
        RequiredStandard mockRSPrs = new RequiredStandard();
        mockRSPrs.setPrs(true);
        mockRSPrs.setRequiredStandard("Noise");
        mockRSPrs.setRefCalculation("1.1");
        mockRSPrs.setSectionNumber("1");
        mockRSPrs.setAdditionalInfo(false);
        mockRSPrs.setSectionDescription("description");
        mockRSPrs.setRsNumber(1);
        mockRSPrs.setInspectionTypes(new String[]{"Normal"});
        mockRSPrs.setAdditionalNotes("Some additional defect");
        RequiredStandard[] requiredStandards = new RequiredStandard[]{mockRS, mockRSPrs};
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
        msva30Data.setReapplicationDate("27/05/2024");
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
        // PRS mock
        RequiredStandard mockRSPrs = new RequiredStandard();
        mockRSPrs.setPrs(true);
        mockRSPrs.setRequiredStandard("Noise");
        mockRSPrs.setRefCalculation("1.1");
        mockRSPrs.setSectionNumber("1");
        mockRSPrs.setAdditionalInfo(false);
        mockRSPrs.setSectionDescription("description");
        mockRSPrs.setRsNumber(1);
        mockRSPrs.setInspectionTypes(new String[]{"Normal"});
        mockRSPrs.setAdditionalNotes("Some additional defect");
        RequiredStandard[] requiredStandards = new RequiredStandard[]{mockRS, mockRSPrs};
        msva30Data.setRequiredStandards(requiredStandards);
        msva30.setMsvaData(msva30Data);

        Signature signature = new Signature();
        signature
                .setImageData("iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAYAAAAfFcSJAAAADUlEQVR42mNkYPhfDwAChwGA60e6kgAAAABJRU5ErkJggg==")
                .setImageType("png");

        msva30.setSignature(signature);

        return msva30;
    }

    public static VTG12 getVTG12() {
        VTG12 vtg12 = new VTG12();
        vtg12.setDocumentName(CertificateTypes.VTG12.getCertificateType());
        String[] reasonsForRefusal = { "Reason 1 exists VTG12","Reason 2 exists VTG12" };
        String additionalComments = "additional comments VTG12";

        Defects defects = new Defects(
            new String[]{"18.1.a.ii A driver’s seat: so insecure or in such a condition that it could cause the driver to lose control of the vehicle."},
            new String[]{"3.1.a Obligatory Seat Belt: missing.",
                    "62.1.a.ii Reflectors, conspicuity markings and/or rear markers: missing, incorrectly positioned and red colour is reflected to the front or white to the rear."},
            new String[]{"8.1.d.i A tyre: rubbing on any part of the vehicle."},
            new String[]{"67.2 European checked on Dipped Beam: The beam image contains"},
            new String[]{
                    "7.2 A Tyre: On a two axle motor vehicle fitted with single tyres on both axles a combination of tyres with structures which are not shown as acceptable in the table.",
                    "7.2 A Tyre: On a two axle motor vehicle fitted with single tyres on both axles a combination of tyres with structures which are not shown as acceptable in the table.",
                    "7.2 A Tyre: On a two axle motor vehicle fitted with single tyres on both axles a combination of tyres with structures which are not shown as acceptable in the table.",
                    "7.2 A Tyre: On a two axle motor vehicle fitted with single tyres on both axles a combination of tyres with structures which are not shown as acceptable in the table.",
                    "7.2 A Tyre: On a two axle motor vehicle fitted with single tyres on both axles a combination of tyres with structures which are not shown as acceptable in the table.",
                    "7.2 A Tyre: On a two axle motor vehicle fitted with single tyres on both axles a combination of tyres with structures which are not shown as acceptable in the table.",
                    "7.2 A Tyre: On a two axle motor vehicle fitted with single tyres on both axles a combination of tyres with structures which are not shown as acceptable in the table.",
                    "7.2 A Tyre: On a two axle motor vehicle fitted with single tyres on both axles a combination of tyres with structures which are not shown as acceptable in the table.",
                    "7.2 A Tyre: On a two axle motor vehicle fitted with single tyres on both axles a combination of tyres with structures which are not shown as acceptable in the table.",
                    "7.2 A Tyre: On a two axle motor vehicle fitted with single tyres on both axles a combination of tyres with structures which are not shown as acceptable in the table.",
            });

        AbandonedData abandonedData = new AbandonedData(
                "poiuytrewq01230109567891",
                reasonsForRefusal,
                "01.02.2024",
                "fake location",
                "fake12312312",
                "fake tester",
                additionalComments,
                defects);


        Signature signature = new Signature();
        signature
                .setImageData("iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAYAAAAfFcSJAAAADUlEQVR42mNkYPhfDwAChwGA60e6kgAAAABJRU5ErkJggg==")
                .setImageType("png");

        vtg12.setSignature(signature);

        vtg12.setData(abandonedData);
        return vtg12;
    }

    public static VTP12 getVTP12() {
        VTP12 vtp12 = new VTP12();
        vtp12.setDocumentName(CertificateTypes.VTP12.getCertificateType());
        String[] reasonsForRefusal = { "Reason 1 exists VTP12","Reason 2 exists VTP12" };
        String additionalComments = "additional comments VTP12";

        Defects defects = new Defects(
                new String[]{"18.1.a.ii A driver’s seat: so insecure or in such a condition that it could cause the driver to lose control of the vehicle."},
                new String[]{"3.1.a Obligatory Seat Belt: missing.",
                        "62.1.a.ii Reflectors, conspicuity markings and/or rear markers: missing, incorrectly positioned and red colour is reflected to the front or white to the rear."},
                new String[]{"8.1.d.i A tyre: rubbing on any part of the vehicle."},
                new String[]{"67.2 European checked on Dipped Beam: The beam image contains"},
                new String[]{
                        "7.2 A Tyre: On a two axle motor vehicle fitted with single tyres on both axles a combination of tyres with structures which are not shown as acceptable in the table.",
                        "7.2 A Tyre: On a two axle motor vehicle fitted with single tyres on both axles a combination of tyres with structures which are not shown as acceptable in the table.",
                        "7.2 A Tyre: On a two axle motor vehicle fitted with single tyres on both axles a combination of tyres with structures which are not shown as acceptable in the table.",
                        "7.2 A Tyre: On a two axle motor vehicle fitted with single tyres on both axles a combination of tyres with structures which are not shown as acceptable in the table.",
                        "7.2 A Tyre: On a two axle motor vehicle fitted with single tyres on both axles a combination of tyres with structures which are not shown as acceptable in the table.",
                        "7.2 A Tyre: On a two axle motor vehicle fitted with single tyres on both axles a combination of tyres with structures which are not shown as acceptable in the table.",
                        "7.2 A Tyre: On a two axle motor vehicle fitted with single tyres on both axles a combination of tyres with structures which are not shown as acceptable in the table.",
                        "7.2 A Tyre: On a two axle motor vehicle fitted with single tyres on both axles a combination of tyres with structures which are not shown as acceptable in the table.",
                        "7.2 A Tyre: On a two axle motor vehicle fitted with single tyres on both axles a combination of tyres with structures which are not shown as acceptable in the table.",
                        "7.2 A Tyre: On a two axle motor vehicle fitted with single tyres on both axles a combination of tyres with structures which are not shown as acceptable in the table.",
                });

        AbandonedData abandonedData = new AbandonedData(
                "poiuytrewq01230109567891",
                reasonsForRefusal,
                "01.02.2024",
                "fake location",
                "fake12312312",
                "fake tester",
                additionalComments,
                defects);

        Signature signature = new Signature();
        signature
                .setImageData("iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAYAAAAfFcSJAAAADUlEQVR42mNkYPhfDwAChwGA60e6kgAAAABJRU5ErkJggg==")
                .setImageType("png");

        vtp12.setSignature(signature);

        vtp12.setData(abandonedData);
        return vtp12;
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

    public static CvsHgvPRSBilingual getCvsHgvPRSBilingualHavingInvalidXMLCharacter() {
        CvsHgvPRSBilingual document = getCvsHgvPRSBilingual();

        CvsMotFailCertificateData data = document.getFailData();
        data.setPrsDefects(generateRFRs(INVALID_XML_RFR_TEXT, 3));

        return document;
    }

    private static List<String> generateRFRs(String rfrName, int numberOfRfrs) {
        List<String> reasonsForRejection = new ArrayList<>();

        for (int i = 0; i < numberOfRfrs; i++) {
            reasonsForRejection.add(rfrName + " #" + i); }
        return reasonsForRejection;
    }

    public static CvsPsvPRSBilingual getCvsPsvPRSBilingual() {
        CvsPsvPRSBilingual cvsPsvPRSBilingual = new CvsPsvPRSBilingual();
        cvsPsvPRSBilingual.setDocumentName(CertificateTypes.CVS_PSV_PRS_BILINGUAL.getCertificateType());

        cvsPsvPRSBilingual.setData(getVtp20W().getData());
        cvsPsvPRSBilingual.setFailData(getVtp30w().getFailData());

        return cvsPsvPRSBilingual;
    }

    public static CvsMotCertificate getCvsPsvPRSBilingualHavingInvalidXMLCharacter() {
        CvsPsvPRSBilingual document = getCvsPsvPRSBilingual();

        CvsMotFailCertificateData data = document.getFailData();
        data.setPrsDefects(generateRFRs(INVALID_XML_RFR_TEXT, 3));

        return document;
    }

    public static MinistryPlate getMinistryPlate() {
        MinistryPlate ministryPlateCertificate = new MinistryPlate();
        Reissue reissue = new Reissue();
        reissue.setReason("REPLACEMENT");
        ministryPlateCertificate.setDocumentName(CertificateTypes.VTG6_VTG7.getCertificateType());
        ministryPlateCertificate.setReissue(reissue);
        MinistryPlateData ministryPlateCertificateData = new MinistryPlateData();

        ministryPlateCertificateData.setPlateSerialNumber("12345")
                .setDtpNumber("DTPNUM").setPrimaryVrm("BBB333").setVin("MMMMMMMMMMMMMMMMMMMMW").setApprovalTypeNumber("23456")
                .setMake("Ford").setModel("Focus").setSpeedLimiterMrk("Yes").setFunctionCode("2AT").setRegnDate("2019-12-12").setManufactureYear("2018")
                .setGrossGbWeight("2987").setGrossEecWeight("2876").setGrossDesignWeight("2765").setTrainGbWeight("2764").setTrainEecWeight("2466")
                .setTrainDesignWeight("4452").setMaxTrainGbWeight("2233").setMaxTrainEecWeight("4567").setMaxLoadOnCoupling("2500")
                .setDimensionLength("5600").setDimensionWidth("8700").setFrontVehicleTo5thWheelCouplingMax("9845").setFrontVehicleTo5thWheelCouplingMin("9999")
                .setCouplingCenterToRearTrlMax("4012").setCouplingCenterToRearTrlMin("4013").setPlateIssueDate("2020-06-12T16:46:09.060Z").setTyreUseCode("2B")
                .setAxles(
                        new Axles(
                                new Axle(new Weight("1230", "5522", "1245"), new Tyre("205/45/R17", "152/148", "single")),
                                new Axle(new Weight("1231", "5523", "1246"), new Tyre("205/45/R18", "152/147", "single")),
                                new Axle(new Weight("1232", "5524", "1247"), new Tyre("205/45/R19", "152/146", "double")),
                                new Axle(new Weight("1233", "5525", "1248"), new Tyre("205/45/R20", "152/145", "single")))
                );
        //35 wide characters
        ministryPlateCertificateData.setVariantNumber("MWMMMMMMMMMMMMMMMMMWMMMMMMMMMMMMMMW");
        ministryPlateCertificate.setPlateData(ministryPlateCertificateData);
        return ministryPlateCertificate;
    }
}
