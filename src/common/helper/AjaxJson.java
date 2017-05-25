package common.helper;

import java.util.Map;

import com.alibaba.fastjson.JSONObject;

/**
 * $.ajax后需要接受的JSON
 * 
 * @author 高雄輝
 * 
 */
public class AjaxJson {

	private boolean success = true;// 是否成功
	private String msg = "操作成功";// 提示信息
	private Object obj = null;// 其他信息
	private Map<String, Object> attributes;// 其他参数
	
	
	
	/**
	 *  ajax 请求返回数据
	 */
	public AjaxJson() {
	}

	/**
	 * @param success
	 * @param msg
	 */
	public AjaxJson(boolean success, String msg) {
		this.success = success;
		this.msg = msg;
	}

	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getJsonStr(){
		JSONObject obj = new JSONObject();
		obj.put("success", this.isSuccess());
		obj.put("msg", this.getMsg());
		obj.put("obj", this.obj);
		obj.put("attributes", this.attributes);
		return obj.toJSONString();
	}
	
	
	public static AjaxJson successAjaxJson(String msg){
		return new AjaxJson(true,msg);
	}
	public static AjaxJson failAjaxJson(String msg){
		return new AjaxJson(false,msg);
	}
}
