package main.entry.webapp.active;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


import common.helper.AjaxJson;
import common.helper.HttpWebIOHelper;
import common.helper.MyBeanUtils;
import common.helper.StringUtil;
import database.common.PageDataList;
import database.models.active.Address;
import service.basicFunctions.active.AddressService;

/**
 * 
 * @Description: 地址
 * @author 高雄辉
 * @date 2017年1月17日 下午22:30:56
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/active/addressController")
public class AddressController {

	@Autowired
	private AddressService addressService;

	/**
	 * 
	 * @Description: 地址信息页面
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping(path = "/list")
	public String list() {
		return "active/addressList";
	}

	/**
	 * 
	 * @Description: 地址信息列表数据
	 *
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping(value = "/loadData")
	public void loadData(HttpServletRequest req, HttpServletResponse res) throws IOException {
		String currentPage = req.getParameter("currentPage");
		PageDataList<Address> pageDataList = addressService
				.loadDataByPage(StringUtil.isNotBlank(currentPage) ? Integer.parseInt(currentPage) : 1);
		HttpWebIOHelper._printWebJson(pageDataList, res);
	}
	
	/**
	 * 
	 * @Description: 获取父级地址信息列表数据
	 *
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping(value = "/loadFatherAddress")
	public void loadFatherAddress(HttpServletRequest req, HttpServletResponse res) throws IOException {
		List<Address> addresses=addressService.findByHql("from Address where fatherId=0");
		HttpWebIOHelper._printWebJson(addresses, res);
	}
	
	/**
	 * 
	 * @Description: 获取二级地址信息列表数据
	 *
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping(value = "/loadSecondAddress")
	public void loadSecondAddress(HttpServletRequest req, HttpServletResponse res) throws IOException {
		List<Address> addresses=addressService.findByHql("from Address where fatherId!=0 order by fatherId");
		HttpWebIOHelper._printWebJson(addresses, res);
	}

	/**
	 * 
	 * @Description: 添加地址
	 *
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping(value = "/addOrUpdate")
	public void addOrUpdate(HttpServletRequest req, HttpServletResponse res, Address address) throws IOException {
		if (null!=address.getId()) {
			Address addressOld=addressService.find(address.getId());
			try {
				MyBeanUtils.copyBean2Bean(addressOld,address);
			} catch (Exception e) {
				e.printStackTrace();
			}
			addressService.update(address);
		} else {
			addressService.save(address);
		}
		HttpWebIOHelper._printWebJson(AjaxJson.successAjaxJson("修改成功!"), res);
	}

	/**
	 * 
	 * @Description: 删除地址
	 *
	 * @param req
	 * @param res
	 * @param activeId
	 */
	@RequestMapping(value = "/del")
	public void del(HttpServletRequest req, HttpServletResponse res, int addressId) throws IOException {
		addressService.delete(addressId);
		HttpWebIOHelper._printWebJson(AjaxJson.successAjaxJson("删除成功!"), res);
	}

}
