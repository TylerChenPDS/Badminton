package njit.model;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

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
	@Override
	public String toString() {
		return "Booking [id=" + id + ", uid=" + uid + ", sid=" + sid + ", date=" + date + ", timecode=" + timecode
				+ "]";
	}
	
	public String getTimeandcode() {
		SimpleDateFormat si = new SimpleDateFormat("yyyy-MM-dd");
		return 	si.format(date) + "  " + timeCodetoTime.get(timecode);
	}
	
	
	
	
	
	static Map<Integer,String> timeCodetoTime = new HashMap<>();
	static {
		String times[] = {
				"00:00 -- 00:30","00:30 -- 01:00",
				"01:00 -- 01:30","01:30 -- 02:00",
				"02:00 -- 02:30","02:30 -- 03:00",
				"03:00 -- 03:30","03:30 -- 04:00",
				"04:00 -- 04:30","04:30 -- 05:00",
				"05:00 -- 05:30","05:30 -- 06:00",
				"06:00 -- 06:30","06:30 -- 07:00",
				"07:00 -- 07:30","07:30 -- 08:00",
				"08:00 -- 08:30","08:30 -- 09:00",
				"09:00 -- 09:30","09:30 -- 10:00",
				"10:00 -- 10:30","10:30 -- 11:00",
				"11:00 -- 11:30","11:30 -- 12:00",
				"12:00 -- 12:30","12:30 -- 13:00",
				"13:00 -- 13:30","13:30 -- 14:00",
				"14:00 -- 14:30","14:30 -- 15:00",
				"15:00 -- 15:30","15:30 -- 16:00",
				"16:00 -- 16:30","16:30 -- 17:00",
				"17:00 -- 17:30","17:30 -- 18:00",
				"18:00 -- 18:30","18:30 -- 19:00",
				"19:00 -- 19:30","19:30 -- 20:00",
				"20:00 -- 20:30","20:30 -- 21:00",
				"21:00 -- 21:30","21:30 -- 22:00",
				"22:00 -- 22:30","22:30 -- 23:00",
				"23:00 -- 23:30","23:30 -- 24:00",
				};
		
		for(int i = 1; i <= 48; i ++) {
			timeCodetoTime.put(i, times[i-1]);
		}
	}
}
