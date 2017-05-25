package database.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * 微信需缓存数据
 * @author Jinx
 *
 */
@Entity
@Table(name = ("jgy_wechat_cache"))
public class WechatCache implements java.io.Serializable{

	private static final long serialVersionUID = 6377240436786934035L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	/**
	 * 缓存数据名称
	 */
	@Column(name = "cache_name")
	private String cacheName;
	/**
	 * 缓存值
	 */
	@Column(name = "cache_value")
	private String cacheValue;
	/**
	 * 公众平台id
	 */
	@Column(name = "app_id")
	private String appId;
	/**
	 * 更新时间戳
	 */
	@Column(name = "last_timestamp")
	private Integer lastTimestamp;
	
	

	public Integer getId(){
		return id;
	}

	public void setId(Integer id){
		this.id = id;
	}

	public String getCacheName(){
		return cacheName;
	}

	public void setCacheName(String cacheName){
		this.cacheName = cacheName;
	}

	public String getCacheValue(){
		return cacheValue;
	}

	public void setCacheValue(String cacheValue){
		this.cacheValue = cacheValue;
	}

	public String getAppId(){
		return appId;
	}

	public void setAppId(String appId){
		this.appId = appId;
	}

	public Integer getLastTimestamp(){
		return lastTimestamp;
	}

	public void setLastTimestamp(Integer lastTimestamp){
		this.lastTimestamp = lastTimestamp;
	}

}
