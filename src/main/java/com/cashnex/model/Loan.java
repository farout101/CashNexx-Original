package com.cashnex.model;
import java.util.Date;

public class Loan {
    private int loanId;
    private int userId;
    private String userName;
    private String accountNumber;
    private String reason;
    private double loanAmount;
    private double paidAmount;
    private double repayment_amount;
    private Date dueDate;
    private boolean fullyPaid;

    // Constructors
    public Loan() {
    }

    public int getLoanId() {
		return loanId;
	}

	public void setLoanId(int loanId) {
		this.loanId = loanId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public double getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(double loanAmount) {
		this.loanAmount = loanAmount;
	}

	public double getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(double paidAmount) {
		this.paidAmount = paidAmount;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public boolean isFullyPaid() {
		return paidAmount >= repayment_amount;
	}

	public void setFullyPaid(boolean fullyPaid) {
		this.fullyPaid = fullyPaid;
	}

	public Loan(int loanId, int userId, String userName, String accountNumber, String reason, double loanAmount, double paidAmount, Date dueDate, boolean fullyPaid) {
        this.loanId = loanId;
        this.userId = userId;
        this.userName = userName;
        this.accountNumber = accountNumber;
        this.reason = reason;
        this.loanAmount = loanAmount;
        this.paidAmount = paidAmount;
        this.dueDate = dueDate;
        this.fullyPaid = fullyPaid;
    }

	public double getRepayment_amount() {
		return repayment_amount;
	}

	public void setRepayment_amount(double repayment_amount) {
		this.repayment_amount = repayment_amount;
	}

	
}

// UPDATED LOAN STRUCTURE