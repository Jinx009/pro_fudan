'use strict'
$(document).ready(function() {
	loadData();
	
	  
	  $("#btn_add_category").click(function(){
			addCategory();
		});
		
		$("#btn_submit_category").click(function(){
			addOrUpdateData();
		});
		
		$("#category_level").change(function(){
			if($("#category_level").val()==0){
				$("#div_category_father").hide();
			}else{
				loadFatherCategory();
			}
		})
		

});

//頁面數據
var _list;

/**
 * 加载分类列表数据
 * @returns
 */
function loadData(){
	var _type=$("#type").val();
	$.ajax({
        url:"active/categoryController/loadData.do",
        data:{currentPage:_currentPage,type:_type},
        success:function(data){
        	$("tbody").empty();
        	updatePageParams(data.page);
        	if(_totalPages>0){
        		_list=data.list;
            	$.each(data.list,function(i,category){
            		var _row="<tr>"+
            		    "<td style=\"display:none;\">"+i+"</td>"+
            		    "<td>"+category.fatherName+"</td>"+
            		  	"<td>"+category.name+"</td>"+
            			"<td>"+category.img+"</td>"+
            			"<td>"+category.type+"</td>"+
            		  	"<td>"+
            		  	"<a href=\"#modal-add\" data-toggle=\"modal\" class=\"btn btn-s-md btn-danger \" onclick=\"updateCategory("+i+")\">修改</a>  "+
            		  	"<a class=\"btn btn-s-md btn-danger \" onclick=\"delCategory("+i+")\">删除</a>"+
            		  	"</td>"+
            		  	"</tr>"
            		$("tbody").append(_row);
            	})
        	};
        }
    });

}

/**
 * 获取父级分类
 * @returns
 */
function loadFatherCategory(){
	$.ajax({
        url:"active/categoryController/loadFatherCategory.do",
        success:function(data){
        	$("#category_father").empty();
            	$.each(data,function(i,category){
            		var _option="<option value="+category.id+">"+category.name+"</option>";
            		$("#category_father").append(_option);
            	});
            	if(data&&data.length>0){
            		$("#div_category_father").show();
            	}else{
            		$("#div_category_father").hide();
            		$("#category_level").val(0)
            	}
        }
    });
}

function addCategory(){
	$("#category_id").val("");
	$("#category_name").val("");
	$("#category_type").val("");
	$("#category_img").val("");
	$("#category_level").val(0);
	$("#div_category_father").hide();
	 $(".modal-title").text("添加");
	 $("#btn_submit_category").text("添加");
}

/**
 * 弹出修改框
 * 
 * @param index
 * @returns
 */
function updateCategory(index){
	if(_list){
		var category=_list[index];
		$("#category_id").val(category.id);
		$("#category_name").val(category.name);
		$("#category_type").val(category.type);
		$("#category_img").val(category.img);
		$(".modal-title").text("修改");
		$("#btn_submit_category").text("修改");
		if(category.fatherId!=0){
			$("#category_level").val(1);
			$.ajax({
		        url:"active/categoryController/loadFatherCategory.do",
		        success:function(data){
		        	$.each(data,function(i,category){
	            		var _option="<option value="+category.id+">"+category.name+"</option>";
	            		$("#category_father").append(_option);
	            	});
	            	if(data&&data.length>0){
	            		$("#div_category_father").show();
	            		$("#div_category_father").val(category.fatherId);
	            	}else{
	            		$("#div_category_father").hide();
	            		$("#category_level").val(0)
	            	}
		        }
		    });
		}else{
			$("#div_category_father").hide();
    		$("#category_level").val(0)
		}
	}
}


/**
 * 添加或修改活动信息
 * @returns
 */
function addOrUpdateData(){
	var _id=$("#category_id").val();
	var _name=$("#category_name").val();
	var _type=$("#category_type").val();
	var _level=$("#category_level").val();
	var _img=$("#category_img").val();
	var _fatherId=$("#category_father").val();
	var _fatherName=$("#category_father").find("option:selected").text();
	if(_level==0){
		_fatherId=0;
		_fatherName='一级分类';
	}
	 $.ajax({
         url:"active/categoryController/addOrUpdate.do",
         type:'post',
         data:{"id":_id,
        	   "name":_name,
        	   "type":_type,
        	   "img":_img,
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
function delCategory(index){
	if(_list){
		$.ajax({
	         url:"active/categoryController/del.do",
	         type:'post',
	         data:{"categoryId":_list[index].id},
	         success:function(data){
	             if(data.success){
	            	 loadData();
	             }else{
	                 
	             }
	         }
	     });
	}
}



