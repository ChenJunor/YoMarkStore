<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<jsp:include page="Public/ShopHeader.jsp"></jsp:include>

<section id="content">
  <div class="container">

    <ol class="breadcrumb">
      <li><a href="#">主页</a></li>
      <li>申请开店</li>
    </ol>

    <h1 class="page-title">开店信息</h1>
    <div class="gap-10"></div>

    <div class="row">
      <div class="col-sm-6">
        
        <h3>快速开店</h3>
        <div class="gap-20"></div>
        <form role="form" method="post" action="/B2cShop/servlet/ShopServlet?action=insertshop&userid=<%= %>">
          <div class="form-group">
            <input type="text" class="form-control input-lg" id="InputUsername" placeholder="Shopname" name="shop">
          </div>
          <div class="pull-right">
            <input type="submit" class="btn btn-primary btn-lg" value="申请">
          </div>
        </form>
        <div class="gap-30"></div>
      </div>
      <div class="col-sm-6">
        
        <h3>已有账户即可免费开店</h3>
        <div class="gap-15"></div>
        <p>申请开店，只需简单店名，即可拥有属于自己的网店~</p>
        <p>已有网店？</p>
        <a href="ShopManagement.jsp" class="btn btn-primary btn-lg">管理网店</a>
      </div>
    </div>
  </div>
</section>
<jsp:include page="Public/ShopFooter.jsp"></jsp:include>
<jsp:include page="Public/ShopCart.jsp"></jsp:include>
<jsp:include page="Public/ShopInclude.jsp"></jsp:include>