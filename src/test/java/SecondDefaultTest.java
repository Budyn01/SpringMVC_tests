/**
 * Created by hlibe on 22.03.2017.
 */
public interface SecondDefaultTest {
    default void sayHi(){
        System.out.println("Hello second");
    }
}
