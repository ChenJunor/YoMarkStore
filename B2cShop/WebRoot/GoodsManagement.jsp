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
	      	<h2>商品管理</h2>
	      	<table class="disptable">
	      		<thead>
	      			<tr>
	      				<th>商品号</th>
	      				<th>商品名</th>
	      				<th>种类</th>
	      				<th>产地</th>
	      				<th>零售价</th>
	      				<th>上架时间</th>
	      				<th>库存量</th>
	      				<th>修改</th>
	      				<th>删除</th>
	      			</tr>
	      		</thead>
	      		<tbody>
	      			<tr>
	      				<th>1</th>
	      				<th>希捷1T移动硬盘</th>
	      				<th>移动硬盘</th>
	      				<th>美国</th>
	      				<th>568</th>
	      				<th>2015-7-2</th>
	      				<th>15</th>
	      				<th><a href="#"><i class="icon-pencil"></i></a></th>
	      				<th><a href="#"><i class="icon-remove"></i></a></th>
	      			</tr>
	      			<tr>
	      				<th>1</th>
	      				<th>希捷1T移动硬盘</th>
	      				<th>移动硬盘</th>
	      				<th>美国</th>
	      				<th>568</th>
	      				<th>2015-7-2</th>
	      				<th>15</th>
	      				<th><a href="#"><i class="icon-pencil"></i></a></th>
	      				<th><a href="#"><i class="icon-remove"></i></a></th>
	      			</tr>
	      		</tbody>
	      	</table>
	      	
	      </div>   
   </div> 
</section>
<jsp:include page="Public/ShopFooter.jsp"></jsp:include>
<jsp:include page="Public/ShopCart.jsp"></jsp:include>
<jsp:include page="Public/ShopInclude.jsp"></jsp:include>