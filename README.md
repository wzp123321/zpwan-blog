# zpwan-blog

个人博客三工程合集：Vue 2 用户前台、Vue 2 管理后台、Spring Boot 后端。由原先分散的三个仓库合并而来，方便一次性克隆、启动与归档。

原仓库：
- 前台 `zpwan_blog_web`（最后更新 2020-09）
- 后台 `zpwan_blog-manager`（最后更新 2020-08）
- 后端 `zpwan_blog_server`（原为私有仓库，最后更新 2020-12）

## 目录结构

| 目录 | 说明 | 技术栈 |
| --- | --- | --- |
| `zpwan-blog-web/` | 博客前台：首页、文章、评论、留言、相册、音乐、登录（GitHub OAuth） | Vue 2.6 + TypeScript + Vue CLI 4 + BootstrapVue + Element UI + axios |
| `zpwan-blog-manager/` | 管理后台：文章/标签/目录/音乐/图片/管理员/友链/公告管理 | Vue 2.6 + TypeScript + Vue CLI 4 + Ant Design Vue 1 + axios |
| `zpwan-blog-server/` | 后端 API：文章、评论、音乐、图片、留言、字典、友链、公告、文件、WebSocket | Spring Boot 1.5.19 + Java 8 + MyBatis + Druid + MySQL + JWT |
| `archive/` | 2024 年实验性的 Vue 3 版前台/后台骨架，仅作历史归档，不参与运行 | Vue 3 + Vite + TypeScript |

## 快速启动

### 1. 数据库

- MySQL 5.7 / 8.0，建库 `zpwan_blog_info`（字符集建议 `utf8mb4`）。
- 执行 `zpwan-blog-server/sql/zpwan_blog_datasource.sql` 建表。
- 仓库内不再包含历史数据备份；如需迁移旧数据请自行从原备份导入。

### 2. 后端

需要 JDK 8 + Maven。

```bash
cd zpwan-blog-server
export DB_URL='jdbc:mysql://127.0.0.1:3306/zpwan_blog_info?useUnicode=true&characterEncoding=utf8&useSSL=false'
export DB_USERNAME=root
export DB_PASSWORD='your-password'
mvn spring-boot:run
```

默认端口 `9898`，接口前缀 `/blogManage`。完整环境变量见 `zpwan-blog-server/.env.example`；Spring Boot 1.5 不会自动读取 `.env`，请在 shell 或部署平台中注入。

### 3. 前台

需要 Node.js 14（Vue CLI 4 兼容版本）。

```bash
cd zpwan-blog-web
cp .env.example .env.local
npm install
npm run serve     # 默认 http://localhost:8088
npm run build     # 产物目录 blogWeb/
```

### 4. 管理后台

```bash
cd zpwan-blog-manager
cp .env.example .env.local
npm install
npm run serve
npm run build
```

`.env.local` 中的 `VUE_APP_API_BASE_URL` 决定请求哪个后端；WebSocket 地址由前台的 `VUE_APP_WS_HOST` 控制。

## 配置与脱敏说明

合并时按下述规则处理，避免凭据公开：

- 删除 588KB 数据库备份 `db_zpwan_blog_info_20201201_221001.sql` 与运行日志、IDE 文件。
- 数据库账号、邮箱 SMTP、FTP 连接改为环境变量注入；源码内不再保存明文密码。
- 前端接口地址与 WebSocket 地址支持 `VUE_APP_*` 环境变量覆盖，默认值仅为本地开发。
- 图片 CDN 的旧 IP 仍以静态资源形式出现在代码中，仅用于显示历史文章配图，不含凭据；如已失效请自行替换。

## 接口约定

后端接口按「模块 / 界面 / 功能」命名，例如 `articlemodule/article/select`。除登录注册外，请求头携带 `token`；403 由前端请求层统一跳转登录。

## 已知限制

- 技术栈停留在 2019—2020 年：Spring Boot 1.5、Vue 2、axios 0.19、log4j 1.2。若用于公网部署，建议先升级依赖并补齐鉴权、限流、HTTPS 与安全审计。
- 登录态由前端保存 token，权限控制较粗；不适合多角色生产场景。
- `archive/` 中的 Vue 3 骨架未接入后端，勿直接部署。

## License

未指定许可证。合并仅用于个人学习与备份，第三方依赖遵循各自许可证。
