package njit.model;

import java.sql.Date;

public class Booklimitation {
	private int id;
	private int sid;
	private Date date;
	private String timecode;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getTimecode() {
		return timecode;
	}
	public void setTimecode(String timecode) {
		this.timecode = timecode;
	}
	@Override
	public String toString() {
		return "Booklimitation [id=" + id + ", sid=" + sid + ", date=" + date + ", timecode=" + timecode + "]";
	}
	
}
