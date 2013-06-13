# Registrar

A Java library for registering and managing dependencies in your code.


## Usage

The IOC Container simply keeps a static set of interfaces/classes as keys and
concrete implementations of those as values.  There are 3 main methods on the
Container class:

* register(key, value): this registers the concrete implementation of the interface/class

* resolve(key): this is used by classes that have a dependency on a class/interface to get a concrete insteance to use

* remove(key): the opposite of register; it removes the key/value from the Container.


### Container Example

```java
interface SomeInterface {
    int someMethod(int a, int b);
}


class SomeClass implements SomeInterface {
    int someMethod(int a, int b) {
        return a + b;
    }
}


// Notice, UsesSomeInterface is decoupled from what implements that interface
class UsesSomeInterface {
    void printsStuff() {
        SomeInterface answerGetter = Container.resolve(SomeInterface.class);
        System.out.println("The answer is %s", answerGetter.someMethod(2, 3));
    }
}


// Register the concrete instance that implements the interface
public static class MyApp {
    public static void main() {
        Container.register(SomeInterface.class, new SomeClass());

        UsesSomeInterface thingy = new UsesSomeInterface()
        thingy.printsStuff();
    }
}
```

## Developer setup

### Building

The project uses Ant to build.  It builds a ``` registrar.jar ``` file in the
``` build/jar ``` folder.

The Ant targets are:
```
$ ant clean           #=> just removes the build directory
$ ant test            #=> compiles the code, compiles the tests, and runs the tests
$ ant main (default)  #=> clean, compile, test, and package up non-test files into the jar
```

### Automated Testing

If you want to have your code autoteted while working on Registrar, simply
install Ruby (1.9.3 and above) and then install bundler, and then install
the gems used to do this:

```shell
$ gem install bundler
....
$ bundle install
...
$ guard
20:28:01 - INFO - Guard uses GNTP to send notifications.
20:28:01 - INFO - Guard uses Tmux to send notifications.
20:28:01 - INFO - Guard uses TerminalTitle to send notifications.
20:28:01 - INFO - Guard::Java is running
20:28:01 - INFO - Guard is now watching at '/Users/someuser/Projects/registrar'
[1] guard(main)>
```

Guard will run the entire build process when it starts (using ant).  Then,
when you change a file, it will compile the code and tests and run just the
single unti test class that relates to the file you change.

It tells you what notification methods it will use to tell you when it has detected changes,
when it is building/testing, and whether those things have succeeded or failed.  In the example 
above, I am running Growl as my system notifier and Tmux as my Terminal Multiplexer within 
Mac OSX's Terminal program.

You can see that it detected those things and is using GNTP to send Growl notifications
(popups with detailed messages), Tmux color coding (Yellow for building/testing, Red for 
failures, and Green for success), as well as updating the Terminal window/tab's title
when guard detects a change.

###
