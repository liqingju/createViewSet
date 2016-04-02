// IMyAidlInterface.aidl
package com.example.liqingju.aidl;
package com.example.liqingju.aidl.Book;

// Declare any non-default types here with import statements

interface IMyAidlInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);

            void getList();
            void ty(in Book aBook);
}
