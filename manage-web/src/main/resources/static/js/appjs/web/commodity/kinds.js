
var prefix = "/web/commodity";
/*var comm=$("#comm").val();*/
var comm=null;
var k=null;
$(function() {
	kinds();
	load();
	
});
function kinds(){
	comm=null;
	$.ajax({
		/*cache : true,*/
		type : "GET",
		url : "/web/commodity/commkindslist",
		//data : $('#signupForm').serialize(),// 你的formid
		async : true,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if(data!=null){
				comm=data.producer;
				k=data.kinds;
				/*alert(comm);
				for(var j=0;j<comm.length;j++){
					alert(data[j].id);
					alert(data[j].name);
				}*/
			}
		}
	});
}
function load() {
	//selectLoad();
	$('#exampleTable')
		.bootstrapTable(
			{
				method : 'get', // 服务器数据的请求方式 get or post
				url : prefix + "/list", // 服务器数据的加载地址
				//	showRefresh : true,
				//	showToggle : true,
				//	showColumns : true,
				iconSize : 'outline',
				toolbar : '#exampleToolbar',
				striped : true, // 设置为true会有隔行变色效果
				dataType : "json", // 服务器返回的数据类型
				pagination : true, // 设置为true会在底部显示分页条
				// queryParamsType : "limit",
				// //设置为limit则会发送符合RESTFull格式的参数
				singleSelect : false, // 设置为true将禁止多选
				// contentType : "application/x-www-form-urlencoded",
				// //发送到服务器的数据编码类型
				pageSize : 10, // 如果设置了分页，每页数据条数
				pageNumber : 1, // 如果设置了分布，首页页码
				//search : true, // 是否显示搜索框
				showColumns : true, // 是否显示内容下拉框（选择显示的列）
				sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
				queryParams : function(params) {
					return {
						//说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
						limit : params.limit,
						offset : params.offset,
						/*title : $('#searchName').val(),*/
						//type : $('#searchName').val(),
						order: params.order,//排序
				        sort:params.sort//排序字段
						
					};
				},
				// //请求服务器数据时，你可以通过重写参数的方式添加一些额外的参数，例如 toolbar 中的参数 如果
				// queryParamsType = 'limit' ,返回参数必须包含
				// limit, offset, search, sort, order 否则, 需要包含:
				// pageSize, pageNumber, searchText, sortName,
				// sortOrder.
				// 返回false将会终止请求
				columns : [
					{
						checkbox : true
					},
					{
						field : 'id',
						title : '编号',
						sortable:true,
						align : 'center'
							
					},
					{
						field : 'name',
						sortable:true,
						title : '商品名称'
                    },
                    {
						field : 'quantity',
						title : '数量',
						sortable:true,
						formatter:function (value,row,index) {
	                        return "<span>"+row.quantity.toFixed(2)+"</span>"
	                    }
						
                    },
                    {
						field : 'price',
						title : '价格',
						align : 'center',
						sortable:true,
                        formatter:function (value,row,index) {
                        	return "<span>"+row.price.toFixed(2)+"</span>"
                        }
                    },
                    {
                    	/*visible : false,*/
						field : 'commoditykindsid',
						title : '商品系列',
						align : 'center',
						sortable:true,
						formatter:function (value,row,index) {
							var str="-1";
                        	if(k!=null){
                        		for(var j=0;j<k.length;j++){
                					if(row.commoditykindsid==k[j].id){
                						str=k[j].name+"";
                						break;
                					}
                				}
                        	}
                            return '<span>'+str+'</span>';
	                     }
						
					},
					{
                    	/*visible : false,*/
						field : 'producerid',
						title : '品牌',
						align : 'center',
						sortable:true,
						formatter:function (value,row,index) {
							var str="-1";
                        	if(comm!=null){
                        		for(var j=0;j<comm.length;j++){
                					if(row.producerid==comm[j].id){
                						str=comm[j].name+"";
                						break;
                					}
                				}
                        	}
                            return '<span>'+str+'</span>';
	                     }
						
					},
					 {
                    	visible : false,
						field : 'comefrom',
						title : '货源',
						align : 'center',
						sortable:true
					},
					{
                    	visible : false,
						field : 'ispromotion',
						title : '是否促销',
						sortable:true,
						align : 'center',
						formatter:function (value,row,index) {
							if (row.ispromotion == '0') {
								return '<span class="label label-danger">否</span>';
							} else if (row.ispromotion == '1') {
								return '<span class="label label-primary">是</span>';
							}
	                     }
						
					},
					{
                    	visible : false,
						field : 'promotionprice',
						title : '促销价',
						sortable:true,
						align : 'center',
						formatter:function (value,row,index) {
							return "<span>"+row.promotionprice.toFixed(2)+"</span>"
	                     }
						
					},
					{
                    	visible : false,
						field : 'starttime',
						title : '促销开始时间',
						sortable:true,
						align : 'center',
						formatter:function (value,row,index) {
							var time=row.starttime==null?"":crtTimeFtt(row.starttime);
							return "<span>"+time+"<span>"; 
	                     }
						
					},
					{
                    	visible : false,
						field : 'endtime',
						title : '促销结束时间',
						sortable:true,
						align : 'center',
						formatter:function (value,row,index) {
							var time=row.endtime==null?"":crtTimeFtt(row.endtime);
							return "<span>"+time+"<span>"; 
	                     }
						
					},
					{
                    	visible : false,
						field : 'ishot',
						title : '是否热销',
						sortable:true,
						align : 'center',
						formatter:function (value,row,index) {
							if (row.ishot == '0') {
								return '<span class="label label-danger">否</span>';
							} else if (row.ishot == '1') {
								return '<span class="label label-primary">是</span>';
							}
	                     }
						
					},
					{
						field : 'createtime',
						title : '上市时间',
						sortable:true,
						align : 'center',
						formatter:function (value,row,index) {
							return "<span>"+crtTimeFtt(row.createtime)+"<span>"; 
						}
					
					},
					{
						visible : false,
						field : 'isclose',
						title : '是否下市',
						sortable:true,
						align : 'center',
						formatter:function (value,row,index) {
							if (row.isclose == '0') {
								return '<span class="label label-danger">否</span>';
							} else if (row.ispromotion == '1') {
								return '<span class="label label-primary">是</span>';
							}
						}
					
					},
					
					{
						title : '操作',
						field : 'id',
						align : 'center',
						
						formatter : function(value, row, index) {
							var e = '<a class="btn btn-primary btn-sm '+s_edit_h+'" href="javascript:void(0);" mce_href="javascript:void(0);" title="编辑" onclick="edit(\''
									+ row.id
									+ '\')"><i class="fa fa-edit"></i></a> ';
							var d = '<a class="btn btn-warning btn-sm '+s_remove_h+'" href="javascript:void(0);" title="删除"  mce_href="javascript:void(0);" onclick="remove(\''
									+ row.id
									+ '\')"><i class="fa fa-remove"></i></a> ';
							var f = '<a class="btn btn-success btn-sm '+s_upload_h+'" href="javascript:void(0);" title="图片上传"  mce_href="javascript:void(0);" onclick="upload(\''
									+ row.id
									+ '\')"><i class="fa fa-upload"></i></a> ';
							return e + d +f;
							/*return e + d;*/
						}
					} ]
			});
}
function crtTimeFtt(val) {
    if (val != null) {
            var date = new Date(val);
            return date.getFullYear() + '-' + (date.getMonth() + 1) + '-' + date.getDate();
        }
}
function reLoad() {
	var opt = {
		query : {
			type : $('.chosen-select').val(),
		}
	}
	$('#exampleTable').bootstrapTable('refresh', opt);
}
function add() {
	layer.open({
		type : 2,
		title : '增加',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/add' // iframe的url
	});
}
function edit(id) {
	layer.open({
		type : 2,
		title : '编辑',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/edit/' + id // iframe的url
	});
}
function upload(id){
	layer.open({
		type : 2,
		title : '图片上传',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/upload/' + id // iframe的url
	});
}
function remove(id) {
	layer.confirm('确定要删除选中的记录？', {
		btn : [ '确定', '取消' ]
	}, function() {
		$.ajax({
			url : prefix + "/remove",
			type : "post",
			data : {
				'id' : id
			},
			success : function(r) {
				if (r.code == 0) {
					layer.msg(r.msg);
					reLoad();
				} else {
					layer.msg(r.msg);
				}
			}
		});
	})
}

function addD(type,description) {
	layer.open({
		type : 2,
		title : '增加',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/add/'+type+'/'+description // iframe的url
	});
}
function batchRemove() {
	var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
	if (rows.length == 0) {
		layer.msg("请选择要删除的数据");
		return;
	}
	layer.confirm("确认要删除选中的'" + rows.length + "'条数据吗?", {
		btn : [ '确定', '取消' ]
	// 按钮
	}, function() {
		var ids = new Array();
		// 遍历所有选择的行数据，取每条数据对应的ID
		$.each(rows, function(i, row) {
			ids[i] = row['id'];
			//alert(ids[i]);
		});
		$.ajax({
			type : 'POST',
			data : {
				"ids" : ids
			},
			url : prefix + '/batchRemove',
			success : function(r) {
				if (r.code == 0) {
					layer.msg(r.msg);
					reLoad();
				} else {
					layer.msg(r.msg);
				}
			}
		});
	}, function() {});
}