package pt.stelvio.weather;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import pt.stelvio.weather.interfaces.ForecastInteractor;
import pt.stelvio.weather.interfaces.MainContract.ForecastView;
import pt.stelvio.weather.pojos.ForecastChannels;
import pt.stelvio.weather.pojos.ForecastQuery;
import pt.stelvio.weather.pojos.ForecastQueryResults;
import pt.stelvio.weather.pojos.ForecastResult;
import pt.stelvio.weather.presenters.ForecastPresenter;
import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * Created by stelv on 6/21/2018.
 */

public class MainPresenterTest {
    @Mock
    private ForecastInteractor forecastInteractor;

    @Mock
    private ForecastView view;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void fetchValidDataShouldLoadIntoView() {

        ForecastResult forecastResult = new ForecastResult();
        forecastResult.setQuery(new ForecastQuery());
        forecastResult.getQuery().setQueryResults(new ForecastQueryResults());
        forecastResult.getQuery().getQueryResults().setForecastChannels(new ForecastChannels());

        when(forecastInteractor.getForecast())
                .thenReturn(Observable.just(forecastResult));

        ForecastPresenter forecastPresenter = new ForecastPresenter(forecastInteractor);
        forecastPresenter.setSchedulers(Schedulers.immediate(), Schedulers.immediate());
        forecastPresenter.bind(view);
        forecastPresenter.requestData();

        InOrder inOrder = Mockito.inOrder(view);
        inOrder.verify(view, times(1)).onFetchDataStarted();
        inOrder.verify(view, times(1)).updateUi(forecastResult.getQuery()
                .getQueryResults().getForecastChannels());
        inOrder.verify(view, times(1)).onFetchDataCompleted();
    }

    @Test
    public void fetchErrorShouldReturnErrorToView() {

        Exception exception = new Exception();

        when(forecastInteractor.getForecast())
                .thenReturn(Observable.<ForecastResult>error(exception));

        ForecastPresenter forecastPresenter = new ForecastPresenter(forecastInteractor);
        forecastPresenter.setSchedulers(Schedulers.immediate(), Schedulers.immediate());
        forecastPresenter.bind(view);
        forecastPresenter.requestData();

        InOrder inOrder = Mockito.inOrder(view);
        inOrder.verify(view, times(1)).onFetchDataStarted();
        inOrder.verify(view, times(1)).onFetchDataError(exception);
        verify(view, never()).onFetchDataCompleted();
    }
}
