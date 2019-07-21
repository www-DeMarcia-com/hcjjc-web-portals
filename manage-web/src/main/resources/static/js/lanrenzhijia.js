 /* 代码整理：懒人之家 www.lanrenzhijia.com */
$(function(){
	// 初始化插件
	$("#demo").zyUpload({
		width            :   "100%",                 // 宽度
		height           :   "100%",                 // 宽度
		itemWidth        :   "150%",                 // 文件项的宽度
		itemHeight       :   "100%",                 // 文件项的高度
		url              :   "picupload",  // 上传文件的路径
		fileType         :   ["bmp", "jpg", "jpeg", "png", "tiff", "gif", "pcx", "tga", "exif", "fpx", "svg", "psd","cdr", "pcd", "dxf", "ufo", "eps", "ai", "raw", "wmf"],// 上传文件的类型
		fileSize         :   51200000,                // 上传文件的大小
		multiple         :   true,                    // 是否可以多个文件上传
		dragDrop         :   true,                    // 是否可以拖动上传文件
		del              :   true,                    // 是否可以删除文件
		finishDel        :   false,  				  // 是否在上传文件完成后删除预览
		/* 外部获得的回调接口 */
		onSelect: function(files, allFiles){                    // 选择文件的回调方法
			/*console.info("当前选择了以下文件：");
			console.info(files);
			console.info("之前没上传的文件：");
			console.info(allFiles);*/
			//alert("111");
		},
		onDelete: function(file, surplusFiles){                     // 删除一个文件的回调方法
			/*console.info("当前删除了此文件：");
			console.info(file);
			console.info("当前剩余的文件：");
			console.info(surplusFiles);*/
			alert("222");
		},
		onSuccess: function(file, response){                    // 文件上传成功的回调方法
			/*console.info("此文件上传成功：");
			console.info(file);*/
			//alert("333");
		},
		onFailure: function(file){                    // 文件上传失败的回调方法
			/*console.info("此文件上传失败：");
			console.info(file);*/
			//alert("444");
		},
		onComplete: function(response){           // 上传完成的回调方法
			/*console.info("文件上传完成");
			console.info(responseInfo);*/
			alert("555");
		}
	});
});

