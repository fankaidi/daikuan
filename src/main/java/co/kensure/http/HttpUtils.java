package co.kensure.http;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import co.kensure.exception.BusinessExceptionUtil;
import co.kensure.io.FileUtils;

@SuppressWarnings("deprecation")
public class HttpUtils {

	private static HttpClient httpClient = null;
	static {
		httpClient = new DefaultHttpClient();
	}

	public static HttpResponse get(String personalUrl) {
		// 获取响应文件，即html，采用get方法获取响应数据
		HttpGet getMethod = null;
		HttpResponse response = null;
		try {
			getMethod = new HttpGet(personalUrl);
			response = new BasicHttpResponse(HttpVersion.HTTP_1_1, HttpStatus.SC_OK, "OK");
			// 执行get方法
			response = httpClient.execute(getMethod);
		} catch (Exception e) {
			BusinessExceptionUtil.threwException(e);
		} finally {
			if (getMethod != null) {
				getMethod.releaseConnection();
			}
		}
		return response;
	}

	/**
	 * 获取内容
	 * 
	 * @param personalUrl
	 * @return
	 * @throws IOException
	 */
	public static String getBody(String personalUrl) {
		HttpResponse response = get(personalUrl);
		// 获取响应状态码
		int StatusCode = response.getStatusLine().getStatusCode();
		String html = null;
		if (StatusCode == 200) {
			try {
				html = EntityUtils.toString(response.getEntity(), "utf-8");
			} catch (Exception e) {
				BusinessExceptionUtil.threwException(e);
			}
		}
		return html;
	}

	/**
	 * 获取内容,变成二进制流
	 * 
	 * @param personalUrl
	 * @return
	 * @throws IOException
	 */
	public static byte[] getBytes(String personalUrl) {
		HttpResponse response = get(personalUrl);
		// 获取响应状态码
		int StatusCode = response.getStatusLine().getStatusCode();
		byte[] bytes = null;
		if (StatusCode == 200) {
			try {
				bytes = EntityUtils.toByteArray(response.getEntity());
			} catch (Exception e) {
				BusinessExceptionUtil.threwException(e);
			}
		}
		return bytes;
	}
	
//  测试jspoup
//	public static void main(String[] args) throws Exception {
//		String url = "";
//		HttpResponse response = get(url);
//		// 获取响应状态码
//		int StatusCode = response.getStatusLine().getStatusCode();
//		// 如果状态响应码为200，则获取html实体内容或者json文件
//		if (StatusCode == 200) {
//			String html = EntityUtils.toString(response.getEntity(), "utf-8");
//			Document doc = Jsoup.parse(html);
//
//			// 获取html标签中的内容
//			Elements elements = doc.select("ul[class=gl-warp clearfix]").select("li[class=gl-item]");
//			for (Element ele : elements) {
//				String bookID = ele.attr("data-sku");
//				String bookPrice = ele.select("div[class=p-price]").select("strong").select("i").text();
//				String bookName = ele.select("div[class=p-name]").select("em").text();
//				// 创建一个对象，这里可以看出，使用Model的优势，直接进行封装
//
//			}
//		}
//		EntityUtils.consume(response.getEntity());
//	}
	
	
	public static void main(String[] args) throws Exception {
		//下载一个图片的测试
		String picurl = "http://127.0.0.1/1.jpg";
		FileUtils.write(getBytes(picurl), "E://", "1.jpg");		
	}
}
