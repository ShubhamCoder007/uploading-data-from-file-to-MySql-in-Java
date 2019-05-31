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

import com.loaddata.AcctDocHeader;

public class JavaLoadData {

	public static Date parseDate(String s) {
		Date date = null;
		date = Date.valueOf(s);
		
		return date;
	}
	
	
	public static void main(String[] args) {
	
		String path = "C:\\Users\\nEW u\\Desktop\\Sample Dataset.csv";
		File f = new File(path);
		
		int count = 0;
		int rs = 0;
		
		Connection dbcon = null;
		PreparedStatement pst = null;
		String url = "jdbc:mysql://localhost/mydb?autoReconnect=true&useSSL=false";
		String username = "root";
		String password = "RootUser@123";
		
		String paramQuery="Insert into acct_doc_header(account_id,document_number_norm,company_code,fiscal_year,branch,customer_number_norm,fk_customer_map_id,document_date_norm,baseline_date_norm,due_date_norm,invoice_number_norm,open_amount_norm, payment_terms,clearing_date_norm,isOpen,order_type,order_date,business_area,ship_date,job_id,tax_amt,current_dispute_amount,ship_to,document_id,document_date,actual_open_amount,due_date,invoice_age,isValid_dispute,posting_key,strategy_id,currency,debit_credit_indicator,valid_open_amount,customer_name,retain_age_amount) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		try {
	
		Class.forName("com.mysql.jdbc.Driver");
		dbcon = DriverManager.getConnection(url, username, password);
		
		
		
		
		if(!f.exists())
			System.out.print("File not present!\n");
		
		BufferedReader fin = null;
		
		try {
			fin = new BufferedReader(new FileReader(f));
		} catch (FileNotFoundException e1) {
			
			e1.printStackTrace();
		} 
		
		String s = null;
		
		try {
			fin.readLine();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			while((s = fin.readLine()) != null && count < 1000) {
				
				count++;
				
				String[] c = s.split(",");
				System.out.println("count of entry: "+count);
				
				AcctDocHeader ob = new AcctDocHeader();
				
				if(c[0].length() != 0 && !c[0].equals("\\N"))
					ob.setAccountID(Integer.parseInt(c[0]));
				
				ob.setDocumentNumberNom(c[1]);
				ob.setCompanyCode(c[2]);
				ob.setFiscalYear(c[3]);
				ob.setBranch(c[4]);
				ob.setCustomerNumberNorm(c[5]);
				
				if(c[6].length() != 0 && !c[6].equals("\\N"))
					ob.setCustomerMapID(Integer.parseInt(c[6]));
				
				
				if(c[7].length() != 0 && !c[7].equals("\\N"))
					ob.setDocumentDateNorm(parseDate(c[7]));
				if(c[8].length() != 0 && !c[8].equals("\\N"))
					ob.setBaselineDateNorm(parseDate(c[8]));
				if(c[9].length() != 0 && !c[9].equals("\\N"))
					ob.setDueDateNorm(parseDate(c[9]));
				
				ob.setInvoiceNumberNorm(c[10]);
				
				if(c[11].length() != 0 && !c[11].equals("\\N"))
					ob.setOpenAmountNorm(Double.parseDouble(c[11]));
				
				ob.setPaymentTerms(c[12]);
				
				if(c[13].length() != 0 && !c[13].equals("\\N"))
					ob.setClearingDate(parseDate(c[13]));
				if(c[14].length() != 0 && !c[14].equals("\\N"))
					ob.setIsOpen(Integer.parseInt(c[14]));
				
				ob.setOrderType(c[15]);
				if(c[16].length() != 0 && !c[16].equals("\\N"))
					ob.setOrderDate(parseDate(c[16]));
				ob.setBusinessArea(c[17]);
				if(c[18].length() != 0 && !c[18].equals("\\N"))
					ob.setShipDate(parseDate(c[18]));
				if(c[19].length() != 0 && !c[19].equals("\\N"))
					ob.setJobId(Integer.parseInt(c[19]));
				
				if(c[20].length() != 0 && !c[20].equals("\\N"))
					ob.setTaxAmt(Double.parseDouble(c[20]));
				if(c[21].length() != 0 && !c[21].equals("\\N"))
					ob.setCurrentDisputeAmount(Double.parseDouble(c[21]));
				ob.setShipTo(c[22]);
				if(c[23].length() != 0 && !c[23].equals("\\N"))
					ob.setDocumentId(Integer.parseInt(c[23]));
				
				if(c[24].length() != 0 && !c[24].equals("\\N"))
					ob.setDocumentDate(parseDate(c[24]));
				if(c[25].length() != 0 && !c[25].equals("\\N"))
					ob.setActualOpenAmount(Double.parseDouble(c[25]));
				if(c[26].length() != 0 && !c[26].equals("\\N"))
					ob.setDueDate(parseDate(c[26]));
				if(c[27].length() != 0 && !c[27].equals("\\N"))
					ob.setInvoiceAge(Integer.parseInt(c[27]));
				if(c[28].length() != 0 && !c[28].equals("\\N"))
					ob.setIsValidDispute(Integer.parseInt(c[28]));
				if(c[29].length() != 0 && !c[29].equals("\\N"))
					ob.setRetainageAmount(Double.parseDouble(c[29]));
				ob.setPostingKey(c[30]);
				if(c[31].length() != 0 && !c[31].equals("\\N"))
					ob.setStrategyId(Integer.parseInt(c[31]));
				
				ob.setCurrency(c[32]);
				if(c[33].length() != 0 && !c[33].equals("\\N"))
					ob.setDebitCreditIndicator(c[33]);
				if(c[34].length() != 0 && !c[34].equals("\\N"))
					ob.setValidOpenAmount(Double.parseDouble(c[34]));
				
				try {
					ob.setCustomerName(c[35]);
				}catch(ArrayIndexOutOfBoundsException e) {
					e.printStackTrace();
					
				}
				finally {
					ob.setCustomerName(null);
				}
				
				ob.setRetainageAmount(0.0);
				
				
				//------------------------pojo loading till here------------
					
				
				
			//-------------------uploading to db ---------------------
				
				
				pst = dbcon.prepareStatement(paramQuery);
				
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
				//pst.setDouble(30, ob.getRetainageAmount());
				pst.setString(30, ob.getPostingKey());
				pst.setLong(31, ob.getStrategyId());
				pst.setString(32, ob.getCurrency());
				pst.setString(33, ob.getDebitCreditIndicator());
				pst.setDouble(34,ob.getValidOpenAmount());
				pst.setString(35, ob.getCustomerName());
				pst.setDouble(36, ob.getRetainageAmount());
				
				
				rs = pst.executeUpdate();
				
				//--------------uploading to db till here---------------------
				
				
				//System.out.println(ob.getAccountID()+" "+ob.getActualOpenAmount());
				
				
				

				
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
