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
 		var intro=$("#intro").val();
 		$('.summernote').summernote("code",intro);
 		validateRule();
 	});


 	$.validator.setDefaults({
 		submitHandler : function() {
 			save();
 		}
 	});
 	function validateRule() {
 		var icon = "<i class='fa fa-times-circle'></i> ";
 		$("#signupForm").validate({
 			rules : {
 				name:"required",
 				address:"required",
 				cellphone:"required",
 				phone:"required",
 				email:"required",
 				culture:"required",
 				intro:"required",
 				introgg:"required"
 			},
 			messages : {
 				name:"请填写公司名称",
 				address:"请填写公司地址",
 				cellphone:"请填写公司手机号码",
 				phone:"请填写公司座机号码",
 				email:"请填写公司邮箱",
 				culture:"请填写公司企业文化",
 				intro:"请填写公司简介",
 				introgg:"请填写公司简介"
 				
 			}
 		});
 	}
 	
 	function save() {
 		var content_sn = $("#content_sn").summernote('code');
 		$("#intro").val(content_sn);
 		$("#introgg").val(removeHTMLTag(content_sn));
 		$.ajax({
 			cache : true,
 			type : "POST",
 			url : "/web/company/save",
 			data : $('#signupForm').serialize(),// 你的formid
 			async : false,
 			error : function(request) {
 				parent.layer.alert("Connection error");
 			},
 			success : function(r) {
 				if (r.code == 0) {
 					parent.layer.msg(r.msg);
 					/*parent.reLoad();*/
 				} else {
 					parent.layer.alert(r.msg)
 				}
 			}
 		});
 	}
 	/*function test(){
 		var content_sn = $("#content_sn").summernote('code');
 		alert("111111111111111");
 		alert(content_sn);
 		var content_sn1= removeHTMLTag(content_sn);
 		alert("222222222222222");
 		alert(content_sn1);
 		
 	}*/
 	function removeHTMLTag(str) {
        str = str.replace(/<\/?[^>]*>/g,''); //去除HTML tag
        str = str.replace(/[ | ]*\n/g,'\n'); //去除行尾空白
        //str = str.replace(/\n[\s| | ]*\r/g,'\n'); //去除多余空行
        str=str.replace(/&nbsp;/ig,'');//去掉&nbsp;
        str=str.replace(/\s/g,''); //将空格去掉
        return str;
 	}