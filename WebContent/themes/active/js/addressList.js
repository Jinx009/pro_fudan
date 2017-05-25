'use strict'
$(document).ready(function() {
	loadData();
	
	$("#btn_add_address").click(function(){
		addAddress();
	});
	
	$("#btn_submit_address").click(function(){
		addOrUpdateData();
	});
	
	$("#address_type").change(function(){
		if($("#address_type").val()==0){
			$("#div_address_father").hide();
		}else{
			loadFatherAddress();
		}
	})
});

//頁面數據
var _list;

/**
 * 加载地址列表数据
 * @returns
 */
function loadData(){
	$.ajax({
        url:"active/addressController/loadData.do",
        data:{currentPage:_currentPage},
        success:function(data){
        	$("tbody").empty();
        	updatePageParams(data.page);
        	if(_totalPages>0){
        		_list=data.list;
            	$.each(data.list,function(i,address){
            		var _row="<tr>"+
            		    "<td style=\"display:none;\">"+i+"</td>"+
            		    "<td>"+address.fatherName+"</td>"+
            		  	"<td>"+address.name+"</td>"+
            		  	"<td>"+(address.type==0?'省/直辖市':'市/区')+"</td>"+
            		  	"<td>"+
            		  	"<a href=\"#modal-add\" data-toggle=\"modal\" class=\"btn btn-s-md btn-danger \" onclick=\"updateAddress("+i+")\">修改</a>  "+
            		  	"<a class=\"btn btn-s-md btn-danger \" onclick=\"delAddress("+i+")\">删除</a>"+
            		  	"</td>"+
            		  	"</tr>"
            		$("tbody").append(_row);
            	})
        	};
        }
    });

}

/**
 * 获取父级地址
 * @returns
 */
function loadFatherAddress(){
	$.ajax({
        url:"active/addressController/loadFatherAddress.do",
        success:function(data){
            	$.each(data,function(i,address){
            		var _option="<option value="+address.id+">"+address.name+"</option>";
            		$("#address_father").append(_option);
            	});
            	if(data&&data.length>0){
            		$("#div_address_father").show();
            	}else{
            		$("#div_address_father").hide();
            		$("#address_type").val(0)
            	}
        }
    });
}

function addAddress(){
	$("#address_id").val("");
	$("#address_name").val("");
	$("#address_type").val(0);
	$("#div_address_father").hide();
	 $(".modal-title").text("添加");
	 $("#btn_submit_active").text("添加");
}

/**
 * 弹出修改框
 * 
 * @param index
 * @returns
 */
function updateAddress(index){
	if(_list){
		var address=_list[index];
		$("#address_id").val(address.id);
		$("#address_name").val(address.name);
		$("#address_type").val(address.type);
		$(".modal-title").text("修改");
		$("#btn_submit_active").text("修改");
		if(address.type==1){
			$.ajax({
		        url:"active/addressController/loadFatherAddress.do",
		        success:function(data){
		            	$.each(data,function(i,address){
		            		var _option="<option value="+address.id+">"+address.name+"</option>";
		            		$("#address_father").append(_option);
		            	});
		            	if(data&&data.length>0){
		            		$("#div_address_father").show();
		            		$("#div_address_father").val(address.fatherId);
		            	}else{
		            		$("#div_address_father").hide();
		            		$("#address_type").val(0)
		            	}
		        }
		    });
		}
	}
}


/**
 * 添加或修改活动信息
 * @returns
 */
function addOrUpdateData(){
	var _id=$("#address_id").val();
	var _name=$("#address_name").val();
	var _type=$("#address_type").val();
	var _fatherId=$("#address_father").val();
	var _fatherName=$("#address_father").find("option:selected").text();
	if(_type==0){
		_fatherId=0;
		_fatherName='中国';
	}
	 $.ajax({
         url:"active/addressController/addOrUpdate.do",
         type:'post',
         data:{"id":_id,
        	   "name":_name,
        	   "type":_type,
	           "fatherId":_fatherId,
	           "fatherName":_fatherName
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
 * 删除活动信息
 * 
 * @param index
 * @returns
 */
function delAddress(index){
	if(_list){
		$.ajax({
	         url:"active/addressController/del.do",
	         type:'post',
	         data:{"addressId":_list[index].id},
	         success:function(data){
	             if(data.success){
	            	 loadData();
	             }else{
	                 
	             }
	         }
	     });
	}
}



