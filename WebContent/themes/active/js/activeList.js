'use strict'
$(document).ready(function() {
	
	loadData();
	loadSecondCategory();
	loadSecondAddress();
	$("#btn_add_active").click(function(){
		addActive();
	})
	$("#btn_submit_active").click(function(){
		addOrUpdateData();
	})
});

//頁面數據
var _list;
/**
 * 加载活动列表数据
 * @returns
 */
function loadData(){
	$.ajax({
        url:"active/activeController/loadData.do",
        data:{currentPage:_currentPage},
        success:function(data){
        	$("#tbody_active").empty();
        	updatePageParams(data.page);
        	if(_totalPages>0){
        		_list=data.list;
            	$.each(data.list,function(i,active){
            		var  _row="<tr>"+
            		    "<td style=\"display:none;\">"+i+"</td>"+
            		  	"<td>"+active.name+"</td>"+
            		  	"<td>"+active.type+"</td>"+
            		 	"<td>"+active.openTime+"</td>"+
            		 	"<td>"+active.closeTime+"</td>"+
            		 	"<td>"+active.startTime+"</td>"+
            		 	"<td>"+active.endTime+"</td>"+
            		 	"<td><img width='100' height='100' src='"+active.topImg+"'></td>"+
            		  	"<td><a class=\"btn btn-s-md btn-danger \" href=\"active/peopleController/list.do?activeId="+active.id+"\">报名人查看</a></td>"+
            		  	"<td>"+active.address+"</td>"+
            		  	"<td>"+active.detailAddress+"</td>"+
            		  	"<td>"+
            		  	"<a href=\"active/materialController/list.do?activeId="+active.id+"\" class=\"btn btn-s-md btn-danger \">素材管理</a>  "+
            		  	"<a href=\"#modal-add-update\" data-toggle=\"modal\" class=\"btn btn-s-md btn-danger \" onclick=\"updateActive("+i+")\">修改</a>  "+
            		  	"<a class=\"btn btn-s-md btn-danger \" onclick=\"delActive("+i+")\">删除</a>"+
            		  	"</td>"+
            		  	"</tr>"
            		$("#tbody_active").append(_row);
            	})
        	};
        }
    });

}

/**
 * 弹出修改框
 * 
 * @param index
 * @returns
 */
function updateActive(index){
	if(_list){
		var active=_list[index];
		$("#active_id").val(active.id);
		$("#active_name").val(active.name);
		$("#active_type").val(active.type);
		$("#active_startTime").val(active.startTime);
		$("#active_endTime").val(active.endTime);
		$("#active_openTime").val(active.openTime);
		$("#active_closeTime").val(active.closeTime);
		$("#active_topImg").val(active.topImg);
		$("#active_address").val(active.address);
		$("#active_detailAddress").val(active.detailAddress);
		$(".modal-title").text("修改");
		$("#btn_submit_active").text("修改");
	}
}

/**
 * 弹出修改框
 * 
 * @param index
 * @returns
 */
function addActive(){
		$("#active_id").val("");
		$("#active_name").val("");
		$("#active_startTime").val("");
		$("#active_endTime").val("");
		$("#active_openTime").val("");
		$("#active_closeTime").val("");
		$("#active_topImg").val("");
		$("#file").fileinput('refresh');
		$("#active_detailAddress").val("");
		$(".modal-title").text("添加");
		$("#btn_submit_active").text("添加");
}


/**
 * 添加或修改活动信息
 * @returns
 */
function addOrUpdateData(){
	var _id=$("#active_id").val();
	var _name=$("#active_name").val();
	var _type=$("#active_type").val();
	//活动开始时间
	var _openTime=$("#active_openTime").val();
	//活动结束时间
	var _closeTime=$("#active_closeTime").val();
	//报名开始时间
	var _startTime=$("#active_startTime").val();
	//报名结束时间
	var _endTime=$("#active_endTime").val();
	var _topImg=$("#active_topImg").val();
	var _address=$("#active_address").val();
	var _detailAddress=$("#active_detailAddress").val();
	if(Date.parse(_openTime)>Date.parse(_closeTime)){
		alert("活动开始时间必须小于活动结束时间!");
	}else if(Date.parse(_startTime)>Date.parse(_endTime)){
		alert("报名开始时间必须小于报名结束时间!");
	}else if(Date.parse(_startTime)>Date.parse(_openTime)){
		alert("报名开始时间必须小于于活动开始时间!");
	}else if(Date.parse(_endTime)>Date.parse(_openTime)){
		alert("报名结束时间必须小于活动开始时间!");
	}else{
	 $.ajax({
         url:"active/activeController/addOrUpdate.do",
         type:'post',
         data:{"id":_id,
        	   "name":_name,
        	   "type":_type,
	           "startTime":_startTime,
	           "endTime":_endTime,
	           "openTime":_openTime,
	           "closeTime":_closeTime,
	           "topImg":_topImg,
	           "address":_address,
	           "detailAddress":_detailAddress,
		 		},
         success:function(data){
             if(data.success){
            	 loadData();
             }
         }
     });
	 }
}


/**
 * 删除活动信息
 * 
 * @param index
 * @returns
 */
function delActive(index){
	if(_list){
		$.ajax({
	         url:"active/activeController/del.do",
	         type:'post',
	         data:{"activeId":_list[index].id},
	         success:function(data){
	             if(data.success){
	            	 loadData();
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
	
}

/**
 * 获取二级分类
 * @returns
 */
function loadSecondCategory(){
	$.ajax({
        url:"active/categoryController/loadSecondCategory.do",
        success:function(data){
        	$("#active_type").empty();
            	$.each(data,function(i,category){
            		var _option="<option value="+category.name+">"+category.fatherName+"-"+category.name+"</option>";
            		$("#active_type").append(_option);
            	});
        }
    });
}

/**
 * 获取二级地址
 * @returns
 */
function loadSecondAddress(){
	$.ajax({
        url:"active/addressController/loadSecondAddress.do",
        success:function(data){
        	$("#active_address").empty();
            	$.each(data,function(i,address){
            		var _option="<option value="+address.name+">"+address.name+"</option>";
            		$("#active_address").append(_option);
            	});
        }
    });
}



