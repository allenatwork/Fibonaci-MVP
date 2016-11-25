package anhdq.fibonaci;

import android.content.Context;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Admin on 25-Nov-16.
 */

public class FibonacciItemView extends RelativeLayout {
    TextView number, time, index;

    public FibonacciItemView(Context context) {
        super(context);
        init(context);
    }

    public void init(Context context) {
        inflate(context, R.layout.fibonacci_item_view, this);
        number = (TextView) findViewById(R.id.number);
        time = (TextView) findViewById(R.id.time);
        index = (TextView) findViewById(R.id.index);
    }

    public void display(FibonacciObject fibonacciObject, int ind) {
        if (fibonacciObject == null) return;
        number.setText(String.valueOf(fibonacciObject.getNumber()));
        time.setText(String.valueOf(fibonacciObject.getTime()) + "ms");
        index.setText(String.valueOf(ind));
        if (fibonacciObject.getNumber() % 2 == 0) { // even
            setBackgroundColor(getResources().getColor(R.color.even_color));
        } else { // odd
            setBackgroundColor(getResources().getColor(R.color.odd_color));
        }
    }
}
