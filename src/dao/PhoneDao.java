package dao;

import model.Phone;

import java.util.List;

public interface PhoneDao {
    String addPhone(Phone phone);
    // with stream

    Phone getPhoneById(int phoneId);
    // with stream

    Phone updatePhoneNameById(int phoneId, String newName);
    // with stream

    List<Phone> getAllPhones();
    // with stream

    List<Phone> getAllPhonesByBrand(String brand);

    void deletePhoneById(int phoneId);
}
