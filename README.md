# paper-api

[![Kotlin](https://img.shields.io/badge/java-16.0.2-ED8B00.svg?logo=java)](https://www.azul.com/)
[![Kotlin](https://img.shields.io/badge/kotlin-1.5.30-585DEF.svg?logo=kotlin)](http://kotlinlang.org)
[![Gradle](https://img.shields.io/badge/gradle-7.2-02303A.svg?logo=gradle)](https://gradle.org)
[![GitHub](https://img.shields.io/github/license/monun/tap)](https://www.gnu.org/licenses/gpl-3.0.html)
[![Kotlin](https://img.shields.io/badge/youtube-각별-red.svg?logo=youtube)](https://www.youtube.com/channel/UCDrAR1OWC2MD4s0JLetN0MA)

[PaperMC API](https://papermc.io/api/docs) 를 스크립트 내에서 사용하기 쉽게 만든 작은 모듈

---

#### version 정보 가져오기 (버전의 빌드 목록)

```shell
java -jar paper-api.jar -r version -v <version>
```

<br>

#### build 정보 가져오기 (빌드의 정보)

```shell
java -jar paper-api.jar -r build -v <version> -b <build>
```

<br>

#### download 정보 가져오기 (다운로드 url)

```shell
java -jar paper-api.jar -r download -v <version> -b <build>
```

<br>

build는 `latest`를 입력하여 마지막 빌드로 지정할 수 있습니다. 