<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String extjsPath = "http://"+request.getServerName()+":8080/extjs42";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>大数据 X-DATA</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" href="<%= application.getInitParameter("Extjs42Location") %>/resources/ext-theme-classic/ext-theme-classic-all.css"/>
	<script src="<%=application.getInitParameter("Extjs42Location")%>/ext-all.js"></script>
	<script src="<%=application.getInitParameter("Extjs42Location")%>/locale/ext-lang-zh_CN.js"></script>
	<link href="Resource/css/top.css" rel="stylesheet" type="text/css" />
  </head>
  
  <body>
    
  </body>
  <script type="text/javascript">
			Ext.regModel('menuitemModel',{
				fields:['text','url']
			});
			var store = Ext.create('Ext.data.TreeStore', {
			    model:'menuitemModel',
			    root: 
			    		{
					        expanded: true,
					        text:'数据中心',
					        children: 
					        	[
					        		{ 
					                	text: "功能测试页面", 
					                	leaf: true,
				                		url:'firstPage/toFirstAction.action'
				                	}
						            ,
						            {
						            	text: "TODO", 
						            	expanded: true, 
						            	children: [
										                { 
										                	text: "TODO组", 
										                	leaf: true,
									                		url:'todo/toListTodoGroupPage.action'
									                	},
										                { 
										                	text: "TODO", 
										                	leaf: true,
									                		url:'todo/toListTodoPage.action'
									                	}
						            			]
						            }
						            ,
						            {
						            	text: "Task", 
						            	expanded: true, 
						            	children: [
						            					 { 
										                	text: "Task列表", 
										                	leaf: true,
									                		url:'task/toListTaskPage.action'
									                	},
										                { 
										                	text: "Task日志", 
										                	leaf: true,
									                		url:'task/toListTaskLogPage.action'
									                	}
						            			]
						            }
						            ,
						            {
						            	text: "证券数据", 
						            	expanded: true, 
						            	children: [
										                
										                { 
										                	text: "股票基本信息", 
										                	leaf: true,
									                		url:'stock/toListStockPage.action'
									                		//url:'stock/toListStockExchangePage.action'
									                	},
									                	{ 
										                	text: "股票交易所基本信息", 
										                	leaf: true,
									                		url:'stock/toListStockExchangePage.action'
									                	}
									                	,
									                	{ 
										                	text: "新浪实时数据", 
										                	leaf: true,
									                		url:'sina/toListStockDataSinaPage.action'
									                	}
									                	,
									                	{ 
										                	text: "雅虎历史数据", 
										                	leaf: true,
									                		url:'yahoo/toListStockDataYahooPage.action'
									                	}
									                	,
									                	{ 
										                	text: "雅虎历史数据处理标志", 
										                	leaf: true,
									                		url:'yahoo/toListStockDataYahooFlagPage.action'
									                	}
						            			]
						            }
						            ,
						            {
						            	text: "平均线数据", 
						            	expanded: true, 
						            	children: [
										                
										                { 
										                	text: "新浪数据平均线", 
										                	leaf: true,
									                		url:'sina/toListStockDataSinaMAPage.action'
									                	}
						            			]
						            }
						            ,
						            {
						            	text: "分析", 
						            	expanded: true, 
						            	children: [
										                
										                { 
										                	text: "关键词", 
										                	leaf: true,
									                		url:'keyword/toListKeywordPage.action'
									                		//url:'stock/toListStockExchangePage.action'
									                	}
									                	,
									                	{ 
										                	text: "关键词-股票", 
										                	leaf: true,
									                		url:'keyword/toListKeywordLinkPage.action'
									                		//url:'stock/toListStockExchangePage.action'
									                	}
						            			]
						            }
					            ] 
				            }
   			             
		        		
		    		
			});
			
			var menuPanel = Ext.create('Ext.tree.Panel', {
			    title: 'Simple Tree',
			    width: 200,
			    height: 150,
			    store: store,
			    rootVisible: true,
			    listeners:{
			    	itemclick:{
			    		fn:function(){
			    			alert('do it');
			    		}
			    	}
			    }
			});
			
			Ext.onReady(function(){
				var viewport = new Ext.Viewport({
			   		layout: 'border',
			   		renderTo:Ext.getBody(),
			   		items:[
			   			
			   			{
			   				xtype:'treepanel',
			   				region:'west',
			   				layout:'fit',
			   				title:'系统菜单',
			   				html:'west',
			   				padding:'2 0 2 2',
			   				collapsible:true,
			   				store:store,
			   				width:200,
			   				split:true,
			   				listeners:{
						    	itemclick:{
						    		fn:function(view,record){
						    			if(record)
										{
											if(record.data.leaf)
											{
												Ext.getCmp('mainContent').setTitle(record.data.text);
												window.frames[0].location =record.data.url;
												
											}
											else
											{
												//Do Nothing
											}
										}
										else
										{
											//Do Nothing
										}
						    		}
						    	}
						    }
			   			},
			   			{
			   				//layout:'fit',
			   				html:'<iframe scrolling="auto" style="width:100%;height:100%;border-style:none" src="http://www.10jqka.com.cn/"></iframe>',
			   				region:'center',
			   				title:'内容',
			   				id:'mainContent',
			   				padding:'2 2 2 0'
			   			}
			   		]
			   	});

			});
		   	
    	</script>
</html>
