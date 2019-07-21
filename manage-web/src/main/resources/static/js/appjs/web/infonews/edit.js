$().ready(function() {
	$('.summernote').summernote({
			height : '220px',
			lang : 'zh-CN',
			callbacks: {
	            onImageUpload: function(files, editor, $editable) {
	                sendFile(files);
	            }
	        }
		});
		var content=$("#content").val();
		$('.summernote').summernote("code",content);
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		update();
	}
});
function update() {
	var content_sn = $("#content_sn").summernote('code');
	$("#content").val(content_sn);
	var str=removeHTMLTag(content_sn);
	$("#contentgg").val(str);
	$.ajax({
		cache : true,
		type : "POST",
		url : "/web/infonews/update",
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
				parent.layer.alert(data.msg)
			}

		}
	});

}
function removeHTMLTag(str) {
    str = str.replace(/<\/?[^>]*>/g,''); //去除HTML tag
    str = str.replace(/[ | ]*\n/g,'\n'); //去除行尾空白
    //str = str.replace(/\n[\s| | ]*\r/g,'\n'); //去除多余空行
    str=str.replace(/&nbsp;/ig,'');//去掉&nbsp;
    str=str.replace(/\s/g,''); //将空格去掉
    return str;
}
function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#signupForm").validate({
 			rules : {
 				title:"required",
 				infonewskindsid:"required",
 				content:"required"
 			},
 			messages : {
 				title:"请填写新闻标题",
 				infonewskindsid:"请选择新闻类型",
 				content:"请填写新闻内容"
 			}
 		})
}
