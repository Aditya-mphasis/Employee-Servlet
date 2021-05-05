package com.employee;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Response;

public class EmployeeServlet extends GenericServlet  
{
	
	@Override
	public void service(ServletRequest arg0, ServletResponse arg1) throws ServletException, IOException {
		// TODO Auto-generated method stub
		arg1.setContentType("text/html");
		try
        {
			 PrintWriter pw = arg1.getWriter();
             DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
             Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "System", "aditya");
             Statement statement=con.createStatement();
             System.out.println("connection established successfully!");     

             ResultSet rs=statement.executeQuery("Select * from emp");

             pw.println("<table border=2>");
                 while(rs.next())
                 {
                     pw.println("<tr><td>"+rs.getInt(1)+"</td><td>"+rs.getString(2)+"</td>"+
                                      "<td>"+rs.getString(3)+"</td>"+"<td>"+rs.getInt(4)+"</td>"+"<td>"+
                                      rs.getDate(5)+"</td>"+"<td>"+rs.getInt(6)+"</td>"
                                      +"<td>"+rs.getInt(7)+"</td>"+"<td>"+rs.getInt(8)+"</td></tr>");
                 }

             pw.println("</table>");

             pw.println("<a href='http://localhost:8080/EmployeeServlet/'> Home </a>");
         
        }
        catch (Exception e){
            e.printStackTrace();
        }

	}
}