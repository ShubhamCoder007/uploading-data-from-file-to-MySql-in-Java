package com.fileio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.lang.ArrayIndexOutOfBoundsException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.loaddata.AcctDocHeader;

public class DataLoading {

	//setting up the constant fields
	private static final String path = "C:\\Users\\nEW u\\Desktop\\Sample Dataset.csv";
	private static final String url = "jdbc:mysql://localhost/mydb?autoReconnect=true&useSSL=false";
	private static String username = "root";
	private static String password = "RootUser@123";


	public static Date parseDate(String s) {
		Date date = null;
		java.util.Date d = null;
		
		if(s.length() != 0 && !s.equals("\\N") && s != null) 
			date = Date.valueOf(s);
		else {
			try {
				d = new SimpleDateFormat("yyyy-MM-dd").parse("0001-01-01");
			} catch (ParseException e) {
				e.printStackTrace();
			}
			date = new java.sql.Date(d.getTime()); 
		}
		return date;
	}
	
	
	public static void main(String[] args) {
	
		AcctDocHeader ob = null;
		File f = new File(path);
		
		//if file not present then terminate
		if(!f.exists()) {
			System.out.print("File not present!\n");
			System.exit(0);
		}
		
		int count = 0;
		
		Connection dbcon = null;
		PreparedStatement pst = null;
		
		//the query to be executed to insert into the db
		String query="Insert into acct_doc_header(account_id,document_number_norm,company_code,fiscal_year,branch,customer_number_norm,fk_customer_map_id,document_date_norm,baseline_date_norm,due_date_norm,invoice_number_norm,open_amount_norm, payment_terms,clearing_date_norm,isOpen,order_type,order_date,business_area,ship_date,job_id,tax_amt,current_dispute_amount,ship_to,document_id,document_date,actual_open_amount,due_date,invoice_age,isValid_dispute,posting_key,strategy_id,currency,debit_credit_indicator,valid_open_amount,customer_name,retain_age_amount) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		try {
	
		Class.forName("com.mysql.jdbc.Driver");
		dbcon = DriverManager.getConnection(url, username, password);
		
		
		BufferedReader fin = null;
		
		try {
			fin = new BufferedReader(new FileReader(f));
		} catch (FileNotFoundException e1) {
			
			e1.printStackTrace();
		} 
		
		String s = null;
		
		try {
			//moves the cursor from header to data part
			fin.readLine();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			while((s = fin.readLine()) != null) {
				
				count++;
				
				String[] c = s.split(",");
				System.out.println("count of entry: "+count);
				
				ob = new AcctDocHeader();
				
				if(c[0].length() != 0 && !c[0].equals("\\N"))
					ob.setAccountID(Integer.parseInt(c[0]));
				
				ob.setDocumentNumberNom(c[1]);
				ob.setCompanyCode(c[2]);
				ob.setFiscalYear(c[3]);
				ob.setBranch(c[4]);
				ob.setCustomerNumberNorm(c[5]);
				
				if(c[6].length() != 0 && !c[6].equals("\\N"))
					ob.setCustomerMapID(Integer.parseInt(c[6]));
				
		
				ob.setDocumentDateNorm(parseDate(c[7]));	
				ob.setBaselineDateNorm(parseDate(c[8]));
				ob.setDueDateNorm(parseDate(c[9]));
				
				ob.setInvoiceNumberNorm(c[10]);
				
				if(c[11].length() != 0 && !c[11].equals("\\N"))
					ob.setOpenAmountNorm(Double.parseDouble(c[11]));
				
				ob.setPaymentTerms(c[12]);
				
				ob.setClearingDate(parseDate(c[13]));
				
				if(c[14].length() != 0 && !c[14].equals("\\N"))
					ob.setIsOpen(Integer.parseInt(c[14]));
				
				ob.setOrderType(c[15]);
				ob.setOrderDate(parseDate(c[16]));
				ob.setBusinessArea(c[17]);
				ob.setShipDate(parseDate(c[18]));
				
				if(c[19].length() != 0 && !c[19].equals("\\N"))
					ob.setJobId(Integer.parseInt(c[19]));
				
				if(c[20].length() != 0 && !c[20].equals("\\N"))
					ob.setTaxAmt(Double.parseDouble(c[20]));
				else
					ob.setTaxAmt(0.0);
				
				if(c[21].length() != 0 && !c[21].equals("\\N"))
					ob.setCurrentDisputeAmount(Double.parseDouble(c[21]));
				else
					ob.setCurrentDisputeAmount(0.0);
				
				ob.setShipTo(c[22]);
				
				if(c[23].length() != 0 && !c[23].equals("\\N"))
					ob.setDocumentId(Integer.parseInt(c[23]));
					
				ob.setDocumentDate(parseDate(c[24]));
				
				if(c[25].length() != 0 && !c[25].equals("\\N"))
					ob.setActualOpenAmount(Double.parseDouble(c[25]));
				else
					ob.setActualOpenAmount(0.0);
				
				ob.setDueDate(parseDate(c[26]));
				
				if(c[27].length() != 0 && !c[27].equals("\\N"))
					ob.setInvoiceAge(Integer.parseInt(c[27]));
				else
					ob.setInvoiceAge(0);
				
				if(c[28].length() != 0 && !c[28].equals("\\N"))
					ob.setIsValidDispute(Integer.parseInt(c[28]));
				
				if(c[29].length() != 0 && !c[29].equals("\\N"))
					ob.setRetainageAmount(Double.parseDouble(c[29]));
				else
					ob.setRetainageAmount(0.0);
				
				ob.setPostingKey(c[30]);
				
				if(c[31].length() != 0 && !c[31].equals("\\N"))
					ob.setStrategyId(Integer.parseInt(c[31]));
				
				ob.setCurrency(c[32]);
				
				if(c[33].length() != 0 && !c[33].equals("\\N"))
					ob.setDebitCreditIndicator(c[33]);
				
				if(c[34].length() != 0 && !c[34].equals("\\N"))
					ob.setValidOpenAmount(Double.parseDouble(c[34]));
				else
					ob.setValidOpenAmount(0.0);
				
				try {
					ob.setCustomerName(c[35]);
				}catch(ArrayIndexOutOfBoundsException e) {
					ob.setCustomerName("No name");
					e.printStackTrace();
				}
				
				
				
				//------------------------pojo loading till here------------
					
				
				
			//-------------------uploading to db ---------------------
				
				
				pst = dbcon.prepareStatement(query);
				
				//setting up the query by prepared statement
				
				pst.setLong(1, ob.getAccountID());
				pst.setString(2, ob.getDocumentNumberNom());
				pst.setString(3, ob.getCompanyCode());
				pst.setString(4, ob.getFiscalYear());
				pst.setString(5,  ob.getBranch());
				pst.setString(6, ob.getCustomerNumberNorm());
				pst.setLong(7, ob.getCustomerMapID());
				pst.setDate(8, (java.sql.Date) ob.getDocumentDateNorm());
				pst.setDate(9, (java.sql.Date) ob.getBaselineDateNorm());
				pst.setDate(10, (java.sql.Date) ob.getDueDateNorm());
				pst.setString(11, ob.getInvoiceNumberNorm());
				pst.setDouble(12, ob.getOpenAmountNorm());
				pst.setString(13, ob.getPaymentTerms());
				pst.setDate(14, (java.sql.Date) ob.getClearingDate());
				pst.setInt(15, ob.getIsOpen());
				pst.setString(16, ob.getOrderType());
				pst.setDate(17, (java.sql.Date) ob.getOrderDate());
				pst.setString(18, ob.getBusinessArea());
				pst.setDate(19, (java.sql.Date) ob.getShipDate());
				pst.setInt(20, ob.getJobId());
				pst.setDouble(21, ob.getTaxAmt());
				pst.setDouble(22, ob.getCurrentDisputeAmount());
				pst.setString(23, ob.getShipTo());
				pst.setInt(24, ob.getDocumentId());
				pst.setDate(25, (java.sql.Date) ob.getDocumentDate());
				pst.setDouble(26, ob.getActualOpenAmount());
				pst.setDate(27, (java.sql.Date) ob.getDueDate());
				pst.setInt(28, ob.getInvoiceAge());
				pst.setInt(29, ob.getIsValidDispute());
				pst.setString(30, ob.getPostingKey());
				pst.setLong(31, ob.getStrategyId());
				pst.setString(32, ob.getCurrency());
				pst.setString(33, ob.getDebitCreditIndicator());
				pst.setDouble(34,ob.getValidOpenAmount());
				pst.setString(35, ob.getCustomerName());
				pst.setDouble(36, ob.getRetainageAmount());
				
				
				pst.execute();
				
				//pst.addBatch();
				//pst.clearParameters();
				
//				if(count % 1000 == 0)
//					pst.executeBatch();
				
//				System.out.println("Executed batch of count 1000 batch no.: "+count/1000+" status: ");
			//	for(int i : rss)
				//	System.out.println("status: "+i);
				

				
//--------------uploading to db till here---------------------------------------------------------------------
				
				
				
				
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		finally {
			try {
				if(dbcon != null) 
					dbcon.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				if(pst != null)
					pst.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
				
}
