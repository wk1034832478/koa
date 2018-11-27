package hyywk.top.koa.controllers;
import java.util.Date;
import hyywk.top.koa.entity.Salary;
import hyywk.top.koa.message.ResponseCode;
import hyywk.top.koa.message.ResponseFactory;
import hyywk.top.koa.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SalaryController {

    @Autowired
    private ResponseFactory responseFactory;

    @Autowired
    private SalaryService salaryService;
    @PostMapping("/api/salary/save")
    public String save( Salary salary ) {
       try {
            this.salaryService.save( salary );
            return this.responseFactory.create(  "保存成功," ,ResponseCode.SUCCESS);
        } catch(org.hibernate.exception.ConstraintViolationException e1) {
           return this.responseFactory.create( "很抱歉，还没有在该单位任职咯",ResponseCode.ERROR);
       } catch ( Exception e ) {
            return this.responseFactory.create( String.format( "保存失败,%s", e.getMessage()),ResponseCode.ERROR);
        }
    }

    /**
     * 获取指定日期所在的月份所发放的工资
     * @param deliverDate 指定日期
     * @param id 所属人id
     * @return 工资列表
     */
    @PostMapping("/api/salary/get")
    public String getSalaryByMonth( @RequestParam(value = "deliverDate", required = false ) Date deliverDate, @RequestParam("id") Long id ) {
        try {
            return this.responseFactory.create(  this.salaryService.getSalaryByMonth( deliverDate, id) ,ResponseCode.SUCCESS);
        } catch ( Exception e ) {
            return this.responseFactory.create( String.format( "获取失败,%s", e.getMessage()),ResponseCode.ERROR);
        }
    }

    /**
     * 获取指定日期所在的月份所发放的工资
     * @param id 所属人id
     * @return 工资列表
     */
    @PostMapping("/api/salary/findall")
    public String getAllSalary( @RequestParam("id") Long id ) {
        try {
            return this.responseFactory.create(  this.salaryService.findAllByAccountId( id ) ,ResponseCode.SUCCESS);
        } catch ( Exception e ) {
            e.printStackTrace();
            return this.responseFactory.create( String.format( "获取失败,%s", e.getMessage()),ResponseCode.ERROR);
        }
    }
}
