<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<jsp:include page="Public/ShopHeader.jsp"></jsp:include>

<section id="content">
  <div class="container">

    <ol class="breadcrumb">
      <li><a href="#">主页</a></li>
      <li>账户注册</li>
    </ol>

    <h1 class="page-title">账户注册</h1>
    <div class="gap-10"></div>

    <div class="row">
      <div class="col-sm-6">
        
        <h3>快速注册</h3>
        <div class="gap-20"></div>
        <form role="form">
          <div class="form-group">
            <input type="text" class="form-control input-lg" id="InputUsername" placeholder="Username">
          </div>
          <div class="form-group">
            <input type="password" class="form-control input-lg" id="InputPassword" placeholder="Password">
          </div>
          <div class="form-group">
            <input type="password" class="form-control input-lg" id="ReInputPassword" placeholder="Repeat Password">
          </div>
          <div class="form-group">
            <input type="email" class="form-control input-lg" id="InputEmail" placeholder="E-mail">
          </div>
          
          <div class="pull-right">
            <button type="submit" class="btn btn-primary btn-lg">注册</button>
          </div>
        </form>
        <div class="gap-30"></div>
      </div>
      <div class="col-sm-6">
        
        <h3>已有账户即可免费开店</h3>
        <div class="gap-15"></div>
        <p>买家升级卖家，简单两步，开启网店模式~</p>

        <p>
          <span class="text-primary">Step 1:</span> 点击“我要开店”，完善店铺信息<br>
          <span class="text-primary">Step 2:</span> 店铺添加商品，开店流程结束
        </p>

        <a href="register.jsp" class="btn btn-primary btn-lg">登录</a>

      </div>
    </div>


  </div>
</section>
<jsp:include page="Public/ShopFooter.jsp"></jsp:include>
<jsp:include page="Public/ShopCart.jsp"></jsp:include>
<jsp:include page="Public/ShopInclude.jsp"></jsp:include>