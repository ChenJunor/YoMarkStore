<%@ page language="java" import="java.util.*,com.dzsw.service.*" pageEncoding="utf-8"%>
<%@ page import="com.dzsw.service.*" %>
<%@ page import="com.dzsw.dao.utils.Paging"%>

<%
	List<Map<String, String>> list = new ArrayList<Map<String, String>>(1000);
	Paging pages;
	if (request.getAttribute("flag") != "1") {
		LogService user = new LogService();
		pages = user.selectLogInfoByPage(new HashMap<String, String>(), 1);
		list = pages.selectListPage();
	} else {
		list = (List)request.getAttribute("result");
		pages = (Paging)request.getAttribute("pages");
	}
%>
<jsp:include  page="Public/ManageHeader.jsp"/>
<div class="container-fluid">
    <div class="row-fluid">
        <jsp:include  page="Public/ManageNavigation.jsp"/>
        <div class="span9">
            <h1 class="page-title">查看日志</h1>
            <div class="btn-toolbar">
                <div class="btn-group"></div>
            </div>
            <div class="well">
                <table class="table">
                    <thead>
                        <tr>
                            <th>序号</th>
                            <th>操作人</th>
                            <th>操作类型</th>
                            <th>操作详情</th>
                            <th>备注信息</th>
                            <th>修改</th>
                            <th>删除</th>
                            <!-- <th style="width: 26px;"></th> -->
                        </tr>
                    </thead>
                    <tbody>
                    <%
                    	for(Map<String, String> map : list) {
                    		String state = "";
                    		String adminname = "";
                    		Map<String, String> amap = new HashMap<String, String>();
                    		
                    		String adminid = "";
                    		if (map.get("ADMIN_ID") != null) {
                    			adminid = map.get("ADMIN_ID").toString();
                    		}
                    		if (map.get("LOG_TYPE") != null) {
                    			state = map.get("LOG_TYPE").toString();
                    		}
                    		
                    		AdminService admin = new AdminService();
                    		
                    		amap = admin.selectAdminInfoById(adminid);
                    		
                    		adminname = (amap.size() != 0) ? amap.get("ADMIN_NAME").toString() : "";
                    		
                    		if ("1".equals(state)) {
                    			state = "增";
                    		} else if ("2".equals(state)) {
                    			state = "删";
                    		} else if ("3".equals(state)){
                    			state = "改";
                    		} else if ("3".equals(state)) {
                    			state = "查";
                    		}
                    		
                   	  %>
                        <tr>
                            <td><%=map.get("LOG_ID") %></td>
                            <td><%=adminname %></td>
                            <td><%=map.get("LOG_TYPE") %></td>
                            <td><%=map.get("LOG_CONTENT") %></td>
                            <td><%=map.get("LOG_REMARK") %></td>
                            <td>
                                <a href="UpdateLog.jsp"><i class="icon-pencil"></i></a>
                            </td>
                            <td>
                                <a href="#myModal" role="button" data-toggle="modal"><i class="icon-remove"></i></a>
                            </td>
                        </tr>
                        <%
                    	}
                        %>
                    </tbody>
                </table>
            </div>
            <div class="pagination">
                <ul> 
           	 	<li><a href="/B2cShop/servlet/LogServlet?action=selectlogbypage&page=<%=Integer.valueOf(pages.getPage())-1 %>">Prev</a></li>
            	<%
            		for(int i = 1; i <= pages.getPageCount(); i++) {
            	 %> 
                    <li><a href="/B2cShop/servlet/LogServlet?action=selectlogbypage&page=<%=i %>"><%=i %></a></li>
                 <%
                 }
                  %>
                    <li><a href="/B2cShop/servlet/LogServlet?action=selectlogbypage&page=<%=Integer.valueOf(pages.getPage())+1 %>">Next</a></li>
                </ul>
            </div>
        </div>
    </div>
</div>
<jsp:include  page="Public/ManageFooter.jsp"/>