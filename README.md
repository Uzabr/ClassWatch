
# 🎓 ClassWatch

**ClassWatch** — это современное веб-приложение для анализа прогресса и активности учащихся по Excel-отчётам.  
Оно автоматически выявляет студентов с просроченными дедлайнами, заблокированными и замороженными аккаунтами, а также тех, кто не достигает целевых уровней.

---

## 🚀 Что умеет ClassWatch

- 📥 Загрузка Excel-файла с данными студентов
- 📊 Автоматическая аналитика:
  - Заблокированные аккаунты
  - Замороженные учащиеся
  - Просроченные дедлайны
  - Не достигшие цели
- 📋 Просмотр результата в виде JSON-отчета
- 🎯 Чёткий REST API между frontend и backend
- 🎨 Визуализация результата на React-интерфейсе

---

## 🧠 Технологии

### Backend (Spring Boot)
- Java 17+
- Spring Boot
- Apache POI (для Excel)
- REST API (`ResponseEntity`, `@RestController`)
- Maven

### Frontend (React)
- React (TypeScript)
- Tailwind CSS
- Axios (загрузка файла, получение JSON)
- Адаптивный UI

---

## 📂 Структура проекта

```
classwatch/
├── backend/           # Spring Boot приложение
│   ├── src/
│   │   ├── controller/
│   │   ├── service/
│   │   ├── dto/
│   │   └── model/
│   └── pom.xml
├── frontend/          # React приложение
│   ├── src/
│   │   ├── components/
│   │   ├── App.tsx
│   │   └── index.tsx
│   ├── tailwind.config.js
│   ├── package.json
│   └── ...
```

---

## 🧪 Как запустить

### Backend
```bash
cd backend
./mvnw spring-boot:run
```

### Frontend
```bash
cd frontend
npm install
npm start
```

---

## 📄 Лицензия

Проект распространяется по **закрытой (proprietary) лицензии**.  
Код не может быть использован, скопирован, модифицирован или распространён без разрешения владельца.  
Все права защищены.  
Для лицензионных вопросов: khaitboboev94@mail.ru
