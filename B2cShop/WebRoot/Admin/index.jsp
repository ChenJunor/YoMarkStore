<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<jsp:include  page="Public/ManageHeader.jsp"/>
  	<div class="container-fluid">
		<div class="row-fluid">
  			<jsp:include  page="Public/ManageNavigation.jsp"/>
  			<div class="span9">
  				<script type="text/javascript" src="lib/jqplot/jquery.jqplot.min.js"></script>
  				<script type="text/javascript" charset="utf-8" src="javascripts/graphDemo.js"></script>
  				<div class="stats">
   					<p class="stat"><span class="number">10</span>待处理</p>
				</div>
				<h2 class="page-title">后台管理</h2>
				<div class="row-fluid">
					<div class="block">
						<p class="block-heading">平台使用情况</p>
						<div id="chart-container" class="block-body collapse in">
							<div id="line-chart"></div>
						</div>
					</div>
					<div class="row-fluid">
						<div class="block span6">
							<div class="block-heading">用户信息</div>
							<div id="tablewidget" class="block-body collapse in">
								<table class="table">
									<thead>
										<tr>
											<th>用户名</th>
											<th>身份</th>
											<th>查看详情</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td>Mark</td>
											<td>Tompson</td>
											<td>the_mark7</td>
										</tr>
										<tr>
											<td>Ashley</td>
											<td>Jacobs</td>
											<td>ash11927</td>
										</tr>
										<tr>
											<td>Audrey</td>
											<td>Ann</td>
											<td>audann84</td>
										</tr>
										<tr>
											<td>John</td>
											<td>Robinson</td>
											<td>jr5527</td>
										</tr>
										<tr>
											<td>Aaron</td>
											<td>Butler</td>
											<td>aaron_butler</td>
										</tr>
										<tr>
											<td>Chris</td>
											<td>Albert</td>
											<td>cab79</td>
										</tr>
									</tbody>
								</table>
								<p><a href="users.html">查看更多...</a></p>
							</div>
						</div>
					<div class="block span6">
						<div class="block-heading">店铺信息</div>
						<div id="table3widget" class="block-body collapse in">
							<table class="table">
								<thead>
									<tr>
										<th>店铺名</th>
										<th>所属者</th>
										<th>查看详情</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>Mark</td>
										<td>Tompson</td>
										<td>the_mark7</td>
									</tr>
									<tr>
										<td>Ashley</td>
										<td>Jacobs</td>
										<td>ash11927</td>
									</tr>
									<tr>
										<td>Audrey</td>
										<td>Ann</td>
										<td>audann84</td>
									</tr>
									<tr>
										<td>John</td>
										<td>Robinson</td>
										<td>jr5527</td>
									</tr>
									<tr>
										<td>Aaron</td>
										<td>Butler</td>
										<td>aaron_butler</td>
									</tr>
									<tr>
										<td>Chris</td>
										<td>Albert</td>
										<td>cab79</td>
									</tr>
								</tbody>
							</table>
							<p><a href="users.html">查看更多...</a></p>
						</div>
					</div>
				</div>
				<div class="row-fluid">
					<div class="block span6">
						<div class="block-heading">库存信息</div>
						<div id="widget2container" class="block-body collapse in">
							<table class="table">
								<tbody>
									<tr>
										<td>
											<p><i class="icon-user"></i> Mark Otto</p>
										</td>
										<td>
											<p>Amount: $1,247</p>
										</td>
										<td>
											<p>Date: 7/19/2012</p>
											<a href="#">View Transaction</a>
										</td>
									</tr>
									<tr>
										<td>
											<p><i class="icon-user"></i> Audrey Ann</p>
										</td>
										<td>
											<p>Amount: $2,793</p>
										</td>
										<td>
											<p>Date: 7/12/2012</p>
											<a href="#">View Transaction</a>
										</td>
									</tr>
									<tr>
										<td>
											<p><i class="icon-user"></i> Mark Tompson</p>
										</td>
										<td>
											<p>Amount: $2,349</p>
										</td>
										<td>
											<p>Date: 3/10/2012</p>
											<a href="#">View Transaction</a>
										</td>
									</tr>
									<tr>
										<td>
											<p><i class="icon-user"></i> Ashley Jacobs</p>
										</td>
										<td>
											<p>Amount: $1,192</p>
										</td>
										<td>
											<p>Date: 1/19/2012</p>
											<a href="#">View Transaction</a>
										</td>
									</tr>	
								</tbody>
							</table>
						</div>
					</div>
					<div class="block span6">
						<div class="block-heading">日志信息</div>
						<div id="widget4container" class="block-body collapse in">
							<table class="table">
								<tbody>
									<tr>
										<td>
											<p><i class="icon-user"></i> Mark Otto</p>
										</td>
										<td>
											<p>Amount: $1,247</p>
										</td>
										<td>
											<p>Date: 7/19/2012</p>
											<a href="#">View Transaction</a>
										</td>
									</tr>
									<tr>
										<td>
											<p><i class="icon-user"></i> Audrey Ann</p>
										</td>
										<td>
											<p>Amount: $2,793</p>
										</td>
										<td>
											<p>Date: 7/12/2012</p>
											<a href="#">View Transaction</a>
										</td>
									</tr>
									<tr>
										<td>
											<p><i class="icon-user"></i> Mark Tompson</p>
										</td>
										<td>
											<p>Amount: $2,349</p>
										</td>
										<td>
											<p>Date: 3/10/2012</p>
											<a href="#">View Transaction</a>
										</td>
									</tr>
									<tr>
										<td>
											<p><i class="icon-user"></i> Ashley Jacobs</p>
										</td>
										<td>
											<p>Amount: $1,192</p>
										</td>
										<td>
											<p>Date: 1/19/2012</p>
											<a href="#">View Transaction</a>
										</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>		
</div>

<jsp:include  page="Public/ManageFooter.jsp"/>



