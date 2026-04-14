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

    public boolean findById(Long deleteId) {
        System.out.println("[ContactRepository.findById()]");
        return state.getStore().containsKey(deleteId);
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

    public void deleteById(Long deleteId) {
        System.out.println("[ContactRepository.deleteById()]");
        state.getStore().remove(deleteId);
    }

    public void updateById(Long updateId, String updatePhone) {
        System.out.println("[ContactRepository.updateById()]");
        // 1. 수정 할 대상의 연락처를 찾아서
        // 그 안에 있는 전화 번호를 수정한다.
        Contact contact = state.getStore().get(updateId);
        //        System.out.println(contact);
        // 2. 해당 id에 다시 저장
        contact.setPhone(updatePhone);
        // 맵은 key가 같으면 수정이 된다.
        state.getStore().put(updateId, contact);
    }
}