package njit.model.toolbean;

public class TimeCodeBean {
	public TimeCodeBean() {}
	
	public TimeCodeBean(int code) {
		this.code = code;
	}
	
	private int code;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
	
	public String getTimeStr() {
		if(this.code % 2 == 0) 
			return String.format("%02d:30", this.code / 2 - 1) + "-" + String.format("%02d:00", this.code / 2);
		return String.format("%02d:00", this.code / 2 ) + "-" + String.format("%02d:30", this.code / 2);
	}
	
	
	public static void main(String[] args) {
		for(int i = 17; i < 43; i ++) {
			TimeCodeBean b = new TimeCodeBean();
			b.setCode(i);
			System.out.println(b.getTimeStr());
		}
		
	}
	
	
}
