<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'left.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}

.STYLE1 {
	font-size: 12px;
	color: #FFFFFF;
}

.STYLE3 {
	font-size: 12px;
	color: #033d61;
}

a {
	text-decoration: none;
}
-->
</style>
<style type="text/css">
.menu_title SPAN {
	FONT-WEIGHT: bold;
	LEFT: 3px;
	COLOR: #ffffff;
	POSITION: relative;
	TOP: 2px
}

.menu_title2 SPAN {
	FONT-WEIGHT: bold;
	LEFT: 3px;
	COLOR: #FFCC00;
	POSITION: relative;
	TOP: 2px
}

a:hover {
	text-decoration: underline;
}
</style>
<script>
	function showsubmenu(sid) {
		whichEl = eval("submenu" + sid);
		imgmenu = eval("imgmenu" + sid);
		if (whichEl.style.display == "none") {
			eval("submenu" + sid + ".style.display=\"\";");
			imgmenu.background = "${pageContext.request.contextPath}/style/images/main_47.gif";
		} else {
			eval("submenu" + sid + ".style.display=\"none\";");
			imgmenu.background = "${pageContext.request.contextPath}/style/images/main_48.gif";
		}
	}
</script>
<script>
	var he = document.body.clientHeight - 105
	document.write("<div id=tt style=height:"+he+";overflow:hidden>")
</script>
<body>
	<table width="165" height="100%" border="0" cellpadding="0"
		cellspacing="0">
		<tr>
			<td height="28" background="${pageContext.request.contextPath}/style/images/main_40.gif"><table
					width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="19%">&nbsp;</td>
						<td width="81%" height="20"><span class="STYLE1">管理菜单</span>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td valign="top"><table width="151" border="0" align="center"
					cellpadding="0" cellspacing="0">
					<tr>
						<td><table width="100%" border="0" cellspacing="0"
								cellpadding="0">
								<tr>
									<td height="23" background="${pageContext.request.contextPath}/style/images/main_47.gif"
										id="imgmenu1" class="menu_title"
										onMouseOver="this.className='menu_title2';"
										onClick="showsubmenu(1)"
										onMouseOut="this.className='menu_title';" style="cursor:hand"><table
											width="100%" border="0" cellspacing="0" cellpadding="0">
											<tr>
												<td width="18%">&nbsp;</td>
												<td width="82%" class="STYLE1">反馈管理</td>
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td background="${pageContext.request.contextPath}/style/images/main_51.gif" id="submenu1">
										<div class="sec_menu">
											<table width="100%" border="0" cellspacing="0"
												cellpadding="0">
												<tr>
													<td><table width="90%" border="0" align="center"
															cellpadding="0" cellspacing="0">
															<tr>
																<td width="16%" height="25"><div align="center">
																		<img res="${pageContext.request.contextPath}/style/images/left.gif" width="10"
																			height="10" />
																	</div>
																</td>
																<td width="84%" height="23"><table width="95%"
																		border="0" cellspacing="0" cellpadding="0">
																		<tr>
																			<td height="20" style="cursor:hand"
																				onmouseover="this.style.borderStyle='solid';this.style.borderWidth='1';borderColor='#7bc4d3'; "
																				onmouseout="this.style.borderStyle='none';">
																				<a href="reportDiary/index" target="Iqq"> 
																					<span style="width:50" class="STYLE3">日志举报</span>
																				</a>
																			</td>
																		</tr>
																	</table>
																</td>
															</tr>
															<tr>
																<td height="23"><div align="center">
																		<img res="${pageContext.request.contextPath}/style/images/left.gif" width="10"
																			height="10" />
																	</div>
																</td>
																<td height="23"><table width="95%" border="0"
																		cellspacing="0" cellpadding="0">
																		<tr>
																			<td height="20" style="cursor:hand"
																				onmouseover="this.style.borderStyle='solid';this.style.borderWidth='1';borderColor='#7bc4d3'; "
																				onmouseout="this.style.borderStyle='none'"><a
																					href="reportUser/index" target="Iqq">
																					<span class="STYLE3">用户举报</span>
																				</a>
																			</td>
																		</tr>
																	</table>
																</td>
															</tr>
															<tr>
																<td height="23"><div align="center">
																		<img res="${pageContext.request.contextPath}/style/images/left.gif" width="10"
																			height="10" />
																	</div>
																</td>
																<td height="23"><table width="95%" border="0"
																		cellspacing="0" cellpadding="0">
																		<tr>
																			<td height="20" style="cursor:hand"
																				onmouseover="this.style.borderStyle='solid';this.style.borderWidth='1';borderColor='#7bc4d3'; "
																				onmouseout="this.style.borderStyle='none'"><a
																					href="feedBack/index" target="Iqq">
																					<span class="STYLE3">用户反馈</span>
																				</a>
																			</td>
																		</tr>
																	</table>
																</td>
															</tr>
															<tr>
																<td height="23"><div align="center">
																		<img res="${pageContext.request.contextPath}/style/images/left.gif" width="10"
																			height="10" />
																	</div>
																</td>
																<td height="23"><table width="95%" border="0"
																		cellspacing="0" cellpadding="0">
																		<tr>
																			<td height="20" style="cursor:hand"
																				onmouseover="this.style.borderStyle='solid';this.style.borderWidth='1';borderColor='#7bc4d3'; "
																				onmouseout="this.style.borderStyle='none'"><a
																					href="reportBangBang/index" target="Iqq">
																					<span class="STYLE3">帮帮举报</span>
																				</a>
																			</td>
																		</tr>
																	</table>
																</td>
															</tr>
														</table>
													</td>
												</tr>
												<tr>
													<td height="5"><img
														res="${pageContext.request.contextPath}/style/images/main_52.gif" width="151"
														height="5" />
													</td>
												</tr>
											</table>
										</div>
									</td>
								</tr>

							</table>
						</td>
					</tr>
					<tr>
					<td><table width="100%" border="0" cellspacing="0"
								cellpadding="0">
								<tr>
									<td height="23" background="${pageContext.request.contextPath}/style/images/main_47.gif"
										id="imgmenu1" class="menu_title"
										onMouseOver="this.className='menu_title2';"
										onClick="showsubmenu(1)"
										onMouseOut="this.className='menu_title';" style="cursor:hand"><table
											width="100%" border="0" cellspacing="0" cellpadding="0">
											<tr>
												<td width="18%">&nbsp;</td>
												<td width="82%" class="STYLE1">内容管理</td>
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td background="${pageContext.request.contextPath}/style/images/main_51.gif" id="submenu1">
										<div class="sec_menu">
											<table width="100%" border="0" cellspacing="0"
												cellpadding="0">
												<tr>
													<td><table width="90%" border="0" align="center"
															cellpadding="0" cellspacing="0">
															<tr>
																<td width="16%" height="25"><div align="center">
																		<img res="${pageContext.request.contextPath}/style/images/left.gif" width="10"
																			height="10" />
																	</div>
																</td>
																<td width="84%" height="23"><table width="95%"
																		border="0" cellspacing="0" cellpadding="0">
																		<tr>
																			<td height="20" style="cursor:hand"
																				onmouseover="this.style.borderStyle='solid';this.style.borderWidth='1';borderColor='#7bc4d3'; "
																				onmouseout="this.style.borderStyle='none';"><a
																					href="diary/index" target="Iqq">
																					<span style="width:50" class="STYLE3">日志管理</span>
																				</a>
																			</td>
																		</tr>
																	</table>
																</td>
															</tr>
															<tr>
																<td height="23"><div align="center">
																		<img res="${pageContext.request.contextPath}/style/images/left.gif" width="10"
																			height="10" />
																	</div>
																</td>
																<td height="23"><table width="95%" border="0"
																		cellspacing="0" cellpadding="0">
																		<tr>
																			<td height="20" style="cursor:hand"
																				onmouseover="this.style.borderStyle='solid';this.style.borderWidth='1';borderColor='#7bc4d3'; "
																				onmouseout="this.style.borderStyle='none'"><a
																					href="diaryComment/index" target="Iqq">
																					<span class="STYLE3">日志评论管理</span>
																				</a>
																			</td>
																		</tr>
																	</table>
																</td>
															</tr>
															<tr>
																<td height="23"><div align="center">
																		<img res="${pageContext.request.contextPath}/style/images/left.gif" width="10"
																			height="10" />
																	</div>
																</td>
																<td height="23"><table width="95%" border="0"
																		cellspacing="0" cellpadding="0">
																		<tr>
																			<td height="20" style="cursor:hand"
																				onmouseover="this.style.borderStyle='solid';this.style.borderWidth='1';borderColor='#7bc4d3'; "
																				onmouseout="this.style.borderStyle='none'"><a
																					href="user/index" target="Iqq">
																					<span class="STYLE3">用户信息管理</span>
																				</a>
																			</td>
																		</tr>
																	</table>
																</td>
															</tr>
															<tr>
																<td height="23"><div align="center">
																		<img res="${pageContext.request.contextPath}/style/images/left.gif" width="10"
																			height="10" />
																	</div>
																</td>
																<td height="23"><table width="95%" border="0"
																		cellspacing="0" cellpadding="0">
																		<tr>
																			<td height="20" style="cursor:hand"
																				onmouseover="this.style.borderStyle='solid';this.style.borderWidth='1';borderColor='#7bc4d3'; "
																				onmouseout="this.style.borderStyle='none'"><a
																					href="teamInfoPending/index" target="Iqq">
																					<span class="STYLE3">小组审核管理</span>
																				</a>
																			</td>
																		</tr>
																	</table>
																</td>
															</tr>
															
															
															<tr>
																<td height="23"><div align="center">
																		<img res="${pageContext.request.contextPath}/style/images/left.gif" width="10"
																			height="10" />
																	</div>
																</td>
																<td height="23"><table width="95%" border="0"
																		cellspacing="0" cellpadding="0">
																		<tr>
																			<td height="20" style="cursor:hand"
																				onmouseover="this.style.borderStyle='solid';this.style.borderWidth='1';borderColor='#7bc4d3'; "
																				onmouseout="this.style.borderStyle='none'"><a
																					href="shopInfoPending/index" target="Iqq">
																					<span class="STYLE3">店铺审核管理</span>
																				</a>
																			</td>
																		</tr>
																	</table>
																</td>
															</tr>
															
															
														</table>
													</td>
												</tr>
												<tr>
													<td height="5"><img
														res="${pageContext.request.contextPath}/style/images/main_52.gif" width="151"
														height="5" />
													</td>
												</tr>
											</table>
										</div>
									</td>
								</tr>

							</table>
						</td>
					</tr>
					<tr>
						<td><table width="100%" border="0" cellspacing="0"
								cellpadding="0">
								<tr>
									<td height="23" background="${pageContext.request.contextPath}/style/images/main_47.gif"
										id="imgmenu2" class="menu_title"
										onmouseover="this.className='menu_title2';"
										onclick="showsubmenu(2)"
										onmouseout="this.className='menu_title';" style="cursor:hand"><table
											width="100%" border="0" cellspacing="0" cellpadding="0">
											<tr>
												<td width="18%">&nbsp;</td>
												<td width="82%" class="STYLE1">系统管理</td>
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td background="${pageContext.request.contextPath}/style/images/main_51.gif" id="submenu2"><div
											class="sec_menu">
											<table width="100%" border="0" cellspacing="0"
												cellpadding="0">
												<tr>
													<td><table width="90%" border="0" align="center"
															cellpadding="0" cellspacing="0">
															
															<c:if test="${sessionScope.admin.level == 1}">
															<tr>
																<td width="16%" height="25"><div align="center">
																		<img res="${pageContext.request.contextPath}/style/images/left.gif" width="10"
																			height="10" />
																	</div>
																</td>
																<td width="84%" height="23"><table width="95%"
																		border="0" cellspacing="0" cellpadding="0">
																		<tr>
																			<td height="20" style="cursor:hand"
																				onmouseover="this.style.borderStyle='solid';this.style.borderWidth='1';borderColor='#7bc4d3'; "
																				onmouseout="this.style.borderStyle='none'"><a
																				href="admin/index" target="Iqq"><span
																					class="STYLE3">管理员设置</span>
																			</a>
																			</td>
																		</tr>
																	</table>
																</td>
															</tr>
															</c:if>
															
															<tr>
																<td height="23"><div align="center">
																		<img res="${pageContext.request.contextPath}/style/images/left.gif" width="10"
																			height="10" />
																	</div>
																</td>
																<td height="23"><table width="95%" border="0"
																		cellspacing="0" cellpadding="0">
																		<tr>
																			<td height="20" style="cursor:hand"
																				onmouseover="this.style.borderStyle='solid';this.style.borderWidth='1';borderColor='#7bc4d3'; "
																				onmouseout="this.style.borderStyle='none'"><a
																				href="admin/changePwdUI" target="Iqq"><span
																					class="STYLE3">修改个人登陆密码</span>
																			</a>
																			</td>
																		</tr>
																	</table>
																</td>
															</tr>
															<tr>
																<td height="23"><div align="center">
																		<img res="${pageContext.request.contextPath}/style/images/left.gif" width="10"
																			height="10" />
																	</div>
																</td>
																<td height="23"><table width="95%" border="0"
																		cellspacing="0" cellpadding="0">
																		<tr>
																			<td height="20" style="cursor:hand"
																				onmouseover="this.style.borderStyle='solid';this.style.borderWidth='1';borderColor='#7bc4d3'; "
																				onmouseout="this.style.borderStyle='none'"><span
																				class="STYLE3">undefined</span>
																			</td>
																		</tr>
																	</table>
																</td>
															</tr>
															<tr>
																<td height="23"><div align="center">
																		<img res="${pageContext.request.contextPath}/style/images/left.gif" width="10"
																			height="10" />
																	</div>
																</td>
																<td height="23"><table width="95%" border="0"
																		cellspacing="0" cellpadding="0">
																		<tr>
																			<td height="20" style="cursor:hand"
																				onmouseover="this.style.borderStyle='solid';this.style.borderWidth='1';borderColor='#7bc4d3'; "
																				onmouseout="this.style.borderStyle='none'"><span
																				class="STYLE3">undefined</span>
																			</td>
																		</tr>
																	</table>
																</td>
															</tr>
														</table>
													</td>
												</tr>
												<tr>
													<td height="5"><img
														res="${pageContext.request.contextPath}/style/images/main_52.gif" width="151"
														height="5" />
													</td>
												</tr>
											</table>
										</div>
									</td>
								</tr>
							</table></td>
					</tr>
					<tr>
						<td><table width="100%" border="0" cellspacing="0"
								cellpadding="0">
								<tr>
									<td height="23" background="${pageContext.request.contextPath}/style/images/main_47.gif"
										id="imgmenu3" class="menu_title"
										onmouseover="this.className='menu_title2';"
										onclick="showsubmenu(3)"
										onmouseout="this.className='menu_title';" style="cursor:hand"><table
											width="100%" border="0" cellspacing="0" cellpadding="0">
											<tr>
												<td width="18%">&nbsp;</td>
												<td width="82%" class="STYLE1">数据监测</td>
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td background="${pageContext.request.contextPath}/style/images/main_51.gif" id="submenu3"><div
											class="sec_menu">
											<table width="100%" border="0" cellspacing="0"
												cellpadding="0">
												<tr>
													<td><table width="90%" border="0" align="center"
															cellpadding="0" cellspacing="0">
															<tr>
																<td width="16%" height="25"><div align="center">
																		<img res="${pageContext.request.contextPath}/style/images/left.gif" width="10"
																			height="10" />
																	</div>
																</td>
																<td width="84%" height="23"><table width="95%"
																		border="0" cellspacing="0" cellpadding="0">
																		<tr>
																			<td height="20" style="cursor:hand"
																				onmouseover="this.style.borderStyle='solid';this.style.borderWidth='1';borderColor='#7bc4d3'; "
																				onmouseout="this.style.borderStyle='none'"><a
																					href="ChartOnlineAction" target="Iqq">
																					<span class="STYLE3">在线用户</span>
																				</a>
																			</td>
																		</tr>
																	</table>
																</td>
															</tr>
															<tr>
																<td height="23"><div align="center">
																		<img res="${pageContext.request.contextPath}/style/images/left.gif" width="10"
																			height="10" />
																	</div>
																</td>
																<td height="23"><table width="95%" border="0"
																		cellspacing="0" cellpadding="0">
																		<tr>
																			<td height="20" style="cursor:hand"
																				onmouseover="this.style.borderStyle='solid';this.style.borderWidth='1';borderColor='#7bc4d3'; "
																				onmouseout="this.style.borderStyle='none'"><a
																				href="ChartActiveAction" target="Iqq"><span
																					class="STYLE3">活跃用户</span>
																			</a>
																			</td>
																		</tr>
																	</table>
																</td>
															</tr>
															<tr>
																<td height="23"><div align="center">
																		<img res="${pageContext.request.contextPath}/style/images/left.gif" width="10"
																			height="10" />
																	</div>
																</td>
																<td height="23"><table width="95%" border="0"
																		cellspacing="0" cellpadding="0">
																		<tr>
																			<td height="20" style="cursor:hand"
																				onmouseover="this.style.borderStyle='solid';this.style.borderWidth='1';borderColor='#7bc4d3'; "
																				onmouseout="this.style.borderStyle='none'"><a
																				href="ChartRegisterAction" target="Iqq"><span
																					class="STYLE3">注册用户</span>
																			</a>
																			</td>
																		</tr>
																	</table>
																</td>
															</tr>
															<tr>
																<td height="23"><div align="center">
																		<img res="${pageContext.request.contextPath}/style/images/left.gif" width="10"
																			height="10" />
																	</div>
																</td>
																<td height="23"><table width="95%" border="0"
																		cellspacing="0" cellpadding="0">
																		<tr>
																			<td height="20" style="cursor:hand"
																				onmouseover="this.style.borderStyle='solid';this.style.borderWidth='1';borderColor='#7bc4d3'; "
																				onmouseout="this.style.borderStyle='none'"><a
																				href="ChartSystemAction" target="Iqq"><span
																					class="STYLE3">系统信息</span>
																			</a>
																			</td>
																		</tr>
																	</table>
																</td>
															</tr>
														</table>
													</td>
												</tr>
												<tr>
													<td height="5"><img
														res="${pageContext.request.contextPath}/style/images/main_52.gif" width="151"
														height="5" />
													</td>
												</tr>
											</table>
										</div>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td height="18" background="${pageContext.request.contextPath}/style/images/main_58.gif"><table
					width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td height="18" valign="bottom"><div align="center"
								class="STYLE3">版本：2015 V3.0</div>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</body>
</html>
