package com.nguyenngochaianh.btl.model;

public class DriverLicense {
    private int licenseId;
    private int userId;
    private String licenseNumber;
    private String category;
    private String issueDate;
    private String expiryDate;
    private String status;

    public DriverLicense(int licenseId, int userId, String licenseNumber, String category, String issueDate, String expiryDate, String status) {
        this.licenseId = licenseId;
        this.userId = userId;
        this.licenseNumber = licenseNumber;
        this.category = category;
        this.issueDate = issueDate;
        this.expiryDate = expiryDate;
        this.status = status;
    }

    public int getLicenseId() { return licenseId; }
    public int getUserId() { return userId; }
    public String getLicenseNumber() { return licenseNumber; }
    public String getCategory() { return category; }
    public String getIssueDate() { return issueDate; }
    public String getExpiryDate() { return expiryDate; }
    public String getStatus() { return status; }

    public void setLicenseId(int licenseId) { this.licenseId = licenseId; }
    public void setUserId(int userId) { this.userId = userId; }
    public void setLicenseNumber(String licenseNumber) { this.licenseNumber = licenseNumber; }
    public void setCategory(String category) { this.category = category; }
    public void setIssueDate(String issueDate) { this.issueDate = issueDate; }
    public void setExpiryDate(String expiryDate) { this.expiryDate = expiryDate; }
    public void setStatus(String status) { this.status = status; }
}
