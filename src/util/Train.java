package util;

import java.util.Date;

public class Train {
	private String train_ID;
	private String station;
	private Date reach;
	private Date leave;
	public Train(String train_ID, String station, Date reach, Date leave) {
		super();
		this.train_ID = train_ID;
		this.station = station;
		this.reach = reach;
		this.leave = leave;
	}
	public String getTrain_ID() {
		return train_ID;
	}
	public void setTrain_ID(String train_ID) {
		this.train_ID = train_ID;
	}
	public String getStation() {
		return station;
	}
	public void setStation(String station) {
		this.station = station;
	}
	public Date getReach() {
		return reach;
	}
	public void setReach(Date reach) {
		this.reach = reach;
	}
	public Date getLeave() {
		return leave;
	}
	public void setLeave(Date leave) {
		this.leave = leave;
	}
	
}
