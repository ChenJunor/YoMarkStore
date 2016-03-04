<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<jsp:include page="Public/ShopHeader.jsp"></jsp:include>

<section id="content">
  <div class="container">

    <ol class="breadcrumb">
      <li><a href="#">主页</a></li>
      <li><a href="#">个人信息管理</a></li>
      <li><a href="#">订单管理</a></li>
    </ol>
    
    <div class="row">
    	<jsp:include page="Public/ShopSelfMenu.jsp"></jsp:include>
	      <div class="col-sm-9">
	      	<h2>举报信息</h2>
	      	<table class="disptable">
	      		<thead>
	      			<tr>
	       				<th>举报商品</th>
	      				<th>举报理由</th>
	      				<th>信息状态</th>
	      				<th>删除</th>
	      			</tr>
	      		</thead>
	      		<tbody>
	      			<tr>
	      				<th>希捷1T移动硬盘</th>
	      				<th>太贵了！！</th>
	      				<th>已通过</th>
	      				<th><i class="icon-remove"></i></th>
	      			</tr>
	      			<tr>
	      				<th>金士顿32GU盘</th>
	      				<th>山寨货！！！</th>
	      				<th>未通过</th>
	      				<th><i class="icon-remove"></i></th>
	      			</tr>
	      		</tbody>
	      	</table>
	      	
	      </div>   
   </div> 
</section>
<jsp:include page="Public/ShopFooter.jsp"></jsp:include>
<jsp:include page="Public/ShopCart.jsp"></jsp:include>
<jsp:include page="Public/ShopInclude.jsp"></jsp:include>