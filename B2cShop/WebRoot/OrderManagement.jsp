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
	      	<h2>订单列表</h2>
	      	<table class="disptable">
	      		<thead>
	      			<tr>
	      				<th>订单号</th>
	      				<th>商品信息</th>
	      				<th>价格</th>
	      				<th>发货信息</th>
	      				<th>操作</th>
	      				<th>删除</th>
	      			</tr>
	      		</thead>
	      		<tbody> 
	      			<tr>
	      				<th>1</th>
	      				<th>希捷1T移动硬盘*1、金士顿32GU盘*1</th>
	      				<th>956.0元</th>
	      				<th>大连民族学院金石滩校区 aaa 12345678901</th>
	      				<th><a href="#">取消订单</a></th>
	      				<th><i class="icon-remove"></i></th>
	      			</tr>
	      			<tr>
	      				<th>1</th>
	      				<th>希捷1T移动硬盘*1、金士顿32GU盘*1</th>
	      				<th>956.0元</th>
	      				<th>大连民族学院金石滩校区 aaa 12345678901</th>
	      				<th><a href="#">取消订单</a></th>
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