'use strict'

//每页数目
var _perNum;
//总数
var _totalNum;
//当前开始索引
var _startNum;
//当前页结束索引
var _endNum;
//总页数
var _totalPages;
//当前页数
var _currentPage = 1;
//每次显示多少页码
var _pagePerNum = 8;
//页码开始数
var _startPage = 1;
//页码结束页
var _endPage;




function updatePageParams(page){
	_perNum=page.pernum;
	_totalPages=page.pages;
	_totalNum=page.total;
	_currentPage=page.currentPage-1;
	_startNum=page.start+1;
	_endNum=page.end;
	_startPage=(parseInt(_currentPage/_pagePerNum))*_pagePerNum+1;
	_endPage=_totalPages<=1?1:(parseInt(_currentPage/_pagePerNum+1))*_pagePerNum;
	console.log(_currentPage);
	console.log(_startPage);
	console.log(_endPage);

	if(_endPage>_totalPages){
	    _endPage=_totalPages
	}
	if(_totalNum<=0){
	        $("#page_currentNums").html("");
    		$("#page_totalPages").empty();
    		$("#page_totalPages").append("<li><a onclick=\"jumpToFirstPage()\">首页</a></li>");
    		$("#page_totalPages").append("<li><a onclick=\"jumpToPrePage()\"><上一页</a></li>");
    		$("#page_totalPages").append("<li><a >1</a></li>");
            $("#page_totalPages").append("<li><a onclick=\"jumpToNextPage()\">下一页></a></li>");
            $("#page_totalPages").append("<li><a onclick=\"jumpToLastPage\">尾页</a></li>");
	}else{
	        $("#page_currentNums").html("showing "+_startNum+"-"+_endNum+" of "+_totalNum+" items");
    		$("#page_totalPages").empty();
            $("#page_totalPages").append("<li><a onclick=\"jumpToFirstPage()\">首页</a></li>");
    		if(_startPage<=1){
    		    //TODO 设置为不可点状态
    		   $("#page_totalPages").append("<li   class=' disabled'><a onclick=\"jumpToPrePage()\"><上一页</a></li>");
    		}else{
    		   $("#page_totalPages").append("<li><a onclick=\"jumpToPrePage()\"><上一页</a></li>");
    		}
            for(var i=_startPage;i<=_endPage;i++){
                        if(i==_currentPage+1){
                            //TODO 设置为选中状态
                            $("#page_totalPages").append("<li class=' active'><a  onclick=\"jumpToPage("+i+")\">"+i+"</a></li>");
                            }else{
                            $("#page_totalPages").append("<li><a  onclick=\"jumpToPage("+i+")\" >"+i+"</a></li>");
                            }

            		}
            if(_endPage==_totalPages){
                //TODO 设置为不可点击状态
              $("#page_totalPages").append("<li class=' disabled'><a onclick=\"jumpToNextPage()\">下一页></a></li>");
            }else{
              $("#page_totalPages").append("<li><a onclick=\"jumpToNextPage()\">下一页></a></li>");
            }

            $("#page_totalPages").append("<li><a onclick=\"jumpToLastPage()\">尾页</a></li>");
	}
}

//跳转到某一页
function jumpToPage(pageIndex){
	_currentPage=pageIndex;
	loadData();
}

//跳转到首页
function jumpToFirstPage(){
    _currentPage=1;
    loadData();
}

//跳转到末页
function jumpToLastPage(){
    _currentPage=_totalPages-1;
    loadData();
}

//跳转到上一页
function jumpToPrePage(){
    if(_startPage!=0){
        _currentPage=_startPage-_pagePerNum;
        loadData();
    }

}

//跳转到下一页
function jumpToNextPage(){
    if(_endPage!=_totalPages){
            _currentPage=_startPage+_pagePerNum;
        }
        loadData();
}