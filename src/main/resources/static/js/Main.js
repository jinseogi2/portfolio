window.onload = function () {
  event_rollimg();
  basic_rollimg();
};

function seeMore(){
window.location.href="/lastPage";
}
function megaOrder(){
//window.location.href="/orderpage";
}
function payCard(){
window.location.href="/payCard";
}
function mainPage(){
window.location.href="/main";
}
function stampPage(){
window.location.href="/stampPage";
}

// 이미지 롤링을 처리하는 함수
function event_rollimg() {
  // 이미지 목록 요소들을 가져옴
  let eImgList = document.getElementsByClassName("eImg-list");

  // 현재 이미지의 인덱스를 표시하는 입력 요소를 가져옴
  let eIndex = document.getElementById("eIndex");

  // 이미지 목록이 비어있지 않은 경우에만 실행
  if (eImgList.length > 0) {
    // 2초마다 실행되는 타이머 설정
    setInterval(function () {
      // 현재 인덱스의 값을 가져옴
      let eIndexValue = Number(eIndex.value);

      // 현재 인덱스가 유효한 범위 내에 있는 경우
      if (eIndexValue >= 0 && eIndexValue < eImgList.length) {
        // 현재 인덱스의 이미지 파일명을 가져옴
        let eImageName = eImgList[eIndexValue].value;

        // 다음 이미지로 인덱스를 증가시킴
        eIndex.value = String(eIndexValue + 1);

        // 순수 JavaScript로 이미지 소스 변경
        // 주의: 해당 요소의 ID가 'roll'이며, 이미지가 표시되는 img 요소여야 함
        document.getElementById("roll").src = eImageName;
      } else {
        // 인덱스가 유효 범위를 벗어나면 첫 번째 이미지로 되돌림
        eIndexValue = 0;
        eIndex.value = 0;
      }
    }, 2000); // 2초(2000밀리초) 간격으로 실행
  }
}

// 이미지 롤링을 처리하는 함수
function basic_rollimg() {
  // 이미지 목록 요소들을 가져옴
  let bImgList = document.getElementsByClassName("bImg-list");

  // 현재 이미지의 인덱스를 표시하는 입력 요소를 가져옴
  let bIndex = document.getElementById("bIndex");

  // 이미지 목록이 비어있지 않은 경우에만 실행
  if (bImgList.length > 0) {
    // 2초마다 실행되는 타이머 설정
    setInterval(function () {
      // 현재 인덱스의 값을 가져옴
      let bIndexValue = Number(bIndex.value);

      // 현재 인덱스가 유효한 범위 내에 있는 경우
      if (bIndexValue >= 0 && bIndexValue < bImgList.length) {
        // 현재 인덱스의 이미지 파일명을 가져옴
        let bImageName = bImgList[bIndexValue].value;

        // 다음 이미지로 인덱스를 증가시킴
        bIndex.value = String(bIndexValue + 1);

        // 배경 이미지를 변경
        // 주의: 해당 요소의 ID가 'secimg'여야 함
        document.getElementById("secimg").style.backgroundImage =
          "url('" + bImageName + "')";
      } else {
        // 인덱스가 유효 범위를 벗어나면 첫 번째 이미지로 되돌림
        bIndexValue = 0;
        bIndex.value = 0;
      }
    }, 2000); // 2초(2000밀리초) 간격으로 실행
  }
}

function modal_item_on(element) {
  // 모달 엘리먼트 가져오기
  var modal = document.querySelector('.modal_item');

  // 모달 내 이미지 엘리먼트 가져오기
  var imgElement = modal.querySelector('.item_img img');

  // 모달 내 텍스트 엘리먼트 가져오기
  var textElement = modal.querySelector('.item_box .item_text');

  // 모달 내 아이템 이름 엘리먼트 가져오기
  var itemNameElement = modal.querySelector('.item_box .item_name');

  // 이미지 소스 설정
  imgElement.src = element.getAttribute('data-item-image-url');

  // 텍스트 설정
  textElement.textContent = element.getAttribute('data-item-explanation');

  // 아이템 이름 설정
  itemNameElement.textContent = element.getAttribute('data-item-name');

  // 모달 창 보이기
  modal.style.display = 'block';
}

function modal_item_off(){
    var modal = document.querySelector('.modal_item');
    modal.style.display = 'none';
}


function modal_barcode_on() {
    var modal = document.querySelector('.modal_barcode');
    var overlay = document.getElementById('modalOverlay');

    modal.style.display = 'block';
    overlay.style.display = 'block';

    // 모달 외부를 클릭할 때 closeModal 함수 호출
    function clickHandler(event) {
        event.stopPropagation(); // 이벤트 전파 중지
        if (!modal.contains(event.target)) {
            // 모달 외부를 클릭한 경우에만 closeModal 함수 호출
            closeModal();
        }
    }

    // 모달 외부 클릭 이벤트 등록
    window.addEventListener('click', clickHandler, true);

    // 모달을 닫는 함수
    function closeModal() {
        modal.style.display = 'none';
        overlay.style.display = 'none';

        // 모달 외부 클릭 이벤트 제거
        window.removeEventListener('click', clickHandler, true);
    }
}

function func_event(noticeNo) {
  window.location.href = "/event?noticeNo=" + noticeNo;
}
function back() {
    history.back();
  }

