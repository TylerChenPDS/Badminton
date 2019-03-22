package njit.model;

public class Role {
	private Integer id;
	private String rolename;
	private String rolecode;
	private Float discount;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRolename() {
		return rolename;
	}
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	public String getRolecode() {
		return rolecode;
	}
	public void setRolecode(String rolecode) {
		this.rolecode = rolecode;
	}
	public Float getDiscount() {
		return discount;
	}
	public void setDiscount(Float discount) {
		this.discount = discount;
	}
	@Override
	public String toString() {
		return "Role [id=" + id + ", rolename=" + rolename + ", rolecode=" + rolecode + ", discount=" + discount + "]";
	}
	
}
