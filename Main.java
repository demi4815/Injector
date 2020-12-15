import injector.Injector;
import myClass.SomeBean;

import java.io.IOException;

public class Main
{
    public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException, IOException
    {
        SomeBean sb = (new Injector()).inject(new SomeBean());
        sb.foo();
    }
}
