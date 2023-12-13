document.addEventListener("DOMContentLoaded", function () {
    // 초기 html이 켜졌을 때
    html_Start_Set();
    updateTotalPrice();
    updateTotalCount();
        // 5초 후에 리디렉션
    setTimeout(function() {
        window.location.href = "/";
    }, 5000); // 5000 밀리초 = 5초
});

function html_Start_Set() {
    // 주문번호 설정
    let ordernum = document.getElementById("ordernum");
    let randNum = parseInt(getRandomNumber());
    ordernum.textContent = "주문번호 : " + randNum;

    // 주문시간 설정
    let ordertime = document.getElementById("ordertime");
    ordertime.textContent = getCurrentDateTime();
}

// 주문번호 랜덤으로 출력하기 위해서
function getRandomNumber() {
    return Math.floor(Math.random() * 9999) + 1;
}

// 현재 날짜와 시간을 얻기 위한 함수
function getCurrentDateTime() {
    var now = new Date();
    var year = now.getFullYear();
    var month = (now.getMonth() + 1).toString().padStart(2, '0'); // 월은 0부터 시작하므로 1을 더함
    var day = now.getDate().toString().padStart(2, '0');
    var hours = now.getHours().toString().padStart(2, '0');
    var minutes = now.getMinutes().toString().padStart(2, '0');

    // 현재 날짜와 시간을 문자열로 반환
    return year + '. ' + month + '. ' + day + '. ' + hours + ':' + minutes;
}

function updateTotalPrice() {
    // 숨겨진 입력 필드에서 값 가져오기
    var priceElements = document.querySelectorAll(".cartinfo_price");
    var countElements = document.querySelectorAll(".cartinfo_count");
    var optionPrice1Elements = document.querySelectorAll(".cartinfo_option1Price");
    var optionPrice2Elements = document.querySelectorAll(".cartinfo_option2Price");
    var optionPrice3Elements = document.querySelectorAll(".cartinfo_option3Price");

    // 각 totalPrice 엘리먼트를 가져오기
    var totalPriceElements = document.querySelectorAll(".price");

    // 전체 총합 초기화
    var totalSum = 0;

    // 각 엘리먼트들을 순회하며 총합 계산
    for (var i = 0; i < priceElements.length; i++) {
        // 필요한 요소가 존재하는지 확인
        if (
            priceElements[i] &&
            countElements[i] &&
            optionPrice1Elements[i] &&
            optionPrice2Elements[i] &&
            optionPrice3Elements[i]
        ) {
            // 가격 및 수량 값 가져오기
            var price = parseInt(priceElements[i].value);
            var count = parseInt(countElements[i].value);

            // 옵션 가격 값 가져오기
            var optionPrice1 = parseFloat(optionPrice1Elements[i].value) || 0;
            var optionPrice2 = parseFloat(optionPrice2Elements[i].value) || 0;
            var optionPrice3 = parseFloat(optionPrice3Elements[i].value) || 0;

            // 총 가격 계산 및 총합에 더하기
            var totalPrice = (price * count) + optionPrice1 + optionPrice2 + optionPrice3;
            totalSum += totalPrice;

            // 각 totalPrice 엘리먼트의 innerText 업데이트
            if (totalPriceElements[i]) {
                totalPriceElements[i].innerText = totalPrice + "원";
            }
        } else {
            console.error("One or more elements not found.");
        }
    }

    // 전체 총합을 표시할 엘리먼트 가져오기
    var totalSumElement = document.querySelector(".payment1");

    // 전체 총합을 업데이트
    if (totalSumElement) {
        totalSumElement.innerText = totalSum + "원";
    } else {
        console.error("Total sum element not found.");
    }
}

function updateTotalCount() {
    // "cartinfo_count" 클래스를 가진 모든 요소 찾기
    var countElements = document.querySelectorAll('.cartinfo_count');

    // 합계를 저장할 변수 초기화
    var totalCount = 0;

    // 각 요소를 반복하면서 값을 합산
    countElements.forEach(function (element) {
        var countValue = parseInt(element.value);
        // 숫자인지 확인한 후 합산
        if (!isNaN(countValue)) {
            totalCount += countValue;
        }
    });

    // 결과를 "stamp1"의 텍스트로 설정
    var stamp1Element = document.getElementById('stamp1');
    if (stamp1Element) {
        stamp1Element.innerText = totalCount;
    } else {
        console.error("Element with ID 'stamp1' not found.");
    }
}
