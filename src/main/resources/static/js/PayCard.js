var btncheck = 0;
var btncheck2 = 0;

function downpage() {
  if (btncheck == 0) {
    $("#btnimg").attr("src", "./img/paycard/down.png");
    $(".sec1_text").css("display", "none");
    btncheck = 1;
  } else {
    $("#btnimg").attr("src", "./img/paycard/up.png");
    $(".sec1_text").css("display", "block");
    btncheck = 0;
  }
}

function downpage2() {
  if (btncheck2 == 0) {
    $("#btnimg2").attr("src", "./img/paycard/down.png");
    $(".sec2_text").css("display", "none");
    btncheck2 = 1;
  } else {
    $("#btnimg2").attr("src", "./img/paycard/up.png");
    $(".sec2_text").css("display", "block");
    btncheck2 = 0;
  }
}
