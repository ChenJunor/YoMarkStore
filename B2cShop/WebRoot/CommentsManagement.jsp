<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<jsp:include page="Public/ShopHeader.jsp"></jsp:include>

<section id="content">
  <div class="container">

    <ol class="breadcrumb">
      <li><a href="#">主页</a></li>
      <li><a href="#">最近动向</a></li>
      <li><a href="#">我的评论</a></li>
    </ol>
    
    <div class="row">
    	<jsp:include page="Public/ShopSelfMenu.jsp"></jsp:include>
	      <div class="col-sm-9">
	      	<h2>评论管理</h2>      
	      	<table class="disptable">
	      		<thead>
	      			<tr>
	      				<th>商品名</th>
	      				<th>评论时间</th>
	      				<th>评论内容</th>
	      				<th>修改</th>
	      				<th>删除</th>
	      			</tr>
	      		</thead>
	      		<tbody>
	      			<tr>
	      				<th>aaa</th>
	      				<th>2015-7-12</th>
	      				<th>斯蒂芬金拉萨及发射距离</th>
	      				<th><a href="ReceiveUpdate.jsp"><i class="icon-pencil"></i></a></th>
	      				<th><i class="icon-remove"></i></th>
	      			</tr>
	      			<tr>
	      				<th>aaa</th>
	      				<th>2015-7-11</th>
	      				<th>夫斯基的考虑放哪啦是否接受了贷款</th>
	      				<th><a href="ReceiveUpdate.jsp"><i class="icon-pencil"></i></a></th>
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