package effexor.roman.nikonovich.data.restApi;

import java.io.IOException;
import java.net.SocketTimeoutException;

import javax.inject.Singleton;

import effexor.roman.nikonovich.data.entity.ErrorType;
import effexor.roman.nikonovich.data.entity.ParseError;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.functions.Function;

@Singleton
public class ErrorTransformers {
    public <Model, ErrorThrowable extends ParseError> ObservableTransformer<Model, Model> parseHttpError() {

        return new ObservableTransformer<Model, Model>() {
            @Override
            public ObservableSource<Model> apply(Observable<Model> upstream) {
                return upstream.onErrorResumeNext(new Function<Throwable, ObservableSource<? extends Model>>() {
                    @Override
                    public ObservableSource<? extends Model> apply(Throwable throwable) throws Exception {
                        if (throwable instanceof SocketTimeoutException) {
                            return Observable.error(new ParseError(ErrorType.SERVER_NOT_AVAILABLE));
                        } else if (throwable instanceof IOException) {
                            //можно обернуть ещё раз в compose и сделать ещё пару попыток соединения с интернетом
                            return Observable.error(new ParseError(ErrorType.NO_INTERNET));
                        } else {
                            return Observable.error(new ParseError(ErrorType.UNKNOWN));
                        }

                    }
                });

            }
        };


    }
}
