package htmlverification.service;

import htmlverification.framework.component.DefectSummaryComponent;
import uk.gov.dvsa.model.cvs.AdrPassCertificate;
import uk.gov.dvsa.model.cvs.MinistryPlate;
import uk.gov.dvsa.model.cvs.TrailerIntoService;
import uk.gov.dvsa.model.cvs.certificateData.AdrPassCertificateData;
import uk.gov.dvsa.model.cvs.certificateData.ApplicantDetails;
import uk.gov.dvsa.model.cvs.certificateData.TankStatement;
import uk.gov.dvsa.model.cvs.RwtCertificate;
import uk.gov.dvsa.model.cvs.certificateData.*;
import uk.gov.dvsa.model.mot.*;
import uk.gov.dvsa.model.mot.certificateData.*;
import uk.gov.dvsa.enums.CertificateTypes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static uk.gov.dvsa.view.mot.OdometerReadingFormatter.MILES_ENGLISH;

public class CertificateTestDataProvider {
    public static final String VTS_ID = "VTS12345";
    public static final String VIN = "QQIDAAAAAAA058568";
    public static final String INVALID_XML_RFR_TEXT = "Invalid XML character\f RFR";
    public static final String ADVISORY_RFR_TEXT = "Advisory RFR";
    public static final String DANGEROUS_RFR_TEXT = "Dangerous RFR";
    public static final String MAJOR_RFR_TEXT = "Major RFR";
    public static final String MINOR_RFR_TEXT = "Minor RFR";
    public static final String ADVISORY_RFR_WELSH_TEXT = "Advisory Welsh RFR";
    public static final String MINOR_RFR_WELSH_TEXT = "Minor Welsh RFR";
    public static final String MAJOR_RFR_WELSH_TEXT = "Major Welsh RFR";
    public static final String DANGEROUS_RFR_WELSH_TEXT = "Dangerous Welsh RFR";
    public static final String INSP_AUTHORITY_DVSA = "Driver & Vehicle Standards Agency";

    public static final String ODOMETER_VALUE = "22,341";

    public static final List<String> INSPECTION_AUTHORITY = new ArrayList<>();

    static {
        INSPECTION_AUTHORITY.add("Welsh Garage");
        INSPECTION_AUTHORITY.add("1 Welsh Road");
        INSPECTION_AUTHORITY.add("Swansea");
        INSPECTION_AUTHORITY.add("SW1 1NT\\t\\t+768-45-4433630");
    }

    public static VT20 getVt20HavingInvalidXMLCharacter() {
        VT20 document = getVt20();

        MotCertificateData data = document.getData();
        data.setEuAdvisoryDefects(generateRFRs(INVALID_XML_RFR_TEXT, 3));

        return document;
    }

    public static VT20 getVt20() {
        VT20 vt20 = new VT20();
        vt20.setDocumentName(CertificateTypes.PASS.getCertificateType());
        MotCertificateData vt20Data = new MotCertificateData();
        vt20Data
            .setMinorDefectsHeader(DefectSummaryComponent.MINOR_DEFECTS_HEADER_TEXT)
            .setEuMinorDefects(generateRFRs(MINOR_RFR_TEXT, 5))

            .setAdvisoryDefectsHeader(DefectSummaryComponent.ADVISORIES_HEADER_TEXT)
            .setEuAdvisoryDefects(generateRFRs(ADVISORY_RFR_TEXT, 3))

            .setCountryOfRegistrationCode("GB")
            .setRawVin(VIN)
            .setIssuedDate("2017-10-12")
            .setDateOfTheTest(LocalDate.of(2017, 10, 12))
            .setExpiryDate("12.10.2018")
            .setDateOfTheExpiryTest("12.10.2018")
            .setFirstUseDate("2010-10-12")
            .setTestStationAddress("VTS004004 53, Call Road, Worthing BN12 6PB, United Kingdom")
            .setMake("Aston Martin")
            .setModel("DB11")
            .setTestClass("4")
            .setVehicleEuClassification("M1")
            .setRawVrm("KA1SAPH")
            .setOdometer(ODOMETER_VALUE + " " + MILES_ENGLISH)
            .setCurrentOdometer(
                new OdometerReading("22341", "mi", LocalDate.now())
            )
            .setOdometerHistory("1 2 2016 200 miles\n 30 1 2017 300 miles")
            .setOdometerHistoryList(Arrays.asList(
                new OdometerReading("120", "km", LocalDate.of(2016, 2, 1)),
                new OdometerReading("330", "km", LocalDate.of(2017, 1, 30))
            ))
            .setIssuersName("R.DREWNO")
            .setTestStationName("POPULAR GARAGES")
            .setInspectionAuthority("POPULAR GARAGES R.DREWNO")
            .setAuthorisedExaminer("MILKE GROUP LIMITED")
            .setTestNumber("1806 8140 0628")
            .setEarliestDateOfTheNextTest(LocalDate.of(2018, 10, 13));
        vt20.setData(vt20Data);

        return vt20;
    }

    public static VT20 getVt20Duplicate() {
        VT20 vt20 = getVt20();
        vt20.setCertificateIssuerInfo("Duplicate certificate issued by B. T. Arctor Tester1 on 13 February 2018");
        return vt20;
    }

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
                .setMake("demoMake").setModel("demoModel").setAtfNameAtfPNumber("demoAtfNameAtfPnumber").setBrakeEndurance(true).setChasisNumber("demoChassisNumber")
                .setExpiryDate("10-12-2011").setPermittedDangerousGoods(new String[]{"\"FP <61 (FL)\"", "Explosives (type 2)", "Explosives (type 3)"})
                .setRegistrationNumber("demoRegistrationNumber").setSpecialProvisions("demoSpecialProvisions").setTankCode("demoTankCode").setTankManufacturer("demoTankManufacturer")
                .setTankManufactureSerialNo("demoTankManufacturerSerialNo").setTankStatement(new TankStatement("Substances permitted under the tank code and any special provisions specified in 9 may be carried", "demoStatement", null))
                .setTc2InitApprovalNo("demoTc2InitApprovalNo").setVehicleType("demoVehicleType").setWeight("demoWeight").setYearOfManufacture("1950").setAtfNameAtfPNumber("demoAtf demoAtfPNumber").setTestTypeDate("12019-11-06T15:37:32.917Z").setNotes("demoNotes");
        adrPassCertificate.setAdrData(adrPassCertificateData);
        return adrPassCertificate;
    }

    public static MinistryPlate getMinistryPlate() {
        MinistryPlate ministryPlateCertificate = new MinistryPlate();
        Reissue reissue = new Reissue();
        reissue.setReason("REPLACEMENT");
        ministryPlateCertificate.setDocumentName(CertificateTypes.VTG6_VTG7.getCertificateType());
        ministryPlateCertificate.setReissue(reissue);
        MinistryPlateData ministryPlateCertificateData = new MinistryPlateData();

        ministryPlateCertificateData.setPlateSerialNumber("12345")
                .setDtpNumber("DTPNUM").setPrimaryVrm("BBB333").setVin("ABCDEFGH444444").setVariantNumber("22").setApprovalTypeNumber("12345")
                .setMake("Ford").setModel("Focus").setSpeedLimiterMrk("Yes").setFunctionCode("A").setRegnDate("2019-12-12").setManufactureYear("2018")
                .setGrossGbWeight("2556").setGrossEecWeight("1245").setGrossDesignWeight("2345").setTrainGbWeight("3500").setTrainEecWeight("2466")
                .setTrainDesignWeight("4452").setMaxTrainGbWeight("2233").setMaxTrainEecWeight("1234").setMaxLoadOnCoupling("2500")
                .setDimensionLength("5600").setDimensionWidth("8700").setFrontVehicleTo5thWheelCouplingMax("9845").setFrontVehicleTo5thWheelCouplingMin("4567")
                .setCouplingCenterToRearTrlMax("1234").setCouplingCenterToRearTrlMin("1111").setPlateIssueDate("2020-06-12T16:46:09.060Z").setTyreUseCode("2B")
                .setAxles(
                    new Axles(
                        new Axle(new Weight("1230", "5522", "1245"), new Tyre("205/45/R17", "152/148", "single")),
                        new Axle(new Weight("1230", "5522", "1245"), new Tyre("205/45/R17", "152/148", "single")),
                        new Axle(new Weight("1230", "5522", "1245"), new Tyre("205/45/R17", "152/148", "single")),
                        new Axle(new Weight("1230", "5522", "1245"), new Tyre("205/45/R17", "152/148", "single")))
                );
        ministryPlateCertificate.setPlateData(ministryPlateCertificateData);
        return ministryPlateCertificate;
    }

    public static MinistryPlate getMinistryPlateTRL() {
        MinistryPlate ministryPlateCertificate = new MinistryPlate();
        Reissue reissue = new Reissue();
        reissue.setReason("REPLACEMENT");
        ministryPlateCertificate.setDocumentName(CertificateTypes.VTG6_VTG7_TRL.getCertificateType());
        ministryPlateCertificate.setReissue(reissue);
        MinistryPlateData ministryPlateCertificateData = new MinistryPlateData();

        ministryPlateCertificateData.setPlateSerialNumber("12345")
                .setDtpNumber("DTPNUM").setPrimaryVrm("BBB333").setVin("ABCDEFGH444444").setVariantNumber("22").setApprovalTypeNumber("12345")
                .setMake("Ford").setModel("Focus").setSpeedLimiterMrk("Yes").setFunctionCode("A").setRegnDate("2019-12-12").setManufactureYear("2018")
                .setGrossGbWeight("2556").setGrossEecWeight("1245").setGrossDesignWeight("2345").setTrainGbWeight("3500").setTrainEecWeight("2466")
                .setTrainDesignWeight("4452").setMaxTrainGbWeight("2233").setMaxTrainEecWeight("1234").setMaxLoadOnCoupling("2500")
                .setDimensionLength("5600").setDimensionWidth("8700").setFrontVehicleTo5thWheelCouplingMax("9845").setFrontVehicleTo5thWheelCouplingMin("4567")
                .setCouplingCenterToRearTrlMax("1234").setCouplingCenterToRearTrlMin("1111").setPlateIssueDate("2020-06-12T16:46:09.060Z").setTyreUseCode("2B")
                .setAxles(
                        new Axles(
                                new Axle(new Weight("1230", "5522", "1245"), new Tyre("205/45/R17", "152/148", "single")),
                                new Axle(new Weight("1230", "5522", "1245"), new Tyre("205/45/R17", "152/148", "single")),
                                new Axle(new Weight("1230", "5522", "1245"), new Tyre("205/45/R17", "152/148", "single")),
                                new Axle(new Weight("1230", "5522", "1245"), new Tyre("205/45/R17", "152/148", "single")))
                );
        ministryPlateCertificate.setPlateData(ministryPlateCertificateData);
        return ministryPlateCertificate;
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

    public static VT20 getMultiPageVt20WithHiddenIssuerInfo() {
        VT20 vt20 = getVt20();
        vt20.setData(vt20.getData().setEuAdvisoryDefects(generateRFRs(ADVISORY_RFR_TEXT, 20)));
        vt20.setIssuerSignatureVisible(false);
        return vt20;
    }

    public static VT20 getMultiPageVt20WithVisibleIssuerInfo() {
        VT20 vt20 = getVt20();
        vt20.setData(vt20.getData().setEuAdvisoryDefects(generateRFRs(ADVISORY_RFR_TEXT, 20)));
        vt20.setIssuerSignatureVisible(true);
        return vt20;
    }

    public static VT20 getMultiPageVt20WithUnspecifiedIssuerVisibilitySetting() {
        VT20 vt20 = getVt20();
        vt20.setData(vt20.getData().setEuAdvisoryDefects(generateRFRs(ADVISORY_RFR_TEXT, 20)));
        return vt20;
    }

    public static VT30 getVt30() {
        VT30 vt30 = new VT30();
        vt30.setDocumentName(CertificateTypes.FAIL.getCertificateType());
        MotFailCertificateData vt30Data = new MotFailCertificateData();
        vt30Data
            .setDangerousDefectsHeader(DefectSummaryComponent.DANGEROUS_DEFECTS_HEADER_TEXT)
            .setEuDangerousDefects(generateRFRs(DANGEROUS_RFR_TEXT, 2))

            .setMajorDefectsHeader(DefectSummaryComponent.MAJOR_DEFECTS_HEADER_TEXT)
            .setEuMajorDefects(generateRFRs(MAJOR_RFR_TEXT, 4))

            .setMinorDefectsHeader(DefectSummaryComponent.MINOR_DEFECTS_HEADER_TEXT)
            .setEuMinorDefects(generateRFRs(MINOR_RFR_TEXT, 5))

            .setAdvisoryDefectsHeader(DefectSummaryComponent.ADVISORIES_HEADER_TEXT)
            .setEuAdvisoryDefects(generateRFRs(ADVISORY_RFR_TEXT, 3))

            .setCountryOfRegistrationCode("GB")
            .setRawVin(VIN)
            .setIssuedDate("2017-10-12")
            .setDateOfTheTest(LocalDate.of(2017, 10, 12))
            .setExpiryDate("12.10.2018")
            .setFirstUseDate("2010-10-12")
            .setTestStationAddress("VTS004004 53, Call Road, Worthing BN12 6PB, United Kingdom")
            .setMake("Aston Martin")
            .setModel("DB11")
            .setTestClass("4")
            .setVehicleEuClassification("M1")
            .setRawVrm("KA1SAPH")
            .setOdometer(ODOMETER_VALUE + " " + MILES_ENGLISH)
            .setOdometerHistory("1 2 2016 200 miles\n 30 1 2017 300 miles")
            .setCurrentOdometer(
                new OdometerReading("22341", "mi", LocalDate.now())
            )
            .setOdometerHistoryList(Arrays.asList(
                new OdometerReading("200", "mi", LocalDate.of(2016, 02, 01)),
                new OdometerReading("300", "mi", LocalDate.of(2017, 01, 30))
            ))
            .setIssuersName("R.DREWNO")
            .setTestStationName("POPULAR GARAGES")
            .setInspectionAuthority("POPULAR GARAGES R.DREWNO")
            .setAuthorisedExaminer("MILKE GROUP LIMITED")
            .setTestNumber("1806 8140 0628")
            .setEarliestDateOfTheNextTest(LocalDate.of(2018, 10, 13));
        vt30.setFailData(vt30Data);

        return vt30;
    }

    public static VT30 getVt30Duplicate() {
        VT30 vt30 = getVt30();
        vt30.setCertificateIssuerInfo("Duplicate certificate issued by B. T. Arctor Tester1 on 13 February 2018");
        return vt30;
    }

    public static VT30 getMultiPageVt30WithHiddenIssuerInfo() {
        VT30 vt30 = getVt30();
        vt30.setData(vt30.getFailData().setEuAdvisoryDefects(generateRFRs(ADVISORY_RFR_TEXT, 20)));
        vt30.setIssuerSignatureVisible(false);
        return vt30;
    }

    public static VT30 getMultiPageVt30WithVisibleIssuerInfo() {
        VT30 vt30 = getVt30();
        vt30.setData(vt30.getFailData().setEuAdvisoryDefects(generateRFRs(ADVISORY_RFR_TEXT, 20)));
        vt30.setIssuerSignatureVisible(true);
        return vt30;
    }

    public static VT30 getMultiPageVt30WithUnspecifiedIssuerVisibilitySetting() {
        VT30 vt30 = getVt30();
        vt30.setData(vt30.getFailData().setEuAdvisoryDefects(generateRFRs(ADVISORY_RFR_TEXT, 20)));
        return vt30;
    }

    public static VT30 getVt30WithOverflownRFRs() {
        VT30 vt30WithOverflownRFRs = new VT30();
        vt30WithOverflownRFRs.setDocumentName(CertificateTypes.FAIL.getCertificateType());
        MotFailCertificateData vt30WithOverflownRFRsData = new MotFailCertificateData();
        vt30WithOverflownRFRsData
            .setDangerousDefectsHeader(DefectSummaryComponent.DANGEROUS_DEFECTS_HEADER_TEXT)
            .setEuDangerousDefects(generateRFRs(DANGEROUS_RFR_TEXT, 60))

            .setAdvisoryDefectsHeader(DefectSummaryComponent.ADVISORIES_HEADER_TEXT)
            .setEuAdvisoryDefects(generateRFRs(ADVISORY_RFR_TEXT, 3))

            .setCountryOfRegistrationCode("GB")
            .setRawVin(VIN)
            .setIssuedDate("2017-10-12")
            .setDateOfTheTest(LocalDate.of(2017, 10, 12))
            .setExpiryDate("12.10.2018")
            .setFirstUseDate("2010-10-12")
            .setTestStationAddress("VTS004004 53, Call Road, Worthing BN12 6PB, United Kingdom")
            .setMake("Aston Martin")
            .setModel("DB11")
            .setTestClass("4")
            .setVehicleEuClassification("M1")
            .setRawVrm("KA1SAPH")
            .setOdometer(ODOMETER_VALUE + " " + MILES_ENGLISH)
            .setOdometerHistory("1 2 2016 200 miles\n 30 1 2017 300 miles")
            .setCurrentOdometer(
                new OdometerReading("22341", "mi", LocalDate.now())
            )
            .setOdometerHistoryList(Arrays.asList(
                new OdometerReading("200", "mi", LocalDate.of(2016, 02, 01)),
                new OdometerReading("300", "mi", LocalDate.of(2017, 01, 30))
            ))

            .setIssuersName("R.DREWNO")
            .setTestStationName("POPULAR GARAGES")
            .setInspectionAuthority("POPULAR GARAGES R.DREWNO")
            .setAuthorisedExaminer("MILKE GROUP LIMITED")
            .setTestNumber("180681400628")
            .setEarliestDateOfTheNextTest(LocalDate.of(2018, 10, 13));

        vt30WithOverflownRFRs.setFailData(vt30WithOverflownRFRsData);
        return vt30WithOverflownRFRs;
    }

    public static VT30 getVt30Refusal() {
        VT30 vt30 = new VT30();
        vt30.setDocumentName("MOT/VT30");
        MotFailCertificateData vt30Data = new MotFailCertificateData();
        vt30Data
            .setCountryOfRegistrationCode("GB")
            .setRawVin(VIN)
            .setDateOfTheTest(LocalDate.of(2017, 10, 12))
            .setExpiryDate("12.10.2018")
            .setFirstUseDate("2010-10-12")
            .setTestStationAddress("VTS004004 53, Call Road, Worthing BN12 6PB, United Kingdom")
            .setMake("Aston Martin")
            .setModel("DB11")
            .setTestClass("4")
            .setVehicleEuClassification("M1")
            .setRawVrm("KA1SAPH")
            .setOdometer("Not Recorded")
            .setCurrentOdometer(
                new OdometerReading("Not Recorded", null, null)
            )
            .setIssuersName("R.DREWNO")
            .setTestStationName("POPULAR GARAGES")
            .setInspectionAuthority("POPULAR GARAGES R.DREWNO")
            .setAuthorisedExaminer("MILKE GROUP LIMITED");

        vt30Data.setReasonForCancelEn("Classified reason for Cancel")
            .setReasonForCancelComment("This is personal comment made by tester");
        vt30.setFailData(vt30Data);

        return vt30;
    }

    public static VT20W getVt20W() {
        VT20W vt20W = new VT20W();
        vt20W.setDocumentName(CertificateTypes.WELSH_PASS.getCertificateType());
        MotCertificateDataWelsh vt20WData = new MotCertificateDataWelsh();
        vt20WData
                .setEuMinorDefectsCy(generateRFRs(MINOR_RFR_WELSH_TEXT, 5))
                .setEuAdvisoryDefectsCy(generateRFRs(ADVISORY_RFR_WELSH_TEXT, 3))
                .setEuMinorDefects(generateRFRs(MINOR_RFR_TEXT, 5))
                .setEuAdvisoryDefects(generateRFRs(ADVISORY_RFR_TEXT, 3))
                .setMinorDefectsHeader(DefectSummaryComponent.MINOR_DEFECTS_HEADER_TEXT_WELSH)
                .setAdvisoryDefectsHeader(DefectSummaryComponent.ADVISORIES_HEADER_TEXT_WELSH)
                .setCountryOfRegistrationCode("GB")
                .setRawVin(VIN)
                .setIssuedDate("2017-10-12")
                .setDateOfTheTest(LocalDate.of(2017, 10, 12))
                .setExpiryDate("12.10.2018")
                .setDateOfTheExpiryTest("12.10.2018")
                .setFirstUseDate("2010-10-12")
                .setTestStationAddress("VTS004004 53, Call Road, Worthing BN12 6PB, United Kingdom")
                .setMake("Aston Martin")
                .setModel("DB11")
                .setTestClass("4")
                .setVehicleEuClassification("M1")
                .setRawVrm("KA1SAPH")
                .setOdometer(ODOMETER_VALUE + " " + MILES_ENGLISH)
                .setCurrentOdometer(
                        new OdometerReading("22341", "22341", "mi", LocalDate.now())
                )
                .setOdometerHistory("1 2 2016 200 miles\n 30 1 2017 300 miles")
                .setOdometerHistoryList(Arrays.asList(
                        new OdometerReading("120", "120", "km", LocalDate.of(2016, 2, 1)),
                        new OdometerReading("330", "330", "km", LocalDate.of(2017, 1, 30))
                ))
                .setIssuersName("R.DREWNO")
                .setTestStationName("POPULAR GARAGES")
                .setInspectionAuthority("POPULAR GARAGES R.DREWNO")
                .setAuthorisedExaminer("MILKE GROUP LIMITED")
                .setTestNumber("1806 8140 0628")
                .setEarliestDateOfTheNextTest(LocalDate.of(2018, 10, 13))
                .setAdditionalInformation("Not really used information");
        vt20W.setData(vt20WData);

        return vt20W;
    }

    public static VT20W getMultiPageVt20WWithHiddenIssuerInfo() {
        VT20W vt20w = getVt20W();
        vt20w.setData(vt20w.getData().setEuAdvisoryDefects(generateRFRs(ADVISORY_RFR_WELSH_TEXT, 20)));
        vt20w.setIssuerSignatureVisible(false);
        return vt20w;
    }

    public static VT20W getMultiPageVt20WWithVisibleIssuerInfo() {
        VT20W vt20w = getVt20W();
        vt20w.setData(vt20w.getData().setEuAdvisoryDefects(generateRFRs(ADVISORY_RFR_WELSH_TEXT, 20)));
        vt20w.setIssuerSignatureVisible(true);
        return vt20w;
    }

    public static VT20W getMultiPageVt20WWithUnspecifiedIssuerVisibilitySetting() {
        VT20W vt20w = getVt20W();
        vt20w.setData(vt20w.getData().setEuAdvisoryDefects(generateRFRs(ADVISORY_RFR_WELSH_TEXT, 20)));
        return vt20w;
    }

    public static VT30W getVt30W() {
        VT30W vt30W = new VT30W();
        vt30W.setDocumentName(CertificateTypes.WELSH_FAIL.getCertificateType());
        MotFailCertificateDataWelsh vt30WData = new MotFailCertificateDataWelsh();
        vt30WData
            .setEuAdvisoryDefectsCy(generateRFRs(ADVISORY_RFR_WELSH_TEXT, 3))
            .setEuDangerousDefectsCy(generateRFRs(DANGEROUS_RFR_WELSH_TEXT, 2))
            .setEuMajorDefectsCy(generateRFRs(MAJOR_RFR_WELSH_TEXT, 4))
            .setEuMinorDefectsCy(generateRFRs(MINOR_RFR_WELSH_TEXT, 5))

            .setDangerousDefectsHeader(DefectSummaryComponent.DANGEROUS_DEFECTS_HEADER_TEXT)
            .setEuDangerousDefects(generateRFRs(DANGEROUS_RFR_TEXT, 2))

            .setMajorDefectsHeader(DefectSummaryComponent.MAJOR_DEFECTS_HEADER_TEXT)
            .setEuMajorDefects(generateRFRs(MAJOR_RFR_TEXT, 4))

            .setMinorDefectsHeader(DefectSummaryComponent.MINOR_DEFECTS_HEADER_TEXT)
            .setEuMinorDefects(generateRFRs(MINOR_RFR_TEXT, 5))

            .setAdvisoryDefectsHeader(DefectSummaryComponent.ADVISORIES_HEADER_TEXT)
            .setEuAdvisoryDefects(generateRFRs(ADVISORY_RFR_TEXT, 3))

            .setCountryOfRegistrationCode("GB")
            .setRawVin(VIN)
            .setIssuedDate("2017-10-12")
            .setDateOfTheTest(LocalDate.of(2017, 10, 12))
            .setExpiryDate("12.10.2018")
            .setFirstUseDate("2010-10-12")
            .setTestStationAddress("VTS004004 53, Call Road, Worthing BN12 6PB, United Kingdom")
            .setMake("Aston Martin")
            .setModel("DB11")
            .setTestClass("4")
            .setVehicleEuClassification("M1")
            .setRawVrm("KA1SAPH")
            .setOdometer(ODOMETER_VALUE + " " + MILES_ENGLISH)
            .setOdometerHistory("1 2 2014 450 miles\n 30 1 2017 500 miles")
            .setCurrentOdometer(
                new OdometerReading("22341", "22341", "mi", LocalDate.now())
            )
            .setOdometerHistoryList(Arrays.asList(
                new OdometerReading("300", "300","mi", LocalDate.of(2016, 02, 01)),
                new OdometerReading("600", "600", "mi", LocalDate.of(2017, 01, 30))
            ))
            .setIssuersName("R.DREWNO")
            .setTestStationName("POPULAR GARAGES")
            .setInspectionAuthority("POPULAR GARAGES R.DREWNO")
            .setAuthorisedExaminer("MILKE GROUP LIMITED")
            .setTestNumber("1806 8140 0628")
            .setEarliestDateOfTheNextTest(LocalDate.of(2018, 10, 13));
        vt30W.setFailData(vt30WData);

        return vt30W;
    }

    public static VT30W getMultiPageVt30WWithHiddenIssuerInfo() {
        VT30W vt30w = getVt30W();
        vt30w.setData(vt30w.getFailData().setEuAdvisoryDefects(generateRFRs(ADVISORY_RFR_TEXT, 20)));
        vt30w.setIssuerSignatureVisible(false);
        return vt30w;
    }

    public static VT30W getMultiPageVt30WWithVisibleIssuerInfo() {
        VT30W vt30w = getVt30W();
        vt30w.setData(vt30w.getFailData().setEuAdvisoryDefects(generateRFRs(ADVISORY_RFR_TEXT, 20)));
        vt30w.setIssuerSignatureVisible(true);
        return vt30w;
    }

    public static VT30W getMultiPageVt30WWithUnspecifiedIssuerVisibilitySetting() {
        VT30W vt30w = getVt30W();
        vt30w.setData(vt30w.getFailData().setEuAdvisoryDefects(generateRFRs(ADVISORY_RFR_TEXT, 20)));
        return vt30w;
    }

    public static CT20 getCt20() {
        CT20 ct20 = new CT20();
        ct20.setDocumentName(CertificateTypes.CONTINGENCY_PASS.getCertificateType());
        ct20.setVts(VTS_ID).setInspectionAuthority(INSPECTION_AUTHORITY);
        return ct20;
    }

    public static CT30 getCt30() {
        CT30 ct30 = new CT30();
        ct30.setDocumentName(CertificateTypes.CONTINGENCY_FAIL.getCertificateType());
        ct30.setVts(VTS_ID).setInspectionAuthority(INSPECTION_AUTHORITY);
        return ct30;
    }

    public static CT32 getCt32() {
        CT32 ct32 = new CT32();
        ct32.setDocumentName(CertificateTypes.CONTINGENCY_ADVISORY_NOTICE.getCertificateType());
        ct32.setVts(VTS_ID).setInspectionAuthority(INSPECTION_AUTHORITY);
        return ct32;
    }

    public static CT20 getEuCt20() {
        CT20 ct20 = new CT20();
        ct20.setDocumentName(CertificateTypes.EU_CONTINGENCY_PASS.getCertificateType());
        ct20.setVts(VTS_ID).setInspectionAuthority(INSPECTION_AUTHORITY);
        return ct20;
    }

    public static CT30 getEuCt30() {
        CT30 ct30 = new CT30();
        ct30.setDocumentName(CertificateTypes.EU_CONTINGENCY_FAIL.getCertificateType());
        ct30.setVts(VTS_ID).setInspectionAuthority(INSPECTION_AUTHORITY);
        return ct30;
    }

    public static VT20W getVt20WDuplicate() {
        VT20W vt20W = getVt20W();
        vt20W.setCertificateIssuerInfoCy("Dyblyg wedi ei gyhoeddi gan B. T. Arctor Tester1 ar 22 Chwefror 2018");
        return vt20W;
    }

    public static VT30W getVt30WDuplicate() {
        VT30W vt30W = getVt30W();
        vt30W.setCertificateIssuerInfoCy("Dyblyg wedi ei gyhoeddi gan B. T. Arctor Tester1 ar 22 Chwefror 2018");
        return vt30W;
    }

    public static PRS getPRS() {
        PRS prs = new PRS();
        prs.setDocumentName(CertificateTypes.PRS.getCertificateType());
        prs.setData(getVt20().getData());
        prs.setFailData(getVt30().getFailData());
        return prs;
    }

    public static PRSW getPRSW() {
        PRSW prsw = new PRSW();
        prsw.setDocumentName(CertificateTypes.WELSH_PRS.getCertificateType());
        prsw.setData(getVt20W().getData());
        prsw.setFailData(getVt30W().getFailData());
        return prsw;
    }

    public static VT32VE getVt32VeW() {
        VT32VE vt32vew = new VT32VE();
        vt32vew.setDocumentName(CertificateTypes.WELSH_ADVISORY_NOTICE.getCertificateType());
        MotFailCertificateDataWelsh vt32vewData = new MotFailCertificateDataWelsh();
        vt32vewData
                .setDangerousDefectsHeader(DefectSummaryComponent.DANGEROUS_DEFECTS_HEADER_TEXT)
                .setEuDangerousDefects(generateRFRs(DANGEROUS_RFR_TEXT, 2))

                .setMajorDefectsHeader(DefectSummaryComponent.MAJOR_DEFECTS_HEADER_TEXT)
                .setEuMajorDefects(generateRFRs(MAJOR_RFR_TEXT, 4))

                .setMinorDefectsHeader(DefectSummaryComponent.MINOR_DEFECTS_HEADER_TEXT)
                .setEuMinorDefects(generateRFRs(MINOR_RFR_TEXT, 5))

                .setAdvisoryDefectsHeader(DefectSummaryComponent.ADVISORIES_HEADER_TEXT)
                .setEuAdvisoryDefects(generateRFRs(ADVISORY_RFR_TEXT, 3))

                .setCountryOfRegistrationCode("GB")
                .setRawVin(VIN)
                .setIssuedDate("2017-10-12")
                .setDateOfTheTest(LocalDate.of(2017, 10, 12))
                .setExpiryDate("12.10.2018")
                .setFirstUseDate("2010-10-12")
                .setTestStation("VTS004004")
                .setTestStationAddress("VTS004004 53, Call Road, Worthing BN12 6PB, United Kingdom")
                .setMake("Aston Martin")
                .setModel("DB11")
                .setTestClass("4")
                .setVehicleEuClassification("M1")
                .setRawVrm("KA1SAPH")
                .setOdometer("22341 km")
                .setCurrentOdometer(
                        new OdometerReading("22341", "km", LocalDate.now())
                )
                .setIssuersName("R.DREWNO")
                .setTestStationName("POPULAR GARAGES")
                .setInspectionAuthority("POPULAR GARAGES R.DREWNO")
                .setAuthorisedExaminer("MILKE GROUP LIMITED")
                .setTestNumber("1806 8140 0628")
                .setEarliestDateOfTheNextTest(LocalDate.of(2018, 10, 13))
                .setInspectionAuthorityLines(Arrays.asList(
                    INSP_AUTHORITY_DVSA,
                    "Telephone number - 03001239000"
                ));
        vt32vew.setFailData(vt32vewData);
        return vt32vew;
    }

    public static VT32VE getVt32Ve() {
        VT32VE vt32ve = new VT32VE();
        vt32ve.setDocumentName(CertificateTypes.ADVISORY_NOTICE.getCertificateType());
        vt32ve.setFailData(getVt32VeW().getFailData());
        return vt32ve;
    }

    public static VT32VE getEuVt32Ve() {
        VT32VE euVt32ve = new VT32VE();
        euVt32ve.setDocumentName(CertificateTypes.COMPLIANCE_ADVISORY_NOTICE.getCertificateType());
        euVt32ve.setFailData(getVt32Ve().getFailData());
        return euVt32ve;
    }

    public static VT32VE getEuVt32VeW() {
        VT32VE euVt32veW = new VT32VE();
        euVt32veW.setDocumentName(CertificateTypes.COMPLIANCE_WELSH_ADVISORY_NOTICE.getCertificateType());
        euVt32veW.setFailData(getVt32VeW().getFailData());
        return euVt32veW;
    }

    public static VT32VE getEuVt32VeWelshWithOverflow() {
        VT32VE euVt32veWelshWithOverflow = new VT32VE();
        euVt32veWelshWithOverflow.setDocumentName(CertificateTypes.COMPLIANCE_WELSH_ADVISORY_NOTICE.getCertificateType());
        euVt32veWelshWithOverflow.setFailData(getVt32VeW().getFailData());
        euVt32veWelshWithOverflow.getFailData()
                .setEuDangerousDefects(generateRFRs(DANGEROUS_RFR_TEXT, 10))
                .setEuMajorDefects(generateRFRs(MAJOR_RFR_TEXT, 10))
                .setEuMinorDefects(generateRFRs(MINOR_RFR_TEXT, 15))
                .setEuAdvisoryDefects(generateRFRs(ADVISORY_RFR_TEXT, 10));
        return euVt32veWelshWithOverflow;
    }

    public static Collection<Object[]> getCertificatesTestData() {
        return Arrays.asList(new Object[][] {
                { getVt20(), getVt20().getData() },
                { getVt30(), getVt30().getFailData() },
                { getVt30WithOverflownRFRs(), getVt30WithOverflownRFRs().getFailData() }
        });
    }

    public static Collection<Object[]> getVT32VECertificatesTestData() {
        return Arrays.asList(new Object[][] {
                { getVt32Ve()},
                { getVt32VeW() },
                { getEuVt32Ve() },
                { getEuVt32VeW() }
        });
    }

    private static List<String> generateRFRs(String rfrName, int numberOfRfrs) {
        List<String> reasonsForRejection = new ArrayList<>();

        for (int i = 0; i < numberOfRfrs; i++) {
            reasonsForRejection.add(rfrName + " #" + i); }
        return reasonsForRejection;
    }
}
