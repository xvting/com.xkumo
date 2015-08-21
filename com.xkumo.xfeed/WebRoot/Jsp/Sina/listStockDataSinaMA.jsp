<!-- 
页面整体说明区域

创建时间：2014-08-19 22:09:09
生成配置：<root><Page title="StockDataSinaMA" id="listStockDataSinaMA" /><Model><field title="序列号" key="true" dataType="int">seq</field><field title="股票编号" search="true" dataType="string">stockcode</field><field title="股票名称" search="true" dataType="string">stockname</field><field title="股票交易所编号" dataType="string">stockexchangecode</field><field title="日期" dataType="date">day</field><field title="MA5" dataType="float">ma5</field><field title="MA10" dataType="float">ma10</field><field title="MA30" dataType="float">ma30</field><field title="MA60" dataType="float">ma60</field><field title="MA120" dataType="float">ma120</field><field title="MA240" dataType="float">ma240</field></Model><Store ProxyUrl="listStockDataSinaMA.action" /><Add RequestUrl="addStockDataSinaMA.action" /><Edit RequestUrl="updateStockDataSinaMA.action" /><Delete RequestUrl="deleteStockDataSinaMAForID.action"></Delete><DeleteAll RequestUrl="deleteStockDataSinaMAAll.action" /></root>
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
    
    <title>StockDataSinaMA</title>
    
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

/*----------------- 页面变量配置区域 -----------------*/
var itemsPerPage = 20;			//Grid的每页显示行数
var gridHeight = 475;	//Grid的高度


/*----------------- 定义Model区域 -----------------*/
//1.要添加Model的idProperty， 如：idProperty : 'dataIdentity'
//2.要设定数据类型，默认值

/*
数据字段名
*/
Ext.define('listStockDataSinaMA.baseModel', {
    extend: 'Ext.data.Model',
    requires: [
        'Ext.data.Field'
    ],

    fields: [
     { name: 'seq',type:'int' },   //序列号
     { name: 'stockcode',type:'string' },   //股票编号
     { name: 'stockname',type:'string' },   //股票名称
     { name: 'stockexchangecode',type:'string' },   //股票交易所编号
     { name: 'day',type:'date' },   //日期
     { name: 'ma5',type:'float' },   //MA5
     { name: 'ma10',type:'float' },   //MA10
     { name: 'ma30',type:'float' },   //MA30
     { name: 'ma60',type:'float' },   //MA60
     { name: 'ma120',type:'float' },   //MA120
     { name: 'ma240',type:'float' }   //MA240

    ],
   
    idProperty : 'seq'
});

/*----------------- 定义数据Store区域 -----------------*/
var thisStore = Ext.create('Ext.data.Store', {
   	model:'listStockDataSinaMA.baseModel',
	autoLoad:false,
	pageSize:itemsPerPage,
	proxy:{
		type:'ajax',
		url:'listStockDataSinaMA.action',
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
	var defaultNewRecord = new  listStockDataSinaMA.baseModel();
	var addWindow = CreateDetailWindow('新建','addStockDataSinaMA.action', defaultNewRecord);	
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
        	var editWindow = CreateDetailWindow('编辑','updateStockDataSinaMA.action', selectedEditItem);
        	
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
	        			 	url:'deleteStockDataSinaMAForID.action',
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
	        			 	url:'deleteStockDataSinaMAAll.action',
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
		        fieldLabel: '日期:',
             	labelAlign:'right',
				allowBlank:false,
             	id: 'f_day'
			},
			{
		        fieldLabel: 'MA5:',
             	labelAlign:'right',
				allowBlank:false,
             	id: 'f_ma5'
			},
			{
		        fieldLabel: 'MA10:',
             	labelAlign:'right',
				allowBlank:false,
             	id: 'f_ma10'
			},
			{
		        fieldLabel: 'MA30:',
             	labelAlign:'right',
				allowBlank:false,
             	id: 'f_ma30'
			},
			{
		        fieldLabel: 'MA60:',
             	labelAlign:'right',
				allowBlank:false,
             	id: 'f_ma60'
			},
			{
		        fieldLabel: 'MA120:',
             	labelAlign:'right',
				allowBlank:false,
             	id: 'f_ma120'
			},
			{
		        fieldLabel: 'MA240:',
             	labelAlign:'right',
				allowBlank:false,
             	id: 'f_ma240'
			}
		]	
	});
	
	//设定初始数据
	if(defaultData){
		showForm.getComponent('f_seq').setValue(defaultData.get('seq'));
		showForm.getComponent('f_stockcode').setValue(defaultData.get('stockcode'));
		showForm.getComponent('f_stockname').setValue(defaultData.get('stockname'));
		showForm.getComponent('f_stockexchangecode').setValue(defaultData.get('stockexchangecode'));
		showForm.getComponent('f_day').setValue(defaultData.get('day'));
		showForm.getComponent('f_ma5').setValue(defaultData.get('ma5'));
		showForm.getComponent('f_ma10').setValue(defaultData.get('ma10'));
		showForm.getComponent('f_ma30').setValue(defaultData.get('ma30'));
		showForm.getComponent('f_ma60').setValue(defaultData.get('ma60'));
		showForm.getComponent('f_ma120').setValue(defaultData.get('ma120'));
		showForm.getComponent('f_ma240').setValue(defaultData.get('ma240'));

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
			var day = showForm.getComponent("f_day").getValue("");
			var ma5 = showForm.getComponent("f_ma5").getValue("");
			var ma10 = showForm.getComponent("f_ma10").getValue("");
			var ma30 = showForm.getComponent("f_ma30").getValue("");
			var ma60 = showForm.getComponent("f_ma60").getValue("");
			var ma120 = showForm.getComponent("f_ma120").getValue("");
			var ma240 = showForm.getComponent("f_ma240").getValue("");
   
       		
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
                     		"day": day,
                     		"ma5": ma5,
                     		"ma10": ma10,
                     		"ma30": ma30,
                     		"ma60": ma60,
                     		"ma120": ma120,
                     		"ma240": ma240 
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
                            width:80
                        },
                    	{
                            xtype: 'gridcolumn',
                            dataIndex: 'day',
                            text: '日期',
                            width:80
                        },
                    	{
                            xtype: 'gridcolumn',
                            dataIndex: 'ma5',
                            text: 'MA5',
                            width:80
                        },
                    	{
                            xtype: 'gridcolumn',
                            dataIndex: 'ma10',
                            text: 'MA10',
                            width:80
                        },
                    	{
                            xtype: 'gridcolumn',
                            dataIndex: 'ma30',
                            text: 'MA30',
                            width:80
                        },
                    	{
                            xtype: 'gridcolumn',
                            dataIndex: 'ma60',
                            text: 'MA60',
                            width:80
                        },
                    	{
                            xtype: 'gridcolumn',
                            dataIndex: 'ma120',
                            text: 'MA120',
                            width:80
                        },
                    	{
                            xtype: 'gridcolumn',
                            dataIndex: 'ma240',
                            text: 'MA240',
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



