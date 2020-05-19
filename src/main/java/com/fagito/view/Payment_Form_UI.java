package com.fagito.view;

public class Payment_Form_UI {
	
	private long account_no;
	private int month;
	private int year;
	private int cvv;
	public long getAccount_no() {
		return account_no;
	}
	public void setAccount_no(long account_no) throws Exception {
		try
		{
		if(Long.toString(account_no).length()<12)
			throw new IllegalArgumentException("Invalid Account Number");
		this.account_no = account_no;
		}
		catch(IllegalArgumentException e)
		{
			System.out.println(e.getMessage());
			throw new Exception(e.getMessage());
		}
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getCvv() {
		return cvv;
	}
	public void setCvv(int cvv) {
		this.cvv = cvv;
	}
	
	
	

}
