$().ready(function() {
	var ispro=$("#ispromotion").val();
	if(ispro=='1'){
		$("#ispro1").attr("style","");
		$("#ispro2").attr("style","");
	}else{
		$("#ispro1").attr("style","display:none");
		$("#ispro2").attr("style","display:none");
	}
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
	
		//$("#starttime").datetimepicker({format: 'yyyy-mm-dd'});
	
	validateRule();
});
$.validator.setDefaults({
	submitHandler : function() {
		update();
	}
});
function update() {
	if($("#ispromotion").val()==1&&$("#promotionprice").val()-$("#price").val()>=0){
		parent.layer.alert("促销价大于商品价格");
		return;
	}
	var sname=document.getElementsByName("sname");
	var svalue=document.getElementsByName("svalue");
	var n="";
	var v="";
	for(var i=0;i<sname.length;i++){
		n=n+sname[i].value+"@@";
		v=v+svalue[i].value+"@@";
	}
	if(n.lastIndexOf("@@")>-1){
		n=n.substring(0,n.lastIndexOf("@@"));
	}
	if(v.lastIndexOf("@@")>-1){
		v=v.substring(0,v.lastIndexOf("@@"));
	}
	n==null?"":n;
	v==null?"":v;
	$("#specname").val(n);
	$("#specvalue").val(v);
	var content_sn = $("#content_sn").summernote('code');
	if(content_sn==""){
		parent.layer.alert("请输入商品简介");
		return;
	}
	if(n==""){
		parent.layer.alert("请输入商品属性名");
		return;
	}
	if(v==""){
		parent.layer.alert("请输入商品属性值");
		return;
	}
	$("#intro").val(content_sn);
	/*var str=removeHTMLTag(content_sn);
	$("#contentgg").val(str);*/
	$.ajax({
		cache : true,
		type : "POST",
		url : "/web/commodity/update",
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
function promotion(checkbox){
	if(checkbox.checked){
		$("#ispro1").attr("style","");
		$("#ispro2").attr("style","");
		$("#ispromotion").val(1);		
	}else{
		$("#ispro1").attr("style","display:none");
		$("#ispro2").attr("style","display:none");
		$("#promotionprice").val("");
		$("#starttime").val("");
		$("#endtime").val("");
		$("#ispromotion").val(0);
	}
}
function close1(checkbox){
	if(checkbox.checked){
		$("#isclose").val(1);
	}else{
		
		$("#isclose").val(0);
	}
}
function hot(checkbox){
	if(checkbox.checked){
		$("#ishot").val(1);
	}else{
		
		$("#ishot").val(0);
	}
}
function insertRow1(r){
	  var i=r.parentNode.parentNode.rowIndex;
	  var table=document.getElementById('myTable');
	  var x=table.insertRow(i+1);
	  var y=x.insertCell(0);
	  var z=x.insertCell(1);
	  var w=x.insertCell(2);
	  y.innerHTML="<input type='text' value='' name='sname'/>";
	  z.innerHTML="<input type='text' value='' name='svalue'/>";
	  w.innerHTML="<a class='btn btn-primary btn-sm' href='javascript:void(0);' title='添加' onclick='insertRow1(this)'><i class='fa fa-edit'></i></a>"+
				"<a class='btn btn-warning btn-sm' href='javascript:void(0);' title='删除' onclick='deleteRow1(this)'><i class='fa fa-remove'></i></a>";
	 var j= table.rows[i].cells[2].innerHTML="<a class='btn btn-warning btn-sm' href='javascript:void(0);' title='删除' onclick='deleteRow1(this)'><i class='fa fa-remove'></i></a> ";
}
function deleteRow1(r){
	  var i=r.parentNode.parentNode.rowIndex;
	  var table=document.getElementById('myTable');
	  var x=table.rows.length;
	  if(x==2){
		 table.rows[i].cells[0].firstChild.value="";
		 table.rows[i].cells[1].firstChild.value="";
	  }else{
		  document.getElementById('myTable').deleteRow(i);
		  if(i==(x-1)){
			 var j= table.rows[i-1].cells[2].innerHTML="<a class='btn btn-primary btn-sm' href='javascript:void(0);' title='添加' onclick='insertRow1(this)'><i class='fa fa-edit'></i></a> "+
			"<a class='btn btn-warning btn-sm' href='javascript:void(0);' title='删除' onclick='deleteRow1(this)'><i class='fa fa-remove'></i></a>";
		  }
	  }
}
function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#signupForm").validate({
		rules : {
				name:"required",
				price:"required",
				commoditykindsid:"required",
				content_sn:"required",
				specname:"required",
				specvalue:"required"
			},
			messages : {
				name:"请输入商品名称",
				price:"请输入商品价格",
				commoditykindsid:"请选择商品类型",
				intro:"请输入商品简介",
				specname:"请输入商品属性名",
				specvalue:"请输入商品属性值"
			}
 		})
}
(function($) {
	$(function() {
		$.datepicker.regional['zh-CN'] = {
			changeMonth: true,
			changeYear: true,
			clearText: '清除',
			clearStatus: '清除已选日期',
			closeText: '关闭',
			closeStatus: '不改变当前选择',
			prevText: '<上月',
			prevStatus: '显示上月',
			prevBigText: '<<',
			prevBigStatus: '显示上一年',
			nextText: '下月>',
			nextStatus: '显示下月',
			nextBigText: '>>',
			nextBigStatus: '显示下一年',
			currentText: '今天',
			currentStatus: '显示本月',
			monthNames: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'],
			monthNamesShort: ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12'],
			monthStatus: '选择月份',
			yearStatus: '选择年份',
			weekHeader: '周',
			weekStatus: '年内周次',
			dayNames: ['星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六'],
			dayNamesShort: ['周日', '周一', '周二', '周三', '周四', '周五', '周六'],
			dayNamesMin: ['日', '一', '二', '三', '四', '五', '六'],
			dayStatus: '设置 DD 为一周起始',
			dateStatus: '选择 m月 d日, DD',
			dateFormat: 'yy-mm-dd',
			firstDay: 1,
			initStatus: '请选择日期',
			isRTL: false
		};

	});
	$(function() {
		$.datepicker.setDefaults($.datepicker.regional['zh-CN']);
		$("#starttime").datetimepicker();
		$("#endtime").datetimepicker();
	});
	}(jQuery));
