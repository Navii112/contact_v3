package main;

import repository.ContactRepository;
import service.ContactService;
import state.ContactState;
import view.ContactView;
import vo.Contact;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    // Key는 Integer(ID), Value는 Contact(객체)로 변경해야 합니다.
    public static Map<Integer, Contact> contactMap = new HashMap<>();

    public static int nextId = 1;

    public static void main(String[] args) {
        // 처음 생성할 때 스캐너를 갖고 시작하도록 스캐너를
        // 생성자 주입방법으로 넣고 시작
        Scanner scanner = new Scanner(System.in);
        ContactState state = new ContactState();
        ContactRepository repository = new ContactRepository(state);
        ContactService contactService = new ContactService(state, repository);
        ContactView contactView = new ContactView(contactService, scanner);
        // 화면 호출
        contactView.run();
    }
}