package cn.itcast.http.test;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

public class HttpClientTest {

	/**
	 * 测试get请求
	 */
	@Test
	public void testGet() throws Exception {
		// 创建客户端
		CloseableHttpClient client = HttpClients.createDefault();
		// 创建HttpGet对象
		HttpGet httpGet = new HttpGet("http://localhost:8080/pd_ssh_server_web");
		// 执行get请求
		CloseableHttpResponse response = client.execute(httpGet);
		// 获取响应实体
		HttpEntity entity = response.getEntity();
		// 获取响应状态
		System.out.println("响应状态: " + response.getStatusLine());
		if (entity != null) {
			// 获取响应内容的长度
			System.out.println("响应内容长度: " + entity.getContentLength());
			// 获取响应内容
			System.out.println("响应内容: " + EntityUtils.toString(entity));
		}
		// 关闭
		response.close();
	}

	/**
	 * HttpGet请求带参数
	 */
	@Test
	public void testGet02() throws Exception {
		// 创建client
		CloseableHttpClient client = HttpClients.createDefault();
		// 封装请求参数
		List<BasicNameValuePair> list = new ArrayList<>();
		list.add(new BasicNameValuePair("username", "cgx"));
		list.add(new BasicNameValuePair("password", "123456"));

		// 转化参数
		String params = EntityUtils.toString(new UrlEncodedFormEntity(list, Consts.UTF_8));
		System.out.println("请求参数: " + params);

		// 产生一个HttpGet请求对象
		HttpGet httpGet = new HttpGet("http://localhost:8080/pd_ssh_server_web/loginAction_login" + "?" + params);
		// 发送请求
		CloseableHttpResponse response = client.execute(httpGet);

		// 得到响应实体
		HttpEntity entity = response.getEntity();
		System.out.println("响应内容: " + EntityUtils.toString(entity));

		response.close();
	}

	/**
	 * HttpPost请求 第一种方式
	 */
	@Test
	public void testPost() throws Exception {
		CloseableHttpClient client = HttpClients.createDefault();
		List<BasicNameValuePair> paramsList = new ArrayList<>();
		paramsList.add(new BasicNameValuePair("username", "cgx"));
		paramsList.add(new BasicNameValuePair("password", "123456"));

		// 转化参数
		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramsList, Consts.UTF_8);
		HttpPost httpPost = new HttpPost("http://localhost:8080/pd_ssh_server_web/loginAction_login");
		httpPost.setEntity(entity);
		CloseableHttpResponse response = client.execute(httpPost);
		System.out.println(EntityUtils.toString(response.getEntity()));

		response.close();
	}

	/**
	 * HttpPost请求 第二种方式
	 */
	@Test
	public void testPost2() throws Exception {
		HttpClient client = new HttpClient();
		// 创建GET或POST请求方法
		PostMethod method = new PostMethod("http://localhost:8080/pd_ssh_server_web/loginAction_login");
		// 设置编码
		client.getParams().setContentCharset("UTF-8");
		// 设置请求消息头, 为表单提交方式
		method.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		// 设置参数
		// method.setParameter("username", "cgx");
		// method.setParameter("password", "123456");

		// 另一种设置参数的方式
		NameValuePair[] params = { new NameValuePair("username", "cgx"), new NameValuePair("password", "123456") };
		method.setRequestBody(params);

		client.executeMethod(method);

		System.out.println("响应状态: " + method.getStatusLine());
		System.out.println("响应内容: " + method.getResponseBodyAsString());
	}
	
	@Test
	public void test03() {
		HttpClient client = new HttpClient();
	}

}
