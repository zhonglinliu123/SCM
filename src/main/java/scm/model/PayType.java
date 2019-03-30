package scm.model;

//付款方式
public enum PayType {
	PY1(1,"货到付款"),
    PY2(2,"款到发货"),
    PY3(3,"预付款到发货");
	
	private int value;
    private String desc;//中文描述
    
    private PayType(int value,String desc){
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
	  for (PayType day:PayType.values()) {
		  if(i == day.getValue()) {
			  desc = day.getDesc();
			  break;
		  }
      }
	  return desc;
    }
  //根据desc返回id
    public static int getId(String desc) {
      int id = -1;
	  for (PayType day:PayType.values()) {
		  if(desc == day.getDesc()) {
			  id = day.getValue();
			  break;
		  }
      }
	  return id;
    }
    
}
