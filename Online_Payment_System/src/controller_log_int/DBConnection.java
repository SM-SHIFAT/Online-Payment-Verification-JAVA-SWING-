package controller_log_int;

import java.net.InetAddress;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JOptionPane;

import model_log_in.AdminPanel;
import net.proteanit.sql.DbUtils;

public class DBConnection
{

    private static Connection connection = null;// host, user,pass
    private static Statement statement = null;
    private static ResultSet resultSet = null;
    private static String uid;

    public static void getconnection()
    {
        String url = "jdbc:mysql://localhost:3306/opm";
        try
        {
            connection = (Connection) DriverManager.getConnection(url, "root", "");

        }
        catch (Exception e)
        {
            System.err.println(e);
        }

    }
    public static int getSize(String sem,String id)
    {
    	
        int num = 0;
        try
        {
            statement = (Statement) connection.createStatement();
            ResultSet count = statement.executeQuery("SELECT `id`, `s_payment` FROM `payment` WHERE id='"+id+"' AND s_payment='"+sem+"'");

            while (count.next())
            {
                num = Integer.parseInt(count.getString(1));
                //System.out.println(num);
            }
        }
        catch (Exception e)
        {
        }
        return num;
    }

    public static int getSize()
    {
        int num = 0;
        try
        {
            statement = (Statement) connection.createStatement();
            ResultSet count = statement.executeQuery("SELECT `s_payment` FROM `payment` WHERE s_payment='"+4+"'");

            while (count.next())
            {
                num = Integer.parseInt(count.getString(1));
                
            }
        }
        catch (Exception e)
        {
        }
        System.out.println("num= "+num);
        return num;
    }
    
    public static ResultSet updatet1(String classname)
    {
    	getconnection();
    	try {
    		//String sql="SELECT * FROM `"+cname+"`";
    		statement = (Statement) connection.createStatement();
    		resultSet  = statement.executeQuery("SELECT * FROM `"+classname+"`");
            //System.out.println("Resultset: "+resultSet);
    	}catch(Exception tt){ System.out.println("updatet1 function: "+tt);
    		
    	}
    	
    	
    	return resultSet;
    }

    public static int getInfo(String sem, String id)
    {
    	getconnection();
        int size =getSize(sem,id);
        //System.out.println(size);
        int data=0;
        int sum=0;
        String s = null;
        try
        {
            statement = (Statement) connection.createStatement();
            resultSet = statement.executeQuery("SELECT `taka`, `id`,`s_payment` FROM `payment` WHERE id='"+id+"' AND s_payment='"+sem+"'");
            
            int i = 0;
            while (resultSet.next())
            {
            	s = resultSet.getString(1); 
                data = Integer.parseInt(s);  
                sum = sum+data;
                //System.out.println(data[i][4]);
                i++;
            }

        }
        catch (Exception e)
        {
            System.err.println(e);
        }
        return sum;
    }
    
    public static String[][] getClassInfo(String id)
    {
    	uid=id;
    	getconnection();
    	int size=getSize();
    	
        String data[][] = new String[size][2];
        try
        {
            statement = (Statement) connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM `classlist` WHERE id='"+id+"'");
            
            int i = 0;
            while (resultSet.next())
            {
                data[i][0] = resultSet.getString(2);
                data[i][1] = resultSet.getString(3);

                //System.out.println(data[i][4]);
                i++;
            }

        }
        catch (Exception e)
        {
            System.err.println(e);
        }
        return data;
    }
    
    public static void deleteData(String uid) {
    	getconnection();
    	try {
    	statement = (Statement) connection.createStatement();
        statement.execute("DELETE FROM `login` WHERE id='"+uid+"'");
    	}
    	catch(Exception dy) {
    		System.out.println("delete function:"+dy);
    	}
    	
    }
    
    public static void insertId(String classname) {
    	getconnection();
    	try {
    	statement = (Statement) connection.createStatement();
        statement.execute("DELETE FROM `login` WHERE id='"+classname+"'");
    	}
    	catch(Exception dy) {
    		System.out.println("delete function:"+dy);
    	}
    	
    }
    public static String getIp() {
    	String ip = null;
    	try {
	    				InetAddress myIP=InetAddress.getLocalHost();
	    			      //System.out.println("My IP Address is:");
	    			      ip = myIP.getHostAddress().toString();
	    				}
	    				catch(Exception y) {}
    	return ip;
    }
    
    public static String getD() {
    	Date date = Calendar.getInstance().getTime();  
        DateFormat dateFormat = new SimpleDateFormat("dd");  
        String strDate = dateFormat.format(date);  
    	
    	return strDate;
    }
    public static void admintable2(String id)
    {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			//Connection con=DriverManager.getConnection("jdbc:mysql://sql12.freesqldatabase.com:3306/sql12360116","sql12360116","9q9uecUdnT");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/opm","root","");
			Statement stmt=con.createStatement();
			String sql="SELECT * FROM `payment` WHERE id='"+id+"' ORDER BY `s_payment` DESC";
			ResultSet rs = stmt.executeQuery(sql);
			AdminPanel.classtable2.setModel(DbUtils.resultSetToTableModel(rs));
			
			System.out.println("table updated");
			con.close();
		}
		catch(Exception t) {JOptionPane.showMessageDialog(null, "Error:"+ t);}
    }
    
    public static Boolean checktxid(String txid) {
    	Boolean bool=false;
    	try {
			Class.forName("com.mysql.jdbc.Driver");
			//Connection con=DriverManager.getConnection("jdbc:mysql://sql12.freesqldatabase.com:3306/sql12360116","sql12360116","9q9uecUdnT");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/opm","root","");
			Statement stmt=con.createStatement();
			
			String sql="SELECT * FROM `verify` WHERE txid= '"+txid+"'";
			ResultSet rs=stmt.executeQuery(sql);
			if(rs.next())
			{bool=true;}
			String sql2 = "SELECT * FROM `payment` WHERE txid='"+txid+"'";
			ResultSet rs2=stmt.executeQuery(sql2);
			if(rs2.next())
			{bool=true;}

				
			con.close();}
			
		
		catch(Exception t) {JOptionPane.showMessageDialog(null, t);}
    	
    	
    	return bool;
    }
    
    public static void data2verify(String txid, String tk, String date) {

		String id="0";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			//Connection con=DriverManager.getConnection("jdbc:mysql://sql12.freesqldatabase.com:3306/sql12360116","sql12360116","9q9uecUdnT");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/opm","root","");
			Statement stmt=con.createStatement();
			String sql="INSERT INTO `verify` (`id`, `txid`, `taka`, `date`) VALUES ('"+id+"', '"+txid+"', '"+tk+"', '"+date+"')";
			int n= stmt.executeUpdate(sql);
			con.close();
			if(n!=0)
				JOptionPane.showMessageDialog(null, "Success");
			else
				JOptionPane.showMessageDialog(null, "Error Studentpane Line: 234");
		}
		catch(Exception t) {JOptionPane.showMessageDialog(null, "Error:"+ t);}
    }
    public static int setPayment(String sem,String id) {
    	int paid = getInfo(sem,id);
    	
    	return paid;
    }
    
    public static void addLogin(String name,String id,String pass,String email,String sem,String type,String fee) {
    	getconnection();
    	
    	try
        {
            statement = (Statement) connection.createStatement();
            int n = statement.executeUpdate("INSERT INTO `login` (`name`, `id`, `password`, `email`, `semester`, `type`, `fees`) VALUES ('"+name+"', '"+id+"', '"+pass+"', '"+email+"', '"+sem+"', '"+type+"', '"+fee+"')");
            if(n!=0);
				
			else
				JOptionPane.showMessageDialog(null, "Error Studentpane Line: 281");
            connection.close();

        }
        catch (Exception e)
        {
        	JOptionPane.showMessageDialog(null, "Error:"+ e);
        }
    }
    public static String updateLogin(String name,String id,String email,String sem,String type,String fee,String uid) {
    	getconnection();
    	try
        {
            statement = (Statement) connection.createStatement();
            Boolean n = statement.execute("UPDATE `login` SET `name`='"+name+"',`id`='"+id+"',`email`='"+email+"',`semester`='"+sem+"',`type`='"+type+"',`fees`='"+fee+"' WHERE id='"+uid+"'");
            if(n==false);
            	
			else
				JOptionPane.showMessageDialog(null, "Error Studentpane Line: 298");
            connection.close();
            return id;
        }
        catch (Exception e)
        {
        	JOptionPane.showMessageDialog(null, "Error:"+ e);
        	return uid;
        }
    }
}
