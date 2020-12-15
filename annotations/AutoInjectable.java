package annotations;

import java.lang.annotation.*;

@Target(value = ElementType.FIELD)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface AutoInjectable { }

/*
@Target отмечает аннотацию как ограничивающую, какие
 элементы java-аннотации могут быть к ней применены;
*/
/*
@Retention — определяет, как отмеченная аннотация может
храниться в коде, в скомпилированном классе или во время
работы кода;
*/
/*
RetentionPolicy.RUNTIME - аннотация будет записана в class-файл и
доступна во время выполнения через reflection.
*/



