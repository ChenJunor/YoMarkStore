<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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

<jsp:include page="Public/ShopHeader.jsp"></jsp:include>
<section id="content">
  <div class="container">

    <ol class="breadcrumb">
      <li><a href="#">主页</a></li>
      <li><a href="#">硬件/配件</a></li>
      <li class="active">U盘/移动硬盘</li>
    </ol>

    <div class="row">
	  <jsp:include page="Public/ShopKindMenu.jsp"></jsp:include>

      <div class="col-sm-9">

        <ul class="products row">
			<%
				for(Map<String, String> map : list) {
			%>
          <li class="col-sm-4">
            <div class="product">
              <div class="thumbnail">
                <a href="/B2cShop/servlet/GoodsServlet?action=goodscontentforward&goodsid=<%=map.get("GOODS_ID") %>"><img src="<%=request.getContextPath()%>/img/images/goods1.png" alt=""></a>
                <a href="#" class="add-to-cart" title="添加到购物车">
                  <span class="fa-stack fa-2x">
                    <i class="fa fa-shopping-cart  fa-stack-1x fa-inverse"></i>
                  </span>
                </a>
              </div>
              <hr>
              <div class="title">
                <h3><a href="#"><%=map.get("GOODS_NAME") %></a></h3>
                <%
                	ShopService shop = new ShopService();
                	Map<String, String> temp = shop.selectShopInfoById(map.get("SHOP_ID"));
                %>
                <p>by <%=temp.get("SHOP_NAME") %></p>
              </div>
              <span class="price">$18</span>
            </div>
          </li>
          <%
				}
          %>

        </ul>

        <ul class="pagination">
        <%
        	if (pages.getPageCount() != 0) {
        %>
          <li><a href="#">&laquo;</a></li>
           	<%
            		for(int i = 1; i <= pages.getPageCount(); i++) {
            	 %> 
                    <li><a href="/B2cShop/servlet/GoodsServlet?action=searchgoodsbykindid&page=<%=i %>"><%=i %></a></li>
                 <%
                 }
                  %>
          <li><a href="#">&raquo;</a></li>
          <%
        	}
          %>
        </ul>
        
      </div>

    </div>

  </div> 
</section>
<jsp:include page="Public/ShopFooter.jsp"></jsp:include>
<jsp:include page="Public/ShopCart.jsp"></jsp:include>
<jsp:include page="Public/ShopInclude.jsp"></jsp:include>
