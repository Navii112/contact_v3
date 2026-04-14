package state;

import vo.Contact;

import java.util.HashMap;
import java.util.Map;

// 연락처를 저장할 map 저장공간과 id를 자동증가할 변수
public class ContactState {
    // 연락처를 저장할 맵 선언
    private Map<Long, Contact> store = new HashMap<>();

    // id를 자동증가시킬 변수
    private Long nextId = 1L;

    // Getter
    public Map<Long, Contact> getStore() {
        return store;
    }
    public Long getNextId() {
        return nextId;
    }

    // Setter
    public void setStore(Map<Long, Contact> store) {
        this.store = store;
    }
    public void increaseId() {
        nextId ++;
    }
}
