#### Arquitectura hexagonal

Arquitectura que fomenta que nuestro dominio sea el núcleo de todas las capas, también conocida como puertos y
adaptadores en la cual el dominio define los puertos y en las capas superiores se definen los adaptadores para
desacoplar el dominio. Se divide princialmente en tres capas, **aplicación**, **dominio** e **infraestructura**

- **Infraestructura**: Capa que tiene las responsabilidades de realizar los adaptadores a los puertos definidos en el
  domino, exponer web services, consumir web services, realizar conexiones a bases de datos, ejecutar sentencias DML, en
  general todo lo que sea implementaciones de cualquier framework
- **Aplicación**: capa encargada de enrutar los eventos entrantes de la capa de infraestructura hacía la capa del
  dominio, generalmente se conoce como una barrera transaccional la cual agrupa toda la invocación de un caso de uso, se
  pueden encontrar patrones como Fabricas, Manejadores de Comandos, Bus de eventos, etc
- **Dominio**: representa toda la lógica de negocio de la aplicación la cual es la razón de existir del negocio. Se
  busca evitar el
  anti-patron [https://martinfowler.com/bliki/AnemicDomainModel.html](https://martinfowler.com/bliki/AnemicDomainModel.html)
  y favorecer el
  principio [https://martinfowler.com/bliki/TellDontAsk.html](https://martinfowler.com/bliki/TellDontAsk.html) en esta
  capa se pueden encontrar los siguientes patrones agregados, servicios de dominio, entidades, objetos de valor,
  repositorios (puerto), etc.
