package htmlverification.service;

import htmlverification.framework.component.DefectSummaryComponent;
import uk.gov.dvsa.model.cvs.AdrPassCertificate;
import uk.gov.dvsa.model.cvs.LecCertificate;
import uk.gov.dvsa.model.cvs.certificateData.*;
import uk.gov.dvsa.model.mot.*;
import uk.gov.dvsa.model.mot.certificateData.MotCertificateData;
import uk.gov.dvsa.model.mot.certificateData.MotCertificateDataWelsh;
import uk.gov.dvsa.model.mot.certificateData.MotFailCertificateData;
import uk.gov.dvsa.model.mot.certificateData.MotFailCertificateDataWelsh;
import uk.gov.dvsa.model.mot.certificateData.OdometerReading;
import uk.gov.dvsa.model.mot.enums.CertificateTypes;

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
        vt20.setDocumentName(CertificateTypes.PASS.getType());
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

    public static AdrPassCertificate getAdrPass() {
        AdrPassCertificate adrPassCertificate = new AdrPassCertificate();
        adrPassCertificate.setDocumentName(CertificateTypes.ADR_PASS.getType());
        AdrPassCertificateData adrPassCertificateData = new AdrPassCertificateData();

        adrPassCertificateData.setApplicantDetails( new ApplicantDetails("applicantDetailsName", "applicantDetailsStreet", "applicantDetailsTown", "applicantDetailsCity", "applicantDetailsPostCode"))
                .setMake("demoMake").setModel("demoModel").setAtfNameAtfPNumber("demoAtfNameAtfPnumber").setBrakeEndurance(true).setChassisNumber("demoChassisNumber")
                .setExpiryDate("10-12-2011").setPermittedDangerousGoods(new String[]{"\"FP <61 (FL)\"", "Explosives (type 2)", "Explosives (type 3)"})
                .setRegistrationNumber("demoRegistrationNumber").setSpecialProvisions("demoSpecialProvisions").setTankCode("demoTankCode").setTankManufacturer("demoTankManufacturer")
                .setTankManufactureSerialNo("demoTankManufacturerSerialNo").setTankStatement(new TankStatement("Substances permitted under the tank code and any special provisions specified in 9 may be carried", "demoStatement", null))
                .setTc2InitApprovalNo("demoTc2InitApprovalNo").setVehicleType("demoVehicleType").setWeight("demoWeight").setYearOfManufacture("1950");
        adrPassCertificate.setAdrData(adrPassCertificateData);
        return adrPassCertificate;
    }

    public static LecCertificate getLecPass() {
        LecCertificate cert = new LecCertificate();
        cert.setDocumentName(CertificateTypes.LEC_PASS.getType());

        LecCertificateData certData = new LecCertificateData();
        certData.setMake("DragonMobile");
        certData.setModel("Hoard");
        certData.setSerialNumber("ABC1233456");
        certData.setExpiryDate("01-01-2020");
        certData.setVrm("MM01MMM");
        certData.setVin("123456789");
        certData.setPrescribedEmissionStandard("Low");
        certData.setParticulateTrapFitted("DragonTrap");
        certData.setParticulateTrapSerialNumber("DG12345");
        certData.setModificationTypeUsed("DragonMod");
        certData.setModificationType("G");
        certData.setSmokeTestLimit("5");
        certData.setTestDate("04/04/2020");
        certData.setTestStationName("MyTest Station");
        certData.setTestStationPNumber("11223344");
        cert.setSignature(getSignature());
        cert.setLecData(certData);
        return cert;
    }

    public static LecCertificate getLecFail() {
        LecCertificate cert = new LecCertificate();
        cert.setDocumentName(CertificateTypes.LEC_FAIL.getType());

        LecCertificateData certData = new LecCertificateData();
        certData.setMake("DragonMobile");
        certData.setModel("Hoard");
        certData.setSerialNumber("ABC123456");
        certData.setExpiryDate("01-01-2020");
        certData.setVrm("MM01MMM");
        certData.setVin("123456789");
        certData.setTestDate("04/04/2020");
        certData.setTestStationName("MyTest Station");
        certData.setTestStationPNumber("11223344");
        certData.setAdditionalNotes("Look, something broke! here's a really long line to show how it splits things over multiple lines when it gets too long!!! I really hope this is long enough or I'll have to keep making stuff up.");
        cert.setSignature(getSignature());
        cert.setLecData(certData);
        return cert;
    }

    private static Signature getSignature() {
        Signature signature = new Signature();
        signature.setImageType("png");
        signature.setImageData("iVBORw0KGgoAAAANSUhEUgAAAr0AAADvCAYAAAD//WwNAAAAAXNSR0IArs4c6QAAGERJREFUeAHt3V3IZOV9APDdTXbrGvOltrt+NItZgm6jYpTSqhe7m5S0lBYp0tqbSiw0eFPxygsJ7BsKpUH6cdOWWG9CqXe9b2mLhIC6xSbVFLPCCkkglBYt1EIvomv6f8qOe/z7nHfmnXln5jzn/A48znnO5///O/PO/J09HwcOGAgQIECAAAECBAgQIECAAAECBAgQIECAAAECBAgQIECAAAECBAgQIECAAAECBAgQIECAAAECBAgQIECAAAECBAgQIECAAAECBAgQIECAAAECBAgQIECAAAECBAgQIECAAAECBAgQIECAAAECBAgQIECAAAECBAgQIECAAAECBAgQIECAAAECBAgQIECAAAECBAgQIECAAAECBAgQIECAAAECBAgQIECAAAECBAgQIECAAAECBAgQIECAAAECBAgQIECAAAECBAgQIECAAAECBAgQIECAAAECBAgQIECAAAECBAgQIECAAAECBAgQIECAAAECBAgQIECAAAECBAgQIECAAAECBAgQIECAAAECBAgQIECAAAECBAgQIECAAAECBAgQIECAAAECBAgQIECAAAECBAgQIECAAAECBAgQIECAAAECBAgQIECAAAECBAgQIECAAAECBAgQIECAAAECBAgQIECAAAECBAgQIECAAAECBAgQIECAAAECBAgQIECAAAECBAgQIECAAAECBAgQIECAAAECBAgQIECAAAECBAgQIECAAAECBAgQIECAAAECBAgQIECAAAECBAgQIECAAAECBAgQIECAAAECBAgQIECAwNAFjkSAd0W7ZuiBio8AAQIECBAgQIDAMgInYqUfRPvJ5faFZTZiHQIECBAgQIAAAQJDFvhaBDcreGevnxpywGIjQIAAAQIECLQqcKjVwEcad/nl9+BIc5MWAQIECBAgQIDABAXK6Q2zX3i7r38zQQspEyBAgAABAgQIjFjgicitW/DOxj854pylRoAAAQIECBAgMEGBdyLnWbHbfXWawwTfDFImQIAAAQIECIxVoJxX/W/RugVvGf/2WBOWFwECBAgQIECAwDQFPhRp56K39H9lmhyyJkCAAAECBAgQGKvAzZFYLnzfjWnO7x3rEZcXAQIECBAgQGCiAo9G3rnwLX3n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");
        return signature;
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
        vt30.setDocumentName(CertificateTypes.FAIL.getType());
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
        vt30WithOverflownRFRs.setDocumentName(CertificateTypes.FAIL.getType());
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
        vt20W.setDocumentName(CertificateTypes.WELSH_PASS.getType());
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
        vt30W.setDocumentName(CertificateTypes.WELSH_FAIL.getType());
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
        ct20.setDocumentName(CertificateTypes.CONTINGENCY_PASS.getType());
        ct20.setVts(VTS_ID).setInspectionAuthority(INSPECTION_AUTHORITY);
        return ct20;
    }

    public static CT30 getCt30() {
        CT30 ct30 = new CT30();
        ct30.setDocumentName(CertificateTypes.CONTINGENCY_FAIL.getType());
        ct30.setVts(VTS_ID).setInspectionAuthority(INSPECTION_AUTHORITY);
        return ct30;
    }

    public static CT32 getCt32() {
        CT32 ct32 = new CT32();
        ct32.setDocumentName(CertificateTypes.CONTINGENCY_ADVISORY_NOTICE.getType());
        ct32.setVts(VTS_ID).setInspectionAuthority(INSPECTION_AUTHORITY);
        return ct32;
    }

    public static CT20 getEuCt20() {
        CT20 ct20 = new CT20();
        ct20.setDocumentName(CertificateTypes.EU_CONTINGENCY_PASS.getType());
        ct20.setVts(VTS_ID).setInspectionAuthority(INSPECTION_AUTHORITY);
        return ct20;
    }

    public static CT30 getEuCt30() {
        CT30 ct30 = new CT30();
        ct30.setDocumentName(CertificateTypes.EU_CONTINGENCY_FAIL.getType());
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
        prs.setDocumentName(CertificateTypes.PRS.getType());
        prs.setData(getVt20().getData());
        prs.setFailData(getVt30().getFailData());
        return prs;
    }

    public static PRSW getPRSW() {
        PRSW prsw = new PRSW();
        prsw.setDocumentName(CertificateTypes.WELSH_PRS.getType());
        prsw.setData(getVt20W().getData());
        prsw.setFailData(getVt30W().getFailData());
        return prsw;
    }

    public static VT32VE getVt32VeW() {
        VT32VE vt32vew = new VT32VE();
        vt32vew.setDocumentName(CertificateTypes.WELSH_ADVISORY_NOTICE.getType());
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
        vt32ve.setDocumentName(CertificateTypes.ADVISORY_NOTICE.getType());
        vt32ve.setFailData(getVt32VeW().getFailData());
        return vt32ve;
    }

    public static VT32VE getEuVt32Ve() {
        VT32VE euVt32ve = new VT32VE();
        euVt32ve.setDocumentName(CertificateTypes.COMPLIANCE_ADVISORY_NOTICE.getType());
        euVt32ve.setFailData(getVt32Ve().getFailData());
        return euVt32ve;
    }

    public static VT32VE getEuVt32VeW() {
        VT32VE euVt32veW = new VT32VE();
        euVt32veW.setDocumentName(CertificateTypes.COMPLIANCE_WELSH_ADVISORY_NOTICE.getType());
        euVt32veW.setFailData(getVt32VeW().getFailData());
        return euVt32veW;
    }

    public static VT32VE getEuVt32VeWelshWithOverflow() {
        VT32VE euVt32veWelshWithOverflow = new VT32VE();
        euVt32veWelshWithOverflow.setDocumentName(CertificateTypes.COMPLIANCE_WELSH_ADVISORY_NOTICE.getType());
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
