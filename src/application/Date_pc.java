package application;

import java.util.Date;

public class Date_pc {
private  Date dt;



public  String getdate() {
	dt=new Date();
	String dat=String.valueOf(1900+dt.getYear())+"-"+String.valueOf(dt.getMonth()+1)+"-"+String.valueOf(dt.getDate());
	return dat;
}
public  String getTime() {
	dt=new Date();
	String dat=dt.getHours()+":"+dt.getMinutes()+":"+dt.getSeconds();
	return dat;
}
}
