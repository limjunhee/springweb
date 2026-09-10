//1. 변수, 상수 선언 키워드 : let / const
let count = 10;     // let 변수선언 키워드'
    count = 3;      // 변수는 수정 가능
const count2 = 20;  // const 상수선언 키워드
        // count2 = 10; // 상수는 수정 불가 Assignment to constant variable.

// 2. 문자열 템플릿, 백틱
console.log(`hello ${ count }`)

let html = `<div> hello ${count2}</div>`
console.log(html)

// 3. if
const point = 85;
if (point >= 90) { console.log("A학점") } 
else if (point >= 80){ console.log("B학점" ) }
else { console.log("C학점") }

// 3-2. : 삼항연산자, 조건 ? 참: 거짓 / 조건1 ? 참1 : 조건2 ? 참2 : 거짓
console.log(point >= 90 ? "A학점": point >= 80 ? "B학점" : "C학점")

// 3-3. : 단축평가
console.log( point >= 90 && "A학점") // 만약 90점 이상이면 'A학점', 아니면 false
console.log( point >= 90 || "A학점" ) // 만약 90점 이상이면 true, 아니면 'A학점'

// 3-4. 조건이 null인 경우
let nu = null;
console.log(nu ?? "익명사용자")

// 4. 반복문
const array = [10, 20, 30, 40, 50]

// 4-1. for문
console.log("for문")
for(let index = 0; index<= array.length-1; index++){
    console.log(array[index])
}

// 4-2. 향상된 for문
for(let index in array){ console.log(array[index]) }
for(let value of array){ console.log(value) }
for (let value in array) { console.log(value) }

// 4-3. forEach 순회, map 순회+반환 , filter 순회+조건(논리)
array.forEach( (value) => {console.log(value); })
const newArray = array.map( (value) => {return value;} );
const newArray2 = array.filter( (value) => {return value >= 20;} )


// 5. 함수
// 5-1. 선언적 함수
function func1(매개변수1, 매개변수2){ }
// 5-2. 익명함수 , 주로 변수/상수에 저장되어서 사용
const func2 = function(매개변수1, 매개변수2){ }
// 5-3. 람다함수, 주로 변수/상수에 저장되어서 사용
const func3 = (매개변수1, 매개변수2) => {}
// 5-4. 매개변수에 기본값 대입 가능, 만일 인수가 없을 때 기본값 대입
const func4 = (매개변수1, 매개변수2, 매개변수3 = 'student') => { console.log( 매개변수1, 매개변수2, 매개변수3 ) }
// --- 함수 호출
func1( 4, 10 ); 
func2( 4, 20 );
func3( 10, { name: '유재석'} ); // 익명함수, 람다함수는 변수명으로 호출한다.
func4( 10, 4 ) // 만일 인수가 없는 매개변수는 기본값이 대입된다. -> 10 4 'student'

// 6. 객체 : 여러 개 값을 가진 하나의 값
// 6-1 : [ 속성명(key) : 속성값(value) ] 한 쌍
const obj1 = { name : '유재석', age : 40, func1 : (param) => { } }
console.log( obj1.func1() )

// 6-2 : [ 값, 값, 값 ]
const obj2 = [ '유재석', 40, (param) => { } ]
console.log( obj2[2]() );      // 인덱스로 속성값 호출

// 7. 스프레드 연산자: ...배열 또는 개체를 복사할 때 사용, *[사용처: 주소값 변경 목적] -> 리액트/플러터에서 자주 사용
const obj3 = { ...obj1 , phone : "010" } // {...기존객체, 새로운속성 : 새로운값}
console.log(obj3)

const obj4 = ["010" , ...obj2 ] // [...기존배열, 새로운값]
console.log(obj4)

// 8. 구조분해 할당: 배열 또는 객체에서 값을 분해
const {name, age} = obj1; // 오른쪽 객체 내 왼쪽에 각 변수/상수에 값 대입 -> 구조분해 할당
console.log(name); // "유재석"
console.log(age); // 40

const [ name2, ...array2] = obj2 // 오른쪽 배열 내 순서대로 값들을 변수/상수에 대입
console.log(name2); // "유재석"
console.log(array2); // "유재석" 을 제외한 나머지

// 9. 콜백함수: 함수를 전달하여 나중에 전달받은 함수를 실행
function printSuccess( message ){ console.log( "출력성공" + message ) }
function printScore( score, onSuccess, onError ){
    if(score >= 80){ onSuccess("합격") }
    else{ onError("불합격"); }
}

printScore( 30, printSuccess, (message) => { console.log( "실패" + message ) } ) 
// 콜백함수 방식으로 함수 호출, 주의 : 인수에 함수 전달 시 함수 실행 X, 함수정의O
// 함수명( 3 + 3 ); 인수: 6     
// 함수명( plus(3,3) ), 인수: 6 
// 함수명(plus), 인수: plus함수

// 10. 
// 동기식: 먼저 호출한 함수/기능 결과가 올때까지 대기상태 , 동기화
// 비동기 : 먼저 호출한 함수/기능 결과는 순서 상관없이 반환 , axios
// axios는 비동기 통신, 동기화로 만드는 방법 - (1) 함수 앞에 async , (2) axios 앞에 await
const backLoad = async ( ) => {
    
    const response = await axios( );

}
backLoad( )