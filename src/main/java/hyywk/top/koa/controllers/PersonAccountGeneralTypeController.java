package hyywk.top.koa.controllers;

import hyywk.top.koa.entity.PersonAccountGeneralType;
import hyywk.top.koa.message.ResponseCode;
import hyywk.top.koa.message.ResponseFactory;
import hyywk.top.koa.message.ResponseMessage;
import hyywk.top.koa.service.PersonAccountGeneralTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PersonAccountGeneralTypeController {
    @Autowired
    private PersonAccountGeneralTypeService personAccountGeneralTypeService;
    @Autowired
    private ResponseFactory responseFactory;
    @PostMapping("/api/pagt/new")
    public Object save( PersonAccountGeneralType personAccountGeneralType ) {
        PersonAccountGeneralType personAccountGeneralType1 = this.personAccountGeneralTypeService.save( personAccountGeneralType );
        if ( personAccountGeneralType1 == null) {
            return this.responseFactory.create(String.format("添加错误,已经存在【%s】", personAccountGeneralType.getName()), ResponseCode.ERROR);
        }
        return this.responseFactory.create(personAccountGeneralType1, ResponseCode.SUCCESS);
    }
    @PostMapping("/api/pagt/all")
    public String findAll() {
        List<PersonAccountGeneralType> personAccountGeneralTypes = this.personAccountGeneralTypeService.findAll();
        if ( personAccountGeneralTypes == null) {
            return this.responseFactory.create( "内容获取失败", ResponseCode.ERROR);
        }
        return this.responseFactory.create( personAccountGeneralTypes , ResponseCode.SUCCESS);
    }
}
