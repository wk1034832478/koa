package hyywk.top.koa.jpa;
import hyywk.top.koa.entity.Salary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public interface SalaryJpa extends JpaRepository<Salary, Long> {
    /**
     * 根据月份获取发放的工资
     * @return 发放的工资
     */
    @Query(" SELECT new Salary (id,mount,deliverDate,workCompany) " +
            " FROM Salary e " +
            " WHERE e.account.id = :id  AND  e.deliverDate BETWEEN :start AND :end ")
    List<Salary> findSalariesByDeliverDates( @Param("start") Date start, @Param("end")  Date end, @Param("id")  Long id);

    @Query(" SELECT new Salary (id,mount,deliverDate,workCompany) " +
            " FROM Salary e " +
            " WHERE e.account.id = :id  AND e.workCompany.id = :wid AND e.deliverDate BETWEEN :start AND :end ")
    List<Salary> findSalariesByDeliverDatesAndWorkCompanyId( @Param("start") Date start, @Param("end")  Date end, @Param("id")  Long id, @Param("wid")  Long wid);

    @Query(" SELECT new Salary (id,mount,deliverDate,workCompany) " +
            " FROM Salary e " +
            " WHERE e.account.id = :id  ORDER BY e.deliverDate ASC")
    List<Salary> findSalariesByAccountIdOrderByDeliverDateAsc(@Param("id") Long id);

}
