package com.infospace.dependencies;

import org.junit.Test;

import static org.junit.Assert.*;

interface MyInterface {
    boolean MyMethod(String someText);
}

class MyClass implements MyInterface {
    public MyClass() {
    }

    public boolean MyMethod(String someText) {
        return true;
    }
}

public class ContainerTest {
    @Test
    public void registerNew_Then_Resolve_Returns_ItemRegistered() {
        MyClass theInstance = new MyClass();
        Container.register(MyInterface.class, theInstance);

        MyInterface retrieved = Container.resolve(MyInterface.class);
        assertSame(theInstance, retrieved);

        Container.remove(MyInterface.class);
    }


    @Test(expected = Container.TypeNotRegisteredException.class)
    public void resolve_Throws_TypeNotRegisteredException_If_Item_IsNotRegistered() {
        Container.resolve(MyInterface.class);
    }

    @Test
    public void resolve_Throws_TypeNotRegisteredException_Includes_TypeName_InMessage_If_Item_IsNotRegistered() {
        try {
            Container.resolve(MyInterface.class);
            fail("Expected TypeNotRegisteredException to be thrown");
        } catch (Container.TypeNotRegisteredException ex) {
            assertEquals("MyInterface is not registered.", ex.getMessage());
        }
    }
}
