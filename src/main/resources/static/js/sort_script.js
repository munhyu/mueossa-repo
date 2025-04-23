document.addEventListener("DOMContentLoaded", function () {
  const sortSelect = document.getElementById("sortBy");
  const productList = document.querySelector(".product-list");

  if (!sortSelect || !productList) return;

  // 최초 렌더링된 상품 카드 순서를 저장
  const originalCards = Array.from(
    productList.querySelectorAll(".product-card")
  );

  sortSelect.addEventListener("change", function () {
    const cards = Array.from(productList.querySelectorAll(".product-card"));

    function getPrice(card) {
      const priceSpan = card.querySelector(".product-price");
      if (!priceSpan) return 0;
      return parseInt(priceSpan.textContent.replace(/[^0-9]/g, ""), 10) || 0;
    }

    let sorted;
    if (sortSelect.value === "price_asc") {
      sorted = cards.sort((a, b) => getPrice(a) - getPrice(b));
    } else if (sortSelect.value === "price_desc") {
      sorted = cards.sort((a, b) => getPrice(b) - getPrice(a));
    } else {
      // 추천순 선택 시, 원래 순서로 복원
      productList.innerHTML = "";
      originalCards.forEach((card) => productList.appendChild(card));
      return;
    }

    productList.innerHTML = "";
    sorted.forEach((card) => productList.appendChild(card));
  });

  // // 기본값 설정 (HTML에서 selected 속성으로도 가능)
  // sortSelect.value = 'recommend_desc';
});
