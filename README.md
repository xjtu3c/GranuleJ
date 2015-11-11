The GranuleJ Language
========

The GranuleJ language is built on top of Java. The extended language constructs primarily  contain context variable, granule, shadow class and fitness test. The most preeminent peculiarity of it is that the language provides the abilities of execution environment perception and dynamic behavioral evolution depending on the environment for the developers. The introduced new syntaxes and semantics are outlined as follows:

**external**: it is the keyword that declares context variable, which is context information that the program is interested in. Context variable is a special variable that is shared by many applications or processes in the distrusted environment. As a result, when a granule program is started, the declared context variables are required to be registered into the central server, and meanwhile retrieved the effective values from it.

**granule**: it is the keyword that declares granule, like the keyword class. But it must be attached to a specific base class.

**within**: it is the keyword that declares shadow class, which is code fragment that supplements some behaviors for its base class in a modular way. Shadow class is encapsulated into a granule construct, and is selected by fitness conditions. Additionally, it serves solely as one base class only once in a granule. For this reason, it has not its own identify.

The important language mechanisms of GranueJ are illustrated in the following

**Fitness test**: the mechanism is used to check that the interested context is suitable for the actual environment. The check is triggered at runtime. The execution control automatically intercepts the corresponding method invocation sites where the program behaviors are to be modified due to the potential context changes, and checks the fitness tests of all ancestors of the current granule over granule tree one by one. If they fit for the current environments, the execution control continues normally. Otherwise, it enters the process of granule substitution.

**Granule substution**: When the running granule is unsuitable for the current environment, the execution control will query the desired granules from individual library. If founded granules satisfy the current execution contexts by fitness test, they will be composed into base classes to generate new class variants, replacing the running classes for execution. If the query fails, the process will go on repetitively until it successes.

**Dynamic evolution**: the formed class variant is substituted during program execution. It includes new class is loaded and many stale instances are updated lazily.

The more details of the current language is available as a summary.
