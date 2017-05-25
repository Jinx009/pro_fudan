'use strict'
$(document).ready(function() {
	
	loadData();
	
	$("#btn_submit_activeUser").click(function(){
		addOrUpdateData();
	})
});

//頁面數據
var _list;

/**
 * 加载用戶列表数据
 * @returns
 */
function loadData(){
	$.ajax({
        url:"/active/activeUserController/loadData.do",
        data:{currentPage:_currentPage},
        success:function(data){
        	$("tbody").empty();
        	updatePageParams(data.page);
        	if(_totalPages>0){
        		_list=data.list;
            	$.each(data.list,function(i,activeUser){
            		var _row="<tr>"+
            		    "<td style=\"display:none;\">"+i+"</td>";
	            		if(activeUser.name!=null){
	        				_row+= "<td>"+activeUser.name+"</td>";
	        			}else{
	        				_row+= "<td></td>";
	        			}
	            		if(activeUser.email!=null){
	        				_row+= "<td>"+activeUser.email+"</td>";
	        			}else{
	        				_row+= "<td></td>";
	        			}if(activeUser.openId!=null){
	        				_row+= "<td>"+activeUser.openId+"</td>";
	        			}else{
	        				_row+= "<td></td>";
	        			}
	            		if(activeUser.nickName!=null){
	        				_row+= "<td>"+activeUser.nickName+"</td>";
	        			}else{
	        				_row+= "<td></td>";
	        			}
            			if(activeUser.mobilePhone!=null){
            				_row+= "<td>"+activeUser.mobilePhone+"</td>";
            			}else{
            				_row+= "<td></td>";
            			}
            			_row+= "<td>"+activeUser.addTime+"</td>";
            			_row+= "<td>";
            			_row+= "<a class=\"btn btn-s-md btn-danger \" onclick=\"delActiveUser("+i+")\">删除</a>"+
            		  	"</td>"+
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

/**
 * 弹出修改框
 * 
 * @param index
 * @returns
 */
function updateActiveUser(index){
	if(_list){
		var active=_list[index];
		$("#active_id").val(active.id);
		$("#active_name").val(active.name);
		$("#active_type").val(active.type);
		$("#active_startTime").val(active.startTime);
		$("#active_endTime").val(active.endTime);
		$("#active_openTime").val(active.openTime);
		$("#active_closeTime").val(active.closeTime);
		$("#active_address").val(active.address);
		$("#active_detailAddress").val(active.detailAddress);
		$("#active_detail").val(active.detail);
		$(".modal-title").text("修改");
		$("#btn_submit_active").text("修改");
	}
}


/**
 * 添加或修改活动信息
 * @returns
 */
function addOrUpdateData(){
	var _id=$("#active_id").val();
	var _name=$("#active_name").val();
	var _type=$("#active_type").val();
	var _startTime=$("#active_startTime").val();
	var _endTime=$("#active_endTime").val();
	var _openTime=$("#active_openTime").val();
	var _closeTime=$("#active_closeTime").val();
	var _address=$("#active_address").val();
	var _detailAddress=$("#active_detailAddress").val();
	console.log(_detailAddress);
	var _detail=$("#active_detail").val();
	 $.ajax({
         url:"/active/activeUserController/addOrUpdate.do",
         type:'post',
         data:{"id":_id,
        	   "name":_name,
        	   "type":_type,
	           "startTime":_startTime,
	           "endTime":_endTime,
	           "openTime":_openTime,
	           "closeTime":_closeTime,
	           "address":_address,
	           "detailAddress":_detailAddress,
	           "detail":_detail
		 		},
         success:function(data){
             if(data.success){
            	 loadData();
             }else{
                 
             }
         }
     });
}


/**
 * 删除用户信息
 * 
 * @param index
 * @returns
 */
function delActiveUser(index){
	if(_list){
		$.ajax({
	         url:"/active/activeUserController/del.do",
	         type:'post',
	         data:{"activeId":_list[index].id},
	         before:function(){
	        	console.log(start); 
	         },
	         success:function(data){
	             if(data.success){
	            	 loadData();
	             }else{
	                 
	             }
	         }
	     });
	}
}

/**
 * 跳转至活动报名人列表
 * 
 * @param index
 * @returns
 */
function listActiveUsers(index){
	if(_list){
		console.log(_list[index]);
	}
}


