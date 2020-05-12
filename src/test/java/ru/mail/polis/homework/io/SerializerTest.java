package ru.mail.polis.homework.io;

import org.junit.Assert;
import org.junit.Test;
import ru.mail.polis.homework.io.objects.Animal;
import ru.mail.polis.homework.io.objects.Food;
import ru.mail.polis.homework.io.objects.Serializer;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class SerializerTest {

    @Test
    public void emptyListTest() {
        Path filePath = Paths.get("src", "test", "resources", "serializer");
        List<Animal> animalList = Collections.emptyList();

        //Serializer serializer = new Serializer();

        Serializer.customSerialize(animalList, filePath.toString());
        List<Animal> Animals = Serializer.customDeserialize(filePath.toString());
        Assert.assertTrue(Animals.isEmpty());//дефолт, пустой

        Serializer.defaultSerialize(animalList, filePath.toString());
        List<Animal> Animals2 = Serializer.customDeserialize(filePath.toString());
        Assert.assertTrue(Animals2.isEmpty());//кастом, пустой
    }

    @Test
    public void oneFile() {
        Path filePath = Paths.get("src", "test", "resources", "serializer");
        List<Animal> myAnimalList = new ArrayList<>();

        Animal mom = new Animal("MAMA", 25, false, false, Food.PLANTS, null, null);
        Animal dad = new Animal("PAPA", 26, false, false, Food.PLANTS, null, null);
        Animal baby = new Animal("OLEG", 3, false, false, Food.FEED, mom, dad);
        Animal baby2 = new Animal("ANTON", 5, false, false, Food.FEED, mom, dad);

        myAnimalList.add(mom);
        myAnimalList.add(dad);
        myAnimalList.add(baby);
        myAnimalList.add(baby2);

        //Serializer serializer = new Serializer();

        Serializer.defaultSerialize(myAnimalList, filePath.toString());
        List<Animal> myDeserializeAnimals = Serializer.defaultDeserialize(filePath.toString());
        assertEquals(myAnimalList, myDeserializeAnimals);

        Serializer.customSerialize(myAnimalList, filePath.toString());
        List<Animal> myDeserializeAnimals2 = Serializer.customDeserialize(filePath.toString());
        assertEquals(myAnimalList, myDeserializeAnimals2);
    }


    @Test
    public void twoFile() {
        Path filePath = Paths.get("src", "test", "resources", "serializer");
        Path filePath2 = Paths.get("src", "test", "resources", "seriolizer");

        List<Animal> myAnimalList = new ArrayList<>();
        Animal mom = new Animal("MAMA", 25, false, false, Food.PLANTS, null, null);
        Animal dad = new Animal("PAPA", 26, false, false, Food.PLANTS, null, null);
        Animal baby = new Animal("OLEG", 3, false, false, Food.FEED, mom, dad);

        myAnimalList.add(mom);
        myAnimalList.add(dad);
        myAnimalList.add(baby);

        //Serializer serializer = new Serializer();

        Serializer.defaultSerialize(myAnimalList, filePath.toString());
        List<Animal> myDeserializeAnimals = Serializer.defaultDeserialize(filePath.toString());
        Serializer.defaultSerialize(myAnimalList, filePath2.toString());
        List<Animal> myDeserializeAnimals2 = Serializer.defaultDeserialize(filePath2.toString());
        assertEquals(myDeserializeAnimals, myDeserializeAnimals2);
        assertEquals(myAnimalList, myDeserializeAnimals2);
        assertEquals(myDeserializeAnimals, myAnimalList);

        Serializer.customSerialize(myAnimalList, filePath.toString());
        myDeserializeAnimals = Serializer.customDeserialize(filePath.toString());
        Serializer.customSerialize(myAnimalList, filePath2.toString());
        myDeserializeAnimals2 = Serializer.customDeserialize(filePath2.toString());
        assertEquals(myDeserializeAnimals, myDeserializeAnimals2);
        assertEquals(myAnimalList, myDeserializeAnimals2);
        assertEquals(myDeserializeAnimals, myAnimalList);
    }
}
