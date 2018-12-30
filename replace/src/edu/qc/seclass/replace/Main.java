package edu.qc.seclass.replace;

public class Main {

    public static void main(String[] args) {
        // TODO: Empty skeleton method

        MyReplace ReplaceUtility= new MyReplace(args, args.length);
        ReplaceUtility.setArgument(args);
        try {
            ReplaceUtility.doReplace();
        } catch (Exception e) {
            if(MyReplace.fileNotFoundExc==false) {
                ReplaceUtility.usage();
            }
        }
    }
}