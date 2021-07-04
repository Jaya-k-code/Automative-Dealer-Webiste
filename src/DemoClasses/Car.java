package DemoClasses;

import java.util.List;
import java.util.ArrayList;

public class Car {
	public static final String TYPE_SUV = "SUV";
	public static final String TYPE_SEDAN = "Sedan";
	public static final String TYPE_TRUCK = "Truck";
	public String make;
	public String model;
	public String type;
	
	public Car(String make, String model, String type) {
		this.make = make;
		this.model = model;
		this.type = type;
	}
	
	public Car() {
		
	}
	
	
	public List<Car> initialize(){
		ArrayList<Car> carList = new ArrayList<>();
		
		carList.add(new Car("Toyota","Camry",TYPE_SEDAN));
		carList.add(new Car("Toyota","High Lander",TYPE_SUV));
		carList.add(new Car("Toyota","Avalon",TYPE_SEDAN));
		carList.add(new Car("Toyota","Corolla",TYPE_SEDAN));
		carList.add(new Car("Toyota","RAV4",TYPE_SUV));
		carList.add(new Car("Toyota","Tundra",TYPE_TRUCK));
		carList.add(new Car("Toyota","Tacoma",TYPE_TRUCK));
		
		carList.add(new Car("Subaru","Forester",TYPE_SUV));
		carList.add(new Car("Subaru","Impreza",TYPE_SEDAN));
		carList.add(new Car("Subaru","Crosstrek",TYPE_SUV));
		
		carList.add(new Car("Honda","HRV",TYPE_SUV));
		carList.add(new Car("Honda","CRV",TYPE_SUV));
		carList.add(new Car("Honda","Civic",TYPE_SEDAN));
		carList.add(new Car("Honda","Accord",TYPE_SEDAN));
		
		carList.add(new Car("Nissan","Altima",TYPE_SEDAN));
		carList.add(new Car("Nissan","Maxima",TYPE_SEDAN));
		carList.add(new Car("Nissan","Versa",TYPE_SEDAN));
		carList.add(new Car("Nissan","Murano",TYPE_SUV));
		carList.add(new Car("Nissan","Pathfinder",TYPE_SUV));
		
		carList.add(new Car("Ford","Fusion",TYPE_SEDAN));
		carList.add(new Car("Ford","Explorer",TYPE_SUV));
		carList.add(new Car("Ford","Expedite",TYPE_SUV));
		
		return carList;
	}
	public String getMake() {
		return this.make;
	}
	
	public String getModel() {
		return this.model;
	}
	
	public String getType() {
		return this.type;
	}
}
