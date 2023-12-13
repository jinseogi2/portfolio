var btncheck = 0;

// 토글 버튼 함수
function downpage() {
  if (btncheck == 0) {
    $("#btnimg").attr("src", "./img/select/down.png");
    $(".down-con").css("display", "none");
    btncheck = 1;
  } else {
    $("#btnimg").attr("src", "./img/select/up.png");
    $(".down-con").css("display", "block");
    btncheck = 0;
  }
}

// 수량 및 가격 업데이트 함수
function count(type) {
  // 요소 참조
  const basePrice = document.getElementById("inputPrice");
  const resultElement = document.getElementById("result");
  const priceElement = document.getElementById("priceforadd");
  const inputItemElement = document.getElementById("inputItemPrice");

  // 현재 값
  let result = parseInt(resultElement.innerText); // 갯수
  let itemPrice = parseInt(basePrice.value); // 아이템의 가격

  // 더하기/빼기
  if (type === "plus") {
    result += 1; // 수량을 1 증가
  } else if (type === "minus") {
    if (result > 0) {
      result -= 1; // 수량을 1 감소
    }
  }

  // 새로운 수량 및 가격 표시
  resultElement.innerText = result;
  priceElement.innerText = itemPrice * result + servePrice;
  inputItemElement.innerText = itemPrice * result + servePrice;
}

var servePrice = 0;

// 라디오박스 요소 참조
const radioNone = document.getElementById("none");
const radioOption = document.getElementById("option");

// 라디오박스 상태 변화 이벤트 처리
radioOption.addEventListener("change", function () {
  if (radioOption.checked) {
    console.log("스모어 잼 추가가 체크되었습니다.");
    // 스모어 잼 추가가 체크되었을 때의 동작 추가
    servePrice = 700;
    count("check");
  }
});

// 라디오박스 상태 변화 이벤트 처리
radioNone.addEventListener("change", function () {
  if (radioNone.checked) {
    console.log("선택안함이 체크되었습니다.");
    // 선택안함이 체크되었을 때의 동작 추가
    servePrice = 0;
    count("uncheck");
  }
});

// 장바구니에 상품 추가 함수
function func_cart() {
  let cartName = document.getElementById("inner-title").innerText;
  let cartImageUrl = document.getElementById("inputcartImageUrl").src;
  let cartPrice = document.getElementById("inputPrice").value;
  let cartCount = document.getElementById("result").innerText;
  let cart_option1_name = document.getElementById("option_name").innerText;
  let cart_option1_price = document.getElementById("option_price").value;

  console.log("cartName :: " + cartName);
  console.log("cartImageUrl :: " + cartImageUrl);
  console.log("cartPrice :: " + cartPrice);
  console.log("cartCount :: " + cartCount);
  console.log("cart_option1_name :: " + cart_option1_name);
  console.log("cart_option1_price :: " + cart_option1_price);

  let params = {
    cartName: cartName,
    cartImageUrl: cartImageUrl,
    cartPrice: cartPrice,
    cartCount: cartCount,
    cartOption1Name: cart_option1_name,
    cartOption1Price: cart_option1_price,
  };

  // 서버로 POST 요청 보내기
  fetch("/cartAddAction", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(params),
  })
    .then((response) => {
      console.log("response:" + response);
      return response.json();
    }) // 서버 응답
    .then((json) => {
      //{ status: "ok", result: 5 }
      //window.location.href = "/cart";
      alert("장바구니에 등록됐습니다.");
    }) // 실제 데이터
    .catch((error) => {
      console.log(error);
    });
}

// 장바구니 페이지로 이동 함수
function goCart() {
  let cartName = document.getElementById("inner-title").innerText;
  let cartImageUrl = document.getElementById("inputcartImageUrl").src;
  let cartPrice = document.getElementById("inputPrice").value;
  let cartCount = document.getElementById("result").innerText;
  let cart_option1_name = document.getElementById("option_name").innerText;
  let cart_option1_price = document.getElementById("option_price").value;

  console.log("cartName :: " + cartName);
  console.log("cartImageUrl :: " + cartImageUrl);
  console.log("cartPrice :: " + cartPrice);
  console.log("cartCount :: " + cartCount);
  console.log("cart_option1_name :: " + cart_option1_name);
  console.log("cart_option1_price :: " + cart_option1_price);

  let params = {
    cartName: cartName,
    cartImageUrl: cartImageUrl,
    cartPrice: cartPrice,
    cartCount: cartCount,
    cartOption1Name: cart_option1_name,
    cartOption1Price: cart_option1_price,
  };

  // 서버로 POST 요청 보내기
  fetch("/cartAddAction", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(params),
  })
    .then((response) => {
      console.log("response:" + response);
      return response.json();
    }) // 서버 응답
    .then((json) => {
      //{ status: "ok", result: 5 }
      window.location.href = "/cart";

    }) // 실제 데이터
    .catch((error) => {
      console.log(error);
    });
}
