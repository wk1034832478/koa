package hyywk.top.koa.controllers;

import hyywk.top.koa.entity.Account;
import hyywk.top.koa.entity.BasicInfo;
import hyywk.top.koa.message.ResponseCode;
import hyywk.top.koa.message.ResponseFactory;
import hyywk.top.koa.service.AccountService;
import hyywk.top.koa.service.BasicInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.InputStream;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class BasicInfoController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private BasicInfoService basicInfoService;
    @Autowired
    private HttpServletRequest httpServletRequest;
    @Autowired
    private ResponseFactory responseFactory;
    private Logger logger = LoggerFactory.getLogger( BasicInfoController.class);
    @PostMapping("api/date/get")
    public Object getDate(@RequestParam Date date) {
        this.logger.info("获取日期:{}", date );
        return responseFactory.create("ok", ResponseCode.SUCCESS);
    }

    /**
     * 获取用户基本信息
     * @param id 用户的id
     * @return
     */
    @PostMapping("/api/basicinfo/get")
    public Object getBasicInfo(@RequestParam("id") Long id) {
        try {
            BasicInfo basicInfo = this.basicInfoService.findByAccount( id );
            if ( basicInfo == null ) {
                return responseFactory.create(basicInfo, ResponseCode.LOST);
            }
            return responseFactory.create(basicInfo, ResponseCode.SUCCESS);
        } catch (Exception e) {
            // 还没有完善账户信息
            return responseFactory.create("获取失败", ResponseCode.ERROR);
        }
    }
    @PostMapping("/api/basicinfo/update")
    public Object updateBasicInfo( BasicInfo basicInfo) {
        try {
            BasicInfo basicInfo2 = this.basicInfoService.updateBasicInfo(basicInfo);
            return this.responseFactory.create(basicInfo2, ResponseCode.SUCCESS);
        } catch (Exception e ) {
            e.printStackTrace();
            return this.responseFactory.create("修改失败", ResponseCode.ERROR);
        }
    }

    @PostMapping("/api/account/get")
   public Object getAccountInfo() {
        String username = this.httpServletRequest.getRemoteUser();
        if ( username == null) {
            return responseFactory.create("用户还未登陆", ResponseCode.LOGINOUT);
        }
        Account account = this.accountService.getAccountNoPassword( username );
       return responseFactory.create(account, ResponseCode.SUCCESS);
   }

    @PostMapping("/api/basic/photo-upload")
    public String uploadPhoto(@RequestParam(value = "photo", required = true) MultipartFile photo, @RequestParam("id") Long id ) {
        try {
            this.basicInfoService.savePhoto( photo.getOriginalFilename(), photo.getInputStream(), id);
            return responseFactory.create("上传成功" , ResponseCode.SUCCESS);
        } catch( Exception e ) {
            e.printStackTrace();
            return responseFactory.create("上传失败，" + e.getMessage() , ResponseCode.ERROR);
        }
    }
    @GetMapping("/api/basic/photo")
    public InputStream getPhoto(@RequestParam(value = "photo", required = true) String photo, @RequestParam("id") Long id, HttpServletResponse response) {
        try {
            this.logger.info( "请求图片：" + photo );
            this.basicInfoService.outputPhoto( photo,  id , response.getOutputStream() );
        } catch (Exception e ) {
            e.printStackTrace();
        }
        return null;
    }

}
