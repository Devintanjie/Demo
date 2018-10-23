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
 * @Description: 字符串格式"@a@b@c",a,b,c会被解析为单独的元素
 * @author luxishi
 * @date 2016年3月8日 下午5:57:56 
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
         SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
         String time=df.format(new Date());
         System.out.println(time);
         String content="fal";
         
         
     }
  
    public static List<String> cutstring(String Stence)
     {
         List<String> stringlist=new ArrayList<String>();//用来存储解析出来的元素
         for(int i=0;i<Stence.length();i++)
        {
             if(Stence.charAt(i)=='#')
           { 
              String temp="";//存储单词
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
     *    @获取系统时间
     */
    public static String getTime() {
    	SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        String time=df.format(new Date());
        time=time.substring(8, 14);
       
        return time;
    }
    /*
     * @获取系统日期
     */
    public static String getDate() {
    	SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        String date=df.format(new Date());
        date=date.substring(0,8);
    	return date;
    }
   
    /*
     * @ 返回数据库所有内容 fal
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
		    	 
		    	            // 执行查询语句，并把结果集返回给ResultSet
		    	          ResultSet rs = s.executeQuery(sql);
		    	          ps.println("id      日期   	  时间    温室id    温度    湿度    电导率    CO2浓度    光照强度");
		    	          while (rs.next()) {
		    	                int id = rs.getInt("id");// 可以使用字段名
		    	                String date = rs.getString(2);// 也可以使用字段的顺序
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
		    	            // 不一定要在这里关闭ReultSet，因为Statement关闭的时候，会自动关闭ResultSet
		    	            // rs.close();
		    	 
		    	        } catch (SQLException e) {
		    	            // TODO Auto-generated catch block
		    	            e.printStackTrace();
		    	        }
		    		}
				}
				
			
    		
    	}
		
		
	}
	
	//返回数据库部分内容 按id竖直查询 fidc
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
	    	 
	    	            // 执行查询语句，并把结果集返回给ResultSet
	    	          ResultSet rs = s.executeQuery(sql);
	    	          
	    	          while (rs.next()) {
	    	                int id = rs.getInt("id");// 可以使用字段名
	    	                String date = rs.getString(2);// 也可以使用字段的顺序
	    	                String time = rs.getString(3);
	    	                int smid = rs.getInt(4);
	    	                float t1 = rs.getFloat(5);
	    	                float t2 = rs.getFloat(6);
	    	                float g = rs.getFloat(7);
	    	                float c1 =rs.getFloat(8);
	    	                
	    	                ps.println(id);
	    	                
	    	            }
	    	            // 不一定要在这里关闭ReultSet，因为Statement关闭的时候，会自动关闭ResultSet
	    	            // rs.close();
	    	 
	    	        } catch (SQLException e) {
	    	            // TODO Auto-generated catch block
	    	            e.printStackTrace();
	    	        }
	    		}
			}
			
		
		
		}
		
	}
	//	按日期竖直查询
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
	    	 
	    	            // 执行查询语句，并把结果集返回给ResultSet
	    	          ResultSet rs = s.executeQuery(sql);
	    	          
	    	          while (rs.next()) {
	    	                int id = rs.getInt("id");// 可以使用字段名
	    	                String date = rs.getString(2);// 也可以使用字段的顺序
	    	                String time = rs.getString(3);
	    	                int smid = rs.getInt(4);
	    	                float t1 = rs.getFloat(5);
	    	                float t2 = rs.getFloat(6);
	    	                float g = rs.getFloat(7);
	    	                float c1 =rs.getFloat(8);
	    	                ps.println(date);
	    	                
	    	            }
	    	            // 不一定要在这里关闭ReultSet，因为Statement关闭的时候，会自动关闭ResultSet
	    	            // rs.close();
	    	 
	    	        } catch (SQLException e) {
	    	            // TODO Auto-generated catch block
	    	            e.printStackTrace();
	    	        }
	    		}
			}
		}
	}
	//	按时间查询
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
	    	 
	    	            // 执行查询语句，并把结果集返回给ResultSet
	    	          ResultSet rs = s.executeQuery(sql);
	    	          
	    	          while (rs.next()) {
	    	                int id = rs.getInt("id");// 可以使用字段名
	    	                String date = rs.getString(2);// 也可以使用字段的顺序
	    	                String time = rs.getString(3);
	    	                int smid = rs.getInt(4);
	    	                float t1 = rs.getFloat(5);
	    	                float t2 = rs.getFloat(6);
	    	                float g = rs.getFloat(7);
	    	                float c1 =rs.getFloat(8);
	    	                ps.println(time);
	    	                
	    	            }
	    	            // 不一定要在这里关闭ReultSet，因为Statement关闭的时候，会自动关闭ResultSet
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
	    	 
	    	            // 执行查询语句，并把结果集返回给ResultSet
	    	          ResultSet rs = s.executeQuery(sql);
	    	          
	    	          while (rs.next()) {
	    	                int id = rs.getInt("id");// 可以使用字段名
	    	                String date = rs.getString(2);// 也可以使用字段的顺序
	    	                String time = rs.getString(3);
	    	                int smid = rs.getInt(4);
	    	                float t1 = rs.getFloat(5);
	    	                float t2 = rs.getFloat(6);
	    	                float g = rs.getFloat(7);
	    	                float c1 =rs.getFloat(8);
	    	                ps.println(smid);
	    	                
	    	            }
	    	            // 不一定要在这里关闭ReultSet，因为Statement关闭的时候，会自动关闭ResultSet
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
	    	 
	    	            // 执行查询语句，并把结果集返回给ResultSet
	    	          ResultSet rs = s.executeQuery(sql);
	    	          
	    	          while (rs.next()) {
	    	                int id = rs.getInt("id");// 可以使用字段名
	    	                String date = rs.getString(2);// 也可以使用字段的顺序
	    	                String time = rs.getString(3);
	    	                int smid = rs.getInt(4);
	    	                float t1 = rs.getFloat(5);
	    	                float t2 = rs.getFloat(6);
	    	                float g = rs.getFloat(7);
	    	                float c1 =rs.getFloat(8);
	    	                ps.println(t1);
	    	                
	    	            }
	    	            // 不一定要在这里关闭ReultSet，因为Statement关闭的时候，会自动关闭ResultSet
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
	    	 
	    	            // 执行查询语句，并把结果集返回给ResultSet
	    	          ResultSet rs = s.executeQuery(sql);
	    	          
	    	          while (rs.next()) {
	    	                int id = rs.getInt("id");// 可以使用字段名
	    	                String date = rs.getString(2);// 也可以使用字段的顺序
	    	                String time = rs.getString(3);
	    	                int smid = rs.getInt(4);
	    	                float t1 = rs.getFloat(5);
	    	                float t2 = rs.getFloat(6);
	    	                float g = rs.getFloat(7);
	    	                float c1 =rs.getFloat(8);
	    	                ps.println(t2);
	    	                
	    	            }
	    	            // 不一定要在这里关闭ReultSet，因为Statement关闭的时候，会自动关闭ResultSet
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
	    	 
	    	            // 执行查询语句，并把结果集返回给ResultSet
	    	          ResultSet rs = s.executeQuery(sql);
	    	          
	    	          while (rs.next()) {
	    	                int id = rs.getInt("id");// 可以使用字段名
	    	                String date = rs.getString(2);// 也可以使用字段的顺序
	    	                String time = rs.getString(3);
	    	                int smid = rs.getInt(4);
	    	                float t1 = rs.getFloat(5);
	    	                float t2 = rs.getFloat(6);
	    	                float g = rs.getFloat(7);
	    	                float c1 =rs.getFloat(8);
	    	                ps.println(g);
	    	                
	    	            }
	    	            // 不一定要在这里关闭ReultSet，因为Statement关闭的时候，会自动关闭ResultSet
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
	    	 
	    	            // 执行查询语句，并把结果集返回给ResultSet
	    	          ResultSet rs = s.executeQuery(sql);
	    	          
	    	          while (rs.next()) {
	    	                int id = rs.getInt("id");// 可以使用字段名
	    	                String date = rs.getString(2);// 也可以使用字段的顺序
	    	                String time = rs.getString(3);
	    	                int smid = rs.getInt(4);
	    	                float t1 = rs.getFloat(5);
	    	                float t2 = rs.getFloat(6);
	    	                float g = rs.getFloat(7);
	    	                float c1 =rs.getFloat(8);
	    	                ps.println(c1);
	    	                
	    	            }
	    	            // 不一定要在这里关闭ReultSet，因为Statement关闭的时候，会自动关闭ResultSet
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
	    	 
	    	            // 执行查询语句，并把结果集返回给ResultSet
	    	          ResultSet rs = s.executeQuery(sql);
	    	          
	    	          while (rs.next()) {
	    	                int id = rs.getInt("id");// 可以使用字段名
	    	                String date = rs.getString(2);// 也可以使用字段的顺序
	    	                String time = rs.getString(3);
	    	                int smid = rs.getInt(4);
	    	                float t1 = rs.getFloat(5);
	    	                float t2 = rs.getFloat(6);
	    	                float g = rs.getFloat(7);
	    	                float c1 =rs.getFloat(8);
	    	                int light=rs.getInt(9);
	    	                ps.println(light);
	    	                
	    	            }
	    	            // 不一定要在这里关闭ReultSet，因为Statement关闭的时候，会自动关闭ResultSet
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
	    	          
	    	 
	    	            // 执行查询语句，并把结果集返回给ResultSet
	    	          ResultSet rs = s.executeQuery(sql);
	    	          ps.println("id      日期   	  时间    温室id    温度    湿度    电导率    CO2浓度    光照强度");
	    	          while (rs.next()) {
	    	                int id = rs.getInt("id");// 可以使用字段名
	    	                String date = rs.getString(2);// 也可以使用字段的顺序
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
	    	            // 不一定要在这里关闭ReultSet，因为Statement关闭的时候，会自动关闭ResultSet
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
        List<String> stringlist=new ArrayList<String>();//用来存储解析出来的元素
        for(int i=0;i<Stence.length();i++)
       {
            if(Stence.charAt(i)=='@')
          { 
             String temp="";//存储单词
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

