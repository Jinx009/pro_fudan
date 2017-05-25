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
 * 活动相关
 * @author jinx
 *
 */
@Entity
@Table(name="active_jgy")
public class Active {
	
	@Id
	@Column(unique=true, nullable=false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "name")
	private String name;
	@Column(name="type")
	private String type;
	@Column(name = "start_time")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")														
	private Date startTime;
	@Column(name = "end_time")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")														
	private Date endTime;
	@Column(name = "open_time")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")														
	private Date openTime;
	@Column(name = "close_time")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")														
	private Date closeTime;
	@Column(name = "top_img")
	private String topImg;
	@Column(name = "address")
	private String address;
	@Column(name = "detail_address")
	private String detailAddress;
	@Column(name = "detail")
	private String detail;
	@Column(name = "detail_img")
	private String detailImg;
	@Column(name = "add_time")
	@DateTimeFormat(pattern="yyyy-MM-dd")														
	private Date addTime;
	@Column(name = "add_user")
	private String addUser;
	@Column(name = "status")
	private String status;
	@Column(name = "num")
	private Integer num;
	
	
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
	
	
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Date getOpenTime() {
		return openTime;
	}
	public void setOpenTime(Date openTime) {
		this.openTime = openTime;
	}
	public Date getCloseTime() {
		return closeTime;
	}
	public void setCloseTime(Date closeTime) {
		this.closeTime = closeTime;
	}
	public String getTopImg() {
		return topImg;
	}
	public void setTopImg(String topImg) {
		this.topImg = topImg;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDetailAddress() {
		return detailAddress;
	}
	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getDetailImg() {
		return detailImg;
	}
	public void setDetailImg(String detailImg) {
		this.detailImg = detailImg;
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
	public String getAddUser() {
		return addUser;
	}
	public void setAddUser(String addUser) {
		this.addUser = addUser;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	
	
}
