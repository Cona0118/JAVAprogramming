package EX;

class Exercise7_20{
    public static void main(String[]args){
        Parent p = new Child();
        Child c = new Child();
        System.out.println("p.x="+p.x);
        p.method();
        System.out.println("c.x="+c.x);
        c.method();
    }
}

class Parent{
    int x=100;
    void method(){
        System.out.println("ParentMethod");
    }
}

class Child extends Parent{
    int x=200;
    void method(){
        System.out.println("ChildMethod");
    }
}