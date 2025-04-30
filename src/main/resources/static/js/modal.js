const pwInput = document.getElementById('pw');
const pwCheckInput = document.getElementById('pwCheck');
const pwMatchInput = document.getElementById('pwMatch'); // 결과를 표시할 필드

function checkPasswordMatch() {
    if (pwInput.value !== pwCheckInput.value) {
        pwMatchInput.value = '비밀번호가 일치하지 않습니다.';
        pwMatchInput.style.color = 'red';
        $('button[type="submit"]').attr('disabled', true);
    } else {
        pwMatchInput.value = '비밀번호가 일치합니다.';
        pwMatchInput.style.color = 'green';
        $('button[type="submit"]').removeAttr('disabled')
    }
}

// 비밀번호 입력 필드와 확인 필드에 이벤트 리스너 추가
pwInput.addEventListener('input', checkPasswordMatch);
pwCheckInput.addEventListener('input', checkPasswordMatch);

document.addEventListener("DOMContentLoaded", function () {
    const deleteButton = document.querySelector('a[href="userDelete"]');
    const modal = document.getElementById('custom-modal');
    const confirmButton = document.getElementById('modal-confirm');
    const cancelButton = document.getElementById('modal-cancel');

    // 회원 탈퇴 버튼 클릭 시 모달 표시
    deleteButton.addEventListener('click', function (event) {
        event.preventDefault();
        modal.style.display = 'flex'; // 모달 표시
    });

    // 확인 버튼 클릭 시 회원 탈퇴 처리
    confirmButton.addEventListener('click', function () {
        window.location.href = "/userDelete"; // 회원 탈퇴 URL로 이동
    });

    // 취소 버튼 클릭 시 모달 닫기
    cancelButton.addEventListener('click', function () {
        modal.style.display = 'none'; // 모달 숨기기
    });
});
