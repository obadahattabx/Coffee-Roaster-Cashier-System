package model;

public class Ordersale {
private int saleid;
private int cust_id;
private String saledec;
private String saleuseradd;
private  String saleaddtime;
private String saledate;
private String nameSup;
public String getNameSup() {
	return nameSup;
}
public void setNameSup(String nameSup) {
	this.nameSup = nameSup;
}
public Ordersale(int saleid, int cust_id, String saledec, String saleuseradd, String saleaddtime, String saledate,
		String nameSup) {
	super();
	this.saleid = saleid;
	this.cust_id = cust_id;
	this.saledec = saledec;
	this.saleuseradd = saleuseradd;
	this.saleaddtime = saleaddtime;
	this.saledate = saledate;
	this.nameSup = nameSup;
}
public int getSaleid() {
	return saleid;
}
public void setSaleid(int saleid) {
	this.saleid = saleid;
}
public int getCust_id() {
	return cust_id;
}
public void setCust_id(int cust_id) {
	this.cust_id = cust_id;
}
public String getSaledec() {
	return saledec;
}
public void setSaledec(String saledec) {
	this.saledec = saledec;
}
public String getSaleuseradd() {
	return saleuseradd;
}
public void setSaleuseradd(String saleuseradd) {
	this.saleuseradd = saleuseradd;
}
public String getSaleaddtime() {
	return saleaddtime;
}
public void setSaleaddtime(String saleaddtime) {
	this.saleaddtime = saleaddtime;
}
public String getSaledate() {
	return saledate;
}
public void setSaledate(String saledate) {
	this.saledate = saledate;
}
public Ordersale() {
	super();
	// TODO Auto-generated constructor stub
}
public Ordersale(int saleid, int cust_id, String saledec, String saleuseradd, String saleaddtime, String saledate) {
	super();
	this.saleid = saleid;
	this.cust_id = cust_id;
	this.saledec = saledec;
	this.saleuseradd = saleuseradd;
	this.saleaddtime = saleaddtime;
	this.saledate = saledate;
}

}
