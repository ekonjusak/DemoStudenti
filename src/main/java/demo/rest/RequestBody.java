package demo.rest;



public class RequestBody {


    private String hello;

    private String foo;
    private Integer count;

    public RequestBody(String hello, String foo, Integer count) {
        this.hello = hello;
        this.foo = foo;
        this.count = count;
    }

    public RequestBody() {
    }

    public String getHello() {
        return hello;
    }

    public void setHello(String hello) {
        this.hello = hello;
    }

    public String getFoo() {
        return foo;
    }

    public void setFoo(String foo) {
        this.foo = foo;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "RequestBody{" +
                "hello='" + hello + '\'' +
                ", foo='" + foo + '\'' +
                ", count=" + count +
                '}';
    }
}
