package com.itheima.web.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import com.itheima.domain.Category;
import com.itheima.domain.Product;
import com.itheima.service.ProductService;
import com.itheima.service.impl.ProductServiceImpl;
import com.itheima.utils.UUIDUtils;

public class MyFileUploadServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//1.获得
			//1.1 使用文件上传的代码获得数据
			String tempPath = getServletContext().getRealPath("temp");
			DiskFileItemFactory factory = new DiskFileItemFactory(1024*1024, new File(tempPath));
			
			ServletFileUpload upload = new ServletFileUpload(factory);
			
			List<FileItem> list = upload.parseRequest(request);
			//创建Product
			//创建map 出来  map<字段名称 , 字段的值>
			Map<String, Object> map = new HashMap<String, Object>();
			
			
			for (FileItem fileItem : list) {
				boolean flag = fileItem.isFormField();
				if(flag){
					String fieldName = fileItem.getFieldName();//表单的name名称
					String string = fileItem.getString("utf-8");//提交的value值
					map.put(fieldName, string);
				}else{
					String name = fileItem.getName();//上传的文件名称
					String fieldName = fileItem.getFieldName();//表单的name名称
					
					//输入流
					InputStream is = fileItem.getInputStream();
					//输出流
					String realPath = getServletContext().getRealPath("upload");
					OutputStream os = new FileOutputStream( realPath + "/" +name);
					IOUtils.copy(is, os);
					is.close();
					os.close();
					//删除临时文件
					fileItem.delete();
					
					map.put(fieldName, "upload/"+ name);
				}
			}
			
			//1.2 封装出一个product对象
			Product product = new Product();
			BeanUtils.populate(product, map);
			
			//1.3 只是封装了一部分数据 
			product.setPid(UUIDUtils.getId());
			product.setPdate(new Date().toLocaleString());
			product.setPflag(1);//Constant.xxx
			
			//创建Category对象
			Category category = new Category();
			category.setCid(map.get("cid").toString());
			product.setCategory(category);
			
			//2.处理
			//调用service开始保存 返回值 boolean
			ProductService service = new ProductServiceImpl();
			boolean flag = service.save(product);
			
			//3.响应
			//写回浏览器
			response.getWriter().print(flag);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
