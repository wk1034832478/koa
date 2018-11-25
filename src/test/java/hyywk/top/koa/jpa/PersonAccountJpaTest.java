package hyywk.top.koa.jpa;

import hyywk.top.koa.entity.PersonAccount;
import hyywk.top.koa.entity.PersonAccountGeneralType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PersonAccountJpaTest {
    @Autowired
    private PersonAccountGeneralTypeJpa personAccountGeneralTypeJpa;
    @Autowired
    private PersonAccountJpa personAccountJpa;
    private Logger logger = LoggerFactory.getLogger( PersonAccountJpaTest.class );
    @Test
    public void testExample() throws Exception {
        this.personAccountGeneralTypeJpa.save( new PersonAccountGeneralType("QQ-test"));
    }

    @Test
    public void testExample2() throws Exception {
//        List<PersonAccount> lists = this.personAccountJpa.presearch("");
//        this.logger.info( lists.toString() + lists.size() );
    }
}
