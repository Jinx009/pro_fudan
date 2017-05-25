package main.entry.webapp.active;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import common.helper.AjaxJson;
import common.helper.HttpWebIOHelper;
import common.helper.MyBeanUtils;
import common.helper.StringUtil;
import database.common.PageDataList;
import database.models.active.Banner;
import service.basicFunctions.active.BannerService;

/**
 * 
 * @Description: 头图
 * @author 高雄辉
 * @date 2017年1月17日 下午22:30:56
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/active/bannerController")
public class BannerController {

	@Autowired
	private BannerService bannerService;

	/**
	 * 
	 * @Description: 头图信息页面
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping(path = "/list")
	public String list() {
		return "active/bannerList";
	}

	/**
	 * 
	 * @Description: 头图信息列表数据
	 *
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping(value = "/loadData")
	public void loadData(HttpServletRequest req, HttpServletResponse res) throws IOException {
		String currentPage = req.getParameter("currentPage");
		PageDataList<Banner> pageDataList = bannerService
				.loadDataByPage(StringUtil.isNotBlank(currentPage) ? Integer.parseInt(currentPage) : 1);
		HttpWebIOHelper._printWebJson(pageDataList, res);
	}

	/**
	 * 
	 * @Description: 添加头图
	 *
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping(value = "/addOrUpdate")
	public void addOrUpdate(HttpServletRequest req, HttpServletResponse res, Banner banner) throws IOException {
		if (null!=banner.getId()) {
			Banner bannerOld=bannerService.find(banner.getId());
			try {
				MyBeanUtils.copyBean2Bean(bannerOld,banner);
			} catch (Exception e) {
				e.printStackTrace();
			}
			banner.setUpdateTime(new Date());
			bannerService.update(banner);
		} else {
			banner.setUpdateTime(new Date());
			bannerService.save(banner);
		}
		HttpWebIOHelper._printWebJson(AjaxJson.successAjaxJson("修改成功!"), res);
	}

	/**
	 * 
	 * @Description: 删除头图
	 *
	 * @param req
	 * @param res
	 * @param activeId
	 */
	@RequestMapping(value = "/del")
	public void del(HttpServletRequest req, HttpServletResponse res, int bannerId) throws IOException {
		bannerService.delete(bannerId);
		HttpWebIOHelper._printWebJson(AjaxJson.successAjaxJson("删除成功!"), res);
	}
	
	/**
	 * Upload single file using Spring Controller
	 */
	@RequestMapping(value = "/uploadFile")
	public void uploadFileHandler(@RequestParam("file") MultipartFile file, HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		if (!file.isEmpty()) {
			InputStream in = null;
			OutputStream out = null;

			try {
				Date date = new Date();
				String rootPath = request.getSession().getServletContext().getRealPath("");
				File dir = new File(rootPath + File.separator + "themes/data/active/banner");
				if (!dir.exists())
					dir.mkdirs();
				File serverFile = new File(dir.getAbsolutePath() + File.separator +date.getTime()+ file.getOriginalFilename());
				in = file.getInputStream();
				out = new FileOutputStream(serverFile);
				byte[] b = new byte[1024];
				int len = 0;
				while ((len = in.read(b)) > 0) {
					out.write(b, 0, len);
				}
				out.close();
				in.close();
				AjaxJson ajaxJson = AjaxJson.successAjaxJson("文件上传成功!");
				ajaxJson.setObj("/themes/data/active/banner/"+date.getTime()+file.getOriginalFilename());
				HttpWebIOHelper._printWebJson(ajaxJson, response);
			} catch (Exception e) {
				AjaxJson ajaxJson = AjaxJson.successAjaxJson("文件上传失败!");
				HttpWebIOHelper._printWebJson(ajaxJson, response);
			} finally {
				if (out != null) {
					out.close();
					out = null;
				}

				if (in != null) {
					in.close();
					in = null;
				}
			}
		} else {
			AjaxJson ajaxJson = AjaxJson.successAjaxJson("未找到文件!");
			HttpWebIOHelper._printWebJson(ajaxJson, response);
		}
	}
	
	

}
