<!-- 
页面整体说明区域

创建时间：2014-06-19 21:41:35
生成配置：<root><Page title="Todo" id="listTodo" /><Model><field title="序列号" key="true">todoseq</field><field title="名称" search="true">todoname</field><field title="内容">todoremark</field><field title="所属组" search="true">todogroupid</field><field title="级别" search="true">todolevel</field><field title="已解决" search="true">isresolved</field><field title="计划解决时间">planresolvedtime</field><field title="实际解决时间">actualresolvedtime</field></Model><Store ProxyUrl="listTodo.action" /><Add RequestUrl="addTodo.action" /><Edit RequestUrl="updateTodo.action" /><Delete RequestUrl="deleteTodoForID.action"></Delete><DeleteAll RequestUrl="deleteTodo.action" /></root>
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
    
    <title>Todo</title>
    
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


/*--------------------- Todo组编码 -------------------------*/
Ext.define('listTodoGroup.baseModel', {
    extend: 'Ext.data.Model',
    requires: [
        'Ext.data.Field'
    ],

    fields: [
     { name: 'todogroupid' },   //组ID
     { name: 'todogroupname' },   //组名
     { name: 'todogroupremark' }   //说明

    ],
   
    idProperty : 'todogroupid'
});

var todoGroupStore = Ext.create('Ext.data.Store', {
   	model:'listTodoGroup.baseModel',
	autoLoad:false,
	pageSize:100,
	proxy:{
		type:'ajax',
		url:'listTodoGroup.action',
		reader:{
			type:'json',
			root:'root',
			totalProperty: 'total'
		}
	}
});

/*------------------------------- 任务优先级 -----------------------------*/
var levelStore = Ext.create('Ext.data.Store', {
    fields: ['code', 'name'],
    data : [
        {"code":"1", "name":"优先级 1"},
        {"code":"2", "name":"优先级 2"},
        {"code":"3", "name":"优先级 3"},
        {"code":"4", "name":"优先级 4"},
        {"code":"5", "name":"优先级 5"}
        //...
    ]
});


/*------------------------------- 是否解决 -----------------------------*/
var isResolvedStore = Ext.create('Ext.data.Store', {
    fields: ['code', 'name'],
    data : [
        {"code":true, "name":"是"},
        {"code":false, "name":"否"}
        //...
    ]
});

/*----------------- 页面变量配置区域 -----------------*/
var itemsPerPage = 20;			//Grid的每页显示行数
var gridHeight = 475;	//Grid的高度


/*----------------- 定义Model区域 -----------------*/
//1.要添加Model的idProperty， 如：idProperty : 'dataIdentity'
//2.要设定数据类型，默认值

/*
数据字段名
*/
Ext.define('listTodo.baseModel', {
    extend: 'Ext.data.Model',
    requires: [
        'Ext.data.Field'
    ],

    fields: [
     { name: 'todoseq' , type: 'int'},   //序列号
     { name: 'todoname', type: 'string'},   //名称
     { name: 'todoremark', type: 'string' },   //内容
     { name: 'todogroupid', type: 'string' },   //所属组
     { name: 'todolevel', type: 'string' },   //级别
     { name: 'isresolved', type: 'boolean' },   //已解决
     { name: 'planresolvedtime',type:'date' },   //计划解决时间
     { name: 'actualresolvedtime',type:'date' }   //实际解决时间

    ],
   
    idProperty : 'todoseq'
});

/*----------------- 定义数据Store区域 -----------------*/
var thisStore = Ext.create('Ext.data.Store', {
   	model:'listTodo.baseModel',
	autoLoad:false,
	pageSize:itemsPerPage,
	proxy:{
		type:'ajax',
		url:'listTodo.action',
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
				s_todoname : Ext.getCmp('s_todoname').getValue(),   //检索项目：名称
				s_todogroupid : Ext.getCmp('s_todogroupid').getValue(),   //检索项目：所属组
				s_todolevel : Ext.getCmp('s_todolevel').getValue(),   //检索项目：级别
				s_isresolved : Ext.getCmp('s_isresolved').getValue()   //检索项目：已解决
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
		        fieldLabel: '名称:',
             	labelAlign:'right',
             	id: 's_todoname'
			},
			{
		        fieldLabel: '所属组:',
		        xtype:'combobox',
		        store:todoGroupStore,
             	labelAlign:'right',
             	displayField:'todogroupname',
             	valueField:'todogroupid',
             	
             	labelWidth:80,
             	width:240,
				editable:true,
             	id: 's_todogroupid'
			},
			{
		        fieldLabel: '级别:',
		        xtype:'combobox',
		        store:levelStore,
             	labelAlign:'right',
             	displayField:'name',
             	valueField:'code',
             	labelWidth:80,
             	width:200,
				editable:true,
             	id: 's_todolevel'
			},
			{
		        fieldLabel: '已解决:',
		        xtype:'combobox',
		        store:isResolvedStore,
             	labelAlign:'right',
             	displayField:'name',
             	valueField:'code',
             	labelWidth:80,
             	width:200,
             	value:false,
				editable:true,
             	id: 's_isresolved'
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
         		}
         		/*,
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
         		*/
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
	var defaultNewRecord = new  listTodo.baseModel();
	var addWindow = CreateDetailWindow('新建','addTodo.action', defaultNewRecord);	
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
        	var editWindow = CreateDetailWindow('编辑','updateTodo.action', selectedEditItem);
        	
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
        				var deleteID = item.data.todoseq;
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
	        			 	url:'deleteTodoForID.action',
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
	        			 	url:'deleteTodo.action',
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
				allowBlank:true,
				hidden:true,
             	id: 'f_todoseq'
			},
			{
		        fieldLabel: '名称:',
             	labelAlign:'right',
				allowBlank:false,
				anchor: '100%',
             	id: 'f_todoname'
			},
			{
				xtype: 'textareafield',
				grow : true,
		        fieldLabel: '内容:',
             	labelAlign:'right',
				allowBlank:true,
				anchor: '100%',
             	id: 'f_todoremark'
			},
			{
				xtype:'combobox',
				store:todoGroupStore,
		        fieldLabel: '所属组:',
             	labelAlign:'right',
             	displayField:'todogroupname',
             	valueField:'todogroupid',
				allowBlank:true,
             	id: 'f_todogroupid'
			},
			{
				xtype:'combobox',
				store:levelStore,
		        fieldLabel: '级别:',
             	labelAlign:'right',
             	displayField:'name',
             	valueField:'code',
				allowBlank:true,
             	id: 'f_todolevel'
			},
			{
				xtype:'combobox',
				store:isResolvedStore,
		        fieldLabel: '已解决:',
             	labelAlign:'right',
             	displayField:'name',
             	valueField:'code',
				allowBlank:false,
				value:'false',
             	id: 'f_isresolved'
			},
			{
				xtype: 'datefield',
		        fieldLabel: '计划解决时间:',
             	labelAlign:'right',
             	format:'Y-m-d',
             	altFormats: 'Y-m-d',
				allowBlank:true,
             	id: 'f_planresolvedtime'
			},
			{
				xtype: 'datefield',
		        fieldLabel: '实际解决时间:',
             	labelAlign:'right',
				allowBlank:true,
				format:'Y-m-d',
             	altFormats: 'Y-m-d',
             	id: 'f_actualresolvedtime'
			}
		]	
	});
	
	//设定初始数据
	if(defaultData){
		showForm.getComponent('f_todoseq').setValue(defaultData.get('todoseq'));
		showForm.getComponent('f_todoname').setValue(defaultData.get('todoname'));
		showForm.getComponent('f_todoremark').setValue(defaultData.get('todoremark'));
		showForm.getComponent('f_todogroupid').setValue(defaultData.get('todogroupid'));
		showForm.getComponent('f_todolevel').setValue(defaultData.get('todolevel'));
		showForm.getComponent('f_isresolved').setValue(defaultData.get('isresolved'));
		showForm.getComponent('f_planresolvedtime').setValue(defaultData.get('planresolvedtime'));
		showForm.getComponent('f_actualresolvedtime').setValue(defaultData.get('actualresolvedtime'));

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
			var todoseq = showForm.getComponent("f_todoseq").getValue("");
			var todoname = showForm.getComponent("f_todoname").getValue("");
			var todoremark = showForm.getComponent("f_todoremark").getValue("");
			var todogroupid = showForm.getComponent("f_todogroupid").getValue("");
			var todolevel = showForm.getComponent("f_todolevel").getValue("");
			var isresolved = showForm.getComponent("f_isresolved").getValue("");
			var planresolvedtime = dateFormat(showForm.getComponent("f_planresolvedtime").getValue(""));
			var actualresolvedtime = dateFormat(showForm.getComponent("f_actualresolvedtime").getValue(""));
   
       		
       		//Ext.MessageBox.wait("载入中...", "提示", {   
            //   interval: 100       //进度条加载速度   
            //}); 
      
           
            Ext.Ajax.request({  
            	url:okAction,
            	method : 'POST',
            	params: {  
                     		"todoseq": todoseq,
                     		"todoname": todoname,
                     		"todoremark": todoremark,
                     		"todogroupid": todogroupid,
                     		"todolevel": todolevel,
                     		"isresolved": isresolved,
                     		"planresolvedtime": planresolvedtime,
                     		"actualresolvedtime": actualresolvedtime 
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
                            dataIndex: 'todoseq',
                            text: '序列号',
                            width:60
                        },
                        {
                            xtype: 'gridcolumn',
                            dataIndex: 'todogroupid',
                            renderer: rendererForTodoGroupName,
                            text: '所属组',
                            width:120
                        },
                        {
                            xtype: 'gridcolumn',
                            dataIndex: 'todolevel',
                            renderer: rendererForTodolevel,
                            text: '级别',
                            width:80
                        },
                    	{
                            xtype: 'gridcolumn',
                            dataIndex: 'todoname',
                            text: '名称',
                            width:160
                        },
                    	{
                            xtype: 'gridcolumn',
                            dataIndex: 'todoremark',
                            text: '内容',
                            width:400
                        },
                    	
                    	
                    	{
                            xtype: 'gridcolumn',
                            dataIndex: 'isresolved',
                            text: '已解决',
                            renderer: rendererForIsresolved,
                            width:80
                        },
                    	{
                            xtype: 'gridcolumn',
                            dataIndex: 'planresolvedtime',
                            renderer: rendererForDate,
                            text: '计划解决时间',
                            width:80
                        },
                    	{
                            xtype: 'gridcolumn',
                            dataIndex: 'actualresolvedtime',
                            renderer: rendererForDate,
                            text: '实际解决时间',
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

//Todo组的转换
function rendererForTodoGroupName(value){

	if(todoGroupStore && todoGroupStore.data)
	{
		for(i = 0;i < todoGroupStore.getCount();i++)
		{
			if(todoGroupStore.getAt(i).data.todogroupid == value)
			{
				
				return  todoGroupStore.getAt(i).data.todogroupname;
			}
		}
	}
	else
	{
		return value;
	}
}


//等级转换
function rendererForTodolevel(value){

	if(levelStore && levelStore.data)
	{
		for(i = 0;i < levelStore.getCount();i++)
		{
			if(levelStore.getAt(i).data.code == value)
			{
				return  levelStore.getAt(i).data.name;
			}
		}
	}
	else
	{
		return value;
	}
}

//rendererForIsresolved
function rendererForIsresolved(value){

	if(isResolvedStore && isResolvedStore.data)
	{
		for(i = 0;i < isResolvedStore.getCount();i++)
		{
				if(isResolvedStore.getAt(i).data.code == value)
				{
					return  isResolvedStore.getAt(i).data.name;
				}

		}
	}
	else
	{
		return value;
	}
}

//rendererForIsresolved
function rendererForDate(value){

	return dateFormatEx(value,'Y-m-d');
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
	
	todoGroupStore.load(
		{
			scope:this,	
			callback:function()
				{ 
					//页面第一次读入数据
					thisStore.load({
					    params:{
					        start:0,
					        limit: itemsPerPage
					    }
					});
				}
		}
	);
	
	
});

  
  	</script>
  </body>
</html>



