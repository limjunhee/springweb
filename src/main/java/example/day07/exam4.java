package example.day07;

import java.util.ArrayList;
import java.util.List;

public class exam4 {
    public static void main(String[] args) {
        // 2)
        List<String> names = List.of("유재석","강호동","신동엽","서장훈");
            // 2.1
            for(int index = 0; index <= names.size()-1; index++){
                System.out.println(names.get(index));
            }

            // 2.2
            for (String name: names){ System.out.println(name);}

            // 2.3
            names.stream().forEach((name) -> { System.out.println(name);});

            // 2.4 메소드참조는 메소드명 명시하고 소괄호 작성하지 않는다.
            names.stream().forEach(System.out::println);

        // 3. 이름들의 글자 수 출력하기
            // 3.1 for문
            for(int index = 0; index <= names.size()-1; index++){
                System.out.println(names.get(index).length());
            }

            // 향상된 for문
            for(String name : names){
                System.out.println(name.length());
            }

            // 3.2 스트림 방식
            names.stream().map((name) -> {return name.length();}).forEach((result) -> {System.out.println( result );});

            // 3.3 메소드 레퍼런스(참조) 방식 / 주의: 메소드 위에 소괄호 사용 X, 내부적으로 메소드 호출하는 구조
            names.stream().map( String::length ).forEach( System.out::println );


        // 4. 리스트 내 문자열/이름들을 각각 대입하여 Student 객체 만들기
            List<Student4> list1 = new ArrayList<>();

            //전통 방식
            for(int index = 0; index <= names.size() - 1; index++){
                Student4 student = new Student4( names.get(index) );
                list1.add(student);
            }

            //스트림 방식
            List<Student4> list2 = names.stream().map((name) -> {return new Student4(name);}).toList();

            //메소드참조(레퍼런스)
            List<Student4> list3 = names.stream().map( Student4::new ).toList();

            /*
                유형
                1. 클래스명::statice메소드명
                2. 인스턴스명::메소드명
                3. 클래스명::new

                JPA 서비스 구조 : entity --> dto 변환
                List<MemberDto> list = entityList.stream().map((entity) -> {return MemberDto.from(entity);}).toList();
                List<MemberDto> list = entityList.stream().map( MemberDto::from ).toList();
            */
    }
}

class Student4 {
    private String name;
    public Student4 (String name){ this.name = name; };
    
}
