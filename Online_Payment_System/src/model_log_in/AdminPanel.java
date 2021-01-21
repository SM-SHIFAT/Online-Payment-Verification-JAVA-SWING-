package model_log_in;
import view_log_in.View_Log_In;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.JScrollPane;
import javax.swing.JSeparator;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import controller_log_int.DBConnection;
import net.proteanit.sql.DbUtils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class AdminPanel extends JFrame implements ActionListener {

	    
	    Font font=new Font("Colabary",Font.PLAIN,20);
	    private JTextField idText=new JTextField();
	    public static String id;
	    JTextField uidText=new JTextField();
	    JTextField txidText = new JTextField();
        JTextField tkText = new JTextField();
        JTextField nameText=new JTextField();
    	JTextField emailText=new JTextField();
    	JTextField semText=new JTextField();
    	JTextField typeText=new JTextField();
    	JTextField feeText=new JTextField();
	    JTable classtable;
	    String tblid;
	    JScrollPane cscrol=new JScrollPane();
	    public static JTable classtable2;
	    JScrollPane cscrol2=new JScrollPane();
	    public static View_Log_In viewInfoObj;
	    
	    
	    public void viewButton()
	    {  
	    	JButton searchButton =new JButton();
	    	searchButton.setText("Search");
	    	searchButton.setFont(new Font("Tahoma", Font.BOLD, 15));
	    	searchButton.setBorder(null);
	    	searchButton.setBackground(new Color(77,183,168));
	    	searchButton.setForeground(new Color(255,255,255));
	    	searchButton.setBounds(935,35,70,30);
	        View_Log_In.viewLabel.add(searchButton);
	        searchButton.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		if (idText.getText().isEmpty()==true);
	        		else {
	        		String id = idText.getText().toString();
	        		DBConnection.admintable2(id);
	        		}}});
	        
	        JButton submitButton =new JButton();
	        submitButton.setText("Submit");
	        submitButton.setFont(new Font("Tahoma", Font.BOLD, 15));
	        submitButton.setBorder(null);
	        submitButton.setBackground(new Color(77,183,168));
	        submitButton.setForeground(new Color(255,255,255));
	        submitButton.setBounds(740,540,100,40);
	        View_Log_In.viewLabel.add(submitButton);
	        submitButton.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		if (txidText.getText().isEmpty()==true || tkText.getText().isEmpty()==true);
	        		else {
	        			String txid = txidText.getText().toString();
	        			String tktext = tkText.getText().toString();
	        			String date = StudentPanel.getDate();
	        			Boolean bool= DBConnection.checktxid(txid);
	        			if(bool==false)
	        			DBConnection.data2verify(txid,tktext,date);
	        			else
	        				JOptionPane.showMessageDialog(null, "Trxid Already Exist");
	        		}}});
	    	
	        JButton addButton =new JButton();
	        addButton.setText("Add");
	        addButton.setFont(new Font("Tahoma", Font.BOLD, 28));
	        addButton.setBorder(null);
	        addButton.setForeground(new Color(255,255,255));
	        addButton.setBackground(new Color(88, 214, 141));
	        addButton.setBounds(429,340,180,70);
	        View_Log_In.viewLabel.add(addButton);
	        addButton.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		String name= nameText.getText().toString();
	        		String uid = uidText.getText().toString();
	        		String pass = uidText.getText().toString();
	        		String email = emailText.getText().toString();
	        		String sem = semText.getText().toString();
	        		String type = typeText.getText().toString();
	        		String fee = feeText.getText().toString();
	        		DBConnection.addLogin(name,uid,pass,email,sem,type,fee);
	        		updatet();
	        	}});
	        
	        JButton updateButton =new JButton();
	        updateButton.setText("Update");
	        updateButton.setFont(new Font("Tahoma", Font.BOLD, 28));
	        updateButton.setBorder(null);
	        updateButton.setForeground(new Color(255,255,255));
	        updateButton.setBackground(new Color(93, 173, 226  ));
	        updateButton.setBounds(429,420,180,70);
	        View_Log_In.viewLabel.add(updateButton);
	        updateButton.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		String name= nameText.getText().toString();
	        		String uid = uidText.getText().toString();
	        		String email = emailText.getText().toString();
	        		String sem = semText.getText().toString();
	        		String type = typeText.getText().toString();
	        		String fee = feeText.getText().toString();
	        		tblid = DBConnection.updateLogin(name,uid,email,sem,type,fee,tblid);
	        		
	        		updatet();
	        	}});
	        
	        JButton delButton =new JButton();
	        delButton.setText("Delete");
	        delButton.setFont(new Font("Tahoma", Font.BOLD, 28));
	        delButton.setBorder(null);
	        delButton.setForeground(new Color(255,255,255));
	        delButton.setBackground(new Color(231, 76, 60 ));
	        delButton.setBounds(429,500,180,70);
	        //submitButton.setBorder(border);
	        View_Log_In.viewLabel.add(delButton);
	        delButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent arg0) {delButton.setForeground(new Color(200,200,200));}
				@Override
				public void mouseExited(MouseEvent e) {delButton.setForeground(new Color(255,255,255));}
			});
	        delButton.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		DBConnection.deleteData(uidText.getText().toString());
	        		updatet();

	        	}});
	        
	    }
	    
	    public void viewTable()
	    {
            String data2[][] = {{"0","0","0","0","0","0"}};
            String col2[]={"Name","ID","Email","Semester","type","Fee"};

    		classtable = new JTable(data2, col2);
            //cscrol.setSize(450,235);
            cscrol.setBounds(25, 25, 598, 300);
            //table.setBounds(500, 100, 500, 100);
            classtable.setFont(new Font("Tahoma",Font.PLAIN,17));
            classtable.setRowHeight(23);
            cscrol.setViewportView(classtable);
            View_Log_In.viewLabel.add(cscrol);
            updatet();
            classtable.addMouseListener( new MouseAdapter() {
            	public void mouseClicked(MouseEvent arg0) {
            		DefaultTableModel tblmodel = (DefaultTableModel) classtable.getModel();
            		
            		String tblname = tblmodel.getValueAt(classtable.getSelectedRow(), 0).toString();
            		tblid = tblmodel.getValueAt(classtable.getSelectedRow(), 1).toString();
            		String tblemail = tblmodel.getValueAt(classtable.getSelectedRow(), 2).toString();
            		String tblsem = tblmodel.getValueAt(classtable.getSelectedRow(), 3).toString();
            		String tblfee = tblmodel.getValueAt(classtable.getSelectedRow(), 4).toString();
            		String tbltype = tblmodel.getValueAt(classtable.getSelectedRow(), 5).toString();
            		
            		nameText.setText(tblname);
            		uidText.setText(tblid);
            		emailText.setText(tblemail);
            		semText.setText(tblsem);
            		feeText.setText(tblfee);
            		typeText.setText(tbltype);
            	}});
            ////////////////////////////////////////////////////////////////////////////
            String data[][] = {{"0","0","0","0","0","0"}};
            String col[]={"ID","TrxID","tk","date","S. payment","c. date"};

    		classtable2 = new JTable(data, col);
            cscrol2.setBounds(645, 85, 360, 300);
            classtable2.setFont(new Font("Tahoma",Font.PLAIN,17));
            classtable2.setRowHeight(23);
            cscrol2.setViewportView(classtable2);
            View_Log_In.viewLabel.add(cscrol2);
	    }
	    
	    public void viewTex()
	    {
	    	
	    	
	    	
	    	nameText.setFont(font);
	    	nameText.setText("");
	    	nameText.setForeground(new Color(50,50,50));
	    	nameText.setBounds(120,340,260,30);
	    	nameText.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(77,183,168)));
	        View_Log_In.viewLabel.add(nameText);
	        
	        uidText.setFont(font);
	        uidText.setText("");
	        uidText.setForeground(new Color(50,50,50));
	        uidText.setBounds(120,380,260,30);
	        uidText.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(77,183,168)));
	        View_Log_In.viewLabel.add(uidText);
	        
	        emailText.setFont(font);
	        emailText.setText("");
	        emailText.setForeground(new Color(50,50,50));
	        emailText.setBounds(120,420,260,30);
	        emailText.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(77,183,168)));
	        View_Log_In.viewLabel.add(emailText);
	        
	        semText.setFont(font);
	        semText.setText("");
	        semText.setForeground(new Color(50,50,50));
	        semText.setBounds(120,460,260,30);
	        semText.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(77,183,168)));
	        View_Log_In.viewLabel.add(semText);
	        
	        feeText.setFont(font);
	        feeText.setText("");
	        feeText.setForeground(new Color(50,50,50));
	        feeText.setBounds(120,500,260,30);
	        feeText.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(77,183,168)));
	        View_Log_In.viewLabel.add(feeText);
	        
	        typeText.setFont(font);
	        typeText.setText("");
	        typeText.setForeground(new Color(50,50,50));
	        typeText.setBounds(120,540,260,30);
	        typeText.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(77,183,168)));
	        View_Log_In.viewLabel.add(typeText);
 
	        
	        idText.setFont(font);
	        idText.setText("");
	        //idText.setBorder(BorderFactory.createCompoundBorder(idText.getBorder(), BorderFactory.createEmptyBorder(0, 5, 0, 0)));
	        idText.setForeground(new Color(50,50,50));
	        idText.setBounds(680,35,250,30);
	        idText.setToolTipText("insert an id that you want to Search");
	        idText.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(77,183,168)));
	        View_Log_In.viewLabel.add(idText);
	        
	        
	        
	        txidText.setFont(font);
	        txidText.setText("");
	        txidText.setForeground(new Color(50,50,50));
	        txidText.setBounds(740,450,260,30);
	        txidText.setToolTipText("Insert TrxID");
	        txidText.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(77,183,168)));
	        View_Log_In.viewLabel.add(txidText);
	        
	        tkText.setFont(font);
	        tkText.setText("");
	        tkText.setForeground(new Color(50,50,50));
	        tkText.setBounds(740,490,260,30);
	        tkText.setToolTipText("Insert Amount");
	        tkText.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(77,183,168)));
	        View_Log_In.viewLabel.add(tkText);

	    }

	    
	    public void viewLabel()
	    {
	    	JLabel nameLabel = new JLabel();
	        nameLabel.setText("Name: ");
	        nameLabel.setFont(font);
	        nameLabel.setHorizontalAlignment(SwingConstants.TRAILING);
	        nameLabel.setBounds(40,330,80,50);
	        View_Log_In.viewLabel.add(nameLabel);
	        
	        JLabel uidLabel = new JLabel();
	        uidLabel.setText("ID: ");
	        uidLabel.setFont(font);
	        uidLabel.setHorizontalAlignment(SwingConstants.TRAILING);
	        uidLabel.setBounds(40,370,80,50);
	        View_Log_In.viewLabel.add(uidLabel);
	        
	        JLabel emailLabel = new JLabel();
	        emailLabel.setText("Email: ");
	        emailLabel.setFont(font);
	        emailLabel.setHorizontalAlignment(SwingConstants.TRAILING);
	        emailLabel.setBounds(40,410,80,50);
	        View_Log_In.viewLabel.add(emailLabel);
	        
	        JLabel semLabel = new JLabel();
	        semLabel.setText("Semester: ");
	        semLabel.setFont(font);
	        semLabel.setHorizontalAlignment(SwingConstants.TRAILING);
	        semLabel.setBounds(20,450,100,50);
	        View_Log_In.viewLabel.add(semLabel);
	        
	        JLabel feeLabel = new JLabel();
	        feeLabel.setText("Fee: ");
	        feeLabel.setFont(font);
	        feeLabel.setHorizontalAlignment(SwingConstants.TRAILING);
	        feeLabel.setBounds(40,490,80,50);
	        View_Log_In.viewLabel.add(feeLabel);
	        
	        JLabel typeLabel = new JLabel();
	        typeLabel.setText("Type: ");
	        typeLabel.setFont(font);
	        typeLabel.setHorizontalAlignment(SwingConstants.TRAILING);
	        typeLabel.setBounds(40,530,80,50);
	        View_Log_In.viewLabel.add(typeLabel);
	        
	        JSeparator separator3 = new JSeparator();
			separator3.setBounds(400, 340, 1, 230);
			separator3.setOrientation(SwingConstants.VERTICAL);
			separator3.setForeground(new Color(0, 128, 128));
			View_Log_In.viewLabel.add(separator3);
	    	
	        JLabel idLabel = new JLabel();
	        idLabel.setText("ID: ");
	        idLabel.setFont(font);
	        idLabel.setHorizontalAlignment(SwingConstants.LEADING);
	        idLabel.setBounds(650,25,33,50);
	        View_Log_In.viewLabel.add(idLabel);
	        
	        JSeparator separator = new JSeparator();
			separator.setBounds(635, 10, 1, 600);
			separator.setForeground(new Color(0, 128, 128));
			separator.setOrientation(SwingConstants.VERTICAL);
			View_Log_In.viewLabel.add(separator);
			
			JSeparator separator2 = new JSeparator();
			separator2.setBounds(635, 400, 376, 1);
			separator2.setForeground(new Color(0, 128, 128));
			View_Log_In.viewLabel.add(separator2);
			
			JLabel verifyLabel = new JLabel();
			verifyLabel.setText("Payment Verification");
			verifyLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
			verifyLabel.setForeground(new Color(0, 128, 128));
			verifyLabel.setHorizontalAlignment(SwingConstants.LEADING);
			verifyLabel.setBounds(710,390,200,50);
	        View_Log_In.viewLabel.add(verifyLabel);
			
			JLabel txidLabel = new JLabel();
			txidLabel.setText("TrxID: ");
			txidLabel.setFont(font);
			txidLabel.setHorizontalAlignment(SwingConstants.LEADING);
			txidLabel.setBounds(650,440,80,50);
	        View_Log_In.viewLabel.add(txidLabel);
	        
	        JLabel tkLabel = new JLabel();
	        tkLabel.setText("Amount: ");
	        tkLabel.setFont(font);
	        tkLabel.setHorizontalAlignment(SwingConstants.LEADING);
	        tkLabel.setBounds(650,480,80,50);
	        View_Log_In.viewLabel.add(tkLabel);
	        
	    }
	    
	    public void viewFrame()
	    {
	        viewLabel();
	        viewTex();
	        viewButton();
	        viewTable();
	        
	        Image image=null;
	        ImageIcon icon=new ImageIcon(getClass().getResource("/Image/icon.png"));
	        image=icon.getImage();
	        icon.setImage(image.getScaledInstance(1100,700,Image.SCALE_SMOOTH));
	        View_Log_In.viewLabel.setIcon(icon);
	        
	        JLabel bgLabel = new JLabel("");
			bgLabel.setIcon(new ImageIcon(View_Log_In.class.getResource("/Image/whitebg.png")));
			bgLabel.setBounds(10, 10, 1003, 600);
			//lblNewLabel.setIcon();
			View_Log_In.viewLabel.add(bgLabel);

	    }
	    public void updatet()
	    {
    		try {
				Class.forName("com.mysql.jdbc.Driver");
				
				//Connection con=DriverManager.getConnection("jdbc:mysql://sql12.freesqldatabase.com:3306/sql12360116","sql12360116","9q9uecUdnT");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/opm","root","");
				Statement stmt=con.createStatement();
				String sql="SELECT `name`, `id`, `email`, `semester`, `fees`, `type` FROM `login`";
				ResultSet rs = stmt.executeQuery(sql);
				classtable.setModel(DbUtils.resultSetToTableModel(rs));
				
				System.out.println("table updated");
				con.close();
    		}
    		catch(Exception t) {System.out.println("t:"+t);}
	    }
	     
	    

	    @Override
	    public void actionPerformed(ActionEvent e)
	    {
	    	   
	        if(e.getActionCommand()=="Submit")
	        {
	        }
	    }
	    
	}
