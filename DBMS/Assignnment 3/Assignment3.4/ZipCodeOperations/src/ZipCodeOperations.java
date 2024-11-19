import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NameNotFoundException;

/**
 * This class build the connection between the storeFront Database and performs
 * query to get
 * zipcode operations
 */
class ZipCodeOperations {
    private static String HOST = "jdbc:mysql://localhost:3306/"; // Host port
    private static String DB_NAME = "StoreFront"; // database name
    private static String MY_SQL_URL = HOST + DB_NAME; // URL of Database for jdbc connectivity
    public static Connection connection; //

    /**
     * Constructor to builds the connection between application and database drivers
     */
    ZipCodeOperations() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String userId = Credentials.getUSER_NAME();
            String password = Credentials.getPASSWORD();

            connection = DriverManager.getConnection(MY_SQL_URL, userId, password);

        } catch (ClassNotFoundException cnfe) {
            System.out.println("Error loading driver: " + cnfe);
        } catch (SQLException sqlError) {
            System.out.println(sqlError);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * 
     * @return returns the list of country's data i.e zipcode , city and
     *         corresponding state
     * @throws Exception throws sql exception
     */
    public static List<CountryData> getCountryData() throws Exception {
        Statement statement = connection.createStatement();
        List<CountryData> citiesList = new ArrayList<>();

        // query to fetch the data of zipcode and city and state in ordered manner
        String query = "SELECT S.state_name,  C1.city_name, Z.zip_code " +
                "FROM Zipcodes Z " +
                "JOIN Cities C1 ON Z.city_id = C1.city_id " +
                "JOIN States S ON C1.state_id = S.state_id " +
                "ORDER BY S.state_name, C1.city_name; ";

        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            String stateName = resultSet.getString("state_name");
            String cityName = resultSet.getString("city_name");
            String zipcode = resultSet.getString("zip_code");
            CountryData countryData = new CountryData(stateName, cityName, zipcode);
            citiesList.add(countryData);
        }

        return citiesList;
    }

    public static void main(String[] args) {

        ZipCodeOperations zipCodeOperations = new ZipCodeOperations();
        try {
            // function call to get the countries data
            List<CountryData> cityList = getCountryData();
            System.out.println("ZipCode" + "\t\t City" + "\t\t State");
            for (CountryData data : cityList) {
                System.out.println(data.getZipCode()
                        + "\t\t" + data.getCityName()
                        + "\t\t" + data.getStateName());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}