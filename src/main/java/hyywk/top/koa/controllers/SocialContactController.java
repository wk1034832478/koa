package hyywk.top.koa.controllers;

import hyywk.top.koa.entity.PersonDynamic;
import hyywk.top.koa.entity.Salary;
import hyywk.top.koa.extension.PageExtension;
import hyywk.top.koa.message.PageMessage;
import hyywk.top.koa.message.ResponseCode;
import hyywk.top.koa.message.ResponseFactory;
import hyywk.top.koa.service.PersonDynamicService;
import hyywk.top.koa.service.SocialContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

@RestController
public class SocialContactController {

    @Autowired
    private ResponseFactory responseFactory;

    @Autowired
    private SocialContactService socialContactService;
    @Autowired
    private PersonDynamicService personDynamicService;

    private PageExtension pageExtension;

    @PostMapping("/api/social-contact/dynamic-get")
    public String find(@RequestParam("page") Integer page,@RequestParam("pageSize") Integer pageSize) {
        try {
            pageExtension = this.personDynamicService.findAllAndPage(  page, pageSize );
            PageMessage pageMessage = new PageMessage(pageExtension.getPage().getTotalPages(), pageExtension.getPage().getTotalElements(),
                    pageExtension.getPageSize(), pageExtension.getPageNumber());
            return this.responseFactory.create(pageExtension.getPage().getContent(), pageMessage ,ResponseCode.SUCCESS );
        } catch ( Exception e ) {
            return this.responseFactory.create( String.format( "保存失败,%s", e.getMessage()),ResponseCode.ERROR);
        }
    }

    @PostMapping("/api/social-contact/dynamic-add")
    public String save(PersonDynamic personDynamic) {
        try {
            this.personDynamicService.save( personDynamic );
            return this.responseFactory.create(  "保存成功",ResponseCode.SUCCESS );
        } catch ( Exception e ) {
            return this.responseFactory.create( String.format( "保存失败,%s", e.getMessage()),ResponseCode.ERROR);
        }
    }

    @PostMapping("/api/social-contact/picture-add")
    public String save(@RequestParam("picture") MultipartFile picture) {
        try {
            String newName = this.socialContactService.savePicture( picture.getInputStream(), picture.getOriginalFilename() );
            return this.responseFactory.create(  "/api/social-contact/picture-get?picture=" + newName,ResponseCode.SUCCESS );
        } catch ( Exception e ) {
            return this.responseFactory.create( String.format( "保存失败,%s", e.getMessage()),ResponseCode.ERROR);
        }
    }

    @GetMapping("/api/social-contact/picture-get")
    public String get(String picture, HttpServletResponse response) {
        try {
            this.socialContactService.readPicture( picture , response.getOutputStream() );
        } catch ( Exception e ) {
            return this.responseFactory.create( String.format( "保存失败,%s", e.getMessage()),ResponseCode.ERROR);
        }
        return null;
    }


}
