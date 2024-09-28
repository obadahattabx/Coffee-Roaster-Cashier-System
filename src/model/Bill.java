package model;

public class Bill {

private int count;
private int p_id;
private int pur_id;
private String detbarcode;
private String detname;
private double detpurprice;
private String det_typequnt ;
private double datqunt;
private double finalpure;
public double getFinalpure() {
	return finalpure;
}
public void setFinalpure(double finalpure) {
	this.finalpure = finalpure;
}
public Bill() {
	super();
	// TODO Auto-generated constructor stub
}
public Bill(int count, int p_id, int pur_id, String detbarcode, String detname, double detpurprice, String det_typequnt,
		double datqunt) {
	super();
	this.count = count;
	this.p_id = p_id;
	this.pur_id = pur_id;
	this.detbarcode = detbarcode;
	this.detname = detname;
	this.detpurprice = detpurprice;
	this.det_typequnt = det_typequnt;
	this.datqunt = datqunt;
}
public Bill(int count, int pur_id, String detbarcode, String detname, double detpurprice, String det_typequnt,
		double datqunt) {
	super();
	this.count = count;
	this.pur_id = pur_id;
	this.detbarcode = detbarcode;
	this.detname = detname;
	this.detpurprice = detpurprice;
	this.det_typequnt = det_typequnt;
	this.datqunt = datqunt;
}
public int getCount() {
	return count;
}
public void setCount(int count) {
	this.count = count;
}
public int getP_id() {
	return p_id;
}
public void setP_id(int p_id) {
	this.p_id = p_id;
}
public int getPur_id() {
	return pur_id;
}
public void setPur_id(int pur_id) {
	this.pur_id = pur_id;
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
public double getDetpurprice() {
	return detpurprice;
}
public void setDetpurprice(double detpurprice) {
	this.detpurprice = detpurprice;
}
public String getDet_typequnt() {
	return det_typequnt;
}
public void setDet_typequnt(String det_typequnt) {
	this.det_typequnt = det_typequnt;
}
public double getDatqunt() {
	return datqunt;
}
public void setDatqunt(double datqunt) {
	this.datqunt = datqunt;
}


}
