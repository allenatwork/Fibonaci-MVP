package anhdq.fibonaci;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Admin on 25-Nov-16.
 */

public class FibonaryListAdapter extends RecyclerView.Adapter<FibonaryListAdapter.FibonacciItemViewHolder> {
    ArrayList<FibonacciObject> listFiboNumber;

    public FibonaryListAdapter(ArrayList<FibonacciObject> listFiboNumber) {
        this.listFiboNumber = listFiboNumber;
    }

    @Override
    public FibonacciItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        FibonacciItemView itemView = new FibonacciItemView(parent.getContext());
        return new FibonacciItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(FibonacciItemViewHolder holder, int position) {
        holder.display(listFiboNumber.get(position), position + 1);
    }

    @Override
    public int getItemCount() {
        if (listFiboNumber == null) return 0;
        return listFiboNumber.size();
    }

    public static class FibonacciItemViewHolder extends RecyclerView.ViewHolder {
        FibonacciItemView view;

        public FibonacciItemViewHolder(View itemView) {
            super(itemView);
            view = (FibonacciItemView) itemView;
        }

        void display(FibonacciObject fiboObject, int index) {
            view.display(fiboObject, index);
        }
    }
}
