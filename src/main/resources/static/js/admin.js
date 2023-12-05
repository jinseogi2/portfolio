function click_main() {
  window.location.href = "/admin";
}

function click_notice() {
  window.location.href = "/admin_notice";
}

function click_member() {
  window.location.href = "/admin_member";
}

function click_menu() {
  window.location.href = "/admin_menu";
}
function click_order() {
  window.location.href = "/admin_order";
}

function func_member_ed(memberNo) {
  window.location.href = "/admin_member_ed?memberNo=" + memberNo;
}
function func_menu_ed(itemNo) {
  window.location.href = "/admin_menu_ed?itemNo=" + itemNo;
}

function func_member_updateAction() {
  // MemberEdDto에 속해있는 변수에 1:1 매칭할것들을 받아온다.

  const inputMemberNo = document.getElementById("inputMemberNo").value;
  const inputMemberId = document.getElementById("inputMemberId").value;
  const inputMemberName = document.getElementById("inputMemberName").value;
  const inputMemberPw = document.getElementById("inputMemberPw").value;
  const inputMemberEmail = document.getElementById("inputMemberEmail").value;

  var memberRole = document.getElementById("inputMemberRole");
  const inputMemberRole = memberRole.options[memberRole.selectedIndex].value;

  const inputMemberStamp = document.getElementById("inputMemberStamp").value;

  // 오브젝트로 포장해서 보내기위해 오브젝트화를 한다.
  // 이때 MemberEdDto에 들어가있는 변수랑 이름이 같아야한다.
  let params = {
    memberNo: inputMemberNo,
    memberId: inputMemberId,
    memberName: inputMemberName,
    memberPw: inputMemberPw,
    memberEmail: inputMemberEmail,
    memberRole: inputMemberRole,
    memberStamp: inputMemberStamp,
  };

  console.log("params : " + params);

  // memberUpdateAction으로 POST한다(보낸다)
  // 컨트롤러에서 PostMapping("/memberUpdateAction") 을 해야한다.
  fetch("/memberUpdateAction", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(params),
  })
    .then((response) => {
      console.log("response:" + response);
      return response.json();
    }) // 컨트롤러에서 보낸것을 받는다.
    // json에는 ResultDto가 받아진다.
    .then((json) => {
      //{ status: "ok", result: 5 }
      console.log("json:" + json);

      //원래페이지로 이동
      window.location.href = "/admin_member";
    }) //실제 데이타
    .catch((error) => {
      console.log(error);
    });
}

function func_member_delete(memberNo) {
  const result = confirm("삭제할까요?");
  if (result == false) {
    return;
  }

  let params = {
    memberNo: memberNo,
  };

  fetch("/memberDeleteAction", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(params),
  })
    .then((response) => {
      console.log("response:" + response);
      return response.json();
    }) //HTTP 응답
    .then((json) => {
      //{ status: "ok", result: 5 }
      console.log("json:" + json);

      //다음페이지로 이동
      window.location.href = "/admin_member";
    }) //실제 데이타
    .catch((error) => {
      console.log(error);
    });
}

function func_admin_notice_add() {
  window.location.href = "/admin_notice_add";
}

function func_notice_ed(noticeNo) {
  window.location.href = "/admin_notice_ed?noticeNo=" + noticeNo;
}

function func_notice_delete(noticeNo) {
  const result = confirm("삭제할까요?");
  if (result == false) {
    return;
  }

  let params = {
    noticeNo: noticeNo,
  };

  fetch("/noticeDeleteAction", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(params),
  })
    .then((response) => {
      console.log("response:" + response);
      return response.json();
    }) //HTTP 응답
    .then((json) => {
      //{ status: "ok", result: 5 }
      console.log("json:" + json);

      //다음페이지로 이동
      window.location.href = "/admin_notice";
    }) //실제 데이타
    .catch((error) => {
      console.log(error);
    });
}
// 버튼 클릭 시 숨겨진 파일 입력란을 클릭하는 함수
function onClickUpload() {
  let inputItemImageUrl = document.getElementById("inputItemImageUrl");
  inputItemImageUrl.click();
}

// 파일이 선택되면 선택한 이미지 미리보기를 표시하는 함수
function readURL(input) {
  if (input.files && input.files[0]) {
    var reader = new FileReader();
    reader.onload = function (e) {
      document.getElementById("imgItemImageUrl").src = e.target.result;
    };
    reader.readAsDataURL(input.files[0]);
  } else {
    document.getElementById("imgItemImageUrl").src = "";
  }

  let inputItemImageUrl = document.getElementById("inputItemImageUrl");
  console.log("input:file value:" + inputItemImageUrl.value);
  console.log("files:" + inputItemImageUrl.files[0]);
}

// 메뉴 업데이트 작업이 트리거될 때 호출되는 함수
function func_menu_updateAction() {
  image_upload(); // image_upload 함수 호출
}

// fetch API를 사용하여 이미지 업로드를 처리하는 함수
function image_upload() {
  let inputItemImageUrl = document.getElementById("inputItemImageUrl");
  console.log(inputItemImageUrl);

  let fileUrl = inputItemImageUrl.value; // 파일 경로 가져오기
  console.log(fileUrl);
  let index = fileUrl.lastIndexOf("\\");
  let fileName = fileUrl.substr(index + 1); // 경로에서 파일 이름 추출
  console.log("fileName:" + fileName);

  // 파일을 multipart/form-data 요청으로 보내기 위한 FormData 객체 생성
  let form = new FormData();
  form.enctype = "multipart/form-data";
  form.append("file", inputItemImageUrl.files[0], fileName);

  // "/upload" 엔드포인트로 POST 요청 보내기
  fetch("/upload", {
    method: "POST",
    headers: {
    },
    body: form, // 요청 본문에 FormData 객체 포함
  })
    .then((response) => {
      console.log("response:" + response);
      console.log("response:" + JSON.stringify(response));

      return response.json(); // JSON 응답 파싱
    })
    .then((json) => {
      //{ status: "ok", result: 5 }
      console.log("json:" + json);
      console.log("json:" + JSON.stringify(json));
      console.log("uploadFileName:" + json.uploadFileName);

      func_menu_updateAction_json(json.uploadFileName); // 얻은 uploadFileName을 사용하여 함수 호출
    })
    .catch((error) => {
      console.log(error); // fetch 요청 중에 발생한 오류 기록
    });
}
// JSON 형식의 아이템 이미지 URL을 받아와서 관련된 폼 데이터를 서버로 전송하는 함수
function func_menu_updateAction_json(itemImageUrl) {
  // 입력 요소들의 값을 가져오기
  const inputItemNo = document.getElementById("inputItemNo").value;
  const inputItemName = document.getElementById("inputItemName").value;
  const inputItemCode = document.getElementById("inputItemCode").value;

  var itemCate = document.getElementById("inputItemCate");
  const inputItemCate = itemCate.options[itemCate.selectedIndex].value;

  const inputItemRecommend =
    document.getElementById("inputItemRecommend").value;
  const inputItemPrice = document.getElementById("inputItemPrice").value;
  //const itemImageUrl = document.getElementById("imgItemImageUrl").src;
  const inputItemExplanation = document.getElementById(
    "inputItemExplanation"
  ).value;

  // 서버에 전송할 파라미터 객체 생성
  // 이때 MemberEdDto에 들어가있는 변수랑 이름이 같아야한다.
  let params = {
    itemNo: inputItemNo,
    itemName: inputItemName,
    itemCode: inputItemCode,
    itemCate: inputItemCate,
    itemRecommend: inputItemRecommend,
    itemPrice: inputItemPrice,
    itemImageUrl: itemImageUrl,
    itemExplanation: inputItemExplanation,
  };

  // 서버로 POST 요청 보내기
  fetch("/menuUpdateAction", {
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
      console.log("json:" + json);
      // 원래 페이지로 이동
      window.location.href = "/admin_menu";
    }) // 실제 데이터
    .catch((error) => {
      console.log(error);
    });
}

// 아이템을 삭제하는 함수
function func_item_delete(itemNo) {
  // 사용자에게 삭제 여부를 확인하는 창 띄우기
  const result = confirm("삭제할까요?");
  if (result == false) {
    return;
  }

  // 서버로 전송할 파라미터 객체 생성
  let params = {
    itemNo: itemNo,
  };

  // 서버로 POST 요청 보내기
  fetch("/itemDeleteAction", {
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
      console.log("json:" + json);

      // 다음 페이지로 이동
      window.location.href = "/admin_menu";
    }) // 실제 데이터
    .catch((error) => {
      console.log(error);
    });
}

//공지 사항 추가 함수
let inputNoticeImageUrl = document.getElementById("inputNoticeImgUrl");
// 버튼 클릭 시 숨겨진 파일 입력란을 클릭하는 함수
function onClickNoticeAdd() {
  let inputNoticeImageUrl = document.getElementById("inputNoticeImgUrl");
  inputNoticeImgUrl.click();
}

// 파일이 선택되면 선택한 이미지 미리보기를 표시하는 함수
function readURL(input) {
  if (input.files && input.files[0]) {
    var reader = new FileReader();
    reader.onload = function (e) {
      document.getElementById("notice_add_img").src = e.target.result;
    };
    reader.readAsDataURL(input.files[0]);
  } else {
    document.getElementById("notice_add_img").src = "";
  }

  let inputNoticeImageUrl = document.getElementById("inputNoticeImgUrl");
  console.log("input:file value:" + inputNoticeImgUrl.value);
  console.log("files:" + inputNoticeImgUrl.files[0]);
}

// 메뉴 업데이트 작업이 트리거될 때 호출되는 함수
function func_notice_addAction() {
  noticeUpload(); // image_upload 함수 호출
}

// fetch API를 사용하여 이미지 업로드를 처리하는 함수
function noticeUpload() {
  let inputNoticeImgUrl = document.getElementById("inputNoticeImgUrl");
  console.log(inputNoticeImgUrl);

  let fileUrl = inputNoticeImgUrl.value; // 파일 경로 가져오기
  console.log(fileUrl);
  let index = fileUrl.lastIndexOf("\\");
  let fileName = fileUrl.substr(index + 1); // 경로에서 파일 이름 추출
  console.log("fileName:" + fileName);

  // 파일을 multipart/form-data 요청으로 보내기 위한 FormData 객체 생성
  let form = new FormData();
  form.enctype = "multipart/form-data";
  form.append("file", inputNoticeImgUrl.files[0], fileName);

  // "/upload" 엔드포인트로 POST 요청 보내기
  fetch("/noticeUpload", {
    method: "POST",
    headers: {
    },
    body: form, // 요청 본문에 FormData 객체 포함
  })
    .then((response) => {
      console.log("response:" + response);
      console.log("response:" + JSON.stringify(response));

      return response.json(); // JSON 응답 파싱
    })
    .then((json) => {
      //{ status: "ok", result: 5 }
      console.log("json:" + json);
      console.log("json:" + JSON.stringify(json));
      console.log("uploadFileName:" + json.uploadFileName);

      func_notice_addAction_json(json.uploadFileName); // 얻은 uploadFileName을 사용하여 함수 호출
    })
    .catch((error) => {
      console.log(error); // fetch 요청 중에 발생한 오류 기록
    });
}
// JSON 형식의 아이템 이미지 URL을 받아와서 관련된 폼 데이터를 서버로 전송하는 함수
function func_notice_addAction_json(inputNoticeImgUrl) {
  // 입력 요소들의 값을 가져오기
  const noticeTitle = document.getElementById("inputNoticeName").value;
  const noticeContent = document.getElementById("inputNoticeInfo").value;
  const noticeImageUrl = document.getElementById("inputNoticeImgUrl").value
  let index = noticeImageUrl.lastIndexOf("\\");
    let fileName = noticeImageUrl.substr(index + 1); // 경로에서 파일 이름 추출
    console.log("fileName:" + fileName);

  var noticeType = document.getElementById("noticeType");
  var inputNoticeCate = noticeType.options[noticeType.selectedIndex].value;

  // 서버에 전송할 파라미터 객체 생성
  // 이때 NoticeAddDto에 들어가있는 변수랑 이름이 같아야한다.
  let params = {
    noticeTitle : noticeTitle,
    noticeContent : noticeContent,
    noticeType: inputNoticeCate,
    noticeImageUrl : inputNoticeImgUrl,
  };

  // 서버로 POST 요청 보내기
  fetch("/admin_noticeAdd", {
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
      console.log("json:"+ json);
      // 원래 페이지로 이동

     // window.location.href = "/admin_notice";
    }) // 실제 데이터
    .catch((error) => {
      console.log(error);
    });
}
