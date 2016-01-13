# Servico

Servico é um projeto prótotipo de leitura de arquivos XML e persistência em banco de dados. 

Utiliza as ferramentas : `maven`, `jpa` , `primefaces` , `persistence hibernate` e `glassfish`. 


  O código esta configurado para persistir no banco de dados mysql versão 5.1+. Para maiores detalhes, podemos acessar o arquivo `persistence.xml`, na pasta `META-INF`, e atribuir um novo valor para o `jta-data-source"` conforme as específicações do servidor.
  Servico contém uma interface web para upload de arquivos XML. E seu processo parse é realizado posteriormente pelos arquivos beans. 
  O Schema configurado para esse protótipo, chama-se `Cliente.xsd`. Ele pode ser visualizado na pasta `main/resources/Schemas/` ; e um exemplo xml, esta na pasta `test/resources/XML/Cliente/`.
  
  O servidor de aplicação escolhido é o glassfish. A facilidade na configuração e perfomance na maquina, utilizando `Linux/debian`, foram um dos pontos a serem levados em consideração para utilizá-lo.
  
  
  Configurações importantes : 
  
  Servico cria uma pasta `servicebr` no caminho `/tmp/`. Portanto, toda manipulação dos arquivos XML e, futuramente, arquivos compactados zip, ocorrem lá. De forma que o leitor do sistema funcione, será necessário copiar o arquivo `Schema.xsd` para a pasta `servicebr/Schema`, o qual será gerado em conjunto com a pasta principal em tempo de execução.
  
  
  Outras informações:
  
  Sistema operacional : `LINUX/DEBIAN 8.2.x`
  Glassfish           : `4.1`
  JDK                 : java version `1.7.0_91`
  Apache Maven        : `3.0.5`
  Primefaces          : `5.0`
  Hibernate           : `4.3.1 Final`
  Package gerado      : `war`
