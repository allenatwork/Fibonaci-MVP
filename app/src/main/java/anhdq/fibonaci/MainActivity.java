package anhdq.fibonaci;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements FibonacciView {

    private FibonacciPresenter mFibonacciPresenter;
    private RecyclerView mListFiboView;
    private FibonaryListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListFiboView = (RecyclerView) findViewById(R.id.recycler_view);

        FibonacciModel fibonacciModel = new FibonacciModel();
        mFibonacciPresenter = new FibonacciPresenter(fibonacciModel, this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mFibonacciPresenter.startShowListFibonacci();
    }

    @Override
    public void displayListFiboNumber(ArrayList<FibonacciObject> listNumber) {
        mListFiboView.setLayoutManager(new LinearLayoutManager(this));
        mListFiboView.setHasFixedSize(true);
        mAdapter = new FibonaryListAdapter(listNumber);
        mListFiboView.setAdapter(mAdapter);
    }

    @Override
    public void displayNewItemAdded(FibonacciObject fiboNumber) {
//        listFiboNumber.add(fiboNumber);
        mAdapter.notifyItemInserted(mAdapter.getItemCount() - 1);
        mListFiboView.smoothScrollToPosition(mAdapter.getItemCount() - 1);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //Todo

    }
}
