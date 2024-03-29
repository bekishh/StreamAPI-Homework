package service.serviceImpl;

import dao.ContactDao;
import dao.daoImpl.ContactDaoImpl;
import model.Contact;
import service.ContactService;

import java.util.List;

public class ContactServiceImpl implements ContactService {
    ContactDao contactService = new ContactDaoImpl();

    @Override
    public String addContactToPhone(int phoneId, Contact contact) {
        return contactService.addContactToPhone(phoneId, contact);
    }

    @Override
    public Contact findContactByName(int phoneId, String contactName) {
        return contactService.findContactByName(phoneId, contactName);
    }

    @Override
    public Contact findContactByPhoneNumber(int phoneId, String phoneNumber) {
        return contactService.findContactByPhoneNumber(phoneId, phoneNumber);
    }

    @Override
    public List<Contact> sortContactsByName(int phoneId) {
        return contactService.sortContactsByName(phoneId);
    }

    @Override
    public void deleteContactByNameFromPhone(int phoneId, String contactName) {
        contactService.deleteContactByNameFromPhone(phoneId, contactName);
    }
}
