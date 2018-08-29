import com.mka.lesson6.dz.Student;
import com.mka.lesson6.dz.StudentRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class StudentRepositoryTest {

    StudentRepository studentApp = null;

    @Before
    public void init() {
        studentApp = new StudentRepository();
    }

    @Test
    public void addStudent() {
        /*final Student student = new Student("Gosha", 87);
        int expected = 1;
        int actual = studentApp.addStudent(student);
        assertEquals(expected, actual);*/
    }

    @Test
    public void update() {
        /*final Student student = new Student(1, "Alex", 21);
        int expected = 1;
        int actual = studentApp.update(student);
        assertEquals(expected, actual);*/
    }

    @Test
    public void getScore() {
        int expected = 87;
        int actual = studentApp.getScore("Gosha");
        assertEquals(expected, actual);
    }

    @After
    public void destroy() {
        studentApp.closeConnection();
        studentApp = null;
    }
}