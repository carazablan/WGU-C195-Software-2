package model;

/** This is the CountryFirstDiv.java class. */
public class CountryFirstDiv {
    private int divisionId;
    private String division;
    private int countryId;
    public CountryFirstDiv(int divisionId, String division, int countryId){
        this.divisionId = divisionId;
        this.division = division;
        this.countryId = countryId;
    }

    /**
     * @return the divisionIdCol
     */
    public int getDivisionId() {
        return divisionId;
    }

    /**
     * @param divisionId the divisionIdCol to set
     */
    public void setDivisionId(int divisionId) {
        this.divisionId = divisionId;
    }

    /**
     * @return the divisionCol
     */
    public String getDivision() {
        return division;
    }

    /**
     * @param division the divisionCol to set
     */
    public void setDivision(String division) {
        this.division = division;
    }

    /**
     * @return the countryIdCol
     */
    public int getCountryId() {
        return countryId;
    }

    /**
     * @param countryId the countryIdCol to set
     */
    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

}
