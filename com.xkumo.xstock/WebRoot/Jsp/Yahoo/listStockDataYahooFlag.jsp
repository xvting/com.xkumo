<!-- 
页面整体说明区域

创建时间：2014-08-07 16:59:23
生成配置：<root><Page title="StockDataYahooFlag" id="listStockDataYahooFlag" /><Model><field title="序列号" key="true" dataType="int">seq</field><field title="股票编号" search="true" dataType="string">stockcode</field><field title="股票名称" search="true" dataType="string">stockname</field><field title="股票交易所编号" dataType="string">stockexchangecode</field><field title="数据下载次数" search="true" dataType="string">dataloadcount</field></Model><Store ProxyUrl="listStockDataYahooFlag.action" /><Add RequestUrl="addStockDataYahooFlag.action" /><Edit RequestUrl="updateStockDataYahooFlag.action" /><Delete RequestUrl="deleteStockDataYahooFlagForID.action"></Delete><DeleteAll RequestUrl="deleteStockDataYahooFlag.action" /></root>
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
    
    <title>StockDataYahooFlag</title>
    
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
Ext.define('listStockDataYahooFlag.baseModel', {
    extend: 'Ext.data.Model',
    requires: [
        'Ext.data.Field'
    ],

    fields: [
     { name: 'seq',type:'int' },   //序列号
     { name: 'stockcode',type:'string' },   //股票编号
     { name: 'stockname',type:'string' },   //股票名称
     { name: 'stockexchangecode',type:'string' },   //股票交易所编号
     { name: 'dataloadcount',type:'string' }   //数据下载次数

    ],
   
    idProperty : 'seq'
});

/*----------------- 定义数据Store区域 -----------------*/
var thisStore = Ext.create('Ext.data.Store', {
   	model:'listStockDataYahooFlag.baseModel',
	autoLoad:false,
	pageSize:itemsPerPage,
	proxy:{
		type:'ajax',
		url:'listStockDataYahooFlag.action',
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
				s_stockcode : Ext.getCmp('s_stockcode').getValue(),   //检索项目：股票编号
				s_stockname : Ext.getCmp('s_stockname').getValue(),   //检索项目：股票名称
				s_dataloadcount : Ext.getCmp('s_dataloadcount').getValue()   //检索项目：数据下载次数
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
		        fieldLabel: '股票编号:',
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
		        xtype:'textfield',
		        fieldLabel: '数据下载次数:',
             	labelAlign:'right',
             	id: 's_dataloadcount'
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
         		{
					text:'删除',
					icon:iconDelete,
					handler : deleteGridItem
         		},
         		{
					text:'全部删除',
					icon:iconDeleteAll,
					handler : deleteGridAllItem
         		}
         		,
         		{
					text:'更新股票信息',
					icon:iconRefresh,
					handler : updateStockInfoYahooFlag
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
	var defaultNewRecord = new  listStockDataYahooFlag.baseModel();
	var addWindow = CreateDetailWindow('新建','addStockDataYahooFlag.action', defaultNewRecord);	
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
        	var editWindow = CreateDetailWindow('编辑','updateStockDataYahooFlag.action', selectedEditItem);
        	
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
        				var deleteID = item.data.seq;
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
	        			 	url:'deleteStockDataYahooFlagForID.action',
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
	        			 	url:'deleteStockDataYahooFlagAll.action',
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
function updateStockInfoYahooFlag() { 
	
	Ext.Msg.confirm("警告", "确定要获得更新当前股票数据吗？", 
			function (button) {
    			if (button == "yes") 
    			{
    				Ext.Ajax.request(
	        			{
	        			 	url:'updateStockInfoYahooFlag.action',
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

/*创建数据窗口
title 标题
okAction 确定/保存 按钮的服务器端处理
defaultData 默认值
*/
function CreateDetailWindow(title,okAction, defaultData)
{
	var showForm = Ext.create('Ext.form.Panel',{
		frame:true,
		bodyStyle:'padding:5px 5px 0',
		defaultType: 'textfield',
		labelAlign:'right',
		labelWidth: 65, 
		id: 'formWindow',
		defaults: {allowBlank: true,width: 280}, 
		//要修改成对应数据类型的控件，默认都为TextField
		items:[
			{
		        fieldLabel: '序列号:',
             	labelAlign:'right',
				allowBlank:false,
             	id: 'f_seq'
			},
			{
		        fieldLabel: '股票编号:',
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
		        fieldLabel: '股票交易所编号:',
             	labelAlign:'right',
				allowBlank:false,
             	id: 'f_stockexchangecode'
			},
			{
		        fieldLabel: '数据下载次数:',
             	labelAlign:'right',
				allowBlank:false,
             	id: 'f_dataloadcount'
			}
		]	
	});
	
	//设定初始数据
	if(defaultData){
		showForm.getComponent('f_seq').setValue(defaultData.get('seq'));
		showForm.getComponent('f_stockcode').setValue(defaultData.get('stockcode'));
		showForm.getComponent('f_stockname').setValue(defaultData.get('stockname'));
		showForm.getComponent('f_stockexchangecode').setValue(defaultData.get('stockexchangecode'));
		showForm.getComponent('f_dataloadcount').setValue(defaultData.get('dataloadcount'));

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
			var seq = showForm.getComponent("f_seq").getValue("");
			var stockcode = showForm.getComponent("f_stockcode").getValue("");
			var stockname = showForm.getComponent("f_stockname").getValue("");
			var stockexchangecode = showForm.getComponent("f_stockexchangecode").getValue("");
			var dataloadcount = showForm.getComponent("f_dataloadcount").getValue("");
   
       		
       		//Ext.MessageBox.wait("载入中...", "提示", {   
            //   interval: 100       //进度条加载速度   
            //}); 
      
           
            Ext.Ajax.request({  
            	url:okAction,
            	method : 'POST',
            	params: {  
                     		"seq": seq,
                     		"stockcode": stockcode,
                     		"stockname": stockname,
                     		"stockexchangecode": stockexchangecode,
                     		"dataloadcount": dataloadcount 
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
                            dataIndex: 'seq',
                            text: '序列号',
                            width:80
                        },
                    	{
                            xtype: 'gridcolumn',
                            dataIndex: 'stockcode',
                            text: '股票编号',
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
                            text: '股票交易所编号',
                            width:120
                        },
                    	{
                            xtype: 'gridcolumn',
                            dataIndex: 'dataloadcount',
                            text: '数据下载次数',
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



