package model_log_in;
import view_log_in.View_Log_In;
import controller_log_int.DBConnection;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import javax.swing.JScrollPane;
import javax.swing.JSeparator;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

import java.text.DateFormat;  
import java.text.SimpleDateFormat;  
import java.util.Date;  
import java.util.Calendar;


import net.proteanit.sql.DbUtils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import java.sql.Statement;


public class StudentPanel extends JFrame implements ActionListener {

	    
	    Font font=new Font("Colabary",Font.PLAIN,20);
	    private JTextField semText=new JTextField();
	    private JTextField tkText=new JTextField();
	    private JTextField passText=new JPasswordField();
	    private JTextField feesText=new JTextField();
	    private	JLabel semLabel=new JLabel();
	    private JLabel idLabel=new JLabel();
	    public static String classname=null;
	    public static String sem=null;
	    JTextField totalText =new JTextField();
        JTextField dueText =new JTextField();
	    public static int fee;
		JTable table;
	    //private static String[][] zdata = {{"0","0","0","0"},{"0","0","0","0"}};
	    
	    public static View_Log_In viewInfoObj;
	    public static String id;//= View_Log_In.uid;
	    
	    private JTextField txidText =new JTextField();
    	private JTextField acnameText =new JTextField();
    	private JTextField idText =new JTextField();
    	private JTextField emailText =new JTextField();
	    
    	public void setInfo()
    	{
    		try {
				Class.forName("com.mysql.jdbc.Driver");
				
				//Connection con=DriverManager.getConnection("jdbc:mysql://sql12.freesqldatabase.com:3306/sql12360116","sql12360116","9q9uecUdnT");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/opm","root","");
				Statement stmt=con.createStatement();
				String sql="Select * from login where id='"+id+"'";
				System.out.println(id);
				ResultSet rs=stmt.executeQuery(sql);
				
				if(rs.next())
					{
					
					acnameText.setText(rs.getString(1));
					idText.setText(rs.getString(2));
					passText.setText(rs.getString(3));
					semText.setText(rs.getString(4));
					emailText.setText(rs.getString(5));
					feesText.setText(rs.getString(7));
					sem= rs.getString(5);
					String s=rs.getString(7);
					fee = Integer.parseInt(s);
					}
					
					
				else
					JOptionPane.showMessageDialog(null, "error");
				con.close();
			}catch (Exception y) {JOptionPane.showMessageDialog(null, "this massage showing");}
    		
    	}
    	
    	
    	public void viewTable()
    	{ 
    		String[][] data = {{" "," "}};

    		String col[]={"ID","Date/Time"};

    		table=new JTable(data, col);
            JScrollPane scrol=new JScrollPane();
            scrol.setSize(500,235);
            table.setRowHeight(23);
            scrol.setBounds(425, 50, 553, 330);
            //table.setBounds(500, 100, 500, 100);
            table.setFont(new Font("Tahoma",Font.PLAIN,17));
            scrol.setViewportView(table);
            View_Log_In.viewLabel.add(scrol);
            updatet();
    	}

	    
	    public void viewButton()
	    {
	    	JButton refreshButton =new JButton();
	    	refreshButton.setText("Refresh");
	    	refreshButton.setFont(new Font("Colabary",Font.BOLD,18));
	    	refreshButton.setBounds(425,380,553,30);
	    	refreshButton.setBackground(new Color(77,183,168));
	    	refreshButton.setForeground(new Color(255,255,255));
	    	refreshButton.setBorder(null);
	    	View_Log_In.viewLabel.add(refreshButton);
	    	refreshButton.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		updatet();
	        		viewPayment();
	        	}});
	    	
	    	JButton updateButton =new JButton();
	    	updateButton.setText("Update Info");
	    	updateButton.setFont(new Font("Colabary",Font.BOLD,18));
	    	updateButton.setBounds(50,450,300,40);
	    	updateButton.setBackground(new Color(77,183,168));
	    	updateButton.setForeground(new Color(255,255,255));
	    	updateButton.setBorder(null);
	        View_Log_In.viewLabel.add(updateButton);
	        updateButton.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		String name=acnameText.getText().toString();
	        		String pass =passText.getText().toString();
	        		String email=semText.getText().toString();
	        		String sem=emailText.getText().toString();
	        		String fees=feesText.getText().toString();
	        		StudentPanel.sem=sem;
	        		viewPayment();
	        		
	        		try {
	    				Class.forName("com.mysql.jdbc.Driver");
	    				
	    				//Connection con=DriverManager.getConnection("jdbc:mysql://sql12.freesqldatabase.com:3306/sql12360116","sql12360116","9q9uecUdnT");
	    				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/opm","root","");
	    				Statement stmt=con.createStatement();
	    				String sql="UPDATE `login` SET `name`='"+name+"',`password`='"+pass+"',`email`='"+email+"',`semester`='"+sem+"',`fees`='"+fees+"' WHERE id='"+id+"'";
	    				Boolean n= stmt.execute(sql);
	    				stmt.close();
	    				con.close();
	    				if(n==false)
	    					JOptionPane.showMessageDialog(null, "Success");
	    				else
	    					JOptionPane.showMessageDialog(null, "Error Studentpane Line: 155");
	    				//setInfo();
	        		}
	        		catch(Exception t) {JOptionPane.showMessageDialog(null, t);}
	        		}});
	    	
	    	JButton submitButton =new JButton();
	    	submitButton.setText("Submit");
	    	submitButton.setFont(new Font("Colabary",Font.BOLD,18));
	    	submitButton.setBounds(880,450,95,90);
	    	submitButton.setBackground(new Color(77,183,168));
	    	submitButton.setForeground(new Color(255,255,255));
	    	submitButton.setBorder(null);
	        View_Log_In.viewLabel.add(submitButton);
	        submitButton.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		String txid= txidText.getText().toString();
	        		String tk = tkText.getText().toString();
	        		String cdate = getDate();
	        		String sem = emailText.getText().toString();
	        		
	        		try {
	    				Class.forName("com.mysql.jdbc.Driver");
	    				//Connection con=DriverManager.getConnection("jdbc:mysql://sql12.freesqldatabase.com:3306/sql12360116","sql12360116","9q9uecUdnT");
	    				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/opm","root","");
	    				Statement stmt=con.createStatement();
	    				String sql="SELECT * FROM `verify` WHERE txid= '"+txid+"' AND taka='"+tk+"'";
	    				ResultSet rs=stmt.executeQuery(sql);
	    				
	    				if(rs.next())
	    				{
	    					String date =rs.getString(4);
	    					//Delete data
	    					String sql2="DELETE FROM `verify` WHERE txid='"+txid+"' ";
	    					String sql3 = "INSERT INTO `payment`(`id`, `txid`, `taka`, `date`, `s_payment`, `c_date`) VALUES ('"+id+"','"+txid+"','"+tk+"','"+date+"','"+sem+"','"+cdate+"')";
	    					//try {
		    				stmt.execute(sql2);
		    				int n= stmt.executeUpdate(sql3);
		    				if(n!=0)
		    					JOptionPane.showMessageDialog(null, "Success");
		    				else
		    					JOptionPane.showMessageDialog(null, "Error Studentpane Line: 234");
		    				
	    					//}catch(Exception dt) {System.out.println(":198:"+dt);	}
	    					
	    					
	    				}else {
	    					JOptionPane.showMessageDialog(null, "Trxid not Found.");
	    				}
	    				con.close();
	    				
	        		}
	        		catch(Exception t) {System.out.println(":207:"+t);}
	        		}});

	    }
	    public void viewTex()
	    {
	    	
	    	txidText.setFont(font);
	    	txidText.setText("");
	    	txidText.setEditable(true);
	    	txidText.setForeground(new Color(50,50,50));
	    	txidText.setBackground(new Color(232, 248, 245));
	    	txidText.setBounds(520,450,350,40);
	    	txidText.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(77,183,168)));
	        View_Log_In.viewLabel.add(txidText);
	        
	    	    
	        acnameText.setFont(font);
	        acnameText.setText("");
	        acnameText.setForeground(new Color(50,50,50));
	        acnameText.setBounds(105,150,255,30);
	        acnameText.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(77,183,168)));
	        View_Log_In.viewLabel.add(acnameText);
	        
	        idText.setFont(font);
	        idText.setText("");
	        idText.setEditable(false);
	        idText.setForeground(new Color(50,50,50));
	        idText.setBounds(75,200,285,30);
	        idText.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(77,183,168)));
	        View_Log_In.viewLabel.add(idText);
	        
	        passText.setFont(font);
	        passText.setText("");
	        passText.setForeground(new Color(50,50,50));
	        passText.setBounds(140,250,220,30);
	        passText.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(77,183,168)));
	        View_Log_In.viewLabel.add(passText);

	        emailText.setFont(font);
	        emailText.setText("");
	        emailText.setForeground(new Color(50,50,50));
	        emailText.setBounds(140,300,220,30);
	        emailText.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(77,183,168)));
	        View_Log_In.viewLabel.add(emailText);
	        
	        feesText.setFont(font);
	        feesText.setText("");
	        feesText.setForeground(new Color(50,50,50));
	        feesText.setBounds(110,400,250,30);
	        feesText.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(77,183,168)));
	        View_Log_In.viewLabel.add(feesText);
	        
	        semText.setFont(font);
	        semText.setText("");
	        semText.setForeground(new Color(50,50,50));
	        semText.setBounds(100,350,260,30);
	        semText.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(77,183,168)));
	        View_Log_In.viewLabel.add(semText);
	        
	        tkText.setFont(font);
	        tkText.setText("");
	        tkText.setToolTipText("Amount you transfer!");
	        tkText.setEditable(true);
	        tkText.setForeground(new Color(50,50,50));
	        tkText.setBackground(new Color(232, 248, 245));
	        tkText.setBounds(520,500,350,40);
	        tkText.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(77,183,168)));
	        View_Log_In.viewLabel.add(tkText);
	        
	        
	        totalText.setFont(font);
	        totalText.setText("");
	        totalText.setEditable(false);
	        totalText.setForeground(new Color(50,50,50));
	        totalText.setBounds(180,500,150,30);
	        totalText.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(77,183,168)));
	        View_Log_In.viewLabel.add(totalText);
	        
	        dueText.setFont(font);
	        dueText.setText("");
	        dueText.setEditable(false);
	        dueText.setForeground(new Color(50,50,50));
	        dueText.setBounds(180,540,150,30);
	        dueText.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(77,183,168)));
	        View_Log_In.viewLabel.add(dueText);
	    }
	    		    
	    
	    
	    public void viewLabel()
	    {
	        
	        JLabel feesLabel=new JLabel();
	    	
	        
	        JLabel nameLabel= new JLabel();
	        JLabel titleLabel=new JLabel();
	        JLabel cclassLabel =new JLabel();
	        
	        JLabel acnameLabel=new JLabel();
	        JLabel passLabel=new JLabel();
	        JLabel emailLabel=new JLabel();
	        JLabel profileLabel=new JLabel();
	        JLabel txidLabel=new JLabel();
	        JLabel tkLabel=new JLabel();
	        
	        txidLabel.setText("TrxID: ");
	        txidLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
	        txidLabel.setHorizontalAlignment(SwingConstants.TRAILING);
	        txidLabel.setBounds(425,450,95,50);
	        View_Log_In.viewLabel.add(txidLabel);
	        
	        tkLabel.setText("Paid(Tk): ");
	        tkLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
	        tkLabel.setHorizontalAlignment(SwingConstants.TRAILING);
	        tkLabel.setBounds(425,500,95,50);
	        View_Log_In.viewLabel.add(tkLabel);
	        		
	        cclassLabel.setText("Verify your payment: ");
	        cclassLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
	        cclassLabel.setBounds(520, 400, 310, 50);
	        cclassLabel.setForeground(new Color(0, 128, 128));
	        View_Log_In.viewLabel.add(cclassLabel);
	        
	        JSeparator separator3 = new JSeparator();
			separator3.setBounds(398, 412, 597, 1);
			separator3.setForeground(new Color(0, 128, 128));
			//separator2.setBackground(new Color(0, 128, 128));
			View_Log_In.viewLabel.add(separator3);
	        
	        titleLabel.setText("Welcome to");
	        titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
	        titleLabel.setBounds(145, 30, 500, 50);
	        titleLabel.setForeground(new Color(0, 128, 128));
	        View_Log_In.viewLabel.add(titleLabel);
	        
	        //nameLabel.setForeground(Color.BLACK);
	        //nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
	        //nameLabel.setFont(new Font("Tahoma", Font.BOLD, 28));
	        nameLabel.setText("Online Payment Verification System");
	        nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 19));
	        nameLabel.setBounds(50, 55, 310, 50);
	        nameLabel.setForeground(new Color(0, 128, 128));
	        View_Log_In.viewLabel.add(nameLabel);
	        
	        JSeparator separator = new JSeparator();
			separator.setBounds(26, 100, 350, 1);
			separator.setForeground(new Color(0, 128, 128));
			View_Log_In.viewLabel.add(separator);
			
			profileLabel.setText("Profile ");
			profileLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
			profileLabel.setBounds(161, 100, 100, 50);
			profileLabel.setForeground(new Color(0, 128, 128));
	        View_Log_In.viewLabel.add(profileLabel);
	        
	        
	        acnameLabel.setText("Name: ");
	        acnameLabel.setFont(font);
	        acnameLabel.setHorizontalAlignment(SwingConstants.LEADING);
	        acnameLabel.setBounds(40,140,65,50);
	        View_Log_In.viewLabel.add(acnameLabel);
	        
	        idLabel.setText("ID: ");
	        idLabel.setFont(font);
	        idLabel.setHorizontalAlignment(SwingConstants.LEADING);
	        idLabel.setBounds(40,190,34,50);
	        View_Log_In.viewLabel.add(idLabel);
	        
	        passLabel.setText("Password: ");
	        passLabel.setFont(font);
	        passLabel.setHorizontalAlignment(SwingConstants.LEADING);
	        passLabel.setBounds(40,240,100,50);
	        View_Log_In.viewLabel.add(passLabel);
	        
	        emailLabel.setText("Semester: ");
	        emailLabel.setFont(font);
	        emailLabel.setHorizontalAlignment(SwingConstants.LEADING);
	        emailLabel.setBounds(40,290,100,50);
	        View_Log_In.viewLabel.add(emailLabel);
	        
	        semLabel.setText("Email: ");
	        semLabel.setFont(font);
	        semLabel.setHorizontalAlignment(SwingConstants.LEADING);
	        semLabel.setBounds(40,340,60,50);
	        View_Log_In.viewLabel.add(semLabel);
	        
	        feesLabel.setText("S. fee: ");
	        feesLabel.setFont(font);
	        feesLabel.setHorizontalAlignment(SwingConstants.LEADING);
	        feesLabel.setBounds(40,390,65,50);
	        View_Log_In.viewLabel.add(feesLabel);
	        
	        JSeparator separator2 = new JSeparator();
			separator2.setBounds(26, 440, 350, 1);
			separator2.setForeground(new Color(0, 128, 128));
			//separator2.setBackground(new Color(0, 128, 128));
			View_Log_In.viewLabel.add(separator2);
			
			JLabel totalLabel = new JLabel();
			totalLabel.setText("Total Paid: ");
			totalLabel.setFont(font);
			totalLabel.setHorizontalAlignment(SwingConstants.LEADING);
			totalLabel.setBounds(40,490,120,50);
	        View_Log_In.viewLabel.add(totalLabel);
	        
	        JLabel dueLabel = new JLabel();
	        dueLabel.setText("Due Payment: ");
	        dueLabel.setFont(font);
	        dueLabel.setHorizontalAlignment(SwingConstants.LEADING);
	        dueLabel.setBounds(40,530,130,50);
	        View_Log_In.viewLabel.add(dueLabel);
	        
	    }
	    public void viewPayment(){
	    	int paid = DBConnection.setPayment(sem,id);
	    	totalText.setText(String.valueOf(paid));
	    	int due = fee-paid;
	    	dueText.setText(String.valueOf(due));
	    }
	    
	    public void viewFrame()
	    {
	        viewLabel();
	        viewTex();
	        viewButton();
	        setInfo();
	        viewPayment();
	        viewTable();

	        
	        Image image=null;
	        ImageIcon icon=new ImageIcon(getClass().getResource("/Image/icon.png"));
	        image=icon.getImage();
	        icon.setImage(image.getScaledInstance(1100,700,Image.SCALE_SMOOTH));
	        View_Log_In.viewLabel.setIcon(icon);
	        
	        JLabel bgLabel = new JLabel("");
			bgLabel.setIcon(new ImageIcon(View_Log_In.class.getResource("/Image/infopanel.png")));
			bgLabel.setBounds(10, 10, 1003, 600);
			//lblNewLabel.setIcon();
			View_Log_In.viewLabel.add(bgLabel);

	    }
	    public static String getDate() {
	    	Date date = Calendar.getInstance().getTime();  
            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");  
            String strDate = dateFormat.format(date);  
            System.out.println(strDate);
	    	
	    	return strDate;
	    }
	    
	    public void updatet()
	    {
    		try {
				Class.forName("com.mysql.jdbc.Driver");
				
				//Connection con=DriverManager.getConnection("jdbc:mysql://sql12.freesqldatabase.com:3306/sql12360116","sql12360116","9q9uecUdnT");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/opm","root","");
				Statement stmt=con.createStatement();
				String sql="SELECT * FROM `payment` WHERE id='"+id+"' ORDER BY `s_payment` DESC";
				ResultSet rs = stmt.executeQuery(sql);
				table.setModel(DbUtils.resultSetToTableModel(rs));
				
				System.out.println("table updated");
				con.close();
    		}
    		catch(Exception t) {System.out.println("table:"+t);}
	    }

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			
		}

}
	        
	    

