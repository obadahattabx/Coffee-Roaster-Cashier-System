package model;

public class Quntity {
 
private int qid;
private String qtype;
private int quntity;
private int Pid;

public int getQid() {
	return qid;
}
public void setQid(int qid) {
	this.qid = qid;
}
public String getQtype() {
	return qtype;
}
public void setQtype(String qtype) {
	this.qtype = qtype;
}
public int getQuntity() {
	return quntity;
}
public void setQuntity(int quntity) {
	this.quntity = quntity;
}
public int getPid() {
	return Pid;
}
public void setPid(int pid) {
	Pid = pid;
}
public Quntity(int qid,String qtype, int quntity, int pid) {
	super();
	this.qid=qid;
	this.qtype = qtype;
	this.quntity = quntity;
	Pid = pid;
}

public Quntity() {
	super();
	// TODO Auto-generated constructor stub
}


}
