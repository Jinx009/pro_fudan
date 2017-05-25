package database.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="gift_user")
public class GiftUser {

	@Id
	@Column(unique=true, nullable=false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "real_name")
	private String realName;
	
	@Column(name = "gift_value")
	private String giftValue;
	
	@Column(name = "gift_status")
	private int giftStatus;
	
	@Column(name = "number")
	private String number;
	
	@Column(name = "gift_time")
	private Date giftTime;
	
	@Column(name = "desk_num")
	private int deskNum;
	
	@Column(name = "desk")
	private int desk;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getGiftValue() {
		return giftValue;
	}

	public void setGiftValue(String giftValue) {
		this.giftValue = giftValue;
	}

	public int getGiftStatus() {
		return giftStatus;
	}

	public void setGiftStatus(int giftStatus) {
		this.giftStatus = giftStatus;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Date getGiftTime() {
		return giftTime;
	}

	public void setGiftTime(Date giftTime) {
		this.giftTime = giftTime;
	}

	public int getDeskNum() {
		return deskNum;
	}

	public void setDeskNum(int deskNum) {
		this.deskNum = deskNum;
	}

	public int getDesk() {
		return desk;
	}

	public void setDesk(int desk) {
		this.desk = desk;
	}
	
	
	
}
