package model;

public class Category {
private int cid ;
private int count ;
public Category(int cid, int count, String cname) {
	super();
	this.cid = cid;
	this.count = count;
	this.cname = cname;
}
public int getCount() {
	return count;
}
public void setCount(int count) {
	this.count = count;
}
private String cname;



public Category(int cid, String cname) {
	super();
	this.cid = cid;
	this.cname = cname;
}
public int getCid() {
	return cid;
}
public void setCid(int cid) {
	this.cid = cid;
}
public String getCname() {
	return cname;
}
public void setCname(String cname) {
	this.cname = cname;
}
public Category() {
	super();
	// TODO Auto-generated constructor stub
}

}
