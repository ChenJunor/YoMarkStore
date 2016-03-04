<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<jsp:include page="Public/ShopHeader.jsp"></jsp:include>

<section id="content">
  <div class="container">

    <ol class="breadcrumb">
      <li><a href="#">主页</a></li>
      <li><a href="#">个人管理</a></li>
      <li><a href="#">个人资料</a></li>
    </ol>
    
    <div class="row">
    	<jsp:include page="Public/ShopSelfMenu.jsp"></jsp:include>
	      <div class="col-sm-9">
	      	<h2>个人资料</h2>
	      	<div class="dispbtn">
	      		<a href="SelfUpdate.jsp" class="btn btn-primary btn-gr">修改</a>
	      	</div>
		  	<ul class="dispinfo">	
		  		<li>用户名： 　<span>aaa</span></li>
		  		<li>注册时间： 　<span>2015-7-14</span></li>
		  		<li>所在地： 　<span>大连</span></li>
        		<li>角　色： 　<span>卖家</span></li>
        	</ul>
	      </div>   
   </div> 
</section>
<jsp:include page="Public/ShopFooter.jsp"></jsp:include>
<jsp:include page="Public/ShopCart.jsp"></jsp:include>
<jsp:include page="Public/ShopInclude.jsp"></jsp:include>