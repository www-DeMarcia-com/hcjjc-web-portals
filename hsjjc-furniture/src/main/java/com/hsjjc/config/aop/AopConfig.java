package com.hsjjc.config.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.Model;


import com.hsjjc.service.CompanyService;

@Aspect
@Configuration
public class AopConfig {
	
	@Autowired
	CompanyService companyService;
	@Value("${Company.id}")
	Long companyid;

	// 定义切点Pointcut
	@Pointcut("execution(* com.hsjjc.controller.*.*(..))")
	public void excudeService() {}

	@Around("excudeService()")
	public void doBefore(ProceedingJoinPoint pjp) throws Throwable {
		Object[]obj=pjp.getArgs();
		if(obj!=null&&obj.length>0) {
			Model model=(Model) obj[0];
			model.addAttribute("company",companyService.findOneById(companyid));	
		}
		pjp.proceed();
	}
}
