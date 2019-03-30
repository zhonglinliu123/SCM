package scm.model;

// ����״̬
public enum Status {
	S1(1,"����"),
	S2(2,"���ջ�"),
	S3(3,"�Ѹ���"),
	S4(4,"���˽�"),
	S5(5,"��Ԥ��");
	
	private int value;
	private String desc;//��������
  
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
  
  //����id����desc
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
//����desc����id
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
