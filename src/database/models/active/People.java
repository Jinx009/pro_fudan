package database.models.active;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 活动报名
 * @author jinx
 *
 */
@Entity
@Table(name="people_jgy")
public class People {

	@Id
	@Column(unique=true, nullable=false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "nick_name")
	private String nickName;
	@Column(name = "mobile_phone")
	private String mobiePhone;
	@Column(name = "active_name")
	private String activeName;
	@Column(name = "name")
	private String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name = "active_id")
	private int activeId;
	@Column(name = "user_id")
	private int userId;
	
	@Column(name = "add_time")
	@DateTimeFormat(pattern="yyyy-MM-dd")														
	private Date addTime;
	/**
	 * 0代表报名用户，1代表取消报名，2代表喜欢用户，3代表取消喜欢用户
	 */
	@Column(name = "type")
	private int type;
	@Column(name = "danwei")
	private String danwei;
	
	
	
	public String getDanwei() {
		return danwei;
	}
	public void setDanwei(String danwei) {
		this.danwei = danwei;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getMobiePhone() {
		return mobiePhone;
	}
	public void setMobiePhone(String mobiePhone) {
		this.mobiePhone = mobiePhone;
	}
	public String getActiveName() {
		return activeName;
	}
	public void setActiveName(String activeName) {
		this.activeName = activeName;
	}
	public int getActiveId() {
		return activeId;
	}
	public void setActiveId(int activeId) {
		this.activeId = activeId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	/**
	 * @return the addTime
	 */
	public Date getAddTime() {
		return addTime;
	}
	/**
	 * @param addTime the addTime to set
	 */
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
	
	
}
