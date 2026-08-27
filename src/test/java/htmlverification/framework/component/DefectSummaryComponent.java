package htmlverification.framework.component;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class DefectSummaryComponent {
    public static final String RESULT_ITEM_CLASS = "results__item";

    public static final Integer RESULT_NAME_INDEX = 0;
    public static final String  MAJOR_DEFECTS_HEADER_TEXT = "Repair immediately (major defects)";
    public static final String  MINOR_DEFECTS_HEADER_TEXT = "Repair as soon as possible (minor defects)";
    public static final String  ADVISORIES_HEADER_TEXT = "Monitor and repair if necessary (advisories)";
    public static final String  DANGEROUS_DEFECTS_HEADER_TEXT = "Do not drive until repaired (dangerous defects)";
    public static final String  ADVISORIES_HEADER_TEXT_WELSH = "Monitro a thrwsio os oes angen (cynghorol)";
    public static final String ADVISORIES_HEADER_TEXT_WELSH_CVS = "Monitro a thrwsio os oes angen (cynghorol)";
    public static final String  MINOR_DEFECTS_HEADER_TEXT_WELSH = "Rhaid trwsio cyn gynted â phosibl (mân ddiffygion)";
    public static final String MINOR_DEFECTS_HEADER_TEXT_WELSH_CVS = "Atgyweiriwch cyn gynted â phosibl (ddiffygion bach)";
    public static final String  MAJOR_DEFECTS_HEADER_TEXT_WELSH = "Rhaid trwsio ar unwaith (diffygion pennaf)";
    public static final String MAJOR_DEFECTS_HEADER_TEXT_WELSH_CVS = "Atgyweirio ar unwaith (diffygion mawr)";
    public static final String  DANGEROUS_DEFECTS_HEADER_PARTIAL_TEXT_WELSH = "Peidiwch â gyrru nes iddo gael ei drwsio (diffygion peryglus)";
    public static final String DANGEROUS_DEFECTS_HEADER_PARTIAL_TEXT_WELSH_CVS = "Peidiwch â gyrru nes ei fod wedi cael ei atgyweirio (diffygion peryglus)";
    public static final String DANGEROUS_DEFECTS_HEADER_TEXT_WELSH_CVS = "Peidiwch â gyrru nes ei fod wedi cael ei atgyweirio";
    public static final String PRS_DEFECTS_HEADER_TEXT_WELSH_CVS = "Diffygion wedi'u cywiro ar adeg y prawf";
    //CVS only
    public static final String  PRS_DEFECTS_HEADER_TEXT = "Monitor and repair if necessary (advisories)";

    protected Element resultSummaryElement;

    public DefectSummaryComponent(Element resultSummaryElement) {
        this.resultSummaryElement = resultSummaryElement;
    }

    public Elements getResultItems() {
        return resultSummaryElement.getElementsByClass(RESULT_ITEM_CLASS);
    }

    public Element getResultNameItem() {
        return getResultItems().get(RESULT_NAME_INDEX);
    }

    public Elements getMajorDefects() {
        return getResultItemEntries(MAJOR_DEFECTS_HEADER_TEXT);
    }

    public Elements getMinorDefects() {
        return getResultItemEntries(MINOR_DEFECTS_HEADER_TEXT);
    }

    public Elements getAdvisories() {
        return getResultItemEntries(ADVISORIES_HEADER_TEXT);
    }

    public Elements getDangerousDefects() {
        return getResultItemEntries(DANGEROUS_DEFECTS_HEADER_TEXT);
    }

    public Elements getMinorDefectsWelsh() {
        return getResultItemEntries(MINOR_DEFECTS_HEADER_TEXT_WELSH);
    }

    public Elements getAdvisoriesWelsh() {
        return getResultItemEntries(ADVISORIES_HEADER_TEXT_WELSH);
    }

    public Elements getMinorDefectsWelshCVS() {
        return getResultItemEntries(MINOR_DEFECTS_HEADER_TEXT_WELSH_CVS);
    }

    public Elements getAdvisoriesWelshCVS() {
        return getResultItemEntries(ADVISORIES_HEADER_TEXT_WELSH_CVS);
    }

    public Elements getByTitle(String title) {
        return getResultItemEntries(title);
    }

    public Elements getMajorDefectsWelsh() {
        return getResultItemEntries(MAJOR_DEFECTS_HEADER_TEXT_WELSH);
    }

    public Elements getMajorDefectsWelshCVS() {
        return getResultItemEntries(MAJOR_DEFECTS_HEADER_TEXT_WELSH_CVS);
    }

    public Elements getDangerousDefectsWelsh() {
        return getResultItemEntries(DANGEROUS_DEFECTS_HEADER_PARTIAL_TEXT_WELSH);
    }

    public Elements getDangerousDefectsWelshCVS() {
        return getResultItemEntries(DANGEROUS_DEFECTS_HEADER_PARTIAL_TEXT_WELSH_CVS);
    }

    //CVS only
    public Elements getPrsDefects() {
        return getResultItemEntries(PRS_DEFECTS_HEADER_TEXT);
    }

    protected Elements getResultItemEntries(String resultItemHeader) {
        return getResultItems().stream()
                .filter(i ->  i.getElementsContainingOwnText(resultItemHeader).size() > 0)
                .findFirst()
                .orElseThrow(() -> new RuntimeException(String.format("The result summary does not contain element with text: '%s'", resultItemHeader)))
                .getElementsByTag("li");
    }

    public boolean isDefectsHeaderPresent(String header) {
        return getResultItems().stream().anyMatch(i -> i.getElementsContainingOwnText(header).size() > 0);
    }
}
