import java.awt.BorderLayout;
import java.awt.Event;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class MainApp2 extends JFrame {
	
	static JLabel lIme, lPrezime, lTelefon, lAdresa, lUser, lPass, lServer, lBaraj;
	static JTextField  tIme, tPrezime, tTelefon, tAdresa, tUser, tPass, tServer, tBaraj;
	static Object [][] databaseResults;
	static Object [] columns = {"id", "ime", "prezime", "telefon", "adresa", "user", "pass", "server", "jan", "feb", "mar", "apr", "may", "jun", "jul", "aug","sep", "oct", "nov", "dec", "dolg"};
	static ResultSet rows;
	static DefaultTableModel dTableModel = new DefaultTableModel(databaseResults, columns){
		public Class getColumnClass(int column){
			Class returnValue;
			
			if ((column >= 0) && (column < getColumnCount())) {
				 returnValue = getValueAt(0, column).getClass();	
			}else{
				returnValue = Object.class;

			}
				
			 return returnValue;
		
			
		}
		};
	//A Method to Search Users
	public static void FilterUsers(String search){
		TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(dTableModel);
       	table.setRowSorter(tr);
       	tr.setRowFilter(RowFilter.regexFilter(search));	
	}
		
	static JTable table = new JTable(dTableModel);
	public static void main(String[] args) {
		
		JFrame frame = new JFrame("Egzit Application");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
Connection conn = null;
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/demo","root","root");
			Statement sqlState = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			String selectStuff = "SELECT * FROM users";
			rows = sqlState.executeQuery(selectStuff);
			
		Object[] tempRow;
		
		while (rows.next()){
			tempRow = new Object[] {rows.getInt(1), rows.getString(2), rows.getString(3),rows.getString(4),rows.getString(5),rows.getString(6),rows.getString(7),rows.getString(8),
					rows.getInt(9),rows.getInt(10),rows.getInt(11),rows.getInt(12),rows.getInt(13),rows.getInt(14),rows.getInt(15),rows.getInt(16),rows.getInt(17),rows.getInt(18),rows.getInt(19),rows.getInt(20),rows.getInt(21)};
				dTableModel.addRow(tempRow);
			}
		
		}catch (ClassNotFoundException e){
		e.printStackTrace();
	
	}catch (SQLException e1){
		e1.printStackTrace();	
	}
		table.setFont(new Font("Calibri", Font.PLAIN, 12));
		table.setRowHeight(table.getRowHeight() + 10);
		table.getAutoCreateRowSorter();
	
		
		JScrollPane scrollPane = new JScrollPane(table);
		
		frame.add(scrollPane, BorderLayout.CENTER);
		
		JButton addPres = new JButton("Add User");
		 
		 addPres.addActionListener(new ActionListener(){
			 public void actionPerformed(ActionEvent e){
				 String ime = "", prezime = "", telefon = "", adresa = "", user = "",pass = "",server = "";
				 int id = 0, jan = 0, feb = 0, mar = 0, apr = 0, may = 0, jun = 0, jul = 0, aug = 0, sep = 0, oct = 0, nov = 0, dec = 0;
				 
				 try {
					 rows.last();
					 id = rows.getInt(1) +1;
				 }
				 catch (SQLException e4){
					 e4.printStackTrace();
				 }
			
				 ime = tIme.getText();
				 prezime = tPrezime.getText();
				 telefon = tTelefon.getText();
				 adresa = tAdresa.getText();
				 user = tUser.getText();
				 pass = tPass.getText();
				 server = tServer.getText();
				 
				 try {
					 rows.moveToInsertRow();
					 rows.updateString("ime", ime);
					 rows.updateString("prezime", prezime);
					 rows.updateString("telefon", telefon);
					 rows.updateString("adresa", adresa);
					 rows.updateString("user", user);
					 rows.updateString("pass", pass);
					 rows.updateString("server", server);
					 rows.updateInt("feb", feb);
					 rows.updateInt("mar", mar);
					 rows.updateInt("apr", apr);
					 rows.updateInt("may", may);
					 rows.updateInt("jun", jun);
					 rows.updateInt("jul", jul);
					 rows.updateInt("aug", aug);
					 rows.updateInt("sep", sep); 
					 rows.updateInt("oct", oct);
					 rows.updateInt("nov", nov);
					 rows.updateInt("dec", dec);
					 
					 rows.insertRow();
					 rows.updateRow();
				 }
				 catch (SQLException e3){
					 e3.printStackTrace();	 
				 }
				 
				 //We need to initialize the integers 
				 
				
				 
				 
				 Object[] president = {id, ime, prezime, telefon, adresa, user, pass, server};
				 dTableModel.addRow(president);
				 
}
});
		 JButton removePres = new JButton("Remove User");
		 removePres.addActionListener(new ActionListener() {
			 
			 
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dTableModel.removeRow(table.getSelectedRow());
				
				try {
					rows.absolute(table.getSelectedRow());
					rows.deleteRow();
				} catch (SQLException e5) {
					e5.printStackTrace();
				}
				
			}
		});
		 
		 lIme = new JLabel("Ime");
		 lPrezime = new JLabel("Prezime");
		 lTelefon = new JLabel("Telefon");
		 lAdresa = new JLabel("Adresa");
		 lUser = new JLabel("User");
		 lPass = new JLabel("Pass");
		 lServer = new JLabel("Server");
		 lIme = new JLabel("Ime");
		 
		 tIme = new JTextField(12);
		 tPrezime = new JTextField(12);
		 tTelefon = new JTextField(10);
		 tAdresa = new JTextField(12);
		 tUser = new JTextField(15);
		 tPass = new JTextField(15);
		 tServer = new JTextField(8);
		 
		 JPanel inputPanel = new JPanel();
		 inputPanel.add(lIme);
		 inputPanel.add(tIme);
		 inputPanel.add(lPrezime);
		 inputPanel.add(tPrezime);
		 inputPanel.add(lTelefon);
		 inputPanel.add(tTelefon);
		 inputPanel.add(lAdresa);
		 inputPanel.add(tAdresa);
		 inputPanel.add(lUser);
		 inputPanel.add(tUser);
		 inputPanel.add(lPass);
		 inputPanel.add(tPass);
		 inputPanel.add(lServer);
		 inputPanel.add(tServer);
		 inputPanel.add(addPres);
		 inputPanel.add(removePres);
		 
		
		 
		 frame.add(inputPanel, BorderLayout.SOUTH);
		 
		 //Add a new Panel to Seach (Filter) users
		 JPanel inputPanelSearch = new JPanel();
		 
		 lBaraj = new JLabel("Prebaraj");
		 tBaraj = new JTextField(20);
		 
		 inputPanelSearch.add(lBaraj);
		 inputPanelSearch.add(tBaraj);
		 frame.add(inputPanelSearch, BorderLayout.NORTH);
		 
		 
		 //Add a key listener to Ssearch (Filter) users
		 tBaraj.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				String search = tBaraj.getText();
				FilterUsers(search);
				
			}
			
			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		 
		 table.addMouseListener(new MouseAdapter() {
		

				@Override
				public void mouseReleased(MouseEvent me) {
					String value = JOptionPane.showInputDialog(null, "Vnesi nova vrednost : ");
					if (value != null){
						table.setValueAt(value, table.getSelectedRow(), table.getSelectedColumn());
					}
				try {
						rows.absolute(table.getSelectedRow()+ 1);
						String updateCol = dTableModel.getColumnName(table.getSelectedColumn());
						
					
				switch (updateCol){
				case "ime":case "prezime":case "telefon":case "adresa":case "user":case "pass":case "server":
				rows.updateString(updateCol, value);
				rows.updateRow();
				break;
				
				default:
				int intValue = Integer.parseInt(value);
				rows.updateInt(updateCol, intValue);
				rows.updateRow();
				break;
				}
				}
				catch (SQLException e6){
						e6.printStackTrace();
				}
				}
			});
	       	
		 
		 
		 frame.setSize(1200, 800);
		 frame.setVisible(true);
		 
		 
}
}
