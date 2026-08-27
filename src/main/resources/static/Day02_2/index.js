console.log("index.js 연결 확인");

waitingfindAll()

// [1] 전체 조회
async function waitingfindAll(){
    console.log("waitingfindAll 실행");

    // 어디에 -> html table 본문에
    let tbody = document.querySelector('.waitingList');
    
    // 무엇을(HTTP 통신(AXIOS) 이용한 백엔드에게 요청)
    // await axios.HTTP메소드명( "HTTP주소" ) *현재 함수명 앞에 async 작성할 것.
    
    // 동기화를 하는 이유: 해당 통신 *이후* 아래 코드를 실행하기 위해 
    // ★ 요청해놓고 결과 나오기도 전에 결과 출력하려고 해서 공백 출력하는 대참사 방지
    // 요청 후 응답을 기다리지 않는 경우 -> 비동기화
    // 요청 후 응답을 기다리는 경우 -> 동기화
    let html = "";
    const response = await axios.get( "http://127.0.0.1:8080/waiting/findAll" );
    console.log( response ); // config, data, headers, request, status, ... 
    
    // data: 통신 결과 데이터를 담은 부분
    const waitingList = response.data;
    for (let i = 0; i <= waitingList.length-1; i++){
        const waitingObj = waitingList[i];
        // console.log(waitingObj);

        html += `<tr>
                    <td>${ waitingObj.no}</td> <td>${waitingObj.phoneNumber}</td> <td>${waitingObj.count }</td>
                    <td>
                        <button onclick = 'waitingUpdate("${ waitingObj.phoneNumber }")'>수정</button>
                        <button onclick = 'waitingDelete("${ waitingObj.phoneNumber }")'>삭제</button>
                    </td>
                </tr>`
    }
    
    // 출력, <마크업> inner </마크업>
    tbody.innerHTML = html;
    console.log("waitingfindAll 완료");
}

async function waitingSave() {
    console.log("waitingSave 실행");
    // 1. 입력값
    const phonenumber = document.querySelector('.phonenumber').value;
    const count = document.querySelector('.count').value;
    // 2. axios 이용한 저장
    const response = await axios.post(`http://127.0.0.1:8080/waiting/save?phoneNumber=${phonenumber}&count=${count}`);
    // 3. 결과출력
    if (response.data == true) {
        alert('새로운 대기명단 저장 성공');
        waitingfindAll(); // 저장 성공시 전체조회 재호출
    }
    else { alert('새로운 대기명단 저장 실패'); }
}

async function waitingUpdate(phoneNumber) {
    console.log("waitingUpdate 실행");
    // 1. 수정할 내용 입력받기 prompt, 전화번호 문자열로 만들기
    const count = Number(prompt('변경할 인원 수 입력: '));

    // 2. 수정 처리 : axios 이용하여 백엔드에게 수정 요청/응답
    const response = await axios.put(`http://127.0.0.1:8080/waiting/update?phoneNumber=${phoneNumber}&count=${count}`);

    // 3. 결과
    if(response.data == true){
        alert('명단 인원수 수정 성공');
        waitingfindAll();
    } else {
        alert('수정 실패');
    }
}

async function waitingDelete(phoneNumber) {
    console.log("waitingDelete 실행");
    // 1. 삭제할 전화번호 -> 매개변수

    // 2. 삭제 처리
    const response = await axios.delete(`http://127.0.0.1:8080/waiting/delete?phoneNumber=${phoneNumber}`);

    // 3. 결과 확인
    if(response.data == true){
        alert('대기명단 삭제 성공');
        waitingfindAll();
    } else {
        alert('대기명단 삭제 실패');
    }
}