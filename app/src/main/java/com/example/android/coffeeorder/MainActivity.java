package com.example.android.coffeeorder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the `+` button is clicked.
     */
    public void increment(View view) {
        quantity++ ;
        display(quantity);
    }

    /**
     * This method is called when the `-`` button is clicked.
     */
    public void decrement(View view) {
        if (quantity > 0)
            display(quantity--);

        else
            display(0);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view){
        showPrice(quantity * 125);
    }

    /**
     * Calculates the price of the order.
     */
    private void showPrice(float number) {
        TextView priceTextView = (TextView) findViewById(R.id.totalPrice);
        priceTextView.setText("That would be ₹ " + number );
    }

    private void display(int quantity) {
        TextView quantityTextView = (TextView)  findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + this.quantity);
    }
}