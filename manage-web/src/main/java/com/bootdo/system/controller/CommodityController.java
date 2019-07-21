package com.bootdo.system.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.bootdo.common.annotation.Log;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.system.domain.CommodityDO;
import com.bootdo.system.domain.PictureDO;
import com.bootdo.system.service.CommodityKindsService;
import com.bootdo.system.service.CommodityService;
import com.bootdo.system.service.PictureService;
import com.bootdo.system.service.ProducerService;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;


@RequestMapping("/web/commodity")
@Controller
public class CommodityController {
	private String prefix="system/commodity" ;
	@Autowired
	CommodityService kindsService;
	@Autowired
	ProducerService service ;

	@Autowired
	CommodityKindsService kinds ;

	@Autowired
	PictureService picture;

	@Autowired
	public FastFileStorageClient fastFileStorageClient;

	@Value("${nginx.port}")
	String port;
	@Value("${nginx.ip}")
	String ip;
	@GetMapping()
	@RequiresPermissions("web:commodity:commodity")
	public String infonewskinds(Model model) {
		return prefix+"/commodity";
	}
	@ResponseBody
	@GetMapping("/commkindslist")
	@RequiresPermissions("web:commodity:commodity")
	public Map<String ,Object> commkindslist() {
		Map<String,Object>map=new HashMap<String,Object>();
		map.put("producer", service.list(new HashMap<String,Object>()));
		map.put("kinds", kinds.list(new HashMap<String,Object>()));
		return map;
		//return service.list(new HashMap<String,Object>());
	}


	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("web:commodity:commodity")
	public PageUtils list(@RequestParam Map<String, Object> params) {
		// 查询列表数据
		Query query = new Query(params);
		List<CommodityDO> kindsList = kindsService.list(query);
		int total = kindsService.count(query);
		PageUtils pageUtils = new PageUtils(kindsList, total);
		return pageUtils;
	}

	//只需要加上下面这段即可，注意不能忘记注解  
	@InitBinder  
	public void initBinder(WebDataBinder binder, WebRequest request) {  

		//转换日期  
		DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm");  
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));// CustomDateEditor为自定义日期编辑器  
	} 

	@GetMapping("/add")
	@RequiresPermissions("web:commodity:add")
	public String add(Model model) {
		model.addAttribute("producer", service.list(new HashMap<String,Object>()));
		model.addAttribute("commkinds", kinds.list(new HashMap<String,Object>()));
		return prefix+"/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("web:commodity:edit")
	public String edit(@PathVariable("id") Long id, Model model) {
		CommodityDO kinds1 = kindsService.get(id);
		if(kinds1==null) {
			kinds1=new CommodityDO();
		}
		model.addAttribute("producer", service.list(new HashMap<String,Object>()));
		model.addAttribute("commkinds", kinds.list(new HashMap<String,Object>()));
		model.addAttribute("kinds", kinds1);
		return  prefix + "/edit";
	}

	@GetMapping("/upload/{id}")
	@RequiresPermissions("web:commodity:pictupload")
	public String pictupload(@PathVariable("id") Long id, Model model) {
		CommodityDO kinds1 = kindsService.get(id);
		if(kinds1==null) {
			kinds1=new CommodityDO();
		}
		model.addAttribute("kinds", kinds1);
		Map<String,Object>map=new HashMap<String,Object>();
		map.put("commodityid", id);
		List<PictureDO>list=picture.list(map);
		model.addAttribute("pict", list);
		model.addAttribute("nginx","http://"+ip+":"+port+"/");
		return  prefix + "/pictupload";
	}
	@Log("上传图片")
	@ResponseBody
	@PostMapping("/upload/picupload")
	@RequiresPermissions("web:commodity:pictupload")
	public R uploda(HttpServletRequest request) {
		synchronized (this) {
			String commodityId=request.getParameter("commodityId");
			Map<String ,Object>map=new HashMap<String,Object>();
			map.put("commodityid", Long.parseLong(commodityId));
			map.put("rank",1);
			List<PictureDO> list=picture.list(map);
			CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext()); 
			if(multipartResolver.isMultipart(request)){  
				//转换成多部分request    
				MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
				// 取得request中的所有文件名
				Iterator<String> iter = multiRequest.getFileNames();
				while (iter.hasNext()) {
					try {
						// 取得上传文件
						MultipartFile file = multiRequest.getFile(iter.next());
						// 数据封装操作 MultipartFile reqeust
						// 取得当前上传文件的文 件名称
						StorePath storePath = fastFileStorageClient.uploadFile(file.getInputStream(),file.getSize(), FilenameUtils.getExtension(file.getOriginalFilename()),null);
						PictureDO pict=new PictureDO();
						pict.setCommodityid(Long.parseLong(commodityId));
						pict.setUrl(storePath.getFullPath());
						if(list!=null&&list.size()>0) {
							int rank =picture.getMaxRank(Long.parseLong(commodityId))+1;
							pict.setRank(rank);
						}else {
							pict.setRank(2);
						}
						picture.save(pict);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

			}
		}
		return R.ok();
	}

	@Log("删除图片")
	@ResponseBody
	@PostMapping("/picture/remove")
	@RequiresPermissions("web:commodity:pictupload")
	public R removepic(@RequestParam("commodityid")Long id) {
		/*Map<String,Object>map=new HashMap<String,Object>();
		map.put("id", id);*/
		//map.put(key, value)
		PictureDO pic=picture.get(id);
		if(pic!=null) {
			int i=picture.remove(id);
			if(i>0) {
				fastFileStorageClient.deleteFile(pic.getUrl());
			}else {
				return R.error(1, "删除数据记录失败");
			}
		}else {
			return R.error(1, "当前图片不存在");
		}
		return R.ok();
	}




	/**
	 * 保存
	 */
	@Log("添加商品")
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("web:commodity:add")
	public R save(CommodityDO kinds) {
		//System.out.println(kinds);
		if (kindsService.save(kinds) > 0) {
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 修改
	 */
	@Log("修改商品")
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("web:commodity:edit")
	public R update(CommodityDO kinds) {
		if (kindsService.update(kinds) > 0) {
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 删除
	 */
	@Log("删除商品")//删除商品同时液压删除图片
	@PostMapping("/remove")
	@ResponseBody
	@RequiresPermissions("web:commodity:remove")
	public R remove(Long id) {
		Map <String,Object>map=new HashMap<String,Object>();
		map.put("commodityid", id);
		List<PictureDO>list=picture.list(map);
		for(PictureDO pic:list) {
			if(pic!=null) {
				try {
					fastFileStorageClient.deleteFile(pic.getUrl());
				}catch (Exception e) {
				}
				picture.remove(pic.getId());
			}
		}
		if (kindsService.remove(id) > 0) {
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 删除
	 */
	@Log("批量删除商品")//删除商品同时液压删除图片
	@PostMapping("/batchRemove")
	@ResponseBody
	@RequiresPermissions("web:commodity:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids) {
		for(Long id:ids) {
			Map <String,Object>map=new HashMap<String,Object>();
			map.put("commodityid", id);
			List<PictureDO>list=picture.list(map);
			for(PictureDO pic:list) {
				if(pic!=null) {
					try {
						fastFileStorageClient.deleteFile(pic.getUrl());
					}catch (Exception e) {

					}
					picture.remove(pic.getId());
				}

			}
		}
		kindsService.batchRemove(ids);
		return R.ok();
	}



	//LinkedList<FileMeta> files = new LinkedList<FileMeta>();
	//FileMeta fileMeta = null;
	/***************************************************
	 * URL: /rest/controller/upload  
	 * upload(): receives files
	 * @param request : MultipartHttpServletRequest auto passed
	 * @param response : HttpServletResponse auto passed
	 * @return LinkedList<FileMeta> as json format
	 ****************************************************/
	@Log("修改首页图片")
	@PostMapping("/first/upload")
	@ResponseBody
	@RequiresPermissions("web:commodity:pictupload")
	public R upload(MultipartHttpServletRequest request, HttpServletResponse response) {
		String id=request.getParameter("commodityid")==null?"":request.getParameter("commodityid");
		List<PictureDO>list=null;
		if(!"".equals(id)) {
			Map <String,Object>map=new HashMap<String,Object>();
			map.put("commodityid", Long.parseLong(id));
			map.put("rank", 1);
			list=picture.list(map);
		}else {
			list=new ArrayList<PictureDO>();
		}
		Iterator<String> itr =  request.getFileNames();
		MultipartFile file = null;
		while(itr.hasNext()){
			try {
				//2.1 get next MultipartFile
				file = request.getFile(itr.next()); 
				StorePath storePath = fastFileStorageClient.uploadFile(file.getInputStream(),file.getSize(), FilenameUtils.getExtension(file.getOriginalFilename()),null);
				if(list!=null&&list.size()>0) {
					PictureDO pict=list.get(0);
					fastFileStorageClient.deleteFile(pict.getUrl());
					pict.setUrl(storePath.getFullPath());
					picture.update(pict);
				}else {
					PictureDO pict=new PictureDO();
					pict.setCommodityid(Long.parseLong(id));
					pict.setRank(1);
					pict.setUrl(storePath.getFullPath());
					picture.save(pict);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return R.ok();
	}
	/***************************************************
	 * URL: /rest/controller/get/{value}
	 * get(): get file as an attachment
	 * @param response : passed by the server
	 * @param value : value from the URL
	 * @return void
	 ****************************************************/
	/* @RequestMapping(value = "/get/{value}", method = RequestMethod.GET)
     public void get(HttpServletResponse response,@PathVariable String value){
         FileMeta getFile = files.get(Integer.parseInt(value));
         try {      
                response.setContentType(getFile.getFileType());
                response.setHeader("Content-disposition", "attachment; filename=\""+getFile.getFileName()+"\"");
                FileCopyUtils.copy(getFile.getBytes(), response.getOutputStream());
         }catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
         }
     }*/



}
