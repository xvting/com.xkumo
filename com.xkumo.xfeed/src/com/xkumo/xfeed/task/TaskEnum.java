package com.xkumo.xfeed.task;

public enum  TaskEnum {
	//通过括号赋值,而且必须带有一个参构造器和一个属性跟方法，否则编译出错
    //赋值必须都赋值或都不赋值，不能一部分赋值一部分不赋值；如果不赋值则不能写构造器，赋值编译也出错
	UpdateDataTorrentKittySo("00001","更新基本信息TorrentKittySo"),
	UpdateDataTorrentKittySoIndex10musume("00002", "更新10musume的数据"),
	UpdateDataTorrentKittySoIndexSCute("00003", "更新SCute的数据"),
	UpdateDataTorrentKittySoIndexTokyoHot("00004", "更新Tokyo hot的数据"),
	UpdateDataTorrentKittySoIndexTV("00005", "更新影片电视的数据");
	
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
