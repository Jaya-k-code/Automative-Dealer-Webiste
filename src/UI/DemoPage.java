package UI;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Panel;
import javax.swing.JSplitPane;
import javax.swing.JLabel;
import java.awt.GridLayout;
import DemoClasses.Car;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.SwingConstants;
public class DemoPage {

	private JFrame frame;
	private JTextField searchText;
	private List<Car> carList;
	private List<Car> list_display;
	private int pageCount = 1;
	private final int MAX_ITEMS = 15;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DemoPage window = new DemoPage();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DemoPage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		// Test list for Cars
		carList = createCarList();

		//
		
		frame = new JFrame();
		frame.setBounds(100, 100, 700, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(6, 6, 688, 566);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		searchText = new JTextField();
		searchText.setBounds(127, 17, 247, 26);
		panel.add(searchText);
		searchText.setColumns(10);
		
		JPanel exhibitPanel = new JPanel();
		exhibitPanel.setBounds(31, 87, 592, 408);
		panel.add(exhibitPanel);
		exhibitPanel.setLayout(new GridLayout(0, 2, 0, 0));
		//Initialize the exhibitPanel with given limitation for items on each page
		initializePanel(exhibitPanel,MAX_ITEMS);
		
		// Search by user entering text
		JButton searchBtn = new JButton("Search");
		searchBtn.setBackground(Color.WHITE);
		
		searchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Clear the exhibit Panel
				clearPanel(exhibitPanel);
				
				// Use Lowercase to search through list
				String search = searchText.getText().toLowerCase();
				
				searchFunction(search,exhibitPanel);
			}
		});
		
		searchBtn.setBounds(386, 17, 117, 29);
		panel.add(searchBtn);
		
		// Filter by ComboBox
		JComboBox makeCombo = new JComboBox<>();
		makeCombo.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				//Clear the Panel
				clearPanel(exhibitPanel);
				
				String s = (String)makeCombo.getSelectedItem();
				s = s.toLowerCase();
				
				
			}
		});
		makeCombo.setModel(new DefaultComboBoxModel(new String[] {"Make", "Toyota", "Nissan", "Subaru"}));
		makeCombo.setBounds(44, 55, 107, 27);
		panel.add(makeCombo);
		
		JComboBox modelCombo = new JComboBox();
		modelCombo.setModel(new DefaultComboBoxModel(new String[] {"Model", "Sedan", "SUV", "PickUp"}));
		modelCombo.setBounds(179, 55, 107, 27);
		panel.add(modelCombo );
		

		
		JLabel sortOptionLabel = new JLabel("Sort By:");
		sortOptionLabel.setBounds(447, 59, 61, 16);
		panel.add(sortOptionLabel);
		
		JComboBox sortCombo = new JComboBox();
		sortCombo.setModel(new DefaultComboBoxModel(new String[] {"Price(Low to High)", "Price(High to Low)", "Year(Low to High)", "Year(High to Low)"}));
		sortCombo.setBounds(509, 55, 130, 27);
		panel.add(sortCombo);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Type", "Sedan", "SUV", "Truck"}));
		comboBox.setBounds(298, 55, 100, 27);
		panel.add(comboBox);
		
		JButton nextPageBtn = new JButton("Next Page");
		nextPageBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pageCount += 1;
			}
		});
		nextPageBtn.setBounds(371, 507, 117, 29);
		panel.add(nextPageBtn);
		
		JButton prevPageBtn = new JButton("Previous Page");
		prevPageBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pageCount -= 1;
			}
		});
		prevPageBtn.setBounds(147, 507, 117, 29);
		panel.add(prevPageBtn);
		
		JLabel pageLabel = new JLabel("1/5");
		pageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		pageLabel.setBounds(287, 512, 61, 16);
		panel.add(pageLabel);
		
	}
	
	public List<Car> createCarList() {
		Car test = new Car();
		List<Car>  carList = test.initialize();
		return carList;
	}
	
	private List<Car> listForDisplay(List<Car> carList, int page){
		List<Car> list_display = new ArrayList<>();
		for(Car c: carList) {
			if(list_display.size() < MAX_ITEMS) {
				list_display.add(c);
			}
			else {
				return list_display;
			}
		}
		return list_display;
	}
	
	private void clearPanel(JPanel panel) {
		// Clear exhibitPanel
		Component[] compList = panel.getComponents();
		
		for(Component c: compList) {
			if(c instanceof JLabel) {
				panel.remove(c);
			}
		}
		
		panel.revalidate();
		panel.repaint();
	}
	
	private void initializePanel(JPanel panel, int count) {
		for(int i = 0 ; i < carList.size();i++) {
			
			if(i >= carList.size()) {
				break;
			}
			
			Car cur = carList.get(i);
			panel.add(new JLabel(cur.make +" " + cur.model));
		}
	}
	
	
	private void searchFunction(String search, JPanel panel) {
		if(search.equals("")) {
			initializePanel(panel,MAX_ITEMS);
			return;
		}
		//Iterate every car to find matching info
		for(Car car: carList) {
			if(car.getMake().toLowerCase().equals(search) || 
					car.getModel().toLowerCase().equals(search) ||
					car.getType().toLowerCase().equals(search)) {
				panel.add(new JLabel(car.make + " " + car.model));
			}
		}
		
	}
	
	private void comboBoxSelect(String s, JPanel panel){ 
		for(int i = 0 ; i < carList.size();i++) {
			
			if(i >= carList.size()) {
				break;
			}
			
			Car cur = carList.get(i);
			panel.add(new JLabel(cur.make +" " + cur.model));
		}
	}
	
	
	private void addPagination() {
		
	}
	
	private void setPageLabel(List<Car> list_display, JLabel label) {
		int total = list_display.size() / MAX_ITEMS;
		label.setText(pageCount + " / " + total);
	}
	
}
