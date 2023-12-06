
// 페이지가 로드된 후 함수를 호출합니다.
window.addEventListener("load", setStampImages);


function setStampImages() {

  var stampIndex = document.getElementById("stampIndex").value;
  // 클래스가 "stamp_inner"인 모든 요소를 가져옵니다.
  var stampInnerElements = document.querySelectorAll(".stamp_inner");

  // 요소를 순회하며 소스와 opacity를 설정합니다.
  for (var i = 0; i < stampInnerElements.length; i++) {
    var imgElement = stampInnerElements[i].querySelector("img");
    if (i < stampIndex) {
      imgElement.src = "/img/stamp/stamp.png";
      imgElement.style.opacity = "1";
    } else {
      imgElement.src = "/img/stamp/circle.png";
      imgElement.style.opacity = "0.5";
    }
  }
}
