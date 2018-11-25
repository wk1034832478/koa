package hyywk.top.koa.service;

import hyywk.top.koa.entity.PersonAccountGeneralType;
import hyywk.top.koa.jpa.PersonAccountGeneralTypeJpa;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class PersonAccountGeneralTypeService {
    private Logger logger = LoggerFactory.getLogger( PersonAccountGeneralType.class );
    @Autowired
    private PersonAccountGeneralTypeJpa personAccountGeneralTypeJpa;

    public PersonAccountGeneralType save(PersonAccountGeneralType personAccountGeneralType) {
        try {
            return this.personAccountGeneralTypeJpa.save( personAccountGeneralType );
        } catch (Exception e) {
            this.logger.info("类型添加失败");
            return null;
        }
    }

    public List<PersonAccountGeneralType> findAll() {
        Iterator<PersonAccountGeneralType> typeIterator = this.personAccountGeneralTypeJpa.findAll().iterator();
        List<PersonAccountGeneralType> list = new ArrayList<PersonAccountGeneralType>( );
        while ( typeIterator.hasNext() ){
            list.add( typeIterator.next() );
        }
        return list;
    }
}
