# Servico

Servico é um projeto prótotipo de leitura de arquivos XML e persistência em banco de dados. <br />

Utiliza as ferramentas : `maven`, `jpa` , `primefaces` , `persistence hibernate` e `glassfish`. <br />


  O código esta configurado para persistir no banco de dados mysql versão 5.1+. Para maiores detalhes, podemos acessar o arquivo `persistence.xml`, na pasta `META-INF`, e atribuir um novo valor para o `jta-data-source"` conforme as específicações do servidor.<br />
  Servico contém uma interface web para upload de arquivos XML. E seu processo parse é realizado posteriormente pelos arquivos beans. <br />
  O Schema configurado para esse protótipo, chama-se `Cliente.xsd`. Ele pode ser visualizado na pasta `main/resources/Schemas/` ; e um exemplo xml, esta na pasta `test/resources/XML/Cliente/`.<br />
  <br />
  O servidor de aplicação escolhido é o glassfish. A facilidade na configuração e perfomance na maquina, utilizando `Linux/debian`, foram um dos pontos a serem levados em consideração para utilizá-lo.<br />
  <br />
<br />
  Configurações importantes : <br />
  <br />
  Servico cria uma pasta `servicebr` no caminho `/tmp/`. Portanto, toda manipulação dos arquivos XML e, futuramente, arquivos compactados zip, ocorrem lá. De forma que o leitor do sistema funcione, será necessário copiar o arquivo `Schema.xsd` para a pasta `servicebr/Schema`, o qual será gerado em conjunto com a pasta principal em tempo de execução.
  <br />
  <br />
  Outras informações:
  <br />
  Sistema operacional : `LINUX/DEBIAN 8.2.x`<br />
  Glassfish           : `4.1`<br />
  JDK                 : java version `1.7.0_91`<br />
  Apache Maven        : `3.0.5`<br />
  Primefaces          : `5.0`<br />
  Hibernate           : `4.3.1 Final`<br />
  Package gerado      : `war`<br />
