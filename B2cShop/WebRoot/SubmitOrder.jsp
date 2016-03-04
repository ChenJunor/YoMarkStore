<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.dzsw.service.*" %>
<%
	List<Map<String, String>> list = (List)request.getAttribute("purchlist");
 %>
<jsp:include page="Public/ShopHeader.jsp"></jsp:include>
<section id="content">
  <div class="container">

    <ol class="breadcrumb">
      <li><a href="#">主页</a></li>
      <li>生成订单</li>
    </ol>

    <h1 class="page-title">生成订单</h1>

    <table class="shop_table cart table-striped">
      <thead>
        <tr>
          <th class="product-select">查看</th>
          <th class="product-desc">商品描述</th>
          <th class="product-size visible-md visible-lg">折扣</th>
          <th class="product-qty">数量</th>
          <th class="product-price">价格</th>
        </tr>
      </thead>
      <tbody>
      <%
      	float sum = 0;
      	for (Map<String, String> map : list) {
      		GoodsService goods = new GoodsService();
      		Map<String, String> gmap = goods.selectGoodsInfoById(map.get("GOODS_ID"));
      		float price = (map.get("PURCH_PRICE") != null)?Float.valueOf(map.get("PURCH_PRICE")):0;
    		int num = (map.get("PURCH_NUM") != null)?Integer.valueOf(map.get("PURCH_NUM")):0;
       %>
        <tr class="cart_item">
          <td class="product-remove"><a href="/B2cShop/servlet/GoodsServlet?action=goodscontentforward&goodsid=<%=gmap.get("GOODS_ID") %>" class="remove"><i class="fa fa-search fa-2x"></i></a></td>
          <td class="product-desc">
            <dl>
              <dd>
                <h3><%=gmap.get("GOODS_NAME") %></h3>
                <p class="hidden-xs"><%=gmap.get("GOODS_CONTENT") %></p>
              </dd>
            </dl>
          </td>
          <td class="product-size visible-md visible-lg">
            <h3>无</h3>
          </td>
          <td class="product-qty">
            <div class="quantity buttons_added">
              <%=num %>
            </div>
          </td>
          <td class="product-price">
            <h3><%=price %></h3>
          </td>
        </tr>
        <%
        	sum += num*price;
        }
         %>
      </tbody>
    </table>

    <div class="cart-collaterals">
      <table class="totals">
        <tbody>
          <tr class="cart-subtotal">
            <th>订单总金额</th>
            <td><span class="amount"><%=sum %></span></td>
          </tr>
          <tr class="shipping">
            <th>配送费用</th>
            <td>0.00</td>
          </tr>
          <tr class="order-total">
            <th>实付金额</th>
            <td><span class="amount"><%=sum %></span></td>
          </tr>
        </tbody>
      </table>
    </div>

  </div>
</section>
<jsp:include page="Public/ShopFooter.jsp"></jsp:include>
<jsp:include page="Public/ShopCart.jsp"></jsp:include>
<jsp:include page="Public/ShopInclude.jsp"></jsp:include>