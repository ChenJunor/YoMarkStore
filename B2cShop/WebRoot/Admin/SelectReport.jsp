<%@ page language="java" import="java.util.*, com.dzsw.service.*" pageEncoding="utf-8"%>
<%@ page import="com.dzsw.service.*" %>
<%@ page import="com.dzsw.dao.utils.Paging"%>

<%
	List<Map<String, String>> list = new ArrayList<Map<String, String>>(1000);
	Paging pages;
	if (request.getAttribute("flag") != "1") {
		ReportService user = new ReportService();
		pages = user.selectReportInfoByPage(new HashMap<String, String>(), 1);
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
            <h1 class="page-title">查看举报信息</h1>
            <div class="btn-toolbar">
                <div class="btn-group"></div>
            </div>
            <div class="well">
                <table class="table">
                    <thead>
                        <tr>
                            <th>序号</th>
                            <th>举报人</th>
                            <th>举报商品</th>
                            <th>举报理由</th>
                            <th>消息状态</th>
                            <th>修改</th>
                            <th>删除</th>
                        </tr>
                    </thead>
                    <tbody>
                                        <%
                    	for(Map<String, String> map : list) {
                    		String user = "";
                    		Map<String, String> umap = new HashMap<String, String>();
                    		Map<String, String> smap = new HashMap<String, String>();
                    		String goods = "";
                    		String state = "";
                    		if (map.get("USER_ID") != null) {
                    			user = map.get("USER_ID").toString();
                    		}
                    		if (map.get("GOODS_ID") != null) {
                    			goods = map.get("GOODS_ID").toString();
                    		}
                    		if (map.get("REPO_STATE") != null) {
                    			state = map.get("REPO_STATE").toString();
                    		}

                    		UserService utemp = new UserService();
                    		umap = utemp.selectUserInfoById(user);

                    		user = (umap.size() != 0) ? umap.get("USER_NAME") : "";

                    		GoodsService stemp = new GoodsService();
                    		smap = stemp.selectGoodsInfoById(goods);
                    		goods = (smap.size() != 0) ? smap.get("GOODS_NAME") : "";
                    		
                    		if ("1".equals(state)) {
                    			state = "未审核";
                    		} else if ("2".equals(state)) {
                    			state = "审核通过";
                    		} else if ("0".equals(state)){
                    			state = "审核未通过";
                    		}
                   	  %>
                        <tr>
                            <td><%=map.get("REPO_ID") %></td>
                            <td><%=user %></td>
                            <td><%=goods %></td>
                            <td><%=map.get("REPO_REASON") %></td>
                            <td><%=state %></td>
                            <td>
                            <a href="/B2cShop/servlet/ReportServlet?action=updatereport&repo_id=<%=map.get("REPO_ID") %>"><i class="icon-pencil"></i></a>
                        </td>
                        <td>
                            <a href="/B2cShop/servlet/ReportServlet?action=deletereport&repo_id=<%=map.get("REPO_ID") %>" role="button" data-toggle="modal"><i class="icon-remove"></i></a>
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
           	 	<li><a href="/B2cShop/servlet/ReportServlet?action=selectreportbypage&page=<%=Integer.valueOf(pages.getPage())-1 %>">Prev</a></li>
            	<%
            		for(int i = 1; i <= pages.getPageCount(); i++) {
            	 %> 
                    <li><a href="/B2cShop/servlet/ReportServlet?action=selectreportbypage&page=<%=i %>"><%=i %></a></li>
                 <%
                 }
                  %>
                    <li><a href="/B2cShop/servlet/ReportServlet?action=selectreportbypage&page=<%=Integer.valueOf(pages.getPage())+1 %>">Next</a></li>
                </ul>
            </div>
        </div>
    </div>
</div>
<jsp:include  page="Public/ManageFooter.jsp"/>