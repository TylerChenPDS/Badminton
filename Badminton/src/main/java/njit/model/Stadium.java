package njit.model;

public class Stadium {
	private Integer id;
	private String detail;
	private Float charge;
	private byte[] picture;
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
	public byte[] getPicture() {
		return picture;
	}
	public void setPicture(byte[] picture) {
		this.picture = picture;
	}
	@Override
	public String toString() {
		return "Stadium [id=" + id + ", detail=" + detail + ", charge=" + charge + "]";
	}
}
