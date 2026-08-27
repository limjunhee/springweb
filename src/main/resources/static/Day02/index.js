console.log("index.js 연결 확인");

boardfindAll()

// [1] 전체 조회
async function boardfindAll(){
    console.log("boardfindAll 실행");

    // 어디에 -> html table 본문에
    let tbody = document.querySelector('.boardList');
    
    // 무엇을(HTTP 통신(AXIOS) 이용한 백엔드에게 요청)
    // await axios.HTTP메소드명( "HTTP주소" ) *현재 함수명 앞에 async 작성할 것.
    
    // 동기화를 하는 이유: 해당 통신 *이후* 아래 코드를 실행하기 위해 
    // ★ 요청해놓고 결과 나오기도 전에 결과 출력하려고 해서 공백 출력하는 대참사 방지
    // 요청 후 응답을 기다리지 않는 경우 -> 비동기화
    // 요청 후 응답을 기다리는 경우 -> 동기화
    let html = "";
    const 응답결과 = await axios.get( "http://127.0.0.1:8080/board/findAll" );
    console.log( 응답결과 ); // config, data, headers, request, status, ... 
    
    // data: 통신 결과 데이터를 담은 부분
    const 게시물리스트 = 응답결과.data;
    for(let i = 0; i <= 게시물리스트.length-1; i++){
        const 게시물객체 = 게시물리스트[i];

        html += `<tr>
                    <td>${ 게시물객체.no}</td> <td>${게시물객체.writer}</td> <td>${게시물객체.content }</td>
                    <td>
                        <button onclick = 'boardUpdate(${ 게시물객체.no })'>수정</button>
                        <button onclick = 'boardDelete(${ 게시물객체.no })'>삭제</button>
                    </td>
                </tr>`
    }
    
    // 출력, <마크업> inner </마크업>
    tbody.innerHTML = html;
}

async function boardSave() {
    // 1. 입력값
    const content = document.querySelector('.content').value;
    const writer = document.querySelector('.writer').value;
    // 2. axios 이용한 저장
    const response = await axios.post(`http://127.0.0.1:8080/board/save?content=${content}&writer=${writer}`);
    // 3. 결과출력
    if (response.data == true) {
        alert('저장 성공');
        boardfindAll(); // 저장 성공시 전체조회 재호출
    }
    else { alert('저장 실패'); }
}

async function boardUpdate(no) {
    // 1. 수정할 내용 입력받기 prompt
    const content = prompt('수정할 내용: ');

    // 2. 수정 처리 : axios 이용하여 백엔드에게 수정 요청/응답
    const response = await axios.put(`http://127.0.0.1:8080/board/update?no=${no}&content=${content}`);

    // 3. 결과
    if(response.data == true){
        alert('수정 성공');
        boardfindAll();
    } else {
        alert('수정 실패');
    }
}

async function boardDelete(no) {
    // 1. 삭제할 작성자 -> 매개변수

    // 2. 삭제 처리
    const response = await axios.delete(`http://127.0.0.1:8080/board/delete?no=${no}`);

    // 3. 결과 확인
    if(response.data == true){
        alert('삭제 성공');
        boardfindAll();
    } else {
        alert('삭제 실패');
    }
}