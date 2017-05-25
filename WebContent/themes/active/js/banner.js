'use strict'
$(document).ready(function() {
	loadData();
	  $("#btn_add_banner").click(function(){
			addBanner();
		});
		$("#btn_submit_banner").click(function(){
			addOrUpdateBanner();
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
        url:"active/bannerController/loadData.do",
        data:{
        	currentPage:_currentPage
        },
        success:function(data){
        	$("tbody").empty();
        	updatePageParams(data.page);
        	if(_totalPages>0){
        		_list=data.list;
            	$.each(data.list,function(i,banner){
            		var _row="<tr>"+
            		    "<td style=\"display:none;\">"+i+"</td>"+
            			"<td><img width='100' height='100' src='"+banner.img+"'></td>"+
            			"<td>"+banner.text+"</td>"+
            			"<td>"+banner.url+"</td>"+
            			"<td>"+banner.updateTime+"</td>"+
            		  	"<td>"+
            		  	"<a href=\"#modal_banner\" data-toggle=\"modal\" class=\"btn btn-s-md btn-danger \" onclick=\"updateBanner("+i+")\">修改</a>"+
            		  	"<a class=\"btn btn-s-md btn-danger \" onclick=\"delBanner("+i+")\">删除</a>"+
            		  	"</td>"+
            		  	"</tr>"
            		$("tbody").append(_row);
            	})
        	};
        }
    });

}



function addBanner(){
	$("#banner_id").val("");
	$("#banner_img").val("");
	$("#banner_text").val("");
	$("#banner_url").val("");
	 $("#btn_submit_banner").text("添加");
}

/**
 * 弹出修改框
 * 
 * @param index
 * @returns
 */
function updateBanner(index){
	if(_list){
		var banner=_list[index];
		$("#banner_id").val(banner.id);
		$("#banner_img").val(banner.img);
		$("#banner_text").val(banner.text);
		$("#banner_url").val(banner.url);
		$("#btn_submit_banner").text("修改");
	}
}



/**
 * 添加或修改头图
 * @returns
 */
function addOrUpdateBanner(){
	var _id=$("#banner_id").val();
	var _img=$("#banner_img").val();
	var _text=$("#banner_text").val();
	var _url=$("#banner_url").val();
	 $.ajax({
         url:"active/bannerController/addOrUpdate.do",
         type:'post',
         data:{"id":_id,
        	   "img":_img,  
        	   "text":_text,
        	   "url":_url,
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
function delBanner(index){
	if(_list){
		$.ajax({
	         url:"active/bannerController/del.do",
	         type:'post',
	         data:{"bannerId":_list[index].id},
	         success:function(data){
	             if(data.success){
	            	 loadData();
	             }else{
	                 
	             }
	         }
	     });
	}
}



