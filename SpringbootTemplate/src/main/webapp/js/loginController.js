var loginModule=angular.module("loginModule",[]);
/*
 *登陆url
 */
var url="";
loginModule.controller("loginController",function($scope){
	$scope.login=function(name,password){
		console.log(name,password);
		$.ajax({
			type:"get",
			async:true,
			url:"http://192.168.1.29:8080/Template-spring3Xml/loginController/login",
			data:"name="+name+"&password="+password,
			success:function(data){
				console.log(data);
				var jsonData=JSON.parse(data);
				if(jsonData.status==true){
					//每次携带此sessionId到服务器
					sessionStorage.setItem("sessionId",jsonData.sessionId);
					//获取用户
					var name=jsonData.currentUser.name;
					if(name=='zhangsan'){
						//跳转到首页
						window.location.href='index.html';
					}else{
						//重新登录
						alert(jsonData.msg);
						window.location.reload();
					}
				}else{
					//登录失败，重新登录
					alert(jsonData.msg);
					window.location.reload();
				}
			
				
			},
			error:function(err){
				console.log(err);
			}

		})
	}
})
loginModule.controller("registerController",function($scope){
	$scope.register=function(){
		//封装json用户对象
		var user={};
		user.name='zcm3';
		user.password='zcm3';
		user.address='潍坊';
		console.log(JSON.stringify(user));
		$.ajax({
			type:"post",
			async:true,
			url:"http://192.168.1.29:8080/Template-spring3Xml/userController/insertUser",
			contentType : "application/json; charset=utf-8",
			data:JSON.stringify(user),
			success:function(data){
				console.log(data);
				var jsonData=JSON.parse(data);
				
			},
			error:function(err){
				console.log(err);
			}

		})
	}
	$scope.testTranslateMap=function(){
		var condition={
			name:'zcm3',
			delFlag:0
		}
		console.log(condition);
		$.ajax({
			type:"post",
			async:true,
			url:"http://192.168.1.29:8080/Template-spring3Xml/userController/getUserByName",
			contentType : "application/json; charset=utf-8",
			data:JSON.stringify(condition),
			success:function(data){
				console.log(data);
				var jsonData=JSON.parse(data);
			},
			error:function(err){
				console.log(err);
			}
		})
	}
	$scope.getNameAndPassword=function(){
		var condition={
			name:'zcm3',
			delFlag:0
		}
		console.log(condition);
		$.ajax({
			type:"post",
			async:true,
			url:"http://192.168.1.29:8080/Template-spring3Xml/userController/getNameAndPassword",
			contentType : "application/json; charset=utf-8",
			data:JSON.stringify(condition),
			success:function(data){
				console.log(data);
				var jsonData=JSON.parse(data);
			},
			error:function(err){
				console.log(err);
			}
		})
	}

	
})