package anhdq.fibonaci;

/**
 * Created by Admin on 25-Nov-16.
 */

public interface BaseModel {
    interface OnNewFiboNumberGeneratedListenter {
        void onNewFiboNumberGenerated(FibonacciObject fibonacciObject);
    }

    void startGenerateFiboNumber();

    void stopGenerateFiboNumber();
}
