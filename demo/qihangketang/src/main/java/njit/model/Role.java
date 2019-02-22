package njit.model;

import java.util.List;

public class Role {
	private Integer id;
	private String name;
	private String code;
	private List<Resource> resources;
	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + ", code=" + code + ", resources=" + resources + "]";
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	public List<Resource> getResources() {
		return resources;
	}
	public void setResources(List<Resource> resources) {
		this.resources = resources;
	}
	@Override
	public boolean equals(Object obj) {
		if(obj == this)
			return true;
		if(obj instanceof Role) {
			Role role = (Role)obj;
			return role.id == this.id;
		}
		return false;
	}
	
	
}
