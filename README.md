**Master:**

[![DevOps By Rultor.com](http://www.rultor.com/b/yegor256/cactoos)](http://www.rultor.com/p/smallcreep/jb-hub-client)

[![Build status](https://ci.appveyor.com/api/projects/status/asqovs1okkxow1w5/branch/master?svg=true)](https://ci.appveyor.com/project/smallcreep/jb-hub-client/branch/master)
[![Build Status](https://travis-ci.org/smallcreep/jb-hub-client.svg?branch=master)](https://travis-ci.org/smallcreep/jb-hub-client)
[![codecov](https://codecov.io/gh/smallcreep/jb-hub-client/branch/master/graph/badge.svg)](https://codecov.io/gh/smallcreep/jb-hub-client)
[![Dependency Status](https://www.versioneye.com/user/projects/594d32ff6725bd00409fff0e/badge.svg?style=flat-square)](https://www.versioneye.com/user/projects/594d32ff6725bd00409fff0e)
[![PDD status](http://www.0pdd.com/svg?name=smallcreep/jb-hub-client)](http://www.0pdd.com/p?name=smallcreep/jb-hub-client)

# JB-HUB-Client

Set of classes in `com.github.smallcreep.jb.hub.api`
package is a fluent OOP adapter [JetBrains API](https://www.jetbrains.com/help/hub/HUB-REST-API.html):

```java
import com.github.smallcreep.jb.hub.api.RtHub;
import com.github.smallcreep.jb.hub.api.Hub;
import java.net.URI;

public class Main {
    public static void  main(String[] args) {
        Hub hub = new RtHub(new URI(".. your Hub uri .."), ".. your OAuth token ..");
        hub.users().self().json().getString("name");
    }
}
```

## Got questions?

If you have questions or general suggestions, don't hesitate to submit
a new [Github issue](https://github.com/smallcreep/jb-hub-client/issues/new).

## How to Contribute

Fork repository, make changes, send us a pull request. We will review
your changes and apply them to the `master` branch shortly, provided
they don't violate our quality standards. To avoid frustration, before
sending us your pull request please run full Gradle build:

```
$ gradle clean build -PQuality
```

To avoid build errors use gradle wrapper:

```
$ ./gradlew clean build -PQuality
```

## Contributors

  - [@smallcreep](https://github.com/smallcreep) as Ilia Rogozhin (ilia.rogozhin@gmail.com)

## License (MIT)

Copyright (c) 2017 Ilia Rogozhin (ilia.rogozhin@gmail.com)

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall
be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.