package com.example.android.coffeeorder;

import static android.R.color.holo_red_light;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 *  Bugs list:-
 *  1. Increment-Decrement not displaying quantity properly.
 *  2. Increment-Decrement displaying 0 in order summary.
 */

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 1;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the `+` button is clicked.
     */
    public void increment(View view) {
//        quantity++ ;
//        displayQuantity(quantity++);

//        if (quantity > 100) {
//            // Show an error message as a toast
//            Toast.makeText(this, "You cannot have more than 100 coffees", Toast.LENGTH_SHORT).show();
//
//            // Exit this method early because there's nothing left to do
//            return;
//        }
//        else
//            displayQuantity(quantity++);

        if (quantity < 100)
            displayQuantity(quantity++);
        else {
            // Show an error message as a toast
            Toast.makeText(this, "You cannot have more than 100 coffees", Toast.LENGTH_SHORT).show();

            // Exit this method early because there's nothing left to do
            return;
        }
    }

    /**
     * This method is called when the `-`  button is clicked.
     */
    public void decrement(View view) {

//        if (quantity < 1) {
//            // Show an error message as a toast
//            Toast.makeText(this, "You cannot have less than 1 coffee", Toast.LENGTH_SHORT).show();
//
//            // Exit this method early because there's nothing left to do
//            return;
//        }
//        else
//            displayQuantity(quantity--);

        if (quantity > 0)
            displayQuantity(quantity--);
        else {
            // Show an error message as a toast
            Toast.makeText(this, "You cannot have less than 1 coffee", Toast.LENGTH_SHORT).show();

            // Exit this method early because there's nothing left to do
            return;
        }

    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int takeQuantity) {
        TextView quantityTextView = (TextView)  findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + takeQuantity);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view){
//        TextView showOutput = (TextView) findViewById(R.id.totalPrice);
//        showOutput.setText("Your order have been placed successfully!!!");
//        int totalPrice = calculatePrice();
//        showPrice(totalPrice);

        CheckBox whippedCreamTopping =  (CheckBox) findViewById(R.id.whippedCreamTopping);
        boolean hasWhippedCreamTopping = whippedCreamTopping.isChecked();

        CheckBox chocolateTopping =  (CheckBox) findViewById(R.id.chocolateTopping);
        boolean hasChocolateTopping = chocolateTopping.isChecked();

        EditText inputName = (EditText) findViewById(R.id.takeName);
        String storeName = inputName.getText().toString();

        int price = calculatePrice(hasWhippedCreamTopping, hasChocolateTopping);

        String priceMessage = orderSummary(price, hasWhippedCreamTopping, hasChocolateTopping, storeName);

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("*/*");
//        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, "Coffee order summary of " + storeName);
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
//        intent.putExtra(Intent.EXTRA_STREAM, attachment);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

//        displayMessage(priceMessage);
    }

//    private void displayMessage(String message){
//        TextView orderSummaryTextView = (TextView) findViewById(R.id.orderSummaryTextView);
//        orderSummaryTextView.setText(message);
//    }

//    /**
//     * Calculates the price of the order.
//     */
//    private void showPrice(int number) {
//        TextView priceTextView = (TextView) findViewById(R.id.totalPrice);
//        priceTextView.setText("That would be ₹ " + number );
//    }


    /**
     * Calculates the price of the order.
     *
     * @param forWhippedCream is used to check whether user wants whipped cream or not.
     * @param forChocolate is used to check whether user wants chocolate or not.
     *
     * @return the total price
     */
    private int calculatePrice(boolean forWhippedCream, boolean forChocolate) {

        int price = quantity * 125;

        // For `whipped cream`.
        if (forWhippedCream)
            price += quantity * 30;

        // For `chocolate`
        if (forChocolate)
            price += quantity * 20;

        return price;
    }

    /**
     * Create an order summary
     *
     * @param price of the order
     * @param toppingWhippedCream is to check whether Whipped cream is included or not.
     *
     * @return text summary
     */
    private String orderSummary(int price, boolean toppingWhippedCream, boolean toppingChocolate, String takeNameInput){
        String priceMessage = getString(R.string.orderSummaryName,takeNameInput);
        priceMessage += "\n" + getString(R.string.orderSummaryQuantity, quantity);

        // For `whipped cream`.
        if (toppingWhippedCream)
            priceMessage += "\n" + getString(R.string.orderSummaryWhippedCream);

        // For `chocolate`
        if (toppingChocolate)
            priceMessage += "\n" + getString((R.string.orderSummaryChocolate));

        priceMessage += "\n" + getString(R.string.orderSummaryPrice, price);
        priceMessage += "\n" + getString(R.string.thankYou);
        return priceMessage;
    }
}