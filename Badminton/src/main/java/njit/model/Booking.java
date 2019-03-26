package njit.model;

import java.sql.Date;

public class Booking {
	private int id;
	private int uid;
	private int sid;
	private Date date;
	private int timecode;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getTimecode() {
		return timecode;
	}
	public void setTimecode(int timecode) {
		this.timecode = timecode;
	}
}
