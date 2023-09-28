package model;

/** This is the Customer.java class. */
public class Customer {
    private int customerId;
    private String customerName;
    private String address;
    private String postalCode;
    private String phoneNumber;
    private int divisionId;
    public Customer(int customerId, String customerName, String address, String postalCode, String phoneNumber, int divisionId){
        this.customerId = customerId;
        this.customerName = customerName;
        this.address = address;
        this.postalCode = postalCode;
        this.phoneNumber = phoneNumber;
        this.divisionId = divisionId;
    }

    /**
     * @return the customerIdCol
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * @param customerId the customerIdCol to set
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /**
     * @return the customerNameCol
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * @param customerName the customerNameCol to set
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * @return the addressCol
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the addressCol to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the postalCodeCol
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * @param postalCode the postalCodeCol to set
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * @return the phoneNumberCol
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * @param phoneNumber the phoneNumberCol to set
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

}
