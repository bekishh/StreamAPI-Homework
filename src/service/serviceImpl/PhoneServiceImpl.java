package service.serviceImpl;

import dao.PhoneDao;
import dao.daoImpl.PhoneDaoImpl;
import model.Phone;
import service.PhoneService;

import java.util.List;

public class PhoneServiceImpl implements PhoneService {
    PhoneDao phoneService = new PhoneDaoImpl();

    @Override
    public String addPhone(Phone phone) {
        return phoneService.addPhone(phone);
    }

    @Override
    public Phone getPhoneById(int phoneId) {
        return phoneService.getPhoneById(phoneId);
    }

    @Override
    public Phone updatePhoneNameById(int phoneId, String newName) {
        return phoneService.updatePhoneNameById(phoneId, newName);
    }

    @Override
    public List<Phone> getAllPhones() {
        return phoneService.getAllPhones();
    }

    @Override
    public List<Phone> getAllPhonesByBrand(String brand) {
        return phoneService.getAllPhonesByBrand(brand);
    }

    @Override
    public void deletePhoneById(int phoneId) {
        phoneService.deletePhoneById(phoneId);
    }
}
