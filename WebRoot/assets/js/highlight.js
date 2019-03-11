/**
 * Created by suking @ 2017-09-22
 */
function showMenu(_objA){
	var eleUL = _objA.parent().parent();
	if(eleUL.hasClass("menu-ul")){
		//清除样式
		_objA.parent('li').siblings().each(function () {
            if ($(this).hasClass('active')) {
                $(this).removeClass('active')
                $(this).find('.active').each(function () {
                    if(this.tagName == "UL"){
                        $(this).hide()
                    }
                    $(this).removeClass('active')
                });
            }
        });
		//添加样式
		_objA.parent('li').addClass('active')
		_objA.parent('li').children('ul').addClass('active');
		_objA.parent('li').children('ul').show();
	}else if(eleUL.hasClass("menu-ul-second")){
		//清除样式
		_objA.parent('li').parent('ul').parent('li').siblings().each(function () {
            if ($(this).hasClass('active')) {
                $(this).removeClass('active')
                $(this).find('.active').each(function () {
                    if(this.tagName == "UL"){
                        $(this).hide()
                    }
                    $(this).removeClass('active')
                });
            }
        });
		_objA.parent('li').siblings().each(function () {
            if ($(this).hasClass('active')) {
                $(this).removeClass('active')
                $(this).find('.active').each(function () {
                    if(this.tagName == "UL"){
                        $(this).hide()
                    }
                    $(this).removeClass('active')
                });
            }
        });
		//添加样式
		_objA.parent().parent().parent().addClass('active')
		_objA.parent('li').children('ul').addClass('active');
		_objA.parent('li').children('ul').show();
		
		_objA.parent('li').addClass('active')
		eleUL.addClass('active');
		eleUL.show();
	}
}

function url(url) {
	// 注：如果没有设置cookie的有效期，则cookie默认在浏览器关闭前都有效，故被称为"会话cookie"。
	var curPath = window.location.href;
	//var index = curPath.indexOf("?");
	/*if(index == -1)*/
		curPath = curPath.substring(curPath.indexOf("/exam") , curPath.length);
	/*else 
		curPath = curPath.substring(curPath.indexOf("/exam") , curPath.indexOf("?"));*/
	$.cookie('highlight_url', curPath);
	location.href=url;
}

//手动确定左菜单
function urlpmenu(url, purl) {
	// 注：如果没有设置cookie的有效期，则cookie默认在浏览器关闭前都有效，故被称为"会话cookie"。
	$.cookie('highlight_url', purl);
	location.href=url;
}

$(function(){
	//var curPath = window.location.pathname;
	var curPath = window.location.href;
	curPath = curPath.substring(curPath.indexOf("/exam") , curPath.length);
	var isFinded = false;
	$(".left-menu-div ul>li>a").each(function(){
		var aurl = $(this).attr("href");
		if(aurl == curPath){
			isFinded = true;
			showMenu($(this));
		}
	});
	
	if(!isFinded){//解决编辑添加等的页面url再菜单栏中无对应项的情况，默认使用上一个菜单的对应项
		var curPath = $.cookie('highlight_url');
		console.log(curPath+"----------curPath");
		$(".left-menu-div ul>li>a").each(function(){
			var aurl = $(this).attr("href");
			/*console.log("Element href:" + aurl);
			console.log("Current Page path:" + curPath);*/
			if(aurl == curPath){
				showMenu($(this));
			}
		});
	}
	
});