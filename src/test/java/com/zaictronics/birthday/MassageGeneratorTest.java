package com.zaictronics.birthday;

import org.junit.jupiter.api.Test;

import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MassageGeneratorTest {
    @Test
    void given_a_person_we_can_generate_a_happy_birthday_message() {
        Person person = new Person("Doe", "John", "1982/10/08", "doe.john@foorbar.com");

        MassageGenerator messageGenerator  = new MassageGenerator();

        String message =  messageGenerator.generateMessage(person);

        assertEquals("Subject: Happy Birthday\r\rHappy birthday, dear Doe!", message);

    }

    @Test
    void given_two_people_we_can_generate_a_happy_birthday_message() {
        Person personOne = new Person("Doe", "John", "1982/10/08", "doe.john@foorbar.com");
        Person personTwo= new Person("Mike", "John", "1982/10/08", "mike.john@foorbar.com");

        MassageGenerator messageGenerator  = new MassageGenerator();

        String messageOne =  messageGenerator.generateMessage(personOne);
        String messageTwo =  messageGenerator.generateMessage(personTwo);

        String result = messageOne +" "+ messageTwo;

        String expected = "Subject: Happy Birthday\r\rHappy birthday, dear Doe!" + " Subject: Happy Birthday\r\rHappy birthday, dear Mike!";

        assertEquals(expected, result);

    }

    @Test
    void given_a_person_we_can_read_birthday() {
        Person person = new Person("Doe", "John", "1982/10/08", "doe.john@foorbar.com");

        MassageGenerator messageGenerator = new MassageGenerator();

        String expected = "10/08";

        String result = messageGenerator.getBirthday(person);

        assertEquals(expected, result);
    }

    @Test
    void given_a_person_we_can_find_if_today_is_their_birthday() {
        Person person = new Person("Doe", "John", "1982/28/02", "doe.john@foorbar.com");

        MassageGenerator messageGenerator = new MassageGenerator();

        boolean expected = true;

        boolean result = messageGenerator.isBirthday(person);

        assertEquals(expected, result);

    }
    @Test
    void given_a_non_leap_year_and_a_persons_birthday_is_29th_we_can_find_if_today_is_their_birthday() {
        Person person = new Person("Doe", "John", "1982/29/02", "doe.john@foorbar.com");

        MassageGenerator messageGenerator = new MassageGenerator();

        boolean expected = true;

        boolean result = messageGenerator.isBirthday(person);

        assertEquals(expected, result);

    }

    @Test
    void given_list_of_persons_we_can_filter_ones_whose_birthday_is_today() {

        MassageGenerator messageGenerator = new MassageGenerator();
        Person personOne = new Person("Ali", "Mulondo", "1982/28/02", "ali.mulondo@foorbar.com");
        Person personTwo = new Person("Wiston", "Magheni", "1992/23/06", "wiston.magheni@foorbar.com");
        Person personThree = new Person("Anthony", "Osalia", "1990/28/02", "antony.osalia@foorbar.com");

        List<Person> list = List.of(personOne, personTwo, personThree);

        List<Person> expected = List.of(personOne,personThree);

        List<Person> result = messageGenerator.birthDayPeople(list);

        assertEquals(expected, result);

    }


    @Test
    void given_a_list_of_people_and_today_is_their_birthday_we_can_send_a_message() {
        MassageGenerator messageGenerator = new MassageGenerator();
        Person personOne = new Person("Ali", "Mulondo", "1982/28/02", "ali.mulondo@foorbar.com");
        Person personTwo = new Person("Wiston", "Magheni", "1992/23/06", "wiston.magheni@foorbar.com");
        Person personThree = new Person("Anthony", "Osalia", "1990/28/02", "antony.osalia@foorbar.com");

        List<Person> list = List.of(personOne, personTwo, personThree);
        Map<String, String> expected = new Hashtable<>();
        expected.put("ali.mulondo@foorbar.com","Subject: Happy Birthday\r" +
                "\r" +
                "Happy birthday, dear Ali!");
        expected.put("antony.osalia@foorbar.com", "Subject: Happy Birthday\r" +
                "\r" +
                "Happy birthday, dear Anthony!");

        Map<String, String> result = messageGenerator.sendMessage(list);

        assertEquals(expected, result);

    }
}