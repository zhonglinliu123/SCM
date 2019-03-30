package scm.model;

// 处理状态
public enum Status {
	S1(1,"新增"),
	S2(2,"已收货"),
	S3(3,"已付款"),
	S4(4,"已了结"),
	S5(5,"已预付");
	
	private int value;
	private String desc;//中文描述
  
	private Status(int value,String desc){
	  	this.value=value;
	    this.desc=desc;
	}

	public String getDesc(){
		return desc;
	}
  
	public int getValue() {
		return value;
	}
  
  //根据id返回desc
  public static String getDesc(int i) {
    String desc = null;
	  for (Status s:Status.values()) {
		  if(i == s.getValue()) {
			  desc = s.getDesc();
			  break;
		  }
    }
	  return desc;
  }
//根据desc返回id
  public static int getId(String desc) {
    int id = -1;
	  for (Status s:Status.values()) {
		  if(desc == s.getDesc()) {
			  id = s.getValue();
			  break;
		  }
    }
	  return id;
  }
  
}
