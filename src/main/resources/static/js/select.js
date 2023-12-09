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

function count(type) {
  // 결과를 표시할 element
  const resultElement = document.getElementById("result");

  // 현재 화면에 표시된 값
  let number = resultElement.innerText;

  // 더하기/빼기
  if (type === "plus") {
    number = parseInt(number) + 1;
  } else if (type === "minus") {
    number = parseInt(number) - 1;
  }

  // 결과 출력
  resultElement.innerText = number;
}
