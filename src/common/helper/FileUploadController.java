package common.helper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Description: 文件上传 配合 bootstrap fileInput
 * @author 高雄辉
 * @date 2017年1月19日 上午3:21:32
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/common/fileUploadController")
public class FileUploadController {

	/**
	 * Upload single file using Spring Controller
	 */
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST, produces = "application/json;charset=utf8")
	@ResponseBody
	public void uploadFileHandler(@RequestParam("file") MultipartFile file, HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		if (!file.isEmpty()) {
			InputStream in = null;
			OutputStream out = null;

			try {
				Date date = new Date();
				String rootPath = request.getSession().getServletContext().getRealPath("");
				File dir = new File(rootPath + File.separator + "themes/data/active");
				if (!dir.exists())
					dir.mkdirs();
				File serverFile = new File(dir.getAbsolutePath() + File.separator + date.getTime()+file.getOriginalFilename());
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

//	/**
//	 * Upload multiple file using Spring Controller
//	 */
//	@RequestMapping(value = "/uploadMultipleFile", method = RequestMethod.POST, produces = "application/json;charset=utf8")
//	@ResponseBody
//	public Message uploadMultipleFileHandler(@RequestParam("file") MultipartFile[] files) throws IOException {
//
//		Message msg = new Message();
//		ArrayList<Integer> arr = new ArrayList<>();
//
//		for (int i = 0; i < files.length; i++) {
//			MultipartFile file = files[i];
//
//			if (!file.isEmpty()) {
//				InputStream in = null;
//				OutputStream out = null;
//
//				try {
//					String rootPath = System.getProperty("catalina.home");
//					File dir = new File(rootPath + File.separator + "tmpFiles");
//					if (!dir.exists())
//						dir.mkdirs();
//					File serverFile = new File(dir.getAbsolutePath() + File.separator + file.getOriginalFilename());
//					in = file.getInputStream();
//					out = new FileOutputStream(serverFile);
//					byte[] b = new byte[1024];
//					int len = 0;
//					while ((len = in.read(b)) > 0) {
//						out.write(b, 0, len);
//					}
//					out.close();
//					in.close();
//					logger.info("Server File Location=" + serverFile.getAbsolutePath());
//
//				} catch (Exception e) {
//					arr.add(i);
//				} finally {
//					if (out != null) {
//						out.close();
//						out = null;
//					}
//
//					if (in != null) {
//						in.close();
//						in = null;
//					}
//				}
//			} else {
//				arr.add(i);
//			}
//
//		}
//
//		if (arr.size() > 0) {
//			msg.setStatus(Status.ERROR);
//			msg.setError("Files upload fail");
//			msg.setErrorKys(arr);
//		} else {
//			msg.setStatus(Status.SUCCESS);
//			msg.setStatusMsg("Files upload success");
//		}
//		return msg;
//	}
}
