package com.Devin.SmartGreen.text1;



import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;




class ServerThread implements Runnable {

    private Socket s; //套接字
    private BufferedReader br; //字符输入流






    /**
     * 构造函数
     * 
     * @param s Socket
     * @throws IOException 
     * @since 1.0
     */
    public ServerThread(Socket s) throws IOException {
        this.s = s;
        br = new BufferedReader(new InputStreamReader(s.getInputStream()));
    }





    /**
     *   		从客户端接收信息
     * 
     * @return 从客户端接收到的信息
     * @since 1.0
     */
    private String readFromClient() {
        try {
            return br.readLine();
        } catch (IOException e) {
            SMServer.socketList.remove(s);
        }
        return null;
    }
    /*
     *   @Devin 写入数据到数据库
     */
    public void setDataToDataBases(String content ,boolean is_write_true) {
    	if(content.startsWith("#")) {
	    	if(content.isEmpty()==false) {
	        	List<String> m_list=CalcString.cutstring(content);
	        	double[] d=new double[5];
	        	int i=0;
	            System.out.println(m_list.size());
	             for(String tmp:m_list){
	            	 System.out.println(tmp);
	            	 if(i<=3)
	            		 d[i]=Double.parseDouble(tmp);
	            	 if(i==4)
	            		 d[4]=Integer.parseInt(tmp);
	            	 i++;
	            	 if(i==5)
	            		 break;
	             }
	             Connection c =null;
	        	 Statement s=null;
	             try {
	                Class.forName("com.mysql.jdbc.Driver");
	                String date=CalcString.getDate();
	                String time=CalcString.getTime();
	                
	                c = DriverManager
	                        .getConnection(
	                                "jdbc:mysql://127.0.0.1:3306/my_database1?characterEncoding=UTF-8",
	                                "root", "1234");
	      
	                System.out.println("连接成功，获取连接对象： " + c);
	                 s = c.createStatement();
	                
	               
	                 String sql = "insert into SmartGreen values(null,"+date+","+time+","+1+","+d[0]+","+d[1]+","+d[2]+","+d[3]+","+d[4]+")";
	                 s.execute(sql);
	                     
	                     
	                
	               
	      
	                System.out.println("执行插入语句成功");
	                is_write_true=true;
	                
	      
	            } catch (ClassNotFoundException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            } catch (SQLException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            }finally {
	            	try {
	    				s.close();
	    			} catch (SQLException e) {
	    				// TODO Auto-generated catch block
	    				e.printStackTrace();
	    			}
	            	try {
	    				c.close();
	    			} catch (SQLException e) {
	    				// TODO Auto-generated catch block
	    				e.printStackTrace();
	    			}
	    		}
	    	}
    	}
    }



    /**
     * 	线程不断从客户端接收信息并转发
     */
    public void run() {
        String content = null; //从客户端接收信息
        while((content = readFromClient()) != null ) //不为空的时候转发给所有客户端
        {
        	/*
        	 * @Devin 对字符串进行解析
        	 */
        	boolean is_write_true=false;
        	setDataToDataBases( content,is_write_true);
        	
        	
        	for(Socket s:SMServer.socketList) {
                try {
                    PrintStream ps; //打印流（字节输出流）
                    ps = new PrintStream(s.getOutputStream()); //用服务器Socket的OutputStream封装
                   
                    if(content.isEmpty()==false)
                    	if(is_write_true==true)
                    		ps.println("数据写入数据库成功");
                    	ps.println(content);
                    	// 纵向查找函数
                    	CalcString.putAllData(s, content);
                    	CalcString.getDataByIDC(s, content);
                    	CalcString.getDataByDateC(s, content);
                    	CalcString.getDataByTimeC(s, content);
                    	CalcString.getDataBySMIDC(s, content);
                    	CalcString.getDataByTeeC(s, content);
                    	CalcString.getDataByTemC(s, content);
                    	CalcString.getDataByCOC(s, content);
                    	CalcString.getDataByGC(s, content);
                    	CalcString.getDataByLiC(s, content);
                    	 CalcString.getDataByCondition(s, content);
                    	
                    	
                    	
                    
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}



public class SMServer {

    public static List<Socket> socketList = Collections.synchronizedList(new ArrayList<>());//保存所有Socket，线程安全





   
    @SuppressWarnings("resource")
    public static void main(String[] args) {
        try {
        	
        	
            ServerSocket serversocket = new ServerSocket(6666);//指定端口为6666
            System.out.println("服务器启动");
            while(true) {
                Socket socket = serversocket.accept();//接受客户端
                if(socket.isConnected())
                	System.out.println("客户端已经连接");
                
                	
                socketList.add(socket);//添加进客户端List中
                new Thread(new ServerThread(socket)).start();//启动服务器线程
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}