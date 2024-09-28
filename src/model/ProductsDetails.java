package model;

import javafx.scene.control.Button;

public class ProductsDetails {	
private int pid ;
private int count;
private  String barcode;
private String pname;
private double sale;
private double saleprice;
private double discount;
private double finalsale;
private String typequnt;
public ProductsDetails() {
	super();
	// TODO Auto-generated constructor stub
}
private double qunt;
public int getPid() {
	return pid;
}
public void setPid(int pid) {
	this.pid = pid;
}
public int getCount() {
	return count;
}
public void setCount(int count) {
	this.count = count;
}
public String getBarcode() {
	return barcode;
}
public void setBarcode(String barcode) {
	this.barcode = barcode;
}
public String getPname() {
	return pname;
}
public void setPname(String pname) {
	this.pname = pname;
}
public double getSale() {
	return sale;
}
public void setSale(double sale) {
	this.sale = sale;
}
public double getSaleprice() {
	return saleprice;
}
public void setSaleprice(double saleprice) {
	this.saleprice = saleprice;
}
public double getDiscount() {
	return discount;
}
public void setDiscount(double discount) {
	this.discount = discount;
}
public double getFinalsale() {
	return finalsale;
}
public void setFinalsale(double finalsale) {
	this.finalsale = finalsale;
}
public String getTypequnt() {
	return typequnt;
}
public void setTypequnt(String typequnt) {
	this.typequnt = typequnt;
}
public double getQunt() {
	return qunt;
}
public void setQunt(double qunt) {
	this.qunt = qunt;
}






}
