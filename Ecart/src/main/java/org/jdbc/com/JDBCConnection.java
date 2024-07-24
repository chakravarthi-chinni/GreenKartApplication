package org.jdbc.com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.cj.x.protobuf.MysqlxSql.StmtExecute;

public class JDBCConnection {

	public String[] jdbcConnection2() throws SQLException {
		String name = null;
		String paswd = null;
		String url ="jdbc:mysql://localhost:3306/Ecartlogindata";
		String user="root";
		String password="Chakri@123";
		Connection connect=DriverManager.getConnection(url, user, password);
		Statement stmt=connect.createStatement();
		ResultSet result=stmt.executeQuery("select * from Ecartlogindata.logindata");
		
		while(result.next()) {
			 name=result.getString("name");
			 paswd=result.getString("password");
			 System.out.println(name);
			 System.out.println(paswd);
		}
		return new String[]{name,paswd};
	}
	
	
	public String[] jdbcConnection() throws SQLException {
		
		String name = null;
		String paswd = null;
		String url ="jdbc:mysql://localhost:3306/Ecartlogindata";
		String user="root";
		String password="Chakri@123";
		Connection connect=DriverManager.getConnection(url, user, password);
		Statement stmt=connect.createStatement();
		ResultSet result=stmt.executeQuery("select name,password from Ecartlogindata.logindata");
		ObjectMapper mapper=new ObjectMapper();
		while(result.next()) {
			 name=result.getString("name");
			 paswd=result.getString("password");
			 System.out.println(name);
			 System.out.println(paswd);
		}
		return new String[]{name,paswd};
	}
	}


