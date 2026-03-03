---

# 🧪 Projeto de Testes Automatizados em BDD com Cucumber, Selenium e Java 21

Este projeto demonstra a aplicação de **Behavior Driven Development (BDD)** utilizando **Cucumber**, **Selenium WebDriver** e **Java 21**. O objetivo é criar testes automatizados claros, legíveis e alinhados ao comportamento esperado pelo usuário final.

---

### 📌 Tecnologias Utilizadas
- **Java 21**
- **Cucumber**
- **Selenium com WebDriver**
- **WebDriverManager e Slf4j**
- **JUnit 4**
- **Gherkin**
- **Maven**

---

### 🧠 Sobre o BDD no Projeto
Os cenários são escritos em **Gherkin**, permitindo que o comportamento da aplicação seja descrito em linguagem natural.  
A estrutura segue o padrão:

- **Given** — contexto inicial  
- **When** — ação executada
- **And** — ação sequencial caso exista
- **Then** — resultado esperado  

Esses cenários refletem os **critérios de aceite definidos pelo PO**, garantindo alinhamento entre negócio e desenvolvimento.

---

### 📁 Estrutura do Projeto (exemplo)
```
src
 └── test
     ├── java
     │    ├── stepDefinitions
     │    └── TestRunner.java
     └── resources
          ├── drivers
          └── features
```

---

### ▶️ Como Executar os Testes
1. Certifique-se de ter o **Java 21** instalado.  
2. Instale as dependências com:
   ```
   mvn clean install
   ```
3. Execute os testes:
   ```
   mvn test
   ```

---

### 🧱 Padrões e Boas Práticas Aplicadas
- Escrita de cenários claros e objetivos  
- Page Object Model (caso esteja usando)  
- Separação entre steps, features e lógica de automação  
- Reutilização de steps e componentes  
- Testes orientados ao comportamento do usuário  

---

### 🚀 Objetivo do Projeto
Servir como base para estudos, demonstração de boas práticas em BDD e automação de testes, além de facilitar a colaboração entre áreas técnicas e de negócio.

---
