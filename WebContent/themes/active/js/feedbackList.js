'use strict'
$(document).ready(function() {
	loadData();
});

//頁面數據
var _list;

/**
 * 加载反馈列表数据
 * @returns
 */
function loadData(){
	$.ajax({
        url:"active/feedbackController/loadData.do",
        data:{currentPage:_currentPage},
        success:function(data){
        	$("tbody").empty();
        	updatePageParams(data.page);
        	if(_totalPages>0){
        		_list=data.list;
            	$.each(data.list,function(i,feedback){
            		var _row="<tr>"+
            		    "<td style=\"display:none;\">"+i+"</td>"+
            		  	"<td>"+decodeUnicode(feedback.nickName)+"</td>"+
            		  	"<td>"+feedback.phone+"</td>"+
            		  	"<td>"+feedback.content+"</td>"+
            		  	"<td>"+feedback.addTime+"</td>"+
            		  	"</tr>"
            		$("tbody").append(_row);
            	})
        	};
        }
    });

}

function decodeUnicode(str) {  
    str = str.replace(/\\/g, "%");  
    return unescape(str);  
}  

