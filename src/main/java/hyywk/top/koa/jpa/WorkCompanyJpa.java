package hyywk.top.koa.jpa;
import hyywk.top.koa.entity.WorkCompany;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WorkCompanyJpa extends JpaRepository<WorkCompany, Long>{


    int deleteWorkCompanyByIdAndAccount_Id( Long id1, Long id2);

    Page<WorkCompany> findAllByAccount_Id(Long id, Pageable pageable);

    List<WorkCompany> findAllByAccount_IdAndIsWorking(Long id, String isWorking);

    /**
     * 更新用户在职状态
     * @param id
     * @param accountId
     * @param isWorking
     * @return
     */
    @Modifying
    @Query( "UPDATE WorkCompany w SET w.isWorking =  :isWorking WHERE w.id = :id AND w.account.id = :accountId" )
    int updateIsWork(@Param("id") Long id, @Param("accountId") Long accountId, @Param("isWorking") String isWorking);
}
