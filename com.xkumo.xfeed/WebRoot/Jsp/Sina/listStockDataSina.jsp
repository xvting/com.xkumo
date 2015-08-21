<!-- 
页面整体说明区域

创建时间：2014-07-01 21:46:03
生成配置：<root><Page title="新浪即时数据" id="StockDataSina" /><Model><field title="流水号" key="true" dataType="int">sinaseq</field><field title="股票代码" search="true" dataType="string">stockcode</field><field title="股票名称" search="true" dataType="string">stockname</field><field title="所属交易所编号" dataType="string">stockexchangecode</field><field title="开盘价" dataType="string">openprice</field><field title="昨日收盘价" dataType="string">lastcloseprice</field><field title="当前价格" dataType="string">currentprice</field><field title="今日最高价" dataType="string">topprice</field><field title="今日最低价" dataType="string">bottomprice</field><field title="竞买价" dataType="string">buyprice</field><field title="竞卖价" dataType="string">sellprice</field><field title="成交金额" dataType="string">transactionamount</field><field title="成交股票数" dataType="string">transactionnumber</field><field title="买一报价" dataType="string">buy1price</field><field title="买一数量" dataType="string">buy1number</field><field title="买二报价" dataType="string">buy2price</field><field title="买二数量" dataType="string">buy2number</field><field title="买三报价" dataType="string">buy3price</field><field title="买三数量" dataType="string">buy3number</field><field title="买四报价" dataType="string">buy4price</field><field title="买四数量" dataType="string">buy4number</field><field title="买五报价" dataType="string">buy5price</field><field title="买五数量" dataType="string">buy5number</field><field title="卖一报价" dataType="string">sell1price</field><field title="卖一数量" dataType="string">sell1number</field><field title="卖二报价" dataType="string">sell2price</field><field title="卖二数量" dataType="string">sel12number</field><field title="卖三报价" dataType="string">sell3price</field><field title="卖三数量" dataType="string">sell3number</field><field title="卖四报价" dataType="string">sell4price</field><field title="卖四数量" dataType="string">sell4number</field><field title="卖五报价" dataType="string">sell5price</field><field title="卖五数量" dataType="string">sell5number</field><field title="数据日期" dataType="string">dataday</field><field title="数据时间" dataType="string">datatime</field></Model><Store ProxyUrl="listStockDataSina.action" /><Add RequestUrl="addStockDataSina.action" /><Edit RequestUrl="updateStockDataSina.action" /><Delete RequestUrl="deleteStockDataSina.action"></Delete><DeleteAll RequestUrl="deleteStockDataSinaAll.action" /></root>
 -->

<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>新浪即时数据</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="<%=application.getInitParameter("Extjs42Location")%>/resources/ext-theme-classic/ext-theme-classic-all.css">
    <script src="<%=application.getInitParameter("Extjs42Location")%>/ext-all.js"></script>
	<script src="<%=application.getInitParameter("Extjs42Location")%>/locale/ext-lang-zh_CN.js"></script>
  </head>
  
  <body>
<script type="text/javascript">

/*----------------- 图片资源配置区域 ----------------- */
var iconSearch = "Resource/icons/cog_edit.png";
var iconView = "Resource/icons/application/application_view_detail.png";
var iconAdd = "Resource/icons/arrow/add.png";
var iconEdit = "Resource/icons/application/application_form_edit.png";
var iconDelete = "Resource/icons/delete.png";
var iconDeleteAll = "Resource/icons/table/table_delete.png";
var iconPlay = "Resource/icons/control/control_play_blue.png";
var iconPause = "Resource/icons/control/control_pause_blue.png";
var iconRefresh = "Resource/icons/arrow/arrow_refresh.png";

/*----------------- 页面变量配置区域 -----------------*/
var itemsPerPage = 20;			//Grid的每页显示行数
var gridHeight = 475;	//Grid的高度


/*----------------- 定义Model区域 -----------------*/
//1.要添加Model的idProperty， 如：idProperty : 'dataIdentity'
//2.要设定数据类型，默认值

/*
数据字段名
*/
Ext.define('StockDataSina.baseModel', {
    extend: 'Ext.data.Model',
    requires: [
        'Ext.data.Field'
    ],

    fields: [
     { name: 'sinaseq',type:'int' },   //流水号
     { name: 'stockcode',type:'string' },   //股票代码
     { name: 'stockname',type:'string' },   //股票名称
     { name: 'stockexchangecode',type:'string' },   //所属交易所编号
     { name: 'openprice',type:'string' },   //开盘价
     { name: 'lastcloseprice',type:'string' },   //昨日收盘价
     { name: 'currentprice',type:'string' },   //当前价格
     { name: 'topprice',type:'string' },   //今日最高价
     { name: 'bottomprice',type:'string' },   //今日最低价
     { name: 'buyprice',type:'string' },   //竞买价
     { name: 'sellprice',type:'string' },   //竞卖价
     { name: 'transactionamount',type:'string' },   //成交金额
     { name: 'transactionnumber',type:'string' },   //成交股票数
     { name: 'buy1price',type:'string' },   //买一报价
     { name: 'buy1number',type:'string' },   //买一数量
     { name: 'buy2price',type:'string' },   //买二报价
     { name: 'buy2number',type:'string' },   //买二数量
     { name: 'buy3price',type:'string' },   //买三报价
     { name: 'buy3number',type:'string' },   //买三数量
     { name: 'buy4price',type:'string' },   //买四报价
     { name: 'buy4number',type:'string' },   //买四数量
     { name: 'buy5price',type:'string' },   //买五报价
     { name: 'buy5number',type:'string' },   //买五数量
     { name: 'sell1price',type:'string' },   //卖一报价
     { name: 'sell1number',type:'string' },   //卖一数量
     { name: 'sell2price',type:'string' },   //卖二报价
     { name: 'sell2number',type:'string' },   //卖二数量
     { name: 'sell3price',type:'string' },   //卖三报价
     { name: 'sell3number',type:'string' },   //卖三数量
     { name: 'sell4price',type:'string' },   //卖四报价
     { name: 'sell4number',type:'string' },   //卖四数量
     { name: 'sell5price',type:'string' },   //卖五报价
     { name: 'sell5number',type:'string' },   //卖五数量
     { name: 'dataday',type:'string' },   //数据日期
     { name: 'datatime',type:'string' },   //数据时间
     { name: 'ma5',type:'float' },
     { name: 'ma10',type:'float' },
      { name: 'ma20',type:'float' },
     { name: 'ma30',type:'float' },
     { name: 'ma60',type:'float' },
     { name: 'ma120',type:'float' },
     { name: 'ma240',type:'float' }   

    ],
   
    idProperty : 'sinaseq'
});

/*----------------- 定义数据Store区域 -----------------*/
var thisStore = Ext.create('Ext.data.Store', {
   	model:'StockDataSina.baseModel',
	autoLoad:false,
	pageSize:itemsPerPage,
	proxy:{
		type:'ajax',
		url:'listStockDataSina.action',
		reader:{
			type:'json',
			root:'root',
			totalProperty: 'total'
		}
	}
});

thisStore.on("beforeload",function(store, operation, eOpts)
	{
		Ext.apply(store.proxy.extraParams,
			{
				s_stockcode : Ext.getCmp('s_stockcode').getValue(),   //检索项目：股票代码
				s_stockname : Ext.getCmp('s_stockname').getValue()   //检索项目：股票名称
			}
		);
	}

);

/*----------------- 创建检索工具条 -----------------*/
//1.需要修改对应数据类型的检索控件

/*
检索项名
*/
var toolBarSearch = Ext.create('Ext.toolbar.Toolbar',{
				
		items : [
			{
		        xtype:'textfield',
		        fieldLabel: '股票代码:',
             	labelAlign:'right',
             	id: 's_stockcode'
			},
			{
		        xtype:'textfield',
		        fieldLabel: '股票名称:',
             	labelAlign:'right',
             	id: 's_stockname'
			},
			{
	        	xtype:'button',
       			text:'检索',
       			tooltip: 'Edit',
       			icon:iconSearch,
       			handler: search        							
	        }
		]
});

//页面检索处理
function search()
{
	 thisStore.loadPage(1);
}

/*----------------- 创建Grid的工具条 -----------------*/

//创建工具条
var toolBarGrid = Ext.create('Ext.toolbar.Toolbar',{
		items:[
         		{
					text:'浏览',
					icon:iconView,
					handler : viewGridItem
         		},
         		/*
         		{
					text:'新建',
					icon:iconAdd,
					handler : addGridItem
         		},
         		{
					text:'编辑',
					icon:iconEdit,
					handler : editGridItem
         		},
         		*/
         		{
					text:'删除',
					icon:iconDelete,
					handler : deleteGridItem
         		},
         		/*
         		{
					text:'全部删除',
					icon:iconDeleteAll,
					handler : deleteGridAllItem
         		}
         		*/
         		,
         		{
					text:'从新浪取得/更新日数据',
					icon:iconRefresh,
					handler : getItemDayDateFromSina
         		}
         		,
         		{
					text:'更新选中证券的MA',
					icon:iconRefresh,
					handler : updataGridItemMA
         		}
         		,
         		{
					text:'更新全部证券的MA',
					icon:iconRefresh,
					handler : updataGridAllMA
         		}
         	]
});

//浏览数据
function viewGridItem()
{
	var viewSelModel = listGrid.getSelectionModel(); 
	if (viewSelModel.hasSelection()) {
		var selectedViewItem = null;
		var selected = viewSelModel.getSelection();
		Ext.each(selected, function (item) {
   				selectedViewItem = item;
   			});
        if(selectedViewItem){
        	var viewWindow = CreateDetailWindow('浏览',null, selectedViewItem);

        	viewWindow.show();	
        }
        else{
        	Ext.Msg.alert("错误", "没有任何行被选中，无法进行浏览操作！");
        }  		
	}
	else{
		Ext.Msg.alert("错误", "没有任何行被选中，无法进行浏览操作！");
	}
}


//新建数据
function addGridItem()
{
	var defaultNewRecord = new  StockDataSina.baseModel();
	var addWindow = CreateDetailWindow('新建','addStockDataSina.action', defaultNewRecord);	
	addWindow.show();
}

//编辑数据
function editGridItem()
{
	var editSelModel = listGrid.getSelectionModel(); 
	if (editSelModel.hasSelection()) {
		var selectedEditItem = null;
		var selected = editSelModel.getSelection();
		Ext.each(selected, function (item) {
   				selectedEditItem = item;
   			});
        if(selectedEditItem){
        	var editWindow = CreateDetailWindow('编辑','updateStockDataSina.action', selectedEditItem);
        	
        	editWindow.show();
        	
        	//editSelModel.clearSelections();	//此处理，防止连续编辑时值没有发生变化问题
        }
        else{
        	Ext.Msg.alert("错误", "没有任何行被选中，无法进行编辑操作！");
        }	
	}
	else{
		Ext.Msg.alert("错误", "没有任何行被选中，无法进行编辑操作！");
	}
}

//删除数据
function deleteGridItem() { 
	
	var selModel = listGrid.getSelectionModel();  

	if (selModel.hasSelection()) 
	{
		Ext.Msg.confirm("警告", "确定要删除吗？", 
			function (button) {
    			if (button == "yes") 
    			{
        			var selected = selModel.getSelection();
        			var Ids = ''; //要删除的id
        			Ext.each(selected, function (item) 
        			{
        				var deleteID = item.data.sinaseq;
        				if(deleteID){
            				Ids = Ids + '"' + deleteID + '",';
        				}
        			});
        			
        			if(Ids)
        			{
        				Ids = Ids.substring(0,Ids.length - 1);
        				
        				Ids = '[' +Ids +']';
        			}
        			else
        			{
        				//Do Nothing
        			}
        			
	        		Ext.Ajax.request(
	        			{
	        			 	url:'deleteStockDataSinaForID.action',
	        			 	params :{
	        			 		deleteID:Ids
	        			 	},
	        			 	method : 'POST',
	        			 	// timeout : 2000,//默认30秒 
	        			 	success : function(response, opts) {
	        			 		var success = Ext.decode(response.responseText).success;
	        			 		var message = Ext.decode(response.responseText).message;
	        			 		 // 当后台数据同步成功时  
	        			 		  if (success) { 
	        			 		  	Ext.Array.each(selected, function(record) {
	        			 		  		thisStore.remove(record);// 页面效果 
	        			 		  	});
	        			 		  }
	        			 		  else{
			            				Ext.Msg.alert("提示", "处理失败！\r\n" + message);  
			            		}
	        			 	},
	        			 	failure: function () {
			            		Ext.Msg.alert("提示", "处理失败，请稍后再试！");   
			            	}
	        			}
	        		);
      				
			    		}
			        });
			    }
    else {
        Ext.Msg.alert("错误", "没有任何行被选中，无法进行删除操作！");
    }
}

//删除全部数据
function deleteGridAllItem()
{
	Ext.Msg.confirm("警告", "确定要删除全部数据吗？", 
			function (button) {
    			if (button == "yes") 
    			{
    				Ext.Ajax.request(
	        			{
	        			 	url:'deleteStockDataSinaAll.action',
	        			 	method : 'POST',
	        			 	// timeout : 2000,//默认30秒 
	        			 	success : function(response, opts) {
	        			 		var success = Ext.decode(response.responseText).success;
	        			 		var message = Ext.decode(response.responseText).message;
	        			 		 // 当后台数据同步成功时  
	        			 		  if (success) { 
	        			 		  	thisStore.removeAll();
	        			 		  }
	        			 		  else{
	        			 		  	 Ext.Msg.alert("提示", "处理失败！\r\n" + message);  
	        			 		  }
	        			 	}
	        			 	,
	        			 	failure: function () {
			            		Ext.Msg.alert("提示", "处理失败，请稍后再试！");   
			            	}
	        			}
	        		);
    			}
    			else{
    				//Do Nothing
    			}
			})
}

//从新浪更新当日数据
function getItemDayDateFromSina() { 
	
	Ext.Msg.confirm("警告", "确定要获得当前全部数据吗？", 
			function (button) {
    			if (button == "yes") 
    			{
    				Ext.Ajax.request(
	        			{
	        			 	url:'updateStockDataSinaByDay.action',
	        			 	method : 'POST',
	        			 	// timeout : 2000,//默认30秒 
	        			 	success : function(response, opts) {
	        			 		var success = Ext.decode(response.responseText).success;
	        			 		var message = Ext.decode(response.responseText).message;
	        			 		 // 当后台数据同步成功时  
	        			 		  if (success) { 
	        			 		  	thisStore.removeAll();
	        			 		  }
	        			 		  else{
	        			 		  	 Ext.Msg.alert("提示", "处理失败！\r\n" + message);  
	        			 		  }
	        			 	}
	        			 	,
	        			 	failure: function () {
			            		Ext.Msg.alert("提示", "处理失败，请稍后再试！");   
			            	}
	        			}
	        		);
    			}
    			else{
    				//Do Nothing
    			}
			})
}

//更新MA数据
function updataGridItemMA() { 
	
	var selModel = listGrid.getSelectionModel();  

	if (selModel.hasSelection()) 
	{
		Ext.Msg.confirm("警告", "确定要更新MA数据吗？", 
			function (button) {
    			if (button == "yes") 
    			{
        			var selected = selModel.getSelection();
        			var Ids = ''; //要删除的id
        			Ext.each(selected, function (item) 
        			{
        				var deleteID = item.data.sinaseq;
        				if(deleteID){
            				Ids = Ids + '"' + deleteID + '",';
        				}
        			});
        			
        			if(Ids)
        			{
        				Ids = Ids.substring(0,Ids.length - 1);
        				
        				Ids = '[' +Ids +']';
        			}
        			else
        			{
        				//Do Nothing
        			}
        			
	        		Ext.Ajax.request(
	        			{
	        			 	url:'updateStockDataSinaMAForID.action',
	        			 	params :{
	        			 		deleteID:Ids
	        			 	},
	        			 	method : 'POST',
	        			 	// timeout : 2000,//默认30秒 
	        			 	success : function(response, opts) {
	        			 		var success = Ext.decode(response.responseText).success;
	        			 		var message = Ext.decode(response.responseText).message;
	        			 		 // 当后台数据同步成功时  
	        			 		  if (success) { 
	        			 		  	thisStore.load();
	        			 		  }
	        			 		  else{
			            				Ext.Msg.alert("提示", "处理失败！\r\n" + message);  
			            		}
	        			 	},
	        			 	failure: function () {
			            		Ext.Msg.alert("提示", "处理失败，请稍后再试！");   
			            	}
	        			}
	        		);
      				
			    		}
			        });
			    }
    else {
        Ext.Msg.alert("错误", "没有任何行被选中，无法进行删除操作！");
    }
}

//更新全部数据MA
function updataGridAllMA()
{
	Ext.Msg.confirm("警告", "确定要更新全部数据MA吗？", 
			function (button) {
    			if (button == "yes") 
    			{
    				Ext.Ajax.request(
	        			{
	        			 	url:'updateStockDataSinaAllMA.action',
	        			 	method : 'POST',
	        			 	// timeout : 2000,//默认30秒 
	        			 	success : function(response, opts) {
	        			 		var success = Ext.decode(response.responseText).success;
	        			 		var message = Ext.decode(response.responseText).message;
	        			 		 // 当后台数据同步成功时  
	        			 		  if (success) { 
	        			 		  	//thisStore.removeAll();
	        			 		  	thisStore.load();
	        			 		  }
	        			 		  else{
	        			 		  	 Ext.Msg.alert("提示", "处理失败！\r\n" + message);  
	        			 		  }
	        			 	}
	        			 	,
	        			 	failure: function () {
			            		Ext.Msg.alert("提示", "处理失败，请稍后再试！");   
			            	}
	        			}
	        		);
    			}
    			else{
    				//Do Nothing
    			}
			})
}


/*创建数据窗口
title 标题
okAction 确定/保存 按钮的服务器端处理
defaultData 默认值
*/
function CreateDetailWindow(title,okAction, defaultData)
{
	var showForm = Ext.create('Ext.form.Panel',{
		frame:true,
		height:380,
		bodyStyle:'padding:5px 5px 0',
		defaultType: 'textfield',
		labelAlign:'right',
		labelWidth: 65, 
		id: 'formWindow',
		autoScroll:true,
		bodyStyle : 'overflow-x:hidden; overflow-y:scroll',
		defaults: {allowBlank: true,width: 280}, 
		//要修改成对应数据类型的控件，默认都为TextField
		items:[
			{
		        fieldLabel: '流水号:',
             	labelAlign:'right',
				allowBlank:false,
             	id: 'f_sinaseq'
			},
			{
		        fieldLabel: '股票代码:',
             	labelAlign:'right',
				allowBlank:false,
             	id: 'f_stockcode'
			},
			{
		        fieldLabel: '股票名称:',
             	labelAlign:'right',
				allowBlank:false,
             	id: 'f_stockname'
			},
			{
		        fieldLabel: '所属交易所编号:',
             	labelAlign:'right',
				allowBlank:false,
             	id: 'f_stockexchangecode'
			}
			,
			{
		        fieldLabel: '数据日期:',
             	labelAlign:'right',
				allowBlank:false,
             	id: 'f_dataday'
			},
			{
		        fieldLabel: '数据时间:',
             	labelAlign:'right',
				allowBlank:false,
             	id: 'f_datatime'
			}
			,
			{
		        fieldLabel: '开盘价:',
             	labelAlign:'right',
				allowBlank:false,
             	id: 'f_openprice'
			},
			{
		        fieldLabel: '昨日收盘价:',
             	labelAlign:'right',
				allowBlank:false,
             	id: 'f_lastcloseprice'
			},
			{
		        fieldLabel: '当前价格:',
             	labelAlign:'right',
				allowBlank:false,
             	id: 'f_currentprice'
			},
			{
		        fieldLabel: '今日最高价:',
             	labelAlign:'right',
				allowBlank:false,
             	id: 'f_topprice'
			},
			{
		        fieldLabel: '今日最低价:',
             	labelAlign:'right',
				allowBlank:false,
             	id: 'f_bottomprice'
			},
			{
		        fieldLabel: '竞买价:',
             	labelAlign:'right',
				allowBlank:false,
             	id: 'f_buyprice'
			},
			{
		        fieldLabel: '竞卖价:',
             	labelAlign:'right',
				allowBlank:false,
             	id: 'f_sellprice'
			},
			{
		        fieldLabel: '成交金额:',
             	labelAlign:'right',
				allowBlank:false,
             	id: 'f_transactionamount'
			},
			{
		        fieldLabel: '成交股票数:',
             	labelAlign:'right',
				allowBlank:false,
             	id: 'f_transactionnumber'
			},
			{
		        fieldLabel: '买一报价:',
             	labelAlign:'right',
				allowBlank:false,
             	id: 'f_buy1price'
			},
			{
		        fieldLabel: '买一数量:',
             	labelAlign:'right',
				allowBlank:false,
             	id: 'f_buy1number'
			},
			{
		        fieldLabel: '买二报价:',
             	labelAlign:'right',
				allowBlank:false,
             	id: 'f_buy2price'
			},
			{
		        fieldLabel: '买二数量:',
             	labelAlign:'right',
				allowBlank:false,
             	id: 'f_buy2number'
			},
			{
		        fieldLabel: '买三报价:',
             	labelAlign:'right',
				allowBlank:false,
             	id: 'f_buy3price'
			},
			{
		        fieldLabel: '买三数量:',
             	labelAlign:'right',
				allowBlank:false,
             	id: 'f_buy3number'
			},
			{
		        fieldLabel: '买四报价:',
             	labelAlign:'right',
				allowBlank:false,
             	id: 'f_buy4price'
			},
			{
		        fieldLabel: '买四数量:',
             	labelAlign:'right',
				allowBlank:false,
             	id: 'f_buy4number'
			},
			{
		        fieldLabel: '买五报价:',
             	labelAlign:'right',
				allowBlank:false,
             	id: 'f_buy5price'
			},
			{
		        fieldLabel: '买五数量:',
             	labelAlign:'right',
				allowBlank:false,
             	id: 'f_buy5number'
			},
			{
		        fieldLabel: '卖一报价:',
             	labelAlign:'right',
				allowBlank:false,
             	id: 'f_sell1price'
			},
			{
		        fieldLabel: '卖一数量:',
             	labelAlign:'right',
				allowBlank:false,
             	id: 'f_sell1number'
			},
			{
		        fieldLabel: '卖二报价:',
             	labelAlign:'right',
				allowBlank:false,
             	id: 'f_sell2price'
			},
			{
		        fieldLabel: '卖二数量:',
             	labelAlign:'right',
				allowBlank:false,
             	id: 'f_sell2number'
			},
			{
		        fieldLabel: '卖三报价:',
             	labelAlign:'right',
				allowBlank:false,
             	id: 'f_sell3price'
			},
			{
		        fieldLabel: '卖三数量:',
             	labelAlign:'right',
				allowBlank:false,
             	id: 'f_sell3number'
			},
			{
		        fieldLabel: '卖四报价:',
             	labelAlign:'right',
				allowBlank:false,
             	id: 'f_sell4price'
			},
			{
		        fieldLabel: '卖四数量:',
             	labelAlign:'right',
				allowBlank:false,
             	id: 'f_sell4number'
			},
			{
		        fieldLabel: '卖五报价:',
             	labelAlign:'right',
				allowBlank:false,
             	id: 'f_sell5price'
			},
			{
		        fieldLabel: '卖五数量:',
             	labelAlign:'right',
				allowBlank:false,
             	id: 'f_sell5number'
			}
		]	
	});
	
	//设定初始数据
	if(defaultData){
		showForm.getComponent('f_sinaseq').setValue(defaultData.get('sinaseq'));
		showForm.getComponent('f_stockcode').setValue(defaultData.get('stockcode'));
		showForm.getComponent('f_stockname').setValue(defaultData.get('stockname'));
		showForm.getComponent('f_stockexchangecode').setValue(defaultData.get('stockexchangecode'));
		showForm.getComponent('f_openprice').setValue(defaultData.get('openprice'));
		showForm.getComponent('f_lastcloseprice').setValue(defaultData.get('lastcloseprice'));
		showForm.getComponent('f_currentprice').setValue(defaultData.get('currentprice'));
		showForm.getComponent('f_topprice').setValue(defaultData.get('topprice'));
		showForm.getComponent('f_bottomprice').setValue(defaultData.get('bottomprice'));
		showForm.getComponent('f_buyprice').setValue(defaultData.get('buyprice'));
		showForm.getComponent('f_sellprice').setValue(defaultData.get('sellprice'));
		showForm.getComponent('f_transactionamount').setValue(defaultData.get('transactionamount'));
		showForm.getComponent('f_transactionnumber').setValue(defaultData.get('transactionnumber'));
		showForm.getComponent('f_buy1price').setValue(defaultData.get('buy1price'));
		showForm.getComponent('f_buy1number').setValue(defaultData.get('buy1number'));
		showForm.getComponent('f_buy2price').setValue(defaultData.get('buy2price'));
		showForm.getComponent('f_buy2number').setValue(defaultData.get('buy2number'));
		showForm.getComponent('f_buy3price').setValue(defaultData.get('buy3price'));
		showForm.getComponent('f_buy3number').setValue(defaultData.get('buy3number'));
		showForm.getComponent('f_buy4price').setValue(defaultData.get('buy4price'));
		showForm.getComponent('f_buy4number').setValue(defaultData.get('buy4number'));
		showForm.getComponent('f_buy5price').setValue(defaultData.get('buy5price'));
		showForm.getComponent('f_buy5number').setValue(defaultData.get('buy5number'));
		showForm.getComponent('f_sell1price').setValue(defaultData.get('sell1price'));
		showForm.getComponent('f_sell1number').setValue(defaultData.get('sell1number'));
		showForm.getComponent('f_sell2price').setValue(defaultData.get('sell2price'));
		showForm.getComponent('f_sell2number').setValue(defaultData.get('sell2number'));
		showForm.getComponent('f_sell3price').setValue(defaultData.get('sell3price'));
		showForm.getComponent('f_sell3number').setValue(defaultData.get('sell3number'));
		showForm.getComponent('f_sell4price').setValue(defaultData.get('sell4price'));
		showForm.getComponent('f_sell4number').setValue(defaultData.get('sell4number'));
		showForm.getComponent('f_sell5price').setValue(defaultData.get('sell5price'));
		showForm.getComponent('f_sell5number').setValue(defaultData.get('sell5number'));
		showForm.getComponent('f_dataday').setValue(defaultData.get('dataday'));
		showForm.getComponent('f_datatime').setValue(defaultData.get('datatime'));

	}
	else{
	}
	
	var okButton = Ext.create('Ext.Button', {
		text: '确定',
		handler:function(button, e, eOpts ){
			
			//验证输入的内容是否合法等，这个是表单的基本操作  
            if (!showForm.isValid()) {  
               Ext.Msg.alert("提示","您输入的信息有误,请重新输入...");  
               return false;  
           }  
			
			//数据取值要对应数据类型
			var sinaseq = showForm.getComponent("f_sinaseq").getValue("");
			var stockcode = showForm.getComponent("f_stockcode").getValue("");
			var stockname = showForm.getComponent("f_stockname").getValue("");
			var stockexchangecode = showForm.getComponent("f_stockexchangecode").getValue("");
			var openprice = showForm.getComponent("f_openprice").getValue("");
			var lastcloseprice = showForm.getComponent("f_lastcloseprice").getValue("");
			var currentprice = showForm.getComponent("f_currentprice").getValue("");
			var topprice = showForm.getComponent("f_topprice").getValue("");
			var bottomprice = showForm.getComponent("f_bottomprice").getValue("");
			var buyprice = showForm.getComponent("f_buyprice").getValue("");
			var sellprice = showForm.getComponent("f_sellprice").getValue("");
			var transactionamount = showForm.getComponent("f_transactionamount").getValue("");
			var transactionnumber = showForm.getComponent("f_transactionnumber").getValue("");
			var buy1price = showForm.getComponent("f_buy1price").getValue("");
			var buy1number = showForm.getComponent("f_buy1number").getValue("");
			var buy2price = showForm.getComponent("f_buy2price").getValue("");
			var buy2number = showForm.getComponent("f_buy2number").getValue("");
			var buy3price = showForm.getComponent("f_buy3price").getValue("");
			var buy3number = showForm.getComponent("f_buy3number").getValue("");
			var buy4price = showForm.getComponent("f_buy4price").getValue("");
			var buy4number = showForm.getComponent("f_buy4number").getValue("");
			var buy5price = showForm.getComponent("f_buy5price").getValue("");
			var buy5number = showForm.getComponent("f_buy5number").getValue("");
			var sell1price = showForm.getComponent("f_sell1price").getValue("");
			var sell1number = showForm.getComponent("f_sell1number").getValue("");
			var sell2price = showForm.getComponent("f_sell2price").getValue("");
			var sell2number = showForm.getComponent("f_sell2number").getValue("");
			var sell3price = showForm.getComponent("f_sell3price").getValue("");
			var sell3number = showForm.getComponent("f_sell3number").getValue("");
			var sell4price = showForm.getComponent("f_sell4price").getValue("");
			var sell4number = showForm.getComponent("f_sell4number").getValue("");
			var sell5price = showForm.getComponent("f_sell5price").getValue("");
			var sell5number = showForm.getComponent("f_sell5number").getValue("");
			var dataday = showForm.getComponent("f_dataday").getValue("");
			var datatime = showForm.getComponent("f_datatime").getValue("");
   
       		
       		//Ext.MessageBox.wait("载入中...", "提示", {   
            //   interval: 100       //进度条加载速度   
            //}); 
      
           
            Ext.Ajax.request({  
            	url:okAction,
            	method : 'POST',
            	params: {  
                     		"sinaseq": sinaseq,
                     		"stockcode": stockcode,
                     		"stockname": stockname,
                     		"stockexchangecode": stockexchangecode,
                     		"openprice": openprice,
                     		"lastcloseprice": lastcloseprice,
                     		"currentprice": currentprice,
                     		"topprice": topprice,
                     		"bottomprice": bottomprice,
                     		"buyprice": buyprice,
                     		"sellprice": sellprice,
                     		"transactionamount": transactionamount,
                     		"transactionnumber": transactionnumber,
                     		"buy1price": buy1price,
                     		"buy1number": buy1number,
                     		"buy2price": buy2price,
                     		"buy2number": buy2number,
                     		"buy3price": buy3price,
                     		"buy3number": buy3number,
                     		"buy4price": buy4price,
                     		"buy4number": buy4number,
                     		"buy5price": buy5price,
                     		"buy5number": buy5number,
                     		"sell1price": sell1price,
                     		"sell1number": sell1number,
                     		"sell2price": sell2price,
                     		"sell2number": sell2number,
                     		"sell3price": sell3price,
                     		"sell3number": sell3number,
                     		"sell4price": sell4price,
                     		"sell4number": sell4number,
                     		"sell5price": sell5price,
                     		"sell5number": sell5number,
                     		"dataday": dataday,
                     		"datatime": datatime 
                 		},
                 		success: function (response, option) {
                 		
                  		var success = Ext.decode(response.responseText).success;
                  		var message = Ext.decode(response.responseText).message;
                  		
                  		if (success) {    
                   		Ext.Msg.alert("提示", "处理成功！");    
		            	result.destroy(); 
		            	thisStore.load();
	            	}
	            	else{
	            		Ext.Msg.alert("提示", "处理失败！\r\n" + message);  
	            	}
	            },
	            failure: function () {
	            	Ext.Msg.alert("提示", "处理失败，请稍后再试！");   
	            }
            });
		
		}
	
	});
	
	if(okAction){
	}
	else{
		okButton.setVisible(false);
	}
	
	var result = new Ext.Window({
		requires: [
        'Ext.form.*'
   		],
		title: title,
		width:600,
		layout:'form',
		modal:true,
		resizable: false,
		msgTarget:'under',
		items:[
				showForm
		],
		buttons:
		[
			okButton,
			{
				xtype: 'button',
				text: '关闭',
				handler: function(){ result.destroy(); }
			}
		]
	
	});
	
	return result;
}


/*----------------- 创建Grid表 -----------------*/
//1.添加修改显示值的处理
var listGrid = Ext.create('Ext.grid.Panel',{
                    store:thisStore,
                    //height:gridHeight,
                    //forceFit: true, //让grid的列自动填满grid的整个宽度，不用一列一列的设定宽度。
                    anchor:'100% -56',
                    autoScroll:true,
                  	viewConfig: {
			            emptyText: '没有记录'
			        },
                    columns:
                    [
                    	{
                            xtype: 'gridcolumn',
                            dataIndex: 'sinaseq',
                            text: '流水号',
                            width:80
                        },
                    	{
                            xtype: 'gridcolumn',
                            dataIndex: 'stockcode',
                            text: '股票代码',
                            width:80
                        },
                    	{
                            xtype: 'gridcolumn',
                            dataIndex: 'stockname',
                            text: '股票名称',
                            width:80
                        },
                    	{
                            xtype: 'gridcolumn',
                            dataIndex: 'stockexchangecode',
                            text: '所属交易所编号',
                            width:80
                        }
                        ,
                    	{
                            xtype: 'gridcolumn',
                            dataIndex: 'dataday',
                            text: '数据日期',
                            width:80
                        },
                    	{
                            xtype: 'gridcolumn',
                            dataIndex: 'datatime',
                            text: '数据时间',
                            width:80
                        },
                        {
                            xtype: 'gridcolumn',
                            dataIndex: 'currentprice',
                            text: '收盘价格',
                            width:80
                        }
                        ,
                    	{
                            xtype: 'gridcolumn',
                            dataIndex: 'ma5',
                            text: 'MA5',
                            width:80
                        }
                         ,
                    	{
                            xtype: 'gridcolumn',
                            dataIndex: 'ma10',
                            text: 'MA10',
                            width:80
                        }
                        ,
                    	{
                            xtype: 'gridcolumn',
                            dataIndex: 'ma20',
                            text: 'MA20',
                            width:80
                        }
                         ,
                    	{
                            xtype: 'gridcolumn',
                            dataIndex: 'ma30',
                            text: 'MA30',
                            width:80
                        }
                         ,
                    	{
                            xtype: 'gridcolumn',
                            dataIndex: 'ma60',
                            text: 'MA60',
                            width:80
                        }
                         ,
                    	{
                            xtype: 'gridcolumn',
                            dataIndex: 'ma120',
                            text: 'MA120',
                            width:80
                        }
                        ,
                    	{
                            xtype: 'gridcolumn',
                            dataIndex: 'ma240',
                            text: 'MA240',
                            width:80
                        }
                        ,
                    	{
                            xtype: 'gridcolumn',
                            dataIndex: 'openprice',
                            text: '开盘价',
                            width:80
                        },
                    	{
                            xtype: 'gridcolumn',
                            dataIndex: 'lastcloseprice',
                            text: '昨日收盘价',
                            width:80
                        },
                    	{
                            xtype: 'gridcolumn',
                            dataIndex: 'currentprice',
                            text: '当前价格',
                            width:80
                        },
                    	{
                            xtype: 'gridcolumn',
                            dataIndex: 'topprice',
                            text: '今日最高价',
                            width:80
                        },
                    	{
                            xtype: 'gridcolumn',
                            dataIndex: 'bottomprice',
                            text: '今日最低价',
                            width:80
                        },
                    	{
                            xtype: 'gridcolumn',
                            dataIndex: 'buyprice',
                            text: '竞买价',
                            width:80
                        },
                    	{
                            xtype: 'gridcolumn',
                            dataIndex: 'sellprice',
                            text: '竞卖价',
                            width:80
                        },
                    	{
                            xtype: 'gridcolumn',
                            dataIndex: 'transactionamount',
                            text: '成交金额',
                            width:80
                        },
                    	{
                            xtype: 'gridcolumn',
                            dataIndex: 'transactionnumber',
                            text: '成交股票数',
                            width:80
                        },
                    	{
                            xtype: 'gridcolumn',
                            dataIndex: 'buy1price',
                            text: '买一报价',
                            width:80
                        },
                    	{
                            xtype: 'gridcolumn',
                            dataIndex: 'buy1number',
                            text: '买一数量',
                            width:80
                        },
                    	{
                            xtype: 'gridcolumn',
                            dataIndex: 'buy2price',
                            text: '买二报价',
                            width:80
                        },
                    	{
                            xtype: 'gridcolumn',
                            dataIndex: 'buy2number',
                            text: '买二数量',
                            width:80
                        },
                    	{
                            xtype: 'gridcolumn',
                            dataIndex: 'buy3price',
                            text: '买三报价',
                            width:80
                        },
                    	{
                            xtype: 'gridcolumn',
                            dataIndex: 'buy3number',
                            text: '买三数量',
                            width:80
                        },
                    	{
                            xtype: 'gridcolumn',
                            dataIndex: 'buy4price',
                            text: '买四报价',
                            width:80
                        },
                    	{
                            xtype: 'gridcolumn',
                            dataIndex: 'buy4number',
                            text: '买四数量',
                            width:80
                        },
                    	{
                            xtype: 'gridcolumn',
                            dataIndex: 'buy5price',
                            text: '买五报价',
                            width:80
                        },
                    	{
                            xtype: 'gridcolumn',
                            dataIndex: 'buy5number',
                            text: '买五数量',
                            width:80
                        },
                    	{
                            xtype: 'gridcolumn',
                            dataIndex: 'sell1price',
                            text: '卖一报价',
                            width:80
                        },
                    	{
                            xtype: 'gridcolumn',
                            dataIndex: 'sell1number',
                            text: '卖一数量',
                            width:80
                        },
                    	{
                            xtype: 'gridcolumn',
                            dataIndex: 'sell2price',
                            text: '卖二报价',
                            width:80
                        },
                    	{
                            xtype: 'gridcolumn',
                            dataIndex: 'sell2number',
                            text: '卖二数量',
                            width:80
                        },
                    	{
                            xtype: 'gridcolumn',
                            dataIndex: 'sell3price',
                            text: '卖三报价',
                            width:80
                        },
                    	{
                            xtype: 'gridcolumn',
                            dataIndex: 'sell3number',
                            text: '卖三数量',
                            width:80
                        },
                    	{
                            xtype: 'gridcolumn',
                            dataIndex: 'sell4price',
                            text: '卖四报价',
                            width:80
                        },
                    	{
                            xtype: 'gridcolumn',
                            dataIndex: 'sell4number',
                            text: '卖四数量',
                            width:80
                        },
                    	{
                            xtype: 'gridcolumn',
                            dataIndex: 'sell5price',
                            text: '卖五报价',
                            width:80
                        },
                    	{
                            xtype: 'gridcolumn',
                            dataIndex: 'sell5number',
                            text: '卖五数量',
                            width:80
                        }
                    ],
                    selModel: Ext.create('Ext.selection.CheckboxModel', {}),
                    listeners:{
                    	'itemdblclick' : viewGridItem
                    },
                    dockedItems: [
                    	{
						    xtype: 'pagingtoolbar',
						    store: thisStore,   // same store GridPanel is using
						    dock: 'bottom',
						    displayInfo: true
						}
					]
});

// 格式化是否发送显示,这是个 renderer, 渲染器
function formatOPTEnable(value) {
	return (value == 1) ? "发送" : "";
}

function dateFormat(value){ 
    if(null != value){ 
        return Ext.Date.format(new Date(value),'Y-m-d H:i:s'); 
    }else{ 
        return null; 
    } 
} 

function dateFormatEx(value, format){ 
    if(null != value){ 
        return Ext.Date.format(new Date(value),format); 
    }else{ 
        return null; 
    } 
} 

/*----------------- 页面初始化处理 -----------------*/

Ext.onReady(function() {

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
	
	//页面第一次读入数据
	thisStore.load({
	    params:{
	        start:0,
	        limit: itemsPerPage
	    }
	});
});

  
  	</script>
  </body>
</html>



