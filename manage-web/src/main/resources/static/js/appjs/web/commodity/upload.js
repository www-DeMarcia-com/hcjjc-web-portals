$().ready(function() {
	
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});

function removepic(id){
	$.ajax({
		cache : true,
		type : "POST",
		url : "/web/commodity/picture/remove",
		data : {"commodityid":id},// 你的formid
		async : true,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("操作成功");
				window.location.reload();
				//var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
				//parent.layer.close(index);

			} else {
				parent.layer.alert(data.msg);
			}

		}
	});
}



function save() {
	
	/*$.ajax({
		cache : true,
		type : "POST",
		url : "/web/commodity/save",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("操作成功");
				parent.reLoad();
				var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
				parent.layer.close(index);

			} else {
				parent.layer.alert(data.msg);
			}

		}
	});*/

}



function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#signupForm").validate({
 			rules : {
 				/*name:"required",
 				price:"required",
 				commoditykindsid:"required",
 				content_sn:"required",
 				specname:"required",
 				specvalue:"required"*/
 			},
 			messages : {
 				/*name:"请输入商品名称",
 				price:"请输入商品价格",
 				commoditykindsid:"请选择商品类型",
 				intro:"请输入商品简介",
 				specname:"请输入商品属性名",
 				specvalue:"请输入商品属性值"*/
 			}
 		})
}
$(function () {
		var commodityid=document.getElementById("cid").value;
	    $('#fileupload').fileupload({
	        dataType: 'json',
			Type:"GET",
			url:"/web/commodity/first/upload?commodityid="+commodityid,
	        done: function (e, data) {
				parent.layer.msg("操作成功");
				window.location.reload();
	           /* $("tr:has(td)").remove();
	            $.each(data.result, function (index, file) {
	 
	                $("#uploaded-files").append(
	                        $('<tr/>')
	                        .append($('<td/>').text(file.fileName))
	                        .append($('<td/>').text(file.fileSize))
	                        .append($('<td/>').text(file.fileType))
	                        .append($('<td/>').html("<a href='rest/controller/get/"+index+"'>Click</a>"))
	                        )//end $("#uploaded-files").append()
	            }); */
	        }/*,*/
	 
	      /*  progressall: function (e, data) {
	            var progress = parseInt(data.loaded / data.total * 100, 10);
	            $('#progress .bar').css(
	                'width',
	                progress + '%'
	            );
	        },
	 
	        dropZone: $('#dropzone')*/
	    });
	});
