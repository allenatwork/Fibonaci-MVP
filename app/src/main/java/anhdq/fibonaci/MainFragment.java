package anhdq.fibonaci;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment implements FibonacciView {
    public static final String TAG = "Fragment";
    private FibonacciPresenter mFibonacciPresenter;
    private RecyclerView mListFiboView;
    private FibonaryListAdapter mAdapter;

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        setRetainInstance(true);
        FibonacciModel fibonacciModel = new FibonacciModel();
        mFibonacciPresenter = new FibonacciPresenter(fibonacciModel, this);
        mFibonacciPresenter.startGenerateFiboNumber();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d(TAG, "onCreateView");
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG, "onViewCreated");
        mListFiboView = (RecyclerView) view.findViewById(R.id.recycler_view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, "onActivityCreated");
        mFibonacciPresenter.startShowListFibonacci();
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    public void displayListFiboNumber(ArrayList<FibonacciObject> listNumber) {
        mListFiboView.setLayoutManager(new LinearLayoutManager(getContext()));
        mListFiboView.setHasFixedSize(true);
        mAdapter = new FibonaryListAdapter(listNumber);
        mListFiboView.setAdapter(mAdapter);
    }

    @Override
    public void displayNewItemAdded(FibonacciObject fiboNumber) {
        if (mAdapter != null) {
            mListFiboView.post(new Runnable() {
                @Override
                public void run() {
                    mAdapter.notifyItemInserted(mAdapter.getItemCount() - 1);
                    mListFiboView.smoothScrollToPosition(mAdapter.getItemCount() - 1);
                }
            });

        }
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mFibonacciPresenter.stopShowListFibonacci();
        Log.d(TAG, "onDestroy");
    }
}
