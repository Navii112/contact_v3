package view;

import service.ContactService;
import vo.Contact;

import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

public class ContactView {
    private final ContactService contactService;
    private final Scanner sc;

    public ContactView(ContactService contactService, Scanner sc) {
        this.contactService = contactService;
        this.sc = sc;
    }

    public void run(){
        while (true) {
            System.out.println("\n1. 추가  2. 목록  3. 수정  4. 삭제  5. 이름의 일부로 검색  -1 : 종료 ");
            int cmd = sc.nextInt();
            sc.nextLine(); // nextInt() 이후에 남아있는 엔터키(개행문자) 제거

            switch (cmd) {
                case -1 :
                    return;
                case 1 :
                    create();
                    break;
                case 2 :
                    readAll();
                    break;
                case 3 :
                    update();
                    break;
                case 4 :
                    delete();
                    break;
                case 5 :
                    search();
                    break;
                default:
                    System.out.println("잘못 입력 함.");
            }
        }
    }

    private void search(){
        System.out.println("[ContactView.readAll()]");
    }

    private void readAll() {
        System.out.println("[ContactView.readAll()]");
        // 맵을 읽어와서 화면 출력
        Map<Long, Contact> store = contactService.findAll();
        // store 비어 있으면 없다고 출력한 후 종료
        if (store.isEmpty()) {
            System.out.println("저장된 자료가 없습니다.");
            return;
        }
        // 출력
        for (Long key : store.keySet()) {
            System.out.println(store.get(key));
        }
    }

    private void create(){
        System.out.println("[ContactView.create()]");
        // 이름과 나이, 전화번호를 입력 받아서
        // 서비스에 전달한다.
        String name;
        int age;
        String phone;
        System.out.println("이름 : ");
        name = sc.next();
        System.out.println("나이 : ");
        age = sc.nextInt();
        System.out.println("전화번호 : ");
        phone = sc.next();
        // 받은 값들을 service.ContactService.insert() 전달
        contactService.insert(name, age, phone);
    }

    private void update() {
        System.out.println("[ContactView.readAll()]");
    }

    private void delete(){
        System.out.println("[ContactView.readAll()]");
    }
}