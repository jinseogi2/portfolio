var toggleCount = true;

function ToggleAction() {
  if (toggleCount) {
    $(".item_list .item_box")
      .removeClass("col-4")
      .addClass("row")
      .addClass("item_box_one");
    $(".item_img").addClass("col-4");
    $(".item_text").addClass("col-6");
    $("#nav_right_text").text("3열 보기");
  } else {
    $(".item_list .item_box")
      .removeClass("row")
      .removeClass("item_box_one")
      .addClass("col-4");
    $(".item_img").removeClass("col-4");
    $(".item_text").removeClass("col-6");
    $("#nav_right_text").text("1열 보기");
  }

  toggleCount = !toggleCount;
}
function goRecommand(){
window.location.href="/order_recommand";
}
function goHotcoffee(){
window.location.href="/order_hotcoffee";
}
function goIcecoffee(){
window.location.href="/order_icecoffee";
}
function goDecaf(){
window.location.href="/order_decaf";
}
function goSmoothie(){
window.location.href="/order_smoothie";
}
function goAde(){
window.location.href="/order_ade";
}
function goTea(){
window.location.href="/order_tea";
}
function goMain(){
window.location.href="/main";
}