package Mstring;

/**
 * Created by Martin on 2016-09-04.
 */
public class Lab1A {

    public static void main(String[] args) {

        simpleMStringTest();
        appendTest();
        insertTest();
        equalsTest();
        // add more tests of specific methods...
    }

    private static void simpleMStringTest() {
        System.out.println("---SIMPLE MSTRING TEST---");

        MString mstr = new MString("Hello!");
        System.out.println(mstr.toString() + ", length=" + mstr.length());


        char c = mstr.charAt(5);
        System.out.println("char at index 5: " + c);

        c = mstr.charAt(100);
        System.out.println("char at index 100 (illegal index): " + c);

        mstr.replaceCharAt(-1, 'v');
        mstr.print();

        mstr.replaceCharAt(5, '?');
        mstr.print();
    }

    private static void testStrings(MString mstr, MString mstr1){
        if(mstr.equals(mstr1))
            System.out.println("'"+mstr.toString()+"'" + " is the same as '"+mstr1.toString()+"'");
        else
            System.out.println("'"+mstr.toString()+"'" + " is not the same as '"+mstr1.toString()+"'");
    }
    private static void equalsTest() {
        System.out.println("---EQUALS TEST---");
        MString mstr = new MString("Hello");

        MString mstr1 = new MString("World!");
        testStrings(mstr, mstr1);

        mstr1 = new MString("Hello");
        testStrings(mstr, mstr1);

    }

    private static void appendTest() {
        System.out.println("---APPEND TEST---");

        MString mstr1 = new MString(5);
        mstr1.print();

        mstr1.append('A'); // append char
        mstr1.print();


        mstr1.append("lphabet"); // append string (and expand)
        mstr1.print();
    }

    public static void insertTest() {
        System.out.println("---INSERT TEST---");

        MString mstr1 = new MString("Toe");
        mstr1.print();

        //insert char
        mstr1.insert('b', 2);
        mstr1.print();
        mstr1.insert(' ', 2);
        mstr1.print();

        // insert string
        mstr1.insert("be or not to ", 22);

        // insert string
        mstr1.insert("be or not to ", 3);
        mstr1.print();
    }
}
