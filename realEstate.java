import static java.nio.file.StandardOpenOption.CREATE;
 
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
 
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
 
public class realEstate extends JFrame implements ActionListener{
	private JPanel contentPane;
	private JPanel panel1;
	private JPanel panel2;
	private JPanel panel3;
	private JPanel panel4;
	private JTextField tfContactFirstName;
	private JTextField tfContactLastName;
	private JTextField tfContactPhoneNumber;
	private JTextField tfContactEmail;
	private JTextField tfFirstContact;
	private JTextField tfContactBudget; 
	private JTextField tfEditContactFirstName;
	private JTextField tfEditContactLastName;
	private JTextField tfEditContactEmail;
	private JTextField tfEditContactPhoneNumber;
	private JTextField tfEditFirstContact;
	private JTextField tfEditContactBudget;
	private JTextField tfEditContactPrepAppAmmount;
	private JCheckBox cbEditContactPreapproved;
	private JLabel lblContactFirstName;
	private JLabel lblContactLastName;
	private JLabel lblContactEmail;
	private JLabel lblEditContactFirstName;
	private JLabel lblEditContactLastName;
	private JLabel lblEditContactEmail;
	private JLabel lblEditContactPhoneNumber;
	private JLabel lblEditFirstContact;
	private JLabel lblEditContactBudget;
	private JLabel lblEditContactPreApproved;
	private JLabel lblEditContactPreAppAmmount;
	private JTextField tfNCContactFirstName;
	private JTextField tfNCContactLastName;
	private JTextField tfNCContactEmail;
	private JLabel lblNCContactFirstName;
	private JLabel lblNCContactLastName;
	private JLabel lblNCContactEmail;
	private JCheckBox cbContactPreApp;
	private JTextField tfApprovedAmmount;
	private JButton acceptButton;
	private JButton searchContacts;
	private JButton searchAgain;
	private JButton editFile;
	private JButton saveEditedContact;
	private String customerInformation;
	private ArrayList<String> searchedContacts = new ArrayList<String>();
	private JTextArea searchResults = new JTextArea();
	private JScrollPane sp;
	protected GridBagConstraints gbc = new GridBagConstraints();
 
	public static void main(String[] args) {
		 realEstate frame = new realEstate();
		 frame.setVisible(true);
	}
 
	public realEstate()   {
		super("Real Estate Made Easy");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(300,150,700,450);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.setBackground(new Color(50,50,200));
		setContentPane(contentPane);
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
 
		//****************************************************************************
		//Menu Bar and Options
		//****************************************************************************
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 700, 20);
		contentPane.add(menuBar);
		JMenu menuBarFile = new JMenu("File");
		menuBar.add(menuBarFile);
		JMenuItem fileItemExit = new JMenuItem("Exit");
		fileItemExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		menuBarFile.add(fileItemExit);
		JMenu mnNewMenu = new JMenu("Help");
		menuBar.add(mnNewMenu);
		JMenuItem mntmNewMenuItem = new JMenuItem("Information");
		mnNewMenu.add(mntmNewMenuItem);
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "This Program is designed to support real estate agents.\n"
						+ " It allows you to save contacts, search thru previous contacts,\n"
						+ " and the ability to update contact information.");
			}
		});
 
		panel1 = new JPanel();
		panel2 = new JPanel();
		panel3 = new JPanel();
		panel4 = new JPanel();
 
		contentPane.add(panel1, BorderLayout.NORTH);
		JLabel title = new JLabel("Real Estate Made Easy");
		title.setFont(new Font("Serif", Font.BOLD, 32));
		title.setForeground(new Color(150,150,150));
		panel1.setBackground(new Color(50,50,200));;
		panel1.add(title);
 
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		tabbedPane.addTab("Search Contacts", panel2);
		tabbedPane.addTab("New Contact",panel3);
		tabbedPane.addTab("Edit Contact" , panel4);
 
		//****************************************************************************
		//This is search contacts tab on gui
		//****************************************************************************
		panel2.setLayout(new GridBagLayout());
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = gbc.WEST;
		gbc.insets = new Insets(5,5,5,5);
		lblContactFirstName = new JLabel("First Name");
		panel2.add(lblContactFirstName, gbc);
		gbc.gridx = 1;
		tfContactFirstName = new JTextField(10);
		panel2.add(tfContactFirstName, gbc);
		gbc.gridy = 1;
		gbc.gridx = 0;
		lblContactLastName = new JLabel("Last Name");
		panel2.add(lblContactLastName, gbc);
		gbc.gridx = 1;
		tfContactLastName = new JTextField(10);
		panel2.add(tfContactLastName, gbc);
		gbc.gridy = 2;
		gbc.gridx = 0;
		lblContactEmail = new JLabel("Contact's Email ");
		panel2.add(lblContactEmail, gbc);
		gbc.gridx = 1;
		tfContactEmail = new JTextField(10);
		panel2.add(tfContactEmail, gbc);
		gbc.gridy = 3;
		gbc.gridx = 0;
		gbc.gridwidth = 2;
		gbc.anchor = gbc.CENTER;
		searchContacts = new JButton("Search");
		panel2.add(searchContacts, gbc);
		searchContacts.addActionListener(this);
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		panel2.add(searchResults, gbc);
		searchResults.setVisible(false);
		gbc.gridx = 0; 
		gbc.gridy = 3; 
		searchAgain = new JButton("Search Again");
		panel2.add(searchAgain, gbc);
		searchAgain.setVisible(false);
		searchAgain.addActionListener(this);
		 
		
		//****************************************************************************
		//This is new contact tab on gui
		//****************************************************************************
		
		panel3.setLayout(new GridBagLayout());
		tfNCContactFirstName = new JTextField(10);
		lblNCContactFirstName = new JLabel("First Name");
		tfNCContactLastName = new JTextField(10);
		lblNCContactLastName = new JLabel("Last Name");
		tfContactPhoneNumber = new JTextField(10);
		JLabel lblContactPhoneNumber = new JLabel("Phone Number");
		tfNCContactEmail = new JTextField(10);
		lblNCContactEmail = new JLabel("Email");
		tfFirstContact = new JTextField(10);
		JLabel lblFirstContact = new JLabel("Date Received");
		tfContactBudget = new JTextField(10);
		JLabel lblContactBudget = new JLabel("Budget");
		cbContactPreApp = new JCheckBox();
		JLabel lblContactPreApp = new JLabel("Pre-Approved");
		tfApprovedAmmount = new JTextField(10);
		JLabel lblApprovedAmmount = new JLabel("Pre-Approved Ammount");
		acceptButton = new JButton("Save Contact");
 
		gbc.gridx = 0;
		gbc.gridy = 0;
 
		gbc.anchor = GridBagConstraints.WEST;
 		panel3.add(lblNCContactFirstName, gbc);
		gbc.gridx = 1;
		gbc.gridy = 0;
		panel3.add(tfNCContactFirstName, gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		panel3.add(lblNCContactLastName, gbc);
		gbc.gridx = 1;
		gbc.gridy = 1;
		panel3.add(tfNCContactLastName, gbc);
		gbc.gridx = 0;
		gbc.gridy = 2;
		panel3.add(lblContactPhoneNumber, gbc);
		gbc.gridx = 1;
		gbc.gridy = 2;
		panel3.add(tfContactPhoneNumber, gbc);
		gbc.gridx = 0;
		gbc.gridy = 3;
		panel3.add(lblNCContactEmail, gbc);
		gbc.gridx = 1;
		gbc.gridy = 3;
		panel3.add(tfNCContactEmail, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 4;
		panel3.add(lblFirstContact, gbc);
		gbc.gridx = 1;
		gbc.gridy = 4;
		panel3.add(tfFirstContact, gbc);
		gbc.gridx = 0;
		gbc.gridy = 5;
		panel3.add(lblContactBudget, gbc);
		gbc.gridx = 1;
		gbc.gridy = 5;
		panel3.add(tfContactBudget, gbc);
		gbc.gridx = 0;
		gbc.gridy = 6;
		panel3.add(lblContactPreApp, gbc);
		gbc.gridx = 1;
		gbc.gridy = 6;
		panel3.add(cbContactPreApp, gbc);
		gbc.gridx = 0;
		gbc.gridy = 7;
		panel3.add(lblApprovedAmmount, gbc);
		gbc.gridx = 1;
		gbc.gridy = 7;
		panel3.add(tfApprovedAmmount, gbc);
		gbc.gridx = 0;
		gbc.gridy = 8;
		gbc.gridwidth = 0;
		gbc.anchor = gbc.CENTER;
		panel3.add(acceptButton, gbc);
		acceptButton.addActionListener(this);
		
		//***************************************************************************
		//This is the Edit Contact Tab display on Gui
		//***************************************************************************
		panel4.setLayout(new GridBagLayout());
		gbc.gridwidth = 1;
		gbc.anchor = gbc.WEST;
		gbc.gridx = 0;
		gbc.gridy = 0;
		lblEditContactFirstName = new JLabel("First Name");
		panel4.add(lblEditContactFirstName,gbc);
		gbc.gridx = 1;
		gbc.gridy = 0;
		tfEditContactFirstName = new JTextField(10);
		panel4.add(tfEditContactFirstName, gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		lblEditContactLastName = new JLabel("Last Name");
		panel4.add(lblEditContactLastName,gbc);
		gbc.gridx = 1;
		gbc.gridy = 1;
		tfEditContactLastName = new JTextField(10);
		panel4.add(tfEditContactLastName , gbc);
		gbc.gridx = 0;
		gbc.gridy = 2;
		lblEditContactEmail = new JLabel("Email");
		panel4.add(lblEditContactEmail, gbc);
		gbc.gridx = 1;
		gbc.gridy = 2;
		tfEditContactEmail = new JTextField(10);
		panel4.add(tfEditContactEmail, gbc);
		gbc.gridx = 0;
		gbc.gridy = 3;
		lblEditContactPhoneNumber = new JLabel("Phone Number");
		panel4.add(lblEditContactPhoneNumber, gbc);
		lblEditContactPhoneNumber.setVisible(false);
		gbc.gridx = 1;
		gbc.gridy = 3;
		tfEditContactPhoneNumber = new JTextField(10);
		panel4.add(tfEditContactPhoneNumber, gbc);
		tfEditContactPhoneNumber.setVisible(false);
		gbc.gridx = 0;
		gbc.gridy = 4;
		lblEditFirstContact = new JLabel("First Contact");
		panel4.add(lblEditFirstContact, gbc);
		lblEditFirstContact.setVisible(false);
		gbc.gridx = 1;
		gbc.gridy = 4;
		tfEditFirstContact = new JTextField(10);
		panel4.add(tfEditFirstContact, gbc);
		tfEditFirstContact.setVisible(false);
		gbc.gridx = 0;
		gbc.gridy = 5;
		lblEditContactBudget = new JLabel("Budget");
		panel4.add(lblEditContactBudget , gbc);
		lblEditContactBudget.setVisible(false);
		gbc.gridx = 1;
		gbc.gridy = 5;
		tfEditContactBudget = new JTextField(10);
		panel4.add(tfEditContactBudget, gbc);
		tfEditContactBudget.setVisible(false);
		gbc.gridx = 0;
		gbc.gridy = 6;
		lblEditContactPreApproved = new JLabel("Pre Approved");
		panel4.add(lblEditContactPreApproved, gbc);
		lblEditContactPreApproved.setVisible(false);
		gbc.gridx = 1;
		gbc.gridy = 6;
		cbEditContactPreapproved = new JCheckBox();
		panel4.add(cbEditContactPreapproved,gbc);
		cbEditContactPreapproved.setVisible(false);
		gbc.gridx = 0;
		gbc.gridy = 7;
		lblEditContactPreAppAmmount = new JLabel("Pre Approved Ammount");
		panel4.add(lblEditContactPreAppAmmount, gbc);
		lblEditContactPreAppAmmount.setVisible(false);
		gbc.gridx = 1;
		gbc.gridy = 7;
		tfEditContactPrepAppAmmount = new JTextField(10);
		panel4.add(tfEditContactPrepAppAmmount, gbc);
		tfEditContactPrepAppAmmount.setVisible(false);
		gbc.gridx = 0;
		gbc.gridy = 8;
		gbc.gridwidth = 2;
		gbc.anchor = gbc.CENTER;
		editFile = new JButton("Edit Contact");
		panel4.add(editFile, gbc);
		editFile.addActionListener(this);
		saveEditedContact = new JButton("Save Contact");
		panel4.add(saveEditedContact, gbc);
		saveEditedContact.setVisible(false);
		saveEditedContact.addActionListener(this);
		
	}
	
	//****************************************************************************
	//Remove Record will remove a record from CSV
	//****************************************************************************
	
	private static Scanner x;
	
	public static void removeRecord(String removeLine) {
		String tempFile = "temp.csv";
		String filePath = "Real Estate Contacts.csv";
		File oldFile = new File(filePath);
		File newFile = new File(tempFile);
		String contactFirstName; String contactLastName; String contactPhoneNumber; String contactEmail; String firstContact; String contactBudget; String contactPreApp; String ApprovedAmmount;
		 
		
		try {
			FileWriter fw = new FileWriter(tempFile, true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			x = new Scanner(new File(filePath));
			x.useDelimiter("[,\n]");
			
			while(x.hasNext()) {
				//x.nextLine().isEmpty();
				contactFirstName = x.next(); contactLastName = x.next(); contactPhoneNumber = x.next();
				contactEmail = x.next(); firstContact = x.next(); contactBudget = x.next();
				contactPreApp = x.next(); ApprovedAmmount = x.next();
				
				if(!contactFirstName.equals(removeLine)) {
					pw.println(contactFirstName + "," + contactLastName + "," + contactEmail + "," + firstContact + "," + contactBudget + "," + contactPreApp + "," + ApprovedAmmount);
				}
			}
			x.close();
			pw.flush();
			pw.close();
			oldFile.delete();
			File dump = new File(filePath);
			newFile.renameTo(dump);
		}
		catch(Exception e)
		{
			
		}
	}
	//****************************************************************************
	// Allows ability to edit CSV. Displays contact to Edit Contact Gui
	// Thru Action Listener in Edit Contract tab, user can then save edited contact.
	//****************************************************************************
	 
	public static ArrayList<String> editReadRecord(String firstName, String lastName, String email){
		boolean found = false;
		ArrayList<String> List = new ArrayList<String>();
		String contactFirstName; String contactLastName; String contactPhoneNumber; String contactEmail; String firstContact; String contactBudget; String contactPreApp; String ApprovedAmmount;
		File file = new File("Real Estate Contacts.csv");
		
		try {
			x = new Scanner(new File("Real Estate Contacts.csv"));
			x.useDelimiter("[,\n]");
			while(x.hasNext() && !found) {
				contactFirstName = x.next(); contactLastName = x.next(); contactPhoneNumber = x.next();
				contactEmail = x.next(); firstContact = x.next(); contactBudget = x.next();
				contactPreApp = x.next(); ApprovedAmmount = x.next();
				 
				if(contactFirstName.equals(firstName) && contactLastName.equals(lastName) && contactEmail.equals(email)) {
					List.add(0,contactFirstName);
					List.add(1, contactLastName);
					List.add(2, contactEmail);
					List.add(3, contactPhoneNumber);
					List.add(4, firstContact);
					List.add(5, contactBudget);
					List.add(6, contactPreApp);
					List.add(7, ApprovedAmmount);
					found = true;
				}
			}
 
			 
		}
		catch(FileNotFoundException e)
		{
			
		}
		return List;
	}
	
	//****************************************************************************
	// Displays results to Search Contacts Tab on gui
	//****************************************************************************
	
	public static ArrayList<String> readRecord(String firstName, String lastName, String email) {
		boolean found = false;
		ArrayList<String> List = new ArrayList<String>();
		String contactFirstName; String contactLastName; String contactPhoneNumber; String contactEmail; String firstContact; String contactBudget; String contactPreApp; String ApprovedAmmount;
 
		
		try {
			x = new Scanner(new File("Real Estate Contacts.csv"));
			x.useDelimiter("[,\n]");
 
			while(x.hasNext() && !found) {
				
				contactFirstName = x.next(); contactLastName = x.next(); contactPhoneNumber = x.next();
				contactEmail = x.next(); firstContact = x.next(); contactBudget = x.next();
				contactPreApp = x.next(); ApprovedAmmount = x.next();
 
				if(contactFirstName.equals(firstName)  && lastName.length() > 0 && email.length() > 0) {
					if(contactLastName.equals(lastName) && contactEmail.equals(email)) {
					System.out.println(contactFirstName + contactLastName);
					List.add(contactFirstName + " " + contactLastName + " Email: " + contactEmail + "\nDate Received: " + firstContact + " Budget: " + contactBudget + " PreApproved: " + contactPreApp + " Approval Ammount: " + ApprovedAmmount + "\n\n");
					}
				}
 
				else if(contactFirstName.equals(firstName)  && lastName.length() > 0 && email.length() == 0) {
					if(contactLastName.equals(lastName)) {
					System.out.println(contactFirstName + contactLastName);
					List.add(contactFirstName + " " + contactLastName + " Email: " + contactEmail + "\nDate Received: " + firstContact + " Budget: " + contactBudget + " PreApproved: " + contactPreApp + " Approval Ammount: " + ApprovedAmmount + "\n\n");}
				}
				else if(contactFirstName.equals(firstName) && lastName.length() == 0 && email.length() > 0) {
					if(contactEmail.equals(email)) {
					System.out.println(contactFirstName + contactLastName);
					List.add(contactFirstName + " " + contactLastName + " Email: " + contactEmail + "\nDate Received: " + firstContact + " Budget: " + contactBudget + " PreApproved: " + contactPreApp + " Approval Ammount: " + ApprovedAmmount + "\n\n");}
				}
				else if(contactFirstName.equals(firstName)  && lastName.length() == 0 && email.length() == 0) {
					System.out.println(contactFirstName + contactLastName);
					List.add(contactFirstName + " " + contactLastName + " Email: " + contactEmail + "\nDate Received: " + firstContact + " Budget: " + contactBudget + " PreApproved: " + contactPreApp + " Approval Ammount: " + ApprovedAmmount + "\n\n");
				}
 
////
				else if(firstName.length() > 0 && contactLastName.equals(lastName) && email.length() > 0) {
					if(contactFirstName.equals(firstName) && contactEmail.equals(email)) {
					System.out.println(contactFirstName + contactLastName);
					List.add(contactFirstName + " " + contactLastName + " Email: " + contactEmail + "\nDate Received: " + firstContact + " Budget: " + contactBudget + " PreApproved: " + contactPreApp + " Approval Ammount: " + ApprovedAmmount + "\n\n");
					}
				}
 
				else if(firstName.length() > 0  && contactLastName.equals(lastName) && email.length() == 0) {
					if(contactFirstName.equals(firstName)) {
					System.out.println(contactFirstName + contactLastName);
					List.add(contactFirstName + " " + contactLastName + " Email: " + contactEmail + "\nDate Received: " + firstContact + " Budget: " + contactBudget + " PreApproved: " + contactPreApp + " Approval Ammount: " + ApprovedAmmount + "\n\n");
					}
				}
				else if(firstName.length() == 0 && contactLastName.equals(lastName) && email.length() > 0) {
					if(contactEmail.equals(email)) {
					System.out.println(contactFirstName + contactLastName);
					List.add(contactFirstName + " " + contactLastName + " Email: " + contactEmail + "\nDate Received: " + firstContact + " Budget: " + contactBudget + " PreApproved: " + contactPreApp + " Approval Ammount: " + ApprovedAmmount + "\n\n");
					}
				}
				else if(firstName.length() == 0  && contactLastName.equals(lastName) && email.length() == 0) {
					System.out.println(contactFirstName + contactLastName);
					List.add(contactFirstName + " " + contactLastName + " Email: " + contactEmail + "\nDate Received: " + firstContact + " Budget: " + contactBudget + " PreApproved: " + contactPreApp + " Approval Ammount: " + ApprovedAmmount + "\n\n");
				}
 
////
				else if(firstName.length() > 0 && lastName.length() > 0 && contactEmail.equals(email)) {
					if(contactFirstName.equals(firstName) && contactLastName.equals(lastName)) {
					System.out.println(contactFirstName + contactLastName);
					List.add(contactFirstName + " " + contactLastName + " Email: " + contactEmail + "\nDate Received: " + firstContact + " Budget: " + contactBudget + " PreApproved: " + contactPreApp + " Approval Ammount: " + ApprovedAmmount + "\n\n");
					}
				}
 
				else if(firstName.length() > 0 && lastName.length() == 0 && contactEmail.equals(email)) {
					if(contactFirstName.equals(firstName)) {
					System.out.println(contactFirstName + contactLastName);
					List.add(contactFirstName + " " + contactLastName + " Email: " + contactEmail + "\nDate Received: " + firstContact + " Budget: " + contactBudget + " PreApproved: " + contactPreApp + " Approval Ammount: " + ApprovedAmmount + "\n\n");
					}
				}
 
				else if(firstName.length() == 0 && lastName.length() > 0 && contactEmail.equals(email)) {
					if(contactLastName.equals(lastName)) {
					List.add(contactFirstName + " " + contactLastName + " Email: " + contactEmail + "\nDate Received: " + firstContact + " Budget: " + contactBudget + " PreApproved: " + contactPreApp + " Approval Ammount: " + ApprovedAmmount + "\n\n");
					}
				}
				else if(firstName.length() == 0 && lastName.length() == 0 && contactEmail.equals(email)) {
					System.out.println(contactFirstName + contactLastName);
					List.add(contactFirstName + " " + contactLastName + " Email: " + contactEmail + "\nDate Received: " + firstContact + " Budget: " + contactBudget + " PreApproved: " + contactPreApp + " Approval Ammount: " + ApprovedAmmount + "\n\n");
				}
				
				
				if(!x.hasNext()) {
					found = true;
				}
			}
 
 
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return List;
	}
	//****************************************************************************
	// Method for checking for duplicate Contacts prior to saving new contact
	//****************************************************************************
	
	public void checkForDuplicateContacts() {
		
	}
	//****************************************************************************
	//Action Listener for buttons in program
	//****************************************************************************
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == acceptButton) {
			if(tfNCContactFirstName.getText().length() > 0 && tfNCContactLastName.getText().length() > 0 && tfNCContactEmail.getText().length() > 0 ) {
			customerInformation = tfNCContactFirstName.getText().toUpperCase() + "," + tfNCContactLastName.getText().toUpperCase() + "," + tfContactPhoneNumber.getText()
			+ "," + tfNCContactEmail.getText().toUpperCase() + "," + tfFirstContact.getText() + "," + tfContactBudget.getText().replace(',', '`') + "," + cbContactPreApp.isSelected() + "," + tfApprovedAmmount.getText().replace(',', '`') ;
			
			
			
			tfNCContactFirstName.setText(""); tfNCContactLastName.setText(""); tfContactPhoneNumber.setText("");tfNCContactEmail.setText("");
			tfFirstContact.setText(""); tfContactBudget.setText(""); tfApprovedAmmount.setText("");
			if(cbContactPreApp.isSelected())
				cbContactPreApp.setSelected(false);
			System.out.println(customerInformation);
			File file = new File("Real Estate Contacts.csv");
			String saveMe;
			try
			{
				if(!file.exists()) {
					file.createNewFile();
				}
				FileWriter fileWriter = new FileWriter(file, true);
				BufferedWriter writer = new BufferedWriter(fileWriter);
				saveMe = customerInformation;
				writer.write(saveMe, 0, saveMe.length());
				writer.newLine();
				writer.close();
			}	
			catch(Exception er)
			{
				System.out.println("Message " + er);
			}
			}
			else {
				JOptionPane.showMessageDialog(null, "Please enter a valid contact First Name, Last Name, and Email.");
			}
		}
		if(e.getSource() == searchContacts) {
			searchedContacts = readRecord(tfContactFirstName.getText().toUpperCase(),
					tfContactLastName.getText().toUpperCase(), tfContactEmail.getText().toUpperCase());
			lblContactFirstName.setVisible(false);
			tfContactFirstName.setVisible(false);
			lblContactLastName.setVisible(false);
			tfContactLastName.setVisible(false);
			lblContactEmail.setVisible(false);
			tfContactEmail.setVisible(false);
			searchContacts.setVisible(false);
			searchResults.setVisible(true);
			searchAgain.setVisible(true);
			searchResults.setEditable(false);

			for(int i = 0; i < searchedContacts.size(); i++) {
				searchResults.append(searchedContacts.get(i).replace('`', ',') + " ");
			}
			sp = new JScrollPane(searchResults, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
					JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			gbc.gridx = 0;
			gbc.gridy = 0;
			gbc.ipadx = 400;
			gbc.ipady = 200;
			panel2.add(sp, gbc);
			repaint();
		}
		if(e.getSource() == searchAgain) {
			tfContactFirstName.setText("");
			tfContactLastName.setText("");
			tfContactEmail.setText("");
			lblContactFirstName.setVisible(true);
			tfContactFirstName.setVisible(true);
			lblContactLastName.setVisible(true);
			tfContactLastName.setVisible(true);
			lblContactEmail.setVisible(true);
			tfContactEmail.setVisible(true);
			searchContacts.setVisible(true);
			searchAgain.setVisible(false);
			searchResults.setVisible(false);
			sp.setVisible(false);
			searchResults.setText("");
		}
		if(e.getSource() == editFile) {
			if(tfEditContactFirstName.getText().length() > 0 && tfEditContactLastName.getText().length() > 0 &&
					tfEditContactEmail.getText().length() > 0 && tfEditContactEmail.getText().contains(".com")) {
			lblEditContactPhoneNumber.setVisible(true);
			tfEditContactPhoneNumber.setVisible(true);
			lblEditContactBudget.setVisible(true);
			tfEditContactBudget.setVisible(true);
			lblEditContactPreApproved.setVisible(true);
			cbEditContactPreapproved.setVisible(true);
			lblEditContactPreAppAmmount.setVisible(true);
			tfEditContactPrepAppAmmount.setVisible(true);
			lblEditFirstContact.setVisible(true);
			tfEditFirstContact.setVisible(true);
			saveEditedContact.setVisible(true);
			editFile.setVisible(false);
			ArrayList<String> test;
			test = editReadRecord(tfEditContactFirstName.getText().toUpperCase(), tfEditContactLastName.getText().toUpperCase(), tfEditContactEmail.getText().toUpperCase());
			tfEditContactFirstName.setText(test.get(0));
			tfEditContactLastName.setText(test.get(1));
			tfEditContactEmail.setText(test.get(2));
			tfEditContactPhoneNumber.setText(test.get(3));
			tfEditFirstContact.setText(test.get(4));
			tfEditContactBudget.setText(test.get(5));
			if(test.get(6).equals("true")) {
			cbEditContactPreapproved.setSelected(true);}
			tfEditContactPrepAppAmmount.setText(test.get(7));
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Please enter a valid First Name, Last Name, and email");
			}
			 
			
			
		}
		if(e.getSource() == saveEditedContact) {
			if(tfEditContactFirstName.getText().length() > 0 && tfEditContactLastName.getText().length() > 0 &&
					tfEditContactEmail.getText().length() > 0 && tfEditContactEmail.getText().contains(".COM")) {
			lblEditContactPhoneNumber.setVisible(false);
			tfEditContactPhoneNumber.setVisible(false);
			lblEditContactBudget.setVisible(false);
			tfEditContactBudget.setVisible(false);
			lblEditContactPreApproved.setVisible(false);
			cbEditContactPreapproved.setVisible(false);
			lblEditContactPreAppAmmount.setVisible(false);
			tfEditContactPrepAppAmmount.setVisible(false);
			saveEditedContact.setVisible(false);
			lblEditFirstContact.setVisible(false);
			tfEditFirstContact.setVisible(false);
			editFile.setVisible(true);
			
			customerInformation = tfEditContactFirstName.getText().toUpperCase() + "," + tfEditContactLastName.getText().toUpperCase() + "," + tfEditContactPhoneNumber.getText()
			+ "," + tfEditContactEmail.getText().toUpperCase() + "," + tfEditFirstContact.getText().toUpperCase() + ","  + tfEditContactBudget.getText().replace(',', '`') + "," + cbEditContactPreapproved.isSelected() + "," + tfEditContactPrepAppAmmount.getText().replace(',', '`') ;
			 
			System.out.println(customerInformation);
			File file = new File("Real Estate Contacts.csv");
			String saveMe;
			try
			{
				FileWriter fileWriter = new FileWriter(file, true);
				BufferedWriter writer = new BufferedWriter(fileWriter);
				saveMe = customerInformation;
				writer.write(saveMe, 0, saveMe.length());
				writer.newLine();
				writer.close();
				tfEditContactFirstName.setText(""); tfEditContactLastName.setText("");tfEditContactEmail.setText("");
			}	
			catch(Exception er)
			{
				System.out.println("Message " + er);
			}
			}
			else {
				JOptionPane.showMessageDialog(null, "Please enter a valid contact First Name, Last Name, and Email.");
			}
			 
			////////////////////////////////////////
			
			boolean found = false;
			ArrayList<String> List = new ArrayList<String>();
			String contactFirstName; String contactLastName; String contactPhoneNumber; String contactEmail; String firstContact; String contactBudget; String contactPreApp; String ApprovedAmmount;
	 
			
			try {
				x = new Scanner(new File("Real Estate Contacts.csv"));
				x.useDelimiter("[,\n]");
	 
				while(x.hasNext()) {
					
					contactFirstName = x.next(); contactLastName = x.next(); contactPhoneNumber = x.next();
					contactEmail = x.next(); firstContact = x.next(); contactBudget = x.next();
					contactPreApp = x.next(); ApprovedAmmount = x.next();
					
					List.add(contactFirstName + contactLastName + contactPhoneNumber + contactEmail + firstContact + contactBudget
							+ contactPreApp + ApprovedAmmount);
					
				}
				for(int x = 0; x < List.size(); x ++) {
					if(List.get(x) == "IANCAULEY8505664941IAN4/21/198010`000TRUE10`000") {
						List.remove(x);
					}
				}
				System.out.println(List);
				
				}catch(Exception er){}
		}
		
	}
}