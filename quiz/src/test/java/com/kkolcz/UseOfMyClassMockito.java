package com.kkolcz;

import com.kkolcz.test.MyClass;
import com.kkolcz.test.UseOfMyClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UseOfMyClassMockito {

    @Mock
    MyClass myClassMock;
    UseOfMyClass useOfMyClassMockito;

    @Spy
    MyClass myClassSpy;
    UseOfMyClass useOfMyClassSpy;

    @Captor
    private ArgumentCaptor<List<String>> captor;

    @Captor
    private ArgumentCaptor<String> stringCaptorFirst, stringCaptorSecond;

    @Before
    public void setUp(){
        when(myClassMock.multiply(anyInt(),anyInt())).thenReturn(2);
        useOfMyClassMockito = new UseOfMyClass();
        useOfMyClassMockito.setMyClass(myClassMock);

        useOfMyClassSpy = new UseOfMyClass();
        useOfMyClassSpy.setMyClass(myClassSpy);
    }

    @Test
    public void verifyMultiplyCalled(){
        int result = useOfMyClassMockito.useMyClass(7,8);
        verify(myClassMock).multiply(7,8);
        Assert.assertEquals(2,result);
    }

    @Test
    public void testMockitoExceptionThrowing(){
        when(myClassMock.add(anyString(),anyString())).thenThrow(
               new IllegalArgumentException("bla")
        );

        try{
            useOfMyClassMockito.useMyClassAdd("99","101");
            fail("bla");
        }catch (IllegalArgumentException e){

        }
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testLinkedListSpyWrong() {
        // Lets mock a LinkedList
        List<String> list = new LinkedList<>();
        List<String> spy = spy(list);

        // this does not work
        // real method is called so spy.get(0)
        // throws IndexOutOfBoundsException (list is still empty)
        when(spy.get(0)).thenReturn("foo");

        Assert.assertEquals("foo", spy.get(0));
    }

    @Test
    public void testLinkedListSpyCorrect() {
        // Lets mock a LinkedList
        List<String> list = new LinkedList<>();
        List<String> spy = spy(list);

        // You have to use doReturn() for stubbing
        doReturn("foo").when(spy).get(0);

        Assert.assertEquals("foo", spy.get(0));
    }

    @Test
    public void verifySpy(){
        useOfMyClassSpy.useMyClassAdd("4","8");
//        verify(myClassSpy).add(ArgumentMatchers.eq("4"),ArgumentMatchers.eq("8"));
        verify(myClassSpy,times(1)).add(anyString(),anyString());
        verify(myClassSpy, never()).add(anyInt(),anyInt());
        verifyNoMoreInteractions(myClassSpy);

    }

    @Test
    public void verifyMock(){
        useOfMyClassMockito.useMyClassAdd("4","8");
        verify(myClassMock.add(anyString(),anyString()));

    }

    @Test
    public final void shouldContainCertainListItem() {
        List<String> asList = Arrays.asList("someElement_test", "someElement");
        final List<String> mockedList = mock(List.class);
        mockedList.addAll(asList);

        verify(mockedList).addAll(captor.capture());
        final List<String> capturedArgument = captor.getValue();
        Assert.assertThat(capturedArgument, hasItem("someElement"));
    }

    @Test
    public void verifyCaptor(){
        useOfMyClassMockito.useMyClassAdd("4","8");
        verify(myClassMock).add(stringCaptorFirst.capture(),stringCaptorSecond.capture());
        Assert.assertEquals(stringCaptorFirst.getValue(),"4");
    }
}
