package dbk.qacourse.sandbox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Collections {

    public static void main(String[] args) {
        // array:
        String[] langs = {"Java", "C#", "Python", "PHP"};

        /* another way of declaring the table:
        String[] langs = new String[4];
        langs[0] = "java";
        langs[1] = "C#";
        langs[2] = "Python";
        langs[3] = "PHP";
        */

        // loop:
        for (int i=0; i < langs.length; i++){
            System.out.println("I want to learn language " + langs[i]);
        }

        // special construction for iterating elements of the collection
        // variable "l" is not an index, it's a reference to the collection element
        for (String l : langs) {
            System.out.println("1. I want to learn language " + l);
        }


        // Collection<E>:
        List<String> languages = new ArrayList<String>();
        languages.add("Java");
        languages.add("C#");
        languages.add("Python");
        languages.add("PHP");

        for (String l : languages) {
            System.out.println("-> 2. I want to learn language " + l);
        }


        // convertig the list into a collection
        List<String> languages2 = Arrays.asList("Java", "C#", "Python", "PHP");

        for (String l : languages2) {
            System.out.println("3. I want to learn language " + l);
        }

        /*
        for (int i = 0; i < languages2.size(); i++) {
            System.out.println("3. I want to learn language " + languages2.get(i));
        }
        */

        List listOfAnything = Arrays.asList("Java", "C#", "Python", "PHP", 12345);

        for (Object l : listOfAnything) {
            System.out.println("3. I want to learn language " + l);
        }

    }
}