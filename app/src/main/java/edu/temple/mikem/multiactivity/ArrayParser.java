package edu.temple.mikem.multiactivity;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by mikem on 9/3/2015.
 */
public class ArrayParser {
    private static final String TAG = "ArrayParser";


    //Parses ArrayList in the format of opened by Parent, which was opened by grandparent...
    public static String parse(ArrayList<String> activities) {

        //A string of the parents identifier and lineage
        String lineage = "";

        if (activities != null) {
            //Goes through the lineage of parents in descending order
            for (int i = activities.size(); i > 0; i--) {
                if (activities.size() == i) {
                    lineage += "" + activities.get(i-1);
                } else {
                    lineage += ", which was " + activities.get(i-1);
                }

            }

            return lineage;
        } else {
            //Display no parents
            return "No Parent";
        }
    }
}
