package scripts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.testng.Reporter;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

import commonMethods.Keywords;

public class Fillonewcomparison extends Keywords {

	public static void main(String[] args) {
		
		
		 
//		 String stowageSheet1;

		try {
			Fillo fillo = new Fillo();

			Connection connection1 = fillo.getConnection("C:\\Users\\RBT\\Documents\\Fillomaster.xlsx");
			Connection connection2 = fillo.getConnection("C:\\Users\\RBT\\Documents\\Fillotest.xlsx");

			String query1 = "Select * from `CARGO LIST`";
			Recordset recordset1 = connection1.executeQuery(query1);

			String query2 = "Select * from `CARGO LIST`";
			Recordset recordset2 = connection2.executeQuery(query2);

			List<String> columnsToCompare = Arrays.asList("ISO", "Weight(t)", "POL", "POD", "Mty", "IsSpl", "Is COD", "Rfr", "OOG", "DG", "mul Haz", "DG class", "UNNO", "OOH (m)", "OLF (m)", "OLA (m)", "OWP (m)", "OWS (m)", "Booking No", "Variant", "FlashPoint", "DGLQ");

			Map<String, Set<String>> failedStowageMap = new HashMap<>();
			Map<String, Integer> failureCountByColumn = new HashMap<>();
			Set<String> uniqueFailedStowageNumbers = new HashSet<>();
			Set<String> stowageNotInTest = new HashSet<>();
			Map<String, Integer> failGroupCountMap1 = new HashMap<>();

			while (recordset1.next()) {
			    String stowageSheet1 = recordset1.getField("Stowage");

			    recordset2.moveFirst();

			    boolean matchFound = false;

			    // Move these inside the loop
			    List<String> DG_stownumber = new ArrayList<>();
			    List<String> DG_Group_Excepted = new ArrayList<>();
			    List<String> DG_Group_Actual = new ArrayList<>();

			    while (recordset2.next()) {
			        String stowageSheet2 = recordset2.getField("Stowage");

			        if (stowageSheet1 != null && stowageSheet1.trim().equals(stowageSheet2 != null ? stowageSheet2.trim() : "")) {
			            matchFound = true;

			            for (String columnName : columnsToCompare) {
			                String valueSheet1Column = recordset1.getField(columnName);
			                String valueSheet2Column = recordset2.getField(columnName);

			                if ((valueSheet1Column == null && valueSheet2Column == null) ||
			                        ((stowageSheet1 == null || stowageSheet1.trim().isEmpty()) && "0".equals(stowageSheet2)) ||
			                        ((stowageSheet2 == null || stowageSheet2.trim().isEmpty()) && "0".equals(stowageSheet1)) ||
			                        (valueSheet1Column != null && valueSheet1Column.equals(valueSheet2Column))) {
			                    Extent_pass1(test, "Values are matched for " + "Stowage number :" + stowageSheet1 + "||" + " Column name is :" + columnName + "||" + " Master value: " + valueSheet1Column + "||" + " Test value: " + valueSheet2Column);
			                } else {
			                    uniqueFailedStowageNumbers.add(stowageSheet1);
			                    failedStowageMap.computeIfAbsent(columnName, k -> new HashSet<>()).add(stowageSheet1);
			                    Extent_fail2(test, "Values are not matched for " + "Stowage number :" + stowageSheet1 + "||" + "Column name is :" + columnName + "||" + " Master value: " + valueSheet1Column + "||" + " Test value: " + valueSheet2Column);
			                    failureCountByColumn.put(columnName, failureCountByColumn.getOrDefault(columnName, 0) + 1);
			                }
			            }
			        }
			    }

//			    if (recordset1.getField("DG") != null && recordset1.getField("DG").contains("Yes")) {
//			        // Continue with your existing logic for "DG" comparison
//			        if ((recordset1.getField("DG") == null && recordset2.getField("DG") == null) || (recordset1.getField("DG") != null && recordset1.getField("DG").equals(recordset2.getField("DG")))) {
//			            // Logic when "DG" comparison passes
//			        } else {
//			            failGroupCountMap1.put("DG", failGroupCountMap1.getOrDefault("DG", 0) + 1);
//			            DG_stownumber.add(stowageSheet1);
//			            DG_Group_Excepted.add(recordset1.getField("DG"));
//			            DG_Group_Actual.add(recordset2.getField("DG"));
//			        }
//			    }
//
//			    if (!matchFound) {
//			        stowageNotInTest.add(stowageSheet1);
//			    }
//
//			    if ((DG_stownumber != null)) {
//			        for (int i = 0; i < DG_stownumber.size(); i++) {
//			            Extent_group_table(test, DG_stownumber.get(i), "DG Group", "DG", DG_Group_Excepted.get(i), DG_Group_Actual.get(i));
//			        }
//			    }
//			}

			System.out.println("Stowage numbers not present in the test sheet: " + stowageNotInTest);

			for (Map.Entry<String, Set<String>> entry : failedStowageMap.entrySet()) {
			    String columnName = entry.getKey();
			    Set<String> failedStowageNumbers = entry.getValue();
			    System.out.println("Failed Stowage values for Column " + columnName + " are: " + failedStowageNumbers);
			    Extent_fail1(test, "Failed Stowage values for Column " + columnName + " are: " + failedStowageNumbers);
			}

			for (Map.Entry<String, Integer> entry : failureCountByColumn.entrySet()) {
			    System.out.println("Failure count for column '" + entry.getKey() + "': " + entry.getValue());
			}

			for (Map.Entry<String, Integer> entry : failGroupCountMap1.entrySet()) {
			    System.out.println("Group failure count for column '" + entry.getKey() + "': " + entry.getValue());
			}

			int totalFailedCount = uniqueFailedStowageNumbers.size();
			System.out.println("Total Failed count is : " + totalFailedCount);

			recordset1.close();
			recordset2.close();
			connection1.close();
			connection2.close();

     
		        }
			
		}catch (FilloException e) {
			            e.printStackTrace();
			        }
	}

}