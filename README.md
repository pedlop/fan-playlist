# **FanPlaylist** #
Repositório vinculado a Projeto privado no Bitbucket e criação realizada para a disciplina de Desenvolvimento de Software para Web - INF/UFG - 2017/1.

Para quem tem o acesso requerido, segue o link para a documentação: [Documentação FanPlaylist](https://bitbucket.org/asbuilt/mobile/src/3012639dfe211020140360a5eb911e45eaa1ba61/docApplication/?at=dev-clean).

Índice
--- 
- [Tecnologias Utilizadas](#quais-as-tecnologias-utilizadas)
- [Rodando Projeto](#como-faço-para-rodar-o-projeto)

## **Quais as Tecnologias utilizadas?** ##

Para o desenvolvimento do projeto serão utilizadas ferramentas para auxiliar na Gerência do Projeto e na construção do software. As seguintes tecnologias serão utilizadas:

**Linguagem de Programação & Frameworks:**
- [Angular 4](https://angular.io/) - _Framework JavaScript open-source, mantido pelo Google, que auxilia na execução de single-page applications._
- [Java](https://www.oracle.com/java/index.html) - _Linguagem de programação interpretada orientada a objetos._
- [Spark](http://sparkjava.com/) - _Um micro framework para criar aplicações web em Java 8._
- [Spring Data](http://projects.spring.io/spring-data/) - _Fornece um modelo de programação familiar e consistente baseado em Spring para acesso a dados, mantendo os traços especiais do armazenamento de dados subjacente._
- [Hibernate](http://hibernate.org/) - _Framework para o mapeamento objeto-relacional escrito na linguagem Java._
- [JUnit](http://junit.org/junit4/) - _Estrutura simples para escrever testes repetitivos. É uma instância da arquitetura xUnit para estruturas de teste de unidade._

**Gerenciador de Dependências:**
- [Maven](https://maven.apache.org/) - _Ferramenta de gerenciamento e compreensão de projetos de software. Com base no conceito de um modelo de objeto de projeto (POM), o Maven pode gerenciar a compilação, relatórios e documentação de um projeto a partir de uma peça central de informação._

**Banco de Dados:**
- [MongoDB](https://www.mongodb.com/) - _Programa de banco de dados orientado a documentos multi-plataforma livre e de código aberto. Classificada como um programa de banco de dados NoSQL, o MongoDB usa documentos semelhantes a JSON com esquemas._

**IDE(s):**
- [IntelliJ](https://www.jetbrains.com/idea/) - _Ambiente de desenvolvimento integrado Java (IDE) para o desenvolvimento de software de computador._
- [Eclipse](https://eclipse.org/) - _IDE para desenvolvimento Java, porém suporta várias outras linguagens a partir de plugins como C/C++, PHP, ColdFusion, Python, Scala e plataforma Android._
- [Visual Studio Code](https://code.visualstudio.com/) - _Editor de código fonte leve mas poderoso que funciona em seu desktop e está disponível para Windows, macOS e Linux._
- [Atom](https://atom.io/) - _Editor de texto que é moderno, acessível, mas hackable para o núcleo - uma ferramenta que você pode personalizar para fazer qualquer coisa, mas também usar de forma produtiva sem nunca tocar em um arquivo de configuração._

**Gerência de Projeto/Configuração e Comunicação da Equipe:**
- [Trello](https://trello.com/) - _Controle de atividades (Pendentes, em execução e realizadas)._
- [Github](https://github.com/) - _Controle de versão do código fonte do projeto._
- [GitKraken](https://www.gitkraken.com/) - _A GUI(Interface Gráfica do Usuário) mais popular do Git para Windows, Mac e Linux_.
- [Whatsapp](https://web.whatsapp.com/) - _Comunicação a qualquer momento do dia, em grupo da equipe._

## **Como faço para rodar o projeto?** ##

**Caso você não tenha o ambiente Anglular CLI configurado:**
>* Faça o download e a instalação do [Node.js](https://nodejs.org/en/);
>* Na linha de comando:
>* `npm install`
>* `npm install -g @angular/cli`

**Caso o ambiente já esteja configurado:**
>* Clone o projeto através do GitKraken, copie e cole o link HTTPS;
>* Ou [Clonando pela linha de comando](#clonando-projeto-pela-linha-de-comando);
>* Vá no diretório `cd fan-playlist` e `cd fan-app` em que o projeto foi clonado pela linha de comando;
>* Digite: `ng serve`
>* Acesse no seu browser o `localhost:4200`
>* Projeto configurado e rodando.

#### **Clonando Projeto pela Linha de Comando:**
>* Caso não tenha o GitKraken:
>* Vá na linha de comando e digite:
> `git clone https://github.com/pedlop/fan-playlist.git`

**Caso você queira criar um novo Component:**
> * No diretório do projeto:
> `ng g (ou 'generate') component <nome>`
