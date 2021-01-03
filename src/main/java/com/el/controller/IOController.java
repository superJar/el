package com.el.controller;

import com.el.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author:superJar
 * @date:2021/1/1
 * @time:15:31
 * @details:
 */
@Slf4j
@RestController
@RequestMapping("/io")
public class IOController {

    //导入
    @PostMapping("/import")
    public Result fileImport(@RequestBody MultipartFile file){

        try {

            //处理逻辑...
            XSSFWorkbook wb =new XSSFWorkbook();


            return Result.ok();
        } catch (Exception e) {
            log.error("操作失败！{}", e.getMessage(), e);
            return Result.fail("操作失败！");
        }
    }

    public static void main(String[] args) {
        PasswordEncoder p = new BCryptPasswordEncoder();
        System.out.println(p.encode("666"));
    }
}
