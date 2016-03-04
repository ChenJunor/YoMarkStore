<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.dzsw.service.*" %>
<%
	Map<String, String> map = (Map)request.getAttribute("goodsinfo");
	List<Map<String, String>> clist = (List)request.getAttribute("commentsinfo");
%>
<jsp:include page="Public/ShopHeader.jsp"></jsp:include>
<section id="content">
  <div class="container">

    <ol class="breadcrumb">
      <li><a href="#">主页</a></li>
      <li><a href="#">U盘 存储</a></li>
      <li>希捷移动硬盘</li>
    </ol>

    <div class="row">
      <div class="col-sm-5">

        <div id="product-large" class="owl-carousel">
          <div class="item"><img src="<%=request.getContextPath()%>/img/images/goods458480.png"></div>
          <div class="item"><img src="<%=request.getContextPath()%>/img/images/goods458480.png"></div>
          <div class="item"><img src="<%=request.getContextPath()%>/img/images/goods458480.png"></div>
          <div class="item"><img src="<%=request.getContextPath()%>/img/images/goods458480.png"></div>
        </div>
        <div id="product-thumb" class="owl-carousel">
          <div class="item"><img src="<%=request.getContextPath()%>/img/images/goods7878.png"></div>
          <div class="item"><img src="<%=request.getContextPath()%>/img/images/goods7878.png"></div>
          <div class="item"><img src="<%=request.getContextPath()%>/img/images/goods7878.png"></div>
          <div class="item"><img src="<%=request.getContextPath()%>/img/images/goods7878.png"></div>
        </div>

      </div>
      <div class="col-sm-7 summary entry-summary">
        
        <h1 class="product_title"><%=map.get("GOODS_NAME") %></h1>

        <p class="price">
          <sup>￥</sup><span class="amount"><%=map.get("ORD_PRICE") %></span><sup>00</sup>
        </p>
        
		<h3>商品编号</h3>
		<p><%=map.get("GOODS_ID") %></p>
		<h3>快递</h3><p>免运费</p>
		<%
			List<Map<String, String>> userinfo = (session.getAttribute("user_info") != null) ? (List)session.getAttribute("user_info") : new ArrayList<Map<String, String>>(10);
			String userid = "";
			if(userinfo.size() != 0) {
				if (userinfo.get(0).size() != 0) {
					userid = userinfo.get(0).get("USER_ID");
				}
			}
		%>
	
        <hr>
		<form action="/B2cShop/servlet/GoodsServlet?action=inserttocart&goodsid=<%=map.get("GOODS_ID") %>&userid=<%=userid %>&price=<%=map.get("ORD_PRICE") %>" method="post">
        	<div class="quantity buttons_added">
          		<input type="number" size="4" class="qty text form-control" title="Qty" value="1" name="number" step="1">
       		</div>
        	<input type="submit" class="btn btn-primary btn-lg btn-addbywy" id="add-to-cart" value="加入购物车">
        </form>
      </div>
    </div>

    <!-- Nav tabs -->
    <ul class="nav nav-tabs product-tabs">
      <li class="active"><a href="#description" data-toggle="tab">详细描述</a></li>
      <li><a href="#reviews" data-toggle="tab">评论</a></li>
    </ul>

    <!-- Tab panes -->
    <div class="tab-content">
      <div class="tab-pane active" id="description">
  			<p><%=map.get("GOODS_CONTENT") %></p>
      </div>
      <div class="tab-pane" id="reviews">

        <div id="reviews">
          <ol class="commentlist">
			<%
				for (Map<String, String> m : clist) {
			%>
            <li class="comment">
              <div class="comment_container">
                <img src="<%=request.getContextPath()%>/img/images/goods6464.png" class="avatar">
                <div class="comment-text">
                  <div class="start-rating">
                    <span class="glyphicon glyphicon-star"></span>
                    <span class="glyphicon glyphicon-star"></span>
                    <span class="glyphicon glyphicon-star"></span>
                    <span class="glyphicon glyphicon-star"></span>
                    <span class="glyphicon glyphicon-star"></span>
                  </div>
                  <%
                  		UserService user = new UserService();
                  		Map<String, String> umap = user.selectUserInfoById(m.get("BUY_USER"));
                  %>
                  <h5 class="meta"><%=umap.get("USER_NAME") %> <span>&mdash; <%=m.get("COMM_DATE") %></span></h5>                
                  <p><%=m.get("COMM_REVIEW") %></p>
                </div>
              </div>
            </li>
            <%
				}
            %>
          </ol>
        </div>

        <hr>

        <div id="review_form">
          <h3 id="reply-title" class="comment-reply-title">评论</h3>
          <form action="#" id="commentform" class="comment-form" method="post">
            <div class="row">
           		<p class="comment-form-comment"><textarea name="review" id="review" class="form-control" cols="30" rows="5" placeholder="请输入你的评论"></textarea></p>
            	<p class="form-submit"><input type="submit" class="btn btn-primary btn-lg" name="proceed" value="提交评论"></p>
          	</div>
          </form>
      </div>

    </div>
  </div>
</section>
<jsp:include page="Public/ShopFooter.jsp"></jsp:include>
<jsp:include page="Public/ShopCart.jsp"></jsp:include>
<jsp:include page="Public/ShopInclude.jsp"></jsp:include>
