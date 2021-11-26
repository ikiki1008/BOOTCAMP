import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JTextPane;
import javax.swing.JFormattedTextField;
import javax.swing.JEditorPane;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.util.ArrayList;



public class Wb2 extends JFrame {

	private JPanel contentPane;
	static String driver, url;
	static Connection conn;
	static Statement stmt;
	static ResultSet rs;
	static private JTextField textField;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public Wb2() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setForeground(new Color(255, 182, 193));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnSearch = new JButton("\uD655\uC778");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				String tempSql = "select * from momo where textField = '" + textField.getText() + "'";
				dbConnect();
				
				try {
					query("select",tempSql); 
					
				}catch (Exception e1) {
					e1.printStackTrace();
				}	
				try {
					viewData();
				}catch (Exception e1) {
					e1.printStackTrace();
				}	
				dbDis();
			}
		});
		btnSearch.setBounds(106, 173, 95, 23);
		contentPane.add(btnSearch);
		
		JLabel lblNewLabel = new JLabel("\uBC31\uC2E0 \uC811\uC885\uC790 \uD655\uC778 \uC0AC\uC774\uD2B8");
		lblNewLabel.setFont(new Font("����", Font.BOLD, 17));
		lblNewLabel.setBounds(121, 49, 303, 53);
		contentPane.add(lblNewLabel);
		
		JButton btnCancel = new JButton("\uCDE8\uC18C");
		btnCancel.setBounds(226, 173, 95, 23);
		contentPane.add(btnCancel);
		
		textField = new JTextField();
		textField.setBounds(131, 123, 173, 21);
		contentPane.add(textField);
		textField.setColumns(10);
	}
	
	public static void dbConnect() { 
    	driver = "sun.jdbc.odbc.JdbcOdbcDriver";
    	try{
    		Class.forName("com.mysql.jdbc.Driver");
    		System.out.println("����̹� �˻� ����!");        
    	}catch(ClassNotFoundException e){
    		System.err.println("error = " + e);
    	}
        
    	
        url = "jdbc:odbc:vaccine";
        conn = null;
        stmt = null;
        rs = null;
        String url = "jdbc:mysql://localhost/vaccine";
        String sql = "Select * From momo";
		try {
         
            conn = DriverManager.getConnection(url,"root","apmsetup");

            stmt = conn.createStatement( );

            rs = stmt.executeQuery(sql);
            
            System.out.println("�����ͺ��̽� ���� ����");            
         
        }
        catch(Exception e) {
            System.out.println("�����ͺ��̽� ���� ����!");
        }
	}
	
	public static void query(String order, String sql) throws SQLException { //query part
		if (order == "select") {
			rs = stmt.executeQuery(sql);
		} 
		else {
			stmt.executeUpdate(sql);
		}
	}
	
	public static void viewData() throws SQLException { //search part
		if(!rs.next()){
			System.out.println("!rs.next()");

		}
		else{		
				System.out.println("rs.next()");
				textField.setText(String.valueOf(rs.getLong("number")));
			
		}
		
		Connection conn = getConnection();
		//���� ����
		String sql = "SELECT name, number, address, firstV, secondV FROM momo";
		//Statement ���� �� ������ �������� ���
		Statement stmt = conn.createStatement();
		//����� ���� ResultSet ���� �� ��� ���
		ResultSet rs = stmt.executeQuery(sql);
		
		//����� ���� ArrayList����
				ArrayList<UserBean> list = new ArrayList<UserBean>();
				
				//ResultSet�� ��� ����� ArrayList�� ���
				while(rs.next()) {
					UserBean bean = new UserBean();
					bean.setName(rs.getString("name"));
					bean.setNumber(rs.getString("number"));
					bean.setSecondV(rs.getString("secondV"));
					list.add(bean);
				}
				//����� ���
				for(int i=0; i<list.size(); i++) {
					System.out.println("�̸� :"+list.get(i).getName());
					System.out.println("��ȭ��ȣ :"+list.get(i).getNumber());
					System.out.println("2�� ��� ������ :"+list.get(i).getSecondV());
				}
	}
	
	public static void dbDis(){
		try {
			if (conn != null)
				conn.close();
			if (stmt != null)
				stmt.close();
			System.out.println("�����ͺ��̽� ���� ����!");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	private static Connection getConnection() {
		// TODO Auto-generated method stub
		return null;
	}

}
