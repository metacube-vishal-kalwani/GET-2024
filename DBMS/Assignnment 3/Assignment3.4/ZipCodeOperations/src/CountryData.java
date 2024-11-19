public class CountryData {

  private String stateName;
  private String cityName;
  private String zipCode;

  CountryData(String state, String city, String zipCode) {
    this.stateName = state;
    this.cityName = city;
    this.zipCode = zipCode;
  }

  public String getCityName() {
    return cityName;
  }

  public String getStateName() {
    return stateName;
  }

  public String getZipCode() {
    return zipCode;
  }
}
