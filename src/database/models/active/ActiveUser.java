package database.models.active;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 活动用户
 * @author jinx
 *
 */
@Entity
@Table(name="active_user_jgy")
public class ActiveUser {

	@Id
	@Column(unique=true, nullable=false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "name")
	private String name;
	@Column(name = "nick_name")
	private String nickName;
	@Column(name = "open_id")
	private String openId;
	@ManyToOne
	@JoinColumn(name = "address")
	private Address address;
	@Column(name = "email")
	private String email;
	@Column(name = "mobile_phone")
	private String mobilePhone;
	@Column(name = "bind_status")
	private int bindstatus;
	@Column(name = "img")
	private String img;
	@Column(name = "add_time")
	@DateTimeFormat(pattern="yyyy-MM-dd")														
	private Date addTime;
	@Column(name = "sex")
	private String sex;
	
	
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
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobilePhone() {
		return mobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	public int getBindstatus() {
		return bindstatus;
	}
	public void setBindstatus(int bindstatus) {
		this.bindstatus = bindstatus;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
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
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	
	
	
}
