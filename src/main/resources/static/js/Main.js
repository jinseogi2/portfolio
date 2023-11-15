window.onload = function () {
  mainrollimg();
  eventrollimg();
};

//메인이미지 돌리기
function mainrollimg() {
  var roll = 1;
  setInterval(function () {
    roll++;
    // 3장만있어서 3으로 돌려놓음
    if (roll > 3) roll = 1;
    $("#roll").attr("src", "img/main/roll_" + roll + ".jpg");
  }, 2000);
}

function eventrollimg() {
  var roll2 = 1;
  setInterval(function () {
    roll2++;
    // 3장만있어서 3으로 돌려놓음
    if (roll2 > 2) roll2 = 1;
    $("#secimg").css(
      "background-image",
      "url(img/main/roll-2_" + roll2 + ".jpg"
    );
  }, 3000);
}
