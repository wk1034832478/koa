package hyywk.top.koa.controllers;

import hyywk.top.koa.entity.PersonAccount;
import hyywk.top.koa.message.ResponseCode;
import hyywk.top.koa.message.ResponseFactory;
import hyywk.top.koa.service.PersonAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PersonAccountController {
    @Autowired
    private PersonAccountService personAccountService;
    @Autowired
    private ResponseFactory responseFactory;
    @PostMapping("/api/pas/new")
    public Object save(PersonAccount personAccount) {
        if( personAccount.getUsername() == null) {
            return responseFactory.create("用户名不可为空！",ResponseCode.ERROR);
        }
        PersonAccount personAccount1 = this.personAccountService.saveOrUpdate( personAccount );
        if ( personAccount1 == null ) {
            return responseFactory.create("保存失败",ResponseCode.ERROR);
        }
        return responseFactory.create(personAccount1,ResponseCode.SUCCESS);
    }
    @PostMapping("/api/pas/presearch")
    public String presearch(@RequestParam("username") String username, @RequestParam("id") Long id) {
        return responseFactory.create(this.personAccountService.presearch(username,id),ResponseCode.SUCCESS);
    }

    @PostMapping("/api/pas/search")
    public String search(@RequestParam("username") String username,@RequestParam(value = "email", required = false) String email,
                         @RequestParam(value = "phonenumber", required = false) String phonenumber,
                         @RequestParam("id") Long id,@RequestParam(value = "pageIndex", required = false) Integer pageIndex,
                         @RequestParam(value = "pageSize",required = false) Integer pageSize) {
        try {
            return responseFactory.create(this.personAccountService.search(username,id,email,phonenumber,pageIndex,pageSize),ResponseCode.SUCCESS);
        } catch (Exception e){
            return responseFactory.create( e.getMessage(),ResponseCode.ERROR);
        }
    }

    @PostMapping("/api/pas/delete")
    public String delete( @RequestParam("id") Long id ) {
        try {
            this.personAccountService.deleteOne(id);
            return responseFactory.create( "删除成功",ResponseCode.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return responseFactory.create( e.getMessage(),ResponseCode.ERROR);
        }
    }
    @PostMapping("/api/pas/usp")
    public String usePercent( @RequestParam("id") Long id ) {
        try {
            return responseFactory.create(  this.personAccountService.getCanUsePercent( id ) ,ResponseCode.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return responseFactory.create( e.getMessage(),ResponseCode.ERROR);
        }
    }
    @PostMapping("/api/pas/distribution")
    public String accountDistribution(  @RequestParam("id") Long id  ) {
        try {
            List list = this.personAccountService.accountDistribution( id );
            if ( list.size() == 0 ) {
                return responseFactory.create(  list ,ResponseCode.LOST);
            }
            return responseFactory.create(  list ,ResponseCode.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return responseFactory.create( e.getMessage(),ResponseCode.ERROR);
        }
    }

}
