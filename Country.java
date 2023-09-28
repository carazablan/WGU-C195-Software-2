package model;

/** This is the Country.java class. */
public class Country{
    private int countryId;
    private String country;
    public Country(int countryId, String country){
        this.countryId = countryId;
        this.country = country;
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

    /**
     * @return the countryCol
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country the countryCol to set
     */
    public void setCountry(String country) {
        this.country = country;
    }

}
