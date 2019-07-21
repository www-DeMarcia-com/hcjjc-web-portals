package com.hsjjc.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hsjjc.model.InfoNews;
import com.hsjjc.model.InfoNewsKinds;
import com.hsjjc.service.InfoNewsKindsService;
import com.hsjjc.service.InfoNewsService;

//@RestController
public class Test {
	@Autowired
	InfoNewsKindsService infonewskinds;
	@Autowired
	InfoNewsService infonewsService;
	//@RequestMapping("/aaa")
	public void insertBlob() throws UnsupportedEncodingException, ParseException {
		FileReader reader =null;
		BufferedReader bufferedReader =null;

		String str="";
		String str2="";
		try{
			reader = new FileReader("D:\\spring-workspace\\hsjjc-furniture\\src\\main\\java\\111.txt");    
			bufferedReader = new BufferedReader(reader);
			String line =null;
			while(true){
				line =bufferedReader.readLine();

				if(line ==null){
					break;
				}
				str=str+line;
			}
			reader = new FileReader("D:\\spring-workspace\\hsjjc-furniture\\src\\main\\java\\222.txt");    
			bufferedReader = new BufferedReader(reader);
			line =null;
			while(true){
				line =bufferedReader.readLine();

				if(line ==null){
					break;
				}
				str2=str2+line;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try{
				bufferedReader.close();
				reader.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		List<InfoNewsKinds> kind=infonewskinds.queryAllInfoNewsKinds();
		for(int q=0;q<2;q++) {
			for(int i=1;i<31;i++) {
				InfoNews info=new InfoNews();
				info.setContent(str2);
				info.setContentgg(str.length()>100?str.substring(0, 101)+"...":str);
				int j=(int) (Math.random()*kind.size());
				InfoNewsKinds k=kind.get(j);
				info.setInfonewskinds(k);
				info.setTags("卡号的，沙发上，第三发");
				info.setTitle(i+""+j);
				/*int m=(int) (Math.random()*12+1);
				int d=(int) (Math.random()*30+1);*/
				String M;
				if(q==0) {
					M="08";
				}else {
					M="09";
				}
				String D=i>9?i+"":"0"+i;
				String s="2017-"+M+"-"+D;
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				info.setTime(sdf.parse(s));
				info= infonewsService.insertNews(info);
			}
		}
	}

}
