package njit.model.toolbean;


public class TimeCodeBean {
	public TimeCodeBean() {}
	
	public TimeCodeBean(int code) {
		this.code = code;
	}
	public TimeCodeBean(int code,boolean islimit) {
		this.code = code;
		this.islimits = islimit;
	}
	
	private boolean islimits = false;


	

	public boolean isIslimits() {
		return islimits;
	}

	public void setIslimits(boolean islimits) {
		this.islimits = islimits;
	}

	private int code;

	public int getCode() {
		return code;
	}
	
	

	public void setCode(int code) {
		this.code = code;
	}
	
	public String getTimeStr() {
//		String flag = islimits == false ? "" : "(不可选)";
		if(this.code % 2 == 0) 
			return String.format("%02d:30", this.code / 2 - 1) + "-" + String.format("%02d:00", this.code / 2) /*+ flag*/;
		return String.format("%02d:00", this.code / 2 ) + "-" + String.format("%02d:30", this.code / 2) /*+ flag*/;
	}
	
	
	public static void main(String[] args) {
		for(int i = 17; i < 43; i ++) {
			TimeCodeBean b = new TimeCodeBean();
			b.setCode(i);
			if(i % 2 == 0)
				b.setIslimits(true);
			System.out.println(b.getTimeStr());
		}
		
	}
	
	
}
