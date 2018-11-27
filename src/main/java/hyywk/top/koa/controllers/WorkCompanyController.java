package hyywk.top.koa.controllers;

import hyywk.top.koa.entity.WorkCompany;
import hyywk.top.koa.extension.PageExtension;
import hyywk.top.koa.message.PageMessage;
import hyywk.top.koa.message.ResponseCode;
import hyywk.top.koa.message.ResponseFactory;
import hyywk.top.koa.service.WorkCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ConstraintViolationException;

@RestController
public class WorkCompanyController {

    @Autowired
    private WorkCompanyService workCompanyService;
    @Autowired
    private ResponseFactory responseFactory;
    @PostMapping("/api/workcompany/save")
    public String save( WorkCompany workCompany ) {
        try {
            this.workCompanyService.save(workCompany);
            return responseFactory.create("保存成功", ResponseCode.SUCCESS);
        }catch(DataIntegrityViolationException e1){
            return responseFactory.create( "保存失败,该公司信息已经上传过啦！", ResponseCode.ERROR );
        } catch (Exception e) {
            e.printStackTrace();
            return responseFactory.create( String.format("保存失败,%s", e.getMessage()), ResponseCode.ERROR );
        }
    }
    @PostMapping("/api/workcompany/find_aid_isworking")
    public String findAllByAccountIdIsWorking(@RequestParam("id")  Long id, @RequestParam("isWorking") String isWorking) {
        try {
            return responseFactory.create(this.workCompanyService.findAllByAccountIdIsWorking(id,isWorking), ResponseCode.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return responseFactory.create( String.format("获取失败,%s", e.getMessage()), ResponseCode.ERROR );
        }
    }

    @PostMapping("/api/workcompany/delete")
    public String delete( Long id1, Long id2 ) {
        try {
            this.workCompanyService.delete( id1,id2 );
            return responseFactory.create( "删除成功", ResponseCode.SUCCESS );
        } catch (Exception e) {
            e.printStackTrace();
            return responseFactory.create( String.format("保存失败,%s", e.getMessage()), ResponseCode.ERROR );
        }
    }
    @PostMapping("/api/workcompany/find")
    public String getWorkCompanies(@RequestParam("id") Long id,@RequestParam( value = "page", required = false)
            Integer page,@RequestParam(value = "pageSize", required = false) Integer pageSize) {
                try {
            PageExtension pageExtension = this.workCompanyService.findByAccountIdAndPageInfo( id, page, pageSize);
            PageMessage pageMessage = new PageMessage( pageExtension.getPage().getTotalPages(), pageExtension.getPage().getTotalElements(), pageExtension.getPageSize() );
            return responseFactory.create( pageExtension.getPage().getContent(), pageMessage, ResponseCode.SUCCESS );
        } catch (Exception e) {
            e.printStackTrace();
            return responseFactory.create( String.format("保存失败,%s", e.getMessage()), ResponseCode.ERROR );
        }
    }

    @PostMapping("/api/workcompany/working")
    public String working(@RequestParam("id") Long id, @RequestParam("workCompanyId") Long workCompanyId, @RequestParam("isWorking") String isWorking) {
        try {
            this.workCompanyService.updateIsWork(workCompanyId, id, isWorking );
            return responseFactory.create( "更新成功！", ResponseCode.SUCCESS );
        } catch (Exception e) {
            e.printStackTrace();
            return responseFactory.create( String.format("更新失败,%s", e.getMessage()), ResponseCode.ERROR );
        }
    }

}
