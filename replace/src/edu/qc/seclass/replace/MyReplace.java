package edu.qc.seclass.replace;

/**
 * Created by AneeqShah on 12/12/18.
 */

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MyReplace {

    String[] args;
    String input;
    String from;
    String to;
    String fileName;
    String [] optionLeftHandSideString;
    String [] optionRightHandSideString;
    String fromString [];
    String toString [];

    int numberOfDoubleDash;
    int size;
    int numberOfOptions;
    int numberOfDoubleDashRightHandSide;

    boolean usageError;
    boolean optionFirst;
    boolean optionLast;
    boolean optionInsensitive;
    boolean optionBackup;
    static boolean fileNotFoundExc=false;

    public MyReplace(String args[], int size) {

        this.args= new String[size];
        this.input=null;
        this.from=null;
        this.to=null;
        this.numberOfDoubleDash =0;
        this.size=0;
        this.numberOfOptions =0;
        this.optionBackup =false;
        this.optionFirst =false;
        this.usageError=false;
        this.optionInsensitive =false;
        this.optionLast =false;
        this.numberOfDoubleDashRightHandSide =0;
    }

    public String getResultWithNoOPTIONS(String input) {

        String result=input;
        for(int i=0;i<this.fromString.length;i++) {
            result=result.replaceAll(this.fromString[i], this.toString[i]);
        }
        return result;
    }

    public File WriteToFile(File input, String output) throws Exception {

        File tempFile=input;
        FileWriter fileWriter = new FileWriter(input);
        fileWriter.write(output);
        fileWriter.close();
        return tempFile;
    }

    public void setFrom(String string) {

        this.from=string;
    }

    public void setTo(String string) {

        this.to=string;
    }

    public void setFileName(String string) {

        this.fileName=string;
    }

    public void setNumberOfDoubleDash() {

        int numberOfDoubleDash=0;
        for(int i=0;i<this.args.length;i++) {
            if(this.args[i].equals("--")) {
                numberOfDoubleDash++;
            }
        }
        this.numberOfDoubleDash =numberOfDoubleDash;
    }

    public void setNumberOfDoubleDashRightHandSide() {

        int numberOfDoubleDashRightHandSide=0;
        for(int i = 0; i<this.optionRightHandSideString.length; i++) {
            if(this.optionRightHandSideString[i].equals("--")) {
                numberOfDoubleDashRightHandSide++;
            }
        }
        this.numberOfDoubleDashRightHandSide =numberOfDoubleDashRightHandSide;
    }

    public void setNumberOfOptions() {

        int numberOfOptions=0;
        int i=0;
        while(i<this.args.length) {
            if(this.args[i].equals("--")) {
                break;
            }
            if(this.args[i].equals("-i")||this.args[i].equals("-l")||this.args[i].equals("-f")||this.args[i].equals("-b")) {
                numberOfOptions++;
            }
            i++;
        }
        this.numberOfOptions =numberOfOptions;
    }

    public String getFileName() {
        return this.fileName;
    }

    public String getTo() {
        return this.to;
    }

    public String getFrom() {
        return this.from;
    }

    public int getNumberOfOptions() {
        return this.numberOfOptions;
    }

    public void setArgument(String []arg) {

        for(int i=0;i<arg.length;i++) {
            this.args[i]=arg[i];
        }
        if(arg.length<=2) {
            usage();
        }
    }

    public void setOptionsString(String [] arg) throws Exception {

        int i=0;
        while(i<arg.length) {
            if(arg[i].equals("--")) {
                break;
            }
            i++;
        }
        this.optionLeftHandSideString = new String[i];
        int j=0;
        while(j<i) {
            this.optionLeftHandSideString[j]=arg[j];
            j++;
        }
        int tempIndex=i;
        int rightHandSideLength=0;
        while(tempIndex<arg.length) {
            tempIndex++;
            rightHandSideLength++;
        }
        this.optionRightHandSideString = new String[rightHandSideLength];

        int start=0;
        int tempIndex2=i;
        while(start<this.optionRightHandSideString.length) {
            this.optionRightHandSideString[start]=arg[tempIndex2];
            start++;
            tempIndex2++;
        }
        if(this.optionRightHandSideString.length<=1) {
            usage();
        }
    }

    public void setOptionsType(String []stringLeftHandSide) {

        for(int i=0;i<stringLeftHandSide.length;i++) {
            if(stringLeftHandSide[i].equals("-f")) {
                this.optionFirst =true;
            }else if(stringLeftHandSide[i].equals("-l")) {
                this.optionLast =true;
            }else if(stringLeftHandSide[i].equals("-i")) {
                this.optionInsensitive =true;
            }else if(stringLeftHandSide[i].equals("-b")) {
                this.optionBackup =true;
            }else {
            }
        }
    }

    public void setLeftHandSideRightHandSideNoCommand(String [] leftHandSideString) throws Exception {

        if(this.optionLeftHandSideString.length%2!=0) {
            usage();
        } else {
            this.fromString= new String[this.optionLeftHandSideString.length/2];
            this.toString= new String[this.optionLeftHandSideString.length/2];

            int i=0;
            int fromIndex=0;
            int toIndex=0;

            while(i<this.optionLeftHandSideString.length) {

                if(i%2==0) {
                    this.fromString[fromIndex]=this.optionLeftHandSideString[i];
                    if(this.fromString[fromIndex]=="") {
                        usage();
                        return;
                    }
                    fromIndex++;

                }else {
                    this.toString[toIndex]=this.optionLeftHandSideString[i];
                    toIndex++;
                }
                i++;
            }
        }
    }

    public void setLeftHandSideRightHandSideWithCommand(String []leftHandSideString) throws Exception {

        String []fromToString= new String[leftHandSideString.length-this.getNumberOfOptions()];
        int startInd=0;
        for(int i = this.getNumberOfOptions(); i<leftHandSideString.length; i++) {
            fromToString[startInd]=leftHandSideString[i];
            startInd++;
        }
        if(leftHandSideString.length==this.numberOfOptions) {
            usage();
        }
        if(fromToString.length%2!=0) {
            usage();
        }
        this.fromString= new String[fromToString.length/2];
        this.toString= new String[fromToString.length/2];
        int i=0;
        int fromIndex=0;
        int toIndex=0;
        while(i<fromToString.length) {
            if(i%2==0) {
                this.fromString[fromIndex]=fromToString[i];
                if(this.fromString[fromIndex]=="") {
                    usage();
                }
                fromIndex++;
            }else {
                this.toString[toIndex]=fromToString[i];
                toIndex++;
            }
            i++;
        }
    }

    public void doReplace() throws Exception {

        this.setNumberOfOptions();
        this.setNumberOfDoubleDash();

        if(this.getNumberOfOptions()==0) {

            this.setOptionsString(args);
            this.setNumberOfDoubleDashRightHandSide();
            this.setOptionsType(this.optionLeftHandSideString);
            int startIndex=0;

            if(this.numberOfDoubleDashRightHandSide ==1) {

                this.setLeftHandSideRightHandSideNoCommand(this.optionLeftHandSideString);
                startIndex=1;

            }else if(this.numberOfDoubleDashRightHandSide ==3) {

                fromString= new String[1];
                toString= new String[1];

                this.fromString[0]=this.optionRightHandSideString[1];
                this.toString[0]=this.optionRightHandSideString[2];

                startIndex=4;

            }else if(this.numberOfDoubleDashRightHandSide ==2) {

                if(this.optionRightHandSideString[0].equals("--")&&this.optionRightHandSideString[2].equals("--")) {
                    this.setFrom(this.optionLeftHandSideString[this.getNumberOfOptions()]);
                    this.setTo(this.optionRightHandSideString[1]);
                    startIndex=3;
                }else {

                    fromString= new String[1];
                    toString= new String[1];

                    this.fromString[0]=this.optionRightHandSideString[1];
                    this.toString[0]=this.optionRightHandSideString[2];

                    startIndex=4;
                }
            }else {
                usage();
            }

            for(int i = startIndex; i<this.optionRightHandSideString.length; i++) {

                setFileName(this.optionRightHandSideString[i]);

                File input= new File(this.getFileName());

                String content=getFileContent(this.getFileName());

                if(this.optionBackup ==false&&this.optionFirst ==false&&this.optionFirst ==false&&this.optionInsensitive ==false) {

                    String output=this.getResultWithNoOPTIONS(content);
                    this.WriteToFile(input, output);

                }else {
                    usage();
                }
            }
        }else if(this.getNumberOfOptions()>=1) {

            this.doReplaceWithCommands();
        }else {
            usage();
        }
    }

    public void doReplaceWithCommands() throws Exception {

        this.setOptionsString(args);
        this.setNumberOfDoubleDashRightHandSide();
        this.setOptionsType(this.optionLeftHandSideString);
        int startIndex=0;

        if(this.numberOfDoubleDashRightHandSide ==1) {

            if(this.optionRightHandSideString.length<=1) {
                usage();
            }

            this.setLeftHandSideRightHandSideWithCommand(this.optionLeftHandSideString);
            startIndex=1;

        }else if(this.numberOfDoubleDashRightHandSide ==3) {
            this.setFrom(this.optionRightHandSideString[1]);
            this.setTo(this.optionRightHandSideString[2]);
            startIndex=4;
        }else if(this.numberOfDoubleDashRightHandSide ==2) {
            if(this.optionRightHandSideString[0].equals("--")&&this.optionRightHandSideString[2].equals("--")) {
                this.setFrom(this.optionLeftHandSideString[this.getNumberOfOptions()]);
                this.setTo(this.optionRightHandSideString[1]);
                startIndex=3;
            }else {
                this.setFrom(this.optionRightHandSideString[1]);
                this.setTo(this.optionRightHandSideString[2]);
                startIndex=4;
            }
        }else {
            usage();
        }

        for(int i = startIndex; i<this.optionRightHandSideString.length; i++) {

            if(this.numberOfDoubleDashRightHandSide ==1) {

                setFileName(this.optionRightHandSideString[i]);
                File input= new File(this.getFileName());

                String content=getFileContent(this.getFileName());

                if(content!=null) {

                    if (this.optionBackup ==true) {
                        if((Files.exists(Paths.get(input.getPath()+".bck"))==true)){
                            backupExist(input);
                        }
                        File backupFile = new File(input.getPath()+".bck");
                        FileWriter backupFileWriter = new FileWriter(backupFile);
                        backupFileWriter.write(content);
                        backupFileWriter.close();
                    }

                    if (this.optionFirst ==true) {
                        for(int i1=0;i1<this.fromString.length;i1++) {
                            if (this.optionInsensitive == true) {
                                content = content.replaceFirst("(?i)"+this.fromString[i1], this.toString[i1]);
                            }
                            else {
                                content = content.replaceFirst(this.fromString[i1], this.toString[i1]);
                            }
                        }
                    }

                    if (this.optionLast ==true) {

                        for(int i1=0;i1<this.fromString.length;i1++) {
                            StringBuilder stringBuilderContent = new StringBuilder();

                            stringBuilderContent.append(content);
                            stringBuilderContent.reverse();

                            StringBuilder stringBuilderWordFrom = new StringBuilder();

                            stringBuilderWordFrom.append(this.fromString[i1]);
                            stringBuilderWordFrom.reverse();

                            StringBuilder stringBuilderWordTo = new StringBuilder();
                            stringBuilderWordTo.append(this.toString[i1]);
                            stringBuilderWordTo.reverse();

                            String reverseContent = stringBuilderContent.toString();

                            if (this.optionInsensitive == true) {
                                reverseContent = reverseContent.replaceFirst("(?i)"+stringBuilderWordFrom.toString(), stringBuilderWordTo.toString());
                            }
                            else {
                                reverseContent = reverseContent.replaceFirst(stringBuilderWordFrom.toString(), stringBuilderWordTo.toString());
                            }

                            StringBuilder stringBuilderReverseContent = new StringBuilder();
                            stringBuilderReverseContent.append(reverseContent);
                            stringBuilderReverseContent.reverse();

                            content = stringBuilderReverseContent.toString();
                        }
                    }

                    if ((this.optionFirst ==false)&&(this.optionLast ==false)){

                        for(int i1=0;i1<this.fromString.length;i1++) {

                            if (this.optionInsensitive == true) {
                                content = content.replaceAll("(?i)"+this.fromString[i1], this.toString[i1]);
                            }
                            else {
                                content = content.replaceAll(this.fromString[i1], this.toString[i1]);
                            }
                        }
                    }
                    this.WriteToFile(input, content);
                }
            }else {

                setFileName(this.optionRightHandSideString[i]);
                File input= new File(this.getFileName());
                String content=getFileContent(this.getFileName());

                if(content!=null) {

                    if (this.optionBackup ==true) {

                        if((Files.exists(Paths.get(input.getPath()+".bck"))==true)){
                            backupExist(input);
                        }
                        File backupFile = new File(input.getPath()+".bck");
                        FileWriter backupFileWriter = new FileWriter(backupFile);
                        backupFileWriter.write(content);
                        backupFileWriter.close();
                    }

                    if (this.optionFirst ==true) {

                        if (this.optionInsensitive == true) {
                            content = content.replaceFirst("(?i)"+this.getFrom(), this.getTo());
                        }
                        else {
                            content = content.replaceFirst(this.getFrom(), this.getTo());
                        }
                    }

                    if (this.optionLast ==true) {

                        StringBuilder stringBuilderContent = new StringBuilder();
                        stringBuilderContent.append(content);
                        stringBuilderContent.reverse();

                        StringBuilder stringBuilderWordFrom = new StringBuilder();

                        stringBuilderWordFrom.append(this.getFrom());
                        stringBuilderWordFrom.reverse();

                        StringBuilder stringBuilderWordTo = new StringBuilder();
                        stringBuilderWordTo.append(this.getTo());
                        stringBuilderWordTo.reverse();

                        String reverseContent = stringBuilderContent.toString();

                        if (this.optionInsensitive == true) {
                            reverseContent = reverseContent.replaceFirst("(?i)"+stringBuilderWordFrom.toString(), stringBuilderWordTo.toString());
                        }
                        else {
                            reverseContent = reverseContent.replaceFirst(stringBuilderWordFrom.toString(), stringBuilderWordTo.toString());
                        }

                        StringBuilder stringBuilderReverseContent = new StringBuilder();
                        stringBuilderReverseContent.append(reverseContent);
                        stringBuilderReverseContent.reverse();

                        content = stringBuilderReverseContent.toString();
                    }

                    if ((this.optionFirst ==false)&&(this.optionLast ==false)){
                        if (this.optionInsensitive == true) {
                            content = content.replaceAll("(?i)"+this.getFrom(), this.getTo());
                        }
                        else {
                            content = content.replaceAll(this.getFrom(), this.getTo());
                        }
                    }
                    this.WriteToFile(input, content);
                }
            }
        }
    }

    private void backupExist(File input) {

        System.err.println("Not performing replace for " + input.getName() + ": Backup file already exists");
    }

    public String getFileContent(String filename) {

        Charset charset = StandardCharsets.UTF_8;
        String content = null;
        try {
            content = new String(Files.readAllBytes(Paths.get(filename)), charset);
        } catch (IOException e) {

            fileNotFound(filename);
        }
        return content;
    }

    public void fileNotFound(String filePath) {
        fileNotFoundExc=true;
        File file = new File(filePath);
        System.err.println("File " + file.getName() + " not found");
        file.delete();
    }

    public void usage() {
        if(usageError==false) {
            usageError=true;
            System.err.println("Usage: Replace [-b] [-f] [-l] [-i] <from> <to> -- " + "<filename> [<filename>]*" );
        }
    }
}