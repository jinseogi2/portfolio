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

//////////////////////
///////멤버 수정//////
//////////////////////
//멤버 수정 페이지를 새창으로 여는함수
function func_member_ed(memberNo) {
  window.open(
    "/admin_member_ed?memberNo=" + memberNo,
    "_blank",
    "width=502, height=575"
  );
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

      // 부모 창 새로고침
      window.opener.location.reload();
      // 현재 창(팝업) 닫기
      window.close();
      // 부모 창에서 "/admin_member" 페이지로 이동
      window.opener.location.href = "/admin_member";
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

/////////////////
//상품 추가하기//
/////////////////

//메뉴 추가 페이지를 새창으로 여는 함수

function func_admin_menu_add() {
  window.open("/admin_menu_add", "_blank", "width=845, height=445");
}

function menu_Add_onClickUpload() {
  let inputItemImageUrl = document.getElementById("inputItemImageUrl");
  inputItemImageUrl.click();
}

// 파일이 선택되면 선택한 이미지 미리보기를 표시하는 함수
function menu_Add_readURL(input) {
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
function func_menu_AddAction() {
  menu_Add_image_upload(); // image_upload 함수 호출
}

// fetch API를 사용하여 이미지 업로드를 처리하는 함수
function menu_Add_image_upload() {
  let inputItemImageUrl = document.getElementById("inputItemImageUrl");

  // 파일이 선택되었는지 확인
  if (inputItemImageUrl.files && inputItemImageUrl.files.length > 0) {
    // 파일이 선택되었을 때만 아래 코드가 실행됨
    let fileUrl = inputItemImageUrl.value;
    let index = fileUrl.lastIndexOf("\\");
    let fileName = fileUrl.substr(index + 1);

    let form = new FormData();
    form.append("file", inputItemImageUrl.files[0], fileName);

    fetch("/menu_Add_upload", {
      method: "POST",
      headers: {},
      body: form,
    })
      .then((response) => {
        return response.json();
      })
      .then((json) => {
        console.log("json:" + JSON.stringify(json));
        func_menu_AddAction_json(json.uploadFileName);
      })
      .catch((error) => {
        console.log(error);
      });
  } else {
    // 파일이 선택되지 않았을 때는 여기로 진입함
    console.log("파일이 선택되지 않았습니다.");
    alert("사진이 등록돼지 않았습니다.");
  }
}
// JSON 형식의 아이템 이미지 URL을 받아와서 관련된 폼 데이터를 서버로 전송하는 함수
function func_menu_AddAction_json(itemImageUrl) {
  // 입력 요소들의 값을 가져오기
  const inputItemName = document.getElementById("inputItemName").value;

  var itemCate = document.getElementById("inputItemCate");
  const inputItemCate = itemCate.options[itemCate.selectedIndex].value;

  const inputItemPrice = document.getElementById("inputItemPrice").value;

  var itemRecommend = document.getElementById("inputItemRecommend");
  const inputItemRecommend =
    itemRecommend.options[itemRecommend.selectedIndex].value;

  const inputItemExplanation = document.getElementById(
    "inputItemExplanation"
  ).value;

  // 서버에 전송할 파라미터 객체 생성
  // 이때 MemberEdDto에 들어가있는 변수랑 이름이 같아야한다.
  let params = {
    itemName: inputItemName,
    itemCate: inputItemCate,
    itemPrice: inputItemPrice,
    itemImageUrl: itemImageUrl,
    itemRecommend: inputItemRecommend,
    itemExplanation: inputItemExplanation,
  };

  // 서버로 POST 요청 보내기
  fetch("/menuAddAction", {
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

      // 부모 창 새로고침
      window.opener.location.reload();
      // 현재 창(팝업) 닫기
      window.close();
      // 부모 창에서 "/admin_member" 페이지로 이동
      window.opener.location.href = "/admin_menu";
    }) // 실제 데이터
    .catch((error) => {
      console.log(error);
    });
}

///////////////
// 상품 변경 //
//////////////

//메뉴 수정 버튼을 새창으로 여는 함수
function func_menu_ed(itemNo) {
  window.open(
    "/admin_menu_ed?itemNo=" + itemNo,
    "_blank",
    "width=824, height=430"
  );
}
// 버튼 클릭 시 숨겨진 파일 입력란을 클릭하는 함수
function menu_Update_onClickUpload() {
  let inputItemImageUrl = document.getElementById("inputItemImageUrl");
  inputItemImageUrl.click();
}

// 파일이 선택되면 선택한 이미지 미리보기를 표시하는 함수
function menu_Update_readURL(input) {
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
  menu_update_image_upload(); // image_upload 함수 호출
}

// fetch API를 사용하여 이미지 업로드를 처리하는 함수
function menu_update_image_upload() {
  let inputItemImageUrl = document.getElementById("inputItemImageUrl");

  // 파일이 선택되었는지 확인
  if (inputItemImageUrl.files && inputItemImageUrl.files.length > 0) {
    let fileUrl = inputItemImageUrl.value; // 파일 경로 가져오기
    let index = fileUrl.lastIndexOf("\\");
    let fileName = fileUrl.substr(index + 1); // 경로에서 파일 이름 추출

    // 파일을 multipart/form-data 요청으로 보내기 위한 FormData 객체 생성
    let form = new FormData();
    form.enctype = "multipart/form-data";
    form.append("file", inputItemImageUrl.files[0], fileName);

    // "/upload" 엔드포인트로 POST 요청 보내기
    fetch("/menu_Update_upload", {
      method: "POST",
      headers: {},
      body: form, // 요청 본문에 FormData 객체 포함
    })
      .then((response) => {
        return response.json(); // JSON 응답 파싱
      })
      .then((json) => {
        console.log("json:" + JSON.stringify(json));
        console.log("uploadFileName:" + json.uploadFileName);

        func_menu_updateAction_json(json.uploadFileName); // 얻은 uploadFileName을 사용하여 함수 호출
      })
      .catch((error) => {
        console.log(error); // fetch 요청 중에 발생한 오류 기록
      });
  } else {
    // 파일이 선택되지 않았을 때 실행할 코드
    console.log("파일이 선택되지 않았습니다.");

    // 파일이 선택되지 않았을 때 실행할 다른 fetch 요청 추가 가능
    func_menu_updateAction_none();
  }
}

// 이미지를 업데이트 안할때
function func_menu_updateAction_none(){
  const inputItemNo = document.getElementById("inputItemNo").value;
  const inputItemName = document.getElementById("inputItemName").value;
  const inputItemCode = document.getElementById("inputItemCode").value;
  const itemImageUrl = document.getElementById("imgItemImageUrl").src;

  console.log("itemImageUrl:: " + itemImageUrl);
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
  fetch("/menuUpdateAction_none", {
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
      // 부모 창 새로고침
      window.opener.location.reload();
      // 현재 창(팝업) 닫기
      window.close();
      // 부모 창에서 "/admin_member" 페이지로 이동
      window.opener.location.href = "/admin_menu";
    }) // 실제 데이터
    .catch((error) => {
      console.log(error);
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
      // 부모 창 새로고침
      window.opener.location.reload();
      // 현재 창(팝업) 닫기
      window.close();
      // 부모 창에서 "/admin_member" 페이지로 이동
      window.opener.location.href = "/admin_menu";
    }) // 실제 데이터
    .catch((error) => {
      console.log(error);
    });
}

// 아이템을 삭제하는 함수
function func_menu_delete(itemNo) {
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
  fetch("/menuDeleteAction", {
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

////////////
//공지사항//
//업데이트//
////////////

//공지사항 수정 새창 띄우는 함수
function func_notice_ed(noticeNo) {
  window.open(
    "/admin_notice_ed?noticeNo=" + noticeNo,
    "_blank",
    "width=1135, height=390"
  );
}

// 버튼 클릭 시 숨겨진 파일 입력란을 클릭하는 함수
function notice_Update_onClickUpload() {
  let inputNoticeImageUrl = document.getElementById("inputNoticeImageUrl");
  inputNoticeImageUrl.click();
}

// 파일이 선택되면 선택한 이미지 미리보기를 표시하는 함수
function notice_Update_readURL(input) {
  if (input.files && input.files[0]) {
    var reader = new FileReader();
    reader.onload = function (e) {
      document.getElementById("imgNoticeImageUrl").src = e.target.result;
    };
    reader.readAsDataURL(input.files[0]);
  } else {
    document.getElementById("imgNoticeImageUrl").src = "";
  }

  let inputItemImageUrl = document.getElementById("inputNoticeImageUrl");
  console.log("input:file value:" + inputNoticeImageUrl.value);
  console.log("files:" + inputNoticeImageUrl.files[0]);
}

// 메뉴 업데이트 작업이 트리거될 때 호출되는 함수
function func_notice_updateAction() {
  notice_update_image_upload(); // image_upload 함수 호출
}



// fetch API를 사용하여 이미지 업로드를 처리하는 함수
function notice_update_image_upload() {
  let inputNoticeImageUrl = document.getElementById("inputNoticeImageUrl");

  // 파일이 선택되었는지 확인
  if (inputNoticeImageUrl.files && inputNoticeImageUrl.files.length > 0) {
    let fileUrl = inputNoticeImageUrl.value; // 파일 경로 가져오기
    let index = fileUrl.lastIndexOf("\\");
    let fileName = fileUrl.substr(index + 1); // 경로에서 파일 이름 추출

    // 파일을 multipart/form-data 요청으로 보내기 위한 FormData 객체 생성
    let form = new FormData();
    form.append("file", inputNoticeImageUrl.files[0], fileName);

    // "/upload" 엔드포인트로 POST 요청 보내기
    fetch("/notice_Update_upload", {
      method: "POST",
      headers: {},
      body: form, // 요청 본문에 FormData 객체 포함
    })
      .then((response) => {
        return response.json(); // JSON 응답 파싱
      })
      .then((json) => {
        console.log("json:" + JSON.stringify(json));
        console.log("uploadFileName:" + json.uploadFileName);

        func_notice_updateAction_json(json.uploadFileName); // 얻은 uploadFileName을 사용하여 함수 호출
      })
      .catch((error) => {
        console.log(error); // fetch 요청 중에 발생한 오류 기록
      });
  } else {
    // 파일이 선택되지 않았을 때 실행할 코드
    console.log("파일이 선택되지 않았습니다.");

    // 파일이 선택되지 않았을 때
    func_notice_updateAction_none();
  }
}
function func_notice_updateAction_none(){
 // 입력 요소들의 값을 가져오기
  const inputNoticeNo = document.getElementById("inputNoticeNo").value;
  const inputNoticeTitle = document.getElementById("inputNoticeTitle").value;
  const inputNoticeDatetime = document.getElementById(
    "inputNoticeDatetime"
  ).value;
  const noticeImageUrl = document.getElementById("imgNoticeImageUrl").src;
  const inputNoticeType = document.getElementById("inputNoticeType").value;
  const inputNoticeContent =
    document.getElementById("inputNoticeContent").value;

  // 서버에 전송할 파라미터 객체 생성
  // 이때 MemberEdDto에 들어가있는 변수랑 이름이 같아야한다.
  let params = {
    noticeNo: inputNoticeNo,
    noticeType: inputNoticeType,
    noticeTitle: inputNoticeTitle,
    noticeContent: inputNoticeContent,
    noticeImageUrl: noticeImageUrl,
    noticeDatetime: inputNoticeDatetime,
  };

  // 서버로 POST 요청 보내기
  fetch("/noticeUpdateAction_none", {
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
      // 부모 창 새로고침
      window.opener.location.reload();
      // 현재 창(팝업) 닫기
      window.close();
      // 부모 창에서 "/admin_member" 페이지로 이동
      window.opener.location.href = "/admin_notice";
    }) // 실제 데이터
    .catch((error) => {
      console.log(error);
    });
}
// JSON 형식의 아이템 이미지 URL을 받아와서 관련된 폼 데이터를 서버로 전송하는 함수
function func_notice_updateAction_json(noticeImageUrl) {
  // 입력 요소들의 값을 가져오기
  const inputNoticeNo = document.getElementById("inputNoticeNo").value;
  const inputNoticeTitle = document.getElementById("inputNoticeTitle").value;
  const inputNoticeDatetime = document.getElementById(
    "inputNoticeDatetime"
  ).value;
  const inputNoticeType = document.getElementById("inputNoticeType").value;
  const inputNoticeContent =
    document.getElementById("inputNoticeContent").value;

  // 서버에 전송할 파라미터 객체 생성
  // 이때 MemberEdDto에 들어가있는 변수랑 이름이 같아야한다.
  let params = {
    noticeNo: inputNoticeNo,
    noticeType: inputNoticeType,
    noticeTitle: inputNoticeTitle,
    noticeContent: inputNoticeContent,
    noticeImageUrl: noticeImageUrl,
    noticeDatetime: inputNoticeDatetime,
  };

  // 서버로 POST 요청 보내기
  fetch("/noticeUpdateAction", {
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
      // 부모 창 새로고침
      window.opener.location.reload();
      // 현재 창(팝업) 닫기
      window.close();
      // 부모 창에서 "/admin_member" 페이지로 이동
      window.opener.location.href = "/admin_notice";
    }) // 실제 데이터
    .catch((error) => {
      console.log(error);
    });
}

////////////////////
//////공지사항//////
//////추가하기//////
////////////////////

// 공지사항 추가하기를 새창으로 여는 함수
function func_admin_notice_add() {
  window.open("/admin_notice_add", "_blank", "width=1135,height=450");
}

// 버튼 클릭 시 숨겨진 파일 입력란을 클릭하는 함수
function notice_Add_onClickUpload() {
  let inputNoticeImageUrl = document.getElementById("inputNoticeImageUrl");
  inputNoticeImageUrl.click();
}

// 파일이 선택되면 선택한 이미지 미리보기를 표시하는 함수
function notice_Add_readURL(input) {
  if (input.files && input.files[0]) {
    var reader = new FileReader();
    reader.onload = function (e) {
      document.getElementById("imgNoticeImageUrl").src = e.target.result;
    };
    reader.readAsDataURL(input.files[0]);
  } else {
    document.getElementById("imgNoticeImageUrl").src = "";
  }

  let inputItemImageUrl = document.getElementById("inputNoticeImageUrl");
  console.log("input:file value:" + inputNoticeImageUrl.value);
  console.log("files:" + inputNoticeImageUrl.files[0]);
}

// 메뉴 업데이트 작업이 트리거될 때 호출되는 함수
function func_notice_AddAction() {
  notice_Add_image_upload(); // image_upload 함수 호출
}

// fetch API를 사용하여 이미지 업로드를 처리하는 함수
function notice_Add_image_upload() {
  let inputNoticeImageUrl = document.getElementById("inputNoticeImageUrl");
  console.log(inputNoticeImageUrl);

  let fileUrl = inputNoticeImageUrl.value; // 파일 경로 가져오기
  console.log(fileUrl);
  let index = fileUrl.lastIndexOf("\\");
  let fileName = fileUrl.substr(index + 1); // 경로에서 파일 이름 추출
  console.log("fileName:" + fileName);

  // 파일을 multipart/form-data 요청으로 보내기 위한 FormData 객체 생성
  let form = new FormData();
  form.enctype = "multipart/form-data";
  form.append("file", inputNoticeImageUrl.files[0], fileName);

  // "/upload" 엔드포인트로 POST 요청 보내기
  fetch("/notice_Add_upload", {
    method: "POST",
    headers: {},
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

      func_notice_AddAction_json(json.uploadFileName); // 얻은 uploadFileName을 사용하여 함수 호출
    })
    .catch((error) => {
      console.log(error); // fetch 요청 중에 발생한 오류 기록
    });
}
// JSON 형식의 아이템 이미지 URL을 받아와서 관련된 폼 데이터를 서버로 전송하는 함수
function func_notice_AddAction_json(noticeImageUrl) {
  // 입력 요소들의 값을 가져오기
  var noticeType = document.getElementById("inputNoticeType");
  const inputNoticeType = noticeType.options[noticeType.selectedIndex].value;

  const inputNoticeTitle = document.getElementById("inputNoticeTitle").value;
  const inputNoticeContent =
    document.getElementById("inputNoticeContent").value;

  // 서버에 전송할 파라미터 객체 생성
  // 이때 MemberEdDto에 들어가있는 변수랑 이름이 같아야한다.
  let params = {
    noticeType: inputNoticeType,
    noticeTitle: inputNoticeTitle,
    noticeContent: inputNoticeContent,
    noticeImageUrl: noticeImageUrl,
  };

  // 서버로 POST 요청 보내기
  fetch("/noticeAddAction", {
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
      // 부모 창 새로고침
      window.opener.location.reload();
      // 현재 창(팝업) 닫기
      window.close();
      // 부모 창에서 "/admin_member" 페이지로 이동
      window.opener.location.href = "/admin_notice";
    }) // 실제 데이터
    .catch((error) => {
      console.log(error);
    });
}
