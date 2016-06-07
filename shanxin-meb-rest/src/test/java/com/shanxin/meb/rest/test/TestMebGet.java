package com.shanxin.meb.rest.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.junit.Test;
import org.springframework.http.MediaType;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.shanxin.core.api.Response;
import com.shanxin.core.util.MyAlgorithmUtils;
import com.shanxin.core.util.MyStringUtils;
import com.shanxin.meb.service.entity.request.MebGetRequest;

public class TestMebGet {
	@SuppressWarnings("unchecked")
	@Test
	public void doTest() {
		try {
			String requstUrl = "http://localhost:8080/shanxin-meb-rest/rest/api";
			String contentType = MediaType.APPLICATION_JSON_VALUE;
			String accept = MediaType.APPLICATION_JSON_VALUE;

			MebGetRequest request = new MebGetRequest();
			request.setMethod(request.getLocalMothedName());
			request.setTimestamp(new Date());
			request.setOprtId(2);
			request.setOprtSecret("9a4fbddd6da2e05e56e5e5f76097a380");
			request.setOprtAccessToken("0bca08b7060c7bf03c69a5a12f14aebf77a493b19fc711bc62b51795082a2398");
			request.setMebId(1);

			// POST数据
			String postData = "";
			String sign = "";
			// json
			ObjectMapper om = new ObjectMapper();
			String tmpJsonPost = om.writeValueAsString(request);
			String tmpJsonSign = MyAlgorithmUtils.MD5(tmpJsonPost);
			// xml
			XmlMapper xm = new XmlMapper();
			String tmpXmlPost = xm.writeValueAsString(request);
			String tmpXmlSign = MyAlgorithmUtils.MD5(tmpXmlPost);
			// urlencoded
			String tmpUrlencodedPost = "";
			Map<String, Object> map = new HashMap<String, Object>();
			String tmpStr = om.writeValueAsString(request);
			map = om.readValue(tmpStr, map.getClass());
			Iterator<String> iterator = map.keySet().iterator();
			while (iterator.hasNext()) {
				String key = iterator.next();
				Object value = map.get(key);
				if (value != null)
					tmpUrlencodedPost += "&" + key + "=" + URLEncoder.encode(value.toString(), "utf-8");

			}
			if (tmpUrlencodedPost.startsWith("&"))
				tmpUrlencodedPost = tmpUrlencodedPost.substring(1);
			String tmpUrlencodeSign = MyAlgorithmUtils.MD5(tmpUrlencodedPost);

			if (contentType.equalsIgnoreCase(MediaType.APPLICATION_JSON_VALUE)) {
				postData = tmpJsonPost;
				sign = tmpJsonSign;
			} else if (contentType.equalsIgnoreCase(MediaType.APPLICATION_XML_VALUE)) {
				postData = tmpXmlPost;
				sign = tmpXmlSign;
			} else if (contentType.equalsIgnoreCase(MediaType.APPLICATION_FORM_URLENCODED_VALUE)) {
				postData = tmpUrlencodedPost;
				sign = tmpUrlencodeSign;
			}

			URL url = new URL(requstUrl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", contentType);
			connection.setRequestProperty("Accept", accept);
			connection.setRequestProperty("Sign", sign);
			connection.setConnectTimeout(30 * 1000);
			connection.setReadTimeout(800 * 1000);
			connection.setUseCaches(false);
			connection.setDoInput(true);

			// send request
			if (!MyStringUtils.isEmpty(postData)) {
				connection.setDoOutput(true);
				OutputStreamWriter os = new OutputStreamWriter(connection.getOutputStream(), Charset.forName("utf-8"));
				os.write(postData);
				os.flush();
				os.close();
			}

			// get response
			BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), Charset.forName("utf-8")));
			String line = null;
			StringBuffer rspSb = new StringBuffer();
			while ((line = br.readLine()) != null)
				rspSb.append(line);

			br.close();
			// connection.disconnect();调试时，因时间过长，出现以下错误
			// ********************
			// 六月 01, 2016 10:56:14 上午 org.apache.catalina.core.StandardWrapperValve invoke
			// 严重: Servlet.service() for servlet [spring-rest] in context with path [/shanxin-oprt-rest] threw exception
			// java.io.IOException: java.io.IOException: 您的主机中的软件中止了一个已建立的连接。
			// ********************
			connection.disconnect();

			Response<?> response = null;
			if (accept.equals(MediaType.APPLICATION_JSON_VALUE)) {
				TypeFactory tf = TypeFactory.defaultInstance();
				JavaType jt = tf.constructParametricType(Response.class, request.getApiResponseType());
				om.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
				response = om.readValue(rspSb.toString(), jt);
			} else if (accept.equals(MediaType.APPLICATION_XML_VALUE)) {
				TypeFactory tf = TypeFactory.defaultInstance();
				JavaType jt = tf.constructParametricType(Response.class, request.getApiResponseType());
				xm.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
				response = xm.readValue(rspSb.toString(), jt);
			}

			System.out.println(postData);
			System.out.println(response);
			System.out.println(rspSb.toString());
		} catch (Throwable ex) {
			ex.printStackTrace();
		}
	}
}
