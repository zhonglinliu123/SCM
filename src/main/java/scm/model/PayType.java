package scm.model;

//���ʽ
public enum PayType {
	PY1(1,"��������"),
    PY2(2,"�����"),
    PY3(3,"Ԥ�������");
	
	private int value;
    private String desc;//��������
    
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
    
    //����id����desc
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
  //����desc����id
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
