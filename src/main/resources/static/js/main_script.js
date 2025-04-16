
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
  const buttons = document.querySelectorAll(".preference-button2");
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
  const buttons = document.querySelectorAll(".preference-toggle");

  buttons.forEach((button) => {
    button.addEventListener("click", () => {
      // 클릭된 버튼이 이미 'selected' 클래스를 가지고 있는지 확인
      if (button.classList.contains("selected")) {
        button.classList.remove("selected"); // 'selected' 클래스 제거
      } else {
        // 모든 버튼에서 'selected' 클래스 제거
        buttons.forEach((btn) => btn.classList.remove("selected"));

        // 클릭된 버튼에 'selected' 클래스 추가
        button.classList.add("selected");
      }
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

// top button 
document.addEventListener("DOMContentLoaded", () => {
    const topButton = document.getElementById("topButton");
    const mobileFrame = document.querySelector(".mobile-frame"); // .mobile-frame 요소 선택
  
    // 스크롤 이벤트
    mobileFrame.addEventListener("scroll", () => {
      if (mobileFrame.scrollTop > 200) {
        topButton.style.display = "flex"; // 버튼 표시
      } else {
        topButton.style.display = "none"; // 버튼 숨김
      }
    });
  
    // 버튼 클릭 이벤트
    topButton.addEventListener("click", () => {
      mobileFrame.scrollTo({
        top: 0,
        behavior: "smooth", // 부드럽게 스크롤
      });
    });
  });

  document.addEventListener("DOMContentLoaded", () => {
    // 모든 찜 버튼을 선택
    const wishButtons = document.querySelectorAll(".wish-button");
  
    wishButtons.forEach((button) => {
      const heartIcon = button.querySelector(".heart-icon");
  
      // 클릭 이벤트 추가
      button.addEventListener("click", () => {
        // 현재 이미지가 like.png인지 heart.png인지 확인
        if (heartIcon.src.includes("like.png")) {
          heartIcon.src = "/images/hearts/heart.png"; // heart.png로 변경
        } else {
          heartIcon.src = "/images/hearts/like.png"; // like.png로 변경
        }
      });
    });
  });




