# myCoordinator Quest
```text
무신사는 다음 8개의 카테고리에서 상품을 하나씩 구매하여, 코디를 완성하는 서비스를 준비중입니다. 
1. 상의
2. 아우터 
3. 바지
4. 스니커즈 
5. 가방
6. 모자
7. 양말
8. 액세서리
단, 구매 가격 외의 추가적인 비용(예, 배송비 등)은 고려하지 않고, 
브랜드의 카테고리에는 1개의 상품은 존재하고, 
구매를 고려하는 모든 쇼핑몰에 상품 품절은 없다고 가정합니다.

개발 사항
1. 고객은 카테고리 별로 최저가격인 브랜드와 가격을 조회하고 총액이 얼마인지 확인할 수 있어야 합니다.
2. 고객은 단일 브랜드로 전체 카테고리 상품을 구매할 경우 최저가격인 브랜드와 총액이 얼마인지 확인할 수 있어야 합니다.
3. 고객은 특정 카테고리에서 최저가격 브랜드와 최고가격 브랜드를 확인하고 각 브랜드 상품의 가격을 확인할 수 있어야 합니다.
4. 운영자는 새로운 브랜드를 등록하고, 모든 브랜드의 상품을 추가, 변경, 삭제할 수 있어야 합니다. 이 4가지 기능을 사용할 수 있게 하는 4개의 API를 구현해야 합니다.
```

1. 구현범위에 대한 설명
   1. 카테고리 별로 최저가인 브랜드를 조회하고 최저 금액들의  합을 구합니다.
      1. 최저가는 중복을 허용합니다.
   2. 테이블은 브랜드, 상품 테이블이 존재합니다.
      1. BrandEntity, ProductEntity 를 참고합니다. 
   3. 


2. 코드 빌드, 테스트, 실행 방법
   1. port : 8081
   2. 스웨거를 통한 테스트
      1. http://localhost:8081/swagger-ui/index.html
      2. 스웨거의 각 api 스키마에 정의된 내용을 참고 
   3. myCoordinator/src/test/java/com/musinsa/mycoordinator/service 에 테스트코드를 실행
      1. 모든 케이스에 대한 테스트코드는 미작성(몇개의 케이스만 작성함)


3. 기타추가정보
   1. 맥에서 개발된 소스를 윈도우에서 실행하니 스웨거 한글이 깨져서 인코딩추가