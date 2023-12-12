var btncheck = 0;

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

var itemPrice = 0;
var check = 0;
function count(type) {
  // 결과를 표시할 element
  const resultElement = document.getElementById("result");
  const priceElement = document.getElementById("priceforadd");
  const inputItemElement = document.getElementById("inputItemPrice");

  // 현재 화면에 표시된 값
  let result = parseInt(resultElement.innerText); // 갯수

  if (itemPrice == 0 && check == 0) {
    // 아이템의 가격
    itemPrice = parseInt(priceElement.innerText);
    check = 1;
  }

  // 더하기/빼기
  if (type === "plus") {
    result += 1; // 수량을 1 증가
  } else if (type === "minus") {
    if (result > 0) {
      result -= 1; // 수량을 1 감소
    }
  }

  // 새로운 수량을 표시
  resultElement.innerText = result;
  priceElement.innerText = itemPrice * result + servePrice;
  inputItemElement.innerText = itemPrice * result + servePrice;
}

var radioCheck = 0;
var servePrice = 0;

// 라디오박스 요소 참조
const radioNone = document.getElementById("none");
const radioOption = document.getElementById("option");

radioOption.addEventListener("change", function () {
  const priceElement = document.getElementById("priceforadd");
  const inputItemElement = document.getElementById("inputItemPrice");
  if (radioOption.checked) {
    console.log("스모어 잼 추가가 체크되었습니다.");
    // 스모어 잼 추가가 체크되었을 때의 동작 추가
    if (radioCheck == 0) {
      radioCheck = 1;
      servePrice = 700;
      priceElement.innerText = parseInt(priceElement.innerText) + servePrice;
      inputItemElement.innerText =
        parseInt(inputItemElement.innerText) + servePrice;
    }
  }
});

// 라디오박스 상태 변화 이벤트 처리
radioNone.addEventListener("change", function () {
  const priceElement = document.getElementById("priceforadd");
  const inputItemElement = document.getElementById("inputItemPrice");

  if (radioNone.checked) {
    console.log("선택안함이 체크되었습니다.");
    // 선택안함이 체크되었을 때의 동작 추가
    if (radioCheck == 1) {
      radioCheck = 0;
      priceElement.innerText = parseInt(priceElement.innerText) - servePrice;
      inputItemElement.innerText =
        parseInt(inputItemElement.innerText) - servePrice;

      servePrice = 0;
    }
  }
});

// CREATE TABLE megacoffee.cart (
// 	cart_no INT AUTO_INCREMENT NOT NULL PRIMARY KEY, -- 카트 번호
// 	cart_name VARCHAR(255) NOT NULL, -- 상품 이름
// 	cart_image_url TEXT NULL, -- 카트에 들어오는 이미지 url ( 아이탬 )
// 	cart_price INT NOT NULL, -- 상품 금액
// 	cart_count TINYINT NOT NULL, -- 상품 개수

//    cart_option1_name VARCHAR(255) NULL, -- 옵션1 이름
//    cart_option1_price INT NULL, -- 옵션1 금액
//    cart_option2_name VARCHAR(255) NULL, -- 옵션2 이름
//    cart_option2_price INT NULL, -- 옵션2 금액
//    cart_option3_name VARCHAR(255) NULL, -- 옵션3 이름
//    cart_option3_price INT NULL, -- 옵션3 금액

// 	cart_datetime DATETIME DEFAULT NOW() -- 작성시간
// );

function func_cart() {
  let cartName = document.getElementById("inner-title").innerText;
  let cartImageUrl = document.getElementById("inputcartImageUrl").src;
  let cartPrice = document.getElementById("inputPrice").value;
  let cartCount = document.getElementById("result").innerText;
  let cart_option1_name = "스모어잼";
  let cart_option1_price = 700;

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
function goCart(){
window.location.href = "/cart";
}
