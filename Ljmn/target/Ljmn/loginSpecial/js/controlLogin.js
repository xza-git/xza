// \lkj20180323
var canGetCookie = 1;//是否支持存储Cookie 0 不支持 1 支持
var ajaxmockjax = 0;//是否启用虚拟Ajax的请求响 0 不启用  1 启用
$(document).keypress(function (e) {
    // 回车键事件
    if (e.which == 13) {
        $('input[type="button"]').click();
    }
});
//粒子背景特效
$('body').particleground({
    dotColor: '#E8DFE8',
    lineColor: '#1b3273'
});
$('input[name="pwd"]').focus(function () {
    $(this).attr('type', 'password');
});
$('input[type="text"]').focus(function () {
    $(this).prev().animate({ 'opacity': '1' }, 200);
});
$('input[type="text"],input[type="password"]').blur(function () {
    $(this).prev().animate({ 'opacity': '.5' }, 200);
});
$('input[name="login"],input[name="pwd"]').keyup(function () {
    var Len = $(this).val().length;
    if (!$(this).val() == '' && Len >= 5) {
        $(this).next().animate({
            'opacity': '1',
            'right': '30'
        }, 200);
    } else {
        $(this).next().animate({
            'opacity': '0',
            'right': '20'
        }, 200);
    }
});
var open = 0;
layui.use('layer', function () {
    //非空验证
    $('input[type="button"]').click(function () {
        var login = $('.username').val();
        var pwd = $('.passwordNumder').val();
        if (login == '') {
            ErroAlert('请输入您的账号');
            return false;
        } else if (pwd == '') {

            ErroAlert('请输入密码');
            return false;
        } else {
        	//全屏
            fullscreen();
            //加载动态效果
            $('.login').addClass('test'); //倾斜特效
            setTimeout(function () {
                $('.login').addClass('testtwo'); //平移特效
            }, 300);
            setTimeout(function () {
                $('.authent').show().animate({ right: -320 }, {
                    easing: 'easeOutQuint',
                    duration: 200,
                    queue: false
                });
                $('.authent').animate({ opacity: 1 }, {
                    duration: 600,
                    queue: false
                }).addClass('visible');
            }, 500);

            //登陆
            //此处做为ajax内部判断
            $.ajax({
            	//登录请求
    			url:"user/login",
    			type:"post",
    			//传递参数
    			data:{"userName": login, "userPwd": pwd},
    			dataType:"json",
    			success:function(data){
    				 setTimeout(function () {
    					 //再次加载动态效果
                         console.log(data)
                         $('.authent').show().animate({ right: 90 }, {
                             easing: 'easeOutQuint',
                             duration: 600,
                             queue: false
                         });
                         $('.authent').animate({ opacity: 0 }, {
                             duration: 200,
                             queue: false
                         }).addClass('visible');
                         $('.login').removeClass('testtwo'); //平移特效
                     }, 2000);
                     setTimeout(function () {
                         $('.authent').hide();
                         $('.login').removeClass('test');
                         if(data.success=="1"){
                        	 $('.login div').fadeOut(100);
                             $('.success').fadeIn(1000);
                             $('.success').html(data.Text);
                             //回调函数 登录成功之后跳转
         					location.href="user/toMain";
         				}if(data.success=="2"){
         					alert("用户名或密码错误")
         					window.location.reload();
         				}
                     }, 2400);
    			},
    			error:function(data){
    				alert("请求失败！");
    			}
    		})
        }
        return false;
    })
})
//全屏幕
var fullscreen = function () {
    elem = document.body;
    if (elem.webkitRequestFullScreen) {
        elem.webkitRequestFullScreen();
    } else if (elem.mozRequestFullScreen) {
        elem.mozRequestFullScreen();
    } else if (elem.requestFullScreen) {
        elem.requestFullscreen();
    } else {
        //浏览器不支持全屏API或已被禁用
    }
}
if(ajaxmockjax == 1){
    $.mockjax({
        url: 'Ajax/Login',
        status: 200,
        responseTime: 50,
        responseText: {"Status":"ok","Text":"登陆成功<br /><br />欢迎回来"}
    });
    $.mockjax({
        url: 'Ajax/LoginFalse',
        status: 200,
        responseTime: 50,
        responseText: {"Status":"Erro","Erro":"账号名或密码或验证码有误"}
    });
}
