package UI;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;

public class ItemsShowing extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Create the panel.
	 */
	public ItemsShowing() {
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(22, 28, 400, 242);
		add(panel);
		panel.setLayout(null);
		
		JLabel makeLabel = new JLabel("Make&Model:");
		makeLabel.setBounds(207, 21, 90, 16);
		panel.add(makeLabel);
		
		JLabel priceLabel = new JLabel("Price:");
		priceLabel.setBounds(207, 77, 61, 16);
		panel.add(priceLabel);
		
		JLabel yearLabel = new JLabel("Year:");
		yearLabel.setBounds(207, 49, 61, 16);
		panel.add(yearLabel);
		
		JButton viewDetailBtn = new JButton("View Detail");
		viewDetailBtn.setBounds(78, 185, 117, 29);
		panel.add(viewDetailBtn);
		
		textField = new JTextField();
		textField.setBounds(295, 16, 99, 26);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(295, 44, 99, 26);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(295, 72, 99, 26);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		JButton quoteBtn = new JButton("Request Quote");
		quoteBtn.setBounds(223, 185, 117, 29);
		panel.add(quoteBtn);
		
		JLabel lblNewLabel = new JLabel("Image Label");
		lblNewLabel.setBounds(42, 49, 83, 16);
		panel.add(lblNewLabel);

	}
}
