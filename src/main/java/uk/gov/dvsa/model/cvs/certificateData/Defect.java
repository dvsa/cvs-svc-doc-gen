package uk.gov.dvsa.model.cvs.certificateData;

public class Defect {
    private final String[] defects;
    private final boolean isFirst;

    public Defect(String[] _defect, boolean _isFirst) {
        this.defects = _defect;
        this.isFirst = _isFirst;
    }
    public String[] getDefects() {
        return defects;
    }

    public boolean getDefectsExist() {
        return this.defects.length > 0;
    }

    public boolean getDefectsIsFirst() {
        return isFirst;
    }
}
