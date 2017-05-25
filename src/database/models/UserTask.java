package database.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name="jgy_user_task")
public class UserTask {

	@Id
	@Column(unique=true, nullable=false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	//发送内容
	@Column(name = "text")
	private String text;
	//openid
	@Column(name = "openid")
	private String openid;
	//用户id
	@Column(name = "user_id")
	private Integer userId;
	//添加时间
	@Column(name = "send_time")
	private Date sendTime;
	//发送时间
	@Column(name = "add_time")
	private Date addTime;
	//发送状态
	@Column(name = "status")
	private int status;
	//user要去
	@Column(name = "user_go")
	private int userGo;
	//喜欢的去
	@Column(name = "like_user_go")
	private int likeUserGo;
	@Column(name = "like_openid")
	private String likeOpenid;
	
	
	
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Date getSendTime() {
		return sendTime;
	}
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
	public Date getAddTime() {
		return addTime;
	}
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getUserGo() {
		return userGo;
	}
	public void setUserGo(int userGo) {
		this.userGo = userGo;
	}
	public int getLikeUserGo() {
		return likeUserGo;
	}
	public void setLikeUserGo(int likeUserGo) {
		this.likeUserGo = likeUserGo;
	}
	public String getLikeOpenid() {
		return likeOpenid;
	}
	public void setLikeOpenid(String likeOpenid) {
		this.likeOpenid = likeOpenid;
	}
	
}
