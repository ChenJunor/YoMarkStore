<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<jsp:include page="Public/ShopHeader.jsp"></jsp:include>

<section id="content">
  <div class="container">

    <ol class="breadcrumb">
      <li><a href="#">主页</a></li>
      <li><a href="#">个人信息管理</a></li>
      <li><a href="#">收货地址</a></li>
    </ol>
    
    <div class="row">
    	<jsp:include page="Public/ShopSelfMenu.jsp"></jsp:include>
	      <div class="col-sm-9">
	      	<h2>收货地址</h2>
	      	<div class="dispbtn">
	      		<a href="ReceiveInsert.jsp" class="btn btn-primary btn-gr">添加</a>
	      	</div>	      
	      	<table class="disptable">
	      		<thead>
	      			<tr>
	      				<th>收货人</th>
	      				<th>收货地址</th>
	      				<th>联系方式</th>
	      				<th>修改</th>
	      				<th>删除</th>
	      			</tr>
	      		</thead>
	      		<tbody>
	      			<tr>
	      				<th>aaa</th>
	      				<th>大连民族学院金石滩校区</th>
	      				<th>12345678901</th>
	      				<th><a href="ReceiveUpdate.jsp"><i class="icon-pencil"></i></a></th>
	      				<th><i class="icon-remove"></i></th>
	      			</tr>
	      			<tr>
	      				<th>aaa</th>
	      				<th>大连民族学院金石滩校区</th>
	      				<th>12345678901</th>
	      				<th><i class="icon-pencil"></i></th>
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