package hyywk.top.koa.jpa;


import hyywk.top.koa.entity.PersonDynamic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface PersonDynamicJpa extends CrudRepository<PersonDynamic,Long> {

    Page<PersonDynamic> findAllByOrderByDeliverTimeDesc( Pageable pageable);
}
