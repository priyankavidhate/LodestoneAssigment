
/*

create table rater_data (date Date,rater varchar(5),correctans3label varchar(15),correctans5label varchar(15),raterans3label varchar(15),raterans5label varchar(15),taskid int NOT NULL AUTO_INCREMENT PRIMARY KEY,agreementlabel3 BOOLEAN,agreementlabel5 BOOLEAN);

drop table rater_data;
select * from rater_data;
delete from  rater_data;*/

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.sql.*;

public class CreatingRecords {

	public static void main(String[] args) throws Exception {
		String[] raterName = { "A", "B", "C", "D", "E" };
		String[] corrAnslabel3 = { "Low", "Average", "High" };
		String[] corrAnslabel5 = { "Bad", "Okay", "Intermediate", "Great", "Exceptional" };
		String[] raterAnslabel3 = { "Low", "Average", "High" };
		String[] raterAnslabel5 = { "Bad", "Okay", "Intermediate", "Great", "Exceptional" };

		Random r = new Random();
		Random corrAns3random = new Random();
		Random corrAns5random = new Random();
		Random raterAns3random = new Random();
		Random raterAns5random = new Random();

		String insertvalues = "INSERT INTO rater_data(date, rater, correctans3label,correctans5label,raterans3label,raterans5label,taskid,agreementlabel3,agreementlabel5) "
				+ "VALUES (?,?,?,?,?,?,?,?,?)";

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/lodestone", "root",
					"rohitpune123");
			
			PreparedStatement insertRecordStmt = con.prepareStatement(insertvalues);

			Statement stmt = con.createStatement();
			Date d1 = new SimpleDateFormat("yyyy-MM-dd").parse("2005-10-01");
			Date d2 = new SimpleDateFormat("yyyy-MM-dd").parse("2005-10-30");

			for (int i = 1; i <= 10000; i++) {

				Date randomDate = new Date(ThreadLocalRandom.current().nextLong(d1.getTime(), d2.getTime()));
				String dateString = new SimpleDateFormat("yyyy-MM-dd").format(randomDate);

				int randomNumber = r.nextInt(raterName.length);
				String name = raterName[randomNumber];

				int corrAns3randomindex = corrAns3random.nextInt(corrAnslabel3.length);
				String corrAns3randomvalue = corrAnslabel3[corrAns3randomindex];
				int corrAns5randomindex = corrAns5random.nextInt(corrAnslabel5.length);
				String corrAns5randomvalue = corrAnslabel5[corrAns5randomindex];

				int raterAns3randomindex = raterAns3random.nextInt(raterAnslabel3.length);
				String raterAns3randomvalue = raterAnslabel3[raterAns3randomindex];
				int raterAns5randomindex = raterAns5random.nextInt(raterAnslabel5.length);
				String raterAns5randomvalue = raterAnslabel5[raterAns5randomindex];

				insertRecordStmt.setString(1, dateString.toString());
				insertRecordStmt.setString(2, name);
				insertRecordStmt.setString(3, corrAns3randomvalue);
				insertRecordStmt.setString(4, corrAns5randomvalue);
				insertRecordStmt.setString(5, raterAns3randomvalue);
				insertRecordStmt.setString(6, raterAns5randomvalue);
				insertRecordStmt.setInt(7, i);
				insertRecordStmt.setBoolean(8, corrAns3randomvalue.equals(raterAns3randomvalue));
				insertRecordStmt.setBoolean(9, corrAns5randomvalue.equals(raterAns5randomvalue));
			
				insertRecordStmt.addBatch();
			}
			insertRecordStmt.executeBatch();
		

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
