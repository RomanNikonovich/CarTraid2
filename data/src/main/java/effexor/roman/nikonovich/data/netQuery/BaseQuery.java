package effexor.roman.nikonovich.data.netQuery;


import java.io.IOException;

public abstract class BaseQuery<T>  {

    public abstract T getData() throws IOException;
}
