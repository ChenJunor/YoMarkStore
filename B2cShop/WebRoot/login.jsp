<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<jsp:include page="Public/ShopHeader.jsp"></jsp:include>
<section id="content">
  <div class="container">

    <ol class="breadcrumb">
      <li><a href="#">主页</a></li>
      <li>账户登录</li>
    </ol>

    <h1 class="page-title">账户登录</h1>
    <div class="gap-10"></div>

    <div class="row">
      <div class="col-sm-6">
        
        <h3>登录</h3>
        <div class="gap-20"></div>
        <form role="form" action="/B2cShop/servlet/UserServlet?action=checklogin" method="post">
          <div class="form-group">
            <input type="text" class="form-control input-lg" id="exampleInputUsername" name="username" placeholder="Username">
          </div>
          <div class="form-group">
            <input type="password" class="form-control input-lg" id="exampleInputPassword" name="password" placeholder="Password">
          </div>
          <div class="checkbox pull-left">
            <label>
              <input type="checkbox"> 记住密码
            </label>
          </div>
          <a href="#" class="pull-left" id="forgot-password">忘记密码？?</a>
          <div class="pull-right">
            <button type="submit" class="btn btn-primary btn-lg">登录</button>
          </div>
        </form>
        <div class="gap-30"></div>

      </div>
      <div class="col-sm-6">
        
        <h3>注册即可免费开店</h3>
        <div class="gap-15"></div>
        <p>新用户注册免店铺一切费用！简单三步，开启网店模式~</p>

        <p>
          <span class="text-primary">Step 1:</span> 注册成为普通买家<br>
          <span class="text-primary">Step 2:</span> 点击“我要开店”，完善店铺信息<br>
          <span class="text-primary">Step 3:</span> 店铺添加商品，开店流程结束
        </p>

        <a href="register.jsp" class="btn btn-primary btn-lg">注册</a>

      </div>
    </div>


  </div>
</section>
<jsp:include page="Public/ShopFooter.jsp"></jsp:include>
<jsp:include page="Public/ShopCart.jsp"></jsp:include>
<jsp:include page="Public/ShopInclude.jsp"></jsp:include>