package Mstring;

import java.util.Arrays;

/**
 * Created by Martin on 2016-09-04.
 */
public class MString {
    private char[] theChars;
    private int length;

    public MString(String str){
        length = str.length();
        theChars = new char[length];
        theChars = str.toCharArray();
    }

    public MString(int i){
        if(i<=0)
            length=10;
        else
            length = i;
        theChars = new char[i];
    }

    public char charAt(int index) {
        if(!checkRange(index))
            return 0;

        return theChars[index];
    }
    public int length() {
        return length;
    }

    public void print(){
        System.out.println(new String(theChars));
    }

    public void replaceCharAt(int index, char ch) {
        if(!checkRange(index))
            return;
        theChars[index] = ch;

    }

    public boolean equals(MString other) {
        if(Arrays.equals(theChars, other.theChars)) //Could also be done by looping through each character and comparing them
            return true;
        else
            return false;
    }

    private boolean checkRange(int index){
        if(index > length || index < 0){
            System.out.println("Index: " +index +" is out of the range(0-"+length+")");
            return false;
        }
        return true;
    }

    private void expand(int mustHaveCapacity) {
        /** Expand creates a temporary array with desired new size and transfer all characters*/

        length = length + mustHaveCapacity; //This could be done where you call the function instead, but this looks nicer
        char[] tempChars = new char[length];

        for(int i = 0; i<length-mustHaveCapacity; i++)
            tempChars[i] = theChars[i];
        theChars = tempChars;
    }

    public void append(char ch) {
        expand(1);
        theChars[length-1] = ch;
    }

    public void append(String str) {
/**     Alternative 1: using string library for much more compact code */
        theChars = (new String(theChars) +str).toCharArray();

/**     Alternative 2: using expand */
//        int j= 0;
//        expand(str.length());
//        for(int i = length-str.length(); i<length;i++){
//                theChars[i] = str.charAt(j++);
//        }
    }


    public void insert(char ch, int index) {

        if(!checkRange(index))
            return;

        expand(1);
        for(int i = theChars.length-2; i>=index;i--)
            theChars[i+1]= theChars[i];
        theChars[index] = ch;
    }

    public void insert(String str, int index) {

        if(!checkRange(index))
            return;
/**     Alternative 1: using string library for more compact code */
        String sub;
        String remainder;
        sub= new String(theChars).substring(0, index);
        remainder= new String(theChars).substring(index);
        theChars = (sub +str +remainder).toCharArray();

/**     Alternative 2: using expand
 *      Loops through theChars and splits it at index
 *      then inserts the new string in between     */

//        char[] tempChar1 = new char[length];
//        char[] tempChar2 = new char[length];
//        for(int i = 0; i < length; i++){
//            if(i < index)
//                tempChar1[i] = theChars[i];
//            else
//                tempChar2[i] = theChars[i];
//        }
//       theChars = new char[length+str.length()];
//        append(new String(tempChar1));
//        append(str);
//        append(new String(tempChar2));



    }

    @Override
    public String toString() {
        return new String(theChars);
    }
}
