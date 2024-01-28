import com.walletapi.repository.FuncionarioRepository;
import javafx.application.Application;
//import org.junit.Test;
//import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

//@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class TestQueryMethod {

   // @Autowired
    private FuncionarioRepository funcionarioRepository;

   // @Test
    @Transactional
    public void testQueryMethodAuthor() {
      //  List<Funcionario> a = funcionarioRepository.findByFirstName("Jesus");
    }
}