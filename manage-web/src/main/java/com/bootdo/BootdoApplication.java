package com.bootdo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.github.tobato.fastdfs.FdfsClientConfig;
@Import(FdfsClientConfig.class)
@EnableTransactionManagement
@ServletComponentScan
@MapperScan("com.bootdo.*.dao")
@SpringBootApplication
public class BootdoApplication {
    public static void main(String[] args) {
        SpringApplication.run(BootdoApplication.class, args);
    }
}
