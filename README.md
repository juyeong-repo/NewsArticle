# NewsArticle
## 멀티스레드 환경에서 동시성 이슈를 해결하기 위해 진행한 사이드 프로젝트
### AS-IS

권한을 가진 관리자가 한 아티클을 동시에 수정하려 할때, 동시성 문제가 발생

### Challenge
- 충돌이 가끔 일어난다는 가정하에 **Optimistic lock** 사용
  -  **버전 사용으로 정합성을 맞춰 문제 해결**
- 고려한 다른 방법
  - 자바 Syncronized : 하나의 프로세스, 하나의 서버 내에서는 괜찮지만 여러 서버에서 접근한다면 문제해결 어려움
  - mysql lock : 데이터에 락을 걸어 정합성을 맞추는 방법, 데드락 가능성 O
  
## Project Structure
        📦src
        ┣ 📂main
        ┃ ┣ 📂java
        ┃ ┃ ┗ 📂com
        ┃ ┃ ┃ ┗ 📂example
        ┃ ┃ ┃ ┃ ┗ 📂concurrency
        ┃ ┃ ┃ ┃ ┃ ┣ 📂domain
        ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜Article.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜Author.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜Image.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜Status.java
        ┃ ┃ ┃ ┃ ┃ ┣ 📂facade
        ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜OptimisticLockFacade.java
        ┃ ┃ ┃ ┃ ┃ ┣ 📂repository
        ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ArticleRepository.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜AuthorRepository.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ImageRepository.java
        ┃ ┃ ┃ ┃ ┃ ┣ 📂service
        ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ArticleService.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜OptimisticLockService.java
        ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜UserService.java
        ┃ ┃ ┃ ┃ ┃ ┗ NewsArticlelication.java
        ┃ ┗ 📂resources
        ┃ ┃ ┣ 📜application.yml
        ┃ ┃ ┗ 📜log4jdbc.log4j2.properties
        ┗ 📂test
        ┃ ┗ 📂java
        ┃ ┃ ┗ 📂com
        ┃ ┃ ┃ ┗ 📂example
        ┃ ┃ ┃ ┃ ┗ 📂concurrency
        ┃ ┃ ┃ ┃ ┃ ┣ 📂service
        ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ArticleServiceTest.java
        ┃ ┃ ┃ ┃ ┃ ┗ NewsArticlelication.java