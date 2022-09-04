public class newArray {                                                                                                 // Här är class men constructor och getters för att ta time och input tillsamans om det kommer behöva
                                                                                                                        // för att sortera
    public String time;
    public int input;

    public newArray(String time, int input) {
        this.time = time;
        this.input = input;
    }

    public void display() {
        System.out.println(time + " - " + time + " - " + + input + " öre \n");
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getInput() {
        System.out.println(input);
        return input;
    }

    public void setInput(int input) {
        this.input = input;
    }
}
