<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.dzsw.service.*" %>
<%
	List<Map<String, String>> list = (session.getAttribute("user_info") == null) ? new ArrayList<Map<String, String>>(2) : (List<Map<String, String>>)session.getAttribute("user_info");
	Map<String, String> map = (list.size() != 0) ? list.get(0) : new HashMap<String, String>();
	PurchaseService purch = new PurchaseService();
	List<Map<String, String>> result = new ArrayList<Map<String, String>>();
	String userid = "";
	if (map.size() != 0) {
		List<Map<String, String>> ltemp = new ArrayList<Map<String, String>>();
		ltemp.add(new HashMap<String, String>());
		Map<String, String> mtemp = new HashMap<String, String>();
		userid = map.get("USER_ID");
		mtemp.put("user_id", map.get("USER_ID"));
		mtemp.put("purch_type", "0");
		ltemp.add(mtemp);
		result = purch.selectPurchase(ltemp);
		result = (result.size() != 0) ? result : new ArrayList<Map<String, String>>();
	}
	
%>
<div id="cart-panel" class="panel-left">
  <aside class="widget_shopping_cart">
    <h3>购物车</h3>
    <ul class="cart_list">
    <%
    	float sum = 0;
    	for (Map<String, String> m : result) {
    		GoodsService goods = new GoodsService();
    		Map<String, String> good = goods.selectGoodsInfoById(m.get("GOODS_ID"));
    		if (good.size() != 0) {
    			float price = (m.get("PURCH_PRICE") != null)?Float.valueOf(m.get("PURCH_PRICE")):0;
    			int num = (m.get("PURCH_NUM") != null)?Integer.valueOf(m.get("PURCH_NUM")):0;
    %>
      <li>
        <a href="#">
          <img alt="" src="img/images/cart.png">
        	<%=good.get("GOODS_NAME") %>
        </a>
        <span class="quantity"><%=num %> × <span class="amount">￥<%=price %></span></span>
      </li>
    <%
    	sum += price*num;
    	}
    }
    %>
    </ul>
    <p class="total"><strong>总价:</strong> <span class="amount"><%=sum %></span></p>
    <p class="buttons">
      <a class="btn btn-default btn-lg btn-block" href="/B2cShop/servlet/PurchaseServlet?action=submitorder&userid=<%=userid %>">生成订单</a>
    </p>
  </aside>
</div>