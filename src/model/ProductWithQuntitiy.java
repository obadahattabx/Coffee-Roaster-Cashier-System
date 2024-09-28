package model;

public class ProductWithQuntitiy {
	private int pid ;
	private int count;
	private  String barcode;
	private String pname;
	private double purprice;
	private double saleprice;
	private double discount;
	private String qtype;
	private double quntity;
	private String pdate;
	private int catid;
	

	public ProductWithQuntitiy(int pid, int count, String barcode, String pname, double purprice, double saleprice,
			double discount, String qtype, double quntity, String pdate, int catid) {
		super();
		this.pid = pid;
		this.count = count;
		this.barcode = barcode;
		this.pname = pname;
		this.purprice = purprice;
		this.saleprice = saleprice;
		this.discount = discount;
		this.qtype = qtype;
		this.quntity = quntity;
		this.pdate = pdate;
		this.catid = catid;
	}
	public ProductWithQuntitiy(int pid, String barcode, String pname, double purprice, double saleprice, double discount,
			String qtype, double quntity, String pdate, int catid) {
		super();
		this.pid = pid;
		this.barcode = barcode;
		this.pname = pname;
		this.purprice = purprice;
		this.saleprice = saleprice;
		this.discount = discount;
		this.qtype = qtype;
		this.quntity = quntity;
		this.pdate = pdate;
		this.catid = catid;
	}
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
	public double getPurprice() {
		return purprice;
	}
	public void setPurprice(double purprice) {
		this.purprice = purprice;
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
	public int getCatid() {
		return catid;
	}
	public void setCatid(int catid) {
		this.catid = catid;
	}
	public String getPdate() {
		return pdate;
	}
	public void setPdate(String pdate) {
		this.pdate = pdate;
	}
	
	public String getQtype() {
		return qtype;
	}
	public void setQtype(String qtype) {
		this.qtype = qtype;
	}
	public double getQuntity() {
		return quntity;
	}
	public void setQuntity(double quntity) {
		this.quntity = quntity;
	}
	
	public ProductWithQuntitiy() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
