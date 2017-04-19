var penoyModule=angular.module("penoyModule",[]);

penoyModule.controller("penoyController",function($scope){

	$scope.inttt='Test';
});
penoyModule.controller('bodyLeftController',function($scope,$rootScope){
	var items=[{name:'基础概览',eName:'base',imgNum:9},
	{name:'观赏牡丹',eName:'forViewPenoy',imgNum:132},
	{name:'观赏芍药',eName:'forViewShaoyao',imgNum:125},
	{name:'牡丹园',eName:'mudanyuan',imgNum:5},
	{name:'油用牡丹 ',eName:'oilPenoy',imgNum:7},
	{name:'油用牡丹种',eName:'oilSeed',imgNum:7},
	{name:'牡丹产品',eName:'product',imgNum:7},
	{name:'工程案例',eName:'projectCase',imgNum:8},
	{name:'圆盘牡丹',eName:'salverPenoy',imgNum:7},
	{name:'牡丹种苗',eName:'seed',imgNum:11},
	{name:'牡丹树木',eName:'tree',imgNum:18}
	];
	sessionStorage.setItem('penoyItems',JSON.stringify(items));
	$rootScope.items=items;

	//左侧点击事件
	$scope.selectAllByName=function(name,eName){
		var penoyItems=JSON.parse(sessionStorage.getItem('penoyItems'));
		console.log(787878787,penoyItems[0].eName);
		var itemsImgs=[];
		var numImg=0;
		for (var i = 0; i < penoyItems.length; i++) {
			if(penoyItems[i].eName==eName){
				numImg=penoyItems[i].imgNum;
			}
		}
		var tempNumImg=0;
		if(numImg>6){
			tempNumImg=6;
		}else{
			tempNumImg=numImg;
		}
		for (var i = 0; i < tempNumImg; i++) {
			itemsImgs[i]={'eName':eName,"index":i}
		}
		$rootScope.itemsImgs=itemsImgs;

		//分页插件
		var itemsPage=[];
		var numTempPage=numImg;
		var numPage=Math.floor(numTempPage/6);
		var page=0;
		var numMod=numTempPage%6;
		if(numMod==0){
			page=numPage;
		}else{
			page=numPage+1;
		}
		for (var i = 0; i < page; i++) {
			itemsPage[i]={'eName':eName,"index":i}
		}
		$rootScope.itemsPage=itemsPage;

	}
});

/**
	初始化时调用
**/

penoyModule.controller('bodyRightController',function($rootScope,$scope){
	var itemsImgs=[];
	var numImg=9;
	var tempNumImg=0;
	if(numImg>6){
		tempNumImg=6;
	}else{
		tempNumImg=numImg;
	}
	for (var i = 0; i < tempNumImg; i++) {
		itemsImgs[i]={'eName':'base',"index":i}
	}
	$rootScope.itemsImgs=itemsImgs;

		var itemsPage=[];
		var numTempPage=9;
		var numPage=Math.floor(numTempPage/6);
		var page=0;
		var numMod=numTempPage%6;
		if(numMod==0){
			page=numPage;
		}else{
			page=numPage+1;
		}
		for (var i = 0; i < page; i++) {
			itemsPage[i]={'eName':'base',"index":i}
		}
		$rootScope.itemsPage=itemsPage;


	$scope.go=function(eName,index){
		var num=0;
		var penoyItems=JSON.parse(sessionStorage.getItem('penoyItems'));
		for (var i = 0; i < penoyItems.length; i++) {
			if(penoyItems[i].eName==eName){
				num=penoyItems[i].imgNum;
			}
		}
		var itemsImgs=[];
		var numImg=num-(index-1)*6;
		var tempNumImg=0;
		if(numImg>6){
			tempNumImg=6;
		}else{
			tempNumImg=numImg;
		}
		for (var i = 0; i < tempNumImg; i++) {
			itemsImgs[i]={'eName':eName,'index':(index-1)+i}
		}
		$rootScope.itemsImgs=itemsImgs;
	}
});
