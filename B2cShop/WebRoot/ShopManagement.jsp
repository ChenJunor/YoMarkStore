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
	      	<h2>店铺管理</h2>
	      	<table class="disptable">
	      		<thead>
	      			<tr>
	      				<th>店铺id</th>
	      				<th>店铺名</th>
	      				<th>开张时间</th>
	      				<th>店铺等级</th>
	      				<th>审核状态</th>
	      				<th>操作</th>
	      				<th>删除</th>
	      			</tr>
	      		</thead>
	      		<tbody>
	      			<tr>
	      				<th>1</th>
	      				<th>aaa的店1</th>
	      				<th>2015-6-1</th>
	      				<th>初级</th>
	      				<th>通过</th>
	      				<th><a href="#">关闭</a></th>
	      				<th><i class="icon-remove"></i></th>
	      			</tr>
	      			<tr>
	      				<th>2</th>
	      				<th>aaa的店2</th>
	      				<th>2015-6-1</th>
	      				<th>初级</th>
	      				<th>通过</th>
	      				<th><a href="#">关闭</a></th>
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