package model;

public class purpriceOrder {
private int purid ;
private String purdate;
private String purdesc;
private int sup_id;
private String puruseradd;
public int getPurid() {
	return purid;
}
public void setPurid(int purid) {
	this.purid = purid;
}
public String getPurdate() {
	return purdate;
}
public void setPurdate(String purdate) {
	this.purdate = purdate;
}
public String getPurdesc() {
	return purdesc;
}
public void setPurdesc(String purdesc) {
	this.purdesc = purdesc;
}
public int getSup_id() {
	return sup_id;
}
public void setSup_id(int sup_id) {
	this.sup_id = sup_id;
}
public String getPuruseradd() {
	return puruseradd;
}
public void setPuruseradd(String puruseradd) {
	this.puruseradd = puruseradd;
}
public purpriceOrder(int purid, String purdate, String purdesc, int sup_id, String puruseradd) {
	super();
	this.purid = purid;
	this.purdate = purdate;
	this.purdesc = purdesc;
	this.sup_id = sup_id;
	this.puruseradd = puruseradd;
}
public purpriceOrder() {
	super();
	// TODO Auto-generated constructor stub
}

}
