package dao.daoImpl;

import dao.PhoneDao;
import db.DataBase;
import exception.StackOverflowException;
import model.Phone;

import java.util.List;

public class PhoneDaoImpl implements PhoneDao {
    @Override
    public String addPhone(Phone phone) {
        DataBase.phones.add(phone);
        return "Телефон успешно добавлен!";
    }

    @Override
    public Phone getPhoneById(int phoneId) {
        try {
            return DataBase.phones.stream()
                    .filter(x -> x.getId() == phoneId)
                    .findFirst()
                    .orElseThrow(() -> new StackOverflowException("Телефон с id-" + phoneId + " не найден!"));
        } catch (StackOverflowException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Phone updatePhoneNameById(int phoneId, String newName) {
        try {
            Phone phone = DataBase.phones.stream()
                    .filter(x -> x.getId() == phoneId)
                    .findFirst()
                    .orElseThrow(() -> new StackOverflowException("Телефон с id-" + phoneId + " не найден!"));
            phone.setName(newName);
            return phone;
        } catch (StackOverflowException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Phone> getAllPhones() {
        return DataBase.phones;
    }

    @Override
    public List<Phone> getAllPhonesByBrand(String brand) {
        return DataBase.phones.stream()
                .filter(x -> x.getBrand().equalsIgnoreCase(brand)).toList();
    }

    @Override
    public void deletePhoneById(int phoneId) {
        try {
            DataBase.phones.remove(DataBase.phones.stream()
                    .filter(x -> x.getId() == phoneId)
                    .findFirst()
                    .orElseThrow(() -> new StackOverflowException("Телефон с id-" + phoneId + " не найден!")));
        } catch (StackOverflowException e) {
            System.out.println(e.getMessage());
        }
    }
}
