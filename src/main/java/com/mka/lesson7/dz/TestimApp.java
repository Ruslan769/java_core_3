package com.mka.lesson7.dz;

public class TestimApp {

    @BeforeSuite
    public void methodBefore() {
        System.out.println("Before test");
    }

    @AfterSuite
    public void methodAfter() {
        System.out.println("After test");
    }

    @Test(priority = 8)
    public void test1() {
        System.out.println("Priority test 8_1");
    }

    @Test(priority = 5)
    public void test2() {
        System.out.println("Priority test 5_2");
    }

    @Test(priority = 1)
    public void test3() {
        System.out.println("Priority test 1_3");
    }

    @Test(priority = 3)
    public void test4() {
        System.out.println("Priority test 3_4");
    }

    @Test(priority = 2)
    public void test5() {
        System.out.println("Priority test 2_5");
    }

    @Test
    public void test6() {
        System.out.println("Priority default test 10_6");
    }

}
