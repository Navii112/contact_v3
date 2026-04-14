package view;

import service.ContactService;
import vo.Contact;

import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

public class ContactView {
    private ContactService contactService = new ContactService();
    private final Scanner sc;

    public ContactView(Scanner sc) {
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
        System.out.println("검색 할 이름의 일부를 입력하세요");
        String searchName = sc.nextLine();
        Map<Integer, Contact> searchList = contactService.search(searchName);

        if (searchList.isEmpty()) {
            System.out.println("검색 결과 없음.");
        } else {
            // Map의 값들만 순회하기 위해 .values() 사용
            for (Contact contact : searchList.values()) {
                System.out.println(contact);
            }
        }
    }

    private void readAll() {
        Map<Integer, Contact> map = contactService.findAll();

        if (map.isEmpty()) {
            System.out.println("목록이 비었습니다.");
            return;
        }

        // Map의 값들만 순회하기 위해 .values() 사용
        for (Contact contact : map.values()) {
            System.out.println(contact);
        }
    }

    private void create(){
        System.out.println("[ContactView.create()]");
        String name = sc.nextLine();
        System.out.println("전화번호를 입력하세요.");
        String phone = sc.nextLine();
        contactService.create(name,phone);
    }

    private void update() {
        Map<Integer, Contact> map = contactService.findAll();

        if (map.isEmpty()) {
            System.out.println("리스트가 비어 있어요");
            System.out.println("메뉴로 이동합니다.");
            return;
        }

        for (Contact contact : map.values()) {
            System.out.println(contact);
        }

        System.out.println("수정 할 id를 입력하세요.");
        int updateNumber = sc.nextInt();
        sc.nextLine(); // nextInt() 이후 엔터키 제거

        Optional<Contact> beforeContact = contactService.findById(updateNumber);

        if (!beforeContact.isPresent()) {
            System.out.println("해당하는 ID 정보가 없습니다.");
            return;
        }

        Contact afterContact = beforeContact.get();

        System.out.println("변경 전 : " + beforeContact.get().getName());
        System.out.println("수정 할 이름을 입력하세요. (엔터치면 안바뀜)");
        String afterName = sc.nextLine();
        // 엔터만 쳤을 경우를 대비해 빈 문자열인지 확인
        if (!afterName.trim().isEmpty()) {
            afterContact.setName(afterName);
        }

        System.out.println("변경 전 : " + beforeContact.get().getPhone());
        System.out.println("수정 할 전화번호를 입력하세요. (엔터치면 안바뀜)");
        String afterPhone = sc.nextLine();
        if (!afterPhone.trim().isEmpty()) {
            afterContact.setPhone(afterPhone);
        }

        contactService.update(updateNumber, afterContact);
        System.out.println("수정이 완료되었습니다.");
    }

    private void delete(){
        readAll();

        Map<Integer, Contact> map = contactService.findAll();
        if (map.isEmpty()) {
            return; // readAll()에서 비어있다는 메시지를 이미 출력했으므로 바로 종료
        }

        System.out.println("삭제 할 id를 입력하세요.");
        int deleteNumber = sc.nextInt();
        sc.nextLine(); // 버퍼 비우기

        contactService.delete(deleteNumber);
    }
}