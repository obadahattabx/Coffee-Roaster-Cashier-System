package model;

public class Billsale {
private int p_id;
private int sale_id;
private String detbarcode;
private String detname;
private double detsaleprice;
private String det_typequnt;
private double detqunt;
private double detsale;
private double detdiscount;
private double detfinalsale;
private int count1;
public Billsale() {
	super();
	// TODO Auto-generated constructor stub
}
public Billsale(int p_id, int sale_id, String detbarcode, String detname, double detsaleprice, String det_typequnt,
		double detqunt, double detsale, double detdiscount, double detfinalsale, int count) {
	super();
	this.p_id = p_id;
	this.sale_id = sale_id;
	this.detbarcode = detbarcode;
	this.detname = detname;
	this.detsaleprice = detsaleprice;
	this.det_typequnt = det_typequnt;
	this.detqunt = detqunt;
	this.detsale = detsale;
	this.detdiscount = detdiscount;
	this.detfinalsale = detfinalsale;
	this.count1 = count;
}
public int getCount() {
	return count1;
}
public void setCount(int count) {
	this.count1 = count;
}
public int getP_id() {
	return p_id;
}
public void setP_id(int p_id) {
	this.p_id = p_id;
}
public int getSale_id() {
	return sale_id;
}
public void setSale_id(int sale_id) {
	this.sale_id = sale_id;
}
public String getDetbarcode() {
	return detbarcode;
}
public void setDetbarcode(String detbarcode) {
	this.detbarcode = detbarcode;
}
public String getDetname() {
	return detname;
}
public void setDetname(String detname) {
	this.detname = detname;
}
public double getDetsaleprice() {
	return detsaleprice;
}
public void setDetsaleprice(double detsaleprice) {
	this.detsaleprice = detsaleprice;
}
public String getDet_typequnt() {
	return det_typequnt;
}
public void setDet_typequnt(String det_typequnt) {
	this.det_typequnt = det_typequnt;
}
public double getDetqunt() {
	return detqunt;
}
public void setDetqunt(double detqunt) {
	this.detqunt = detqunt;
}
public double getDetsale() {
	return detsale;
}
public void setDetsale(double detsale) {
	this.detsale = detsale;
}
public double getDetdiscount() {
	return detdiscount;
}
public void setDetdiscount(double detdiscount) {
	this.detdiscount = detdiscount;
}
public double getDetfinalsale() {
	return detfinalsale;
}
public void setDetfinalsale(double detfinalsale) {
	this.detfinalsale = detfinalsale;
}

}
