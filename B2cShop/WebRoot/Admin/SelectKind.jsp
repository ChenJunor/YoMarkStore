<%@ page language="java" import="java.util.*,com.dzsw.service.*" pageEncoding="utf-8"%>
<%@ page import="com.dzsw.service.*" %>
<%@ page import="com.dzsw.dao.utils.Paging"%>

<%
	List<Map<String, String>> list = new ArrayList<Map<String, String>>(1000);
	Paging pages;
	if (request.getAttribute("flag") != "1") {
		KindService user = new KindService();
		pages = user.selectKindInfoByPage(new HashMap<String, String>(), 1);
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
            <h1 class="page-title">查看商品分类</h1>
            <div class="btn-toolbar">
                <div class="btn-group"></div>
                <%=list %>
            </div>
            <div class="well">
                <table class="table">
                    <thead>
                        <tr>
                            <th>序号</th>
                            <th>分类名称</th>
                            <th>父分类</th>
                            <th>分类描述</th>
                            <th>状态</th>
                            <th>修改</th>
                            <th>删除</th>
                            <!-- <th style="width: 26px;"></th> -->
                        </tr>
                    </thead>
                    <tbody>
                        <%
                    	for(Map<String, String> map : list) {
                    		String state = "";
                    		Map<String, String> umap = new HashMap<String, String>();
                    		
                    		String kindfath = "";
                    		if (map.get("KIND_FATH") != null && map.get("KIND_FATH").toString() != "") {
                    			kindfath = map.get("KIND_FATH").toString();
                    		}
                    		if (map.get("KIND_HIDE") != null) {
                    			state = map.get("KIND_HIDE").toString();
                    		}
                    		KindService kind = new KindService();
                    		if (kindfath != "" && kindfath != null) {
                    			umap = kind.selectKindInfoById(kindfath);
                    		}
                    		state = ("1".equals(state)) ? "显示" : "隐藏";
                    		kindfath = (umap.size() != 0 && !"0".equals(map.get("KIND_FATH"))) ? umap.get("KIND_NAME").toString() : "无";
                   	  %>
                        <tr>
                            <td><%=map.get("KIND_ID") %></td>
                            <td><%=map.get("KIND_NAME") %></td>
                            <td><%=kindfath %></td>
                            <td><%=map.get("KIND_REMARK") %></td>
                            <td><%=state %></td>
                            <td>
                                <a href="/B2cShop/servlet/KindServlet?action=updatekind&kind_id=<%=map.get("KIND_ID") %>"><i class="icon-pencil"></i></a>
                            </td>
                            <td>
                                <a href="/B2cShop/servlet/KindServlet?action=deletekind&kind_id=<%=map.get("KIND_ID") %>" role="button" data-toggle="modal"><i class="icon-remove"></i></a>
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
           	 	<li><a href="/B2cShop/servlet/KindServlet?action=selectkindbypage&page=<%=Integer.valueOf(pages.getPage())-1 %>">Prev</a></li>
            	<%
            		for(int i = 1; i <= pages.getPageCount(); i++) {
            	 %> 
                    <li><a href="/B2cShop/servlet/KindServlet?action=selectkindbypage&page=<%=i %>"><%=i %></a></li>
                 <%
                 }
                  %>
                    <li><a href="/B2cShop/servlet/KindServlet?action=selectkindbypage&page=<%=Integer.valueOf(pages.getPage())+1 %>">Next</a></li>
                </ul>
            </div>
        </div>
    </div>
</div>
<jsp:include  page="Public/ManageFooter.jsp"/>