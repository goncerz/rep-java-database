import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Main {
	public static void printAllRecords(Statement stmt) {
		try {
			ResultSet rs = stmt.executeQuery("select * from pracownik");
			System.out.println("\nPracownicy:");
			while(rs.next()) {
				System.out.println(rs.getInt(1) + ": " + rs.getString(2) + " " + rs.getString(3));
			}
			System.out.println("");
			rs.close();
		} catch(Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}
	
	public static void addRecord(Statement stmt) {
		try {
			Scanner sc = new Scanner(System.in);
			String fName = new String();
			String lName = new String();
			
			System.out.print("Wprowadz imie nowego pracownika: ");
			fName = sc.nextLine();
			System.out.print("Wprowadz nazwisko nowego pracownika: ");
			lName = sc.nextLine();
			sc.close();
			
			stmt.executeUpdate(
				"insert into pracownik(imie,nazwisko) values('" +
				fName + "','" +
				lName + "')"
				);
			
		} catch(Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Scanner sc = new Scanner(System.in);
			String pass = new String();
			
			System.out.print("Wprowadz haslo do bazy: ");
			pass = sc.nextLine();
			
			Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/test" +
				"?" + "useSSL=false" +
				"&" + "serverTimezone=UTC" +
				"&" + "user=" + "root" +
				"&" + "password=" + pass
				);
			
			Statement stmt = conn.createStatement();
			printAllRecords(stmt);
			
			addRecord(stmt);
			printAllRecords(stmt);
			
			sc.close();
			stmt.close();
			conn.close();
			
		} catch(Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}

}