package example.day07;

import java.util.Comparator;
import java.util.List;

public class exam3 {
    public static void main(String[] args) {
        List<Integer> numbers = List.of(1,2,3,4,5,6,7,8,9,10,5,4,7);

        // 1. 컬렉션(List/Set/Map) 순회
            // 1) 일반 for문
            for( int index = 0; index <= numbers.size()-1; index++){
                System.out.println( numbers.get(index));
            }

            // 2) 향상 for문
            for(Integer data : numbers){
                System.out.println(data);
            }

            // 3) forEach
            numbers.forEach( (data) -> { System.out.println(data); });

        
        // 2. 스트림API, 리스트객체.stream().중간연산1().중간연산2().최종연산()     -> 중간 연산은 여러개 가능, 최종 연산은 하나만
            // 1) stream().forEach( (순회 변수명) -> { 실행문 } );      , return 없는 단순 순회 제공
            numbers.stream().forEach((data) -> { System.out.println(data); });
            
            // 2) stream().map( (순회 변수명) -> { return 값;}). 최종연산();    , return 있는 순회 제공
            List<Integer> newList = numbers.stream().map( (data) -> { return data; }).toList();

            // 3) stream().filter( (순회 변수명) -> { return 조건식; } ).최종연산();
            List<Integer> newList2 = numbers.stream().filter( (data) -> { return data % 2 == 0; } ).toList();
            System.out.println(newList2); // [2,4,6,8,10]

            // 4) stream().sorted().최종연산() : 오름차순 정렬 / stream().sorted( Comparator.reverseOrder() ) : 내림차순 정렬
            List<Integer> newList3 = numbers.stream().sorted( Comparator.reverseOrder() ).toList();
            System.out.println( newList3 );

            // 5) stream().distinct().최종연산() : 중복값 제거 / stream().limit(n)  : n개만큼 반환
            List<Integer> newList4 = numbers.stream().distinct().limit(3).toList();
            System.out.println(newList4);

            // 6) 중간 연산은 여러 번 가능, 최종 연산은 딱 한 번 가능
            List<Integer> newList5 = numbers.stream().distinct()
                                                    .filter( (data) -> {return data % 2 == 0;} )        // 중간연산 - 조건식
                                                    .map( (data) -> {return data;} )                    // 중간연산 - 반환
                                                    .sorted( Comparator.reverseOrder() )                // 중간연산 - 내림차순
                                                    .limit( 3 )                                // 중간연산 - 반환 개수 3개로 제한
                                                    .toList();                                          // 최종연산 - 리스트로 반환한다

            System.out.println(newList5); // 짝수 숫자 3개 내림차순으로 출력 -> [10, 8, 6]

            // *) JPA 에서 Entity <---> Dto 변환 과정에서 사용하는 빌더 패턴과 비슷
    }
}
