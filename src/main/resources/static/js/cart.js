// 삭제 함수
function func_cart_delete(cartNo) {
    // 삭제 여부 확인
    if (confirm("정말로 삭제하시겠습니까?")) {
        // 확인 시 해당 카트 번호로 페이지 이동
        window.location.href = "/cartRe?cartNo=" + cartNo;
    }
}

var indexCount = 1;

// 수량 조절 함수
function count(action) {
    // 현재 수량 가져오기
    var index = parseInt(document.getElementById('result').innerText);

    // 증가 또는 감소 동작에 따라 수량 조절
    if (action === "plus") {
        index++;
    } else if (action === "minus" && index > 1) {
        index--;
    }
    indexCount = index;

    // 조절된 수량을 HTML에 반영
    document.getElementById('result').innerText = index;

    updateTotalPrice();
}

document.addEventListener("DOMContentLoaded", function () {
    updateTotalPrice(); // 초기 총 가격을 계산하고 표시합니다.
});


// 콤마찍어주는 함수
function addCommas(number) {
    return number.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
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
            var optionPrice1 = 0;
            if (optionPrice1Elements[i] && optionPrice1Elements[i].value !== "") {
                optionPrice1 = parseFloat(optionPrice1Elements[i].value);
            }

            var optionPrice2 = 0;
            if (optionPrice2Elements[i] && optionPrice2Elements[i].value !== "") {
                optionPrice2 = parseFloat(optionPrice2Elements[i].value);
            }

            var optionPrice3 = 0;
            if (optionPrice3Elements[i] && optionPrice3Elements[i].value !== "") {
                optionPrice3 = parseFloat(optionPrice3Elements[i].value);
            }

            // 총 가격 계산 및 총합에 더하기
            var totalPrice = indexCount * price * count + optionPrice1 + optionPrice2 + optionPrice3;
            totalSum += totalPrice;

            // 각 totalPrice 엘리먼트의 innerText 업데이트
            if (totalPriceElements[i]) {
                totalPriceElements[i].innerText = addCommas(totalPrice) + " 원";
            }
        } else {
            console.error("One or more elements not found.");
        }
    }

    // 전체 총합을 표시할 엘리먼트 가져오기
    var totalSumElement = document.querySelector(".footer_inner_price");

    // 전체 총합을 업데이트
    if (totalSumElement) {
        totalSumElement.innerText = addCommas(totalSum) + " 원";
    } else {
        console.error("Total sum element not found.");
    }
}

function goMain() {
    window.location.href = "/main";
}

function goOrder() {
    window.location.href = "/lastorderpage";
}
