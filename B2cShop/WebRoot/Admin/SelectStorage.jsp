<%@ page language="java" import="java.util.*, com.dzsw.service.*" pageEncoding="utf-8"%>
<%
	List<Map<String, String>> list;
	if (request.getAttribute("searchstoragelist") == null) {
		StorageService storage = new StorageService();
		list = storage.selectStorageInfoByPage(new HashMap<String, String>(), 1);
		list = (list.size() == 0) ? new ArrayList<Map<String, String>>(100) : list;
	} else {
		list = (List<Map<String, String>>)request.getAttribute("searchstoragelist");
	}
%>
<jsp:include  page="Public/ManageHeader.jsp"/>
<div class="container-fluid">
    <div class="row-fluid">
        <jsp:include  page="Public/ManageNavigation.jsp"/>
        <div class="span9">
            <h1 class="page-title">查看库存变化信息</h1>
            <div class="btn-toolbar">
                <div class="btn-group"></div>
            </div>
            <div class="well">
                <table class="table">
                    <thead>
                        <tr>
                            <th>序号</th>
                            <th>商品名</th>
                            <th>操作类型</th>
                            <th>操作数量</th>
                            <th>操作时间</th>
                            <th>修改</th>
                            <th>删除</th>
                        </tr>
                    </thead>
                    <tbody>
                    	<%
                    	for(Map<String, String> map : list) {
                    		String state = "";
                    		Map<String, String> smap = new HashMap<String, String>();
                    		String goods = "";
                    		if (map.get("GOODS_ID") != null) {
                    			goods = map.get("GOODS_ID").toString();
                    		}
                    		if (map.get("STOR_TYPE") != null) {
                    			state = map.get("STOR_TYPE").toString();
                    		}
                    		
                    		GoodsService stemp = new GoodsService();
                    		smap = stemp.selectGoodsInfoById(goods);
                    		goods = (smap.size() != 0) ? smap.get("GOODS_NAME") : "";	
                    		
                    		if ("1".equals(state)) {
                    			state = "审核通过";
                    		} else if ("2".equals(state)) {
                    			state = "未审核";
                    		} else if ("3".equals(state)){
                    			state = "审核未通过";
                    		}
                    		state = ("1".equals(state)) ? "卖出" : "买入";
                    		
                   	  %>
                        <tr>
                            <td><%=map.get("STOR_ID") %></td>
                            <td><%=goods %></td>
                            <td><%=state %></td>
                            <td><%=map.get("STOR_NUM") %></td>
                            <td><%=map.get("STOR_DATE") %></td>
                            <td>
                            <a href="UpdateStorage.jsp"><i class="icon-pencil"></i></a>
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
                    <li><a href="#">Prev</a></li>
                    <li><a href="#">1</a></li>
                    <li><a href="#">2</a></li>
                    <li><a href="#">3</a></li>
                    <li><a href="#">4</a></li>
                    <li><a href="#">Next</a></li>
                </ul>
            </div>
            <div class="modal small hide fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            	<div class="modal-header">
              		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
              		<h3 id="myModalLabel">确认</h3>
            	</div>
          	  	<div class="modal-body">
            		<p class="error-text"><i class="icon-warning-sign modal-icon"></i>确定要删除用户么？</p>
            	</div>
            	<div class="modal-footer">
              		<button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
              		<button class="btn btn-danger" data-dismiss="modal">删除</button>
            	</div>
          	</div>
        </div>
    </div>
</div>
<jsp:include  page="Public/ManageFooter.jsp"/>