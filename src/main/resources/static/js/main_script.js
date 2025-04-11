
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
