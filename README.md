# mueossa 👟

**무엇사: 상품 리뷰 데이터 분석 기반 신발 추천 서비스**

> "어떤 신발이 나에게 가장 잘 맞을까?"

수많은 신발 리뷰 속에서 진짜 나에게 필요한 정보를 찾아 헤맨 경험, 다들 있으시죠? '무엇사'는 실제 구매자들의 생생한 리뷰를 감성 분석 기술로 분석하여, 각 신발의 특징과 만족감을 **감성 점수**로 보여줍니다. 이를 통해 여러분의 취향과 중요하게 생각하는 가치(편안함, 디자인, 가격 등)에 맞는 신발을 쉽고 빠르게 찾을 수 있도록 돕는 서비스입니다.

## ✨ 프로젝트 개요

* **프로젝트 기간:** 2025.03.25 ~ 2025.05.09
* **배경:**
    * 사용자가 신발을 구매할 때 가장 우선시하는 요소는 착화감, 디자인, 가격, 리뷰 신뢰도이지만, 제품 설명만 보고 파악이 힘듦
    * 온라인 쇼핑, 특히 신발 구매 시 실사용 리뷰의 중요성 증대
    * 방대한 리뷰 속에서 유의미한 정보 파악의 어려움
    * 단순 긍/부정 분류를 넘어선 정량적 감성 분석의 필요성
    
* **목표:**
    * 리뷰 텍스트를 감성 점수(0~100점)로 정량화하여 제공
    * 사용자 취향과 상품별 감성 점수를 기반으로 맞춤형 신발 추천
    * 상품별 키워드(착화감, 디자인, 가격)를 도출해 키워드 기반 제품 분류 및 추천
    * 직관적인 UI/UX를 통해 신발 탐색 및 구매 결정 과정의 편의성 증대

## 🚀 주요 기능

* **상품 추천 및 탐색:**
    * **감성 점수 기반 상품 목록:** 각 신발의 리뷰 텍스트를 분석한 감성 점수를 기반으로한 추천 목록
    * **맞춤 필터링:** 원하는 키워드(예: 편한, 가벼운) 및 신발 종류 선택하여 상품 조회
    * **정렬 기능:** 무엇사 추천순(감성 점수), 낮은 가격순, 높은 가격순 정렬
    * **상품 검색:** 상품명, 브랜드, 종류로 원하는 상품 검색
    * **상세 정보 확인:**
        * 상품 기본 정보 (이미지, 브랜드, 가격, 할인율, 구매 링크 등)
        * **키워드별 감성 점수:** 편안함, 디자인 등 세부 항목별 점수 확인
        * **유사 상품 추천:** 현재 상품과 비슷한 카테고리별 감성 점수를 가진 다른 상품 보기
* **회원 기능:**
    * 회원가입 및 로그인 (이메일 인증, ID/PW 찾기)
    * 개인정보 수정 및 회원 탈퇴
    * 개인 취향 키워드 선택 및 관리 (마이페이지)
    * 찜 목록 관리 (관심 상품 저장/삭제)
    * 문의 게시판 이용 (작성/수정/삭제)
* **비회원:**
    * 상품 목록 조회, 검색, 필터링, 정렬 기능 이용 가능
    * 세션 기반 찜 목록 이용 가능 (브라우저 종료 시 초기화)
* **관리자 기능:**
    * 모든 사용자 문의 내역 관리 (조회/수정/삭제)
 
## 🙍‍♂️ 페르소나

### **이ㅇㅇ**(27세, 남성)

* **주요 특징:**
   * #힙하고편안한 #온라인쇼핑즐겨 #브랜드신발선호 #실착리뷰중요
   * 가격 대비 성능과 디자인을 중시하는 합리적인 소비자.
   * 쇼핑에 많은 시간을 들이기 어려워함.

* **니즈:**
   * 실제 착용 리뷰를 통해 사이즈, 핏, 착용감을 정확히 파악.
     * 다양한 브랜드 신발의 스타일 비교 정보 획득.
   * 효율적인 온라인 신발 쇼핑 경험.

* **Pain Point:**
   * 온라인 정보만으로 실제 착용감 및 디테일 파악의 어려움.
   * 리뷰 탐색에 많은 시간 소요.
## 🛠️ 기술 스택

| 구분 | 기술 스택 |
|---|---|
| **Frontend** | HTML, CSS, JavaScript, jQuery |
| **Backend** | Java, Spring Boot, Spring Data JPA, Thymeleaf, Tomcat |
| **Database** | MySQL |
| **Data Analysis & Modeling** | Python, Pandas, Numpy, Scikit-learn, Selenium, BeautifulSoup, Jupyter Notebook, Transformers (Hugging Face), PyTorch, Tensorflow |
| **NLP 모델** | Helsinki-NLP/opus-mt-ko-en (번역), cardiffnlp/twitter-roberta-base-sentiment-latest (감성 분석) |
| **Tools** | Git, GitHub, Figma |

## ⚙️ 시스템 아키텍처

![image](https://github.com/user-attachments/assets/7587244f-881d-4597-8a49-dc32d422c842)


## 📊 유스케이스 다이어그램

![image](https://github.com/user-attachments/assets/05d09f1b-1e9b-480b-8903-40dda9cba768)


## 🗃️ ER 다이어그램

![mueossa_20250430_102747](https://github.com/user-attachments/assets/9a21b75c-d623-43b8-b7e3-f2e05b942054)

## 🖥️ 화면 구성

* **메인 페이지:** 상품 정보, 감성 점수, 별점, 할인률, 가격, 키워드 등을 확인할 수 있음.
    ![메인 페이지 화면](https://github.com/user-attachments/assets/4392fddc-16ad-4a0a-882a-04a904a601a4)

* **카테고리 페이지:** 키워드 및 상품 종류 별 선택을 통한 상품 나열 시스템 제공. 
    ![카테고리 페이지 화면](https://github.com/user-attachments/assets/2c893a66-d872-4515-8099-a77325fdf45a)
    ![카테고리 페이지2 화면](https://github.com/user-attachments/assets/ba9e3bb7-054e-4867-b1f5-279290b69564)

* **상품 상세 페이지:** 상품 정보, 감성 점수, 유사 상품, 구매 링크
    ![상품 상세 페이지1 화면](https://github.com/user-attachments/assets/6520672f-624d-4c7f-95bf-edc762fee3dd)
    ![상품 상세 페이지2 화면](https://github.com/user-attachments/assets/1c1afc91-bb13-457a-9eab-1eac6423f9aa)
    ![상품 상세 페이지3 화면](https://github.com/user-attachments/assets/6c6ddc8c-40be-40e6-8ec7-9bdc0288c95b)

* **찜 목록 페이지:** 회원 정보 수정, 취향 관리, 찜 목록, 문의 게시판
    ![찜목록 페이지 화면](https://github.com/user-attachments/assets/eedb18a8-bf1f-44f7-a39d-199bb93426a4)

## 💾 데이터 분석 & 모델링

* **데이터 출처:** 무신사 ([https://www.musinsa.com/](https://www.musinsa.com/)) 웹사이트의 상품 리뷰
* **수집 방법:** Python의 `Selenium` 및 `BeautifulSoup` 라이브러리를 이용한 웹 크롤링
* **데이터 종류:**
    * 신발 상품 정보 (상품명, 브랜드, 가격, 할인율, 이미지 URL, 상품 링크 등)
    * 사용자 리뷰 텍스트
* **데이터 규모:**
    * 상품 수: 633개 (스니커즈, 샌들/슬리퍼, 구두, 부츠/워커 포함)
    * 리뷰 수: 약 315,753개 (상품당 150개 이상 리뷰 보유 상품 기준)
* **데이터 처리:**
    * 텍스트 정제 (공백, 특수문자, 이모지 제거)
    * 형태소 분석 (Okt) 및 불용어 처리
    * TF-IDF 기반 키워드 분석을 통한 추천 카테고리 정의 및 키워드별 점수 산출
    * 리뷰 번역 (한국어 → 영어) 및 감성 점수 산출 (0~100)

### 1. TF-IDF 기반 키워드 분석 및 키워드별 점수 산출

* 신발 리뷰 데이터를 형태소 단위로 분리
* 각 형태소에 대해 TF-IDF (Term Frequency-Inverse Document Frequency) 점수를 산출하여 리뷰에서 중요한 키워드를 추출
* 추출된 키워드들을 의미적 유사성 및 실제 리뷰에서의 사용 맥락을 고려하여 다음과 같은 추천 카테고리로 군집화하고, 각 키워드 그룹에 해당하는 형태소들을 정의
    * **편한:** `편하다`, `편안하다`
    * **푹신한:** `푹신하다`
    * **가벼운:** `가볍다`
    * **부드러운:** `부드럽다`
    * **딱딱한:** `딱딱하다`
    * **예쁜:** `이쁘다`, `예쁘다`
    * **귀여운:** `귀엽다`
    * **깔끔한:** `깔끔하다`
    * **모던한:** `세련되다`, `멋지다`, `고급스럽다`
    * **휘뚜루마뚜루:** `데일`, `자주`, `매일`, `코디`, `활용`, `어울리다`, `여기저기`, `포인트`, `무난`, `휘뚜루마뚜루`
    * **발볼이 넓은:** `크다`, `다운`, `넉넉하다`, `넓다`
    * **발볼이 보통:** `정사`, `잘맞다`, `딱이다`, `맞다`, `정사이즈`
    * **발볼이 좁은:** `업`, `작다`, `좁다`
    * **가격이 착한:** `가성`, `저렴하다`, `싸다`, `할인`, `쿠폰`, `득템`, `대비`, `착한`, `착하다`, `가성비`
    * **튼튼한:** `튼튼하다`, `구성`, `구도`, `마감`, `퀄리티`, `재질`, `오래오래`, `내구성`
* **키워드별 점수 산출:** 각 리뷰를 분석하여 정의된 키워드 그룹에 속하는 형태소들의 출현 빈도를 합산하고, 이를 해당 리뷰의 각 키워드 카테고리별 점수로 활용

### 2. 리뷰 감성 점수 산출

* 리뷰 텍스트를 영어로 번역
    * 사용 모델: Helsinki-NLP/opus-mt-ko-en
* 번역된 리뷰를 영어 감성 분석 모델에 입력하여 각 리뷰의 감성 점수를 예측
    * 사용 모델: cardiffnlp/twitter-roberta-base-sentiment-latest
    * 감성 점수는 긍정 클래스의 softmax 확률 값을 0부터 100 사이의 값으로 변환하여 사용
* 각 상품별 리뷰에 대해 긍정(1) 또는 부정(0) 라벨을 부여
* 상품별 평균 감성 점수 및 평균 긍정 라벨을 산출
![image](https://github.com/user-attachments/assets/b9650cfd-b982-4ef9-8922-d1fdddecee74)



## 💡 트러블슈팅

### 📍 문제 A. 데이터 수집 과정

#### 1. 스크롤 기반 동적 로딩으로 인한 리뷰 누락

- 무신사 리뷰는 스크롤을 내릴 때마다 리뷰가 동적으로 로딩되는 구조
- 한 번에 약 20개의 리뷰만 DOM에 유지되고, 이전 리뷰는 제거됨
- 단순히 계속 스크롤할 경우 이미 로딩된 리뷰가 사라지면서 누락 발생
- 리뷰마다 높이가 달라 정밀한 스크롤 제어가 어려움

#### 2. '더보기' 버튼으로 인한 텍스트 누락

- 일부 리뷰는 본문이 잘려서 표시되며, '더보기' 버튼 클릭 시 전체 텍스트 확인 가능
- '더보기' 클릭 시 리뷰 높이가 증가하면서 기존 리뷰가 다시 DOM에서 사라질 수 있음

### ✅ 해결 방안 A. 리뷰 수집 안정화

#### `data-item-index` 속성을 활용한 스크롤 제어

- 각 리뷰 요소에는 고유한 `data-item-index` 속성이 존재
- 스크롤 후 마지막으로 본 인덱스가 DOM에서 사라질 때까지 반복하여 새 리뷰 로딩 감지
- 최대 반복 횟수를 제한해 무한 루프 방지

#### `Set` 자료구조를 활용한 리뷰 수집 관리

- 수집한 리뷰의 `data-item-index`를 `Set`에 저장하여 중복 방지
- '더보기' 클릭이나 DOM 재배치로 인한 누락 및 중복 수집 문제 해결

### 📍 문제 B. 한국어 감성 분석 성능

#### 1. 한국어 감성 분석 모델의 낮은 신뢰도

- 공개된 한국어 감성 분석 모델들이 정확도와 일관성에서 한계를 보임  
- 특히 쇼핑 리뷰 도메인에서는 뉘앙스 반영이 어려움

### ✅ 해결 방안 B. 영어 기반 감성 분석 파이프라인 구축

#### 1. 영어 감성 분석 모델 사용

- 신뢰도 높은 영어 사전학습 모델  
  → `cardiffnlp/twitter-roberta-base-sentiment-latest` 사용  
- 한국어 리뷰를 영어로 번역한 후 분석하는 파이프라인 구성

#### 2. Helsinki-NLP 번역 모델 활용

- `Helsinki-NLP/opus-mt-ko-en` 모델을 사용해 한국어 → 영어 자동 번역  
- 이후 영어 감성 분석 모델로 처리해 감성 점수(0~100)로 정량화



## 👨‍👩‍👧‍👦 팀원 역할

![팀원소개](https://github.com/user-attachments/assets/63bb4a71-5e85-46c7-a1cc-90ce36c84d47)


## ▶️ 시연 영상

[[시연 영상 보러가기]](https://www.youtube.com/watch?v=3fRKiIi4Q-4)
