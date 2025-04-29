document.addEventListener("DOMContentLoaded", () => {
  // 모든 찜 버튼을 선택
  const wishButtons = document.querySelectorAll(".wish-button");

  wishButtons.forEach((button) => {
    const heartIcon = button.querySelector(".heart-icon");
    // 상품 카드 요소 찾기
    const productCard = button.closest(".product-card");
    // 상품 ID 가져오기
    const productId = productCard ? productCard.getAttribute("data-pid") : null;

    // 클릭 이벤트 추가
    button.addEventListener("click", (event) => {
      event.preventDefault(); // a 태그 링크 이동 방지
      event.stopPropagation(); // 이벤트 버블링 방지

      if (!productId) {
        // console.error("상품 ID를 찾을 수 없습니다.");
        return;
      }

      // 서버로 찜 토글 요청 보내기
      fetch("/toggleWishlist", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          // CSRF 토큰이 필요하다면 헤더에 추가
        },
        body: JSON.stringify({ pId: productId }),
      })
        .then((response) => {
          if (!response.ok) {
            throw new Error(
              "Network response was not ok " + response.statusText
            );
          }
          return response.json();
        })
        .then((data) => {
          if (data.success) {
            // 응답에 따라 아이콘 변경
            if (data.added) {
              heartIcon.src = "/images/hearts/heart.png"; // 찜 추가됨
            } else {
              heartIcon.src = "/images/hearts/like.png"; // 찜 제거됨
            }
          } else {
            alert(data.message || "찜 처리에 실패했습니다.");
          }
        })
        .catch((error) => {
          // console.error("Error:", error);
          alert("오류가 발생했습니다. 다시 시도해주세요.");
        });
    });
  });
});
