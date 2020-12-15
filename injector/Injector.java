package injector;

import annotations.AutoInjectable;
import reader.ReaderForProperties;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class Injector
{
    /**
     * Метод принимает произвольный объект, исследует существующие в нем поля.
     * Если поле аннотировано нужной аннотацией, то метод получает тип этого поля и ищет его
     * реализацию в файле properties.
     * @param object - произвольный объект
     * @param <T> - параметризация
     * @return - возвращает тот же объект, что и принимал, только уже с инициализированными полями
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws ClassNotFoundException
     * @throws IOException
     */
    public static<T> T inject(T object) throws IllegalAccessException, InstantiationException, ClassNotFoundException, IOException
    {
        //<T> generic(параметризация)
        Class<?> objOfAnyType = object.getClass();//объект класса, который может быть любого типа (? - это подстановочный знак)

        Field[] fields = objOfAnyType.getDeclaredFields();//getDeclaredFields() возвращает
        //массив объектов типа java.lang.reflect.Field соответствующих private и protected полям класса
        for (Field field : fields)
        {
            Annotation annotation = field.getAnnotation(AutoInjectable.class);//возвращает аннотацию этого элемента,
            //если такая аннотация присутствует, в противном случае возвращает null.
            field.setAccessible(true);//Так как поле не было public следует дать доступ для работы с ним.
            if (annotation != null)
            {
                String typeNameField = field.getType().getName();//получаем тип поля(один из интерфейсов)
                String impClass = ReaderForProperties.implementingClass(typeNameField);//получаем реализацию интерфейса

                //Динамическое создание экземпляра класса
                Class clazz = Class.forName(impClass);//загружаем класс с помощью метода Class.forName(),
                //передавая имя этого класса.
                //В результате возвращается объект типа Class.
                Object obj = clazz.newInstance();//создаем экземпляр объекта исходного класса
                //newInstance() возвращает объект обобщенного типа Object

                field.set(object, obj);//записываем obj в выбранное поле экземпляра object
            }
        }
        return object;
    }
}
