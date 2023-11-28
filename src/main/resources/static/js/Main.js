window.onload = function () {
  mainrollimg();
  eventrollimg();
};

//메인이미지 돌리기
// 메인 이미지 돌리기
function mainrollimg() {
  let eImgList = document.getElementsByClassName("eImg-list");
  let eIndex = document.getElementById("eIndex");

  if (eImgList.length > 0) {
    setInterval(function () {

      let eIndexValue = Number(eIndex.value);
      if (eIndexValue >= 0 && eIndexValue < eImgList.length) {
        let eImageName = eImgList[eIndexValue].value;
        eIndex.value = String(eIndexValue + 1);
        // 순수 JavaScript로 이미지 소스 변경
        document.getElementById("roll").src = eImageName;
      }

    }, 2000);

  }
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
