package hyywk.top.koa.service;

import hyywk.top.koa.entity.Salary;
import hyywk.top.koa.jpa.SalaryJpa;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SalaryService {
    private final int DEFAULT_PAGE_SIZE = 10;
    @Autowired
    private SalaryJpa salaryJpa;

    private Logger logger = LoggerFactory.getLogger( SalaryService.class );

    public void save( Salary salary) throws org.hibernate.exception.ConstraintViolationException,Exception{
        try {
        // 判断该公司该月是否已经发过工资，如果发过，则拒绝请求
        if ( salary.getDeliverDate() == null ) {
            salary.setDeliverDate( new Date() );
        }
        Date[] dates = this.getCurrentMonthDates( salary.getDeliverDate() );
        if ( this.salaryJpa.findSalariesByDeliverDatesAndWorkCompanyId(  dates[0], dates[1], salary.getAccount().getId(), salary.getWorkCompany().getId()).size() > 0 ) {
            throw new Exception( "本月该公司已经给你发过工资呢，如果需要追加，请使用追加接口！" );
        }
            this.salaryJpa.save( salary );
        } catch (org.hibernate.exception.ConstraintViolationException e) {
            throw e;
        }
    }



    public  List<Salary> findAllByAccountId( Long id ) {
        List<Salary> salaries = this.salaryJpa.findSalariesByAccountIdOrderByDeliverDateAsc( id );
        /**
         * 对薪资进行分类 和 排序
         */
//        Map<Long, List<Salary>> map = new HashMap<>();
//        for ( int i = 0; i < salaries.size(); i++ ) {
//            Salary salary = salaries.get(i);
//            List<Salary> salaryList = map.get( salary.getWorkCompany().getId() ) ; // 以公司的id作为主键
//            if ( salaryList == null ) {
//                salaryList = new ArrayList<>();
//            }
//            salary.getWorkCompany().setAccount( null ); // 清除账户信息
//            salaryList.add( salary );
//            map.put( salary.getWorkCompany().getId(), salaryList );
//        }
        return salaries;
    }
// 按页查询
//    public Page<Salary> findAllByAccountId(Long id, Integer page, Integer pageSize ) {
//        List<Salary> salaries = this.salaryJpa.find( id );
//        return salaries;
//    }


    /**
     * 根据月份获取工资
     */
    public List<Salary> getSalaryByMonth(Date date, Long id) {
        if ( date == null ) {
            date = new Date();
            this.logger.info( "当前时间戳：" + date.getTime() );
        }
        Date[] ds = this.getCurrentMonthDates( date );
        return this.salaryJpa.findSalariesByDeliverDates ( ds[0], ds[1] ,id );
    }

    public Date[] getCurrentMonthDates( Date date ) {
        int startYear = date.getYear();
        int startMonth = date.getMonth();
        int endYear;
        int endMonth;
        if ( startMonth == 12 ) {
            endYear = startYear + 1;
            endMonth = 1;
        } else {
            endYear = startYear;
            endMonth = startMonth + 1;
        }
        return new Date[]{ new Date( startYear, startMonth, 1), new Date( endYear, endMonth, 1) };
    }
}
