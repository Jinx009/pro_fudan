'use strict'
$(document).ready(function() {
	loadData();
	  $("#btn_add_photo").click(function(){
			addPhoto();
		});
		$("#btn_submit_photo").click(function(){
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
	$.ajax({
        url:"active/photoController/loadData.do",
        data:{
        	currentPage:_currentPage
        },
        success:function(data){
        	$("tbody").empty();
        	updatePageParams(data.page);
        	if(_totalPages>0){
        		_list=data.list;
            	$.each(data.list,function(i,photo){
            		var _row="<tr>"+
            		    "<td style=\"display:none;\">"+i+"</td>"+
            			"<td><img width='100' height='100' src='"+photo.img+"'></td>"+
            			"<td>"+photo.key+"</td>"+
            			"<td>"+photo.text+"</td>"+
            			"<td>"+photo.updateTime+"</td>"+
            		  	"<td>"+
            		  	"<a href=\"#modal_photo\" data-toggle=\"modal\" class=\"btn btn-s-md btn-danger \" onclick=\"updatePhoto("+i+")\">修改</a>"+
            		  	"<a class=\"btn btn-s-md btn-danger \" onclick=\"delPhoto("+i+")\">删除</a>"+
            		  	"</td>"+
            		  	"</tr>"
            		$("tbody").append(_row);
            	})
        	};
        }
    });

}



function addPhoto(){
	$("#photo_id").val("");
	$("#photo_img").val("");
	$("#photo_key").val("");
	$("#photo_text").val("");
	$("#btn_submit_photo").text("添加");
}

/**
 * 弹出修改框
 * 
 * @param index
 * @returns
 */
function updatePhoto(index){
	if(_list){
		var photo=_list[index];
		$("#photo_id").val(photo.id);
		$("#photo_img").val(photo.img);
		$("#photo_key").val(photo.key);
		$("#photo_text").val(photo.text);
		$("#btn_submit_photo").text("修改");
	}
}



/**
 * 添加或修改图片
 * @returns
 */
function addOrUpdatePhoto(){
	var _id=$("#photo_id").val();
	var _img=$("#photo_img").val();
	var _key=$("#photo_key").val();
	var _text=$("#photo_text").val();
	 $.ajax({
         url:"active/photoController/addOrUpdate.do",
         type:'post',
         data:{"id":_id,
        	   "img":_img,  
        	   "key":_key,
        	   "text":_text,
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
 * 删除相册图片
 * 
 * @param index
 * @returns
 */
function delPhoto(index){
	if(_list){
		$.ajax({
	         url:"active/photoController/del.do",
	         type:'post',
	         data:{"photoId":_list[index].id},
	         success:function(data){
	             if(data.success){
	            	 loadData();
	             }else{
	                 
	             }
	         }
	     });
	}
}



