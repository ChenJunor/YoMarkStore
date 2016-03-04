<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<jsp:include page="Public/ShopHeader.jsp"></jsp:include>

<section id="content">
  <div class="container">

    <ol class="breadcrumb">
      <li><a href="#">主页</a></li>
      <li><a href="#">个人管理</a></li>
      <li><a href="#">新增店铺</a></li>
    </ol>
    
    <div class="row">
    	<jsp:include page="Public/ShopSelfMenu.jsp"></jsp:include>
	      <div class="col-sm-9">
	      	<h2>新增店铺</h2>
	      	<div class="dispbtn">
	      		<a href="ReceiveManagement.jsp" class="btn btn-primary btn-gr">返回查看</a>
	      	</div>
		  	<form class="dispinfo" method="post">
		  		<label>店铺名</label>
		  		<input type="text"  class="form-control input-gr">	
		  		
		  		
		  		<input class="btn btn-primary dispbtn" type="submit" value="添加" />
        	</form>
	      </div>   
	 </div>
   </div> 
</section>
<jsp:include page="Public/ShopFooter.jsp"></jsp:include>
<jsp:include page="Public/ShopCart.jsp"></jsp:include>
<jsp:include page="Public/ShopInclude.jsp"></jsp:include>