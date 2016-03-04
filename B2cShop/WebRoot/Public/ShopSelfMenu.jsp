<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
     <div class="col-sm-3 sidebar" >

        <aside class="widget widget_product_categories">
          <h3 class="widget-title">个人信息管理:</h3>
          <ul>
          	<li><a href="SelfManagement.jsp">个人资料</a></li>
            <li ><a href="ReceiveManagement.jsp">收货地址</a></li>
            <li ><a href="PasswordUpdate.jsp">密码修改</a></li>       
          </ul>
        </aside>

        <aside class="widget widget_product_brand">
          <h3 class="widget-title">交易管理</h3>
          <ul>
            <li><a href="OrderManagement.jsp">我的订单</a></li>
          </ul>
        </aside>

        <aside class="widget widget_product_color">
          <h3 class="widget-title">最近动向</h3>
          <ul>
            <li><a href="ReportManagement.jsp">举报信息</a></li>
            <li><a href="CommentsManagement.jsp">我的评论</a></li>
          </ul>
        </aside>

        <aside class="widget widget_product_size">
          <h3 class="widget-title">店铺情况</h3>
          <ul>
            <li><a href="ShopManagement.jsp">店铺管理</a></li>
            <li><a href="GoodsManagement.jsp">商品管理</a></li>
            <li><a href="SentGoodsManagement.jsp">发货管理</a></li>
          </ul>
        </aside>
		
		<aside class="widget widget_product_search">
			<h3 class="widget-title">查找商品</h3>
			<form action="#" method="post ">
				<input type="text" id="amount" class="menu_key" />
				<input type="submit" class="menu_search input-sm" value="搜索" name="search"/>
			</form>
		</aside>
		
      </div>