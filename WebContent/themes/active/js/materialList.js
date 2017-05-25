'use strict'
$(document).ready(function() {
	loadData();
	
	  
	  $("#btn_add_material_text").click(function(){
			addMaterialText();
		});
	  
	  $("#btn_add_material_photo").click(function(){
			addMaterialPhoto();
		});
		$("#btn_submit_material_text").click(function(){
			addOrUpdateText();
		});
		
		$("#btn_submit_material_photo").click(function(){
			addOrUpdatePhoto();
		});
		
});

//頁面數據
var _list;

/**
 * 加载分类列表数据
 * @returns
 */
function loadData(){
	var _activeId=$("#material_activeId").val();
	$.ajax({
        url:"active/materialController/loadData.do",
        data:{
        	activeId:_activeId,
        	currentPage:_currentPage
        },
        success:function(data){
        	$("tbody").empty();
        	updatePageParams(data.page);
        	if(_totalPages>0){
        		_list=data.list;
            	$.each(data.list,function(i,material){
            		var _row="<tr>"+
            		    "<td style=\"display:none;\">"+i+"</td>";
            			if(material.type==1){
            				_row+="<td>图片</td>";
            				_row+="<td><img width='100' height='100' src='"+material.content+"'></td>";
            			}else{
            				_row+="<td>文本</td>";
            				_row+="<td>"+material.content+"'></td>";
            			}
            			_row+= "<td>"+material.remark+"</td>"+
            			"<td>"+material.orderNum+"</td>"+
            		  	"<td>"+
            		  	"<a href=\"#"+(material.type==1?'modal-add-photo':'modal-add-text')+"\" data-toggle=\"modal\" class=\"btn btn-s-md btn-danger \" onclick=\""+(material.type==1?'updateMaterialPhoto('+i+')':'updateMaterialText('+i+')')+"\">修改</a>  "+
            		  	"<a class=\"btn btn-s-md btn-danger \" onclick=\"delMaterial("+i+")\">删除</a>"+
            		  	"</td>"+
            		  	"</tr>"
            		$("tbody").append(_row);
            	})
        	};
        }
    });

}



function addMaterialText(){
	$("#material_id").val("");
	$("#material_text").val("");
	$("#material_text_remark").val("");
	$("#material_text_order").val('0');
	 $("#btn_submit_material_text").text("添加");
}

function addMaterialPhoto(){
	$("#material_id").val("");
	$("#material_photo_remark").val("");
	$("#file").fileinput('refresh');
	$("#material_photo_order").val('0');
	$("#btn_submit_material_photo").text("添加");
}

/**
 * 弹出修改框
 * 
 * @param index
 * @returns
 */
function updateMaterialText(index){
	if(_list){
		var material=_list[index];
		$("#material_id").val(material.id);
		$("#material_text").val(material.content);
		$("#material_text_order").val(material.orderNum);
		$("#material_text_remark").val(material.remark);
		$("#btn_submit_material_text").text("修改");
	}
}

/**
 * 弹出修改框
 * 
 * @param index
 * @returns
 */
function updateMaterialPhoto(index){
	if(_list){
		var material=_list[index];
		$("#material_id").val(material.id);
		$("#material_img").val(material.content);
		$("#material_photo_remark").val(material.remark);
		$("#material_photo_order").val(material.orderNum);
		$("#btn_submit_material_photo").text("修改");
	}
}


/**
 * 添加或修改文本素材
 * @returns
 */
function addOrUpdateText(){
	var _id=$("#material_id").val();
	var _content=$("#material_text").val();
	var _remark=$("#material_text_remark").val();
	var _activeId=$("#material_activeId").val();
	var _orderNum = $('#material_text_order').val();
	 $.ajax({
         url:"active/materialController/addOrUpdate.do",
         type:'post',
         data:{"id":_id,
        	 "activeId":_activeId,
        	   "content":_content,
        	   "remark":_remark,
        	   "type":2,
       	    	"orderNum":_orderNum
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
 * 添加或修改图片素材
 * @returns
 */
function addOrUpdatePhoto(){
	var _id=$("#material_id").val();
	var _content=$("#material_img").val();
	var _remark=$("#material_photo_remark").val();
	var _activeId=$("#material_activeId").val();
	var _orderNum = $('#material_photo_order').val();
	 $.ajax({
         url:"active/materialController/addOrUpdate.do",
         type:'post',
         data:{"id":_id,
        	   "activeId":_activeId,  
        	   "content":_content,
        	   "remark":_remark,
        	   "type":1,
      	    	"orderNum":_orderNum
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
function delMaterial(index){
	if(_list){
		$.ajax({
	         url:"active/materialController/del.do",
	         type:'post',
	         data:{"materialId":_list[index].id},
	         success:function(data){
	             if(data.success){
	            	 loadData();
	             }else{
	                 
	             }
	         }
	     });
	}
}



