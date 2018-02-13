package com.kkolcz.test;

import org.springframework.beans.factory.annotation.Autowired;

public class UseOfMyClass {
//    @Autowired
    MyClass myClass;

    public MyClass getMyClass() {
        return myClass;
    }

    public void setMyClass(MyClass myClass) {
        this.myClass = myClass;
    }

    public int useMyClass(int a, int b){
        return myClass.multiply(a,b);
    }

    public int useMyClassAdd(String a, String b){
        return myClass.add(a,b);
    }
}
