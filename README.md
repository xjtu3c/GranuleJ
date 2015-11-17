The GranuleJ Language
========

The GranuleJ language is built on top of Java. The extended language constructs primarily contain context variable, granule, shadow class and fitness test. The most preeminent peculiarity of it is the ability to perceive execution environment and evolve dynamic behavior depending on the environment changes for the developers. The new syntax and semantics we introduced are outlined as follows:

**external**: it is the keyword that declares context variable, which is context information that the program is interested in. Context variable is a special variable that is shared by many applications or processes in a distrusted environment. As a result, when a granulej program is started, the declared context variables are required to be registered into the central server, and meanwhile retrieved the effective values from it.

**granule**: it is the keyword that declares granule, like the keyword class. But it must be attached to a specific base class.

**within**: it is the keyword that declares shadow class, which is code fragment that supplements some behaviors for its base class in a modular way. Shadow class is encapsulated into a granule construct, and is selected by fitness conditions. Additionally, it serves solely as one base class only once in a granule. For this reason, it has not its own identify.

The important language mechanisms of GranueJ are illustrated in the following

**Fitness test**: the mechanism is used to check that whether or not the interested context is suitable for the actual environment. The check is triggered at runtime. The execution control automatically intercepts the corresponding method invocation sites where the program behaviors are to be modified due to the potential context changes, and checks the fitness tests of all ancestors of the current granule over granule tree one by one. If they fit for the current environments, the execution control continues normally. Otherwise, it enters the process of granule substitution.

**Granule substitution**: When the running granule is unsuitable for the current environment, the execution control will query the desired granules from individual library. If the granules which satisfy the current execution contexts through fitness test were found, they will be composed into base classes to generate new class variants, replacing the running classes for execution. If the query fails, the process will go on repetitively until it succeeds.

**Dynamic evolution**: the formed class variant is substituted during program execution. It includes new class is loaded and many existing stale instances are updated lazily.

More details of the current language are available as [a summary](https://github.com/xjtu3c/GranuleJ/blob/master/GOP/Documents/GranuleJSummary.pdf).
