import com.mka.lesson6.Calculator;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

// Параметризированный тест
@RunWith(Parameterized.class)
public class CalculatorTest {

    // эти данные автоматически передадутся в конструктор и пройдут 4 теста
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                // a, b, c - что ожидаем
                {1, 2, 3},
                {0, 0, 0},
                {10, 10, 20},
                {5, 5, 10}
        });
    }

    private int a;
    private int b;
    private int c;

    public CalculatorTest(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    Calculator calculator = null;

    // Будет выполнятся перед каждым тестом
    @Before
    public void init() {
        calculator = new Calculator();
    }

    // Если нужно подключится к БД нужно использовать BeforeClass обязательно должен принадлежать static
    @BeforeClass
    public static void initStatic() {}

    @Test
    public void sumTest() {
        int expected = 4; // хотим получить
        int actual = calculator.sum(2, 2);
        assertEquals(expected, actual);
    }

    @Test
    public void sumTest2() {
        assertEquals(c, calculator.sum(a, b));
    }

    // Если ожидаем какое нибудь исключение, тест будет пройден, если метод вернет exception
    @Test(expected = ArithmeticException.class)
    public void divTest() {
        int expected = 10;
        int actual = calculator.div(1, 0);
        assertEquals(expected, actual);
    }

    // Если метод должен выполнится ограниченное время
    @Test(timeout = 1000)
    public void multTest() {
        int expected = 1;
        int actual = calculator.mult(1, 1);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(expected, actual);
    }

    @After
    public void destroy() {
        calculator = null;
    }

    @AfterClass
    public static void destroyStatic() {
        //connection.close();
    }
}