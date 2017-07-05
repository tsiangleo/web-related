package com.chance.remote;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.ConnectException;
import java.net.URI;
import java.net.URISyntaxException;

import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.cookie.Cookie;
import org.apache.http.cookie.CookieOrigin;
import org.apache.http.cookie.CookieSpec;
import org.apache.http.cookie.CookieSpecFactory;
import org.apache.http.cookie.MalformedCookieException;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.impl.cookie.BrowserCompatSpec;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.dom4j.DocumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.chance.monitor.model.ResultInfo;
import com.chance.monitor.model.UserLocation;
import com.chance.swift.SwiftImpl;
import com.chance.util.MonitorConstants;
import com.chance.util.TypeTransfer;

public class RemoteSupport {
	private static final Logger log = LoggerFactory.getLogger(RemoteSupport.class);
	/**
	 * 根据url返回指定key对应的Json对象字符串
	 */
	public static String getJsonStringByKey(String url,String key) throws RemoteDataAccessException{
		Assert.hasText(url, "请求的url不能为空");
		Assert.hasText(key, "key不能为空");
		
		String jsonString = getJsonString(url);
		String s = "{\""+key+"\":"; 
		
		if(jsonString.contains(s)){ //包含有对应的key
			String after = jsonString.substring(jsonString.indexOf(s)+s.length(),jsonString.lastIndexOf("}"));
			return after;
		}else if(jsonString.contains("HTTP ERROR 404")){ //请求地址错误的时候，服务器返回的是HTML代码
			log.error("请求的URL地址有误！本次请求的URL地址为："+url);
			throw new RemoteDataAccessException("请求的URL地址有误！");	//InvalidURLException
		}else if(jsonString.contains("ErrorUser")){ //用户名或者token错误的时候，服务器返回{"ErrorUser":{"code":701,"reason":"invalid user"}}
			log.error("用户名或者token错误！ 服务器返回的json字符串为:"+jsonString);
			throw new RemoteDataAccessException("用户名或者token错误！");
		}else{
			log.error("请求的URL地址或者key有误！本次请求的URL地址为："+url+",服务器返回的Json字符串为："+jsonString+",要获取的Json对象对应的key为："+key);
			throw new RemoteDataAccessException("请求的URL地址或者key有误");
		}
	}
	
	/**
	 * 返回给定url对应的json字符串
	 * 
	 */
	private static  String getJsonString(String url) throws RemoteDataAccessException{
		//获取JSON字符串
		String jsonString = null;	
		HttpClient client = new HttpClient();
		GetMethod method = new GetMethod(url);	
		
		try {
			client.executeMethod(method);
			jsonString = method.getResponseBodyAsString();
		} catch (ConnectException e) {//发生网络异常
			log.error("网络连接问题：应用服务器拒绝连接，或者本地网络连接问题", e);
			throw new RemoteDataAccessException("网络连接问题：应用服务器拒绝连接，或者本地网络连接问题，请稍后再试！", e);
		} catch (HttpException e) {//发生致命的异常，可能是协议不对或者返回的内容有问题
			log.error("向服务器发送Http请求时出现异常", e);
			throw new RemoteDataAccessException("向服务器发送Http请求时出现异常", e);
		} catch (IOException e) {//发生网络异常
			log.error("从服务器获取数据时发生网络异常", e);
			throw new RemoteDataAccessException("从服务器获取数据时发生网络异常", e);
		}
		finally{
			method.releaseConnection();
		}
		return jsonString;
	}
	
	/**
	 * 向服务器发送删除、更新等非数据获取类型的请求后返回的ResultInfo对象，里面包含有操作结果。
	 * @param url
	 * @param key
	 * @return
	 * @throws RemoteDataAccessException
	 */
	public static ResultInfo getResultInfo(String url,String key) throws RemoteDataAccessException{
		Assert.hasText(url, "请求的url不能为空");
		Assert.hasText(key, "key不能为空");
		
		//获取JSON字符串
		String jsonString = getJsonString(url);	
		//获取JSON对象
		JSONObject rootJsonObject = JSONObject.fromObject(jsonString);
		//获取包含ResultInfo的JSON对象
		JSONObject resultInfoObject = (JSONObject)rootJsonObject.get(key);
		
		 ResultInfo resultInfo= new  ResultInfo();
		 int status = resultInfoObject.getInt("status");
		 String desc = resultInfoObject.getString("desc");
		 resultInfo.setStatus(status);
		 resultInfo.setDesc(desc);
		 resultInfo.setData(resultInfoObject);
		 return resultInfo;
	}
	

	/**
	 * 获取聊天图片URL,成功返回图片的路径，不成功返回null。
	 * @param tmpkey
	 * @return
	 */
	public static String  getChatPicURL(String tmpkey) {
		
		if(tmpkey == null || "".equals(tmpkey) || "null".equals(tmpkey)){//注意：diary类型为非文本的时候，JSON返回的url为字符串"null"
			return null;	
		}
	
		//获取类路径
		String dir = RemoteSupport.class.getResource("/").toString();
		
		int index = dir.indexOf("webapps");
		dir = dir.substring(0,index)+"webapps/webchance/data/chat/";
			
		URI uriDir = null;
		try {
			uriDir = new URI(dir);
		} catch (URISyntaxException e) {
			log.error("获取图片时出现异常，无法解析图片URI，对应的URL为："+tmpkey, e);
			return null;
		}
				
		//图片名
		String picName = tmpkey+".jpg";
		SwiftImpl si=SwiftImpl.getSwiftImpl();
		String destPath = si.swiftDownloadChatImgFile(tmpkey, uriDir, picName);

		if(destPath == null)
			return null;
		else	
			return "/webchance/data/chat/"+picName;
	}
	
	/**
	 * 获取头像图片URL,成功返回图片的路径，不成功返回null。
	 * @param tmpkey
	 * @return
	 */
	public static String  getAvatarPicURL(String tmpkey) {
		
		
		if(tmpkey == null || "".equals(tmpkey) || "null".equals(tmpkey)){//注意：diary类型为非文本的时候，JSON返回的url为字符串"null"
			return null;	
		}
	
		//获取类路径
		String dir = RemoteSupport.class.getResource("/").toString();
		
		int index = dir.indexOf("webapps");
		dir = dir.substring(0,index)+"webapps/webchance/data/avatar/";
		
		URI uriDir = null;
		try {
			uriDir = new URI(dir);
		} catch (URISyntaxException e) {
			log.error("获取图片时出现异常，无法解析图片URI，对应的URL为："+tmpkey, e);
			return null;
		}
				
		//图片名
		String picName = tmpkey+".jpg";
		SwiftImpl si=SwiftImpl.getSwiftImpl();
		String destPath = si.swiftDownloadAvatarImgFile(tmpkey, uriDir, picName);

		if(destPath == null)
			return null;
		else	
			return "/webchance/data/avatar/"+picName;
		
	}
	
	
	/**
	 * 获取日志图片URL,成功返回图片的路径，不成功返回null。
	 * @param userid
	 * @param diaryid
	 * @param tmpkey
	 * @return
	 */
	public static String getPicURL(int userid, int diaryid, String tmpkey) {
		
		if(tmpkey == null || "".equals(tmpkey) || "null".equals(tmpkey)){//注意：diary类型为非文本的时候，JSON返回的url为字符串"null"
			return null;	
		}
		
		String key = null;
		try{
			key = TypeTransfer.stringConvertToArrayList(tmpkey).get(0);
		} catch (StringIndexOutOfBoundsException e) {
			log.warn("StringIndexOutOfBoundsException异常 " + e );
			return null;
		}catch (Exception e) {
			log.error("获取图片时出现异常，无法解析图片URI，对应的URL为："+tmpkey, e);
			return null;
		}
		
		//获取类路径
		String dir = RemoteSupport.class.getResource("/").toString();
		int index = dir.indexOf("webapps");
		dir = dir.substring(0,index)+"webapps/webchance/data/diary/";
			
		URI uriDir = null;
		try {
			uriDir = new URI(dir);
		} catch (URISyntaxException e) {
			log.warn("获取图片时出现异常，无法解析图片URI，对应的URL为："+tmpkey, e);
			return null;
		}
				
		SwiftImpl si=SwiftImpl.getSwiftImpl();
		String destPath = si.swiftDownloadImgFile(key, uriDir, userid+""+diaryid+".jpg");
		if(destPath == null)
			return null;
		else	
			return "/webchance/data/diary/"+userid+""+diaryid+".jpg";
	}
	
	
	
	/**
	 * 获取商家log和证件等图片,成功返回图片的路径，不成功返回null。
	 * @return
	 */
	public static String  getTradeLCPicURL(String tmpkey) {
		
		
		if(tmpkey == null || "".equals(tmpkey) || "null".equals(tmpkey)){//注意：diary类型为非文本的时候，JSON返回的url为字符串"null"
			return null;	
		}
	
		//获取类路径
		String dir = RemoteSupport.class.getResource("/").toString();
		
		int index = dir.indexOf("webapps");
		dir = dir.substring(0,index)+"webapps/webchance/data/tradelc/";
		
		URI uriDir = null;
		try {
			uriDir = new URI(dir);
		} catch (URISyntaxException e) {
			log.error("获取图片时出现异常，无法解析图片URI，对应的URL为："+tmpkey, e);
			return null;
		}
				
		//图片名
		String picName = tmpkey+".jpg";
		SwiftImpl si=SwiftImpl.getSwiftImpl();
		String destPath = si.swiftDownloadTradeLCFile(tmpkey, uriDir, picName);

		if(destPath == null)
			return null;
		else	
			return "/webchance/data/tradelc/"+picName;
		
	}
	
	/**
	 * 获取商家商铺相关图片,成功返回图片的路径，不成功返回null。
	 * @return
	 */
	public static String  getTradeImgPicURL(String tmpkey) {
		
		
		if(tmpkey == null || "".equals(tmpkey) || "null".equals(tmpkey)){//注意：diary类型为非文本的时候，JSON返回的url为字符串"null"
			return null;	
		}
	
		//获取类路径
		String dir = RemoteSupport.class.getResource("/").toString();
		
		int index = dir.indexOf("webapps");
		dir = dir.substring(0,index)+"webapps/webchance/data/trade/";
		
		URI uriDir = null;
		try {
			uriDir = new URI(dir);
		} catch (URISyntaxException e) {
			log.error("获取图片时出现异常，无法解析图片URI，对应的URL为："+tmpkey, e);
			return null;
		}
				
		//图片名
		String picName = tmpkey+".jpg";
		SwiftImpl si=SwiftImpl.getSwiftImpl();
		String destPath = si.swiftDownloadTradeImgFile(tmpkey, uriDir, picName);

		if(destPath == null)
			return null;
		else	
			return "/webchance/data/trade/"+picName;
		
	}
	
	/**
	 * 根据经纬度返回具体位置
	 * @param la 纬度
	 * @param lo 经度
	 * @return
	 */
	public static String getAddress(double la,double lo){
		String location = null;
		UserLocation addre = null;
		
		if(la == 0 && lo == 0){
			return "未获取到地址位置";
		}
		
		
		addre = gpsToTencentLocation(la,lo);
		if(addre == null){
			addre = gpsToBaiduLocation(la,lo);
			if(addre == null){
				addre = gpsToGoogleLocation(la,lo);
				if(addre == null){
					location = "未获取到地址位置";
				}else {
					location = addre.toString();
				}
			}else{
				location = addre.toString();
			}
		}else{
			location = addre.toString();
		}
		
		log.info("address : " + location);
		return location; 
	}
	
	private static UserLocation gpsToTencentLocation(double lat,double lng){
		String url = String.format(MonitorConstants.formatTencentUrl, lat,lng);
		log.info("【formatTencentUrl】"+url);
		String result;
		UserLocation location = null;
		try {
			result = getAddressFromGeoServer(url);
			
			if(result != null && !result.equals("")){
				location = stringToTencentLocation(result);
			}
			

		} catch (IOException e) {
			log.warn(e.getMessage(), e);
		} catch (DocumentException e) {
			log.warn(e.getMessage(), e);
		}
		return location;
	}
	
	private static UserLocation gpsToBaiduLocation(double lat,double lng){
		String url = String.format(MonitorConstants.formatBaiduUrl, lat,lng);
		log.info("【formatBaiduUrl】"+url);
		String result;
		UserLocation location = null;
		try {
			result = getAddressFromGeoServer(url);
			
			if(result != null && !result.equals("")){
				location = stringToBaiduLocation(result);
			}
			

		} catch (IOException e) {
			log.warn(e.getMessage(), e);
		} catch (DocumentException e) {
			log.warn(e.getMessage(), e);
		}
		return location;
	}
	
	private static UserLocation gpsToGoogleLocation(double lat,double lng){
		String url = String.format(MonitorConstants.formatGoogleUrl, lat,lng);
		log.info("【formatGoogleUrl】"+url);
		String result;
		UserLocation location = null;
		
		try {
			result = getAddressFromGeoServer(url);
			location = stringToGoogleLocation(result);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.warn(e.getMessage(), e);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			log.warn(e.getMessage(), e);
		}
		return location;
	}
	
	private static String getAddressFromGeoServer(String url) throws ClientProtocolException, IOException{
		org.apache.http.client.HttpClient hc = createHttpClient();
		HttpGet httpGet = new HttpGet(url);
		HttpResponse httpresponse = hc.execute(httpGet);
		HttpEntity entity = httpresponse.getEntity();
		String body = EntityUtils.toString(entity);
		return body;
	}
	private static UserLocation stringToGoogleLocation(String result) throws DocumentException{
		ByteArrayInputStream bis = new ByteArrayInputStream(result.getBytes());
		UserLocation location = UserLocation.XmlToLocation(bis);
		return location;
	}
	private static UserLocation stringToBaiduLocation(String result) throws DocumentException{
		UserLocation location = UserLocation.JsonToLocation(result);
		return location;
	}
	
	private static UserLocation stringToTencentLocation(String result) throws DocumentException{
		UserLocation location = UserLocation.tencentJsonToLocation(result);
		return location;
	}
	
	private static org.apache.http.client.HttpClient createHttpClient(){

		CookieSpecFactory csf = new CookieSpecFactory() {
			public CookieSpec newInstance(HttpParams params) {
				return new BrowserCompatSpec() {

					@Override
					public void validate(Cookie arg0, CookieOrigin arg1)
							throws MalformedCookieException {
						// TODO Auto-generated method stub
					}   
					
				};
			}
		};
		HttpParams params = new BasicHttpParams();
		HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
		HttpProtocolParams.setContentCharset(params, HTTP.DEFAULT_CONTENT_CHARSET);
		HttpProtocolParams.setUseExpectContinue(params, true);

		SchemeRegistry schReg = new SchemeRegistry();
		schReg.register(new Scheme("http",PlainSocketFactory.getSocketFactory(),80));
		schReg.register(new Scheme("https",PlainSocketFactory.getSocketFactory(),433));
		ClientConnectionManager conMgr = new ThreadSafeClientConnManager(params,schReg);
		DefaultHttpClient dhc =new DefaultHttpClient(conMgr,params);
		dhc.getCookieSpecs().register("easy", csf);
		dhc.getParams().setParameter(ClientPNames.COOKIE_POLICY, "easy");
		return dhc;
	};

}
