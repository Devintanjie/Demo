package com.Devin.SmartGreen.text1;

 
 import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
 import java.util.Scanner;
/**
  * 
 * @ClassName: MainClass 
 * @Description: �ַ�����ʽ"@a@b@c",a,b,c�ᱻ����Ϊ������Ԫ��
 * @author luxishi
 * @date 2016��3��8�� ����5:57:56 
 *
  */
public class CalcString { 
     public  static void main(String[] args) {
        String m_sentence="@id=5@time=25@date=25";
        List<String> m_list=cutIfString(m_sentence);
        System.out.println(m_list.size());
         for(String tmp:m_list){
           System.out.println(tmp);
         }
         SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//�������ڸ�ʽ
         String time=df.format(new Date());
         System.out.println(time);
         String content="fal";
         
         
     }
  
    public static List<String> cutstring(String Stence)
     {
         List<String> stringlist=new ArrayList<String>();//�����洢����������Ԫ��
         for(int i=0;i<Stence.length();i++)
        {
             if(Stence.charAt(i)=='#')
           { 
              String temp="";//�洢����
               int wordlength=i;                 
               while(wordlength<Stence.length()-1&&Stence.charAt(++wordlength)!='#')                 {
                    temp+=Stence.charAt(wordlength);
                     //System.out.println(temp);
                 }
                stringlist.add(temp);
             }
         }
        return stringlist;      
        }
    /*
     *    @��ȡϵͳʱ��
     */
    public static String getTime() {
    	SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        String time=df.format(new Date());
        time=time.substring(8, 14);
       
        return time;
    }
    /*
     * @��ȡϵͳ����
     */
    public static String getDate() {
    	SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        String date=df.format(new Date());
        date=date.substring(0,8);
    	return date;
    }
   
    /*
     * @ �������ݿ��������� fal
     */
	public static void putAllData(Socket socket ,String content ) throws IOException {
		
			if(content.length()>=3) {
				if(content.startsWith("f")) {
					if(content.substring(1,3).equals("al")) {
		    			try {
		    	            Class.forName("com.mysql.jdbc.Driver");
		    	        } catch (ClassNotFoundException e) {
		    	            e.printStackTrace();
		    	        }
		    		 PrintStream ps;
		    		 ps = new PrintStream(socket.getOutputStream());
		    	     try (Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/my_database1?characterEncoding=UTF-8",
		    	               "root", "1234"); Statement s = c.createStatement();) {
		    	 
		    	          String sql = "select * from SmartGreen";
		    	 
		    	            // ִ�в�ѯ��䣬���ѽ�������ظ�ResultSet
		    	          ResultSet rs = s.executeQuery(sql);
		    	          ps.println("id      ����   	  ʱ��    ����id    �¶�    ʪ��    �絼��    CO2Ũ��    ����ǿ��");
		    	          while (rs.next()) {
		    	                int id = rs.getInt("id");// ����ʹ���ֶ���
		    	                String date = rs.getString(2);// Ҳ����ʹ���ֶε�˳��
		    	                String time = rs.getString(3);
		    	                int smid = rs.getInt(4);
		    	                float t1 = rs.getFloat(5);
		    	                float t2 = rs.getFloat(6);
		    	                float g = rs.getFloat(7);
		    	                float c1 =rs.getFloat(8);
		    	                int light=rs.getInt(9);
		    	                ps.printf("%d\t %s\t %s\t %d\t ", id, date, time, smid);
		    	                ps.printf("%.2f\t %.2f\t %.2f\t %.2f\t %d\n",t1,t2,g,c1,light);
		    	                
		    	            }
		    	            // ��һ��Ҫ������ر�ReultSet����ΪStatement�رյ�ʱ�򣬻��Զ��ر�ResultSet
		    	            // rs.close();
		    	 
		    	        } catch (SQLException e) {
		    	            // TODO Auto-generated catch block
		    	            e.printStackTrace();
		    	        }
		    		}
				}
				
			
    		
    	}
		
		
	}
	
	//�������ݿⲿ������ ��id��ֱ��ѯ fidc
	public static void getDataByIDC(Socket socket ,String content ) throws IOException {
		if(content.length()>=4) {
			if(content.startsWith("f")) {
				if(content.substring(1,4).equals("idc")) {
	    			try {
	    	            Class.forName("com.mysql.jdbc.Driver");
	    	        } catch (ClassNotFoundException e) {
	    	            e.printStackTrace();
	    	        }
	    		 PrintStream ps;
	    		 ps = new PrintStream(socket.getOutputStream());
	    	     try (Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/my_database1?characterEncoding=UTF-8",
	    	               "root", "1234"); Statement s = c.createStatement();) {
	    	 
	    	          String sql = "select * from SmartGreen";
	    	 
	    	            // ִ�в�ѯ��䣬���ѽ�������ظ�ResultSet
	    	          ResultSet rs = s.executeQuery(sql);
	    	          
	    	          while (rs.next()) {
	    	                int id = rs.getInt("id");// ����ʹ���ֶ���
	    	                String date = rs.getString(2);// Ҳ����ʹ���ֶε�˳��
	    	                String time = rs.getString(3);
	    	                int smid = rs.getInt(4);
	    	                float t1 = rs.getFloat(5);
	    	                float t2 = rs.getFloat(6);
	    	                float g = rs.getFloat(7);
	    	                float c1 =rs.getFloat(8);
	    	                
	    	                ps.println(id);
	    	                
	    	            }
	    	            // ��һ��Ҫ������ر�ReultSet����ΪStatement�رյ�ʱ�򣬻��Զ��ر�ResultSet
	    	            // rs.close();
	    	 
	    	        } catch (SQLException e) {
	    	            // TODO Auto-generated catch block
	    	            e.printStackTrace();
	    	        }
	    		}
			}
			
		
		
		}
		
	}
	//	��������ֱ��ѯ
	public static void getDataByDateC(Socket socket ,String content ) throws IOException {
		if(content.length()>=4) {
			if(content.startsWith("f")) {
				if(content.substring(1,4).equals("dac")) {
	    			try {
	    	            Class.forName("com.mysql.jdbc.Driver");
	    	        } catch (ClassNotFoundException e) {
	    	            e.printStackTrace();
	    	        }
	    		 PrintStream ps;
	    		 ps = new PrintStream(socket.getOutputStream());
	    	     try (Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/my_database1?characterEncoding=UTF-8",
	    	               "root", "1234"); Statement s = c.createStatement();) {
	    	 
	    	          String sql = "select * from SmartGreen";
	    	 
	    	            // ִ�в�ѯ��䣬���ѽ�������ظ�ResultSet
	    	          ResultSet rs = s.executeQuery(sql);
	    	          
	    	          while (rs.next()) {
	    	                int id = rs.getInt("id");// ����ʹ���ֶ���
	    	                String date = rs.getString(2);// Ҳ����ʹ���ֶε�˳��
	    	                String time = rs.getString(3);
	    	                int smid = rs.getInt(4);
	    	                float t1 = rs.getFloat(5);
	    	                float t2 = rs.getFloat(6);
	    	                float g = rs.getFloat(7);
	    	                float c1 =rs.getFloat(8);
	    	                ps.println(date);
	    	                
	    	            }
	    	            // ��һ��Ҫ������ر�ReultSet����ΪStatement�رյ�ʱ�򣬻��Զ��ر�ResultSet
	    	            // rs.close();
	    	 
	    	        } catch (SQLException e) {
	    	            // TODO Auto-generated catch block
	    	            e.printStackTrace();
	    	        }
	    		}
			}
		}
	}
	//	��ʱ���ѯ
	public static void getDataByTimeC(Socket socket ,String content ) throws IOException {
	
		if(content.length()>=4) {
			if(content.startsWith("f")) {
				if(content.substring(1,4).equals("tic")) {
	    			try {
	    	            Class.forName("com.mysql.jdbc.Driver");
	    	        } catch (ClassNotFoundException e) {
	    	            e.printStackTrace();
	    	        }
	    		 PrintStream ps;
	    		 ps = new PrintStream(socket.getOutputStream());
	    	     try (Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/my_database1?characterEncoding=UTF-8",
	    	               "root", "1234"); Statement s = c.createStatement();) {
	    	 
	    	          String sql = "select * from SmartGreen";
	    	 
	    	            // ִ�в�ѯ��䣬���ѽ�������ظ�ResultSet
	    	          ResultSet rs = s.executeQuery(sql);
	    	          
	    	          while (rs.next()) {
	    	                int id = rs.getInt("id");// ����ʹ���ֶ���
	    	                String date = rs.getString(2);// Ҳ����ʹ���ֶε�˳��
	    	                String time = rs.getString(3);
	    	                int smid = rs.getInt(4);
	    	                float t1 = rs.getFloat(5);
	    	                float t2 = rs.getFloat(6);
	    	                float g = rs.getFloat(7);
	    	                float c1 =rs.getFloat(8);
	    	                ps.println(time);
	    	                
	    	            }
	    	            // ��һ��Ҫ������ر�ReultSet����ΪStatement�رյ�ʱ�򣬻��Զ��ر�ResultSet
	    	            // rs.close();
	    	 
	    	        } catch (SQLException e) {
	    	            // TODO Auto-generated catch block
	    	            e.printStackTrace();
	    	        }
	    		}
			}
		}
	
	
	
	}
	public static void getDataBySMIDC(Socket socket ,String content ) throws IOException {
		
		if(content.length()>=4) {
			if(content.startsWith("f")) {
				if(content.substring(1,4).equals("sic")) {
	    			try {
	    	            Class.forName("com.mysql.jdbc.Driver");
	    	        } catch (ClassNotFoundException e) {
	    	            e.printStackTrace();
	    	        }
	    		 PrintStream ps;
	    		 ps = new PrintStream(socket.getOutputStream());
	    	     try (Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/my_database1?characterEncoding=UTF-8",
	    	               "root", "1234"); Statement s = c.createStatement();) {
	    	 
	    	          String sql = "select * from SmartGreen";
	    	 
	    	            // ִ�в�ѯ��䣬���ѽ�������ظ�ResultSet
	    	          ResultSet rs = s.executeQuery(sql);
	    	          
	    	          while (rs.next()) {
	    	                int id = rs.getInt("id");// ����ʹ���ֶ���
	    	                String date = rs.getString(2);// Ҳ����ʹ���ֶε�˳��
	    	                String time = rs.getString(3);
	    	                int smid = rs.getInt(4);
	    	                float t1 = rs.getFloat(5);
	    	                float t2 = rs.getFloat(6);
	    	                float g = rs.getFloat(7);
	    	                float c1 =rs.getFloat(8);
	    	                ps.println(smid);
	    	                
	    	            }
	    	            // ��һ��Ҫ������ر�ReultSet����ΪStatement�رյ�ʱ�򣬻��Զ��ر�ResultSet
	    	            // rs.close();
	    	 
	    	        } catch (SQLException e) {
	    	            // TODO Auto-generated catch block
	    	            e.printStackTrace();
	    	        }
	    		}
			}
		}
	
	
	
	}
	public static void getDataByTemC(Socket socket ,String content ) throws IOException {
		
		if(content.length()>=4) {
			if(content.startsWith("f")) {
				if(content.substring(1,4).equals("tec")) {
	    			try {
	    	            Class.forName("com.mysql.jdbc.Driver");
	    	        } catch (ClassNotFoundException e) {
	    	            e.printStackTrace();
	    	        }
	    		 PrintStream ps;
	    		 ps = new PrintStream(socket.getOutputStream());
	    	     try (Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/my_database1?characterEncoding=UTF-8",
	    	               "root", "1234"); Statement s = c.createStatement();) {
	    	 
	    	          String sql = "select * from SmartGreen";
	    	 
	    	            // ִ�в�ѯ��䣬���ѽ�������ظ�ResultSet
	    	          ResultSet rs = s.executeQuery(sql);
	    	          
	    	          while (rs.next()) {
	    	                int id = rs.getInt("id");// ����ʹ���ֶ���
	    	                String date = rs.getString(2);// Ҳ����ʹ���ֶε�˳��
	    	                String time = rs.getString(3);
	    	                int smid = rs.getInt(4);
	    	                float t1 = rs.getFloat(5);
	    	                float t2 = rs.getFloat(6);
	    	                float g = rs.getFloat(7);
	    	                float c1 =rs.getFloat(8);
	    	                ps.println(t1);
	    	                
	    	            }
	    	            // ��һ��Ҫ������ر�ReultSet����ΪStatement�رյ�ʱ�򣬻��Զ��ر�ResultSet
	    	            // rs.close();
	    	 
	    	        } catch (SQLException e) {
	    	            // TODO Auto-generated catch block
	    	            e.printStackTrace();
	    	        }
	    		}
			}
		}
	
	
	
	}
	public static void getDataByTeeC(Socket socket ,String content ) throws IOException {
		
		if(content.length()>=4) {
			if(content.startsWith("f")) {
				if(content.substring(1,4).equals("tmc")) {
	    			try {
	    	            Class.forName("com.mysql.jdbc.Driver");
	    	        } catch (ClassNotFoundException e) {
	    	            e.printStackTrace();
	    	        }
	    		 PrintStream ps;
	    		 ps = new PrintStream(socket.getOutputStream());
	    	     try (Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/my_database1?characterEncoding=UTF-8",
	    	               "root", "1234"); Statement s = c.createStatement();) {
	    	 
	    	          String sql = "select * from SmartGreen";
	    	 
	    	            // ִ�в�ѯ��䣬���ѽ�������ظ�ResultSet
	    	          ResultSet rs = s.executeQuery(sql);
	    	          
	    	          while (rs.next()) {
	    	                int id = rs.getInt("id");// ����ʹ���ֶ���
	    	                String date = rs.getString(2);// Ҳ����ʹ���ֶε�˳��
	    	                String time = rs.getString(3);
	    	                int smid = rs.getInt(4);
	    	                float t1 = rs.getFloat(5);
	    	                float t2 = rs.getFloat(6);
	    	                float g = rs.getFloat(7);
	    	                float c1 =rs.getFloat(8);
	    	                ps.println(t2);
	    	                
	    	            }
	    	            // ��һ��Ҫ������ر�ReultSet����ΪStatement�رյ�ʱ�򣬻��Զ��ر�ResultSet
	    	            // rs.close();
	    	 
	    	        } catch (SQLException e) {
	    	            // TODO Auto-generated catch block
	    	            e.printStackTrace();
	    	        }
	    		}
			}
		}
	
	
	
	}
	public static void getDataByGC(Socket socket ,String content ) throws IOException {
		
		if(content.length()>=4) {
			if(content.startsWith("f")) {
				if(content.substring(1,4).equals("grc")) {
	    			try {
	    	            Class.forName("com.mysql.jdbc.Driver");
	    	        } catch (ClassNotFoundException e) {
	    	            e.printStackTrace();
	    	        }
	    		 PrintStream ps;
	    		 ps = new PrintStream(socket.getOutputStream());
	    	     try (Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/my_database1?characterEncoding=UTF-8",
	    	               "root", "1234"); Statement s = c.createStatement();) {
	    	 
	    	          String sql = "select * from SmartGreen";
	    	 
	    	            // ִ�в�ѯ��䣬���ѽ�������ظ�ResultSet
	    	          ResultSet rs = s.executeQuery(sql);
	    	          
	    	          while (rs.next()) {
	    	                int id = rs.getInt("id");// ����ʹ���ֶ���
	    	                String date = rs.getString(2);// Ҳ����ʹ���ֶε�˳��
	    	                String time = rs.getString(3);
	    	                int smid = rs.getInt(4);
	    	                float t1 = rs.getFloat(5);
	    	                float t2 = rs.getFloat(6);
	    	                float g = rs.getFloat(7);
	    	                float c1 =rs.getFloat(8);
	    	                ps.println(g);
	    	                
	    	            }
	    	            // ��һ��Ҫ������ر�ReultSet����ΪStatement�رյ�ʱ�򣬻��Զ��ر�ResultSet
	    	            // rs.close();
	    	 
	    	        } catch (SQLException e) {
	    	            // TODO Auto-generated catch block
	    	            e.printStackTrace();
	    	        }
	    		}
			}
		}
	
	
	
	}
	public static void getDataByCOC(Socket socket ,String content ) throws IOException {
		
		if(content.length()>=4) {
			if(content.startsWith("f")) {
				if(content.substring(1,4).equals("coc")) {
	    			try {
	    	            Class.forName("com.mysql.jdbc.Driver");
	    	        } catch (ClassNotFoundException e) {
	    	            e.printStackTrace();
	    	        }
	    		 PrintStream ps;
	    		 ps = new PrintStream(socket.getOutputStream());
	    	     try (Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/my_database1?characterEncoding=UTF-8",
	    	               "root", "1234"); Statement s = c.createStatement();) {
	    	 
	    	          String sql = "select * from SmartGreen";
	    	 
	    	            // ִ�в�ѯ��䣬���ѽ�������ظ�ResultSet
	    	          ResultSet rs = s.executeQuery(sql);
	    	          
	    	          while (rs.next()) {
	    	                int id = rs.getInt("id");// ����ʹ���ֶ���
	    	                String date = rs.getString(2);// Ҳ����ʹ���ֶε�˳��
	    	                String time = rs.getString(3);
	    	                int smid = rs.getInt(4);
	    	                float t1 = rs.getFloat(5);
	    	                float t2 = rs.getFloat(6);
	    	                float g = rs.getFloat(7);
	    	                float c1 =rs.getFloat(8);
	    	                ps.println(c1);
	    	                
	    	            }
	    	            // ��һ��Ҫ������ر�ReultSet����ΪStatement�رյ�ʱ�򣬻��Զ��ر�ResultSet
	    	            // rs.close();
	    	 
	    	        } catch (SQLException e) {
	    	            // TODO Auto-generated catch block
	    	            e.printStackTrace();
	    	        }
	    		}
			}
		}
	
	
	}
	public static void getDataByLiC(Socket socket ,String content ) throws IOException {
		
		if(content.length()>=4) {
			if(content.startsWith("f")) {
				if(content.substring(1,4).equals("lic")) {
	    			try {
	    	            Class.forName("com.mysql.jdbc.Driver");
	    	        } catch (ClassNotFoundException e) {
	    	            e.printStackTrace();
	    	        }
	    		 PrintStream ps;
	    		 ps = new PrintStream(socket.getOutputStream());
	    	     try (Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/my_database1?characterEncoding=UTF-8",
	    	               "root", "1234"); Statement s = c.createStatement();) {
	    	 
	    	          String sql = "select * from SmartGreen";
	    	 
	    	            // ִ�в�ѯ��䣬���ѽ�������ظ�ResultSet
	    	          ResultSet rs = s.executeQuery(sql);
	    	          
	    	          while (rs.next()) {
	    	                int id = rs.getInt("id");// ����ʹ���ֶ���
	    	                String date = rs.getString(2);// Ҳ����ʹ���ֶε�˳��
	    	                String time = rs.getString(3);
	    	                int smid = rs.getInt(4);
	    	                float t1 = rs.getFloat(5);
	    	                float t2 = rs.getFloat(6);
	    	                float g = rs.getFloat(7);
	    	                float c1 =rs.getFloat(8);
	    	                int light=rs.getInt(9);
	    	                ps.println(light);
	    	                
	    	            }
	    	            // ��һ��Ҫ������ر�ReultSet����ΪStatement�رյ�ʱ�򣬻��Զ��ر�ResultSet
	    	            // rs.close();
	    	 
	    	        } catch (SQLException e) {
	    	            // TODO Auto-generated catch block
	    	            e.printStackTrace();
	    	        }
	    		}
			}
		}
	
	
	}
	public static void getDataByCondition(Socket socket ,String content ) throws IOException {

		
		if(content.length()>=3) {
			if(content.startsWith("*if")) {
				try {
					try {
	    	            Class.forName("com.mysql.jdbc.Driver");
	    	        } catch (ClassNotFoundException e) {
	    	            e.printStackTrace();
	    	        }
	    		 PrintStream ps;
	    		 ps = new PrintStream(socket.getOutputStream());
	    	     try (Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/my_database1?characterEncoding=UTF-8",
	    	               "root", "1234"); Statement s = c.createStatement();) {
	    	    	 String i="id       ";
	    	          String sql ="select * from smartgreen where "+content.substring(3, 25)+"";
	    	          
	    	 
	    	            // ִ�в�ѯ��䣬���ѽ�������ظ�ResultSet
	    	          ResultSet rs = s.executeQuery(sql);
	    	          ps.println("id      ����   	  ʱ��    ����id    �¶�    ʪ��    �絼��    CO2Ũ��    ����ǿ��");
	    	          while (rs.next()) {
	    	                int id = rs.getInt("id");// ����ʹ���ֶ���
	    	                String date = rs.getString(2);// Ҳ����ʹ���ֶε�˳��
	    	                String time = rs.getString(3);
	    	                int smid = rs.getInt(4);
	    	                float t1 = rs.getFloat(5);
	    	                float t2 = rs.getFloat(6);
	    	                float g = rs.getFloat(7);
	    	                float c1 =rs.getFloat(8);
	    	                int light=rs.getInt(9);
	    	                ps.printf("%d\t %s\t %s\t %d\t ", id, date, time, smid);
	    	                ps.printf("%.2f\t %.2f\t %.2f\t %.2f\t %d\n",t1,t2,g,c1,light);
	    	                
	    	            }
	    	            // ��һ��Ҫ������ر�ReultSet����ΪStatement�رյ�ʱ�򣬻��Զ��ر�ResultSet
	    	            // rs.close();
	    	 
	    	        } catch (SQLException e) {
	    	            // TODO Auto-generated catch block
	    	            e.printStackTrace();
	    	        }
				} catch (Exception e) {
					// TODO: handle exception
				}
	    			
	    		
			}
			
		
		
	}
	
	
}
	public static List<String> cutIfString(String Stence)
    {
        List<String> stringlist=new ArrayList<String>();//�����洢����������Ԫ��
        for(int i=0;i<Stence.length();i++)
       {
            if(Stence.charAt(i)=='@')
          { 
             String temp="";//�洢����
              int wordlength=i;                 
              while(wordlength<Stence.length()-1&&Stence.charAt(++wordlength)!='#')                 {
                   temp+=Stence.charAt(wordlength);
                    //System.out.println(temp);
                }
               stringlist.add(temp);
            }
        }
       return stringlist;      
       }


}

