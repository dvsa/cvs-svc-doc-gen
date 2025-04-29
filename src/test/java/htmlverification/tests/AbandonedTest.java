package htmlverification.tests;

import htmlverification.framework.page_object.CertificatePageObject;
import org.junit.Test;
import uk.gov.dvsa.model.cvs.AbandonedCertificate;
import uk.gov.dvsa.service.HtmlGenerator;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public abstract class AbandonedTest {

    protected String DYNAMIC_TITLE_SECTION;
    protected String REGULATION_TEXT;
    protected String VEHICLE_TYPE_TEXT_LINE;
    protected String FORM_NUMBER;
    protected String SECTION_REF_TEXT;
    protected static String DEFECT_TITLE = "Defects added during test:";


    protected HtmlGenerator htmlGenerator;
    protected AbandonedCertificate testCertificate;
    protected CertificatePageObject certificatePageObject;

    @Test
    public void verifyValuesSetByConstructor() {
        assertEquals(REGULATION_TEXT, testCertificate.getRegulationText());
        assertEquals(VEHICLE_TYPE_TEXT_LINE, testCertificate.getVehicleTypeText());
        assertEquals(DYNAMIC_TITLE_SECTION, testCertificate.getTitleTextIncludingRollingHeaders());
        assertEquals(FORM_NUMBER, testCertificate.getFormNumber());
        assertEquals(SECTION_REF_TEXT, testCertificate.getSectionTextRef());
    }

    @Test
    public void verifyDocumentType() {
        assertEquals(testCertificate.getDocumentType(), certificatePageObject.getDocumentType());
    }

    @Test
    public void verifyFirstPageTitle() {
        String firstPageTitle = certificatePageObject.getFirstPageTitle();
        assertEquals("Notification of Failure to Comply with the Conditions of Acceptance of a " +
                testCertificate.getTitleTextIncludingRollingHeaders() + " (" + testCertificate.getDocumentType() + ")", firstPageTitle);
    }

    @Test
    public void verifyRegulationText() {
        assertEquals(testCertificate.getRegulationText(), certificatePageObject.getRegulationText());
    }

    @Test
    public void verifyFooterDocumentType() {
        assertEquals(testCertificate.getDocumentType(), certificatePageObject.getFooterDocumentType());
    }

    @Test
    public void verifyFooterTestNumber() {
        assertEquals("(DVSA" + testCertificate.getFormNumber() + ")", certificatePageObject.getFooterTestNumber());
    }

    @Test
    public void verifyVehicleTypeText() {
        assertEquals(testCertificate.getVehicleTypeText(), certificatePageObject.getVehicleTestType());
    }

    @Test
    public void verifyRegistrationNumber() {
        String[] reg = testCertificate.getData().getRegistrationNumber();
        for (int i = 0; i < reg.length; i++) {
            assertEquals(reg[i], certificatePageObject.getSpacedRegistrationNumber(i));
        }
    }

    @Test
    public void verifySectionText() {
        assertTrue(certificatePageObject.getSanctionText().contains(testCertificate.getSectionTextRef()));
    }

    @Test
    public void verifyRunningHeaderLeft() {
        assertEquals(testCertificate.getDocumentType(), certificatePageObject.getRunningHeaderLeft());
    }

    @Test
    public void verifyRunningHeaderRight() {
        assertEquals("Notification of Failure to Comply with the Conditions of Acceptance of a " +
                testCertificate.getTitleTextIncludingRollingHeaders(), certificatePageObject.getRunningHeaderRight());
    }

    @Test
    public void verifyReasonsForAbandonment() {
        String[] reasonsForAbandonment = testCertificate.getData().getReasonsForRefusal();
        for (int i = 0; i < reasonsForAbandonment.length; i++) {
            assertEquals(reasonsForAbandonment[i], certificatePageObject.getReasonsForAbandonment(i));
        }
    }

    @Test
    public void verifyAdditionalComments() {
        assertEquals(testCertificate.getData().getAdditionalComments(), certificatePageObject.getAdditionalComments());
    }

    @Test
    public void verifyDataProtectionWithDocumentType() {
        assertEquals("We collect, use and store your personal data so that we can correctly issue your vehicle with a " + testCertificate.getDocumentType() + " failure notification." , certificatePageObject.getDataProtectionWithDocumentType());
    }

    @Test
    public void verifySignature() {
        assertEquals(testCertificate.getSignature().getFormattedImageData(), certificatePageObject.getSignature());
    }

    @Test
    public void verifyPrintName() {
        assertEquals(testCertificate.getData().getIssuersName(), certificatePageObject.getPrintName());
    }

    @Test
    public void verifyLocation() {
        assertEquals(testCertificate.getData().getTestStationName(), certificatePageObject.getLocation());
    }

    @Test
    public void verifyLocationNumber() {
        assertEquals(testCertificate.getData().getTestStationPNumber(), certificatePageObject.getLocationNumber());
    }

    @Test
    public void verifyDateOfTheTest() {
        assertEquals(testCertificate.getData().getDateOfTheTest(), certificatePageObject.getDateOfTest());
    }

    @Test
    public void verifyDefectsTitle() {
        assertEquals(AbandonedTest.DEFECT_TITLE, certificatePageObject.getDefectsHeader());
    }

    @Test
    public void verifyDefectDangerous() {
        String[] dangerousDefects = testCertificate.getData().getDefects().getDangerousDefects().getDefects();
        for (int i = 0; i < dangerousDefects.length ; i++) {
            assertEquals(dangerousDefects[i], certificatePageObject.getDefectDangerous(i));
        }
    }

    @Test
    public void verifyDefectMajor() {
        String[] majorDefects = testCertificate.getData().getDefects().getMajorDefects().getDefects();
        for (int i = 0; i < majorDefects.length ; i++) {
            assertEquals(majorDefects[i], certificatePageObject.getDefectMajor(i));
        }
    }

    @Test
    public void verifyDefectMinor() {
        String[] minorDefects = testCertificate.getData().getDefects().getMinorDefects().getDefects();
        for (int i = 0; i < minorDefects.length ; i++) {
            assertEquals(minorDefects[i], certificatePageObject.getDefectMinor(i));
        }
    }

    @Test
    public void verifyDefectAdvisory() {
        String[] advisoryDefects = testCertificate.getData().getDefects().getAdvisoryDefects().getDefects();
        for (int i = 0; i < advisoryDefects.length ; i++) {
            assertEquals(advisoryDefects[i], certificatePageObject.getDefectAdvisory(i));
        }
    }

    @Test
    public void verifyDefectPRS() {
        String[] pRSDefects = testCertificate.getData().getDefects().getPRSDefects().getDefects();
        for (int i = 0; i < pRSDefects.length ; i++) {
            assertEquals(pRSDefects[i], certificatePageObject.getDefectPrs(i));
        }
    }
}
