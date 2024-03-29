package dao.daoImpl;

import dao.ContactDao;
import db.DataBase;
import exception.StackOverflowException;
import model.Contact;
import model.Phone;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ContactDaoImpl implements ContactDao {
    @Override
    public String addContactToPhone(int phoneId, Contact contact) {
        try {
            Phone phone = DataBase.phones.stream()
                    .filter(x -> x.getId() == phoneId)
                    .findFirst()
                    .orElseThrow(() -> new StackOverflowException("Телефон с id-" + phoneId + " не найден!"));

            boolean isContactExists = phone.getContacts().stream()
                    .filter(x -> x.getName().equalsIgnoreCase(contact.getName()))
                    .findFirst().isEmpty();

            if (isContactExists) {
                phone.getContacts().add(contact);
            }
        } catch (StackOverflowException e) {
            System.out.println(e.getMessage());
        }
        return "";
    }

    @Override
    public Contact findContactByName(int phoneId, String contactName) {
        try {
            Phone phone = DataBase.phones.stream()
                    .filter(x -> x.getId() == phoneId)
                    .findFirst()
                    .orElseThrow(() -> new StackOverflowException("Телефон с id-" + phoneId + " не найден!"));

//            if (phone != null) {
            return phone.getContacts().stream()
                    .filter(x -> x.getName().equalsIgnoreCase(contactName))
                    .findFirst()
                    .orElseThrow(() -> new StackOverflowException("Контакт с именем '" + contactName + "' не найден!"));
//            }

        } catch (StackOverflowException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Contact findContactByPhoneNumber(int phoneId, String phoneNumber) {
        try {
            Phone phone = DataBase.phones.stream()
                    .filter(x -> x.getId() == phoneId)
                    .findFirst()
                    .orElseThrow(() -> new StackOverflowException("Телефон с id-" + phoneId + " не найден!"));

//            if (phone != null) {
            return phone.getContacts().stream()
                    .filter(x -> x.getPhoneNumber().equalsIgnoreCase(phoneNumber))
                    .findFirst()
                    .orElseThrow(() -> new StackOverflowException("Контакт с номером '" + phoneNumber + "' не найден!"));
//            }

        } catch (StackOverflowException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Contact> sortContactsByName(int phoneId) {
        try {
            List<Contact> phoneContacts = DataBase.phones.stream()
                    .filter(x -> x.getId() == phoneId)
                    .findFirst()
                    .orElseThrow(() -> new StackOverflowException("Телефон с id-" + phoneId + " не найден!"))
                    .getContacts();

            phoneContacts.sort(Comparator.comparing(Contact::getName));
            return phoneContacts;

        } catch (StackOverflowException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void deleteContactByNameFromPhone(int phoneId, String contactName) {
        try {
            Phone phone = DataBase.phones.stream()
                    .filter(x -> x.getId() == phoneId)
                    .findFirst()
                    .orElseThrow(() -> new StackOverflowException("Телефон с id-" + phoneId + " не найден!"));

            phone.getContacts().remove(phone.getContacts().stream()
                    .filter(x -> x.getPhoneNumber().equalsIgnoreCase(contactName))
                    .findFirst()
                    .orElseThrow(() -> new StackOverflowException("Контакт с именем '" + contactName + "' не найден!")));
        } catch (StackOverflowException e) {
            System.out.println(e.getMessage());
        }
    }
}
