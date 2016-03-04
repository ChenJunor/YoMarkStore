<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.dzsw.service.*" %>
<%
	KindService kind = new KindService(); 
	List<Map<String, String>> val = new ArrayList<Map<String, String>>();
	val.add(new HashMap<String, String>());
	Map<String, String> selVal = new HashMap<String, String>();
	selVal.put("KIND_FATH", "0");
	val.add(selVal);
	List<Map<String, String>> list = kind.selectKind(val);
%>
     <div class="col-sm-3 sidebar" >

        <aside class="widget widget_product_categories">

          <h3 class="widget-title">商品分类:</h3>
          <ul>
           <%
           		int count = 0;
        		for(Map<String, String> map : list) {
        	%>
            <li>
                <div data-toggle="collapse" data-target="#side-menu<%=count %></div>"><a><b><%=map.get("KIND_NAME") %></b></a></div>
                    <ul id="side-menu<%=count %>">
                    	<%
                    		selVal.put("KIND_FATH", map.get("KIND_ID"));
                    		List<Map<String, String>> clist = kind.selectKind(val);
                    		for(Map<String, String> temp : clist) {
                    	%>
                        	<li><a href="/B2cShop/servlet/GoodsServlet?action=searchgoodsbykindid&kindid=<%=temp.get("KIND_ID") %>"><%=temp.get("KIND_NAME") %></a></li>
                        <%
                    		}
                        %>              
                    </ul>
            </li>
            <%	count++;
        		}
            %>
          </ul>
        </aside>
		
		<aside class="widget widget_product_search">
			<h3 class="widget-title">查找商品</h3>
			<form action="#" method="post ">
				<input type="text" id="amount" class="menu_key" />
				<input type="submit" class="menu_search input-sm" value="搜索" name="search"/>
			</form>
		</aside>
		
      </div>