<%@ page language="java" import="java.util.*, com.dzsw.service.*" pageEncoding="utf-8"%>
<%@ page import="com.dzsw.service.*" %>
<%@ page import="com.dzsw.dao.utils.Paging"%>

<%
	List<Map<String, String>> list = new ArrayList<Map<String, String>>(1000);
	Paging pages;
	if (request.getAttribute("flag") != "1") {
		GoodsService user = new GoodsService();
		pages = user.selectGoodsInfoByPage(new HashMap<String, String>(), 1);
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
            <h1 class="page-title">查看商品</h1>
            <div class="btn-toolbar">
                <div class="btn-group"></div>
                <%=list %>
            </div>
            <div class="well">
                <table class="table">
                    <thead>
                        <tr>
                            <th>序号</th>
                            <th>商品</th>
                            <th>分类</th>
                            <th>所属店铺</th>
                            <th>查看</th>
                            <th>修改</th>
                            <th>删除</th>
                            <!-- <th style="width: 26px;"></th> -->
                        </tr>
                    </thead>
                    <tbody>
                       <%
                    	for(Map<String, String> map : list) {
                    		String kind = "";
                    		Map<String, String> kmap = new HashMap<String, String>();
                    		Map<String, String> smap = new HashMap<String, String>();
                    		String shop = "";
                    		if (map.get("KIND_ID") != null) {
                    			kind = map.get("KIND_ID").toString();
                    		}
                    		if (map.get("SHOP_ID") != null) {
                    			shop = map.get("SHOP_ID").toString();
                    		}
                    		
                    		KindService ktemp = new KindService();
                    		ShopService stemp = new ShopService();
                    		kmap = ktemp.selectKindInfoById(kind);
                    		smap = stemp.selectShopInfoById(shop);
                    		kind = (kmap.size() != 0) ? kmap.get("KIND_NAME") : "";
                    		shop = (smap.size() != 0) ? smap.get("SHOP_NAME") : "";		
                   	  %>
                        <tr>
                            <td><%=map.get("GOODS_ID") %></td>
                            <td><%=map.get("GOODS_NAME") %></td>
                            <td><%=kind %></td>
                            <td><%=shop %></td>
                            <td>
                                <a href="/B2cShop/servlet/GoodsServlet?action=goodscontent&goodsid=<%=map.get("GOODS_ID") %>"><i class="icon-search"></i></a>
                            </td>
                            <td>
                                <a href="/B2cShop/servlet/GoodsServlet?action=updategoods&goodsid=<%=map.get("GOODS_ID") %>"><i class="icon-pencil"></i></a>
                            </td>
                            <td>
                                <a href="/B2cShop/servlet/GoodsServlet?action=deletegoods&goodsid=<%=map.get("GOODS_ID") %>" role="button" data-toggle="modal"><i class="icon-remove"></i></a>
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
           	 	<li><a href="/B2cShop/servlet/GoodsServlet?action=selectgoodsbypage&page=<%=Integer.valueOf(pages.getPage())-1 %>">Prev</a></li>
            	<%
            		for(int i = 1; i < pages.getPageCount()+1; i++) {
            	 %> 
                    <li><a href="/B2cShop/servlet/GoodsServlet?action=selectgoodsbypage&page=<%=i %>"><%=i %></a></li>
                 <%
                 }
                  %>
                    <li><a href="/B2cShop/servlet/GoodsServlet?action=selectgoodsbypage&page=<%=Integer.valueOf(pages.getPage())+1 %>">Next</a></li>
                </ul>
            </div>
        </div>
    </div>
</div>
<jsp:include  page="Public/ManageFooter.jsp"/>
