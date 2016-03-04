<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<jsp:include  page="Public/ManageHeader.jsp"/>
<div class="container-fluid">
    <div class="row-fluid">
        <div class="dialog span4">
            <div class="block">
            <div class="block-heading">登录</div>
            <div class="block-body">
                <form action="/B2cShop/servlet/AdminServlet?action=checklogin" method="post">
                    <label>用户名</label>
                    <input type="text" class="span12" name="username">
                    <label>密码</label>
                    <input type="password" class="span12" name="password">
                    <input type="submit" class="btn btn-primary pull-right" value="登录"></a>
                    <label class="remember-me"><input type="checkbox"> 记住密码</label>
                    <div class="clearfix"></div>
                </form>
            </div>
        </div>
    </div>
</div>
<jsp:include  page="Public/ManageFooter.jsp"/>