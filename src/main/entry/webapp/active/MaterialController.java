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
import database.models.active.Material;
import service.basicFunctions.active.MaterialService;

/**
 * 
 * @Description: 素材
 * @author 高雄辉
 * @date 2017年1月17日 下午22:30:56
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/active/materialController")
public class MaterialController {

	@Autowired
	private MaterialService materialService;

	/**
	 * 
	 * @Description: 素材信息页面
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping(path = "/list")
	public String list(HttpServletRequest req, HttpServletResponse res) {
		Integer activeId=Integer.parseInt(req.getParameter("activeId"));
		req.setAttribute("activeId",activeId);
		return "active/materialList";
	}

	/**
	 * 
	 * @Description: 素材信息列表数据
	 *
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping(value = "/loadData")
	public void loadData(HttpServletRequest req, HttpServletResponse res) throws IOException {
		String currentPage = req.getParameter("currentPage");
		Integer activeId=Integer.parseInt(req.getParameter("activeId"));
		PageDataList<Material> pageDataList = materialService
				.loadDataByPage(StringUtil.isNotBlank(currentPage) ? Integer.parseInt(currentPage) : 1,activeId);
		HttpWebIOHelper._printWebJson(pageDataList, res);
	}

	/**
	 * 
	 * @Description: 添加素材
	 *
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping(value = "/addOrUpdate")
	public void addOrUpdate(HttpServletRequest req, HttpServletResponse res, Material material) throws IOException {
		if (null!=material.getId()) {
			Material materialOld=materialService.find(material.getId());
			try {
				MyBeanUtils.copyBean2Bean(materialOld,material);
			} catch (Exception e) {
				e.printStackTrace();
			}
			material.setAddTime(new Date());
			materialService.update(material);
		} else {
			material.setAddTime(new Date());
			materialService.save(material);
		}
		HttpWebIOHelper._printWebJson(AjaxJson.successAjaxJson("修改成功!"), res);
	}

	/**
	 * 
	 * @Description: 删除素材
	 *
	 * @param req
	 * @param res
	 * @param activeId
	 */
	@RequestMapping(value = "/del")
	public void del(HttpServletRequest req, HttpServletResponse res, int materialId) throws IOException {
		materialService.delete(materialId);
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
				File dir = new File(rootPath + File.separator + "themes/data/active/material");
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
				ajaxJson.setObj("/themes/data/active/material/"+date.getTime()+file.getOriginalFilename());
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
