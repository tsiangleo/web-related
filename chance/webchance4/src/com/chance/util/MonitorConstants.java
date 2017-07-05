
package com.chance.util;

public class MonitorConstants {
	
	public static String 			ClientToken = "04f8dc380b7d551f3c42b5983766a49e";//"8922de65d2f5fe812e303a62f608f8dd";
	public static String 			ClientName = "1";
	public static String 			MainServerAddr = "http://222.214.218.32:9081";//"http://222.214.218.177:9081";//177外网地址，32内网地址
	public static String 			PrefixName = "/ChanceServer_M/rest/Monitor";
	public static String 			PostfixName = ".json?name="+ClientName+"&token="+ClientToken;
	
	public static final int	 		TOTAL_NUMBER_FROM_SERVER = 50;	//批量获取的时候，每次从服务器获取多少条记录
    public static final int    		PAGE_SIZE       = 5;		//列表每一页显示的记录条数
   
    public static String 			formatGoogleUrl = "http://maps.google.com/maps/api/geocode/xml?latlng=%1$f,%2$f&language=en&sensor=true";
	public static String 			formatBaiduUrl = "http://api.map.baidu.com/geocoder/v2/?ak=64c54f75e575c65a70d70f461cf019db&location=%1$f,%2$f&output=json&pois=0";
	public static String 			formatTencentUrl = "http://apis.map.qq.com/ws/geocoder/v1/?key=JEDBZ-T5G34-YDEUX-XPOR5-FOA7H-KLBVH&location=%1$f,%2$f";
	
	public static final int 		RESULT_STATUS_SUCCESS = 1;
	public static final int 		RESULT_STATUS_FAIL_PARAMERROR = 2;
	public static final int 		RESULT_STATUS_FAIL_CONTENTERROR = 3;
	public static final int 		RESULT_STATUS_FAIL_CONTENTNULL = 4;
	public static final String 		RESULT_DESC_SUCCESS = "success";
	public static final String 		RESULT_DESC_FAIL_PARAMERROR = "param error";
	public static final String 		RESULT_DESC_FAIL_CONTETNRROR = "content error";
	public static final String 		RESULT_DESC_FAIL_CONTENTNULL = "content null";	
	
	public static final int 		ERROR_INVALIDUSER = 701;
	public static final int 		ERROR_SUCCESS = 1;
	public static final int 		ERROR_FAIL = 2;
	public static final int 		STATUS_SUCCESS= 201;
	public static final String 		ERROR_REASON_SUCCESS = "success";
	public static final String 		ERROR_REASON_FALI = "fail";
	
	public static final int 		FLAG_CHANCE_USERNAME = 1;
	public static final int 		FLAG_CHANCE_USERID  =  2;

	//标识是否处理举报信息
	public static final int 		FLAG_CHECKRESULT_UNCHECKED  =  0;
	public static final int 		FLAG_CHECKRESULT_CHECKED  =  1;
	
	public static String 			OTHERINFO_LASTLOVE_TIME_FILED 						= "llt";
	public static String 			OTHERINFO_ATTENTION_LIMIT_NUM_FILED 				= "aln";
	public static String 			OTHERINFO_LOVE_LIMIT_NUM_FILED 						= "lln";
	public static String 			OTHERINFO_MARK_LIMIT_NUM_FILED 						= "mln";
	public static String 			OTHERINFO_FRIEND_LIMIT_NUM_FILED 					= "fln";
	public static String 			OTHERINFO_SLEEPLOVE_TIME_FILED 						= "slt";
	public static String 			OTHERINFO_CREATE_TEAM_LIMIT_NUM_FILED 				= "ctln";
	public static String 			OTHERINFO_ATTEND_TEAM_LIMIT_NUM_FILED 				= "atln";

	
	public static final String 		PUSH_CHANCE_KEY = "RunChancePush";
	public static final String 		PUSH_CHANCE_URL = MainServerAddr+PrefixName+"/ChancePush";
	
	
	public static final String 		GET_REPORTDIARY_KEY = "GetReportDiary";
	public static final String 		GET_REPORTDIARY_URL = MainServerAddr+PrefixName+"/GetReportDiary";
	public static final String 		DELETE_REPORTDIARY_KEY = "DeleteReportDiary";
	public static final String 		DELETE_REPORTDIARY_URL = MainServerAddr+PrefixName+"/DeleteReportDiary";
	
	public static final String 		GET_SINGLEDIARY_KEY = "GetSingleDiary";
	public static final String 		GET_SINGLEDIARY_URL = MainServerAddr+PrefixName+"/GetSingleDiary";
	public static final String 		DELETE_SINGLEDIARY_KEY = "DeleteSingleDiary";
	public static final String 		DELETE_SINGLEDIARY_URL = MainServerAddr+PrefixName+"/DeleteSingleDiary";
	
	public static final String		GET_REPORTCLIENT_KEY = "GetReportClient";
	public static final String		GET_REPORTCLIENT_URL = MainServerAddr+PrefixName+"/GetReportClient";
	public static final String		DELETE_REPORTCLIENT_KEY = "DeleteReportClient";
	public static final String		DELETE_REPORTCLIENT_URL = MainServerAddr+PrefixName+"/DeleteReportClient";
	
	public static final String		GET_FEEDBACK_KEY = "GetFeedback";
	public static final String		GET_FEEDBACK_URL = MainServerAddr+PrefixName+"/GetFeedback";
	public static final String		DELETE_FEEDBACK_KEY = "DeleteFeedback";
	public static final String		DELETE_FEEDBACK_URL = MainServerAddr+PrefixName+"/DeleteFeedback";
	
	public static final String		GET_REPORTBANGBANG_KEY = "GetReportBangBang";
	public static final String		GET_REPORTBANGBANG_URL = MainServerAddr+PrefixName+"/GetReportBangBang";
	public static final String		DELETE_REPORTBANGBANG_KEY = "DeleteReportBangBang";
	public static final String		DELETE_REPORTBANGBANG_URL = MainServerAddr+PrefixName+"/DeleteReportBangBang";
	
	public static final String		GET_BRIEFINFO_KEY = "GetUserBriefInfo";
	public static final String		GET_BRIEFINFO_URL = MainServerAddr+PrefixName+"/GetUserBriefInfo";
	
	public static final String		UPDATE_OTHERINFO_KEY = "UpdateOtherInfo";
	public static final String		UPDATE_OTHERINFO_URL = MainServerAddr+PrefixName+"/UpdateOtherInfo";
	public static final String		GET_OTHERINFO_KEY = "GetOtherInfo";
	public static final String		GET_OTHERINFO_URL = MainServerAddr+PrefixName+"/GetOtherInfo";
	
	public static final String		GET_LOCATION_KEY = "GetLocation";
	public static final String		GET_LOCATION_URL = MainServerAddr+PrefixName+"/GetUserLocation";
	
	public static final String		GET_DIARY_KEY = "GetDiary";
	public static final String		GET_DIARY_URL = MainServerAddr+PrefixName+"/GetDiary";
	
	public static final String		GET_SINGLEDIARYCOMMENT_KEY = "GetSingleDiaryComment";
	public static final String		GET_SINGLEDIARYCOMMENT_URL = MainServerAddr+PrefixName+"/GetSingleDiaryComment";
	public static final String		GET_DIARYCOMMENT_KEY = "GetDiaryComment";
	public static final String		GET_DIARYCOMMENT_URL = MainServerAddr+PrefixName+"/GetDiaryComment";
	public static final String		DELETE_SINGLEDIARYCOMMENT_KEY = "DeleteSingleDiaryComment";
	public static final String		DELETE_SINGLEDIARYCOMMENT_URL = MainServerAddr+PrefixName+"/DeleteSingleDiaryComment";
	
	public static final String		ACTIVE_USER_KEY = "ActiveUser";
	public static final String		ACTIVE_USER_URL = MainServerAddr+PrefixName+"/ActiveUser";
	
	public static final String		GET_TEAMINFOPENDING_KEY = "GetTeamInfoPending";
	public static final String		GET_TEAMINFOPENDING_URL = MainServerAddr+PrefixName+"/GetTeamInfoPending";
	public static final String		AUDIA_TEAMINFOPENDING_KEY = "AudiaTeamInfoPending";
	public static final String		AUDIA_TEAMINFOPENDING_URL = MainServerAddr+PrefixName+"/AudiaTeamInfoPending";
	
	public static final String		GET_SHOPINFOPENDING_KEY = "GetShopInfoPending";
	public static final String		GET_SHOPINFOPENDING_URL = MainServerAddr+PrefixName+"/GetShopInfoPending";
	public static final String		AUDIA_SHOPINFOPENDING_KEY = "AudiaShopInfoPending";
	public static final String		AUDIA_SHOPINFOPENDING_URL = MainServerAddr+PrefixName+"/AudiaShopInfoPending";
	 //Trade审核结果错误码定义
    public final static int    		ERROR_TRADEVERIFY_PASS                  = 1;                            //审核通过
    public final static int    		ERROR_TRADEVERIFY_NOPASS                = 2;                            //审核未通过

	
	public static final String 		GET_SINGLEBANGBANG_KEY = "GetSingleBangBang";
	public static final String 		GET_SINGLEBANGBANG_URL = MainServerAddr+PrefixName+"/GetSingleBangBang";
	public static final String 		DELETE_SINGLEBANGBANG_KEY = "DeleteSingleBangBang";
	public static final String 		DELETE_SINGLEBANGBANG_URL = MainServerAddr+PrefixName+"/DeleteSingleBangBang";
	public static final String		GET_BANGBANG_KEY = "GetBangBang";
	public static final String		GET_BANGBANG_URL = MainServerAddr+PrefixName+"/GetBangBang";
	
	
	
	
	public static final int 							RESULT_STATUS_FAIL_USERNAMEERROR = 5;
	public static final String 							RESULT_DESC_FAIL_USERNAMEERROR = "username error";
	
	
	
	public static final String 							MODELVIEW_ERROR_INVALID = "ErrorUser";
	public static final String 							MODELVIEW_GET_USERNUMBER = "GetUserNumber";
	public static final String 							MODELVIEW_ACTIVE_DAIRY = "ActiveDiary";
	//public static final String 							MODELVIEW_GET_REPORTDIARY = "GetReportDiary";
	//public static final String 							MODELVIEW_DELETE_REPORTDIARY = "DeleteReportDiary";
	//public static final String							MODELVIEW_GET_SINGLEDIARY = "GetSingleDiary";
	//public static final String 							MODELVIEW_DELETE_SINGLEDIARY = "DeleteSingleDiary";
	//public static final String							MODELVIEW_GET_DIARY = "GetDiary";
	public static final String 							MODELVIEW_DELETE_DIARY = "DeleteDiary";
	//public static final String 							MODELVIEW_GET_SINGLEDIARYCOMMENT = "GetSingleDiaryComment";
	//public static final String 							MODELVIEW_DELETE_SINGLEDIARYCOMMENT = "DeleteSingleDiaryComment";
	//public static final String 							MODELVIEW_GET_DIARYCOMMENT = "GetDiaryComment";
	public static final String 							MODELVIEW_DELETE_DIARYCOMMENT = "DeleteDiaryComment";
	//public static final String 							MODELVIEW_ACTIVE_USER = "ActiveUser";
	//public static final String 							MODELVIEW_GET_FEEDBACK = "GetFeedback";
	//public static final String 							MODELVIEW_DELETE_FEEDBACK = "DeleteFeedback";
	public static final String 							MODELVIEW_SET_VERSION = "SetVersion";
	public static final String 							MODELVIEW_GET_VERSION = "GetVersion";
	//public static final String 							MODELVIEW_GET_LOCATION = "GetLocation";
	//public static final String 							MODELVIEW_GET_BRIEFINFO = "GetUserBriefInfo";
	//public static final String							MODELVIEW_GET_REPORTCLIENT = "GetReportClient";
	//public static final String							MODELVIEW_DELETE_REPORTCLIENT = "DeleteReportClient";
	//public static final String 							MODELVIEW_GET_REPORTBANGBANG = "GetReportBangBang";
	//public static final String 							MODELVIEW_DELETE_REPORTBANGBANG = "DeleteReportBangBang";
	public static final String 							MODELVIEW_GET_DIARYWEBCONTENT = "GetDiaryWebContent";
	public static final String 							MODELVIEW_TIGGER_UPDATEGONCIF = "TiggerUpdateConfig";
	public static final String 							MODELVIEW_RUN_CHANCEPUSH = "RunChancePush";
	public static final String 							MODELVIEW_GET_ACTIVITYCLIENTMONEY = "GetActivityClientMoney";
	public static final String 							MODELVIEW_SET_ACTIVITYCLIENTMONEY = "SetActivityClientMoney";
	public static final String 							MODELVIEW_ACTIVE_ACTIVITY = "ActiveActivity";
	public static final String 							MODELVIEW_GET_TEAMINFOPENDING = "GetTeamInfoPending";
	public static final String 							MODELVIEW_AUDIA_TEAMINFOPENDING = "AudiaTeamInfoPending";
}
