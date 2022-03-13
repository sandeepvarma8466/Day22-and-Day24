package com.bridgelabz;

import java.util.*;
import java.util.stream.Collectors;

public class AddressBook {
    static Scanner sc = new Scanner(System.in);
    static List<Contacts> addressBook = new ArrayList<Contacts>();
    static Contacts addressBoook =new Contacts();

    public static void main(String[] args) {
        System.out.println("Address Book Problem");
        AddressBook addressBookList=new AddressBook();
        boolean condition = true;

        while (condition == true) {
            System.out.println("Enter  options"+"\n"+"1. continue with current addressBook"+"\n"+"2.Create a new addressBook");
            int options = sc.nextInt();
            switch(options) {
                case 1:
                    boolean condition1 = true;
                    while (condition1== true) {
                        System.out.println("1.add" +"\n" +"2.display"+ "\n" + "3.edit"+"\n" +"4.delete"+"\n"+"5.RemoveDuplicates"+"\n"+
                                "6.SearchPerson in a city or State"+"\n"+"7.View Person By CityOrState"+"\n"
                                +"8.Get a Count Of City or State"+"\n"+"9.sortWithPersonName"+"\n"+"10.sortByCityStateZip"+"\n"+"11.exit");
                        int option = sc.nextInt();
                        switch (option) {
                            case 1:
                                addressBookList.addContactDetails();
                                break;
                            case 2:
                                addressBookList.display();
                                break;
                            case 3:
                                addressBookList.editContactDetails();
                                break;
                            case 4:
                                addressBookList.deleteContact();
                                break;
                            case 5:
                                System.out.println("enter a  name");
                                String personName=sc.next();
                                addressBookList.removeDuplicates(personName);
                            case 6:
                                System.out.println("Enter a city and state:-");
                                String city=sc.next();
                                String state=sc.next();
                                addressBookList.searchPersonInCityOrState(city, state);
                                break;
                            case 7:
                                addressBookList.viewPersonByCityAndState();
                                break;
                            case 8:
                                addressBookList.getCountOfCityAndState();
                                break;
                            case 9:
                                addressBookList.sortWithPersonName();
                                break;
                            case 10:
                                addressBookList.sortByCityStateZip();
                                break;
                            case 11:
                                condition1 = false;
                                break;
                            default:
                                System.out.println("Invalid Input");
                        }
                    }
                    break;
                case 2:
                    HashMap<String, List<Contacts>> map = new HashMap<>();
                    List<Contacts> arrayList = new ArrayList<>();
                    Contacts addressBook1= AddressBook.addressBoook;
                    arrayList.add(addressBook1);
                    map.put("Ram", arrayList);
                    System.out.println(map);
                    if (map.containsKey("Ram")) {
                        List<Contacts> ram = map.get("Ram");
                        Contacts addressBook2= AddressBook.addressBoook;
                        ram.add(addressBook2);
                        map.put("Ram", ram);
                    }
                    System.out.println(map);
                    Contacts addressBook3= AddressBook.addressBoook;
                    arrayList.add(addressBook3);
                    map.put("Abhi", arrayList);
                    System.out.println(map);
                    break;
                default:
                    System.out.println("Invalid Input");
            }
        }
    }

    public void sortByCityStateZip() {
        List<Contacts> sortPersonByStateCityZip= addressBook.stream().sorted(Comparator.comparing(Contacts::getState).thenComparing(Contacts::getCity).thenComparing(Contacts::getZip)).collect(Collectors.toList());
        System.out.println(sortPersonByStateCityZip);
    }

    public void sortWithPersonName() {
        addressBook.sort((p1, p2)->p1.getFirstName().compareTo(p2.getFirstName()));
        System.out.println(addressBook);
    }

    public void getCountOfCityAndState() {
        Map<String, Map<String, Long>> person= addressBook.stream().collect(Collectors.groupingBy(Contacts::getCity, Collectors.groupingBy(Contacts::getState, Collectors.counting())));
        System.out.println("count of city and state :-"+person);
    }

    public void viewPersonByCityAndState() {
        Map<String, Map<String, List<Contacts>>> person1= addressBook.stream().collect(Collectors.groupingBy(Contacts::getCity, Collectors.groupingBy(Contacts::getState)));
        System.out.println("View person by city and state is:-"+person1);
    }

    public void searchPersonInCityOrState(String city, String state) {
        List<Contacts> searchPerson= addressBook.stream().filter(Contacts->Contacts.getCity().equals(city)).filter(Contacts->Contacts.getState().equals(state)).collect(Collectors.toList());
        System.out.println("Searching person using city or state "+searchPerson);
    }

    public void removeDuplicates(String personName) {
        List<Contacts> AfterRemovingDuplicates= addressBook.stream().filter(Contacts->Contacts.getFirstName().equalsIgnoreCase(personName)).distinct().collect(Collectors.toList());
        System.out.println("After removing duplicate elements"+AfterRemovingDuplicates);
    }

    public void addContactDetails() {
        Contacts details=new Contacts();
        System.out.println("Enter a first name:");
        details.setFirstName(sc.next());
        System.out.println("Enter a last name:");
        details.setLastName(sc.next());
        System.out.println("Enter a Address:");
        details.setAddress(sc.next());
        System.out.println("Enter a City name:");
        details.setCity(sc.next());
        System.out.println("Enter a state:");
        details.setState(sc.next());
        System.out.println("Enter a email:");
        details.setEmail(sc.next());
        System.out.println("Enter a zip code:");
        details.setZip(sc.nextInt());
        System.out.println("Enter a phone number:");
        details.setPhoneNumber(sc.nextLong());

        addressBook.add(details);
        System.out.print(addressBook);
        System.out.println("successfully added person new contacts");

    }

    public void editContactDetails() {
        System.out.println("enter a name for edit:");
        String editName=sc.next();
        for(int i = 0; i< addressBook.size(); i++) {
            if(addressBook.get(i).getFirstName().equals(editName)) {
                System.out.println("select options");
                System.out.println("\n1.First Name\n2.Last Name\n3.Address\n4.City\n5.State\n6.Zip\n7.Phone Number\n8.Email");
                int editOption=sc.nextInt();

                switch(editOption) {
                    case 1:
                        System.out.println("Enter a First name:");
                        String editFirstName=sc.next();
                        addressBook.get(i).setFirstName(editFirstName);
                        System.out.println(editFirstName);
                        break;
                    case 2:
                        System.out.print("Enter a Last name:");
                        addressBook.get(i).setLastName(sc.next());
                        break;
                    case 3:
                        System.out.print("Enter a Address:");
                        addressBook.get(i).setAddress(sc.next());
                        break;
                    case 4:
                        System.out.print("Enter a city:");
                        addressBook.get(i).setCity(sc.nextLine());
                        break;
                    case 5:
                        System.out.print("Enter a state:");
                        addressBook.get(i).setState(sc.nextLine());
                        break;
                    case 6:
                        System.out.print("Enter a zip code:");
                        addressBook.get(i).setZip(sc.nextInt());
                        break;
                    case 7:
                        System.out.print("Enter a phone number:");
                        addressBook.get(i).setPhoneNumber(sc.nextLong());
                        break;
                    case 8:
                        System.out.print("Enter a email:");
                        addressBook.get(i).setEmail(sc.nextLine());
                        break;
                    default:
                        System.out.println("enter valid contact");
                }
            }
            System.out.println("Edited list is:");
            System.out.println(addressBook);
        }
    }

    public void deleteContact() {
        System.out.println("confirm the name to delete contact");
        String confirmName=sc.next();
        for (int i = 0; i < addressBook.size(); i++) {
            if(addressBook.get(i).getFirstName().equalsIgnoreCase(confirmName));
            Contacts contact = addressBook.get(i);
            addressBook.remove(contact);
        }
        System.out.println(addressBook);
    }

    public void display() {
        for(int i = 0; i< addressBook.size(); i++) {
            Contacts contact= addressBook.get(i);
            System.out.println("FirstName"+":="+contact.getFirstName()+"\n"+"lastname"+":= "+contact.getLastName()+"\n"+"Address"+":= "+contact.getAddress()
                    +"\n"+"City"+":= "+contact.getCity()+"\n"+"State"+":="+contact.getState()+"\n"+"Zip"+":= "+contact.getZip()+"\n"+"PhoneNumber"+":= "+contact.getPhoneNumber()+"\n"+"Email"+":= "+contact.getEmail());
        }
    }
}
