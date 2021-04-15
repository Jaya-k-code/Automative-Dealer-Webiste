package com.info5100.team5.UI;

import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.*;
import javax.swing.*;
import com.info5100.team5.DAO.DBConnector;
import com.info5100.team5.DAO.PrepareData;
import com.info5100.team5.DTO.VehicleDetails;


public class FilterAndBrowseUI extends JFrame implements ItemListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JButton btnNewButton;
    private JFrame frame;
    private JLabel  year,mileagelabel1, pricelabel,brandLabel, modelLabel, typeLabel, categoryLabel, colorLabel, priceLabel,
            priceMinLabel, priceMaxLabel, yearLabel, mileageLabel,brandvalue, modelvalue, yearvalue,categoryvalue, colorvalue, mileagevalue,pricevalue,space,empty;
    private JComboBox brand, model, type, category, color, priceMin, priceMax, selectYear, mileage;
    private JButton clearBtn;
    private Button viewDetailBtn;
    

    public FilterAndBrowseUI() {
    	dbConn = new DBConnector();
    }
    
    
    Container container = getContentPane();
    PrepareData br = new PrepareData();
    String dealerID = "";
    DBConnector dbConn;

/*
 * this method creates all the UI components required for search and sort
 */
    public void buildComponent() throws SQLException {
    	
    	 frame = new JFrame();
         // frame.pack();
          frame.setSize(1800, 1800);
    	
    	textField = new JTextField();
    	textField.setSize(542, 60);
		//textField.setBounds(320, 106, 800, 70);
		textField.setColumns(10);
		
		btnNewButton = new JButton("Search");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		//btnNewButton.addActionListener(new ActionListener() {
		btnNewButton.setBounds(920, 120, 102, 33);
		//frame.getContentPane().add(btnNewButton);
    	
		brand = new JComboBox(br.fetchData(dbConn.fetchDistinctValues("brand")));
    	//brand = new JComboBox(sd.fetchData(dbConn.filterValues("brand")));
    	//brand.setBounds(295, 269, 29, 21);
        type = new JComboBox(br.fetchData(dbConn.fetchDistinctValues("type")));
        category = new JComboBox(br.fetchData(dbConn.fetchDistinctValues("category")));
        color = new JComboBox(br.fetchData(dbConn.fetchDistinctValues("color")));
        mileage = new JComboBox(new String[]{"0-2000", "2000-3000", "3000-5000", "5000-6000"});
        model = new JComboBox(new String[]{"SEDAN","SUV"});
        priceMin = new JComboBox(new String[]{"0", "1000", "2000", "3000", "4000", "5000"});
        priceMax = new JComboBox(new String[]{"15000", "25000", "35000", "45000", "55000", "65000"});
        selectYear = new JComboBox(new String[]{"2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019","2020","2021"});
        reset();
        clearBtn = new JButton("Reset");

        brand.setEditable(false);
        type.setEditable(false);
        category.setEditable(false);
        color.setEditable(false);
        priceMin.setEditable(false);
        priceMax.setEditable(false);
        selectYear.setEditable(false);
        mileage.setEditable(false);

        brandLabel = new JLabel("  Brand");
        modelLabel = new JLabel("  Model");
        typeLabel = new JLabel("  Type");
        categoryLabel = new JLabel("  Category");
        colorLabel = new JLabel("  Color");
        priceLabel = new JLabel("  Price");
        priceMinLabel = new JLabel("Min");
        priceMaxLabel = new JLabel("Max");
        yearLabel = new JLabel("  Year");
        mileageLabel = new JLabel("  Mileage");

        Font font1 = new Font("Courier", Font.PLAIN, 16);
        brandLabel.setFont(font1);
        modelLabel.setFont(font1);
        typeLabel.setFont(font1);
        categoryLabel.setFont(font1);
        colorLabel.setFont(font1);
        priceLabel.setFont(font1);
        priceMinLabel.setFont(font1);
        priceMaxLabel.setFont(font1);
        yearLabel.setFont(font1);
        mileageLabel.setFont(font1);

    }

    /*
     * this method creates the panel for the UI components and adds it to the frame
     */
    public void buildPanelAndFrame() {
        JPanel p = new JPanel();
        p.setPreferredSize(new Dimension(1500, 0));
        JPanel p1 = new JPanel();
        p1.setBackground(Color.BLUE);
        p.setBackground(Color.PINK);
        p1.setPreferredSize(new Dimension(1500, 60));
        JPanel p2 = new JPanel(); 
        JPanel p3 = new JPanel(); 
        p1.add(textField);
        p1.add(btnNewButton);
        p.add(brandLabel);
        p.add(brand);
        p.add(modelLabel);
        p.add(model);
        p.add(typeLabel);
        p.add(type);
        p.add(categoryLabel);
        p.add(category);
        p.add(colorLabel);
        p.add(color);
        p.add(mileageLabel);
        p.add(mileage);
        p.add(priceLabel);
        p.add(p2);
        p2.add(priceMinLabel);
        p2.add(priceMin);
        p2.add(priceMaxLabel);
        p2.add(priceMax);
        p.add(yearLabel);
        p.add(selectYear);
        p.add(clearBtn);
        p.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        p2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        displayAllData(container);
        frame.add(p, BorderLayout.WEST);
        frame.add(p1, BorderLayout.NORTH);
    }

   /*
    * This method fetches all the vehicles data and creates a complete list for it
    */
    public ArrayList<VehicleDetails> getAllData() {
        ResultSet rs;
        ArrayList<VehicleDetails> completeList = new ArrayList<VehicleDetails>();

        try {
            rs = dbConn.fetchAllData();
            while (rs.next()) {
            	VehicleDetails vehicle = new VehicleDetails();

                vehicle.setId(rs.getString(1));
                vehicle.setBrand(rs.getString(3));
                vehicle.setModel(rs.getString(4));
                vehicle.setYear(rs.getString(5));
                vehicle.setType(rs.getString(6));
                String category = rs.getString(7);
                if (category.equals("NEW")) {
                    vehicle.setCategory("NEW");
                } else {
                    vehicle.setCategory("USED");
                }
                vehicle.setColor(rs.getString(8));
                float price = Float.parseFloat(rs.getString(9));
                vehicle.setPrice(price);
                float mileage = Float.parseFloat(rs.getString(10));
                vehicle.setMileage(mileage);
                completeList.add(vehicle);
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
		return completeList;
    }
    
    /*
     * This method takes the word to be searched from the search box and creates the data to be searched in database
     */
    public void getSearchResult(String searchWord) {
    	String wordFormed=null;
    	container.removeAll();
    	
    	 ArrayList<VehicleDetails> list = getAllData();
    	 for (VehicleDetails vehi : list) {
			
    		 if(vehi.getModel().equalsIgnoreCase(searchWord)) {
    			 wordFormed="model =" + "'"+searchWord+"'";
    			 displaySortedData(container, wordFormed);
    		 }
    		 
    		 if(vehi.getBrand().equalsIgnoreCase(searchWord)) {
    			 wordFormed="brand =" + "'"+searchWord+"'";
    			 displaySortedData(container, wordFormed);
    		 }
    		 
    		 if(vehi.getColor().equalsIgnoreCase(searchWord)) {
    			 wordFormed="color =" + "'"+searchWord+"'";
    			 displaySortedData(container, wordFormed);
    		 }
    		 
    		 if(vehi.getCategory().equalsIgnoreCase(searchWord)) {
    			 wordFormed="category =" + "'"+searchWord+"'";
    			 displaySortedData(container, wordFormed);
    		 }
    		 
    		 if(vehi.getType().equalsIgnoreCase(searchWord)) {
    			 wordFormed="type =" + "'"+searchWord+"'";
    			 displaySortedData(container, wordFormed);
    		 }
		}
    	 
		    }
    
    
    /*
     * This method displays all the vehicle details on UI
     */
    public void displayAllData(Container container) {
    	container.revalidate();
     frame.add(container, BorderLayout.SOUTH);
        ArrayList<VehicleDetails> list = getAllData();
        JPanel basepanel = new JPanel();
        basepanel.setBackground(Color.GREEN);;
        BoxLayout bl = new BoxLayout(basepanel, BoxLayout.Y_AXIS);
        container.add(basepanel);
        basepanel.setLayout(bl);

        if (list.size() == 0) {
            JPanel panelempty = new JPanel();
            panelempty.setLayout(new FlowLayout(FlowLayout.CENTER));
            this.empty = new JLabel("Not Available");
            panelempty.add(empty);
            basepanel.add(panelempty);

        }
        for (int i = 0; i < list.size(); i++) {
            VehicleDetails car = list.get(i);
            JPanel panel = new JPanel();
            FlowLayout fl = new FlowLayout();
            panel.setLayout(fl);
            buildSortedComponent(panel, car, i);
            panel.setBorder(BorderFactory.createBevelBorder(1));
            basepanel.add(panel);
            basepanel.add(Box.createVerticalStrut(15));

        }
    }

    
    /*
     * This method displays only the sorted vehicle details on UI
     */
    public void displaySortedData(Container filtered, String searchString) {

        ArrayList<VehicleDetails> list = getSortedData(searchString);
        JPanel basePanel = new JPanel();
        BoxLayout bl = new BoxLayout(basePanel, BoxLayout.Y_AXIS);
        filtered.add(basePanel);
        JScrollPane sp = new JScrollPane(basePanel);
        filtered.add(sp);
        basePanel.setLayout(bl);
        if (list.size() == 0) {
            JPanel panelempty = new JPanel();
            panelempty.setLayout(new FlowLayout(FlowLayout.CENTER));
            this.empty = new JLabel("No Results, Please Try Again.");
            panelempty.add(empty);
            basePanel.add(panelempty);
        }
        for (int i = 0; i < list.size(); i++) {
            VehicleDetails car = list.get(i);
            JPanel panel = new JPanel();
            FlowLayout fl = new FlowLayout();
            panel.setLayout(fl);
            buildSortedComponent(panel, car, i);
            panel.setBorder(BorderFactory.createBevelBorder(1));
            basePanel.add(panel);
            basePanel.add(Box.createVerticalStrut(15));
        }
        container.revalidate();
    }

    
    /*
     * This method gets the sorted data and returns the vehicle list
     */
    private ArrayList<VehicleDetails> getSortedData(String searchString) {
         ArrayList<VehicleDetails> vehicleList = new ArrayList<VehicleDetails>();
         ResultSet rs;
         try {
             rs = dbConn.fetchAllDataWithFilters(searchString);

             while (rs.next()) {
            	 VehicleDetails vehicle = new VehicleDetails();

                 vehicle.setId(rs.getString(1));
                 vehicle.setBrand(rs.getString(3));
                 vehicle.setModel(rs.getString(4));
                 vehicle.setYear(rs.getString(5));
                 vehicle.setType(rs.getString(6));

                 String category = rs.getString(7);

                 if (category.equals("NEW")) {
                     vehicle.setCategory("NEW");
                 } else {
                     vehicle.setCategory("USED");
                 }

                 vehicle.setColor(rs.getString(8));

                 float price = Float.parseFloat(rs.getString(9));
                 vehicle.setPrice(price);

                 float mileage = Float.parseFloat(rs.getString(10));
                 vehicle.setMileage(mileage);

                 vehicleList.add(vehicle);
             }
         } catch (SQLException se) {
             se.printStackTrace();
         }
		return vehicleList;
    }
    
    
    /*
     * This method creates the sorted components that would be displayed after search or sort
     */
	public void buildSortedComponent(JPanel panel, VehicleDetails vehicle, int i) {

        this.brandvalue = new JLabel(vehicle.getBrand() + "  - ");
        this.modelvalue = new JLabel(vehicle.getModel() + "                    ");
        this.year = new JLabel("Year : ");
        this.yearvalue = new JLabel(String.valueOf(vehicle.getYear()) + "                ");
        this.categoryvalue = new JLabel(vehicle.getCategory() + "                 ");
        this.colorvalue = new JLabel(vehicle.getColor() + "\n\n\n");
        this.mileagelabel1 = new JLabel("Mileage: ");
        this.mileagevalue = new JLabel(String.valueOf(vehicle.getMileage()) + "                       ");
        this.pricelabel = new JLabel("Price:");
        this.pricevalue = new JLabel(String.valueOf("$" + vehicle.getPrice()) + "0" + "         ");
        this.viewDetailBtn = new Button("View Detail");
        viewDetailBtn.setBackground(Color.yellow);
        this.space = new JLabel("");

        JPanel jp = new JPanel();
        BoxLayout bl1 = new BoxLayout(jp, BoxLayout.Y_AXIS);
        jp.setLayout(bl1);
        createPanel2(jp, brandvalue, modelvalue);
        createPanel1(jp, categoryvalue);
        createPanel2(jp, year, yearvalue);
        createPanel2(jp, mileagelabel1, mileagevalue);
        panel.add(jp);
        
        
        JPanel jp1 = new JPanel();
        BoxLayout bl2 = new BoxLayout(jp1, BoxLayout.Y_AXIS);
        jp1.setLayout(bl2);
        createPanel1(jp1, colorvalue);
        createPanel1(jp1, space);
        createPanel2(jp1, pricelabel, pricevalue);
        createPanel1(jp1, space);
        jp1.add(viewDetailBtn);
        viewDetailBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        });

        panel.add(jp1);
    }

	/*
	 * This method creates panel for the sorted details (color, category)
	 */
    public void createPanel1(JPanel panel, JLabel value) {
        JPanel jp2 = new JPanel();
        jp2.setLayout(new FlowLayout(FlowLayout.LEFT));
        jp2.add(value);
        panel.add(jp2);
    }

    /*
	 * This method creates panel for the sorted details (brand,mileage,price)
	 */
    public void createPanel2(JPanel panel, JLabel value1, JLabel value2) {
        JPanel jp2 = new JPanel();
        jp2.setLayout(new FlowLayout(FlowLayout.LEFT));
        jp2.add(value1);
        jp2.add(value2);
        panel.add(jp2);
    }

    public void addListener() {
        brand.addItemListener(this);
        brand.addItemListener(this);
        model.addItemListener(this);
        type.addItemListener(this);
        color.addItemListener(this);
        category.addItemListener(this);
        priceMin.addItemListener(this);
        priceMax.addItemListener(this);
        selectYear.addItemListener(this);
        mileage.addItemListener(this);
        btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String wordSearch= textField.getText();
				getSearchResult(wordSearch);
				
			}
		});
        clearBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                reset();
                getSelectedOption().clear();
            }
        });
    }
    
    @Override
    public void itemStateChanged(ItemEvent event) {
        if (event.getStateChange() == ItemEvent.SELECTED) {
            changeData();

        }
    }

/*
 * This method takes the user seletcted options and puts it in hashmap. ex- brand,BMW
 */
    public HashMap<String, String> getSelectedOption() {
        HashMap<String, String> selectedOption = new HashMap<>();
        if (!(brand.getSelectedItem() == null)) {
        	selectedOption.put("brand", (String) brand.getSelectedItem());
        }
        if (!(model.getSelectedItem() == null)) {
        	selectedOption.put("model", (String) model.getSelectedItem());
        }
        if (!(type.getSelectedItem() == null)) {
        	selectedOption.put("type", (String) type.getSelectedItem());
        }
        if (!(category.getSelectedItem() == null)) {
        	selectedOption.put("category", (String) category.getSelectedItem());
        }
        if (!(color.getSelectedItem() == null)) {
        	selectedOption.put("color", (String) color.getSelectedItem());
        }
        if (!(selectYear.getSelectedItem() == null)) {
        	selectedOption.put("year", (String) selectYear.getSelectedItem());
        }
        if (!(priceMin.getSelectedItem() == null)) {
        	selectedOption.put("priceMin", (String) priceMin.getSelectedItem());
        }
        if (!(priceMax.getSelectedItem() == null)) {
        	selectedOption.put("priceMax", (String) priceMax.getSelectedItem());
        }
        if (!(mileage.getSelectedItem() == null)) {
        	selectedOption.put("mileage", (String) mileage.getSelectedItem());
        }
        return selectedOption;
    }

    /*
     * This method updates the display data after the sort
     */
    public void changeData() {
    	container.removeAll();
        HashMap<String, String> result = getSelectedOption();
        String find = br.fetchSelectedResult(result);
        System.out.println("search string:" + find);
        displaySortedData(container, find);
    }

/*
 * This method does the frame operations such as setting size, visibility, and closing
 */
    public void frameOperations() {
        frame.setSize(900, 600);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    /*
     * This method resets all the fields and options selected by the user
     */
    public void reset() {
        brand.setSelectedItem(null);
        type.setSelectedItem(null);
        category.setSelectedItem(null);
        color.setSelectedItem(null);
        mileage.setSelectedItem(null);
        model.setSelectedItem(null);
        priceMin.setSelectedItem(null);
        priceMax.setSelectedItem(null);
        selectYear.setSelectedItem(null);
        container.removeAll();
        displayAllData(container);
    }
    
    
    public void buildUseCase2UI() throws Exception {
        this.buildComponent();
        this.buildPanelAndFrame();
        this.addListener();
        this.frameOperations();

    }
   
    
}