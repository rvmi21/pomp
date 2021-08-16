package SQL;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.zip.GZIPInputStream;

import com.mysql.jdbc.Blob;

import java.nio.file.*;

import UI.Home;
import UI.QRSave;

public class SQLManager {
	
    public static String getext (String info) {
        try {
            String url = "jdbc:mysql://localhost/dbpfe";
            Connection conn = DriverManager.getConnection(url,"root","");
            Statement stmt = conn.createStatement();
            ResultSet rs;
            
            String ext = "";
            
            rs = stmt.executeQuery("SELECT Type FROM files WHERE Filename = "+"'"+info+"'");
            while ( rs.next() ) {
                ext = rs.getString("Type");
                return ext ;
            }
            conn.close();
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return "";
    }
	
	public static String readFile(String fileName) throws IOException {
	    BufferedReader br = new BufferedReader(new FileReader(fileName));
	    try {
	        StringBuilder sb = new StringBuilder();
	        String line = br.readLine();

	        while (line != null) {
	            sb.append(line);
	            sb.append("\n");
	            line = br.readLine();
	        }
	        return sb.toString();
	    } finally {
	        br.close();
	    }
	}
	
    public static void readPicture(String filename) {
        // update sql
        String selectSQL = "SELECT Content FROM files WHERE Filename=?";
        ResultSet rs = null;
        FileOutputStream fos = null;
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            //connecto
	        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
     	    String mysqlUrl = "jdbc:mysql://localhost/dbpfe";
		    Connection con = DriverManager.getConnection(mysqlUrl, "root", "");
		    System.out.println("Connection established......");
		    //connecto
		    
		    //dozan
            pstmt = con.prepareStatement(selectSQL);
            pstmt.setString(1, filename);
            rs = pstmt.executeQuery();
            //dozan
            // write binary stream into file
            File file = new File("ppnyar"+".png");
            fos = new FileOutputStream(file);

            System.out.println("Writing BLOB to file " + file.getAbsolutePath());
            while (rs.next()) {
                InputStream input = rs.getBinaryStream("Content");
                byte[] buffer = new byte[1024];
                while (input.read(buffer) > 0) {
                    fos.write(buffer);
                }
            }
        } catch (SQLException | IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }

                if (conn != null) {
                    conn.close();
                }
                if (fos != null) {
                    fos.close();
                }

            } catch (SQLException | IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
	
	   public static int countFilesuser(String user) throws Exception {
		   
		   System.out.println("count Fileuploader = "+ user);
		   
		      DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		      String mysqlUrl = "jdbc:mysql://localhost/dbpfe";
		      Connection con = DriverManager.getConnection(mysqlUrl, "root", "");
		      System.out.println("Connection established......");
		      Statement stmt = con.createStatement();
		      String query = "select count(*) from files where Owner = '"+user+"'";
		      ResultSet rs = stmt.executeQuery(query);
		      rs.next();
		      int count = rs.getInt(1);
		      System.out.println("Number of records in the cricketers_data table: "+count);
		      return count ;
		   }
	
    public static String [][] FileUploaderuser (String user) {
        try {
        	
        	System.out.println("Fileuploader = "+ user);
        	
        	DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            String url = "jdbc:mysql://localhost/dbpfe";
            Connection conn = DriverManager.getConnection(url,"root","");
            Statement stmt = conn.createStatement();
            ResultSet rs;

            int n = countFilesuser(user);
            
            rs = stmt.executeQuery("SELECT * FROM files where Owner = '"+user+"'");
            int i = 0 ;
            String [][] data = new String [n][4]; 
            while ( rs.next() ) {
                data[i][0] = rs.getString("Filename");
                data[i][1] = rs.getString("Description");
                data[i][2] = rs.getString("Owner");
                data[i][3] = rs.getString("Type");
                System.out.println(data[i][0] +","+ data[i][1] +","+ data[i][2] +","+ data[i][3]);
                i++;
                
            }
            conn.close();
            return data ;
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
            return null ;
        }
    }
	  public static void Delete(String info) {
		    String jdbcUrl = "jdbc:mysql://localhost/dbpfe";
		    String username = "root";
		    String password = "";
		    String sql = "delete from files where Filename = '"+info+"'";
		    
		    System.out.println(sql);

		    try (Connection conn = DriverManager.getConnection(jdbcUrl, username, password); 
		        Statement stmt = conn.createStatement();) {
		      
		      stmt.executeUpdate(sql);
		      System.out.println("Record deleted successfully");
		    } catch (SQLException e) {
		      e.printStackTrace();
		    }
		  }
	
	  public static String ext(String path) {
		    File file = new File(path);

		    // convert the file name into string
		    String fileName = file.toString();

		    int index = fileName.lastIndexOf('.');
		    if(index > 0) {
		      String extension = fileName.substring(index + 1);
		      System.out.println("File extension is " + extension);
		      return extension;
		    }
		    return null;
		  }
	
	   public static int countFiles() throws Exception {
		      DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		      String mysqlUrl = "jdbc:mysql://localhost/dbpfe";
		      Connection con = DriverManager.getConnection(mysqlUrl, "root", "");
		      System.out.println("Connection established......");
		      Statement stmt = con.createStatement();
		      String query = "select count(*) from files";
		      ResultSet rs = stmt.executeQuery(query);
		      rs.next();
		      int count = rs.getInt(1);
		      System.out.println("Number of records in the cricketers_data table: "+count);
		      return count ;
		   }
	
    public static String [][] FileUploader () {
        try {
        	DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            String url = "jdbc:mysql://localhost/dbpfe";
            Connection conn = DriverManager.getConnection(url,"root","");
            Statement stmt = conn.createStatement();
            ResultSet rs;

            int n = countFiles();
            
            rs = stmt.executeQuery("SELECT * FROM files");
            int i = 0 ;
            String [][] data = new String [n][4]; 
            while ( rs.next() ) {
                data[i][0] = rs.getString("Filename");
                data[i][1] = rs.getString("Description");
                data[i][2] = rs.getString("Owner");
                data[i][3] = rs.getString("Type");
                System.out.println(data[i][0] +","+ data[i][1] +","+ data[i][2] +","+ data[i][3]);
                i++;
                
            }
            conn.close();
            return data ;
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
            return null ;
        }
    }
	
	   public static void FileInsert(String Owner, String name, String desc, String path, String ext ,String txt) throws Exception{
		      //Registering the Driver
		      DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		      //Getting the connection
		      String mysqlUrl = "jdbc:mysql://localhost/dbpfe";
		      Connection con = DriverManager.getConnection(mysqlUrl, "root", "");
		      System.out.println("Connection established......");

		      //Inserting values
		      String query = "INSERT INTO files(Owner,Filename,Description,Type,Content,Text) VALUES (?,?,?,?,?,?)";
		      PreparedStatement pstmt = con.prepareStatement(query);
		      pstmt.setString(1, Owner);
		      FileInputStream fin = new FileInputStream(path);
		      pstmt.setString(2, name);
		      pstmt.setString(3, desc);
		      pstmt.setString(4,ext);
		      pstmt.setBinaryStream(5, fin);
		      pstmt.setString(6,txt);
		      pstmt.execute();
		      System.out.println("Record inserted .....");
		   }
	
	public static void insertReg(String query, String QRPath, String Owner){
	    Connection conn = null;
	    Statement stmt = null;
	    try {
	       try {
	          Class.forName("com.mysql.jdbc.Driver");
	       } catch (Exception e) {
	          System.out.println(e);
	    }
	    conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/dbpfe", "root", "");
	    System.out.println("Connection is created successfully:");
	    stmt = (Statement) conn.createStatement();
	    stmt.executeUpdate(query);
	    System.out.println("Record is inserted in the table successfully..................");
	    QRSave QRSaveFrame = new QRSave(QRPath);
	    QRSaveFrame.qro(null, QRPath);
	    
	    Home homeFrame = new Home(QRPath,Owner);
	    homeFrame.sain(null, QRPath, Owner);
	    
	    
	    } catch (SQLException excep) {
	       excep.printStackTrace();
	    } catch (Exception excep) {
	       excep.printStackTrace();
	    } finally {
	       try {
	          if (stmt != null)
	             conn.close();
	       } catch (SQLException se) {}
	       try {
	          if (conn != null)
	             conn.close();
	       } catch (SQLException se) {
	          se.printStackTrace();
	       }  
	    }
	    System.out.println("Please check it in the MySQL Table......... ……..");
	}
	
	public static String[] InfoUser(String QR) {
        try {
            String url = "jdbc:mysql://localhost/dbpfe";
            Connection conn = DriverManager.getConnection(url,"root","");
            Statement stmt = conn.createStatement();
            ResultSet rs;
 
            rs = stmt.executeQuery("SELECT * FROM users WHERE QRB64 = '"+QR+"'");
            String info[] = new String[4];
            while ( rs.next() ) {
                info[0] = rs.getString("Email");
                info[1] = rs.getString("FirstName");
                info[2] = rs.getString("LastName");
                info[3] = rs.getString("Password");
                System.out.println(info[0]);
                return info;
            }
            conn.close();
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
		return null;
		
	}
}

