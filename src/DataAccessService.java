import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DataAccessService {

    public List<VehicleDetails> findAllVehicles() {
        DBConnector dbConnector = new DBConnector();
        try {
            return mapResultSetToVehicleDetails(dbConnector.getAllData());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public List<VehicleDetails> findVehiclesBySearchTerm(String searchTerm) {
        DBConnector dbConnector = new DBConnector();
        List<VehicleDetails> answer = new ArrayList<>();

        try {
            ResultSet resultOne = dbConnector.findVehicleDetailsBySearchTermInColumn(searchTerm, "BRAND");
            ResultSet resultTwo = dbConnector.findVehicleDetailsBySearchTermInColumn(searchTerm, "COLOR");
            ResultSet resultThree = dbConnector.findVehicleDetailsBySearchTermInColumn(searchTerm, "MAKE");
            // we can add more columns here
            answer.addAll(mapResultSetToVehicleDetails(resultOne));
            answer.addAll(mapResultSetToVehicleDetails(resultTwo));
            answer.addAll(mapResultSetToVehicleDetails(resultThree));
        } catch (SQLException e) {
            System.out.println("error " + e.getMessage());
        }

        return answer;
    }

    public List<VehicleDetails> findVehicleDetailsByBrand(String brand) {
        // run a query on db
        DBConnector dbConnector = new DBConnector();

        try {
            ResultSet result = dbConnector.findVehicleDetailsBySearchTermInColumn(brand, "BRAND");
            return mapResultSetToVehicleDetails(result);
        } catch (SQLException e) {
            System.out.println("error " + e.getMessage());
        }
//
//        return mockVehicleDetails()
//                .stream()
//                .filter(v -> v.getBrand().equals(brand))
//                .collect(Collectors.toList());

        return new ArrayList<>();
    }

    // mapper for db to vehicle details class
    private List<VehicleDetails> mapResultSetToVehicleDetails(ResultSet set) {
        try {
            List<VehicleDetails> ans = (List<VehicleDetails>) set.getObject(0, List.class);
        } catch (SQLException e) {
            System.out.println("Could not read result set " + e.getMessage());
        }
        return new ArrayList<>();
    }

    // mock db access for now
    private List<VehicleDetails> mockVehicleDetails() {
        List<VehicleDetails> resultSet = new ArrayList<>();
        VehicleDetails one = new VehicleDetails();
        one.setBrand("Mercedes-Benz");
        one.setColor("Black");
        one.setCategory("used");
        one.setModel("EQS");


        VehicleDetails two = new VehicleDetails();
        two.setBrand("Tesla");
        two.setColor("White");
        two.setCategory("new");
        two.setModel("Model-X");

        VehicleDetails three = new VehicleDetails();
        three.setBrand("BMW");
        three.setColor("Grey");
        three.setCategory("new");
        three.setModel("X-5");

        resultSet.add(one);
        resultSet.add(two);
        resultSet.add(three);

        return resultSet;
    }
}
