/**
 * chance.com Inc.
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.chance.monitor.model;

import java.io.File;
/**
 * 
 * @author lyb
 * @version $Id: WebsocketConstants.java, v 0.1 Feb 22, 2013 4:03:03 PM lyb Exp $
 */
public class WebsocketConstants
{
	
	public static final int    CHANCE_TEAM_ID                         = 100150;
	public static final int    CHANCE_PLAY_ID                         = 100151;
	
	/*
	 * MsgID
	 * */
	public static final int    MSGID_RECV_SELFUID_RSP                 = 0x01000a01;                                      //接收自身uid


	public static final int    CHANCE_PRIVATE_START_ID                = 100000;
	public static final int    CHANCE_PRIVATE_END_ID                  = 101000;

	
	public static final int    MSGID_LBS_REQ                          = 0x00000001;
	public static final int    MSGID_LBS_RSP                          = 0x01000001;
	public static final int    MSGID_NBS_REQ                          = 0x00000002;
	public static final int    MSGID_NBS_RSP                          = 0x01000002;
	public static final int    MSGID_ADDFRIEND_REQ                    = 0x00000003;
	public static final int    MSGID_ADDFRIEND_RSP                    = 0x01000003;
	public static final int    MSGID_NOTICE_ADDFRIEND_REQ             = 0x00000004;
	public static final int    MSGID_NOTICE_ADDFRIEND_RSP             = 0x01000004;
	public static final int    MSGID_RMFRIEND_REQ                     = 0x00000005;
	public static final int    MSGID_RMFRIEND_RSP                     = 0x01000005;
	public static final int    MSGID_ADDATTENTION_REQ                 = 0x00000006;
	public static final int    MSGID_ADDATTENTION_RSP                 = 0x01000006;
	public static final int    MSGID_RMATTENTIION_REQ                 = 0x00000007;
	public static final int    MSGID_RMATTENTION_RSP                  = 0x01000007;
	public static final int    MSGID_ADDLOVE_REQ                      = 0x00000008;
	public static final int    MSGID_ADDLOVE_RSP                      = 0x01000008;
	public static final int    MSGID_RMLOVE_REQ                       = 0x00000009;
	public static final int    MSGID_RMLOVE_RSP                       = 0x01000009;
	public static final int    MSGID_ADDMARK_REQ                      = 0x0000000a;
	public static final int    MSGID_ADDMARK_RSP                      = 0x0100000a;
	public static final int    MSGID_RMMARK_REQ                       = 0x0000000b;
	public static final int    MSGID_RMMARK_RSP                       = 0x0100000b;
	public static final int    MSGID_CHANGELOVE_REQ                   = 0x0000000c;
	public static final int    MSGID_CHANCELOVE_RSP                   = 0x0100000c;

	//用户名字备注添加（如果存在此记录，则进行更新）与删除
	public static final int    MSGID_USER_NAMEREMARK_ADD_OR_UPDATE_REQ = 0x0000000d;                                      //增加（或更新）记录请求
	public static final int    MSGID_USER_NAMEREMARK_ADD_OR_UPDATE_RSP = 0x0100000d;
	public static final int    MSGID_USER_NAMEREMARK_RM_REQ            = 0x0000000e;                                      //删除记录请求
	public static final int    MSGID_USER_NAMEREMARK_RM_RSP            = 0x0100000e;
	//强制用户登出
	public static final int    MSGID_FORCE_LOGOUT_RSP                 = 0x01000a01;
	//服务器主动发送给用户的token消息 
	public static final int    MSGID_LOGINTOKEN_RSP                   = 0x01000b01;                                      //发给用户的登录token

	//download,upload, update table
	public static final int    MSGID_DOWNLOAD_TABLE_REQ               = 0x000000f1;
	public static final int    MSGID_DOWNLOAD_TABLE_RSP               = 0x010000f1;
	public static final int    MSGID_UPLOAD_TABLE_REQ                 = 0x000000f2;
	public static final int    MSGID_UPLOAD_TABLE_RSP                 = 0x010000f2;
	public static final int    MSGID_UPDATE_TABLE_REQ                 = 0x000000f3;
	public static final int    MSGID_UPDATE_TABLE_RSP                 = 0x010000f3;

	//download table with CID
	public static final int    MSGID_DOWNLOAD_UNITABRECWITHCID_REQ    = 0x000000f4;                                      //通过CID下载单条记录请求
	public static final int    MSGID_DOWNLOAD_UNITABRECWITHCID_RSP    = 0x010000f4;                                      //通过CID下载单条记录回复

	//日志表相关操作
	public static final int    MSGID_DOWNLOAD_DIARY_REQ               = 0x00000101;
	public static final int    MSGID_DOWNLOAD_DIARY_RSP               = 0x01000101;
	public static final int    MSGID_DOWNLOAD_DIARYCOMMENT_REQ        = 0x00000102;
	public static final int    MSGID_DOWNLOAD_DIARYCOMMENT_RSP        = 0x01000102;
	public static final int    MSGID_UPLOAD_DIARY_REQ                 = 0x00000103;
	public static final int    MSGID_UPLOAD_DIARY_RSP                 = 0x01000103;
	public static final int    MSGID_UPLOAD_DIARYCOMMENT_REQ          = 0x00000104;
	public static final int    MSGID_UPLOAD_DIARYCOMMENT_REQ_1        = 0x00100104;                                      //新版本的upload diaryComment请求，增加了若干个字段
	public static final int    MSGID_UPLOAD_DIARYCOMMENT_RSP          = 0x01000104;
	public static final int    MSGID_DELETE_DIARY_REQ                 = 0x00000105;
	public static final int    MSGID_DELETE_DIARY_RSP                 = 0x01000105;
	public static final int    MSGID_PRAISE_DIARY_REQ                 = 0x00000106;
	public static final int    MSGID_PRAISE_DIARY_RSP                 = 0x01000106;
	public static final int    MSGID_PUSHDIARY_AUTHORITY_SET_REQ      = 0x00000107;                                      //日志推送权限设置请求
	public static final int    MSGID_PUSHDIARY_AUTHORITY_SET_RSP      = 0x01000107;
	public static final int    MSGID_TRANSFER_DIARY_REQ               = 0x00000108;
	public static final int    MSGID_TRNASFER_DIARY_RSP               = 0x01000108;
	public static final int    MSGID_DIARY_REPLYORAT_NOTICE_RSP       = 0x01000109;                                      //日志回复或者@通知

	//目前没有删除评论功能
	//public static final int    MSGID_DELETE_DIARYCOMMENT_REQ   = 0x00000106;
	//public static final int    MSGID_DELETE_DIARYCOMMENT_RSP   = 0x01000106;

	//聊天相关操作
	public static final int    MSGID_CHATMESSAGE_REQ                  = 0x00000201;
	public static final int    MSGID_CHATMESSAGE_RSP                  = 0x01000201;
	public static final int    MSGID_GROUPCHATMSG_REQ                 = 0x00000202;
	public static final int    MSGID_GROUPCHATMSG_RSP                 = 0x01000202;

	//新鲜事相关操作
	public static final int    MSGID_DOWNLOAD_FRESHNEWS_REQ           = 0x00000301;
	public static final int    MSGID_DOWNLOAD_FRESHNEWS_RSP           = 0x01000301;
	public static final int    MSGID_FRESHNEWS_AUTHORITY_SET_REQ      = 0x00000302;                                      //新鲜事权限设置
	public static final int    MSGID_FRESHNEWS_AUTHORITY_SET_RSP      = 0x01000302;
	public static final int    MSGID_FRESHNEWS_DIARY_DOWNLOAD_REQ     = 0x00000303;
	public static final int    MSGID_FRESHNEWS_DIARY_DOWNLOAD_RSP     = 0x01000303;
	
	public final static int    FRESHNEWS_THROW_PERSON                = 0x00000007;                                      //甩了某个人

	//心跳相关操作
	public static final int    MSGID_BEACON_REQ                       = 0x00000401;

	//主页访问操作
	public static final int    MSGID_VISIT_MAINPAGE_REQ               = 0x00000501;
	public static final int    MSGID_VISIT_MAINPAGE_RSP               = 0x01000501;

	//查找好友操作
	public static final int    MSGID_FINDFRIEND_REQ                   = 0x00000601;
	public static final int    MSGID_FINDFRIEND_RSP                   = 0x01000601;

	//获取个人头像操作
	public static final int    MSGID_GET_USERAVATER_REQ               = 0x00000701;
	public static final int    MSGID_GET_USERAVATER_RSP               = 0x01000701;
	public static final int    MSGID_MODIFY_PASSWD_REQ                = 0x00000702;
	public static final int    MSGID_MODIFY_PASSWD_RSP                = 0x01000702;

	//意见反馈，投诉，拉黑，获取更新信息操作
	public static final int    MSGID_FEEDBACK_REQ                     = 0x00000801;                                      //意见反馈
	public static final int    MSGID_FEEDBACK_RSP                     = 0x01000801;
	public static final int    MSGID_DIARYREPORT_REQ                  = 0x00000802;                                      //日志投诉
	public static final int    MSGID_DIARYREPORT_RSP                  = 0x01000802;
	public static final int    MSGID_ADDBLACKLIST_REQ                 = 0x00000803;                                      //加黑名单
	public static final int    MSGID_ADDBLACKLIST_RSP                 = 0x01000803;
	public static final int    MSGID_RMBLACKLIST_REQ                  = 0x00000804;                                      //删除黑名单
	public static final int    MSGID_RMBLACKLIST_RSP                  = 0x01000804;
	public static final int    MSGID_GETCHANCEVERINFO_REQ             = 0x00000805;                                      //请求chance更新信息
	public static final int    MSGID_GETCHANCEVERINFO_RSP             = 0x01000805;


	//离线消息操作
	public static final int    MSGID_OFFLINEMSG_REQ                   = 0x00000901;                                      //离线消息请求
	public static final int    MSGID_OFFLINEMSG_RSP                   = 0x01000901;

	
	//普通消息ack请求
    public static final int    MSGID_MSGACK_REQ                        = 0x00000c01;                                      //普通消息ack(发给服务器)
    //普通消息ack回复
    public static final int    MSGID_MSGACK_RSP                        = 0x01000c01;                                      //普通消息ack(发给客户端)
	
    //bangbang消息操作
    public static final int    MSGID_BANGBANG_REQ                      = 0x00000d01;
    public static final int    MSGID_BANGBANG_RSP                      = 0x01000d01;
    public static final int    MSGID_BANGBANG_AUTHORITY_SET_REQ        = 0x00000d02;                                      //帮帮应用权限设置
    public static final int    MSGID_BANGBANG_AUTHORITY_SET_RSP        = 0x01000d02;
    public static final int    MSGID_BANGBANG_REPORT_REQ               = 0x00000d03;                                      //帮帮消息举报
    public static final int    MSGID_BANGBANG_REPORT_RSP               = 0x01000d03;
    
    //照片墙消息操作
    public static final int    MSGID_DOWNPHOTOWALL_REQ                 = 0x00000e01;                                      //下载照片墙请求
    public static final int    MSGID_DOWNPHOTOWALL_RSP                 = 0x01000e01;
    public static final int    MSGID_SUBSCRIBE_PHOTOWALL_REQ           = 0x00000e02;                                      //订阅请求
    public static final int    MSGID_SUBSCRIBE_PHOTOWALL_RSP           = 0x01000e02;
    public static final int    MSGID_UNSUBSCRIBE_PHOTOWALL_REQ         = 0x00000e03;                                      //取消订阅请求
    public static final int    MSGID_UNSUBSCRIBE_PHOTOWALL_RSP         = 0x01000e03;
    public static final int    MSGID_QUERY_SELFSUBSCRIBE_PHOTOWALL_REQ = 0x00000e04;                                      //查询我订阅的人
    public static final int    MSGID_QUERY_SELFSUBSCRIBE_PHOTOWALL_RSP = 0X01000e04;
    public static final int    MSGID_QUERY_SUBSCRIBETOME_PHOTOWALL_REQ = 0x00000e05;                                      //查询订阅我的人
    public static final int    MSGID_QUERY_SUBSCRIBETOME_PHOTOWALL_RSP = 0x01000e05;
    public static final int    MSGID_QUERY_PHOTOWALL_OWNER_INFO_REQ    = 0x00000e06;                                      //查询照片墙所有者的个人信息
    public static final int    MSGID_QUERY_PHOTOWALL_OWNER_INFO_RSP    = 0x01000e06;
    public static final int    MSGID_BYSUBSCRIBE_PHOTOWALL_RSP         = 0x01000e07;                                      //被订阅回复
    public static final int    MSGID_BYUNSUBSCRIBE_PHOTOWALL_RSP       = 0x01000e08;                                      //被取消订阅回复
    
    //MeetMemory消息操作
    public static final int    MSGID_MEETMEMORY_ADD_REQ                = 0x00000f01;                                      //添加Memory请求
    public static final int    MSGID_MEETMEMORY_ADD_RSP                = 0x01000f01;
    public static final int    MSGID_MEETMEMORY_DEL_REQ                = 0x00000f02;                                      //删除Memory请求
    public static final int    MSGID_MEETMEMORY_DEL_RSP                = 0x01000f02;
    public static final int    MSGID_MEETMEMORY_DOWN_REQ               = 0x00000f03;                                      //下载Memory请求
    public static final int    MSGID_MEETMEMORY_DOWN_RSP               = 0x01000f03;
    
    public static final int    MSGID_REPORTUSER_REQ                    = 0x00000806;                                      //举报用户
    
	//SoundLoc消息操作
    public static final int    MSGID_SOUNDLOC_OPER_REQ                 = 0x00001001;                                      //声音定位操作请求
    public static final int    MSGID_SOUNDLOC_OPER_RSP                 = 0x01001001;                                      //声音定位操作回复
    
    //赞日志消息
    public static final int    MSGID_DIARY_PRAISE_NOTICE_RSP           = 0x0100010a;                                      //日志赞通知
    
    //Throw Person消息操作
    public static final int    MSGID_THROW_PERSON_OPER_REQ             = 0x00001002;                                      //甩人操作请求
    public static final int    MSGID_THROW_PERSON_OPER_RSP             = 0x01001002;                                      //甩人操作回复
    public static final int    MSGID_THROWPERSON_NOTICE_RSP            = 0x01001003;                                      //被甩通知

    //日志分享消息
    public static final int    MSGID_DIARY_TRANSFER_NOTICE_RSP         = 0x0100010b;
    
    //检查关注暗恋标记对象操作
    public static final int    MSGID_CHECK_ATTLOVEMARK_REQ             = 0x00001004;                                      //检查自己是否是对方的关注暗恋标记对象
    public static final int    MSGID_CHECK_ATTLOVEMARK_RSP             = 0x01001004;
    
    //日志with通知
    public static final int    MSGID_DIARY_WITH_NOTICE_RSP             = 0x0100010c;                                      //日志with通知
    
    //日志收藏操作
    public static final int    MSGID_COLLECT_DIARY_ADD_REQ             = 0x0000010e;                                      //日志收藏添加请求
    public static final int    MSGID_COLLECT_DIARY_ADD_RSP             = 0x0100010e;                                      //日志收藏添加回复
    public static final int    MSGID_COLLECT_DIARY_DEL_REQ             = 0x0000010f;                                      //日志收藏删除请求
    public static final int    MSGID_COLLECT_DIARY_DEL_RSP             = 0x0100010f;                                      //日志收藏删除回复
    public static final int    MSGID_DOWN_COLLECTDIARY_REQ             = 0x0000011a;                                      //下载收藏日志请求
    public static final int    MSGID_DOWN_COLLECTDIARY_RSP             = 0x0100011a;                                      //下载收藏日志回复
    
    
    //日志@的通知
    public static final int    MSGID_DIARY_AT_NOTICE_RSP               = 0x0100010d;                                      //日志@通知
    
    //chance推送消息
    public static final int    MSGID_RECOMM_DIARY_NOTICE_RSP           = 0x0100011b;                                      //推荐日志通知(ChanceTeam新发日志)
    
    //在意暗恋隐私设置
    public static final int    MSGID_ATTLOVE_READAUTH_UPLOAD_REQ       = 0x00001005;                                      //上传权限请求
    public static final int    MSGID_ATTLOVE_READAUTH_UPLOAD_RSP       = 0x01001005;
    public static final int    MSGID_ATTLOVE_READAUTH_DOWN_REQ         = 0x00001006;                                      //下载权限请求
    public static final int    MSGID_ATTLOVE_READAUTH_DOWN_RSP         = 0x01001006;
    
    
    //邀请人员加群
    public static final int    MSGID_APPLYFOR_JOINGROUP_REQ            = 0x00000203;                                      //申请入群(包括被群成员(非群主)邀请，或者自己主动申请；都需要群主进行验证)
    public static final int    MSGID_APPLYFOR_JOINGROUP_RSP            = 0x01000203;
    
	/*
	 * 功能性常量 
	 **/
	//查询好友条件标记
	public static final int    FLAG_FINDFRIEND_BYCID                  = 0;                                               //根据CID来查询好友(查询UserInfo表)
	public static final int    FLAG_FINDFRIEND_BYEMAIL                = 1;                                               //根据email来查询好友(联合User表来查询UserInfo表)
	public static final int    FLAG_FINDFRIEND_BYLOGINNAME             = 2;                                               //根据chance名来查询好友(查询UserInfo表)

	//NBS一些常量
	public static final int    NBS_SUC_DOWNLOAD                       = 1;                                               //正常情况
	public static final int    NBS_ERROR_OFFLINE                      = 2;                                               //对方不在线
	public static final int    NBS_ERROR_CHANCE_USER                  = 3;                                               //不是chance用户
	public static final int    NBS_ERROR_MAC_INCONSISTENT             = 4;                                               //请求的mac与发下来的brief信息里面的mac不一致　

	//NBS请求发的指定mac类型
	public static final int    NBS_WIFIDIRECT_MAC_TYPE                = 1;                                               //wifidirect mac类型
	public static final int    NBS_BLUETOOTH_MAC_TYPE                 = 2;                                               //bluetooth mac类型

	//关注暗恋标志常量
	public static final int    FLAG_MARK_TYPE                         = 0;
	public static final int    FLAG_ATTENTION_TYPE                    = 1;
	public static final int    FLAG_LOVE_TYPE                         = 2;

	//区分邻居或无关系，关注暗恋，好友关系常量
	public static final int    FLAG_FRIEND_RELATION                   = 0;                                               //好友关系
	public static final int    FLAG_ATTORLOVE_RELATION                = 1;                                               //关注暗恋关系
	public static final int    FLAG_NEIGHBOR_RELATION                 = 2;                                               //邻居关系(弱陌生人)
	public static final int    FLAG_NO_RELATION                       = 3;                                               //无关系（强陌生人）

	//0表示not allow, 1表示not read(pushDiary,freshnews权限相关)
	public static final int    FLAG_NOTALLOW_SPECIAL                  = 0;                                               //表示不给指定人看
	public static final int    FLAG_NOTREAD_SPECIAL                   = 1;                                               //表示不看指定人的

	//邻居加入或离开标志常量
	public static final int    FLAG_NEIGHBOR_JOIN_TYPE                = 0;
	public static final int    FLAG_NEIGHBOR_LEAVE_TYPE               = 1;

    //日志属性标志常量
    public static final int    FLAG_DIARY_PROPERTY_PRIVATE             = 0;                                               //表示日志为私有的
    public static final int    FLAG_DIARY_PROPERTY_PUBLIC              = 1;                                               //表示日志为公开的
    public static final int    FLAG_DIARY_PROPERTY_ALL                 = 2;                                               //表示私有与公开的日志
	
	//日志评论标志常量
	public static final int    FLAG_DIARYCOMMENT_TEXT                 = 0;                                               //文字评论
	public static final int    FLAG_DIARYCOMMENT_AUDIO                = 1;                                               //语音评论

	//用户信息标志常量
	public static final int    FLAG_USERINFO_SEX_ALL                  = 2;                                               //男与女
	public static final int    FLAG_USERINFO_SEX_MALE                 = 1;                                               //男
	public static final int    FLAG_USERINFO_SEX_FELMALE              = 0;                                               //女

	//新鲜事操作类型定义
	public final static int    FRESHNEWS_ADD_ATTENTION                = 0x00000001;                                      //加关注
	public final static int    FRESHNEWS_ADD_LOVE                     = 0x00000002;                                      //加暗恋
	public final static int    FRESHNEWS_ADD_MARK                     = 0x00000003;                                      //加标记
	public final static int    FRESHNEWS_ADD_FRIEND                   = 0x00000004;                                      //加好友
	public final static int    FRESHNEWS_ADD_PRAISE                   = 0x00000005;                                      //加赞
	public final static int    FRESHNEWS_TRANSFER_DIARY               = 0x00000006;										 //日志转载


	//离线表中消息类型定义(这是服务器数据库的离线表中的类型定义)
	public final static byte   CHAT_OFFLINEMSG_TYPE                   = 0x01;                                            //聊天离线消息类型
	public final static byte   ADD_FRIEND_OFFLINEMSG_TYPE             = 0x02;                                            //加好友离线消息类型
	public final static byte   ADD_ATTENTION_OFFLINEMSG_TYPE          = 0x03;                                            //加关注离线消息类型
	public final static byte   ADD_LOVE_OFFLINEMSG_TYPE               = 0x04;                                            //加暗恋离线消息类型
	public final static byte   ADD_MARK_OFFLINEMSG_TYPE               = 0x05;                                            //加标记离线消息类型
	public final static byte   AGREE_ADD_FRIEND_OFFLINEMSG_TYPE       = 0x06;                                            //同意加好友离线消息类型
	public final static byte   DONOT_AGREE_ADD_FRIEND_OFFLINEMSG_TYPE = 0x07;                                            //不同意加好友离线消息类型
	public final static byte   NO_OFFLINEMSG_TYPE                     = 0x08;                                            //没有离线消息类型

	//用户详细信息可见权限常量定义
	public final static int    FLAG_USERINFO_ALLOW_ALL                = 0;                                               //所有人可见
	public final static int    FLAG_USERINFO_ALLOW_FRIEND             = 1;                                               //仅好友可见
	public final static int    FLAG_USERINFO_ALLOW_ATT                = 2;                                               //仅关注可见
	public final static int    FLAG_USERINFO_ALLOW_SELF               = 3;                                               //仅自己可见

	//用户位置查看权限定义
	public final static int    FLAG_USERINFO_SHAREMAPADDR_LOW         = 0;                                               //低级
	public final static int    FLAG_USERINFO_SHAREMAPADDR_MEDIUM      = 1;                                               //中级
	public final static int    FLAG_USERINFO_SHAREMAPADDR_HIGH        = 2;                                               //高级

	
    //照片墙请求常量定义
    public final static int    FLAG_PHOTOWALL_RECOMMEND                = 1;                                               //系统推荐
    public final static int    FLAG_PHOTOWALL_PUSH                     = 2;                                               //自己push列表
    public final static int    FLAG_PHOTOWALL_OTHER                    = 3;                                               //他人的列表

    //照片墙排序算法常量定义
    public final static int    TYPE_PHOTOWALL_SORT_DEFAULT             = 1;                                               //默认的排序算法

    //MeetMemory常量定义
    public final static int    FLAG_MEETMEMORY_NORMAL                  = 0;                                               //普通记录
    public final static int    FLAG_MEETMEMORY_MEMORY                  = 1;                                               //memory记录

    //MeetMemory下载常量定义
    public final static int    FLAG_MEETMEMORY_DOWN_SELF_TO_ALL        = 0;                                               //下自己所有的
    public final static int    FLAG_MEETMEMORY_DOWN_SELF_TO_TARGET     = 1;                                               //下自己针对某个人的
    public final static int    FLAG_MEETMEMORY_DOWN_TARGET_TO_SELF     = 2;                                               //下对方针对自己的
    public final static int    FLAG_MEETMEMORY_DOWN_SELF_TO_ALL_LATEST = 3;                                               //下自己针对所有人最新的一条

    //MeetMemory下载errorCode定义
    public final static int    ERRORCODE_NORMAL                        = 0;                                               //正常情况
    public final static int    ERRORCODE_NOPRIVILEGE                   = 1;                                               //没有权限
    
    //甩人具体类型定义
    public final static int    FLAG_THROW_TO_PERSON                    = 1;                                               //甩一个人
    public final static int    FLAG_DOWNLOAD_THROWPERSONS              = 2;                                               //下载我甩的人或者甩我的人
    
    //在意查看具体类型的权限区分
    public final static int    FLAG_FRESHNEWS_READAUTH_TYPE            = 1;                                               //动态读权限类型
    public final static int    FLAG_DIARY_READAUTH_TYPE                = 2;                                               //行博读权限类型
    
	/*
	 * TableName
	 * */
	public static final String TABLE_FRIEND                           = "C_Friend";
	public static final String TABLE_ATTLOVEMARK                      = "C_AttLoveMark";
	public static final String TABLE_USERINFO                         = "C_UserInfo";
	public static final String TABLE_BRIEF                            = "C_Brief";
	public static final String TABLE_NOTPUSHANDNOTREAD                = "C_NotPushAndNotRead";
	public static final String TABLE_BLACKLIST                        = "C_Blacklist";
	public static final String TABLE_USERNAMEREMARK                   = "C_User_NameRemark";
	public static final String TABLE_MEET                              = "C_Meet";



	//server_url
	public static final String LOAD_BALANCE_SERV_URL                  = "http://192.168.1.71:8080/ChanceServer_L/get.ip";
	public static final String SERV_BASE_URL                          = ":8080/ChanceServer_C/websocket.do?data=";
	public static final String SERV_BASE_RADIX                        = "10";

	public static final String FIRST_REGISTER_URL                     = "http://192.168.1.72:8080/Login/random";
	public static final String SECOND_REGISTER_URL                    = "http://192.168.1.72:8080/Login/LoginServlet";

	//网络状态
	public final static int    MOBILE_NETWORK_WIFI                    = 0;
	public final static int    MOBILE_NETWORK_3G_2G                   = 1;
	public final static int    MOBILE_NETWORK_UNCONNECTED             = 2;
	public final static int    MOBILE_NETWORK_UNKNOWN                 = -1;

	//网络通信常量
	public static final int    HTTP_CONNECTION_TIMEOUT                = 10000;                                           //http协议连接超时为10秒钟
	public static final int    HTTP_SO_TIMEOUT                        = 10000;                                           //http协议数据读取超时为10秒钟

	public static final int    BEACON_MSG_INTERVAL                    = 5 * 60 * 1000;                                   //心跳消息发送间隔5分钟
	public static final int    DOWN_DATA_TIMEOUT                      = 30000;                                           //设置从服务器拖数据超时为30秒
	public static final int    NET_OK                                 = 1;                                               //正常情况
	public static final int    NET_DEFAULT_ERROR                      = -1;                                              //默认错误(比如内部数据出错)
	public static final int    NET_DOWN_DATA_TIMEOUT_ERROR            = -2;                                              //从服务器下载数据超时错误
	public static final int    NET_UNLOGIN_ERROR                      = -3;                                              //未登录错误
	public static final int    NET_CONNECTION_DISCONNECT              = -4;                                              //与服务器连接断开

	//登录错误信息常量
	public static final int    LOGIN_DEFAULT                          = -1;                                              //登录默认值
	public static final int    LOGIN_SUC                              = 0;                                               //登录成功
	public static final int    ERROR_UID                              = 1;                                               //用户名出错
	public static final int    ERROR_PSW                              = 2;                                               //用户密码出错
	public static final int    ERROR_BLUETOOTH_MAC                    = 3;                                               //用户蓝牙MAC地址出错
	public static final int    ERROR_COUNT                            = 4;                                               //Count出错
	public static final int    ERROR_NO_DATA                          = 5;                                               //后面data无数据

	//注册错误信息常量
	public static final int    REGISTER_DEFAULT                       = -1;                                              //注册默认值
	public static final int    REGISTER_SUC                           = 0;                                               //注册成功
	public static final int    REGISTER_EMAIL_HAS_USED                = 1;                                               //邮箱已被使用
	public static final int    REGISTER_BLUEMAC_HAS_USED              = 2;                                               //蓝牙地址已被使用
	public static final int    REGISTER_PASSWD_TOO_SHORT              = 3;                                               //密码少于六位

	//修改密码错误信息常量
	public static final int    MODIFY_PASSWD_DEFAULT                  = -1;                                              //修改密码默认错误（可能是网络不通问题）
	public static final int    MODIFY_PASSWD_SUC                      = 0;                                               //修改密码成功
	public static final int    MODIFY_PASSWD_EOLDPSSWD                = 1;                                               //提供的旧密码不对
	public static final int    MODIFY_PASSWD_NEWPASSWD_TOOSHORT       = 2;                                               //提供的新密码少于六位

	//不推送，不看日志和新鲜事常量
	public static final int    NOTPUSH_NOTREAD_FLAG_NOTPUSH			  = 0;												 //不推送
	public static final int    NOTPUSH_NOTREAD_FLAG_NOTREAD			  = 1;												 //不看
	public static final int    NOTPUSH_NOTREAD_TYPE_DIARY			  = 0;												 //日志
	public static final int    NOTPUSH_NOTREAD_TYPE_FRESHNEWS		  = 1;												 //新鲜事

	//好友，关注、暗恋、标记操作错误常量 
	public static final int    ERROR_FRIATTLOVEMARK_NUM_LIMIT_EXCEEDED = 1;                                               //人数超出限制
	public static final int    ERROR_FRIATTLOVEMARK_RECORD_NOTEXISTS   = 2;                                               //删除好友，关注，暗恋，标记时，服务器不存在此记录，即客户端与服务器表不同步，需同步表
	public static final int    ERROR_FRIATTLOVEMARK_RECORD_EXISTS      = 3;                                               //加好友，关注，暗恋，标记时，服务器已存在此记录，即客户端与服务器表不同步，需同步表
	public static final int    ERROR_FRIATTLOVEMARK_REDIS_OPER_FAILURE = 4;                                               //服务器redis操作失败(此错误上层UI可以忽略)
	public static final int    ERROR_CHANGELOVE_TIME_TOOSHORT          = 5;                                               //这个只有在更改暗恋对象(先删除暗恋，再新加另一个暗恋时)有用，处于冷却期，不能再加
	
  
    //声音定位具体阶段类型定义
    public final static int    FLAG_SOUNDLOC_START                     = 1;                                               //主动方准备发送声音
    public final static int    FLAG_SOUNDLOC_STARTREAY                 = 2;                                               //参与方准备好接受声音
    public final static int    FLAG_SOUNDLOC_STOP                      = 3;                                               //主动方停止发送声音
    public final static int    FLAG_SOUNDLOC_STOPRESULT                = 4;                                               //参与方对收到的声音处理并返回主动方
	
	
    
    //强制退出类型定义
    public final static int    FLAG_FORCE_LOGOUT_CHANGE_DEVICE         = 1;                                               //换设备登录类型
    public final static int    FLAG_FORCE_LOGOUT_MODIFY_PWD            = 2;                                               //修改密码类型
    public final static int    FLAG_FORCE_LOGOUT_FROZEN_ACCOUNT        = 3;                                               //账号被封类型
    
    
    //登录类型常量 
    public static final int    LOGIN_UID_TYPE                          = 1;                                               //以uid登录
    public static final int    LOGIN_EMAIL_TYPE                        = 2;                                               //以email登录
    public static final int    LOGIN_LOGINNAME_TYPE                    = 3;                                               //以loginName登录

	public static final int MSGID_SERVERINFO = 0x10000001;
	public static final int MSGID_SERVERLOADINFO = 0x10000002;

	public static final int MSGID_TEMPDATA = 0x0000000;
	
	
	//单独线程执行的判断常量
	public static final int INSERT_PUSHDIAEY = 1;  //将日志id插入所有粉丝和好友的pushdiary表
	public static final int REMOVE_PUSHDIAEY = 2;  //将日志id从所有粉丝和好友的pushdiary表中删除
	public static final int INSERT_OPEN_PUSHDIAEY = 3;  //将日志id插入所有粉丝和好友的open pushdiary表
	public static final int REMOVE_OPEN_PUSHDIAEY = 4;  //将日志id从所有粉丝和好友的open pushdiary表中删除
 	public static final int CHANCE_ACTIVE_PUSH_INSERT = 5;  //当chance，或者chance内部号发送日志时，给每个用户推送或者插入
	
	public static final int    MAX_SEND_MSG_COUNT 						= 2000000000;

	
	/**
	 * chance app id
	 */
	public static final int     CHANCE_INTERNAL_APPID                             = 0x00000001;
	
	
    //新鲜事划分类型定义
    public final static int    FRESHNEWS_BY_REALWORLD                  = 1;                                               //基于真实世界
    public final static int    FRESHNEWS_BY_VIRTUALWORLD               = 2;                                               //基于虚拟世界
    
    
    //日志类型标志常量 
    public static final int    FLAG_DIARY_PICTURE_TYPE                 = 0;                                               //表示普通的图片日志
    public static final int    FLAG_DIARY_TEXT_TYPE                    = 1;                                               //表示纯文本（说说）日志
    public static final int    FLAG_DIARY_LINK_TYPE                    = 2;                                               //表示链接日志
    public static final int    FLAG_DIARY_DECALS_TYPE                  = 3;                                               //表示贴纸日志
    
  //举报用户，举报类型定义
    public final static int    FLAG_REPORTUSER_HARASS                  = 1;                                               //骚扰信息
    public final static int    FLAG_REPORTUSER_AD                      = 2;                                               //广告信息
    public final static int    FLAG_REPORTUSER_SEXY                    = 3;                                               //色情信息
}