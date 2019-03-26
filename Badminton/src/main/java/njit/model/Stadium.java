package njit.model;

public class Stadium {
	private Integer id;
	private String detail;
	private Float charge;
	private Integer pid;//图片的
	
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public Float getCharge() {
		return charge;
	}
	public void setCharge(Float charge) {
		this.charge = charge;
	}
	@Override
	public String toString() {
		return "Stadium [id=" + id + ", detail=" + detail + ", charge=" + charge + ", pid=" + pid + "]";
	}
	
}
