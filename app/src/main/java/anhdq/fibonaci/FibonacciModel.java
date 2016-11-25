package anhdq.fibonaci;

import android.os.AsyncTask;

import java.util.ArrayList;

/**
 * Created by Admin on 25-Nov-16.
 */

public class FibonacciModel implements BaseModel {
    public static final int MAX_ITEM = 100;
    public static final int SEED_ZERO = 0;
    public static final int SEED_ONE = 1;
    private ArrayList<FibonacciObject> listFibo;


    BaseModel.OnNewFiboNumberGeneratedListenter onNewFiboNumberGeneratedListenter;

    public FibonacciModel() {
        listFibo = new ArrayList<>();
    }

    public void setOnNewFiboNumberGeneratedListenter(OnNewFiboNumberGeneratedListenter onNewFiboNumberGeneratedListenter) {
        this.onNewFiboNumberGeneratedListenter = onNewFiboNumberGeneratedListenter;
    }

    // This class should not be call by outsite class
    private void addNewFiboNumber(FibonacciObject fibo) {
        listFibo.add(fibo);
        if (onNewFiboNumberGeneratedListenter == null)
            throw new IllegalStateException("Presenter should implement interface when new item generated");
        onNewFiboNumberGeneratedListenter.onNewFiboNumberGenerated(fibo);
    }

    public ArrayList<FibonacciObject> getListFibo() {
        return listFibo;
    }

    //call task and generate
    @Override
    public void startGenerateFiboNumber() {
        FibonacciGenerateTask.execute(listFibo);
    }

    // Todo: cancel the task because user want to stop or the activity destroy
    @Override
    public void stopGenerateFiboNumber() {
        FibonacciGenerateTask.cancel(true);
    }

    private AsyncTask<ArrayList<FibonacciObject>, FibonacciObject, Void> FibonacciGenerateTask = new AsyncTask<ArrayList<FibonacciObject>, FibonacciObject, Void>() {

        private FibonacciObject generateNewFiboNumberFromList(ArrayList<FibonacciObject> listNumber) {
            long startTime = System.currentTimeMillis();
            int size = listNumber.size();
            FibonacciObject fibonacciObject;
            if (size == 0) {
                fibonacciObject = new FibonacciObject(SEED_ONE + SEED_ZERO, System.currentTimeMillis() - startTime);
            } else if (size == 1) {
                fibonacciObject = new FibonacciObject(listNumber.get(0).getNumber() + SEED_ONE, System.currentTimeMillis() - startTime + listNumber.get(0).getTime());
            } else {
                fibonacciObject = new FibonacciObject(listNumber.get(size - 1).getNumber() + listNumber.get(size - 2).getNumber(), System.currentTimeMillis() - startTime + listNumber.get(size - 1).getTime());
            }
            FibonacciModel.this.addNewFiboNumber(fibonacciObject);
            return fibonacciObject;
        }

        @Override
        protected Void doInBackground(ArrayList<FibonacciObject>... listNumber) {
            if (!isCancelled()) {
                for (int i = 0; i < FibonacciModel.MAX_ITEM; i++) {
                    try {
                        Thread.sleep(400);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    FibonacciObject next_fibo = generateNewFiboNumberFromList(listNumber[0]);
                    publishProgress(next_fibo);
                }
            }
            return null;
        }
    };


}
