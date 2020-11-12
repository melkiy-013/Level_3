package Lessen_6;

import org.junit.jupiter.api.BeforeEach;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class TestMain {

    private Main m;

    @BeforeEach
    public void init() {
        m = new Main();
    }
                                                            //задание_1
    @ParameterizedTest
    @MethodSource("randomList")
    public void test_1(List list){
        System.out.println(list);
        System.out.println(m.zadanie_1(list));
    }

    public Stream randomList(){
        List list = new ArrayList();
        for(int i=0; i<11; i++){
            list.add((int)(Math.random() * 10));
        }
        return list.stream();
    }

                                                            //задание 2 делается по аналогии



}
