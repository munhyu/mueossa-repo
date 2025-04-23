// 키워드 p가 있는 카드는 a태그로 감싸져 있어 preventDefault()를 사용하여 기본 동작을 방지
$(document).on("click", ".keywords p", function (e) {
  e.stopPropagation();
  e.preventDefault();
});

$(function () {
  const pTypeMap = {
    구두: [
      "더비 슈즈",
      "로퍼",
      "모카신",
      "블로퍼",
      "메리제인 슈즈",
      "플랫 슈즈",
      "힐/펌프스",
      "기타 구두",
      "윙 팁",
      "스트레이트 팁",
      "몽크 스트랩",
    ],
    "부츠/워커": [
      "미들/하프 부츠",
      "레인 부츠",
      "워커",
      "앵클/숏 부츠",
      "롱 부츠",
      "기타 부츠",
    ],
    "샌들/슬리퍼": [
      "슬라이드",
      "쪼리/플립플랍",
      "스포츠/캐주얼 샌들",
      "클로그",
      "실내화",
      "기타 슬리퍼",
      "기타 샌들",
      "스트랩 샌들",
      "스트랩 슬리퍼",
    ],
    스니커즈: [
      "패션스니커즈화",
      "캔버스/단화",
      "슬립온",
      "기타 스니커즈",
      "스니커즈 뮬",
    ],
  };

  const $filterInputs = $(".preference-buttons2 input");
  const $productCards = $(".product-card");
  const $pTypeArea = $("#pType-area");
  const $pTypeCheckboxes = $("#pType-checkboxes");
  let selectedKeyword = null;

  function updatePTypeOptions() {
    const $selectedRadio = $('input[type="radio"][name="pGroup"]:checked');
    if ($selectedRadio.length === 0) {
      $pTypeArea.hide();
      $pTypeCheckboxes.empty();
      filterProducts();
      return;
    }
    const group = $selectedRadio.val();
    const types = pTypeMap[group];
    if (!types) {
      $pTypeArea.hide();
      $pTypeCheckboxes.empty();
      filterProducts();
      return;
    }
    // 체크박스 동적 생성
    let html = "";
    $.each(types, function (_, type) {
      html += `<label class="pType-label">
        <input type="checkbox" class="pType-checkbox" name="pType" value="${type}"> ${type}
      </label>`;
    });
    $pTypeCheckboxes.html(html);
    $pTypeArea.show();
    // 새로 생성된 체크박스에도 필터 이벤트 연결
    $pTypeCheckboxes.find("input").on("change", filterProducts);
    filterProducts();
  }

  function filterProducts() {
    const checked = {};

    // 기존 체크박스(감성 등)
    $filterInputs.each(function () {
      if (this.type === "checkbox" && this.checked) {
        checked[this.name.toLowerCase()] = this.value;
      }
    });

    // 라디오(신발 종류)
    $(".preference-toggle").each(function () {
      if ($(this).hasClass("selected")) {
        const radioInput = $("#" + $(this).attr("for"))[0];
        if (radioInput && radioInput.name) {
          checked[radioInput.name.toLowerCase()] = radioInput.value;
        }
      }
    });

    // pType 체크박스(세부 분류)
    const checkedTypes = [];
    $(".pType-checkbox:checked").each(function () {
      checkedTypes.push($(this).val());
    });

    $productCards.each(function () {
      let show = true;
      for (const key in checked) {
        const dataValue = $(this).data(key.toLowerCase());
        if (dataValue != checked[key]) {
          show = false;
          break;
        }
      }
      if (show && checkedTypes.length > 0) {
        const cardType = $(this).data("ptype");
        // inArray() 메서드는 배열에 특정 값이 포함되어 있는지 확인 있으면 해당 값 첫 번째 인덱스 반환, 없으면 -1 반환
        if ($.inArray(cardType, checkedTypes) === -1) {
          show = false;
        }
      }
      // 키워드 필터 추가
      if (show && selectedKeyword) {
        const hasKeyword =
          $(this)
            .find(".keywords p")
            .filter(function () {
              return $(this).text() === selectedKeyword;
            }).length > 0;
        if (!hasKeyword) show = false;
      }
      // 제품 보여주기/숨기기
      $(this).toggle(show);
    });
  }

  // 키워드 클릭 이벤트 (하나만 선택)
  // $(document).on("click", ".keywords p")을 사용하는 이유
  // 상위 고정된 요소에 이벤트 핸들러를 연결하여 메모리 사용량에서 효율적
  // $(.keywords p).on("click", fun(){})는 동적으로 생긴 요소에는 작동하지 않음
  $(document).on("click", ".keywords p", function (e) {
    e.stopPropagation();
    const $this = $(this);
    const text = $this.text();
    if ($this.hasClass("keyword-selected")) {
      // 이미 선택된 키워드 클릭 시 해제
      $(".keywords p").removeClass("keyword-selected");
      selectedKeyword = null;
    } else {
      $(".keywords p").removeClass("keyword-selected");
      $(".keywords p")
        .filter(function () {
          return $(this).text() === text;
        })
        .addClass("keyword-selected");
      selectedKeyword = text;
    }
    filterProducts();
  });

  $filterInputs.on("change", function () {
    if (this.type === "radio" && this.name === "pGroup") {
      updatePTypeOptions();
    } else {
      filterProducts();
    }
  });

  $(".preference-toggle").on("click", function () {
    setTimeout(updatePTypeOptions, 10);
  });

  updatePTypeOptions();
});
