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

// 파일올리고 그 이미지를 화면에 띄우기
 function noticeDtoToJson(noticeDto) {
        return {
            noticeNo: noticeDto.noticeNo,
            noticeTitle: noticeDto.noticeTitle,
            noticeContent: noticeDto.noticeContent,
            noticeImageUrl: noticeDto.noticeImageUrl,
            noticeDatetime: noticeDto.noticeDatetime,
            noticeType: noticeDto.noticeType
        };
    }

    function fileUpLoad() {
        // 파일 입력 엘리먼트 가져오기
        var fileInput = document.getElementById('noticeImageUrl');
        // 이미지 엘리먼트 가져오기
        var imageElement = document.getElementById('uploadImage');

        // 오류처리
        if (fileInput.files.length > 0) {
            // 선택된 파일의 URL을 읽어와서 이미지 엘리먼트의 src 속성에 설정
            var fileReader = new FileReader();
            fileReader.onload = function (e) {
                imageElement.src = e.target.result;
            };
            fileReader.readAsDataURL(fileInput.files[0]);
        } else {
            // 파일이 선택되지 않았을 때 기존 이미지로 복원하거나 다른 처리를 수행
            // 예: imageElement.src = "기존 이미지 URL";
            console.error("No file selected");
        }

        // src 가 잘 셋팅되나 확인
        const upload = imageElement.src;
        console.log(upload);
    }

    function func_notice_updateAction() {
        // 변수 이름 수정 및 새로운 변수 추가
        var uploadimg = document.querySelector(".uploadImage").src;
        var inputNoticeNo = document.getElementById("noticeNo").value;
        var inputNoticeTitle = document.getElementById("noticeTitle").value;
        var inputNoticeContent = document.getElementById("noticeContent").value;
        var inputDatetime = document.getElementById("noticeDatetime").value;
        var inputType = document.getElementById("noticeType").value;

        // NoticeEdDto 객체 생성
        var noticeDto = {
            noticeNo: inputNoticeNo,
            noticeTitle: inputNoticeTitle,
            noticeContent: inputNoticeContent,
            noticeImageUrl: uploadimg,
            noticeDatetime: inputDatetime,
            noticeType: inputType
        };

        // NoticeEdDto 객체를 JSON 형식으로 변환
        var params = noticeDtoToJson(noticeDto);

        fetch("/noticeUpdateAction", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(params),
        })
            .then((response) => response.json())
            .then((json) => {
                console.log("json: ", json);
                window.location.href = "/admin_notice";
            })
            .catch((error) => {
                console.log(error);
            });
    }


