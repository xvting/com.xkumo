package com.xkumo.xstock.core;

public enum  TaskEnum {
	//通过括号赋值,而且必须带有一个参构造器和一个属性跟方法，否则编译出错
    //赋值必须都赋值或都不赋值，不能一部分赋值一部分不赋值；如果不赋值则不能写构造器，赋值编译也出错
	UpdateStockInfo("00001","更新股票基本信息"),
	ScheduleUpdateStockInfo("00002", "定时执行[更新股票基本信息]处理"),
	
	UpdateStockDataSina("00003","读取来自新浪接口的数据"),
	ScheduleUpdateStockDataSina("00004","定时执行[读取来自新浪接口的数据]处理"),
	
	UpdateStockDataYahoo("00005","读取来自雅虎接口的股票数据"),
	ScheduleUpdateStockDataYahoo("00006","定时执行[读取来自雅虎接口的股票数据]处理"),
	
	UpdateStockDataYahooFlag("00007", "更新雅虎股票数据下载标志"),
	ScheduleUpdateStockDataYahooFlag("00008", "定时执行[更新雅虎股票数据下载标志]处理"),	
	
	UpdateStockDataSinaBlock("00009","将新浪数据分析出上升下降区间块"),
	UpdateBonusStock("00010","更新当日除权的股票列表"),
	
	UpdateAG1512DataSina("00011", "读取来自新浪接口的AG1512数据"),
	ScheduleUpdateAG1512DataSina("00012", "定时执行[读取来自新浪接口的AG1512数据]");
	
	
    private final String id;
    private final String name;

    //构造器默认也只能是private, 从而保证构造函数只能在内部使用
    TaskEnum(String id,String name) {
        this.id = id;
        this.name = name;
    }
    
    public String getID() {
        return id;
    }
    
    public String getName() {
        return name;
    }
}
