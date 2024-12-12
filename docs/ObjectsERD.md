# ObjectsERD

> Written in Mermaid.js syntax  
> https://mermaid.js.org/syntax/flowchart.html

- vscode에서 다음 확장프로그램을 설치
  - Mermaid Markdown Syntax Highlighting : 마크다운 텍스트 문서에서 신택스 린팅
  - Mermaid Graphical Editor : 마크다운 코드블럭을 mermaid로 지정하면 상단에 GUI 에디터로 이동할 수 있는 "MermaidEditor" 표시

## Users, Products, Transactions
```mermaid
erDiagram
    USERS {
        bigint id PK "사용자 ID"
        varchar name "사용자 이름"
        varchar introduction "사용자 소개글"
        boolean isActive "사용자 활성화 여부"
        varchar createProgramId "사용자 등록 프로그램 ID"
        datetime createdDateTime "사용자 등록일시"
        varchar modifyProgramId "사용자 수정 프로그램 ID"
        datetime modifiedDateTime "사용자 수정일시"
        varchar deleteProgramId "사용자 삭제 프로그램 ID"
        datetime deletedDateTime "사용자 삭제일시"
    }

    PRODUCTS {
        bigint id PK "상품 ID"
        varchar name "상품 이름"
        int price "상품 가격"
        varchar category "상품 카테고리"
        varchar description "상품 설명"
        boolean isPrivate "상품 비공개 여부"
        boolean isSoldOut "상품 판매 여부"
        boolean isDeleted "상품 삭제 여부"
        bigint createUserId FK "상품 등록 사용자 ID"
        datetime createdDateTime "상품 등록일시"
        bigint modifyUserId FK "상품 수정 사용자 ID"
        datetime modifiedDateTime "상품 수정일시"
        bigint deleteUserId FK "상품 삭제 사용자 ID"
        datetime deletedDateTime "상품 삭제일시"
    }

    TRANSACTIONS {
        bigint id PK "거래 ID"
        bigint productId FK "거래 상품 ID"
        bigint buyerId FK "구매자 ID"
        bigint sellerId FK "판매자 ID"
        datetime transactionDateTime "거래 완료 일시"
        int transactionAmount "거래 금액"
        boolean isOpen "거래 상태"
        boolean isDeleted "거래 삭제 여부"
        bigint createdUserId FK "거래 등록 사용자 ID"
        datetime createdDateTime "거래 등록일시"
        bigint modifiedUserId FK "거래 수정 사용자 ID"
        datetime modifiedDateTime "거래 수정일시"
        bigint deletedUserId FK "거래 삭제 사용자 ID"
        datetime deletedDateTime "거래 삭제일시"
    }

    USERS ||--o{ PRODUCTS : "createUserId, modifyUserId, deleteUserId"
    USERS ||--o{ TRANSACTIONS : "buyerId, sellerId, createdUserId, modifiedUserId, deletedUserId"
    PRODUCTS ||--o{ TRANSACTIONS : "productId"
```