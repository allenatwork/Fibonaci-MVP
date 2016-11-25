package anhdq.fibonaci;

/**
 * Created by Admin on 25-Nov-16.
 */

public class FibonacciPresenter implements BasePresenter, BaseModel.OnNewFiboNumberGeneratedListenter {
    private FibonacciModel mFibonacciModel;
    private FibonacciView mFibonacciView;

    public FibonacciPresenter(FibonacciModel mFibonacciModel, FibonacciView mFibonacciView) {
        this.mFibonacciModel = mFibonacciModel;
        this.mFibonacciView = mFibonacciView;
    }


    @Override
    public void onNewFiboNumberGenerated(FibonacciObject fibonacciObject) {
        mFibonacciView.displayNewItemAdded(fibonacciObject);
    }

    @Override
    public void startShowListFibonacci() {
        mFibonacciView.displayListFiboNumber(mFibonacciModel.getListFibo());
    }


    @Override
    public void stopShowListFibonacci() {
        mFibonacciModel.stopGenerateFiboNumber();
    }

    @Override
    public void startGenerateFiboNumber() {
        mFibonacciModel.setOnNewFiboNumberGeneratedListenter(this);
        mFibonacciModel.startGenerateFiboNumber();
    }
}
