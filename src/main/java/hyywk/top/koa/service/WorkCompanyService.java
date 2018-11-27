package hyywk.top.koa.service;

import hyywk.top.koa.entity.WorkCompany;
import hyywk.top.koa.extension.PageExtension;
import hyywk.top.koa.jpa.WorkCompanyJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class WorkCompanyService extends ServiceParent{
    @Autowired
    private WorkCompanyJpa workCompanyJpa;

    public void save(WorkCompany workCompany ) {
        // 在保存之前
        this.workCompanyJpa.save( workCompany );
    }
    @Transactional
    public void delete(Long work_company_id, Long account_id ) throws Exception{
        // 在保存之前
        if ( this.workCompanyJpa.deleteWorkCompanyByIdAndAccount_Id( work_company_id, account_id) != 1 ) {
            throw new Exception( "该信息已经删除！" );
        }
    }

    /**
     * 获取现在在职的公司
     * @return
     */
    public List<WorkCompany> findAllByAccountIdIsWorking( Long id, String isWorking ) {
        return this.workCompanyJpa.findAllByAccount_IdAndIsWorking( id,  isWorking);
    }

    public PageExtension<WorkCompany> findByAccountIdAndPageInfo(Long id, Integer page, Integer pageSize) {
        PageRequest pageRequest =  this.getPage(page,pageSize);
        PageExtension pageExtension = new PageExtension( this.workCompanyJpa.findAllByAccount_Id(id, pageRequest) , pageRequest.getPageSize() );
        return pageExtension;
    }
        @Transactional
        public void updateIsWork( Long id, Long accountId,String isWorking) throws Exception {
            if ( isWorking == null || ( !isWorking.equals("是") && !isWorking.equals("否") ) ) {
                throw new Exception( "数据格式错误" );
            }
        if ( this.workCompanyJpa.updateIsWork(id, accountId, isWorking) != 1) {
            throw new Exception( "更新失败" );
        }
    }


}
