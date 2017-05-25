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
 * 素材
 * @author 高雄輝
 *
 */
@Entity
@Table(name="material_jgy")
public class Material {
	
	@Id
	@Column(unique=true, nullable=false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "active_id")
	private int activeId;
	
	/**
	 * 1图片  2 文本
	 */
	@Column(name = "type")
	private int type;
	
	@Column(name = "remark")
	private String remark;
	
	@Column(name = "content")
	private String content;

	@Column(name = "add_time")
	@DateTimeFormat(pattern="yyyy-MM-dd")														
	private Date addTime;
	@Column(name = "order_num")
	private String orderNum;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	

	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	/**
	 * @return the activeId
	 */
	public int getActiveId() {
		return activeId;
	}
	/**
	 * @param activeId the activeId to set
	 */
	public void setActiveId(int activeId) {
		this.activeId = activeId;
	}
	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}
	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}
	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
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
	
	
}
