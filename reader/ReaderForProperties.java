package reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ReaderForProperties
{
    /**
     * Метод ищет в файле properties1 или properties2 название интерфейса и возвращает имя класса,
     * который должен реализовывать этот интерфейс
     * @param nameOfInterface - имя интерфейса
     * @return - имя реализующего класса
     * @throws IOException
     */
    public static String implementingClass(String nameOfInterface) throws IOException
    {
        //File file = new File("src/main/java/reader/properties1.txt");
        File file = new File("src/main/java/reader/properties2.txt");
        FileReader fr = new FileReader(file);
        BufferedReader reader = new BufferedReader(fr);

        String line;
        while ((line = reader.readLine()) != null)
        {
            String[] classes = line.split("=");// Метод split() разделяет данную строку вокруг данного выражения.
            if (classes[0].equals(nameOfInterface))
                return classes[1];
        }
        return null;
    }
}
