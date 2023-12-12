var btncheck = 0;
// var btncheck2 = 0;

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

// function downpage2() {
//   if (btncheck2 == 0) {
//     $("#btnimg2").attr("src", "./img/paycard/down.png");
//     $(".sec2_text").css("display", "none");
//     btncheck2 = 1;
//   } else {
//     $("#btnimg2").attr("src", "./img/paycard/up.png");
//     $(".sec2_text").css("display", "block");
//     btncheck2 = 0;
//   }
// }


var itemPrice = 0;

function count(type) {
  // 결과를 표시할 element
  const resultElement = document.getElementById("result");
  const priceElement = document.getElementById("priceforadd");
  const inputItemElement = document.getElementById("inputItemPrice");

  // 현재 화면에 표시된 값
  let result = parseInt(resultElement.innerText); // 갯수

   if(!itemPrice){  // 아이탬의 가격
    itemPrice = parseInt(priceElement.innerText);
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

  // 새로운 가격을 표시
  priceElement.innerText = itemPrice * result;
  inputItemElement.innerText  =  itemPrice * result;

}

function noneOption() {
    var itemPriceDiv = document.getElementById('inputItemPrice');
    var priceForAddDiv = document.getElementById('priceforadd');

    // 현재 아이템 가격 가져오기
    var currentPrice = parseInt(itemPriceDiv.innerText, 10);

    // 가격을 700 감소시키기 (음수로 변하지 않도록 최소값 설정)
    var newPrice = Math.max(currentPrice - 700, 0);

    // 감소된 가격 업데이트
    itemPriceDiv.innerText = newPrice;
    priceForAddDiv.innerText = newPrice + '원';
}

