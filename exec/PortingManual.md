# ๐  Porting Manual

> ์ฑํ ๋ฆฌ๋ ์๋์ ๊ฐ์ ํ๊ฒฝ์์ ์๋น์ค์ค์ด๋ฉฐ, ๋ค์๊ณผ ๊ฐ์ ๋ฐฉ๋ฒ์ผ๋ก ์คํํ  ์ ์์ต๋๋ค.

#### โ๏ธ ์์คํ ํ๊ฒฝ

- CPU : `Intel(R) Xeon(R) CPU E5-2686 v4 @ 2.30GHz`
- OS : `Ubuntu 20.04 LTS (GNU/Linux 5.4.0-1018-aws x86_64)`
- Total Memory : `16396056 kB`

#### ๐ฅ ์์คํ ๊ตฌ์ฑ

- `Jenkins 3.7 - blueocean` : CI/CD tool
- `Docker` : Backend/Frontend(`nginx`) ์ปจํ์ด๋ ์คํ
  - Frontend(nginx)์ Backend๋ ๋์ปค๋ฅผ ํตํด ๊ฐ๊ฐ ๋ค๋ฅธ ์ปจํ์ด๋์์ ์คํ๋๊ณ  ์์ต๋๋ค.
- File server : ` AWS S3`
- `nginx` : docker์์ Frontend๋ฅผ ์คํํ๋ฉฐ, Backend๋ฅผ redirecting ํฉ๋๋ค.

#### ๐ป ๊ฐ๋ฐ ํ๊ฒฝ

- Java `1.8`
- MySQL `5.7`
- IntelliJ IDEA ULTIMATE `2020.3`

- Node.js `14.17.0 LTS`

- Visual Studio Code`1.59.0`

#### ๐ฝ ๊ธฐ์  ์คํ

##### โซ๏ธ Back-end

- Gradle
- spring-boot `2.5.3`
- spring-boot-data-jpa
- spring security
- spring-boot-starter-data-redis
- spring-boot-starter-mail
- swagger`2`
- jjwt
- lombok
- junit `5`
- firebase-admin

##### ๐ก Front-end

- Vue.js `2`
- SCSS
- firebase

##### ๐ด Infra

- AWS EC2 - deply server
- AWS S3 - file server
- nginx , docker, jenkins
- gitlab, mattermost

#### โพ DevOps

> ๋ฐฐํฌํ๋ก์ฐ

![devops](devops.png)

#### ๐น Install & Usage

##### ๐ต SSL Key ๋ฐ๊ธ

> ์ด๋ฏธ ๋ฐ๊ธ๋ฐ์์ ์ด ์๋ค๋ฉด ํด๋น ์์์ ๋ฌด์ํด๋ ๋ฌด๊ดํฉ๋๋ค.

```
$ sudo apt-get install letsencrypt
$ sudo letsencrypt certonly --standalone -d <www์ ์ธํ ๋๋ฉ์ธ ์ด๋ฆ>
```

##### ๐  Docker network ์ค์ 

> Frontend, Backend ๊ฐ๊ฐ ์๋ก ๋ค๋ฅธ container์์ ์คํ๋๊ธฐ๋๋ฌธ์ docker network๋ฅผ ์ด์ฉํ ์ฐ๊ฒฐ์ด ํ์ํฉ๋๋ค.

```bash
$ sudo docker network create booktorycicdnetwork
```

##### ๐  Docker image ์์ฑ

> Frontend, Backend ๋ด์ `Dockerfile` ์ ์ด์ฉํ์ฌ ์ด๋ฏธ์ง๋ฅผ ์์ฑํ๋ ๊ณผ์ ์์
> Frontend, Backend ํ๋ก์ ํธ ๊ฐ๊ฐ์ ์คํํ๊ฒฝ๊ณผ build ํ์ผ์ด ์์ฑ๋ฉ๋๋ค.

```bash
/* ๋ค์ ๋ช๋ น์ project root directory์์ ์ํํฉ๋๋ค.*/

$ docker build -t frontend:latest ./frontend/
$ docker build -t backend:latest ./backend/
```

##### ๐  Docker container ์คํ

> Frontend๋ nginx ํ๊ฒฝ์์ ์คํ๋๋ฉฐ, ๋ฐ๋ก ์คํ๋ Backend ์ปจํ์ด๋๋ niginx๋ก ์์ฒญ๋ url (`/api`)์ ๋ฐ๋ผ redirecting ๋ฉ๋๋ค.

```bash
/* ๋ค์ ๋ช๋ น์ project root directory์์ ์ํํฉ๋๋ค.*/

$ docker run -d --name frontend -p 80:80 -p 443:443 \
  -v /etc/letsencrypt:/cert -v /etc/localtime:/etc/localtime:ro \
  --network booktorycicdnetwork frontend:latest

$ docker run -d --name backend -p 8080:8080 \
  --network booktorycicdnetwork backend:latest
```

##### ์์ ์ค์น ๋ฐ ์คํ ๊ณผ์ ์์ ์ฌ์ฉ๋๋ ์ค์ ํ์ผ๋ค์ ๋ค์๊ณผ ๊ฐ์ต๋๋ค.

- `../frontend/Dockerfile`
- `../frontend/homepage.conf`
- `../backend/Dockerfile`

##### ์ด ์ธ ํด๋น ๋๋ ํ ๋ฆฌ์ ํฌํจํ๋ ํ์ผ์ ๋ค์๊ณผ ๊ฐ์ต๋๋ค.

- ๋ฐ์ดํฐ๋ฒ ์ด์ค ๋คํํ์ผ
- ์์ฐ ์๋๋ฆฌ์ค
