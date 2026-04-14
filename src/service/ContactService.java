package service;

import main.Main;
import repository.ContactRepository;
import state.ContactState;
import vo.Contact;

import java.util.Map;
import java.util.Optional;

public class ContactService {
    private final ContactState state;
    private final ContactRepository repository;

    public ContactService(ContactState state, ContactRepository repository) {
        this.state = state;
        this.repository = repository;
    }


    public void insert(String name, int age, String phone) {
        System.out.println("[ContactService.insert()]");
        // Contact를 받은 데이터를 갖고 만들어요.
        Long id = state.getNextId();
        Contact contact = new Contact(id, name, age, phone);
        System.out.println(contact);
        // repository.ContactRepository.save()를 호출해서
        // store에 저장
        repository.save(id, contact);
        // 출력
        System.out.println("저장된 거 출력");
        Map<Long, Contact> currStore = state.getStore();
        for (Long key : currStore.keySet()) {
            System.out.println(currStore.get(key));
        }
    }
}