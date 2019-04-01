package njit.model;

import java.sql.Date;
import java.util.Arrays;
import java.util.Comparator;

public class Booklimitation {
	private int id;
	private int sid;
	private Date date;
	private String timecode;
	private Stadium stadium;
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
	public Stadium getStadium() {
		return stadium;
	}
	
	public void setStadium(Stadium stadium) {
		this.stadium = stadium;
	}
	
	//将timecode转化程时间段
	public  String getTimeStrs() {
		String str = "";
		String timecodes[] = this.timecode.trim().split(",");
		Integer timecodenums[] = new Integer[timecodes.length];
		for(int i = 0; i < timecodes.length; i ++) {
			timecodenums[i] = Integer.parseInt(timecodes[i]);
		}
		Arrays.sort(timecodenums, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o1 - o2;
			}
		});
		
		int begin = timecodenums[0];
		str += inverse(begin);
		for(int i = 1; i < timecodenums.length; i ++) {
			if(timecodenums[i] - timecodenums[i - 1] > 1) {
				if(timecodenums[i - 1] != begin) {
					str += "-" + inverse(timecodenums[i - 1]);
					begin = timecodenums[i];
					str += "," + inverse(begin);
				}
			}
		}
		if(timecodenums.length >= 2 && timecodenums[timecodenums.length - 1] - timecodenums[timecodenums.length - 2] == 1) {
			str += "-" + inverse(timecodenums[timecodenums.length - 1]);
		}
		return str;
 	}
			
	private  String inverse(Integer num) {
		if(num % 2 == 0)
			return String.format("%02d:30", num / 2 - 1);
		return String.format("%02d:00", num / 2);
	}
	
	
	public static void main(String[] args) {
		Booklimitation b = new Booklimitation();
		b.setTimecode("2,1,3,4,5,6,8,9,10");
		System.out.println(b.getTimeStrs());
	}
	
}
