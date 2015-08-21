<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'firstAction.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" href="<%= application.getInitParameter ("Extjs42Location") %>/resources/ext-theme-classic/ext-theme-classic-all.css"/>
	<script src="<%= application.getInitParameter ("Extjs42Location") %>/ext-all.js"></script>
	<script src="<%= application.getInitParameter ("Extjs42Location") %>/locale/ext-lang-zh_CN.js"></script>
	<link href="Resource/css/top.css" rel="stylesheet" type="text/css" />
  </head>
  
  <body>
    This is my First JSP page. <br>
  </body>
   <script type="text/javascript">
		/*----------------- 创建检索工具条 -----------------*/
		//1.需要修改对应数据类型的检索控件
		
		/*
		检索项名
		*/
		var toolBarSearch = Ext.create('Ext.toolbar.Toolbar',{
						
				items : [
					{
			        	xtype:'button',
		       			text:'检索',
		       			tooltip: 'Edit',
		       			icon:iconSearch,
		       			handler: search        							
			        }
				]
		});
		//创建页面
		var root = Ext.create('Ext.container.Viewport',{
		        layout:'anchor',
			    items: [
							toolBarSearch,
			            	toolBarGrid,
			            	listGrid
			    		]
			}
		);
		   	
   	</script>
</html>
