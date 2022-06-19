package com.zaictronics.birthday;


import java.util.*;

public class MassageGenerator {
    public String generateMessage(Person person) {

        return "Subject: Happy Birthday\r\rHappy birthday, dear "+person.getFname()+"!";
    }

    public String getBirthday(Person person) {
        String[] date = person.getBirthday().split("/");
        return date[1]+"/"+date[2];
    }

    public static boolean isBirthday(Person person) {
//        Calendar date =Calendar.getInstance();
        GregorianCalendar date = new GregorianCalendar(2022,1,28);
        int month = date.get(Calendar.MONTH) + 1;
        String monthstr = dateChecker(month);
        int day = date.get(Calendar.DAY_OF_MONTH);
        int year= date.get(Calendar.YEAR);

        String[] birthday = person.getBirthday().split("/");
        String birthMonth =birthday[2];
        String dayofbirth =birthday[1];

        return birthMonth.equals(monthstr) && dayofbirth.equals(String.valueOf(day))
                || isOffTheMarkBirthday(monthstr, Integer.parseInt(dayofbirth));
    }

    private static String dateChecker(int month) {

        if(month < 10){
            return "0"+month;
        }

       else {
           return String.valueOf(month);
        }
    }

    public List<Person> birthDayPeople(List<Person> list) {
        return list.stream()
                .filter(MassageGenerator::isBirthday)
                .toList();
    }
    public Map<String, String> sendMessage(List<Person> list) {

        List<Person> birthDayPeople = birthDayPeople(list);

        Map<String, String> map = new Hashtable<>();
        for(Person person : birthDayPeople) {
            map.put(person.getEmail(), generateMessage(person));
        }

        return map;
    }

    public static boolean isOffTheMarkBirthday(String month, int day){
        boolean checker = false;
        GregorianCalendar gcalender = new GregorianCalendar();
        if(!gcalender.isLeapYear(gcalender.get(Calendar.YEAR))){
                if(Integer.parseInt(month) == 2 && day == 29){
                    checker = true;
                }
        }
        return checker;
    }


}
