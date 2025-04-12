
document.addEventListener("DOMContentLoaded", () => {
    const buttons = document.querySelectorAll(".preference-button");
    const hiddenInput = document.getElementById("selectedPreferences");
    const selectedValues = new Set(); // 선택된 취향을 저장할 Set

    buttons.forEach(button => {
        button.addEventListener("click", () => {
            const value = button.getAttribute("data-value");

            // 선택된 버튼 토글
            if (button.classList.contains("selected")) {
                button.classList.remove("selected");
                selectedValues.delete(value); // 선택 해제
            } else {
                button.classList.add("selected");
                selectedValues.add(value); // 선택 추가
            }

            // Set을 배열로 변환 후, 쉼표로 구분된 문자열로 저장
            hiddenInput.value = Array.from(selectedValues).join(",");
        });
    });
});
document.addEventListener("DOMContentLoaded", () => {
    const slides = document.querySelector('.slides');
    const prevButton = document.querySelector('.prev-slide');
    const nextButton = document.querySelector('.next-slide');

    if (!slides) {
        console.error("슬라이드 요소를 찾을 수 없습니다.");
        return;
    }

    let currentIndex = 0;
    const totalSlides = slides.children.length;
    console.log("슬라이드 요소:", slides);
    console.log("슬라이드 개수:", totalSlides);

    // 슬라이드 이동 함수
    function updateSlidePosition() {
        slides.style.transform = `translateX(-${currentIndex * 100}%)`;
    }

    // 다음 슬라이드로 이동
    function showNextSlide() {
        currentIndex = (currentIndex + 1) % totalSlides; // 순환 이동
        updateSlidePosition();
    }

    // 이전 슬라이드로 이동
    function showPrevSlide() {
        currentIndex = (currentIndex - 1 + totalSlides) % totalSlides; // 순환 이동
        updateSlidePosition();
    }

    // 버튼 클릭 이벤트
    if (prevButton) {
        prevButton.addEventListener('click', showPrevSlide);
    }

    if (nextButton) {
        nextButton.addEventListener('click', showNextSlide);
    }

    // 자동 슬라이드 (3초마다)
    setInterval(showNextSlide, 3000);
});