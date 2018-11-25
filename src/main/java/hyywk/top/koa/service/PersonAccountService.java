package hyywk.top.koa.service;

import hyywk.top.koa.entity.PersonAccount;
import hyywk.top.koa.jpa.PersonAccountJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonAccountService {
    private final Integer DEFAULT_PAGE_INDEX = 0;
    private final Integer DEFAULT_PAGE_SIZE = 20;
    @Autowired
    private PersonAccountJpa personAccountJpa;
    /**
     * 搜索结果数量
     * @param username
     * @param id
     * @return
     */
    public List<String[]> presearch( String username ,  Long id) {
        return this.personAccountJpa.presearch( new StringBuffer( "%").append( username).append( "%").toString(), id );
    }

    public List<PersonAccount> search(String username, Long id, String email, String phonenumber, Integer pageIndex, Integer pageSize ) throws Exception{
        if ( id == null ) {
            throw new Exception( "缺失查询的id" );
        }
        if ( email != null && phonenumber != null) {
            return this.personAccountJpa.search( getPercentKey( username ) , getPercentKey( email ),  getPercentKey( phonenumber ), id, getPage( pageIndex, pageSize ) );
        }
        if ( email != null ) {
            return this.personAccountJpa.search( getPercentKey( username ) , getPercentKey( email ),id, getPage( pageIndex, pageSize ) );
        }
        if ( phonenumber != null ) {
            return this.personAccountJpa.search(id,  getPercentKey( username ) , getPercentKey( phonenumber ), getPage( pageIndex, pageSize ) );
        }
        return this.personAccountJpa.search( getPercentKey( username ), id, getPage( pageIndex, pageSize ) );
    }

    public String getPercentKey(String key){
        return  new StringBuffer( "%").append( key).append( "%").toString();
    }

    public PersonAccount saveOrUpdate( PersonAccount personAccount ) {
        try {
            return this.personAccountJpa.save(personAccount);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<String[]> accountDistribution( Long id ) {
        return this.personAccountJpa.distribution( id );
    }

    public Double getCanUsePercent(Long id) {
        return this.personAccountJpa.getCanUsePercent(id);
    }

    public void deleteOne( Long id ) {
        this.personAccountJpa.deleteById(id);
    }

    /**
     *生成页数 index > 1 并且 size > 0
     * @param index 页索引
     * @param size 一页的个数
     */
    public Pageable getPage( Integer index, Integer size ) {
        if ( index == null ) {
            index = this.DEFAULT_PAGE_INDEX;
        }
        if ( size == null ){
            size = this.DEFAULT_PAGE_SIZE;
        }
        return PageRequest.of( index, size );
    }
}
