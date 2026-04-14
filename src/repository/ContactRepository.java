package repository;

import main.Main;
import service.ContactService;
import state.ContactState;
import vo.Contact;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ContactRepository {
    private final ContactState state;

    public ContactRepository(ContactState state) {
        this.state = state;
    }

    public void save(Long id, Contact contact) {
        System.out.println("[ContactRepository.save()]");
        Map<Long, Contact> currentStore = state.getStore();
        currentStore.put(id, contact);
        System.out.println("저장 완료");
        state.increaseId();
        System.out.println("ID 증가 완료");
    }

    public Map<Long, Contact> findAll() {
        System.out.println("[ContactRepository.findAll()]");
        return state.getStore();
    }

    public Optional<Contact> findById(int searchNumber) {
        Contact foundContact = Main.contactMap.get(searchNumber);
        return Optional.ofNullable(foundContact);
    }

    public void delete(Contact deleteContact) {
        Main.contactMap.remove(deleteContact.getId());
    }

    public void update(int updateNumber, Contact afterContact) {
        Main.contactMap.put(updateNumber, afterContact);
    }

    public Map<Integer, Contact> search(String searchName) {
        Map<Integer, Contact> searchResult = new HashMap<>();
        for (Map.Entry<Integer, Contact> entry : Main.contactMap.entrySet()) {
            if (entry.getValue().getName().contains(searchName)) {
                searchResult.put(entry.getKey(), entry.getValue());
            }
        }
        return searchResult;
    }
}