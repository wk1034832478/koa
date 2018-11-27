package hyywk.top.koa.service;

import hyywk.top.koa.entity.PersonDynamic;
import hyywk.top.koa.extension.PageExtension;
import hyywk.top.koa.jpa.PersonDynamicJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class PersonDynamicService extends ServiceParent{
    @Autowired
    private PersonDynamicJpa personDynamicJpa;

    public void save(PersonDynamic personDynamic) {
        this.personDynamicJpa.save( personDynamic );
    }

    public PageExtension findAllAndPage( Integer page, Integer pageSize ) {
        PageRequest pageRequest = this.getPage( page, pageSize);
        Page<PersonDynamic> personDynamics = this.personDynamicJpa.findAllByOrderByDeliverTimeDesc( pageRequest );
        return new PageExtension( personDynamics, pageRequest.getPageSize() , pageRequest.getPageNumber());
    }


}
